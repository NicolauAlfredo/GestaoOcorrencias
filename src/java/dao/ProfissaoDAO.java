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
import modelo.Profissao;
import util.Conexao;

/**
 *
 * @author user
 */
public class ProfissaoDAO implements GenericoDAO<Profissao> {

    private static final String INSERIR = "INSERT INTO profissao (nome_profissao) VALUES(?)";
    private static final String ACTUALIZAR = "UPDATE profissao SET nome_profissao = ? WHERE id_profissao = ?";
    private static final String ELIMINAR = "DELETE FROM profissao WHERE id_profissao = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM profissao WHERE id_profissao = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM profissao";
    private static final String LISTAR_POR_NOME = "SELECT * FROM profissao WHERE nome_profissao LIKE ? ORDER BY nome_profissao";

    @Override
    public void save(Profissao profissao) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (profissao == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, profissao.getNomeProfissao());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da profissao: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Profissao profissao) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (profissao == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, profissao.getNomeProfissao());
            ps.setInt(2, profissao.getIdProfissao());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da profissao: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Profissao profissao) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (profissao == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, profissao.getIdProfissao());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados da profissao: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Profissao findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Profissao profissao = new Profissao();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(profissao, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da profissao: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return profissao;
    }

    @Override
    public List<Profissao> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Profissao> profissoes = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profissao profissao = new Profissao();
                popularComDados(profissao, rs);
                profissoes.add(profissao);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao LER dados da profissao: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return profissoes;
    }

    public List<Profissao> findByNome(String nome) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Profissao> profissaos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Profissao profissao = new Profissao();
                popularComDados(profissao, rs);
                profissaos.add(profissao);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhuma profissão com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do profissão: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return profissaos;
    }

    private void popularComDados(Profissao profissao, ResultSet rs) {
        try {
            profissao.setIdProfissao(rs.getInt("id_profissao"));
            profissao.setNomeProfissao(rs.getString("nome_profissao"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados da profissao: " + ex.getLocalizedMessage());
        }
    }
}
