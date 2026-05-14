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

    private static final int TOTAL_POSTOS_POR_PAGINA = 6;

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

    private static final String BASE_SELECT
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM posto_trabalho p "
            + "INNER JOIN municipio m ON p.id_municipio = m.id_municipio ";

    private static final String BUSCAR_POR_CODIGO
            = BASE_SELECT
            + "WHERE p.id_posto_trabalho = ?";

    private static final String LISTAR_TUDO
            = BASE_SELECT
            + "ORDER BY p.nome_posto_trabalho";

    private static final String LISTAR_POR_NOME
            = BASE_SELECT
            + "WHERE p.nome_posto_trabalho LIKE ? "
            + "ORDER BY p.nome_posto_trabalho";

    private static final String LISTAR_POR_NUMERO
            = BASE_SELECT
            + "WHERE p.numero_posto_trabalho = ? "
            + "ORDER BY p.numero_posto_trabalho";

    private static final String LISTAR_POR_MUNICIPIO
            = BASE_SELECT
            + "WHERE m.nome_municipio LIKE ? "
            + "ORDER BY m.nome_municipio, p.nome_posto_trabalho";

    private static final String CONTAR_POSTOS
            = "SELECT COUNT(1) AS total_postos FROM posto_trabalho";

    private static final String CONSULTAR_PAGINA
            = BASE_SELECT
            + "ORDER BY p.nome_posto_trabalho "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_POSTOS_POR_NOME
            = "SELECT COUNT(1) AS total_postos "
            + "FROM posto_trabalho "
            + "WHERE nome_posto_trabalho LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_NOME
            = BASE_SELECT
            + "WHERE p.nome_posto_trabalho LIKE ? "
            + "ORDER BY p.nome_posto_trabalho "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_POSTOS_POR_NUMERO
            = "SELECT COUNT(1) AS total_postos "
            + "FROM posto_trabalho "
            + "WHERE numero_posto_trabalho = ?";

    private static final String CONSULTAR_PAGINA_POR_NUMERO
            = BASE_SELECT
            + "WHERE p.numero_posto_trabalho = ? "
            + "ORDER BY p.numero_posto_trabalho "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_POSTOS_POR_MUNICIPIO
            = "SELECT COUNT(1) AS total_postos "
            + "FROM posto_trabalho p "
            + "INNER JOIN municipio m ON p.id_municipio = m.id_municipio "
            + "WHERE m.nome_municipio LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_MUNICIPIO
            = BASE_SELECT
            + "WHERE m.nome_municipio LIKE ? "
            + "ORDER BY m.nome_municipio, p.nome_posto_trabalho "
            + "LIMIT ? OFFSET ?";

    @Override
    public void save(PostoTrabalho postoTrabalho) {
        if (postoTrabalho == null || postoTrabalho.getMunicipio() == null) {
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
        if (postoTrabalho == null || postoTrabalho.getMunicipio() == null) {
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
        return consultarListaSimples(LISTAR_TUDO, null, null);
    }

    public List<PostoTrabalho> findByNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return consultarListaSimples(LISTAR_POR_NOME, "%" + nome.trim() + "%", null);
    }

    public List<PostoTrabalho> findByNumero(Integer numero) {
        if (numero == null) {
            return new ArrayList<PostoTrabalho>();
        }

        return consultarListaSimples(LISTAR_POR_NUMERO, null, numero);
    }

    public List<PostoTrabalho> findByMunicipio(String municipio) {
        if (municipio == null) {
            municipio = "";
        }

        return consultarListaSimples(LISTAR_POR_MUNICIPIO, "%" + municipio.trim() + "%", null);
    }

    public int quantidadePaginas() {
        return contarPaginas(CONTAR_POSTOS, null, null);
    }

    public List<PostoTrabalho> consultarPagina(String numeroPagina) {
        return consultarPaginaSemFiltro(CONSULTAR_PAGINA, numeroPagina);
    }

    public int quantidadePaginasPorNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return contarPaginas(CONTAR_POSTOS_POR_NOME, "%" + nome.trim() + "%", null);
    }

    public List<PostoTrabalho> consultarPaginaPorNome(String nome, String numeroPagina) {
        if (nome == null) {
            nome = "";
        }

        return consultarPaginaComTexto(CONSULTAR_PAGINA_POR_NOME, nome, numeroPagina);
    }

    public int quantidadePaginasPorMunicipio(String municipio) {
        if (municipio == null) {
            municipio = "";
        }

        return contarPaginas(CONTAR_POSTOS_POR_MUNICIPIO, "%" + municipio.trim() + "%", null);
    }

    public List<PostoTrabalho> consultarPaginaPorMunicipio(String municipio, String numeroPagina) {
        if (municipio == null) {
            municipio = "";
        }

        return consultarPaginaComTexto(CONSULTAR_PAGINA_POR_MUNICIPIO, municipio, numeroPagina);
    }

    public int quantidadePaginasPorNumero(Integer numero) {
        if (numero == null) {
            return 1;
        }

        return contarPaginas(CONTAR_POSTOS_POR_NUMERO, null, numero);
    }

    public List<PostoTrabalho> consultarPaginaPorNumero(Integer numero, String numeroPagina) {
        if (numero == null) {
            return new ArrayList<PostoTrabalho>();
        }

        return consultarPaginaComNumero(CONSULTAR_PAGINA_POR_NUMERO, numero, numeroPagina);
    }

    private List<PostoTrabalho> consultarListaSimples(String sql, String texto, Integer numero) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PostoTrabalho> postosTrabalho = new ArrayList<PostoTrabalho>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            if (texto != null) {
                ps.setString(1, texto);
            }

            if (numero != null) {
                ps.setInt(1, numero);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postosTrabalho.add(postoTrabalho);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar postos de trabalho: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return postosTrabalho;
    }

    private int contarPaginas(String sql, String texto, Integer numero) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int quantidadePaginas = 1;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            if (texto != null) {
                ps.setString(1, texto);
            }

            if (numero != null) {
                ps.setInt(1, numero);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalPostos = rs.getInt("total_postos");

                quantidadePaginas = (int) Math.ceil(
                        totalPostos / (double) TOTAL_POSTOS_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular páginas dos postos de trabalho: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    private List<PostoTrabalho> consultarPaginaSemFiltro(String sql, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PostoTrabalho> postosTrabalho = new ArrayList<PostoTrabalho>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_POSTOS_POR_PAGINA) - TOTAL_POSTOS_POR_PAGINA;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, TOTAL_POSTOS_POR_PAGINA);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postosTrabalho.add(postoTrabalho);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página dos postos de trabalho: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return postosTrabalho;
    }

    private List<PostoTrabalho> consultarPaginaComTexto(String sql, String texto, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PostoTrabalho> postosTrabalho = new ArrayList<PostoTrabalho>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_POSTOS_POR_PAGINA) - TOTAL_POSTOS_POR_PAGINA;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + texto.trim() + "%");
            ps.setInt(2, TOTAL_POSTOS_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postosTrabalho.add(postoTrabalho);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página filtrada dos postos de trabalho: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return postosTrabalho;
    }

    private List<PostoTrabalho> consultarPaginaComNumero(String sql, Integer numero, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<PostoTrabalho> postosTrabalho = new ArrayList<PostoTrabalho>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_POSTOS_POR_PAGINA) - TOTAL_POSTOS_POR_PAGINA;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, numero);
            ps.setInt(2, TOTAL_POSTOS_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                PostoTrabalho postoTrabalho = new PostoTrabalho();
                popularComDados(postoTrabalho, rs);
                postosTrabalho.add(postoTrabalho);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página por número dos postos de trabalho: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return postosTrabalho;
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
