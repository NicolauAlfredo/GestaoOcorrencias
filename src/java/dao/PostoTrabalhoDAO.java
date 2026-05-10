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
import modelo.PostoTrabalho;
import util.Conexao;

/**
 *
 * @author user
 */
public class PostoTrabalhoDAO implements GenericoDAO<PostoTrabalho> {

    private static final String INSERIR = "INSERT INTO posto_trabalho (nome_posto_trabalho, numero_posto_trabalho, id_municipio) VALUES(?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE posto_trabalho SET nome_posto_trabalho = ?, numero_posto_trabalho = ?, id_municipio = ? WHERE id_posto_trabalho = ?";
    private static final String ELIMINAR = "DELETE FROM posto_trabalho WHERE id_posto_trabalho = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM posto_trabalho p INNER JOIN municipio m ON p.id_municipio = m.id_municipio WHERE p.id_posto_trabalho = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM posto_trabalho p INNER JOIN municipio m ON p.id_municipio = m.id_municipio";
    private static final String LISTAR_POR_NOME = "SELECT * FROM posto_trabalho p INNER JOIN municipio m ON p.id_municipio = m.id_municipio WHERE p.nome_posto_trabalho LIKE ? ORDER BY p.nome_posto_trabalho";
    private static final String LISTAR_POR_NUMERO = "SELECT * FROM posto_trabalho p INNER JOIN municipio m ON p.id_municipio = m.id_municipio WHERE p.numero_posto_trabalho = ? ORDER BY p.numero_posto_trabalho";
    private static final String LISTAR_POR_MUNICIPIO = "SELECT * FROM posto_trabalho p INNER JOIN municipio m ON p.id_municipio = m.id_municipio WHERE m.nome_municipio LIKE ? ORDER BY m.nome_municipio";

    @Override
    public void save(PostoTrabalho postoTrabalho) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (postoTrabalho == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, postoTrabalho.getNomePostoTrabalho());
            ps.setInt(2, postoTrabalho.getNumeroPostoTrabalho());
            ps.setInt(3, postoTrabalho.getMunicipio().getIdMunicipio());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do posto de trabalho: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(PostoTrabalho postoTrabalho) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (postoTrabalho == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, postoTrabalho.getNomePostoTrabalho());
            ps.setInt(2, postoTrabalho.getNumeroPostoTrabalho());
            ps.setInt(3, postoTrabalho.getMunicipio().getIdMunicipio());
            ps.setInt(4, postoTrabalho.getIdPostoTrabalho());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados do posto de trabalho: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(PostoTrabalho postoTrabalho) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (postoTrabalho == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, postoTrabalho.getIdPostoTrabalho());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados do posto de trabalho: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public PostoTrabalho findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        PostoTrabalho postoTrabalho = new PostoTrabalho();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(postoTrabalho, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do posto de trabalho: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return postoTrabalho;
    }

    @Override
    public List<PostoTrabalho> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<PostoTrabalho> postoTrabalhos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postoTrabalhos.add(postoTrabalho);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao LER dados do posto de trabalho: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return postoTrabalhos;
    }

    public List<PostoTrabalho> findByNome(String nome) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<PostoTrabalho> postoTrabalhos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postoTrabalhos.add(postoTrabalho);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum Posto Trabalho com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do Posto Trabalho: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return postoTrabalhos;
    }

    public List<PostoTrabalho> findByNumero(Integer numero) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<PostoTrabalho> postoTrabalhos = new ArrayList<>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NUMERO);
            ps.setInt(1, numero);
            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postoTrabalhos.add(postoTrabalho);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum posto trabalho com a numero: " + numero);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do posto trabalho: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return postoTrabalhos;
    }

    public List<PostoTrabalho> findByMunicipio(String municipio) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<PostoTrabalho> postoTrabalhos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_MUNICIPIO);
            ps.setString(1, "%" + municipio + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postoTrabalhos.add(postoTrabalho);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum município com nome: " + municipio);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do posto trabalho: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return postoTrabalhos;
    }

    private void popularComDados(PostoTrabalho postoTrabalho, ResultSet rs) {
        try {
            postoTrabalho.setIdPostoTrabalho(rs.getInt("p.id_posto_trabalho"));
            postoTrabalho.setNomePostoTrabalho(rs.getString("p.nome_posto_trabalho"));
            postoTrabalho.setNumeroPostoTrabalho(rs.getInt("p.numero_posto_trabalho"));

            Municipio municipio = new Municipio();
            municipio.setIdMunicipio(rs.getInt("m.id_municipio"));
            municipio.setNomeMunicipio(rs.getString("m.nome_municipio"));
            postoTrabalho.setMunicipio(municipio);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados do posto de trabalho: " + ex.getLocalizedMessage());
        }
    }

}
