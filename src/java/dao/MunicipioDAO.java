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

    private static final String INSERIR = "INSERT INTO municipio (nome_municipio, id_provincia) VALUES(?, ?)";
    private static final String ACTUALIZAR = "UPDATE municipio SET nome_municipio = ?, id_provincia = ? WHERE id_municipio = ?";
    private static final String ELIMINAR = "DETELE FROM municipio WHERE id_municipio = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia WHERE id_municipio = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia";
    private static final String LISTAR_POR_NOME = "SELECT * FROM municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia WHERE m.nome_municipio LIKE ? ORDER BY m.nome_municipio";
    private static final String LISTAR_POR_PROVINCIA = "SELECT * FROM municipio m INNER JOIN provincia p ON m.id_provincia = p.id_provincia WHERE m.id_provincia LIKE ? order by m.nome_municipio";

    @Override
    public void save(Municipio municipio) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (municipio == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getProvincia().getIdProvincia());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do municipio: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Municipio municipio) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (municipio == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, municipio.getNomeMunicipio());
            ps.setInt(2, municipio.getProvincia().getIdProvincia());
            ps.setInt(3, municipio.getIdMunicipio());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados do municipio: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Municipio municipio) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (municipio == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, municipio.getIdMunicipio());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados do municipio: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Municipio findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Municipio municipio = new Municipio();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(municipio, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do municipio: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return municipio;
    }

    @Override
    public List<Municipio> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Municipio> municipios = new ArrayList<>();
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
            System.err.println("Erro ao LER dados do municipio: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return municipios;
    }

    public List<Municipio> findByNome(String nome) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Municipio> municipios = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Municipio municipio = new Municipio();
                popularComDados(municipio, rs);
                municipios.add(municipio);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum municipio com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do municipio: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return municipios;
    }

    public List<Municipio> findByProvincia(int provincia) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Municipio> municipios = new ArrayList<>();
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
            System.err.println("Erro ao ler dados municipio: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return municipios;
    }

    public int quantidadePagina() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(1) AS totalMunicipios FROM municipio;";
            int quantidadePagina = 1;
            double totalMunicipiosPorPagina = 6.0;

            conn = Conexao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                double totalMunicipios = rs.getDouble("totalMunicipios");
                if (totalMunicipios > totalMunicipiosPorPagina) {
                    double quantidadePaginaTemp = Float.parseFloat("" + (totalMunicipios / totalMunicipiosPorPagina));
                    if (!(quantidadePaginaTemp % 2 == 0)) {
                        quantidadePagina = new Double(quantidadePaginaTemp).intValue() + 1;
                    } else {
                        quantidadePagina = new Double(quantidadePaginaTemp).intValue();
                    }
                } else {
                    quantidadePagina = 1;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro quantidade de página município!");
        } finally {
            Conexao.closeConnection(conn);
        }
        return quantidadePagina();
    }

    public List<Municipio> consultarPagina(String numeroPagina) throws Exception {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        int totalMunicipioPorPagina = 6;
        if (numeroPagina == null || (numeroPagina != null && numeroPagina.trim().isEmpty())) {
            numeroPagina = "0";
        }

        int offset = (Integer.parseInt(numeroPagina) * totalMunicipioPorPagina) - totalMunicipioPorPagina;
        if (offset < 0) {
            offset = 0;
        }

        List<Municipio> retorno = new ArrayList<Municipio>();
        String sql = "SELECT * FROM municipio LIMIT " + totalMunicipioPorPagina + " OFFSET " + offset + "; ";

        conn = Conexao.getConnection();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            Municipio municipio = new Municipio();
            popularComDados(municipio, rs);
            retorno.add(municipio);
        }

        return retorno;
    }

    private void popularComDados(Municipio municipio, ResultSet rs) {
        try {
            municipio.setIdMunicipio(rs.getInt("m.id_municipio"));
            municipio.setNomeMunicipio(rs.getString("m.nome_municipio"));

            Provincia provincia = new Provincia();
            provincia.setIdProvincia(rs.getInt("p.id_provincia"));
            provincia.setNomeProvincia(rs.getString("p.nome_provincia"));
            municipio.setProvincia(provincia);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados do município: " + ex.getLocalizedMessage());
        }
    }
}
