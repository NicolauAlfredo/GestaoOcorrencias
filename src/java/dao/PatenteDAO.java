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
import modelo.Patente;
import util.Conexao;

/**
 *
 * @author user
 */
public class PatenteDAO implements GenericoDAO<Patente> {

    private static final String INSERIR
            = "INSERT INTO patente (nome_patente) VALUES (?)";

    private static final String ACTUALIZAR
            = "UPDATE patente SET nome_patente = ? WHERE id_patente = ?";

    private static final String ELIMINAR
            = "DELETE FROM patente WHERE id_patente = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT * FROM patente WHERE id_patente = ?";

    private static final String LISTAR_TUDO
            = "SELECT * FROM patente ORDER BY nome_patente";

    private static final String LISTAR_POR_NOME
            = "SELECT * FROM patente WHERE nome_patente LIKE ? ORDER BY nome_patente";

    @Override
    public void save(Patente patente) {
        if (patente == null) {
            System.err.println("Erro ao INSERIR patente: o objeto patente não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, patente.getNomePatente());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da patente: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Patente patente) {
        if (patente == null) {
            System.err.println("Erro ao ACTUALIZAR patente: o objeto patente não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            ps.setString(1, patente.getNomePatente());
            ps.setInt(2, patente.getIdPatente());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da patente: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Patente patente) {
        if (patente == null) {
            System.err.println("Erro ao ELIMINAR patente: o objeto patente não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, patente.getIdPatente());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados da patente: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Patente findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR patente: o id não pode ser nulo.");
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
                Patente patente = new Patente();
                popularComDados(patente, rs);
                return patente;
            }

            System.err.println("Não foi encontrada nenhuma patente com id: " + id);

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da patente: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Patente> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Patente> patentes = new ArrayList<Patente>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);

            rs = ps.executeQuery();

            while (rs.next()) {
                Patente patente = new Patente();
                popularComDados(patente, rs);
                patentes.add(patente);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao LISTAR dados das patentes: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return patentes;
    }

    public List<Patente> findByNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Patente> patentes = new ArrayList<Patente>();

        if (nome == null) {
            nome = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Patente patente = new Patente();
                popularComDados(patente, rs);
                patentes.add(patente);
            }

            if (patentes.isEmpty()) {
                System.err.println("Não foi encontrada nenhuma patente com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da patente por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return patentes;
    }

    private void popularComDados(Patente patente, ResultSet rs) throws SQLException {
        patente.setIdPatente(rs.getInt("id_patente"));
        patente.setNomePatente(rs.getString("nome_patente"));
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
