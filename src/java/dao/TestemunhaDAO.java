/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.Municipio;
import modelo.Profissao;
import modelo.Sexo;
import modelo.Testemunha;
import util.Conexao;

/**
 *
 * @author user
 */
public class TestemunhaDAO implements GenericoDAO<Testemunha> {

    private static final String CAMPOS_CONSULTA
            = "t.id_testemunha, "
            + "t.nome_testemunha, "
            + "t.pai_testemunha, "
            + "t.mae_testemunha, "
            + "t.bi_testemunha, "
            + "t.residencia_testemunha, "
            + "t.data_nascimento_testemunha, "
            + "t.sexo_testemunha, "
            + "t.proximidade_testemunha, "
            + "t.estado_civil_testemunha, "
            + "t.data_emissao_bi_testemunha, "
            + "t.data_validade_bi_testemunha, "
            + "t.telefone_testemunha, "
            + "m.id_municipio, "
            + "m.nome_municipio, "
            + "p.id_profissao, "
            + "p.nome_profissao";

    private static final String INSERIR
            = "INSERT INTO testemunha "
            + "(nome_testemunha, pai_testemunha, mae_testemunha, bi_testemunha, residencia_testemunha, "
            + "data_nascimento_testemunha, sexo_testemunha, proximidade_testemunha, estado_civil_testemunha, "
            + "data_emissao_bi_testemunha, data_validade_bi_testemunha, telefone_testemunha, "
            + "id_municipio, id_profissao) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String ACTUALIZAR
            = "UPDATE testemunha SET "
            + "nome_testemunha = ?, "
            + "pai_testemunha = ?, "
            + "mae_testemunha = ?, "
            + "bi_testemunha = ?, "
            + "residencia_testemunha = ?, "
            + "data_nascimento_testemunha = ?, "
            + "sexo_testemunha = ?, "
            + "proximidade_testemunha = ?, "
            + "estado_civil_testemunha = ?, "
            + "data_emissao_bi_testemunha = ?, "
            + "data_validade_bi_testemunha = ?, "
            + "telefone_testemunha = ?, "
            + "id_municipio = ?, "
            + "id_profissao = ? "
            + "WHERE id_testemunha = ?";

    private static final String ELIMINAR
            = "DELETE FROM testemunha WHERE id_testemunha = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM testemunha t "
            + "INNER JOIN municipio m ON t.id_municipio = m.id_municipio "
            + "INNER JOIN profissao p ON t.id_profissao = p.id_profissao "
            + "WHERE t.id_testemunha = ?";

    private static final String LISTAR_TUDO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM testemunha t "
            + "INNER JOIN municipio m ON t.id_municipio = m.id_municipio "
            + "INNER JOIN profissao p ON t.id_profissao = p.id_profissao "
            + "ORDER BY t.nome_testemunha";

    private static final String LISTAR_POR_NOME
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM testemunha t "
            + "INNER JOIN municipio m ON t.id_municipio = m.id_municipio "
            + "INNER JOIN profissao p ON t.id_profissao = p.id_profissao "
            + "WHERE t.nome_testemunha LIKE ? "
            + "ORDER BY t.nome_testemunha";

    private static final String LISTAR_POR_BI
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM testemunha t "
            + "INNER JOIN municipio m ON t.id_municipio = m.id_municipio "
            + "INNER JOIN profissao p ON t.id_profissao = p.id_profissao "
            + "WHERE t.bi_testemunha LIKE ? "
            + "ORDER BY t.nome_testemunha";

    private static final String LISTAR_POR_DATA
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM testemunha t "
            + "INNER JOIN municipio m ON t.id_municipio = m.id_municipio "
            + "INNER JOIN profissao p ON t.id_profissao = p.id_profissao "
            + "WHERE t.data_nascimento_testemunha = ? "
            + "ORDER BY t.nome_testemunha";

    @Override
    public void save(Testemunha testemunha) {
        if (testemunha == null) {
            System.err.println("Erro ao INSERIR testemunha: o objeto testemunha não pode ser nulo.");
            return;
        }

        if (testemunha.getMunicipio() == null) {
            System.err.println("Erro ao INSERIR testemunha: o município não pode ser nulo.");
            return;
        }

        if (testemunha.getProfissao() == null) {
            System.err.println("Erro ao INSERIR testemunha: a profissão não pode ser nula.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            preencherPreparedStatement(ps, testemunha, false);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Testemunha testemunha) {
        if (testemunha == null) {
            System.err.println("Erro ao ACTUALIZAR testemunha: o objeto testemunha não pode ser nulo.");
            return;
        }

        if (testemunha.getMunicipio() == null) {
            System.err.println("Erro ao ACTUALIZAR testemunha: o município não pode ser nulo.");
            return;
        }

        if (testemunha.getProfissao() == null) {
            System.err.println("Erro ao ACTUALIZAR testemunha: a profissão não pode ser nula.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            preencherPreparedStatement(ps, testemunha, true);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Testemunha testemunha) {
        if (testemunha == null) {
            System.err.println("Erro ao ELIMINAR testemunha: o objeto testemunha não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, testemunha.getIdTestemunha());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Testemunha findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR testemunha: o id não pode ser nulo.");
            return null;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                return testemunha;
            }

            System.err.println("Não foi encontrada nenhuma testemunha com id: " + id);

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Testemunha> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao LISTAR dados das testemunhas: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
    }

    public List<Testemunha> findByNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        if (nome == null) {
            nome = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

            if (testemunhas.isEmpty()) {
                System.err.println("Não foi encontrada nenhuma testemunha com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da testemunha por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
    }

    public List<Testemunha> findByData(java.sql.Date data) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        if (data == null) {
            System.err.println("Erro ao BUSCAR testemunha: a data não pode ser nula.");
            return testemunhas;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_DATA);

            ps.setDate(1, data);

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

            if (testemunhas.isEmpty()) {
                System.err.println("Não foi encontrada nenhuma testemunha com a data: " + data);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da testemunha por data: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
    }

    public List<Testemunha> findByBi(String numeroBI) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        if (numeroBI == null) {
            numeroBI = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_BI);

            ps.setString(1, "%" + numeroBI.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

            if (testemunhas.isEmpty()) {
                System.err.println("Não foi encontrada nenhuma testemunha com o número do B.I.: " + numeroBI);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da testemunha por B.I.: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
    }

    private void preencherPreparedStatement(
            PreparedStatement ps,
            Testemunha testemunha,
            boolean actualizar
    ) throws SQLException {

        ps.setString(1, testemunha.getNomeTestemunha());
        ps.setString(2, testemunha.getPaiTestemunha());
        ps.setString(3, testemunha.getMaeTestemunha());
        ps.setString(4, testemunha.getBiTestemunha());
        ps.setString(5, testemunha.getResidenciaTestemunha());

        setDate(ps, 6, testemunha.getDataNascimentoTestemunha());

        if (testemunha.getSexo() != null) {
            ps.setString(7, testemunha.getSexo().getExtensao());
        } else {
            ps.setNull(7, Types.VARCHAR);
        }

        ps.setString(8, testemunha.getProximidadeTestemunha());
        ps.setString(9, testemunha.getEstadoCivilTestemunha());

        setDate(ps, 10, testemunha.getDataEmissaoBiTestemunha());
        setDate(ps, 11, testemunha.getDataValidadeBiTestemunha());

        ps.setString(12, testemunha.getTelefoneTestemunha());
        ps.setInt(13, testemunha.getMunicipio().getIdMunicipio());
        ps.setInt(14, testemunha.getProfissao().getIdProfissao());

        if (actualizar) {
            ps.setInt(15, testemunha.getIdTestemunha());
        }
    }

    private void setDate(PreparedStatement ps, int index, java.util.Date data) throws SQLException {
        if (data != null) {
            ps.setDate(index, new java.sql.Date(data.getTime()));
        } else {
            ps.setNull(index, Types.DATE);
        }
    }

    private void popularComDados(Testemunha testemunha, ResultSet rs) throws SQLException {
        testemunha.setIdTestemunha(rs.getInt("id_testemunha"));
        testemunha.setNomeTestemunha(rs.getString("nome_testemunha"));
        testemunha.setPaiTestemunha(rs.getString("pai_testemunha"));
        testemunha.setMaeTestemunha(rs.getString("mae_testemunha"));
        testemunha.setBiTestemunha(rs.getString("bi_testemunha"));
        testemunha.setResidenciaTestemunha(rs.getString("residencia_testemunha"));
        testemunha.setDataNascimentoTestemunha(rs.getDate("data_nascimento_testemunha"));

        String sexoTestemunha = rs.getString("sexo_testemunha");
        testemunha.setSexo(Sexo.getExtensao(sexoTestemunha));

        testemunha.setProximidadeTestemunha(rs.getString("proximidade_testemunha"));
        testemunha.setEstadoCivilTestemunha(rs.getString("estado_civil_testemunha"));
        testemunha.setDataEmissaoBiTestemunha(rs.getDate("data_emissao_bi_testemunha"));
        testemunha.setDataValidadeBiTestemunha(rs.getDate("data_validade_bi_testemunha"));
        testemunha.setTelefoneTestemunha(rs.getString("telefone_testemunha"));

        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(rs.getInt("id_municipio"));
        municipio.setNomeMunicipio(rs.getString("nome_municipio"));
        testemunha.setMunicipio(municipio);

        Profissao profissao = new Profissao();
        profissao.setIdProfissao(rs.getInt("id_profissao"));
        profissao.setNomeProfissao(rs.getString("nome_profissao"));
        testemunha.setProfissao(profissao);
    }

    private void fecharResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar ResultSet: " + ex.getLocalizedMessage());
            }
        }
    }
}
