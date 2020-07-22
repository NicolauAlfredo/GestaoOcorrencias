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
import modelo.Provincia;
import util.Conexao;

/**
 *
 * @author user
 */
public class ProvinciaDAO implements GenericoDAO<Provincia> {

    private static final String INSERIR = "INSERT INTO provincia (nome_provincia) VALUES(?)";
    private static final String ACTUALIZAR = "UPDATE provincia SET nome_provincia = ? WHERE id_provincia = ?";
    private static final String ELIMINAR = "DETELE FROM provincia WHERE id_provincia = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM provincia WHERE id_provincia = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM provincia";
    private static final String LISTAR_POR_NOME = "SELECT * FROM provincia WHERE nome_provincia LIKE ? ORDER BY nome_provincia";

    @Override
    public void save(Provincia provincia) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (provincia == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, provincia.getNomeProvincia());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da provincia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Provincia provincia) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (provincia == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, provincia.getNomeProvincia());
            ps.setInt(2, provincia.getIdProvincia());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da provincia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Provincia provincia) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (provincia == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, provincia.getIdProvincia());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados da provincia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Provincia findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Provincia provincia = new Provincia();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(provincia, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da provincia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return provincia;
    }

    @Override
    public List<Provincia> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Provincia> provincias = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Provincia provincia = new Provincia();
                popularComDados(provincia, rs);
                provincias.add(provincia);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao LER dados da provincia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return provincias;
    }

    public List<Provincia> findByNome(String nome) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Provincia> provincias = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Provincia provincia = new Provincia();
                popularComDados(provincia, rs);
                provincias.add(provincia);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhuma provincia com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return provincias;
    }

    private void popularComDados(Provincia provincia, ResultSet rs) {
        try {
            provincia.setIdProvincia(rs.getInt("id_provincia"));
            provincia.setNomeProvincia(rs.getString("nome_provincia"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados da província: " + ex.getLocalizedMessage());
        }
    }
}
