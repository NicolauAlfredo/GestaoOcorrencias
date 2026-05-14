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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import modelo.Sexo;
import modelo.Testemunha;
import util.Conexao;

/**
 *
 * @author user
 */
public class TestemunhaDAO implements GenericoDAO<Testemunha> {

    private static final int TOTAL_TESTEMUNHAS_POR_PAGINA = 6;

    private static final String INSERIR
            = "INSERT INTO testemunha "
            + "(nome_testemunha, residencia_testemunha, data_nascimento_testemunha, "
            + "sexo_testemunha, bi_testemunha, telefone_testemunha) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String ACTUALIZAR
            = "UPDATE testemunha SET "
            + "nome_testemunha = ?, "
            + "residencia_testemunha = ?, "
            + "data_nascimento_testemunha = ?, "
            + "sexo_testemunha = ?, "
            + "bi_testemunha = ?, "
            + "telefone_testemunha = ? "
            + "WHERE id_testemunha = ?";

    private static final String ELIMINAR
            = "DELETE FROM testemunha WHERE id_testemunha = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT * FROM testemunha WHERE id_testemunha = ?";

    private static final String LISTAR_TUDO
            = "SELECT * FROM testemunha ORDER BY nome_testemunha";

    private static final String LISTAR_POR_NOME
            = "SELECT * FROM testemunha WHERE nome_testemunha LIKE ? ORDER BY nome_testemunha";

    private static final String LISTAR_POR_BI
            = "SELECT * FROM testemunha WHERE bi_testemunha LIKE ? ORDER BY nome_testemunha";

    private static final String LISTAR_POR_DATA
            = "SELECT * FROM testemunha WHERE data_nascimento_testemunha = ? ORDER BY nome_testemunha";

    private static final String CONTAR_TESTEMUNHAS
            = "SELECT COUNT(1) AS total_testemunhas FROM testemunha";

    private static final String CONSULTAR_PAGINA
            = "SELECT * FROM testemunha ORDER BY nome_testemunha LIMIT ? OFFSET ?";

    private static final String CONTAR_TESTEMUNHAS_POR_NOME
            = "SELECT COUNT(1) AS total_testemunhas FROM testemunha WHERE nome_testemunha LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_NOME
            = "SELECT * FROM testemunha WHERE nome_testemunha LIKE ? ORDER BY nome_testemunha LIMIT ? OFFSET ?";

    private static final String CONTAR_TESTEMUNHAS_POR_BI
            = "SELECT COUNT(1) AS total_testemunhas FROM testemunha WHERE bi_testemunha LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_BI
            = "SELECT * FROM testemunha WHERE bi_testemunha LIKE ? ORDER BY nome_testemunha LIMIT ? OFFSET ?";

    private static final String CONTAR_TESTEMUNHAS_POR_DATA
            = "SELECT COUNT(1) AS total_testemunhas FROM testemunha WHERE data_nascimento_testemunha = ?";

    private static final String CONSULTAR_PAGINA_POR_DATA
            = "SELECT * FROM testemunha WHERE data_nascimento_testemunha = ? ORDER BY nome_testemunha LIMIT ? OFFSET ?";

    @Override
    public void save(Testemunha testemunha) {
        if (testemunha == null) {
            System.err.println("Erro ao INSERIR testemunha: o objeto testemunha não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            preencherPreparedStatement(ps, testemunha, false);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Testemunha testemunha) {
        if (testemunha == null) {
            System.err.println("Erro ao ACTUALIZAR testemunha: o objeto testemunha não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            preencherPreparedStatement(ps, testemunha, true);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Testemunha testemunha) {
        if (testemunha == null) {
            System.err.println("Erro ao ELIMINAR testemunha: o objeto testemunha não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, testemunha.getIdTestemunha());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Testemunha findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR testemunha: o id não pode ser nulo.");
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
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                return testemunha;
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da testemunha: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Testemunha> findAll() {
        return consultarListaSemFiltro(LISTAR_TUDO);
    }

    public List<Testemunha> findByNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return consultarListaComTexto(LISTAR_POR_NOME, nome);
    }

    public List<Testemunha> findByBi(String bi) {
        if (bi == null) {
            bi = "";
        }

        return consultarListaComTexto(LISTAR_POR_BI, bi);
    }

    public List<Testemunha> findByData(java.sql.Date data) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        if (data == null) {
            return testemunhas;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_DATA);

            ps.setDate(1, data);

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR testemunha por data: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
    }

    public int quantidadePaginas() {
        return contarPaginas(CONTAR_TESTEMUNHAS, null, null);
    }

    public List<Testemunha> consultarPagina(String numeroPagina) {
        return consultarPaginaSemFiltro(CONSULTAR_PAGINA, numeroPagina);
    }

    public int quantidadePaginasPorNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return contarPaginas(CONTAR_TESTEMUNHAS_POR_NOME, "%" + nome.trim() + "%", null);
    }

    public List<Testemunha> consultarPaginaPorNome(String nome, String numeroPagina) {
        if (nome == null) {
            nome = "";
        }

        return consultarPaginaComTexto(CONSULTAR_PAGINA_POR_NOME, nome, numeroPagina);
    }

    public int quantidadePaginasPorBi(String bi) {
        if (bi == null) {
            bi = "";
        }

        return contarPaginas(CONTAR_TESTEMUNHAS_POR_BI, "%" + bi.trim() + "%", null);
    }

    public List<Testemunha> consultarPaginaPorBi(String bi, String numeroPagina) {
        if (bi == null) {
            bi = "";
        }

        return consultarPaginaComTexto(CONSULTAR_PAGINA_POR_BI, bi, numeroPagina);
    }

    public int quantidadePaginasPorData(java.sql.Date data) {
        if (data == null) {
            return 1;
        }

        return contarPaginas(CONTAR_TESTEMUNHAS_POR_DATA, null, data);
    }

    public List<Testemunha> consultarPaginaPorData(java.sql.Date data, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        if (data == null) {
            return testemunhas;
        }

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_TESTEMUNHAS_POR_PAGINA) - TOTAL_TESTEMUNHAS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONSULTAR_PAGINA_POR_DATA);

            ps.setDate(1, data);
            ps.setInt(2, TOTAL_TESTEMUNHAS_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar testemunhas por data com paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
    }

    private int contarPaginas(String sql, String filtroTexto, java.sql.Date filtroData) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int quantidadePaginas = 1;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            if (filtroTexto != null) {
                ps.setString(1, filtroTexto);
            }

            if (filtroData != null) {
                ps.setDate(1, filtroData);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalTestemunhas = rs.getInt("total_testemunhas");

                quantidadePaginas = (int) Math.ceil(
                        totalTestemunhas / (double) TOTAL_TESTEMUNHAS_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular quantidade de páginas das testemunhas: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    private List<Testemunha> consultarPaginaSemFiltro(String sql, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_TESTEMUNHAS_POR_PAGINA) - TOTAL_TESTEMUNHAS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, TOTAL_TESTEMUNHAS_POR_PAGINA);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página de testemunhas: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
    }

    private List<Testemunha> consultarPaginaComTexto(String sql, String filtro, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_TESTEMUNHAS_POR_PAGINA) - TOTAL_TESTEMUNHAS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + filtro.trim() + "%");
            ps.setInt(2, TOTAL_TESTEMUNHAS_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar testemunhas com filtro e paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
    }

    private List<Testemunha> consultarListaSemFiltro(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar testemunhas: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
    }

    private List<Testemunha> consultarListaComTexto(String sql, String filtro) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Testemunha> testemunhas = new ArrayList<Testemunha>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + filtro.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Testemunha testemunha = new Testemunha();
                popularComDados(testemunha, rs);
                testemunhas.add(testemunha);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao pesquisar testemunhas: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return testemunhas;
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

    private void preencherPreparedStatement(
            PreparedStatement ps,
            Testemunha testemunha,
            boolean actualizar
    ) throws SQLException {

        ps.setString(1, testemunha.getNomeTestemunha());
        ps.setString(2, testemunha.getResidenciaTestemunha());

        if (testemunha.getDataNascimentoTestemunha() != null) {
            ps.setDate(3, new java.sql.Date(testemunha.getDataNascimentoTestemunha().getTime()));
        } else {
            ps.setNull(3, Types.DATE);
        }

        if (testemunha.getSexo() != null) {
            ps.setString(4, testemunha.getSexo().getExtensao());
        } else {
            ps.setNull(4, Types.VARCHAR);
        }

        ps.setString(5, testemunha.getBiTestemunha());
        ps.setString(6, testemunha.getTelefoneTestemunha());

        if (actualizar) {
            ps.setInt(7, testemunha.getIdTestemunha());
        }
    }

    private void popularComDados(Testemunha testemunha, ResultSet rs) throws SQLException {
        testemunha.setIdTestemunha(rs.getInt("id_testemunha"));
        testemunha.setNomeTestemunha(rs.getString("nome_testemunha"));
        testemunha.setResidenciaTestemunha(rs.getString("residencia_testemunha"));
        testemunha.setDataNascimentoTestemunha(rs.getDate("data_nascimento_testemunha"));
        testemunha.setSexo(Sexo.getExtensao(rs.getString("sexo_testemunha")));
        testemunha.setBiTestemunha(rs.getString("bi_testemunha"));
        testemunha.setTelefoneTestemunha(rs.getString("telefone_testemunha"));
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
