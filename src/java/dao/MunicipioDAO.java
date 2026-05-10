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
import modelo.Provincia;
import util.Conexao;

/**
 *
 * @author user
 */
public class MunicipioDAO implements GenericoDAO<Municipio> {

    private static final int TOTAL_MUNICIPIOS_POR_PAGINA = 6;

    private static final String CAMPOS_CONSULTA
            = "m.id_municipio, "
            + "m.nome_municipio, "
            + "p.id_provincia, "
            + "p.nome_provincia";

    private static final String INSERIR
            = "INSERT INTO municipio (nome_municipio, id_provincia) VALUES (?, ?)";

    private static final String ACTUALIZAR
            = "UPDATE municipio SET nome_municipio = ?, id_provincia = ? WHERE id_municipio = ?";

    private static final String ELIMINAR
            = "DELETE FROM municipio WHERE id_municipio = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM municipio m "
            + "INNER JOIN provincia p ON m.id_provincia = p.id_provincia "
            + "WHERE m.id_municipio = ?";

    private static final String LISTAR_TUDO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM municipio m "
            + "INNER JOIN provincia p ON m.id_provincia = p.id_provincia "
            + "ORDER BY m.nome_municipio";

    private static final String LISTAR_POR_NOME
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM municipio m "
            + "INNER JOIN provincia p ON m.id_provincia = p.id_provincia "
            + "WHERE m.nome_municipio LIKE ? "
            + "ORDER BY m.nome_municipio";

    private static final String LISTAR_POR_PROVINCIA
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM municipio m "
            + "INNER JOIN provincia p ON m.id_provincia = p.id_provincia "
            + "WHERE m.id_provincia = ? "
            + "ORDER BY m.nome_municipio";

    private static final String CONTAR_MUNICIPIOS
            = "SELECT COUNT(1) AS total_municipios FROM municipio";

    private static final String CONSULTAR_PAGINA
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM municipio m "
            + "INNER JOIN provincia p ON m.id_provincia = p.id_provincia "
            + "ORDER BY m.nome_municipio "
            + "LIMIT ? OFFSET ?";

    @Override
    public void save(Municipio municipio) {
        if (municipio == null) {
            System.err.println("Erro ao INSERIR município: o objeto município não pode ser nulo.");
            return;
        }

        if (municipio.getProvincia() == null) {
            System.err.println("Erro ao INSERIR município: a província do município não pode ser nula.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getProvincia().getIdProvincia());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do município: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Municipio municipio) {
        if (municipio == null) {
            System.err.println("Erro ao ACTUALIZAR município: o objeto município não pode ser nulo.");
            return;
        }

        if (municipio.getProvincia() == null) {
            System.err.println("Erro ao ACTUALIZAR município: a província do município não pode ser nula.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            ps.setString(1, municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getProvincia().getIdProvincia());
            ps.setInt(3, municipio.getIdMunicipio());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados do município: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Municipio municipio) {
        if (municipio == null) {
            System.err.println("Erro ao ELIMINAR município: o objeto município não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, municipio.getIdMunicipio());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados do município: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Municipio findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR município: o id não pode ser nulo.");
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
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                return municipio;
            }

            System.err.println("Não foi encontrado nenhum município com id: " + id);

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do município: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Municipio> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Municipio> municipios = new ArrayList<Municipio>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);

            rs = ps.executeQuery();

            while (rs.next()) {
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                municipios.add(municipio);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao LISTAR dados dos municípios: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return municipios;
    }

    public List<Municipio> findByNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Municipio> municipios = new ArrayList<Municipio>();

        if (nome == null) {
            nome = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                municipios.add(municipio);
            }

            if (municipios.isEmpty()) {
                System.err.println("Não foi encontrado nenhum município com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do município por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return municipios;
    }

    public List<Municipio> findByProvincia(int provincia) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Municipio> municipios = new ArrayList<Municipio>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_PROVINCIA);

            ps.setInt(1, provincia);

            rs = ps.executeQuery();

            while (rs.next()) {
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                municipios.add(municipio);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR municípios por província: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return municipios;
    }

    public int quantidadePagina() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int quantidadePagina = 1;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONTAR_MUNICIPIOS);

            rs = ps.executeQuery();

            if (rs.next()) {
                int totalMunicipios = rs.getInt("total_municipios");

                quantidadePagina = (int) Math.ceil(
                        totalMunicipios / (double) TOTAL_MUNICIPIOS_POR_PAGINA
                );

                if (quantidadePagina < 1) {
                    quantidadePagina = 1;
                }
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao calcular quantidade de páginas de municípios: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return quantidadePagina;
    }

    public List<Municipio> consultarPagina(String numeroPagina) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Municipio> municipios = new ArrayList<Municipio>();

        int pagina = converterNumeroPagina(numeroPagina);
        int offset = (pagina * TOTAL_MUNICIPIOS_POR_PAGINA) - TOTAL_MUNICIPIOS_POR_PAGINA;

        if (offset < 0) {
            offset = 0;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(CONSULTAR_PAGINA);

            ps.setInt(1, TOTAL_MUNICIPIOS_POR_PAGINA);
            ps.setInt(2, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                municipios.add(municipio);
            }

        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return municipios;
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

    private void popularComDados(Municipio municipio, ResultSet rs) throws SQLException {
        municipio.setIdMunicipio(rs.getInt("id_municipio"));
        municipio.setNomeMunicipio(rs.getString("nome_municipio"));

        Provincia provincia = new Provincia();
        provincia.setIdProvincia(rs.getInt("id_provincia"));
        provincia.setNomeProvincia(rs.getString("nome_provincia"));

        municipio.setProvincia(provincia);
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
