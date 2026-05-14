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

    private static final int TOTAL_PATENTES_POR_PAGINA = 6;

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

    private static final String CONTAR_PATENTES
            = "SELECT COUNT(1) AS total_patentes FROM patente";

    private static final String CONSULTAR_PAGINA
            = "SELECT * FROM patente ORDER BY nome_patente LIMIT ? OFFSET ?";

    private static final String CONTAR_PATENTES_POR_NOME
            = "SELECT COUNT(1) AS total_patentes FROM patente WHERE nome_patente LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_NOME
            = "SELECT * FROM patente WHERE nome_patente LIKE ? ORDER BY nome_patente LIMIT ? OFFSET ?";

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
        return consultarListaSemFiltro(LISTAR_TUDO);
    }

    public List<Patente> findByNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return consultarListaComNome(LISTAR_POR_NOME, nome);
    }

    public int quantidadePaginas() {
        return contarPaginas(CONTAR_PATENTES, null);
    }

    public List<Patente> consultarPagina(String numeroPagina) {
        return consultarPaginaSemFiltro(CONSULTAR_PAGINA, numeroPagina);
    }

    public int quantidadePaginasPorNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return contarPaginas(CONTAR_PATENTES_POR_NOME, nome);
    }

    public List<Patente> consultarPaginaPorNome(String nome, String numeroPagina) {
        if (nome == null) {
            nome = "";
        }

        return consultarPaginaComNome(CONSULTAR_PAGINA_POR_NOME, nome, numeroPagina);
    }

    private int contarPaginas(String sql, String filtroNome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int quantidadePaginas = 1;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            if (filtroNome != null) {
                ps.setString(1, "%" + filtroNome.trim() + "%");
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalPatentes = rs.getInt("total_patentes");

                quantidadePaginas = (int) Math.ceil(
                        totalPatentes / (double) TOTAL_PATENTES_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular quantidade de páginas das patentes: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    private List<Patente> consultarPaginaSemFiltro(String sql, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Patente> patentes = new ArrayList<Patente>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_PATENTES_POR_PAGINA) - TOTAL_PATENTES_POR_PAGINA;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, TOTAL_PATENTES_POR_PAGINA);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Patente patente = new Patente();
                popularComDados(patente, rs);
                patentes.add(patente);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página de patentes: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return patentes;
    }

    private List<Patente> consultarPaginaComNome(String sql, String nome, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Patente> patentes = new ArrayList<Patente>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_PATENTES_POR_PAGINA) - TOTAL_PATENTES_POR_PAGINA;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + nome.trim() + "%");
            ps.setInt(2, TOTAL_PATENTES_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Patente patente = new Patente();
                popularComDados(patente, rs);
                patentes.add(patente);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar patentes por nome com paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return patentes;
    }

    private List<Patente> consultarListaSemFiltro(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Patente> patentes = new ArrayList<Patente>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
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

    private List<Patente> consultarListaComNome(String sql, String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Patente> patentes = new ArrayList<Patente>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + nome.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Patente patente = new Patente();
                popularComDados(patente, rs);
                patentes.add(patente);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da patente por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return patentes;
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
