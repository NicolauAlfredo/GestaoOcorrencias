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
import modelo.Autuado;
import modelo.Autuante;
import modelo.Municipio;
import modelo.Ocorrencia;
import modelo.Sexo;
import modelo.Testemunha;
import modelo.TipoOcorrencia;
import util.Conexao;

/**
 *
 * @author user
 */
public class OcorrenciaDAO implements GenericoDAO<Ocorrencia> {

    private static final String CAMPOS_CONSULTA
            = "o.id_ocorrencia, "
            + "o.data_ocorrencia, "
            + "o.hora_ocorrencia, "
            + "o.descricao_ocorrencia, "
            + "o.rua_ocorrencia, "
            + "o.cidade_ocorrencia, "
            + "o.bairro_ocorrencia, "
            + "o.proximidade_ocorrencia, "
            + "a.id_autuado AS autuado_id, "
            + "a.nome_autuado AS autuado_nome, "
            + "a.pai_autuado AS autuado_pai, "
            + "a.mae_autuado AS autuado_mae, "
            + "a.estado_civil_autuado AS autuado_estado_civil, "
            + "a.data_nascimento_autuado AS autuado_data_nascimento, "
            + "a.sexo_autuado AS autuado_sexo, "
            + "a.bi_autuado AS autuado_bi, "
            + "a.data_emissao_bi_autuado AS autuado_data_emissao_bi, "
            + "a.telefone_autuado AS autuado_telefone, "
            + "at.id_autuante AS autuante_id, "
            + "at.nome_autuante AS autuante_nome, "
            + "at.pai_autuante AS autuante_pai, "
            + "at.mae_autuante AS autuante_mae, "
            + "at.data_nascimento_autuante AS autuante_data_nascimento, "
            + "at.sexo_autuante AS autuante_sexo, "
            + "at.bi_autuante AS autuante_bi, "
            + "at.data_emissao_bi_autuante AS autuante_data_emissao_bi, "
            + "at.nip_autuante AS autuante_nip, "
            + "at.telefone_autuante AS autuante_telefone, "
            + "t.id_tipo_ocorrencia AS tipo_ocorrencia_id, "
            + "t.nome_tipo_ocorrencia AS tipo_ocorrencia_nome, "
            + "tt.id_testemunha AS testemunha_id, "
            + "tt.nome_testemunha AS testemunha_nome, "
            + "tt.data_nascimento_testemunha AS testemunha_data_nascimento, "
            + "tt.sexo_testemunha AS testemunha_sexo, "
            + "tt.bi_testemunha AS testemunha_bi, "
            + "tt.telefone_testemunha AS testemunha_telefone, "
            + "tt1.id_testemunha AS testemunha1_id, "
            + "tt1.nome_testemunha AS testemunha1_nome, "
            + "tt1.data_nascimento_testemunha AS testemunha1_data_nascimento, "
            + "tt1.sexo_testemunha AS testemunha1_sexo, "
            + "tt1.bi_testemunha AS testemunha1_bi, "
            + "tt1.telefone_testemunha AS testemunha1_telefone, "
            + "m.id_municipio AS municipio_id, "
            + "m.nome_municipio AS municipio_nome";

    private static final String BASE_SELECT
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM ocorrencia o "
            + "INNER JOIN autuado a ON o.id_autuado = a.id_autuado "
            + "INNER JOIN autuante at ON o.id_autuante = at.id_autuante "
            + "INNER JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia "
            + "INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha "
            + "LEFT JOIN testemunha tt1 ON o.id_testemunha1 = tt1.id_testemunha "
            + "INNER JOIN municipio m ON o.id_municipio = m.id_municipio ";

    private static final String INSERIR
            = "INSERT INTO ocorrencia "
            + "(data_ocorrencia, hora_ocorrencia, descricao_ocorrencia, rua_ocorrencia, "
            + "cidade_ocorrencia, bairro_ocorrencia, proximidade_ocorrencia, "
            + "id_autuado, id_autuante, id_tipo_ocorrencia, id_testemunha, id_testemunha1, id_municipio) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String ACTUALIZAR
            = "UPDATE ocorrencia SET "
            + "data_ocorrencia = ?, "
            + "hora_ocorrencia = ?, "
            + "descricao_ocorrencia = ?, "
            + "rua_ocorrencia = ?, "
            + "cidade_ocorrencia = ?, "
            + "bairro_ocorrencia = ?, "
            + "proximidade_ocorrencia = ?, "
            + "id_autuado = ?, "
            + "id_autuante = ?, "
            + "id_tipo_ocorrencia = ?, "
            + "id_testemunha = ?, "
            + "id_testemunha1 = ?, "
            + "id_municipio = ? "
            + "WHERE id_ocorrencia = ?";

    private static final String ELIMINAR
            = "DELETE FROM ocorrencia WHERE id_ocorrencia = ?";

    private static final String BUSCAR_POR_CODIGO
            = BASE_SELECT
            + "WHERE o.id_ocorrencia = ?";

    private static final String LISTAR_TUDO
            = BASE_SELECT
            + "ORDER BY o.data_ocorrencia DESC, o.id_ocorrencia DESC";

    private static final String LISTAR_POR_AUTUADO
            = BASE_SELECT
            + "WHERE a.nome_autuado LIKE ? "
            + "ORDER BY a.nome_autuado";

    private static final String LISTAR_POR_AUTUANTE
            = BASE_SELECT
            + "WHERE at.nome_autuante LIKE ? "
            + "ORDER BY at.nome_autuante";

    private static final String LISTAR_POR_CIDADE
            = BASE_SELECT
            + "WHERE o.cidade_ocorrencia LIKE ? "
            + "ORDER BY o.cidade_ocorrencia";

    private static final String LISTAR_POR_DATA
            = BASE_SELECT
            + "WHERE o.data_ocorrencia = ? "
            + "ORDER BY o.data_ocorrencia DESC";

    private static final String LISTAR_POR_TESTEMUNHA
            = BASE_SELECT
            + "WHERE tt.nome_testemunha LIKE ? OR tt1.nome_testemunha LIKE ? "
            + "ORDER BY tt.nome_testemunha";

    private static final String LISTAR_POR_TIPO
            = BASE_SELECT
            + "WHERE t.nome_tipo_ocorrencia LIKE ? "
            + "ORDER BY t.nome_tipo_ocorrencia";

    private static final int TOTAL_OCORRENCIAS_POR_PAGINA = 6;

    private static final String CONTAR_OCORRENCIAS
            = "SELECT COUNT(1) AS total_ocorrencias FROM ocorrencia";

    private static final String CONSULTAR_PAGINA
            = BASE_SELECT
            + "ORDER BY o.data_ocorrencia DESC, o.id_ocorrencia DESC "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_OCORRENCIAS_POR_AUTUADO
            = "SELECT COUNT(1) AS total_ocorrencias "
            + "FROM ocorrencia o "
            + "INNER JOIN autuado a ON o.id_autuado = a.id_autuado "
            + "WHERE a.nome_autuado LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_AUTUADO
            = BASE_SELECT
            + "WHERE a.nome_autuado LIKE ? "
            + "ORDER BY a.nome_autuado "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_OCORRENCIAS_POR_AUTUANTE
            = "SELECT COUNT(1) AS total_ocorrencias "
            + "FROM ocorrencia o "
            + "INNER JOIN autuante at ON o.id_autuante = at.id_autuante "
            + "WHERE at.nome_autuante LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_AUTUANTE
            = BASE_SELECT
            + "WHERE at.nome_autuante LIKE ? "
            + "ORDER BY at.nome_autuante "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_OCORRENCIAS_POR_CIDADE
            = "SELECT COUNT(1) AS total_ocorrencias "
            + "FROM ocorrencia "
            + "WHERE cidade_ocorrencia LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_CIDADE
            = BASE_SELECT
            + "WHERE o.cidade_ocorrencia LIKE ? "
            + "ORDER BY o.cidade_ocorrencia "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_OCORRENCIAS_POR_DATA
            = "SELECT COUNT(1) AS total_ocorrencias "
            + "FROM ocorrencia "
            + "WHERE data_ocorrencia = ?";

    private static final String CONSULTAR_PAGINA_POR_DATA
            = BASE_SELECT
            + "WHERE o.data_ocorrencia = ? "
            + "ORDER BY o.data_ocorrencia DESC, o.id_ocorrencia DESC "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_OCORRENCIAS_POR_TESTEMUNHA
            = "SELECT COUNT(1) AS total_ocorrencias "
            + "FROM ocorrencia o "
            + "INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha "
            + "LEFT JOIN testemunha tt1 ON o.id_testemunha1 = tt1.id_testemunha "
            + "WHERE tt.nome_testemunha LIKE ? OR tt1.nome_testemunha LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_TESTEMUNHA
            = BASE_SELECT
            + "WHERE tt.nome_testemunha LIKE ? OR tt1.nome_testemunha LIKE ? "
            + "ORDER BY tt.nome_testemunha "
            + "LIMIT ? OFFSET ?";

    @Override
    public void save(Ocorrencia ocorrencia) {
        if (ocorrencia == null) {
            System.err.println("Erro ao INSERIR ocorrência: o objeto ocorrência não pode ser nulo.");
            return;
        }

        if (!validarRelacionamentosObrigatorios(ocorrencia, "INSERIR")) {
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            preencherPreparedStatement(ps, ocorrencia, false);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Ocorrencia ocorrencia) {
        if (ocorrencia == null) {
            System.err.println("Erro ao ACTUALIZAR ocorrência: o objeto ocorrência não pode ser nulo.");
            return;
        }

        if (!validarRelacionamentosObrigatorios(ocorrencia, "ACTUALIZAR")) {
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            preencherPreparedStatement(ps, ocorrencia, true);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Ocorrencia ocorrencia) {
        if (ocorrencia == null) {
            System.err.println("Erro ao ELIMINAR ocorrência: o objeto ocorrência não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, ocorrencia.getIdOcorrencia());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados da ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Ocorrencia findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR ocorrência: o id não pode ser nulo.");
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
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                return ocorrencia;
            }

            System.err.println("Não foi encontrada nenhuma ocorrência com id: " + id);

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da ocorrência: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Ocorrencia> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);

            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao LISTAR dados das ocorrências: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return ocorrencias;
    }

    public List<Ocorrencia> findByAutuado(String autuado) {
        if (autuado == null) {
            autuado = "";
        }

        return buscarPorTexto(
                LISTAR_POR_AUTUADO,
                autuado,
                "Não foi encontrada nenhuma ocorrência com autuado: "
        );
    }

    public List<Ocorrencia> findByAutuante(String autuante) {
        if (autuante == null) {
            autuante = "";
        }

        return buscarPorTexto(
                LISTAR_POR_AUTUANTE,
                autuante,
                "Não foi encontrada nenhuma ocorrência com autuante: "
        );
    }

    public List<Ocorrencia> findByCidade(String cidade) {
        if (cidade == null) {
            cidade = "";
        }

        return buscarPorTexto(
                LISTAR_POR_CIDADE,
                cidade,
                "Não foi encontrada nenhuma ocorrência com cidade: "
        );
    }

    public List<Ocorrencia> findByData(java.sql.Date data) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

        if (data == null) {
            System.err.println("Erro ao BUSCAR ocorrência: a data não pode ser nula.");
            return ocorrencias;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_DATA);

            ps.setDate(1, data);

            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

            if (ocorrencias.isEmpty()) {
                System.err.println("Não foi encontrada nenhuma ocorrência com a data: " + data);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da ocorrência por data: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return ocorrencias;
    }

    public List<Ocorrencia> findByTestemunha(String testemunha) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

        if (testemunha == null) {
            testemunha = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_TESTEMUNHA);

            String termo = "%" + testemunha.trim() + "%";

            ps.setString(1, termo);
            ps.setString(2, termo);

            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

            if (ocorrencias.isEmpty()) {
                System.err.println("Não foi encontrada nenhuma ocorrência com testemunha: " + testemunha);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da ocorrência por testemunha: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return ocorrencias;
    }

    public List<Ocorrencia> findByTipo(String tipo) {
        if (tipo == null) {
            tipo = "";
        }

        return buscarPorTexto(
                LISTAR_POR_TIPO,
                tipo,
                "Não foi encontrada nenhuma ocorrência com tipo: "
        );
    }

    private List<Ocorrencia> buscarPorTexto(String sql, String valor, String mensagemSemResultado) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + valor.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

            if (ocorrencias.isEmpty()) {
                System.err.println(mensagemSemResultado + valor);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da ocorrência: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return ocorrencias;
    }

    public int quantidadePaginas() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int quantidadePaginas = 1;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONTAR_OCORRENCIAS);

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalOcorrencias = rs.getInt("total_ocorrencias");

                quantidadePaginas = (int) Math.ceil(
                        totalOcorrencias / (double) TOTAL_OCORRENCIAS_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular quantidade de páginas das ocorrências: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    public List<Ocorrencia> consultarPagina(String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_OCORRENCIAS_POR_PAGINA) - TOTAL_OCORRENCIAS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONSULTAR_PAGINA);

            ps.setInt(1, TOTAL_OCORRENCIAS_POR_PAGINA);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página de ocorrências: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return ocorrencias;
    }

    public int quantidadePaginasPorAutuado(String autuado) {
        if (autuado == null) {
            autuado = "";
        }

        return contarPaginasPorTexto(CONTAR_OCORRENCIAS_POR_AUTUADO, autuado);
    }

    public int quantidadePaginasPorAutuante(String autuante) {
        if (autuante == null) {
            autuante = "";
        }

        return contarPaginasPorTexto(CONTAR_OCORRENCIAS_POR_AUTUANTE, autuante);
    }

    public List<Ocorrencia> consultarPaginaPorAutuante(String autuante, String numeroPagina) {
        if (autuante == null) {
            autuante = "";
        }

        return consultarPaginaPorTexto(CONSULTAR_PAGINA_POR_AUTUANTE, autuante, numeroPagina);
    }

    public List<Ocorrencia> consultarPaginaPorAutuado(String autuado, String numeroPagina) {
        if (autuado == null) {
            autuado = "";
        }

        return consultarPaginaPorTexto(CONSULTAR_PAGINA_POR_AUTUADO, autuado, numeroPagina);
    }

    private int contarPaginasPorTexto(String sql, String filtro) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int quantidadePaginas = 1;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + filtro.trim() + "%");

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalOcorrencias = rs.getInt("total_ocorrencias");

                quantidadePaginas = (int) Math.ceil(
                        totalOcorrencias / (double) TOTAL_OCORRENCIAS_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular páginas filtradas das ocorrências: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    private List<Ocorrencia> consultarPaginaPorTexto(String sql, String filtro, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_OCORRENCIAS_POR_PAGINA) - TOTAL_OCORRENCIAS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + filtro.trim() + "%");
            ps.setInt(2, TOTAL_OCORRENCIAS_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar ocorrências filtradas com paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return ocorrencias;
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

    public int quantidadePaginasPorTestemunha(String testemunha) {
        if (testemunha == null) {
            testemunha = "";
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int quantidadePaginas = 1;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONTAR_OCORRENCIAS_POR_TESTEMUNHA);

            String termo = "%" + testemunha.trim() + "%";

            ps.setString(1, termo);
            ps.setString(2, termo);

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalOcorrencias = rs.getInt("total_ocorrencias");

                quantidadePaginas = (int) Math.ceil(
                        totalOcorrencias / (double) TOTAL_OCORRENCIAS_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular páginas por testemunha: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    public List<Ocorrencia> consultarPaginaPorTestemunha(String testemunha, String numeroPagina) {
        if (testemunha == null) {
            testemunha = "";
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_OCORRENCIAS_POR_PAGINA) - TOTAL_OCORRENCIAS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONSULTAR_PAGINA_POR_TESTEMUNHA);

            String termo = "%" + testemunha.trim() + "%";

            ps.setString(1, termo);
            ps.setString(2, termo);
            ps.setInt(3, TOTAL_OCORRENCIAS_POR_PAGINA);
            ps.setInt(4, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar ocorrências por testemunha com paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return ocorrencias;
    }

    public int quantidadePaginasPorCidade(String cidade) {
        if (cidade == null) {
            cidade = "";
        }

        return contarPaginasPorTexto(CONTAR_OCORRENCIAS_POR_CIDADE, cidade);
    }

    public int quantidadePaginasPorData(java.sql.Date data) {
        if (data == null) {
            return 1;
        }

        return contarPaginasPorData(CONTAR_OCORRENCIAS_POR_DATA, data);
    }

    public List<Ocorrencia> consultarPaginaPorData(java.sql.Date data, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

        if (data == null) {
            return ocorrencias;
        }

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_OCORRENCIAS_POR_PAGINA) - TOTAL_OCORRENCIAS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONSULTAR_PAGINA_POR_DATA);

            ps.setDate(1, data);
            ps.setInt(2, TOTAL_OCORRENCIAS_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar ocorrências por data com paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return ocorrencias;
    }

    private int contarPaginasPorData(String sql, java.sql.Date data) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int quantidadePaginas = 1;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setDate(1, data);

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalOcorrencias = rs.getInt("total_ocorrencias");

                quantidadePaginas = (int) Math.ceil(
                        totalOcorrencias / (double) TOTAL_OCORRENCIAS_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular páginas por data: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    public List<Ocorrencia> consultarPaginaPorCidade(String cidade, String numeroPagina) {
        if (cidade == null) {
            cidade = "";
        }

        return consultarPaginaPorTexto(CONSULTAR_PAGINA_POR_CIDADE, cidade, numeroPagina);
    }

    private void preencherPreparedStatement(PreparedStatement ps, Ocorrencia ocorrencia, boolean actualizar) throws SQLException {

        setDate(ps, 1, ocorrencia.getDataOcorrencia());

        ps.setString(2, ocorrencia.getHoraOcorrencia());
        ps.setString(3, ocorrencia.getDescricaoOcorrencia());
        ps.setString(4, ocorrencia.getRuaOcorrencia());
        ps.setString(5, ocorrencia.getCidadeOcorrencia());
        ps.setString(6, ocorrencia.getBairroOcorrencia());
        ps.setString(7, ocorrencia.getProximidadeOcorrencia());

        ps.setInt(8, ocorrencia.getAutuado().getIdAutuado());
        ps.setInt(9, ocorrencia.getAutuante().getIdAutuante());
        ps.setInt(10, ocorrencia.getTipoOcorrencia().getIdTipoOcorrencia());
        ps.setInt(11, ocorrencia.getTestemunha().getIdTestemunha());

        if (ocorrencia.getTestemunha1() != null) {
            ps.setInt(12, ocorrencia.getTestemunha1().getIdTestemunha());
        } else {
            ps.setNull(12, Types.INTEGER);
        }

        ps.setInt(13, ocorrencia.getMunicipio().getIdMunicipio());

        if (actualizar) {
            ps.setInt(14, ocorrencia.getIdOcorrencia());
        }
    }

    private boolean validarRelacionamentosObrigatorios(Ocorrencia ocorrencia, String operacao) {
        if (ocorrencia.getAutuado() == null) {
            System.err.println("Erro ao " + operacao + " ocorrência: o autuado não pode ser nulo.");
            return false;
        }

        if (ocorrencia.getAutuante() == null) {
            System.err.println("Erro ao " + operacao + " ocorrência: o autuante não pode ser nulo.");
            return false;
        }

        if (ocorrencia.getTipoOcorrencia() == null) {
            System.err.println("Erro ao " + operacao + " ocorrência: o tipo de ocorrência não pode ser nulo.");
            return false;
        }

        if (ocorrencia.getTestemunha() == null) {
            System.err.println("Erro ao " + operacao + " ocorrência: a testemunha principal não pode ser nula.");
            return false;
        }

        if (ocorrencia.getMunicipio() == null) {
            System.err.println("Erro ao " + operacao + " ocorrência: o município não pode ser nulo.");
            return false;
        }

        return true;
    }

    private void setDate(PreparedStatement ps, int index, java.util.Date data) throws SQLException {
        if (data != null) {
            ps.setDate(index, new java.sql.Date(data.getTime()));
        } else {
            ps.setNull(index, Types.DATE);
        }
    }

    private void popularComDados(Ocorrencia ocorrencia, ResultSet rs) throws SQLException {
        ocorrencia.setIdOcorrencia(rs.getInt("id_ocorrencia"));
        ocorrencia.setDataOcorrencia(rs.getDate("data_ocorrencia"));
        ocorrencia.setHoraOcorrencia(rs.getString("hora_ocorrencia"));
        ocorrencia.setDescricaoOcorrencia(rs.getString("descricao_ocorrencia"));
        ocorrencia.setRuaOcorrencia(rs.getString("rua_ocorrencia"));
        ocorrencia.setCidadeOcorrencia(rs.getString("cidade_ocorrencia"));
        ocorrencia.setBairroOcorrencia(rs.getString("bairro_ocorrencia"));
        ocorrencia.setProximidadeOcorrencia(rs.getString("proximidade_ocorrencia"));

        Autuado autuado = new Autuado();
        autuado.setIdAutuado(rs.getInt("autuado_id"));
        autuado.setNomeAutuado(rs.getString("autuado_nome"));
        autuado.setPaiAutuado(rs.getString("autuado_pai"));
        autuado.setMaeAutuado(rs.getString("autuado_mae"));
        autuado.setEstadoCivilAutuado(rs.getString("autuado_estado_civil"));
        autuado.setDataNascimentoAutuado(rs.getDate("autuado_data_nascimento"));
        autuado.setSexo(Sexo.getExtensao(rs.getString("autuado_sexo")));
        autuado.setBiAutuado(rs.getString("autuado_bi"));
        autuado.setDataEmissaoBiAutuado(rs.getDate("autuado_data_emissao_bi"));
        autuado.setTelefoneAutuado(rs.getString("autuado_telefone"));
        ocorrencia.setAutuado(autuado);

        Autuante autuante = new Autuante();
        autuante.setIdAutuante(rs.getInt("autuante_id"));
        autuante.setNomeAutuante(rs.getString("autuante_nome"));
        autuante.setPaiAutuante(rs.getString("autuante_pai"));
        autuante.setMaeAutuante(rs.getString("autuante_mae"));
        autuante.setDataNascimentoAutuante(rs.getDate("autuante_data_nascimento"));
        autuante.setSexo(Sexo.getExtensao(rs.getString("autuante_sexo")));
        autuante.setBiAutuante(rs.getString("autuante_bi"));
        autuante.setDataEmissaoBiAutuante(rs.getDate("autuante_data_emissao_bi"));
        autuante.setNipAutuante(rs.getInt("autuante_nip"));
        autuante.setTelefoneAutuante(rs.getString("autuante_telefone"));
        ocorrencia.setAutuante(autuante);

        TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
        tipoOcorrencia.setIdTipoOcorrencia(rs.getInt("tipo_ocorrencia_id"));
        tipoOcorrencia.setNomeTipoOcorrencia(rs.getString("tipo_ocorrencia_nome"));
        ocorrencia.setTipoOcorrencia(tipoOcorrencia);

        Testemunha testemunha = new Testemunha();
        testemunha.setIdTestemunha(rs.getInt("testemunha_id"));
        testemunha.setNomeTestemunha(rs.getString("testemunha_nome"));
        testemunha.setDataNascimentoTestemunha(rs.getDate("testemunha_data_nascimento"));
        testemunha.setSexo(Sexo.getExtensao(rs.getString("testemunha_sexo")));
        testemunha.setBiTestemunha(rs.getString("testemunha_bi"));
        testemunha.setTelefoneTestemunha(rs.getString("testemunha_telefone"));
        ocorrencia.setTestemunha(testemunha);

        int idTestemunha1 = rs.getInt("testemunha1_id");

        if (!rs.wasNull()) {
            Testemunha testemunha1 = new Testemunha();
            testemunha1.setIdTestemunha(idTestemunha1);
            testemunha1.setNomeTestemunha(rs.getString("testemunha1_nome"));
            testemunha1.setDataNascimentoTestemunha(rs.getDate("testemunha1_data_nascimento"));
            testemunha1.setSexo(Sexo.getExtensao(rs.getString("testemunha1_sexo")));
            testemunha1.setBiTestemunha(rs.getString("testemunha1_bi"));
            testemunha1.setTelefoneTestemunha(rs.getString("testemunha1_telefone"));
            ocorrencia.setTestemunha1(testemunha1);
        }

        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(rs.getInt("municipio_id"));
        municipio.setNomeMunicipio(rs.getString("municipio_nome"));
        ocorrencia.setMunicipio(municipio);
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
