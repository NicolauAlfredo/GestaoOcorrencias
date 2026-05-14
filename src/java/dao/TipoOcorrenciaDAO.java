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
import modelo.TipoOcorrencia;
import util.Conexao;

/**
 *
 * @author user
 */
public class TipoOcorrenciaDAO implements GenericoDAO<TipoOcorrencia> {

    private static final int TOTAL_TIPOS_OCORRENCIA_POR_PAGINA = 6;

    private static final String INSERIR
            = "INSERT INTO tipo_ocorrencia (nome_tipo_ocorrencia) VALUES (?)";

    private static final String ACTUALIZAR
            = "UPDATE tipo_ocorrencia SET nome_tipo_ocorrencia = ? WHERE id_tipo_ocorrencia = ?";

    private static final String ELIMINAR
            = "DELETE FROM tipo_ocorrencia WHERE id_tipo_ocorrencia = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT * FROM tipo_ocorrencia WHERE id_tipo_ocorrencia = ?";

    private static final String LISTAR_TUDO
            = "SELECT * FROM tipo_ocorrencia ORDER BY nome_tipo_ocorrencia";

    private static final String LISTAR_POR_NOME
            = "SELECT * FROM tipo_ocorrencia "
            + "WHERE nome_tipo_ocorrencia LIKE ? "
            + "ORDER BY nome_tipo_ocorrencia";

    private static final String CONTAR_TIPOS_OCORRENCIA
            = "SELECT COUNT(1) AS total_tipos_ocorrencia "
            + "FROM tipo_ocorrencia";

    private static final String CONSULTAR_PAGINA
            = "SELECT * FROM tipo_ocorrencia "
            + "ORDER BY nome_tipo_ocorrencia "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_TIPOS_OCORRENCIA_POR_NOME
            = "SELECT COUNT(1) AS total_tipos_ocorrencia "
            + "FROM tipo_ocorrencia "
            + "WHERE nome_tipo_ocorrencia LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_NOME
            = "SELECT * FROM tipo_ocorrencia "
            + "WHERE nome_tipo_ocorrencia LIKE ? "
            + "ORDER BY nome_tipo_ocorrencia "
            + "LIMIT ? OFFSET ?";

    @Override
    public void save(TipoOcorrencia tipoOcorrencia) {
        if (tipoOcorrencia == null) {
            System.err.println("Erro ao INSERIR tipo de ocorrência: o objeto tipo de ocorrência não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, tipoOcorrencia.getNomeTipoOcorrencia());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do tipo de ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(TipoOcorrencia tipoOcorrencia) {
        if (tipoOcorrencia == null) {
            System.err.println("Erro ao ACTUALIZAR tipo de ocorrência: o objeto tipo de ocorrência não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            ps.setString(1, tipoOcorrencia.getNomeTipoOcorrencia());
            ps.setInt(2, tipoOcorrencia.getIdTipoOcorrencia());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados do tipo de ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(TipoOcorrencia tipoOcorrencia) {
        if (tipoOcorrencia == null) {
            System.err.println("Erro ao ELIMINAR tipo de ocorrência: o objeto tipo de ocorrência não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, tipoOcorrencia.getIdTipoOcorrencia());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados do tipo de ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public TipoOcorrencia findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR tipo de ocorrência: o id não pode ser nulo.");
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
                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                popularComDados(tipoOcorrencia, rs);
                return tipoOcorrencia;
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do tipo de ocorrência: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<TipoOcorrencia> findAll() {
        return consultarListaSemFiltro(LISTAR_TUDO);
    }

    public List<TipoOcorrencia> findByNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return consultarListaComFiltro(
                LISTAR_POR_NOME,
                nome
        );
    }

    public int quantidadePaginas() {
        return contarPaginas(
                CONTAR_TIPOS_OCORRENCIA,
                null
        );
    }

    public List<TipoOcorrencia> consultarPagina(String numeroPagina) {
        return consultarPaginaSemFiltro(
                CONSULTAR_PAGINA,
                numeroPagina
        );
    }

    public int quantidadePaginasPorNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return contarPaginas(
                CONTAR_TIPOS_OCORRENCIA_POR_NOME,
                nome
        );
    }

    public List<TipoOcorrencia> consultarPaginaPorNome(String nome, String numeroPagina) {
        if (nome == null) {
            nome = "";
        }

        return consultarPaginaComFiltro(
                CONSULTAR_PAGINA_POR_NOME,
                nome,
                numeroPagina
        );
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
                ps.setString(1, "%" + filtro.trim() + "%");
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalTipos = rs.getInt("total_tipos_ocorrencia");

                quantidadePaginas = (int) Math.ceil(
                        totalTipos / (double) TOTAL_TIPOS_OCORRENCIA_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular quantidade de páginas dos tipos de ocorrência: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    private List<TipoOcorrencia> consultarPaginaSemFiltro(
            String sql,
            String numeroPagina
    ) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoOcorrencia> tipoOcorrencias = new ArrayList<TipoOcorrencia>();

        int pagina = converterNumeroPagina(numeroPagina);

        int offset = (pagina * TOTAL_TIPOS_OCORRENCIA_POR_PAGINA)
                - TOTAL_TIPOS_OCORRENCIA_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, TOTAL_TIPOS_OCORRENCIA_POR_PAGINA);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                popularComDados(tipoOcorrencia, rs);
                tipoOcorrencias.add(tipoOcorrencia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página dos tipos de ocorrência: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return tipoOcorrencias;
    }

    private List<TipoOcorrencia> consultarPaginaComFiltro(
            String sql,
            String filtro,
            String numeroPagina
    ) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoOcorrencia> tipoOcorrencias = new ArrayList<TipoOcorrencia>();

        int pagina = converterNumeroPagina(numeroPagina);

        int offset = (pagina * TOTAL_TIPOS_OCORRENCIA_POR_PAGINA)
                - TOTAL_TIPOS_OCORRENCIA_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + filtro.trim() + "%");
            ps.setInt(2, TOTAL_TIPOS_OCORRENCIA_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                popularComDados(tipoOcorrencia, rs);
                tipoOcorrencias.add(tipoOcorrencia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar tipos de ocorrência com filtro e paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return tipoOcorrencias;
    }

    private List<TipoOcorrencia> consultarListaSemFiltro(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoOcorrencia> tipoOcorrencias = new ArrayList<TipoOcorrencia>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                popularComDados(tipoOcorrencia, rs);
                tipoOcorrencias.add(tipoOcorrencia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao LISTAR dados dos tipos de ocorrência: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return tipoOcorrencias;
    }

    private List<TipoOcorrencia> consultarListaComFiltro(String sql, String filtro) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoOcorrencia> tipoOcorrencias = new ArrayList<TipoOcorrencia>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + filtro.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                popularComDados(tipoOcorrencia, rs);
                tipoOcorrencias.add(tipoOcorrencia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do tipo de ocorrência por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return tipoOcorrencias;
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

    private void popularComDados(TipoOcorrencia tipoOcorrencia, ResultSet rs) throws SQLException {
        tipoOcorrencia.setIdTipoOcorrencia(rs.getInt("id_tipo_ocorrencia"));
        tipoOcorrencia.setNomeTipoOcorrencia(rs.getString("nome_tipo_ocorrencia"));
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
