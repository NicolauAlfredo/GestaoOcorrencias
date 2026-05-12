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
import modelo.Autuante;
import modelo.Municipio;
import modelo.Patente;
import modelo.PostoTrabalho;
import modelo.Sexo;
import util.Conexao;

/**
 *
 * @author user
 */
public class AutuanteDAO implements GenericoDAO<Autuante> {

    private static final String CAMPOS_CONSULTA
            = "a.id_autuante, "
            + "a.nome_autuante, "
            + "a.pai_autuante, "
            + "a.mae_autuante, "
            + "a.bi_autuante, "
            + "a.residencia_autuante, "
            + "a.data_nascimento_autuante, "
            + "a.sexo_autuante, "
            + "a.altura_autuante, "
            + "a.data_emissao_bi_autuante, "
            + "a.data_validade_bi_autuante, "
            + "a.nip_autuante, "
            + "a.telefone_autuante, "
            + "p.id_patente, "
            + "p.nome_patente, "
            + "m.id_municipio, "
            + "m.nome_municipio, "
            + "pt.id_posto_trabalho, "
            + "pt.nome_posto_trabalho, "
            + "pt.numero_posto_trabalho";

    private static final String INSERIR
            = "INSERT INTO autuante "
            + "(nome_autuante, pai_autuante, mae_autuante, bi_autuante, residencia_autuante, "
            + "data_nascimento_autuante, sexo_autuante, altura_autuante, "
            + "data_emissao_bi_autuante, data_validade_bi_autuante, nip_autuante, telefone_autuante, "
            + "id_patente, id_municipio, id_posto_trabalho) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String ACTUALIZAR
            = "UPDATE autuante SET "
            + "nome_autuante = ?, "
            + "pai_autuante = ?, "
            + "mae_autuante = ?, "
            + "bi_autuante = ?, "
            + "residencia_autuante = ?, "
            + "data_nascimento_autuante = ?, "
            + "sexo_autuante = ?, "
            + "altura_autuante = ?, "
            + "data_emissao_bi_autuante = ?, "
            + "data_validade_bi_autuante = ?, "
            + "nip_autuante = ?, "
            + "telefone_autuante = ?, "
            + "id_patente = ?, "
            + "id_municipio = ?, "
            + "id_posto_trabalho = ? "
            + "WHERE id_autuante = ?";

    private static final String ELIMINAR
            = "DELETE FROM autuante WHERE id_autuante = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "WHERE a.id_autuante = ?";

    private static final String LISTAR_TUDO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "ORDER BY a.nome_autuante";

    private static final String LISTAR_POR_NOME
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "WHERE a.nome_autuante LIKE ? "
            + "ORDER BY a.nome_autuante";

    private static final String LISTAR_POR_DATA
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "WHERE a.data_nascimento_autuante = ? "
            + "ORDER BY a.nome_autuante";

    private static final String LISTAR_POR_BI
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "WHERE a.bi_autuante LIKE ? "
            + "ORDER BY a.nome_autuante";

    private static final String LISTAR_POR_NIP
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "WHERE a.nip_autuante = ? "
            + "ORDER BY a.nome_autuante";

    private static final int TOTAL_AUTUANTES_POR_PAGINA = 6;

    private static final String CONTAR_AUTUANTES
            = "SELECT COUNT(1) AS total_autuantes FROM autuante";

    private static final String CONSULTAR_PAGINA
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "ORDER BY a.nome_autuante "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_AUTUANTES_POR_NOME
            = "SELECT COUNT(1) AS total_autuantes FROM autuante WHERE nome_autuante LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_NOME
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "WHERE a.nome_autuante LIKE ? "
            + "ORDER BY a.nome_autuante "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_AUTUANTES_POR_BI
            = "SELECT COUNT(1) AS total_autuantes FROM autuante WHERE bi_autuante LIKE ?";

    private static final String CONSULTAR_PAGINA_POR_BI
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "WHERE a.bi_autuante LIKE ? "
            + "ORDER BY a.nome_autuante "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_AUTUANTES_POR_DATA
            = "SELECT COUNT(1) AS total_autuantes FROM autuante WHERE data_nascimento_autuante = ?";

    private static final String CONSULTAR_PAGINA_POR_DATA
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "WHERE a.data_nascimento_autuante = ? "
            + "ORDER BY a.nome_autuante "
            + "LIMIT ? OFFSET ?";

    private static final String CONTAR_AUTUANTES_POR_NIP
            = "SELECT COUNT(1) AS total_autuantes FROM autuante WHERE nip_autuante = ?";

    private static final String CONSULTAR_PAGINA_POR_NIP
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuante a "
            + "INNER JOIN patente p ON a.id_patente = p.id_patente "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho "
            + "WHERE a.nip_autuante = ? "
            + "ORDER BY a.nome_autuante "
            + "LIMIT ? OFFSET ?";

    @Override
    public void save(Autuante autuante) {
        if (autuante == null) {
            System.err.println("Erro ao INSERIR autuante: o objeto autuante não pode ser nulo.");
            return;
        }

        if (autuante.getMunicipio() == null) {
            System.err.println("Erro ao INSERIR autuante: o município não pode ser nulo.");
            return;
        }

        if (autuante.getPostoTrabalho() == null) {
            System.err.println("Erro ao INSERIR autuante: o posto de trabalho não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            preencherPreparedStatement(ps, autuante, false);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do autuante: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Autuante autuante) {
        if (autuante == null) {
            System.err.println("Erro ao ACTUALIZAR autuante: o objeto autuante não pode ser nulo.");
            return;
        }

        if (autuante.getMunicipio() == null) {
            System.err.println("Erro ao ACTUALIZAR autuante: o município não pode ser nulo.");
            return;
        }

        if (autuante.getPostoTrabalho() == null) {
            System.err.println("Erro ao ACTUALIZAR autuante: o posto de trabalho não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            preencherPreparedStatement(ps, autuante, true);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados do autuante: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Autuante autuante) {
        if (autuante == null) {
            System.err.println("Erro ao ELIMINAR autuante: o objeto autuante não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, autuante.getIdAutuante());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados do autuante: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Autuante findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR autuante: o id não pode ser nulo.");
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
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                return autuante;
            }

            System.err.println("Não foi encontrado nenhum autuante com id: " + id);

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuante: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Autuante> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuante> autuantes = new ArrayList<Autuante>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao LISTAR dados dos autuantes: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuantes;
    }

    public List<Autuante> findByNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuante> autuantes = new ArrayList<Autuante>();

        if (nome == null) {
            nome = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

            if (autuantes.isEmpty()) {
                System.err.println("Não foi encontrado nenhum autuante com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuante por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuantes;
    }

    public List<Autuante> findByData(java.sql.Date data) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuante> autuantes = new ArrayList<Autuante>();

        if (data == null) {
            System.err.println("Erro ao BUSCAR autuante: a data não pode ser nula.");
            return autuantes;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_DATA);

            ps.setDate(1, data);

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

            if (autuantes.isEmpty()) {
                System.err.println("Não foi encontrado nenhum autuante com a data: " + data);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuante por data: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuantes;
    }

    public List<Autuante> findByBi(String numeroBI) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuante> autuantes = new ArrayList<Autuante>();

        if (numeroBI == null) {
            numeroBI = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_BI);

            ps.setString(1, "%" + numeroBI.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

            if (autuantes.isEmpty()) {
                System.err.println("Não foi encontrado nenhum autuante com o número do B.I.: " + numeroBI);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuante por B.I.: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuantes;
    }

    public List<Autuante> findByNip(Integer nip) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuante> autuantes = new ArrayList<Autuante>();

        if (nip == null) {
            System.err.println("Erro ao BUSCAR autuante: o NIP não pode ser nulo.");
            return autuantes;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NIP);

            ps.setInt(1, nip);

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

            if (autuantes.isEmpty()) {
                System.err.println("Não foi encontrado nenhum autuante com o NIP: " + nip);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuante por NIP: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuantes;
    }

    public int quantidadePaginas() {
        return contarPaginas(CONTAR_AUTUANTES, null, null, null);
    }

    public List<Autuante> consultarPagina(String numeroPagina) {
        return consultarPaginaSemFiltro(CONSULTAR_PAGINA, numeroPagina);
    }

    public int quantidadePaginasPorNome(String nome) {
        if (nome == null) {
            nome = "";
        }

        return contarPaginas(CONTAR_AUTUANTES_POR_NOME, "%" + nome.trim() + "%", null, null);
    }

    public List<Autuante> consultarPaginaPorNome(String nome, String numeroPagina) {
        if (nome == null) {
            nome = "";
        }

        return consultarPaginaComTexto(CONSULTAR_PAGINA_POR_NOME, nome, numeroPagina);
    }

    public int quantidadePaginasPorBi(String bi) {
        if (bi == null) {
            bi = "";
        }

        return contarPaginas(CONTAR_AUTUANTES_POR_BI, "%" + bi.trim() + "%", null, null);
    }

    public List<Autuante> consultarPaginaPorBi(String bi, String numeroPagina) {
        if (bi == null) {
            bi = "";
        }

        return consultarPaginaComTexto(CONSULTAR_PAGINA_POR_BI, bi, numeroPagina);
    }

    public int quantidadePaginasPorData(java.sql.Date data) {
        if (data == null) {
            return 1;
        }

        return contarPaginas(CONTAR_AUTUANTES_POR_DATA, null, data, null);
    }

    public List<Autuante> consultarPaginaPorData(java.sql.Date data, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuante> autuantes = new ArrayList<Autuante>();

        if (data == null) {
            return autuantes;
        }

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_AUTUANTES_POR_PAGINA) - TOTAL_AUTUANTES_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONSULTAR_PAGINA_POR_DATA);

            ps.setDate(1, data);
            ps.setInt(2, TOTAL_AUTUANTES_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar autuantes por data com paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuantes;
    }

    public int quantidadePaginasPorNip(Integer nip) {
        if (nip == null) {
            return 1;
        }

        return contarPaginas(CONTAR_AUTUANTES_POR_NIP, null, null, nip);
    }

    public List<Autuante> consultarPaginaPorNip(Integer nip, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuante> autuantes = new ArrayList<Autuante>();

        if (nip == null) {
            return autuantes;
        }

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_AUTUANTES_POR_PAGINA) - TOTAL_AUTUANTES_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONSULTAR_PAGINA_POR_NIP);

            ps.setInt(1, nip);
            ps.setInt(2, TOTAL_AUTUANTES_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar autuantes por NIP com paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuantes;
    }

    private int contarPaginas(String sql, String filtroTexto, java.sql.Date filtroData, Integer filtroNumero) {
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

            if (filtroNumero != null) {
                ps.setInt(1, filtroNumero);
            }

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalAutuantes = rs.getInt("total_autuantes");

                quantidadePaginas = (int) Math.ceil(
                        totalAutuantes / (double) TOTAL_AUTUANTES_POR_PAGINA
                );

                if (quantidadePaginas < 1) {
                    quantidadePaginas = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular quantidade de páginas dos autuantes: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePaginas;
    }

    private List<Autuante> consultarPaginaSemFiltro(String sql, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuante> autuantes = new ArrayList<Autuante>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_AUTUANTES_POR_PAGINA) - TOTAL_AUTUANTES_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, TOTAL_AUTUANTES_POR_PAGINA);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar página de autuantes: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuantes;
    }

    private List<Autuante> consultarPaginaComTexto(String sql, String filtro, String numeroPagina) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuante> autuantes = new ArrayList<Autuante>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_AUTUANTES_POR_PAGINA) - TOTAL_AUTUANTES_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + filtro.trim() + "%");
            ps.setInt(2, TOTAL_AUTUANTES_POR_PAGINA);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao consultar autuantes com filtro e paginação: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuantes;
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

    private void preencherPreparedStatement(PreparedStatement ps, Autuante autuante, boolean actualizar) throws SQLException {

        ps.setString(1, autuante.getNomeAutuante());
        ps.setString(2, autuante.getPaiAutuante());
        ps.setString(3, autuante.getMaeAutuante());
        ps.setString(4, autuante.getBiAutuante());
        ps.setString(5, autuante.getResidenciaAutuante());

        setDate(ps, 6, autuante.getDataNascimentoAutuante());

        if (autuante.getSexo() != null) {
            ps.setString(7, autuante.getSexo().getExtensao());
        } else {
            ps.setNull(7, Types.VARCHAR);
        }

        ps.setDouble(8, autuante.getAlturaAutuante());

        setDate(ps, 9, autuante.getDataEmissaoBiAutuante());
        setDate(ps, 10, autuante.getDataValidadeBiAutuante());

        ps.setInt(11, autuante.getNipAutuante());
        ps.setString(12, autuante.getTelefoneAutuante());

        if (autuante.getPatente() != null) {
            ps.setInt(13, autuante.getPatente().getIdPatente());
        } else {
            ps.setNull(13, Types.INTEGER);
        }

        ps.setInt(14, autuante.getMunicipio().getIdMunicipio());
        ps.setInt(15, autuante.getPostoTrabalho().getIdPostoTrabalho());

        if (actualizar) {
            ps.setInt(16, autuante.getIdAutuante());
        }
    }

    private void setDate(PreparedStatement ps, int index, java.util.Date data) throws SQLException {
        if (data != null) {
            ps.setDate(index, new java.sql.Date(data.getTime()));
        } else {
            ps.setNull(index, Types.DATE);
        }
    }

    private void popularComDados(Autuante autuante, ResultSet rs) throws SQLException {
        autuante.setIdAutuante(rs.getInt("id_autuante"));
        autuante.setNomeAutuante(rs.getString("nome_autuante"));
        autuante.setPaiAutuante(rs.getString("pai_autuante"));
        autuante.setMaeAutuante(rs.getString("mae_autuante"));
        autuante.setBiAutuante(rs.getString("bi_autuante"));
        autuante.setResidenciaAutuante(rs.getString("residencia_autuante"));
        autuante.setDataNascimentoAutuante(rs.getDate("data_nascimento_autuante"));

        String sexoAutuante = rs.getString("sexo_autuante");
        autuante.setSexo(Sexo.getExtensao(sexoAutuante));

        autuante.setAlturaAutuante(rs.getDouble("altura_autuante"));
        autuante.setDataEmissaoBiAutuante(rs.getDate("data_emissao_bi_autuante"));
        autuante.setDataValidadeBiAutuante(rs.getDate("data_validade_bi_autuante"));
        autuante.setNipAutuante(rs.getInt("nip_autuante"));
        autuante.setTelefoneAutuante(rs.getString("telefone_autuante"));

        Patente patente = new Patente();
        patente.setIdPatente(rs.getInt("id_patente"));
        patente.setNomePatente(rs.getString("nome_patente"));
        autuante.setPatente(patente);

        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(rs.getInt("id_municipio"));
        municipio.setNomeMunicipio(rs.getString("nome_municipio"));
        autuante.setMunicipio(municipio);

        PostoTrabalho postoTrabalho = new PostoTrabalho();
        postoTrabalho.setIdPostoTrabalho(rs.getInt("id_posto_trabalho"));
        postoTrabalho.setNomePostoTrabalho(rs.getString("nome_posto_trabalho"));
        postoTrabalho.setNumeroPostoTrabalho(rs.getInt("numero_posto_trabalho"));
        autuante.setPostoTrabalho(postoTrabalho);
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
