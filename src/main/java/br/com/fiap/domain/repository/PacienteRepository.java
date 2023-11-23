package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Paciente;
import br.com.fiap.infra.ConnectionFactory;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PacienteRepository implements Repository<Paciente, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<PacienteRepository> instance = new AtomicReference<>();

    private PacienteRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static PacienteRepository build() {
        instance.compareAndSet(null, new PacienteRepository());
        return instance.get();
    }
    @Override
    public Paciente persist(Paciente paciente) {
        var sql = "INSERT INTO TB_PACIENTE (ID_PACIENTE, NM_PACIENTE, CPF, NASCIMENTO) VALUES (0,?,?,?,?";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql, new String[]{"ID_PACIENTE"});
            ps.setString(1, paciente.getNome());
            ps.setDate(2, Date.valueOf(paciente.getDataNascimento()));
            ps.setString(4, paciente.getCpf());


            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                final Long id = rs.getLong(1);
                paciente.setId(id);
            }
        }catch (SQLException e){
            System.err.println("Não foi posivel salvar o paciente no banco de dados: " + e.getMessage());
        }finally {
            fecharObjetos(null, ps, conn);
        }
        return paciente;
    }

    @Override
    public List<Paciente> findAll() {
        List<Paciente> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_PACIENTE";
        try {
            ps = con.prepareStatement(sql, new String[]{"ID_PACIENTE"});
            rs = ps.executeQuery(sql);
            if (rs.isBeforeFirst()){
                while (rs.next()) {
                    Long id = rs.getLong("ID_PACIENTE");
                    String nome = rs.getString("NM_PACIENTE");
                    LocalDate nascimento = (rs.getDate("NASCIMENTO") != null) ? rs.getDate("NASCIMENTO").toLocalDate() : null;
                    String cpf = rs.getString("CPF");
                    list.add(new Paciente(id, nome, cpf, nascimento));
                }
            }
        }catch (SQLException e){
            System.err.println("Não foi possível consultar o paciente\n" + e.getMessage());
        }finally {
            fecharObjetos(rs,ps,con);
        }
        return list;
    }

    @Override
    public Paciente findById(Long id) {
        Paciente paciente = null;
        var sql = "SELECT * FROM TB_PACIENTE where ID_PACIENTE=?";

        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()){
                while (rs.next()){
                    String nome = rs.getString("NM_PACIENTE");
                    LocalDate nascimento = rs.getDate("NASCIMENTO").toLocalDate();
                    String cpf = rs.getString("CPF");
                    paciente = new Paciente(null, nome, cpf, nascimento);
                }
            }
        }catch (SQLException e){
            System.err.println("Não foi possível consultar o ID do paciente");
        }finally {
            fecharObjetos(rs, ps, con);
        }
        return paciente;
    }

}
