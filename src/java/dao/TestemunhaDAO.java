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
import java.util.ArrayList;
import java.util.List;
import modelo.Municipio;
import modelo.Profissao;
import modelo.Testemunha;
import util.Conexao;

/**
 *
 * @author user
 */
public class TestemunhaDAO implements GenericoDAO<Testemunha> {

    private static final String INSERIR = "INSERT INTO testemunha (nome_testemunha, pai_testemunha, mae_testemunha, bi_testemunha, residencia_testemunha, data_nascimento_testemunha, sexo_testemunha, proximidade_testemunha, estado_civil_testemunha, data_emissao_bi_testemunha, data_validade_bi_testemunha, telefone_testemunha, id_municipio, id_profissao) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE testemunha SET nome_testemunha = ?, pai_testemunha = ?, mae_testemunha = ?, bi_testemunha = ?, residencia_testemunha = ?, data_nascimento_testemunha = ?, sexo_testemunha = ?, proximidade_testemunha = ?, estado_civil_testemunha = ?, data_emissao_bi_testemunha = ?, data_validade_bi_testemunha = ?, telefone_testemunha = ?, id_municipio = ? WHERE id_testemunha = ?, id_profissao = ?";
    private static final String ELIMINAR = "DELETE FROM testemunha WHERE id_testemunha = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM testemunha t INNER JOIN municipio m ON t.id_municipio = m.id_municipio WHERE id_testemunha = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM testemunha t INNER JOIN municipio m ON t.id_municipio = m.id_municipio INNER JOIN profissao p ON t.id_profissao = p.id_profissao";
    private static final String LISTAR_POR_NOME = "SELECT * FROM testemunha t INNER JOIN municipio m ON t.id_municipio = m.id_municipio INNER JOIN profissao p ON t.id_profissao = p.id_profissao WHERE t.nome_testemunha LIKE ? ORDER BY t.nome_testemunha";
    private static final String LISTAR_POR_BI = "SELECT * FROM testemunha t INNER JOIN municipio m ON t.id_municipio = m.id_municipi INNER JOIN profissao p ON t.id_profissao = p.id_profissaoo WHERE t.bi_testemunha LIKE ? ORDER BY t.nome_testemunha";
    private static final String LISTAR_POR_DATA = "SELECT * FROM testemunha t INNER JOIN municipio m ON t.id_municipio = m.id_municipio INNER JOIN profissao p ON t.id_profissao = p.id_profissao WHERE t.data_nascimento_testemunha LIKE ? ORDER BY t.nome_testemunha";

    @Override
    public void save(Testemunha testemunha) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (testemunha == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, testemunha.getNomeTestemunha());
            ps.setString(2, testemunha.getPaiTestemunha());
            ps.setString(3, testemunha.getMaeTestemunha());
            ps.setString(4, testemunha.getBiTestemunha());
            ps.setString(5, testemunha.getResidenciaTestemunha());
            ps.setDate(6, new java.sql.Date(testemunha.getDataNascimentoTestemunha().getTime()));
            ps.setString(7, testemunha.getSexo().getExtensao()); // Fixar bm isso
            ps.setString(8, testemunha.getProximidadeTestemunha());
            ps.setString(9, testemunha.getEstadoCivilTestemunha());
            ps.setDate(10, new java.sql.Date(testemunha.getDataEmissaoBiTestemunha().getTime()));
            ps.setDate(11, new java.sql.Date(testemunha.getDataValidadeBiTestemunha().getTime()));
            ps.setString(12, testemunha.getTelefoneTestemunha());
            ps.setInt(13, testemunha.getMunicipio().getIdMunicipio());
            ps.setInt(14, testemunha.getProfissao().getIdProfissao());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Testemunha testemunha) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (testemunha == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, testemunha.getNomeTestemunha());
            ps.setString(2, testemunha.getPaiTestemunha());
            ps.setString(3, testemunha.getMaeTestemunha());
            ps.setString(4, testemunha.getBiTestemunha());
            ps.setString(5, testemunha.getResidenciaTestemunha());
            ps.setDate(6, new java.sql.Date(testemunha.getDataNascimentoTestemunha().getTime()));
            ps.setString(7, testemunha.getSexo().getExtensao()); // Fixar bm isso
            ps.setString(8, testemunha.getProximidadeTestemunha());
            ps.setString(9, testemunha.getEstadoCivilTestemunha());
            ps.setDate(10, new java.sql.Date(testemunha.getDataEmissaoBiTestemunha().getTime()));
            ps.setDate(11, new java.sql.Date(testemunha.getDataValidadeBiTestemunha().getTime()));
            ps.setString(12, testemunha.getTelefoneTestemunha());
            ps.setInt(13, testemunha.getMunicipio().getIdMunicipio());
            ps.setInt(14, testemunha.getProfissao().getIdProfissao());
            ps.setInt(15, testemunha.getIdTestemunha());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Testemunha testemunha) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (testemunha == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
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
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Testemunha testemunha = new Testemunha();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(testemunha, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return testemunha;
    }

    @Override
    public List<Testemunha> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Testemunha> testemunhas = new ArrayList<>();
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
            System.err.println("Erro ao LER dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return testemunhas;
    }

    public List<Testemunha> findByNome(String nome) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Testemunha> testemunhas = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum testemunha com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return testemunhas;
    }

    public List<Testemunha> findByData(java.sql.Date data) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Testemunha> testemunhas = new ArrayList<>();

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

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhuma testemunha com nome: " + data);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return testemunhas;
    }

    public List<Testemunha> findByBi(String numeroBI) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Testemunha> testemunhas = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_BI);
            ps.setString(1, "%" + numeroBI + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhuma testemunha com nome: " + numeroBI);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return testemunhas;
    }

    private void popularComDados(Testemunha testemunha, ResultSet rs) {
        try {
            testemunha.setIdTestemunha(rs.getInt("t.id_testemunha"));
            testemunha.setNomeTestemunha(rs.getString("t.nome_testemunha"));
            testemunha.setPaiTestemunha(rs.getString("t.pai_testemunha"));
            testemunha.setMaeTestemunha(rs.getString("t.mae_testemunha"));
            testemunha.setBiTestemunha(rs.getString("t.bi_testemunha"));
            testemunha.setResidenciaTestemunha(rs.getString("t.residencia_testemunha"));
            testemunha.setDataNascimentoTestemunha(rs.getDate("t.data_nascimento_testemunha"));
            testemunha.setSexo(testemunha.getSexo().getExtensao(rs.getString("t.sexo_testemunha")));
            testemunha.setProximidadeTestemunha(rs.getString("t.proximidade_testemunha"));
            testemunha.setEstadoCivilTestemunha(rs.getString("t.estado_civil_testemunha"));
            testemunha.setDataEmissaoBiTestemunha(rs.getDate("t.data_emissao_bi_testemunha"));
            testemunha.setDataValidadeBiTestemunha(rs.getDate("t.data_validade_bi_testemunha"));
            testemunha.setTelefoneTestemunha(rs.getString("t.telefone_testemunha"));

            Municipio municipio = new Municipio();
            municipio.setIdMunicipio(rs.getInt("m.id_municipio"));
            municipio.setNomeMunicipio(rs.getString("m.nome_municipio"));
            testemunha.setMunicipio(municipio);

            Profissao profissao = new Profissao();
            profissao.setIdProfissao(rs.getInt("p.id_profissao"));
            profissao.setNomeProfissao(rs.getString("p.nome_profissao"));
            testemunha.setProfissao(profissao);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados da testemunha: " + ex.getLocalizedMessage());
        }
    }

}
