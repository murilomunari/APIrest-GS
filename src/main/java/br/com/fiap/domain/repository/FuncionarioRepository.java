package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.EmpresaTerciera;
import br.com.fiap.domain.entity.Funcionarios;
import br.com.fiap.infra.ConnectionFactory;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FuncionarioRepository implements Repository<Funcionarios, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<FuncionarioRepository> instance = new AtomicReference<>();

    private FuncionarioRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static FuncionarioRepository build() {
        instance.compareAndSet(null, new FuncionarioRepository());
        return instance.get();
    }
    @Override
    public Funcionarios persist(Funcionarios funcionarios) {
        var sql = "INSERT INTO TB_FUNCIONARIOS (ID_FUNCIONARIO, NM_FUNCIONARIO, NR_CPF, SETOR, EMPRESA) VALUES (0,?,?,?,?)";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement( sql, new String[]{"ID_FUNCIONARIO"} );
            ps.setString(1, funcionarios.getNome());
            ps.setString(2, funcionarios.getCpf());
            ps.setString(3, funcionarios.getSetor());

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                final Long id = rs.getLong(1);
                funcionarios.setId(id);
            }
        }catch (SQLException e){
            System.err.println("Não foi posivel salvar a bicicleta no banco de dados: " + e.getMessage());
        }finally {
            fecharObjetos(null, ps, conn);
        }
        return funcionarios;
    }

    @Override
    public List<Funcionarios> findAll() {
        List<Funcionarios> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_FUNCIONARIOS";
        try {
            ps = con.prepareStatement(sql, new String[]{"ID_FUNCIONARIO"});
            rs = ps.executeQuery(sql);
            if (rs.isBeforeFirst()){
                EmpresaTerceiraRepository empresaTerceiraRepo = EmpresaTerceiraRepository.build();
                while (rs.next()){
                    Long id = rs.getLong("ID_FUNCIONARIO");
                    String nome = rs.getString("NM_FUNCIONARIO");
                    String cpf = rs.getString("NR_CPF");
                    String setor = rs.getString("SETOR");
                    Long idEmpresa = rs.getLong("EMPRESA");
                    EmpresaTerciera empresa = empresaTerceiraRepo.findById(idEmpresa);
                    list.add(new Funcionarios(id, nome, cpf, setor,empresa));
                }
            }

        }catch (SQLException e){
            System.err.println("Não foi possível consultar o funcionario\n" + e.getMessage());
        }finally {
            fecharObjetos(rs,ps,con);
        }
        return list;
    }

    @Override
    public Funcionarios findById(Long id) {
        Funcionarios funcionarios = null;
        var sql = "SELECT * FROM TB_FUNCIONARIOS where ID_FUNCIONARIO=?";

        Connection con  = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()){
                EmpresaTerceiraRepository empresaTerceiraRepo = EmpresaTerceiraRepository.build();
                while (rs.next()){
                    String nome = rs.getString("NM_FUNCIONARIO");
                    String cpf = rs.getString("NR_CPF");
                    String setor = rs.getString("SETOR");
                    Long idEmpresa = rs.getLong("EMPRESA");
                    EmpresaTerciera empresa = empresaTerceiraRepo.findById(idEmpresa);
                    funcionarios = new Funcionarios(null, nome, cpf, setor, empresa);
                }
            }
        }catch (SQLException e){
            System.err.println("Não foi possível consultar o ID do funcionario");
        }finally {
            fecharObjetos(rs, ps, con);
        }
        return funcionarios;
    }
}
