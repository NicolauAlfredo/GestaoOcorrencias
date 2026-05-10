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

    private static final String INSERIR = "INSERT INTO patente (nome_patente) VALUES(?)";
    private static final String ACTUALIZAR = "UPDATE patente SET nome_patente = ? WHERE id_patente = ?";
    private static final String ELIMINAR = "DELETE FROM patente WHERE id_patente = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM patente WHERE id_patente = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM patente";
    private static final String LISTAR_POR_NOME = "SELECT * FROM patente WHERE nome_patente LIKE ? ORDER BY nome_patente";

    @Override
    public void save(Patente patente) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (patente == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
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
        PreparedStatement ps = null;
        Connection conn = null;

        if (patente == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
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
        PreparedStatement ps = null;
        Connection conn = null;

        if (patente == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
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
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Patente patente = new Patente();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(patente, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da patente: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return patente;
    }

    @Override
    public List<Patente> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Patente> patentes = new ArrayList<>();
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
            System.err.println("Erro ao LER dados da patente: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return patentes;
    }

    public List<Patente> findByNome(String nome) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Patente> patentes = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Patente patente = new Patente();
                popularComDados(patente, rs);
                patentes.add(patente);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhuma patente com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do patente: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return patentes;
    }

    private void popularComDados(Patente patente, ResultSet rs) {
        try {
            patente.setIdPatente(rs.getInt("id_patente"));
            patente.setNomePatente(rs.getString("nome_patente"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados da patente: " + ex.getLocalizedMessage());
        }
    }

}
