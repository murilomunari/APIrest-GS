package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.EmpresaTerciera;
import br.com.fiap.domain.entity.Ramo;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class EmpresaTerceiraRepository implements Repository<EmpresaTerciera,Long> {

    private ConnectionFactory factory;

    private static final AtomicReference<EmpresaTerceiraRepository> instance = new AtomicReference<>();

    private EmpresaTerceiraRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static EmpresaTerceiraRepository build() {
        instance.compareAndSet(null, new EmpresaTerceiraRepository());
        return instance.get();
    }
    @Override
    public EmpresaTerciera persist(EmpresaTerciera empresaTerciera) {
        var sql = "INSERT INTO TB_EMPRESA (ID_EMPRESA, NM_EMPRESA, NR_CNPJ, RAMO) VALUES (0,?,?,?)";

        Connection conn  = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement( sql, new String[]{"ID_EMPRESA"} );
            ps.setString(1, empresaTerciera.getCnpj());
            ps.setString(2, empresaTerciera.getNome());
            ps.setString(3, empresaTerciera.getEmail());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                final Long id = rs.getLong(1);
                empresaTerciera.setId(id);
            }
        }catch (SQLException e){
            System.err.println("Não foi posivel salvar a empresa no banco de dados: " + e.getMessage());
        }finally {
            fecharObjetos(null, ps, conn);
        }
        return empresaTerciera;
    }

    @Override
    public List<EmpresaTerciera> findAll() {
        List<EmpresaTerciera> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_EMPRESA";
        try {
            ps = con.prepareStatement(sql, new String[]{"ID_EMPRESA"});
            rs = ps.executeQuery(sql);
            if (rs.isBeforeFirst()){
                RamoRepository ramoRepo = RamoRepository.build();
                while (rs.next()){
                    Long id = rs.getLong("ID_EMPRESA");
                    String nome = rs.getString("NM_EMPRESA");
                    String cnpj = rs.getString("NR_CNPJ");
                    String email = rs.getString("EMAIL");
                    Long idRamo = rs.getLong("RAMO");
                    Ramo ramo = ramoRepo.findById(idRamo);
                    list.add(new EmpresaTerciera(id, nome, cnpj, email, ramo));
                }
            }
        }catch (SQLException e){
            System.err.println("Não foi possível consultar a empresa\n" + e.getMessage());
        }finally {
            fecharObjetos(rs, ps, con);
        }
        return list;
    }

    @Override
    public EmpresaTerciera findById(Long aLong) {
        return null;
    }
}
