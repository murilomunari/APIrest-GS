package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Doacao;
import br.com.fiap.domain.entity.EmpresaTerciera;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DoacaoRepository implements Repository<Doacao, Long>{

    private ConnectionFactory factory;

    private static final AtomicReference<DoacaoRepository> instance = new AtomicReference<>();

    private DoacaoRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static DoacaoRepository build() {
        instance.compareAndSet(null, new DoacaoRepository());
        return instance.get();
    }
    @Override
    public Doacao persist(Doacao doacao) {
        var sql = "INSERT INTO TB_DOACOES(ID_DOACAO, VALOR, TIPO, DATA_DOACAO, EMPRESA_DOADORA) VALUES(0, ?, ?, ?, ?)";

        Connection conn = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql, new String[]{"ID_DOACAO"});
            ps.setDouble(1, doacao.getValor());
            ps.setString(2, doacao.getTipo());
            ps.setDate(3, Date.valueOf(doacao.getData()));

            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()){
                final Long id = rs.getLong(1);
                doacao.setId(id);
            }

        }catch (SQLException e){
            System.err.println("Não foi posivel salvar a doação no banco de dados: " + e.getMessage());
        }finally {
            fecharObjetos(null, ps, conn);
        }
        return doacao;
    }

    @Override
    public List<Doacao> findAll() {
        var sql = "SELECT * FROM TB_DOACOES";
        return executeQuery(sql);
    }

    @Override
    public Doacao findById(Long id) {
        var sql = "SELECT * FROM TB_DOACOES WHERE ID_DOACAO=?";
        List<Doacao> result = executeQuery(sql, id);
        return result.isEmpty() ? null : result.get(0);
    }

    private List<Doacao> executeQuery(String sql, Object... params) {
        List<Doacao> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(sql);
            int paramIndex = 1;
            for (Object param : params) {
                if (param instanceof Long) {
                    ps.setLong(paramIndex, (Long) param);
                } else if (param instanceof String) {
                    ps.setString(paramIndex, (String) param);
                }
                // Adicione outros tipos de parâmetros conforme necessário

                paramIndex++;
            }

            rs = ps.executeQuery();

            EmpresaTerceiraRepository empresaRepo = EmpresaTerceiraRepository.build();

            while (rs.next()) {
                Long id = rs.getLong("ID_DOACAO");
                int valor = rs.getInt("VALOR");
                String tipo = rs.getString("TIPO");
                LocalDate data = (rs.getDate("DATA_DOACAO") != null) ? rs.getDate("DATA_DOACAO").toLocalDate() : null;
                Long idEmpresa = rs.getLong("EMPRESA_DOADORA");
                EmpresaTerciera empresaTerciera = empresaRepo.findById(idEmpresa);
                list.add(new Doacao(id, valor, tipo, data, empresaTerciera));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao executar consulta: " + e.getMessage());
        } finally {
            fecharObjetos(rs, ps, con);
        }
        return list;
    }

}
