package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Servicos;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ServicosRepository implements Repository<Servicos, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<ServicosRepository> instance = new AtomicReference<>();

    private ServicosRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static ServicosRepository build() {
        instance.compareAndSet(null, new ServicosRepository());
        return instance.get();
    }
    @Override
    public Servicos persist(Servicos servicos) {
        var sql = "INSERT INTO TB_SERVICOS (ID_SERVICO, DT_SERVICO, TIPO) VALUES (0,?,?)";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement( sql, new String[]{"ID_SERVICO"} );
            ps.setString(1, servicos.getDescricao());
            ps.setString(2, servicos.getTipo());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                final Long id = rs.getLong(1);
                servicos.setId(id);
            }
        }catch (SQLException e){
            System.err.println("Não foi posivel salvar o serviço no banco de dados: " + e.getMessage());
        }finally {
            fecharObjetos(null, ps, conn);
        }
        return servicos;
    }


    @Override
    public List<Servicos> findAll() {
        List<Servicos> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_BICICLETA";
        try {
            ps = con.prepareStatement( sql, new String[]{"ID_SERVICO"} );
            rs = ps.executeQuery(sql);
            if (rs.isBeforeFirst()){
                while (rs.next()){
                    Long id = rs.getLong("ID_SERVICO");
                    String descricao = rs.getString("DT_SERVICO");
                    String tipo = rs.getString("TIPO");
                    list.add(new Servicos(id, descricao, tipo));
                }
            }

        }catch (SQLException e){
            System.err.println("Não foi possível consultar a bicicleta\n" + e.getMessage());
        }finally {
            fecharObjetos(rs,ps,con);
        }
        return list;
    }

    @Override
    public Servicos findById(Long id) {
        Servicos servicos = null;
        var sql = "SELECT * FROM TB_SERVICOS where ID_SERVICO=?";

        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()){

                while (rs.next()){
                    String descricao = rs.getString("DT_SERVICO");
                    String tipo = rs.getString("TIPO");
                    servicos = new Servicos(null, descricao, tipo);
                }
            }
        }catch (SQLException e){
            System.err.println("Não foi possível consultar o ID do serviço");
        }finally {
            fecharObjetos(rs, ps, con);
        }
        return servicos;
    }
}
