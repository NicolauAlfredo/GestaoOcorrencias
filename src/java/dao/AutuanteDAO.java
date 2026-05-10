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

    private static final String INSERIR = "INSERT INTO autuante (nome_autuante, pai_autuante, mae_autuante, bi_autuante, residencia_autuante, data_nascimento_autuante, sexo_autuante, altura_autuante, data_emissao_bi_autuante, data_validade_bi_autuante, nip_autuante, telefone_autuante, id_patente, id_municipio, id_posto_trabalho) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE autuante SET nome_autuante = ?, pai_autuante = ?, mae_autuante = ?, bi_autuante = ?, residencia_autuante = ?, data_nascimento_autuante = ?, sexo_autuante = ?, altura_autuante = ?, data_emissao_bi_autuante = ?, data_validade_bi_autuante = ?, nip_autuante = ?, telefone_autuante = ?, id_patente = ?, id_municipio = ?, id_posto_trabalho = ? WHERE id_autuante = ?";
    private static final String ELIMINAR = "DELETE FROM autuante WHERE id_autuante = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM autuante a INNER JOIN patente p ON a.id_patente = p.id_patente INNER JOIN municipio m ON a.id_municipio = m.id_municipio INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho WHERE a.id_autuante = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM autuante a INNER JOIN patente p ON a.id_patente = p.id_patente INNER JOIN municipio m ON a.id_municipio = m.id_municipio INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho";
    private static final String LISTAR_POR_NOME = "SELECT * FROM autuante a INNER JOIN patente p ON a.id_patente = p.id_patente INNER JOIN municipio m ON a.id_municipio = m.id_municipio INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho WHERE a.nome_autuante LIKE ? ORDER BY a.nome_autuante";
    private static final String LISTAR_POR_DATA = "SELECT * FROM autuante a INNER JOIN patente p ON a.id_patente = p.id_patente INNER JOIN municipio m ON a.id_municipio = m.id_municipio INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho WHERE a.data_nascimento_autuante LIKE ? ORDER BY a.nome_autuante";
    private static final String LISTAR_POR_BI = "SELECT * FROM autuante a INNER JOIN patente p ON a.id_patente = p.id_patente INNER JOIN municipio m ON a.id_municipio = m.id_municipio INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho WHERE a.bi_autuante LIKE ? ORDER BY a.nome_autuante";
    private static final String LISTAR_POR_NIP = "SELECT * FROM autuante a INNER JOIN patente p ON a.id_patente = p.id_patente INNER JOIN municipio m ON a.id_municipio = m.id_municipio INNER JOIN posto_trabalho pt ON a.id_posto_trabalho = pt.id_posto_trabalho WHERE a.nip_autuante = ? ORDER BY a.nome_autuante";

    @Override
    public void save(Autuante autuante) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (autuante == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, autuante.getNomeAutuante());
            ps.setString(2, autuante.getPaiAutuante());
            ps.setString(3, autuante.getMaeAutuante());
            ps.setString(4, autuante.getBiAutuante());
            ps.setString(5, autuante.getResidenciaAutuante());
            if (autuante.getDataNascimentoAutuante() != null) {
                ps.setDate(6, new java.sql.Date(autuante.getDataNascimentoAutuante().getTime()));
            } else {
                ps.setDate(6, null);
            }
            ps.setString(7, autuante.getSexo().getExtensao()); // Fixar bem isso
            ps.setDouble(8, autuante.getAlturaAutuante());
            if (autuante.getDataValidadeBiAutuante() != null) {
                ps.setDate(9, new java.sql.Date(autuante.getDataEmissaoBiAutuante().getTime()));
            } else {
                ps.setDate(9, null);
            }
            if (autuante.getDataValidadeBiAutuante() != null) {
                ps.setDate(10, new java.sql.Date(autuante.getDataValidadeBiAutuante().getTime()));
            } else {
                ps.setDate(10, null);
            }
            ps.setInt(11, autuante.getNipAutuante());
            ps.setString(12, autuante.getTelefoneAutuante());
            if (autuante.getPatente() != null) {
                ps.setInt(13, autuante.getPatente().getIdPatente());
            } else {
                ps.setNull(13, java.sql.Types.INTEGER);
            }
            ps.setInt(14, autuante.getMunicipio().getIdMunicipio());
            ps.setInt(15, autuante.getPostoTrabalho().getIdPostoTrabalho());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do autuante: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Autuante autuante) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (autuante == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, autuante.getNomeAutuante());
            ps.setString(2, autuante.getPaiAutuante());
            ps.setString(3, autuante.getMaeAutuante());
            ps.setString(4, autuante.getBiAutuante());
            ps.setString(5, autuante.getResidenciaAutuante());
            ps.setDate(6, new java.sql.Date(autuante.getDataNascimentoAutuante().getTime()));
            ps.setString(7, autuante.getSexo().getExtensao()); // Fixar bm isso
            ps.setDouble(8, autuante.getAlturaAutuante());
            ps.setDate(9, new java.sql.Date(autuante.getDataEmissaoBiAutuante().getTime()));
            ps.setDate(10, new java.sql.Date(autuante.getDataValidadeBiAutuante().getTime()));
            ps.setInt(11, autuante.getNipAutuante());
            ps.setString(12, autuante.getTelefoneAutuante());
            ps.setInt(13, autuante.getPatente().getIdPatente());
            ps.setInt(14, autuante.getMunicipio().getIdMunicipio());
            ps.setInt(15, autuante.getPostoTrabalho().getIdPostoTrabalho());
            ps.setInt(16, autuante.getIdAutuante());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados do autuante: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Autuante autuante) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (autuante == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
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
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Autuante autuante = new Autuante();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(autuante, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuante: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return autuante;
    }

    @Override
    public List<Autuante> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Autuante> autuantes = new ArrayList<>();
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
            System.err.println("Erro ao LER dados do autuante: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return autuantes;
    }

    public List<Autuante> findByNome(String nome) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Autuante> autuantes = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuado com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return autuantes;
    }

    public List<Autuante> findByData(java.sql.Date data) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Autuante> autuantes = new ArrayList<>();

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

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuante com a data: " + data);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return autuantes;
    }

    public List<Autuante> findByBi(String numeroBI) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Autuante> autuantes = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_BI);
            ps.setString(1, "%" + numeroBI + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Autuante autuante = new Autuante();
                popularComDados(autuante, rs);
                autuantes.add(autuante);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuante com o número do B.I. : " + numeroBI);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do autuante: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return autuantes;
    }

    public List<Autuante> findByNip(Integer nip) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Autuante> autuantes = new ArrayList<>();

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

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuante com o nip: " + nip);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return autuantes;
    }

    private void popularComDados(Autuante autuante, ResultSet rs) {
        try {
            autuante.setIdAutuante(rs.getInt("a.id_autuante"));
            autuante.setNomeAutuante(rs.getString("a.nome_autuante"));
            autuante.setPaiAutuante(rs.getString("a.pai_autuante"));
            autuante.setMaeAutuante(rs.getString("a.mae_autuante"));
            autuante.setBiAutuante(rs.getString("a.bi_autuante"));
            autuante.setResidenciaAutuante(rs.getString("a.residencia_autuante"));
            autuante.setDataNascimentoAutuante(rs.getDate("a.data_nascimento_autuante"));
            autuante.setSexo(Sexo.getExtensao(rs.getString("a.sexo_autuante")));
            autuante.setAlturaAutuante(rs.getDouble("a.altura_autuante"));
            autuante.setDataEmissaoBiAutuante(rs.getDate("a.data_emissao_bi_autuante"));
            autuante.setDataValidadeBiAutuante(rs.getDate("a.data_validade_bi_autuante"));
            autuante.setNipAutuante(rs.getInt("a.nip_autuante"));
            autuante.setTelefoneAutuante(rs.getString("a.telefone_autuante"));

            Patente patente = new Patente();
            patente.setIdPatente(rs.getInt("p.id_patente"));
            patente.setNomePatente(rs.getString("p.nome_patente"));
            autuante.setPatente(patente);

            Municipio municipio = new Municipio();
            municipio.setIdMunicipio(rs.getInt("m.id_municipio"));
            municipio.setNomeMunicipio(rs.getString("m.nome_municipio"));
            autuante.setMunicipio(municipio);

            PostoTrabalho postoTrabalho = new PostoTrabalho();
            postoTrabalho.setIdPostoTrabalho(rs.getInt("pt.id_posto_trabalho"));
            postoTrabalho.setNomePostoTrabalho(rs.getString("pt.nome_posto_trabalho"));
            postoTrabalho.setNumeroPostoTrabalho(rs.getInt("pt.numero_posto_trabalho"));
            autuante.setPostoTrabalho(postoTrabalho);
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados do autuante: " + ex.getLocalizedMessage());
        }
    }

}
