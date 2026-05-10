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

    private static final String INSERIR
            = "INSERT INTO provincia (nome_provincia) VALUES (?)";

    private static final String ACTUALIZAR
            = "UPDATE provincia SET nome_provincia = ? WHERE id_provincia = ?";

    private static final String ELIMINAR
            = "DELETE FROM provincia WHERE id_provincia = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT * FROM provincia WHERE id_provincia = ?";

    private static final String LISTAR_TUDO
            = "SELECT * FROM provincia ORDER BY nome_provincia";

    private static final String LISTAR_POR_NOME
            = "SELECT * FROM provincia WHERE nome_provincia LIKE ? ORDER BY nome_provincia";

    @Override
    public void save(Provincia provincia) {
        if (provincia == null) {
            System.err.println("Erro ao INSERIR província: o objeto província não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, provincia.getNomeProvincia());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da província: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Provincia provincia) {
        if (provincia == null) {
            System.err.println("Erro ao ACTUALIZAR província: o objeto província não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            ps.setString(1, provincia.getNomeProvincia());
            ps.setInt(2, provincia.getIdProvincia());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da província: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Provincia provincia) {
        if (provincia == null) {
            System.err.println("Erro ao ELIMINAR província: o objeto província não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, provincia.getIdProvincia());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados da província: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Provincia findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR província: o id não pode ser nulo.");
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
                Provincia provincia = new Provincia();
                popularComDados(provincia, rs);
                return provincia;
            }

            System.err.println("Não foi encontrada nenhuma província com id: " + id);

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da província: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Provincia> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Provincia> provincias = new ArrayList<Provincia>();

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
            System.err.println("Erro ao LISTAR dados das províncias: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return provincias;
    }

    public List<Provincia> findByNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Provincia> provincias = new ArrayList<Provincia>();

        if (nome == null) {
            nome = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Provincia provincia = new Provincia();
                popularComDados(provincia, rs);
                provincias.add(provincia);
            }

            if (provincias.isEmpty()) {
                System.err.println("Não foi encontrada nenhuma província com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da província por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return provincias;
    }

    private void popularComDados(Provincia provincia, ResultSet rs) throws SQLException {
        provincia.setIdProvincia(rs.getInt("id_provincia"));
        provincia.setNomeProvincia(rs.getString("nome_provincia"));
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
