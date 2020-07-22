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
import modelo.Administrador;
import util.Conexao;

/**
 *
 * @author user
 */
public class AdministradorDAO implements GenericoDAO<Administrador> {

    private static final String INSERIR = "INSERT INTO administrador(nome_administrador, data_nascimento_administrador, sexo_administrador, bi_administrador, nip_administrador, email_administrador, telefone_administrador, palavra_passe_administrador) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String EDITAR = "UPDATE administrador SET nome_administrador = ?, data_nascimento_administrador = ?, sexo_administrador = ?, bi_administrador = ?, nip_administrador = ?, email_administrador = ?, telefone_administrador = ?, palavra_passe_administrador = ? WHERE id_administrador = ?";
    private static final String ELIMINAR = "DELETE FROM administrador WHERE id_administrador = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM administrador WHERE id_administrador = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM administrador";
    private static final String VERIFICAR_ACESSO = "SELECT * FROM administrador WHERE nip_administrador = ? and palavra_passe_administrador = ?";

    @Override
    public void save(Administrador administrador) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (administrador == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, administrador.getNomeAdministrador());
            ps.setDate(2, new java.sql.Date(administrador.getDataNascimentoAdministrador().getTime()));
            ps.setString(3, administrador.getSexo().getExtensao());
            ps.setString(4, administrador.getBiAdministrador());
            ps.setInt(5, administrador.getNipAdministrador());
            ps.setString(6, administrador.getEmailAdministrador());
            ps.setString(7, administrador.getTelefoneAdministrador());
            ps.setString(8, administrador.getPalavraPasseAdministrador());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do administrador: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Administrador administrador) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (administrador == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(EDITAR);
            ps.setString(1, administrador.getNomeAdministrador());
            ps.setDate(2, new java.sql.Date(administrador.getDataNascimentoAdministrador().getTime()));
            ps.setString(3, administrador.getSexo().getExtensao());
            ps.setString(4, administrador.getBiAdministrador());
            ps.setInt(5, administrador.getNipAdministrador());
            ps.setString(6, administrador.getEmailAdministrador());
            ps.setString(7, administrador.getTelefoneAdministrador());
            ps.setString(8, administrador.getPalavraPasseAdministrador());
            ps.setInt(9, administrador.getIdAdministrador());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao EDITAR dados do administrador: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Administrador administrador) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (administrador == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, administrador.getIdAdministrador());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados do administrador: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Administrador findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Administrador administrador = new Administrador();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(administrador, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do login: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return administrador;
    }

    @Override
    public List<Administrador> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Administrador> administradores = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Administrador administrador = new Administrador();
                popularComDados(administrador, rs);
                administradores.add(administrador);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao LER dados do administrador: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return administradores;
    }

    public Administrador verificarAcesso(String nip, String palavraPasse) throws SQLException,
            ClassNotFoundException {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;

        Conexao conexao = new Conexao();
        conn = Conexao.getConnection();

        ps = conn.prepareStatement(VERIFICAR_ACESSO);

        ps.setString(1, nip);
        ps.setString(2, palavraPasse);
        rs = ps.executeQuery();

        Administrador administrador = null;
        if (rs.next()) {
            administrador = new Administrador();
            popularComDados(administrador, rs);
        }

        conexao.closeConnection(conn);

        return administrador;
    }

    private void popularComDados(Administrador administrador, ResultSet rs) {
        try {
            administrador.setIdAdministrador(rs.getInt("id_administrador"));
            administrador.setNomeAdministrador(rs.getString("nome_administrador"));
            administrador.setDataNascimentoAdministrador(rs.getDate("data_nascimento_administrador"));
            administrador.setSexo(administrador.getSexo().getExtensao(rs.getString("sexo_administrador")));
            administrador.setBiAdministrador(rs.getString("bi_administrador"));
            administrador.setNipAdministrador(rs.getInt("nip_administrador"));
            administrador.setEmailAdministrador(rs.getString("email_administrador"));
            administrador.setTelefoneAdministrador(rs.getString("telefone_administrador"));
            administrador.setPalavraPasseAdministrador(rs.getString("palavra_passe_administrador"));
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados do administrador: " + ex.getLocalizedMessage());
        }
    }

}
