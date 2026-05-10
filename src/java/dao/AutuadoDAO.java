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
import modelo.Autuado;
import modelo.Municipio;
import modelo.Profissao;
import util.Conexao;

/**
 *
 * @author user
 */
public class AutuadoDAO implements GenericoDAO<Autuado> {

    private static final String INSERIR = "INSERT INTO autuado (nome_autuado, pai_autuado, mae_autuado, bi_autuado, residencia_autuado, data_nascimento_autuado, sexo_autuado, proximidade_autuado, estado_civil_autuado, data_emissao_bi_autuado, data_validade_bi_autuado, telefone_autuado, id_profissao, id_municipio) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE autuado SET nome_autuado = ?, pai_autuado = ?, mae_autuado = ?, bi_autuado = ?, residencia_autuado = ?, data_nascimento_autuado = ?, sexo_autuado = ?, proximidade_autuado = ?, estado_civil_autuado = ?, data_emissao_bi_autuado = ?, data_validade_bi_autuado = ?, telefone_autuado = ?, id_profissao = ?, id_municipio = ? WHERE id_autuado = ?";
    private static final String ELIMINAR = "DELETE FROM autuado WHERE id_autuado = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM autuado a INNER JOIN profissao p ON a.id_profissao = p.id_profissao INNER JOIN municipio m ON a.id_municipio = m.id_municipio WHERE a.id_autuado = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM autuado a INNER JOIN profissao p ON a.id_profissao = p.id_profissao INNER JOIN municipio m ON a.id_municipio = m.id_municipio";
    private static final String LISTAR_POR_NOME = "SELECT * FROM autuado a INNER JOIN profissao p ON a.id_profissao = p.id_profissao INNER JOIN municipio m ON a.id_municipio = m.id_municipio WHERE a.nome_autuado LIKE ? ORDER BY a.nome_autuado";
    private static final String LISTAR_POR_DATA = "SELECT * FROM autuado a INNER JOIN profissao p ON a.id_profissao = p.id_profissao INNER JOIN municipio m ON a.id_municipio = m.id_municipio WHERE a.data_nascimento_autuado LIKE ? ORDER BY a.nome_autuado";
    private static final String LISTAR_POR_BI = "SELECT * FROM autuado a INNER JOIN profissao p ON a.id_profissao = p.id_profissao INNER JOIN municipio m ON a.id_municipio = m.id_municipio WHERE a.bi_autuado LIKE ? ORDER BY a.nome_autuado";
    private static final String TOTAL_AUTUADOS = "SELECT COUNT(*) as total FROM autuado";
    
    @Override
    public void save(Autuado autuado) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (autuado == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, autuado.getNomeAutuado());
            ps.setString(2, autuado.getPaiAutuado());
            ps.setString(3, autuado.getMaeAutuado());
            ps.setString(4, autuado.getBiAutuado());
            ps.setString(5, autuado.getResidenciaAutuado());
            ps.setDate(6, new java.sql.Date(autuado.getDataNascimentoAutuado().getTime()));
            ps.setString(7, autuado.getSexo().getExtensao()); 
            ps.setString(8, autuado.getProximidadeAutuado());
            ps.setString(9, autuado.getEstadoCivilAutuado());
            ps.setDate(10, new java.sql.Date(autuado.getDataEmissaoBiAutuado().getTime()));
            ps.setDate(11, new java.sql.Date(autuado.getDataValidadeBiAutuado().getTime()));
            ps.setString(12, autuado.getTelefoneAutuado());
            ps.setInt(13, autuado.getProfissao().getIdProfissao());
            ps.setInt(14, autuado.getMunicipio().getIdMunicipio());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Autuado autuado) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (autuado == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, autuado.getNomeAutuado());
            ps.setString(2, autuado.getPaiAutuado());
            ps.setString(3, autuado.getMaeAutuado());
            ps.setString(4, autuado.getBiAutuado());
            ps.setString(5, autuado.getResidenciaAutuado());
            ps.setDate(6, new java.sql.Date(autuado.getDataNascimentoAutuado().getTime()));
            ps.setString(7, autuado.getSexo().getExtensao()); // Fixar bm isso
            ps.setString(8, autuado.getProximidadeAutuado());
            ps.setString(9, autuado.getEstadoCivilAutuado());
            ps.setDate(10, new java.sql.Date(autuado.getDataEmissaoBiAutuado().getTime()));
            ps.setDate(11, new java.sql.Date(autuado.getDataValidadeBiAutuado().getTime()));
            ps.setString(12, autuado.getTelefoneAutuado());
            ps.setInt(13, autuado.getProfissao().getIdProfissao());
            ps.setInt(14, autuado.getMunicipio().getIdMunicipio());
            ps.setInt(15, autuado.getIdAutuado());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Autuado autuado) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (autuado == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, autuado.getIdAutuado());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Autuado findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Autuado autuado = new Autuado();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(autuado, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return autuado;
    }

    @Override
    public List<Autuado> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Autuado> autuados = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Autuado autuado = new Autuado();
                popularComDados(autuado, rs);
                autuados.add(autuado);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao LER dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return autuados;
    }

    public List<Autuado> findByNome(String nome) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Autuado> autuados = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Autuado autuado = new Autuado();
                popularComDados(autuado, rs);
                autuados.add(autuado);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuado com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return autuados;
    }

    public List<Autuado> findByData(java.sql.Date data) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Autuado> autuados = new ArrayList<>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_DATA);
            ps.setDate(1, data);
            rs = ps.executeQuery();

            while (rs.next()) {
                Autuado sutuado = new Autuado();
                popularComDados(sutuado, rs);
                autuados.add(sutuado);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuado com a data: " + data);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return autuados;
    }

    public List<Autuado> findByBi(String numeroBI) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Autuado> autuados = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_BI);
            ps.setString(1, "%" + numeroBI + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Autuado autuado = new Autuado();
                popularComDados(autuado, rs);
                autuados.add(autuado);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuado com o número do B.I. : " + numeroBI);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return autuados;
    }
    
    private void popularComDados(Autuado autuado, ResultSet rs) {
        try {
            autuado.setIdAutuado(rs.getInt("a.id_autuado"));
            autuado.setNomeAutuado(rs.getString("a.nome_autuado"));
            autuado.setPaiAutuado(rs.getString("a.pai_autuado"));
            autuado.setMaeAutuado(rs.getString("a.mae_autuado"));
            autuado.setBiAutuado(rs.getString("a.bi_autuado"));
            autuado.setResidenciaAutuado(rs.getString("a.residencia_autuado"));
            autuado.setDataNascimentoAutuado(rs.getDate("a.data_nascimento_autuado"));
            autuado.setSexo(autuado.getSexo().getExtensao(rs.getString("a.sexo_autuado")));
            autuado.setProximidadeAutuado(rs.getString("a.proximidade_autuado"));
            autuado.setEstadoCivilAutuado(rs.getString("a.estado_civil_autuado"));
            autuado.setDataEmissaoBiAutuado(rs.getDate("a.data_emissao_bi_autuado"));
            autuado.setDataValidadeBiAutuado(rs.getDate("a.data_validade_bi_autuado"));
            autuado.setTelefoneAutuado(rs.getString("a.telefone_autuado"));

            Profissao profissao = new Profissao();
            profissao.setIdProfissao(rs.getInt("p.id_profissao"));
            profissao.setNomeProfissao(rs.getString("p.nome_profissao"));
            autuado.setProfissao(profissao);

            Municipio municipio = new Municipio();
            municipio.setIdMunicipio(rs.getInt("m.id_municipio"));
            municipio.setNomeMunicipio(rs.getString("m.nome_municipio"));
            autuado.setMunicipio(municipio);
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados do autuado: " + ex.getLocalizedMessage());
        }
    }

}
