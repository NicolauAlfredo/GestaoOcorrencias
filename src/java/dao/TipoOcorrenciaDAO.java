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

    private static final String INSERIR = "INSERT INTO tipo_ocorrencia (nome_tipo_ocorrencia) VALUES(?)";
    private static final String ACTUALIZAR = "UPDATE tipo_ocorrencia SET nome_tipo_ocorrencia = ? WHERE id_tipo_ocorrencia = ?";
    private static final String ELIMINAR = "DELETE FROM tipo_ocorrencia WHERE id_tipo_ocorrencia = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM tipo_ocorrencia WHERE id_tipo_ocorrencia = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM tipo_ocorrencia";
    private static final String LISTAR_POR_NOME = "SELECT * FROM tipo_ocorrencia WHERE nome_tipo_ocorrencia LIKE ? ORDER BY nome_tipo_ocorrencia";

    @Override
    public void save(TipoOcorrencia tipoOcorrencia) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (tipoOcorrencia == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setString(1, tipoOcorrencia.getNomeTipoOcorrencia());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do tipo de ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(TipoOcorrencia tipoOcorrencia) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (tipoOcorrencia == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setString(1, tipoOcorrencia.getNomeTipoOcorrencia());
            ps.setInt(2, tipoOcorrencia.getIdTipoOcorrencia());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados do tipo de ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(TipoOcorrencia tipoOcorrencia) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (tipoOcorrencia == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, tipoOcorrencia.getIdTipoOcorrencia());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados do tipo de ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public TipoOcorrencia findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(tipoOcorrencia, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do tipo de ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return tipoOcorrencia;
    }

    @Override
    public List<TipoOcorrencia> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<TipoOcorrencia> tipoOcorrencias = new ArrayList<>();
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
            System.err.println("Erro ao LER dados do tipo de ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return tipoOcorrencias;
    }

    public List<TipoOcorrencia> findByNome(String nome) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<TipoOcorrencia> tipoOcorrencias = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_NOME);
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                popularComDados(tipoOcorrencia, rs);
                tipoOcorrencias.add(tipoOcorrencia);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum tipo de ocorrência com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do tipo de ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return tipoOcorrencias;
    }

    private void popularComDados(TipoOcorrencia tipoOcorrencia, ResultSet rs) {
        try {
            tipoOcorrencia.setIdTipoOcorrencia(rs.getInt("id_tipo_ocorrencia"));
            tipoOcorrencia.setNomeTipoOcorrencia(rs.getString("nome_tipo_ocorrencia"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados do tipo de ocorrencia: " + ex.getLocalizedMessage());
        }
    }
}
