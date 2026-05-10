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
import modelo.Administrador;
import modelo.Sexo;
import util.Conexao;

/**
 *
 * @author user
 */
public class AdministradorDAO implements GenericoDAO<Administrador> {

    private static final String INSERIR
            = "INSERT INTO administrador "
            + "(nome_administrador, data_nascimento_administrador, sexo_administrador, "
            + "bi_administrador, nip_administrador, email_administrador, "
            + "telefone_administrador, palavra_passe_administrador) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String EDITAR
            = "UPDATE administrador SET "
            + "nome_administrador = ?, "
            + "data_nascimento_administrador = ?, "
            + "sexo_administrador = ?, "
            + "bi_administrador = ?, "
            + "nip_administrador = ?, "
            + "email_administrador = ?, "
            + "telefone_administrador = ?, "
            + "palavra_passe_administrador = ? "
            + "WHERE id_administrador = ?";

    private static final String ELIMINAR
            = "DELETE FROM administrador WHERE id_administrador = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT * FROM administrador WHERE id_administrador = ?";

    private static final String LISTAR_TUDO
            = "SELECT * FROM administrador ORDER BY nome_administrador";

    private static final String VERIFICAR_ACESSO
            = "SELECT * FROM administrador "
            + "WHERE nip_administrador = ? "
            + "AND palavra_passe_administrador = ?";

    @Override
    public void save(Administrador administrador) {
        if (administrador == null) {
            System.err.println("Erro ao INSERIR administrador: o objeto administrador não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            preencherPreparedStatement(ps, administrador, false);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do administrador: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(null);
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Administrador administrador) {
        if (administrador == null) {
            System.err.println("Erro ao EDITAR administrador: o objeto administrador não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(EDITAR);

            preencherPreparedStatement(ps, administrador, true);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao EDITAR dados do administrador: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(null);
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Administrador administrador) {
        if (administrador == null) {
            System.err.println("Erro ao ELIMINAR administrador: o objeto administrador não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);

            ps.setInt(1, administrador.getIdAdministrador());

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados do administrador: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(null);
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Administrador findById(Integer id) {
        if (id == null) {
            System.err.println("Erro ao BUSCAR administrador: o id não pode ser nulo.");
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
                Administrador administrador = new Administrador();
                popularComDados(administrador, rs);
                return administrador;
            }

            System.err.println("Não foi encontrado nenhum administrador com id: " + id);

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do administrador: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Administrador> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Administrador> administradores = new ArrayList<Administrador>();

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
            System.err.println("Erro ao LISTAR dados dos administradores: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return administradores;
    }

    public Administrador verificarAcesso(String nip, String palavraPasse)
            throws SQLException, ClassNotFoundException {

        if (nip == null || nip.trim().isEmpty()) {
            return null;
        }

        if (palavraPasse == null || palavraPasse.trim().isEmpty()) {
            return null;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(VERIFICAR_ACESSO);

            ps.setString(1, nip.trim());
            ps.setString(2, palavraPasse);

            rs = ps.executeQuery();

            if (rs.next()) {
                Administrador administrador = new Administrador();
                popularComDados(administrador, rs);
                return administrador;
            }

        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    private void preencherPreparedStatement(
            PreparedStatement ps,
            Administrador administrador,
            boolean editar
    ) throws SQLException {

        ps.setString(1, administrador.getNomeAdministrador());

        if (administrador.getDataNascimentoAdministrador() != null) {
            ps.setDate(2, new java.sql.Date(administrador.getDataNascimentoAdministrador().getTime())
            );
        } else {
            ps.setNull(2, Types.DATE);
        }

        if (administrador.getSexo() != null) {
            ps.setString(3, administrador.getSexo().getExtensao());
        } else {
            ps.setNull(3, Types.VARCHAR);
        }

        ps.setString(4, administrador.getBiAdministrador());
        ps.setInt(5, administrador.getNipAdministrador());
        ps.setString(6, administrador.getEmailAdministrador());
        ps.setString(7, administrador.getTelefoneAdministrador());
        ps.setString(8, administrador.getPalavraPasseAdministrador());

        if (editar) {
            ps.setInt(9, administrador.getIdAdministrador());
        }
    }

    private void popularComDados(Administrador administrador, ResultSet rs) throws SQLException {
        administrador.setIdAdministrador(rs.getInt("id_administrador"));
        administrador.setNomeAdministrador(rs.getString("nome_administrador"));
        administrador.setDataNascimentoAdministrador(rs.getDate("data_nascimento_administrador"));

        String sexoAdministrador = rs.getString("sexo_administrador");
        administrador.setSexo(Sexo.getExtensao(sexoAdministrador));

        administrador.setBiAdministrador(rs.getString("bi_administrador"));
        administrador.setNipAdministrador(rs.getInt("nip_administrador"));
        administrador.setEmailAdministrador(rs.getString("email_administrador"));
        administrador.setTelefoneAdministrador(rs.getString("telefone_administrador"));
        administrador.setPalavraPasseAdministrador(rs.getString("palavra_passe_administrador"));
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
