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
            = "SELECT * FROM tipo_ocorrencia WHERE nome_tipo_ocorrencia LIKE ? ORDER BY nome_tipo_ocorrencia";

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

            System.err.println("Não foi encontrado nenhum tipo de ocorrência com id: " + id);

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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoOcorrencia> tipoOcorrencias = new ArrayList<TipoOcorrencia>();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);

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

    public List<TipoOcorrencia> findByNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TipoOcorrencia> tipoOcorrencias = new ArrayList<TipoOcorrencia>();

        if (nome == null) {
            nome = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                popularComDados(tipoOcorrencia, rs);
                tipoOcorrencias.add(tipoOcorrencia);
            }

            if (tipoOcorrencias.isEmpty()) {
                System.err.println("Não foi encontrado nenhum tipo de ocorrência com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do tipo de ocorrência por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return tipoOcorrencias;
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
