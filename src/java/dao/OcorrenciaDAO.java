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
import modelo.Autuante;
import modelo.Municipio;
import modelo.Ocorrencia;
import modelo.Testemunha;
import modelo.TipoOcorrencia;
import util.Conexao;

/**
 *
 * @author user
 */
public class OcorrenciaDAO implements GenericoDAO<Ocorrencia> {

    private static final String INSERIR = "INSERT INTO ocorrencia (data_ocorrencia, hora_ocorrencia, descricao_ocorrencia, rua_ocorrencia, cidade_ocorrencia, bairro_ocorrencia, proximidade_ocorrencia, id_autuado, id_autuante, id_tipo_ocorrencia, id_testemunha, id_testemunha1, id_municipio) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE ocorrencia SET data_ocorrencia = ?, hora_ocorrencia = ?, descricao_ocorrencia = ?, rua_ocorrencia = ?, cidade_ocorrencia = ?, bairro_ocorrencia  = ?, proximidade_ocorrencia = ?, id_autuado = ?, id_autuante = ?, id_tipo_ocorrencia = ?, id_testemunha = ?, id_testemunha1 = ?, id_municipio = ? WHERE id_ocorrencia = ?";
    private static final String ELIMINAR = "DELETE FROM ocorrencia WHERE id_ocorrencia = ?";
    private static final String BUSCAR_POR_CODIGO = "SELECT * FROM ocorrencia o INNER JOIN autuado a ON o.id_autuado = a.id_autuado INNER JOIN autuante at ON o.id_autuante = at.id_autuante INNER JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha INNER JOIN municipio m ON o.id_municipio = m.id_municipio WHERE o.id_ocorrencia = ?";
    private static final String LISTAR_TUDO = "SELECT * FROM ocorrencia o INNER JOIN autuado a ON o.id_autuado = a.id_autuado INNER JOIN autuante at ON o.id_autuante = at.id_autuante INNER JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha INNER JOIN municipio m ON o.id_municipio = m.id_municipio";
    private static final String LISTAR_POR_AUTUADO = "SELECT * FROM ocorrencia o INNER JOIN autuado a ON o.id_autuado = a.id_autuado INNER JOIN autuante at ON o.id_autuante = at.id_autuante INNER JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha INNER JOIN municipio m ON o.id_municipio = m.id_municipio WHERE a.nome_autuado LIKE ? ORDER BY a.nome_autuado";
    private static final String LISTAR_POR_AUTUANTE = "SELECT * FROM ocorrencia o INNER JOIN autuado a ON o.id_autuado = a.id_autuado INNER JOIN autuante at ON o.id_autuante = at.id_autuante INNER JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha INNER JOIN municipio m ON o.id_municipio = m.id_municipio WHERE at.nome_autuante LIKE ? ORDER BY at.nome_autuante";
    private static final String LISTAR_POR_CIDADE = "SELECT * FROM ocorrencia o INNER JOIN autuado a ON o.id_autuado = a.id_autuado INNER JOIN autuante at ON o.id_autuante = at.id_autuante INNER JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha INNER JOIN municipio m ON o.id_municipio = m.id_municipio WHERE o.cidade_ocorrencia LIKE ?";
    private static final String LISTAR_POR_DATA = "SELECT * FROM ocorrencia o INNER JOIN autuado a ON o.id_autuado = a.id_autuado INNER JOIN autuante at ON o.id_autuante = at.id_autuante INNER JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha INNER JOIN municipio m ON o.id_municipio = m.id_municipio WHERE o.data_ocorrencia LIKE ?";
    private static final String LISTAR_POR_TESTEMUNHA = "SELECT * FROM ocorrencia o INNER JOIN autuado a ON o.id_autuado = a.id_autuado INNER JOIN autuante at ON o.id_autuante = at.id_autuante INNER JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha INNER JOIN municipio m ON o.id_municipio = m.id_municipio WHERE tt.nome_testemunha LIKE ? ORDER BY tt.nome_testemunha";
    private static final String LISTAR_POR_TIPO = "SELECT * FROM ocorrencia o INNER JOIN autuado a ON o.id_autuado = a.id_autuado INNER JOIN autuante at ON o.id_autuante = at.id_autuante INNER JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia INNER JOIN testemunha tt ON o.id_testemunha = tt.id_testemunha INNER JOIN municipio m ON o.id_municipio = m.id_municipio  WHERE t.nome_tipo_ocorrencia LIKE ? ORDER BY t.nome_tipo_ocorrencia";

    @Override
    public void save(Ocorrencia ocorrencia) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (ocorrencia == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            ps.setDate(1, new java.sql.Date(ocorrencia.getDataOcorrencia().getTime()));
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
            ps.setInt(12, ocorrencia.getTestemunha1().getIdTestemunha());
            ps.setInt(13, ocorrencia.getMunicipio().getIdMunicipio());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados da ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Ocorrencia ocorrencia) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (ocorrencia == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            ps.setDate(1, new java.sql.Date(ocorrencia.getDataOcorrencia().getTime()));
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
            ps.setInt(12, ocorrencia.getTestemunha1().getIdTestemunha());
            ps.setInt(13, ocorrencia.getMunicipio().getIdMunicipio());
            ps.setInt(14, ocorrencia.getIdOcorrencia());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados da ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Ocorrencia ocorrencia) {
        PreparedStatement ps = null;
        Connection conn = null;

        if (ocorrencia == null) {
            System.err.println("O valor passado não pode ser nulo.");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, ocorrencia.getIdOcorrencia());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ELIMINAR dados da ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public Ocorrencia findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Ocorrencia ocorrencia = new Ocorrencia();

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registro com id: " + id);
            }
            popularComDados(ocorrencia, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados da ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
        return ocorrencia;
    }

    @Override
    public List<Ocorrencia> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Ocorrencia> ocorrencias = new ArrayList<>();
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
            System.err.println("Erro ao LER dados da ocorrencia: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return ocorrencias;
    }

    public List<Ocorrencia> findByAutuado(String autuado) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_AUTUADO);
            ps.setString(1, "%" + autuado + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuado com autuado: " + autuado);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return ocorrencias;
    }

    public List<Ocorrencia> findByAutuante(String autuante) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_AUTUANTE);
            ps.setString(1, "%" + autuante + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuado com autuante: " + autuante);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return ocorrencias;
    }

    public List<Ocorrencia> findByCidade(String cidade) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_CIDADE);
            ps.setString(1, "%" + cidade + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum autuado com cidade: " + cidade);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return ocorrencias;
    }

    public List<Ocorrencia> findByData(java.sql.Date data) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Ocorrencia> ocorrencias = new ArrayList<>();

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

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhuma ocorrência com a data: " + data);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return ocorrencias;
    }

    public List<Ocorrencia> findByTestemunha(String testemunha) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_TESTEMUNHA);
            ps.setString(1, "%" + testemunha + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhuma ocorrência com o testemunha: " + testemunha);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return ocorrencias;
    }

    public List<Ocorrencia> findByTipo(String tipo) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        try {
            conn = Conexao.getConnection();

            ps = conn.prepareStatement(LISTAR_POR_TIPO);
            ps.setString(1, "%" + tipo + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Ocorrencia ocorrencia = new Ocorrencia();
                popularComDados(ocorrencia, rs);
                ocorrencias.add(ocorrencia);
            }

            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhuma ocorrência com o tipo: " + tipo);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados do ocorrência: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return ocorrencias;
    }

    private void popularComDados(Ocorrencia ocorrencia, ResultSet rs) {
        try {
            ocorrencia.setIdOcorrencia(rs.getInt("o.id_ocorrencia"));
            ocorrencia.setDataOcorrencia(rs.getDate("o.data_ocorrencia"));
            ocorrencia.setHoraOcorrencia(rs.getString("o.hora_ocorrencia"));
            ocorrencia.setDescricaoOcorrencia(rs.getString("o.descricao_ocorrencia"));
            ocorrencia.setRuaOcorrencia(rs.getString("o.rua_ocorrencia"));
            ocorrencia.setCidadeOcorrencia(rs.getString("o.cidade_ocorrencia"));
            ocorrencia.setBairroOcorrencia(rs.getString("o.bairro_ocorrencia"));
            ocorrencia.setProximidadeOcorrencia(rs.getString("o.proximidade_ocorrencia"));

            Autuado autuado = new Autuado();
            autuado.setIdAutuado(rs.getInt("a.id_autuado"));
            autuado.setNomeAutuado(rs.getString("a.nome_autuado"));
            autuado.setPaiAutuado(rs.getString("a.pai_autuado"));
            autuado.setMaeAutuado(rs.getString("a.mae_autuado"));
            autuado.setEstadoCivilAutuado(rs.getString("a.estado_civil_autuado"));
            autuado.setDataNascimentoAutuado(rs.getDate("a.data_nascimento_autuado"));
            autuado.setSexo(autuado.getSexo().getExtensao(rs.getString("a.sexo_autuado")));
            autuado.setBiAutuado(rs.getString("a.bi_autuado"));
            autuado.setDataEmissaoBiAutuado(rs.getDate("a.data_emissao_bi_autuado"));
            autuado.setTelefoneAutuado(rs.getString("a.telefone_autuado"));
            ocorrencia.setAutuado(autuado);

            Autuante autuante = new Autuante();
            autuante.setIdAutuante(rs.getInt("at.id_autuante"));
            autuante.setNomeAutuante(rs.getString("at.nome_autuante"));
            autuante.setPaiAutuante(rs.getString("at.pai_autuante"));
            autuante.setMaeAutuante(rs.getString("at.mae_autuante"));
            autuante.setDataNascimentoAutuante(rs.getDate("at.data_nascimento_autuante"));
            autuante.setSexo(autuante.getSexo().getExtensao(rs.getString("at.sexo_autuante")));
            autuante.setBiAutuante(rs.getString("at.bi_autuante"));
            autuante.setDataEmissaoBiAutuante(rs.getDate("at.data_emissao_bi_autuante"));
            autuante.setNipAutuante(rs.getInt("at.nip_autuante"));
            autuante.setTelefoneAutuante(rs.getString("at.telefone_autuante"));
            ocorrencia.setAutuante(autuante);

            TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
            tipoOcorrencia.setIdTipoOcorrencia(rs.getInt("t.id_tipo_ocorrencia"));
            tipoOcorrencia.setNomeTipoOcorrencia(rs.getString("t.nome_tipo_ocorrencia"));
            ocorrencia.setTipoOcorrencia(tipoOcorrencia);

            Testemunha testemunha = new Testemunha();
            testemunha.setIdTestemunha(rs.getInt("tt.id_testemunha"));
            testemunha.setNomeTestemunha(rs.getString("tt.nome_testemunha"));
            testemunha.setDataNascimentoTestemunha(rs.getDate("tt.data_nascimento_testemunha"));
            testemunha.setSexo(testemunha.getSexo().getExtensao(rs.getString("tt.sexo_testemunha")));
            testemunha.setBiTestemunha(rs.getString("tt.bi_testemunha"));
            testemunha.setTelefoneTestemunha(rs.getString("tt.telefone_testemunha"));
            ocorrencia.setTestemunha(testemunha);

            Municipio municipio = new Municipio();
            municipio.setIdMunicipio(rs.getInt("m.id_municipio"));
            municipio.setNomeMunicipio(rs.getString("m.nome_municipio"));
            ocorrencia.setMunicipio(municipio);
        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados da ocorrencia: " + ex.getLocalizedMessage());
        }
    }

}
