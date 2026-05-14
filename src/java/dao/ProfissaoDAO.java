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
import modelo.Profissao;
import util.Conexao;

/**
 *
 * @author user
 */
public class ProfissaoDAO implements GenericoDAO<Profissao> {

    private static final int TOTAL_PROFISSOES_POR_PAGINA = 6;

    private static final String INSERIR
            = "INSERT INTO profissao (nome_profissao) VALUES (?)";

    private static final String ACTUALIZAR
            = "UPDATE profissao SET nome_profissao = ? WHERE id_profissao = ?";

    private static final String ELIMINAR
            = "DELETE FROM profissao WHERE id_profissao = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT * FROM profissao WHERE id_profissao = ?";

    private static final String LISTAR_TUDO
            = "SELECT * FROM profissao ORDER BY nome_profissao";

    private static final String LISTAR_POR_NOME
            = "SELECT * FROM profissao WHERE nome_profissao LIKE ? ORDER BY nome_profissao";

    private static final String CONTAR_PROFISSOES
            = "SELECT COUNT(1) AS total_profissoes FROM profissao";

    private static final String CONSULTAR_PAGINA
            = "SELECT * FROM profissao ORDER BY nome_profissao LIMIT ? OFFSET ?";

    private static final String CONTAR_PROFISSOES_POR_NOME
            = "SELECT COUNT(1) AS total_profissoes FROM profissao WHERE nome_profissao LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_NOME
            = "SELECT * FROM profissao WHERE nome_profissao LIKE ? ORDER BY nome_profissao LIMIT ? OFFSET ?";

    @Override
    public void save(Profissao profissao) {
        if (profissao == null) {
            System.err.println("Erro ao INSERIR profissão: o objeto profissão não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, profissao.getNomeProfissao());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da profissão: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Profissao profissao) {
        if (profissao == null) {
            System.err.println("Erro ao ACTUALIZAR profissão: o objeto profissão não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            ps.setString(1, profissao.getNomeProfissao());
            ps.setInt(2, profissao.getIdProfissao());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da profissão: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Profissao profissao) {
        if (profissao == null) {
            System.err.println("Erro ao ELIMINAR profissão: o objeto profissão não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, profissao.getIdProfissao());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados da profissão: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Profissao findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR profissão: o id não pode ser nulo.");
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
                Profissao profissao = new Profissao();
                popularComDados(profissao, rs);
                return profissao;
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da profissão: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Profissao> findAll() {
        return consultarListaSemParametros(LISTAR_TUDO);
    }

    public List<Profissao> findByNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Profissao> profissoes = new ArrayList<Profissao>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Profissao profissao = new Profissao();
                popularComDados(profissao, rs);
                profissoes.add(profissao);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da profissão por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return profissoes;
    }

    public int quantidadePaginas() {
        return contarPaginas(CONTAR_PROFISSOES, null);
    }

    public List<Profissao> consultarPagina(String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Profissao> profissoes = new ArrayList<Profissao>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_PROFISSOES_POR_PAGINA) - TOTAL_PROFISSOES_POR_PAGINA;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONSULTAR_PAGINA);

            ps.setInt(1, TOTAL_PROFISSOES_POR_PAGINA);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Profissao profissao = new Profissao();
                popularComDados(profissao, rs);
                profissoes.add(profissao);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página de profissões: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return profissoes;
    }

    public int quantidadePaginasPorNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return contarPaginas(CONTAR_PROFISSOES_POR_NOME, "%" + nome.trim() + "%");
    }

    public List<Profissao> consultarPaginaPorNome(String nome, String numeroPagina) {
        if (nome == null) {
            nome = "";
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Profissao> profissoes = new ArrayList<Profissao>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_PROFISSOES_POR_PAGINA) - TOTAL_PROFISSOES_POR_PAGINA;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONSULTAR_PAGINA_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");
            ps.setInt(2, TOTAL_PROFISSOES_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Profissao profissao = new Profissao();
                popularComDados(profissao, rs);
                profissoes.add(profissao);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar profissões por nome com paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return profissoes;
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
                int totalProfissoes = rs.getInt("total_profissoes");

                quantidadePaginas = (int) Math.ceil(
                        totalProfissoes / (double) TOTAL_PROFISSOES_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular quantidade de páginas das profissões: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    private List<Profissao> consultarListaSemParametros(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Profissao> profissoes = new ArrayList<Profissao>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Profissao profissao = new Profissao();
                popularComDados(profissao, rs);
                profissoes.add(profissao);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao LISTAR dados das profissões: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return profissoes;
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

    private void popularComDados(Profissao profissao, ResultSet rs) throws SQLException {
        profissao.setIdProfissao(rs.getInt("id_profissao"));
        profissao.setNomeProfissao(rs.getString("nome_profissao"));
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
