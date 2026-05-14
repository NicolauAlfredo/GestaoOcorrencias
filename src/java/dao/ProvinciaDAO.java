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

    private static final int TOTAL_PROVINCIAS_POR_PAGINA = 6;

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

    private static final String CONTAR_PROVINCIAS
            = "SELECT COUNT(1) AS total_provincias FROM provincia";

    private static final String CONSULTAR_PAGINA
            = "SELECT * FROM provincia ORDER BY nome_provincia LIMIT ? OFFSET ?";

    private static final String CONTAR_PROVINCIAS_POR_NOME
            = "SELECT COUNT(1) AS total_provincias FROM provincia WHERE nome_provincia LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_NOME
            = "SELECT * FROM provincia WHERE nome_provincia LIKE ? ORDER BY nome_provincia LIMIT ? OFFSET ?";

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
        if (nome == null) {
            nome = "";
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Provincia> provincias = new ArrayList<Provincia>();

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

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da província por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return provincias;
    }

    public int quantidadePaginas() {
        return contarPaginas(CONTAR_PROVINCIAS, null);
    }

    public List<Provincia> consultarPagina(String numeroPagina) {
        return consultarPaginaSemFiltro(CONSULTAR_PAGINA, numeroPagina);
    }

    public int quantidadePaginasPorNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return contarPaginas(CONTAR_PROVINCIAS_POR_NOME, "%" + nome.trim() + "%");
    }

    public List<Provincia> consultarPaginaPorNome(String nome, String numeroPagina) {
        if (nome == null) {
            nome = "";
        }

        return consultarPaginaComFiltro(CONSULTAR_PAGINA_POR_NOME, nome, numeroPagina);
    }

    private int contarPaginas(String sql, String filtro) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int quantidadePaginas = 1;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            if (filtro != null) {
                ps.setString(1, filtro);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalProvincias = rs.getInt("total_provincias");

                quantidadePaginas = (int) Math.ceil(
                        totalProvincias / (double) TOTAL_PROVINCIAS_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular quantidade de páginas das províncias: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    private List<Provincia> consultarPaginaSemFiltro(String sql, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Provincia> provincias = new ArrayList<Provincia>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_PROVINCIAS_POR_PAGINA) - TOTAL_PROVINCIAS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, TOTAL_PROVINCIAS_POR_PAGINA);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Provincia provincia = new Provincia();
                popularComDados(provincia, rs);
                provincias.add(provincia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página de províncias: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return provincias;
    }

    private List<Provincia> consultarPaginaComFiltro(String sql, String filtro, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Provincia> provincias = new ArrayList<Provincia>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_PROVINCIAS_POR_PAGINA) - TOTAL_PROVINCIAS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + filtro.trim() + "%");
            ps.setInt(2, TOTAL_PROVINCIAS_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Provincia provincia = new Provincia();
                popularComDados(provincia, rs);
                provincias.add(provincia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar províncias com filtro e paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return provincias;
    }

    private int converterNumeroPagina(String numeroPagina) {
        if (numeroPagina == null || numeroPagina.trim().isEmpty()) {
            return 1;
        }

        try {
            int pagina = Integer.parseInt(numeroPagina.trim());

            if (pagina < 1) {
                return 1;
            }

            return pagina;

        } catch (NumberFormatException ex) {
            return 1;
        }
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
