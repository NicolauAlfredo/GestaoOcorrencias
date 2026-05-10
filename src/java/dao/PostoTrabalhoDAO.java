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

    private static final String CAMPOS_CONSULTA
            = "p.id_posto_trabalho, "
            + "p.nome_posto_trabalho, "
            + "p.numero_posto_trabalho, "
            + "m.id_municipio, "
            + "m.nome_municipio";

    private static final String INSERIR
            = "INSERT INTO posto_trabalho "
            + "(nome_posto_trabalho, numero_posto_trabalho, id_municipio) "
            + "VALUES (?, ?, ?)";

    private static final String ACTUALIZAR
            = "UPDATE posto_trabalho SET "
            + "nome_posto_trabalho = ?, "
            + "numero_posto_trabalho = ?, "
            + "id_municipio = ? "
            + "WHERE id_posto_trabalho = ?";

    private static final String ELIMINAR
            = "DELETE FROM posto_trabalho WHERE id_posto_trabalho = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM posto_trabalho p "
            + "INNER JOIN municipio m ON p.id_municipio = m.id_municipio "
            + "WHERE p.id_posto_trabalho = ?";

    private static final String LISTAR_TUDO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM posto_trabalho p "
            + "INNER JOIN municipio m ON p.id_municipio = m.id_municipio "
            + "ORDER BY p.nome_posto_trabalho";

    private static final String LISTAR_POR_NOME
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM posto_trabalho p "
            + "INNER JOIN municipio m ON p.id_municipio = m.id_municipio "
            + "WHERE p.nome_posto_trabalho LIKE ? "
            + "ORDER BY p.nome_posto_trabalho";

    private static final String LISTAR_POR_NUMERO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM posto_trabalho p "
            + "INNER JOIN municipio m ON p.id_municipio = m.id_municipio "
            + "WHERE p.numero_posto_trabalho = ? "
            + "ORDER BY p.numero_posto_trabalho";

    private static final String LISTAR_POR_MUNICIPIO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM posto_trabalho p "
            + "INNER JOIN municipio m ON p.id_municipio = m.id_municipio "
            + "WHERE m.nome_municipio LIKE ? "
            + "ORDER BY m.nome_municipio, p.nome_posto_trabalho";

    @Override
    public void save(PostoTrabalho postoTrabalho) {
        if (postoTrabalho == null) {
            System.err.println("Erro ao INSERIR posto de trabalho: o objeto posto de trabalho não pode ser nulo.");
            return;
        }

        if (postoTrabalho.getMunicipio() == null) {
            System.err.println("Erro ao INSERIR posto de trabalho: o município não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

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
        if (postoTrabalho == null) {
            System.err.println("Erro ao ACTUALIZAR posto de trabalho: o objeto posto de trabalho não pode ser nulo.");
            return;
        }

        if (postoTrabalho.getMunicipio() == null) {
            System.err.println("Erro ao ACTUALIZAR posto de trabalho: o município não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

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
        if (postoTrabalho == null) {
            System.err.println("Erro ao ELIMINAR posto de trabalho: o objeto posto de trabalho não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

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
        if (id == null) {
            System.err.println("Erro ao BUSCAR posto de trabalho: o id não pode ser nulo.");
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
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                return postoTrabalho;
            }

            System.err.println("Não foi encontrado nenhum posto de trabalho com id: " + id);

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do posto de trabalho: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<PostoTrabalho> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PostoTrabalho> postosTrabalho = new ArrayList<PostoTrabalho>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);

            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postosTrabalho.add(postoTrabalho);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao LISTAR dados dos postos de trabalho: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return postosTrabalho;
    }

    public List<PostoTrabalho> findByNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PostoTrabalho> postosTrabalho = new ArrayList<PostoTrabalho>();

        if (nome == null) {
            nome = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postosTrabalho.add(postoTrabalho);
            }

            if (postosTrabalho.isEmpty()) {
                System.err.println("Não foi encontrado nenhum posto de trabalho com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do posto de trabalho por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return postosTrabalho;
    }

    public List<PostoTrabalho> findByNumero(Integer numero) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PostoTrabalho> postosTrabalho = new ArrayList<PostoTrabalho>();

        if (numero == null) {
            System.err.println("Erro ao BUSCAR posto de trabalho: o número não pode ser nulo.");
            return postosTrabalho;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NUMERO);

            ps.setInt(1, numero);

            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postosTrabalho.add(postoTrabalho);
            }

            if (postosTrabalho.isEmpty()) {
                System.err.println("Não foi encontrado nenhum posto de trabalho com número: " + numero);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do posto de trabalho por número: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return postosTrabalho;
    }

    public List<PostoTrabalho> findByMunicipio(String municipio) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PostoTrabalho> postosTrabalho = new ArrayList<PostoTrabalho>();

        if (municipio == null) {
            municipio = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_MUNICIPIO);

            ps.setString(1, "%" + municipio.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postosTrabalho.add(postoTrabalho);
            }

            if (postosTrabalho.isEmpty()) {
                System.err.println("Não foi encontrado nenhum posto de trabalho no município: " + municipio);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do posto de trabalho por município: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return postosTrabalho;
    }

    private void popularComDados(PostoTrabalho postoTrabalho, ResultSet rs) throws SQLException {
        postoTrabalho.setIdPostoTrabalho(rs.getInt("id_posto_trabalho"));
        postoTrabalho.setNomePostoTrabalho(rs.getString("nome_posto_trabalho"));
        postoTrabalho.setNumeroPostoTrabalho(rs.getInt("numero_posto_trabalho"));

        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(rs.getInt("id_municipio"));
        municipio.setNomeMunicipio(rs.getString("nome_municipio"));

        postoTrabalho.setMunicipio(municipio);
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
