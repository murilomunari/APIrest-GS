package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Ramo;
import br.com.fiap.infra.ConnectionFactory;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class RamoRepository implements Repository<Ramo, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<RamoRepository> instance = new AtomicReference<>();

    private RamoRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static RamoRepository build() {
        instance.compareAndSet(null, new RamoRepository());
        return instance.get();
    }
    @Override
    public Ramo persist(Ramo ramo) {
        var sql = "INSERT INTO TB_RAMO (ID_RAMO, NM_RAMO) VALUES(0, ?)";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql, new String[]{"ID_RAMO"});
            ps.setString(1, ramo.getNome());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                final Long id = rs.getLong(1);
                ramo.setId(id);
            }

        }catch (SQLException e ){
            System.err.println("Não foi posivel salvar o ramo no banco de dados: " + e.getMessage());
        }finally {
            fecharObjetos(null, ps, conn);
        }
        return ramo;
    }

    @Override
    public List<Ramo> findAll() {
        List<Ramo> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_RAMO";
        try {
            ps = con.prepareStatement(sql, new String[]{"ID_RAMO"});
            rs = ps.executeQuery(sql);
            if (rs.isBeforeFirst()){
                while (rs.next()){
                    Long id = rs.getLong("ID_RAMO");
                    String nome = rs.getString("NM_RAMO");
                    list.add(new Ramo(id, nome));
                }
            }

        }catch (SQLException e){
            System.err.println("Não foi possível consultar o ramo\n" + e.getMessage());
        }finally {
            fecharObjetos(rs,ps,con);
        }
        return list;
    }

    @Override
    public Ramo findById(Long id) {
        Ramo ramo = null;
        var sql = "SELECT * FROM TB_RAMO where ID_RAMO=?";

        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()){
                while (rs.next()){
                    String nome = rs.getString("NM_RAMO");
                    ramo = new Ramo(null, nome);
                }
            }
        }catch (SQLException e){
            System.err.println("Não foi possível consultar o ID do ramo");
        }finally {
            fecharObjetos(rs, ps, con);
        }
        return ramo;
    }
}
