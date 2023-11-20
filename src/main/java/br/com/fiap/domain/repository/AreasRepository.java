package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Areas;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AreasRepository implements Repository<Areas, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<AreasRepository> instance = new AtomicReference<>();

    private AreasRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static AreasRepository build() {
        instance.compareAndSet(null, new AreasRepository());
        return instance.get();
    }

    @Override
    public Areas persist(Areas areas) {
        var sql = "INSERT INTO TB_AREAS(ID_AREA, LOCAL) VALUES(0, ?)";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql, new String[]{"ID_AREA"});
            ps.setString(1, areas.getLocal());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                final Long id = rs.getLong(1);
                areas.setId(id);
            }
        }catch (SQLException e){
            System.err.println("Não foi posivel salvar a Area no banco de dados: " + e.getMessage());
        }finally {
            fecharObjetos(null, ps, conn);
        }
        return areas;
    }

    @Override
    public List<Areas> findAll() {
        List<Areas> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_AREAS";
        try {
            ps = con.prepareStatement(sql, new String[]{"ID_AREA"});
            rs = ps.executeQuery(sql);
            if (rs.isBeforeFirst()){
                while (rs.next()){
                    Long id = rs.getLong("ID_AREA");
                    String local = rs.getString("LOCAL");
                    list.add(new Areas(id, local));
                }
            }
        }catch (SQLException e){
            System.err.println("Não foi possível consultar a area\n" + e.getMessage());
        }finally {
            fecharObjetos(rs,ps,con);
        }
        return list;
    }

    @Override
    public Areas findById(Long id) {
        Areas areas = null;
        var sql = "SELECT * FROM TB_AREAS where ID_AREA=?";

        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()){
                while (rs.next()){
                    String local = rs.getString("LOCAL");
                    areas = new Areas(null,local);
                }
            }

        }catch (SQLException e){
            System.err.println("Não foi possível consultar o ID da area" +e.getMessage());
        }finally {
            fecharObjetos(rs, ps, con);
        }
        return areas;
    }
}
