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
import modelo.Autuado;
import modelo.Municipio;
import modelo.Profissao;
import modelo.Sexo;
import util.Conexao;

/**
 *
 * @author user
 */
public class AutuadoDAO implements GenericoDAO<Autuado> {

    private static final String CAMPOS_CONSULTA
            = "a.id_autuado, "
            + "a.nome_autuado, "
            + "a.pai_autuado, "
            + "a.mae_autuado, "
            + "a.bi_autuado, "
            + "a.residencia_autuado, "
            + "a.data_nascimento_autuado, "
            + "a.sexo_autuado, "
            + "a.proximidade_autuado, "
            + "a.estado_civil_autuado, "
            + "a.data_emissao_bi_autuado, "
            + "a.data_validade_bi_autuado, "
            + "a.telefone_autuado, "
            + "p.id_profissao, "
            + "p.nome_profissao, "
            + "m.id_municipio, "
            + "m.nome_municipio";

    private static final String INSERIR
            = "INSERT INTO autuado "
            + "(nome_autuado, pai_autuado, mae_autuado, bi_autuado, residencia_autuado, "
            + "data_nascimento_autuado, sexo_autuado, proximidade_autuado, estado_civil_autuado, "
            + "data_emissao_bi_autuado, data_validade_bi_autuado, telefone_autuado, "
            + "id_profissao, id_municipio) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String ACTUALIZAR
            = "UPDATE autuado SET "
            + "nome_autuado = ?, "
            + "pai_autuado = ?, "
            + "mae_autuado = ?, "
            + "bi_autuado = ?, "
            + "residencia_autuado = ?, "
            + "data_nascimento_autuado = ?, "
            + "sexo_autuado = ?, "
            + "proximidade_autuado = ?, "
            + "estado_civil_autuado = ?, "
            + "data_emissao_bi_autuado = ?, "
            + "data_validade_bi_autuado = ?, "
            + "telefone_autuado = ?, "
            + "id_profissao = ?, "
            + "id_municipio = ? "
            + "WHERE id_autuado = ?";

    private static final String ELIMINAR
            = "DELETE FROM autuado WHERE id_autuado = ?";

    private static final String BUSCAR_POR_CODIGO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuado a "
            + "INNER JOIN profissao p ON a.id_profissao = p.id_profissao "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "WHERE a.id_autuado = ?";

    private static final String LISTAR_TUDO
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuado a "
            + "INNER JOIN profissao p ON a.id_profissao = p.id_profissao "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "ORDER BY a.nome_autuado";

    private static final String LISTAR_POR_NOME
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuado a "
            + "INNER JOIN profissao p ON a.id_profissao = p.id_profissao "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "WHERE a.nome_autuado LIKE ? "
            + "ORDER BY a.nome_autuado";

    private static final String LISTAR_POR_DATA
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuado a "
            + "INNER JOIN profissao p ON a.id_profissao = p.id_profissao "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "WHERE a.data_nascimento_autuado = ? "
            + "ORDER BY a.nome_autuado";

    private static final String LISTAR_POR_BI
            = "SELECT " + CAMPOS_CONSULTA + " "
            + "FROM autuado a "
            + "INNER JOIN profissao p ON a.id_profissao = p.id_profissao "
            + "INNER JOIN municipio m ON a.id_municipio = m.id_municipio "
            + "WHERE a.bi_autuado LIKE ? "
            + "ORDER BY a.nome_autuado";

    @Override
    public void save(Autuado autuado) {
        if (autuado == null) {
            System.err.println("Erro ao INSERIR autuado: o objeto autuado não pode ser nulo.");
            return;
        }

        if (autuado.getProfissao() == null) {
            System.err.println("Erro ao INSERIR autuado: a profissão não pode ser nula.");
            return;
        }

        if (autuado.getMunicipio() == null) {
            System.err.println("Erro ao INSERIR autuado: o município não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            preencherPreparedStatement(ps, autuado, false);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao INSERIR dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void update(Autuado autuado) {
        if (autuado == null) {
            System.err.println("Erro ao ACTUALIZAR autuado: o objeto autuado não pode ser nulo.");
            return;
        }

        if (autuado.getProfissao() == null) {
            System.err.println("Erro ao ACTUALIZAR autuado: a profissão não pode ser nula.");
            return;
        }

        if (autuado.getMunicipio() == null) {
            System.err.println("Erro ao ACTUALIZAR autuado: o município não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            preencherPreparedStatement(ps, autuado, true);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro ao ACTUALIZAR dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public void delete(Autuado autuado) {
        if (autuado == null) {
            System.err.println("Erro ao ELIMINAR autuado: o objeto autuado não pode ser nulo.");
            return;
        }

        Connection conn = null;
        PreparedStatement ps = null;

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
        if (id == null) {
            System.err.println("Erro ao BUSCAR autuado: o id não pode ser nulo.");
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
                Autuado autuado = new Autuado();
                popularComDados(autuado, rs);
                return autuado;
            }

            System.err.println("Não foi encontrado nenhum autuado com id: " + id);

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuado: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return null;
    }

    @Override
    public List<Autuado> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuado> autuados = new ArrayList<Autuado>();

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
            System.err.println("Erro ao LISTAR dados dos autuados: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuados;
    }

    public List<Autuado> findByNome(String nome) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuado> autuados = new ArrayList<Autuado>();

        if (nome == null) {
            nome = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_NOME);

            ps.setString(1, "%" + nome.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuado autuado = new Autuado();
                popularComDados(autuado, rs);
                autuados.add(autuado);
            }

            if (autuados.isEmpty()) {
                System.err.println("Não foi encontrado nenhum autuado com nome: " + nome);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuado por nome: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuados;
    }

    public List<Autuado> findByData(java.sql.Date data) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuado> autuados = new ArrayList<Autuado>();

        if (data == null) {
            System.err.println("Erro ao BUSCAR autuado: a data não pode ser nula.");
            return autuados;
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_DATA);

            ps.setDate(1, data);

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuado autuado = new Autuado();
                popularComDados(autuado, rs);
                autuados.add(autuado);
            }

            if (autuados.isEmpty()) {
                System.err.println("Não foi encontrado nenhum autuado com a data: " + data);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuado por data: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuados;
    }

    public List<Autuado> findByBi(String numeroBI) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Autuado> autuados = new ArrayList<Autuado>();

        if (numeroBI == null) {
            numeroBI = "";
        }

        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_POR_BI);

            ps.setString(1, "%" + numeroBI.trim() + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Autuado autuado = new Autuado();
                popularComDados(autuado, rs);
                autuados.add(autuado);
            }

            if (autuados.isEmpty()) {
                System.err.println("Não foi encontrado nenhum autuado com o número do B.I.: " + numeroBI);
            }

        } catch (SQLException ex) {
            System.err.println("Erro ao BUSCAR dados do autuado por B.I.: " + ex.getLocalizedMessage());
        } finally {
            fecharResultSet(rs);
            Conexao.closeConnection(conn, ps);
        }

        return autuados;
    }

    private void preencherPreparedStatement(
            PreparedStatement ps,
            Autuado autuado,
            boolean actualizar
    ) throws SQLException {

        ps.setString(1, autuado.getNomeAutuado());
        ps.setString(2, autuado.getPaiAutuado());
        ps.setString(3, autuado.getMaeAutuado());
        ps.setString(4, autuado.getBiAutuado());
        ps.setString(5, autuado.getResidenciaAutuado());

        setDate(ps, 6, autuado.getDataNascimentoAutuado());

        if (autuado.getSexo() != null) {
            ps.setString(7, autuado.getSexo().getExtensao());
        } else {
            ps.setNull(7, Types.VARCHAR);
        }

        ps.setString(8, autuado.getProximidadeAutuado());
        ps.setString(9, autuado.getEstadoCivilAutuado());

        setDate(ps, 10, autuado.getDataEmissaoBiAutuado());
        setDate(ps, 11, autuado.getDataValidadeBiAutuado());

        ps.setString(12, autuado.getTelefoneAutuado());
        ps.setInt(13, autuado.getProfissao().getIdProfissao());
        ps.setInt(14, autuado.getMunicipio().getIdMunicipio());

        if (actualizar) {
            ps.setInt(15, autuado.getIdAutuado());
        }
    }

    private void setDate(PreparedStatement ps, int index, java.util.Date data) throws SQLException {
        if (data != null) {
            ps.setDate(index, new java.sql.Date(data.getTime()));
        } else {
            ps.setNull(index, Types.DATE);
        }
    }

    private void popularComDados(Autuado autuado, ResultSet rs) throws SQLException {
        autuado.setIdAutuado(rs.getInt("id_autuado"));
        autuado.setNomeAutuado(rs.getString("nome_autuado"));
        autuado.setPaiAutuado(rs.getString("pai_autuado"));
        autuado.setMaeAutuado(rs.getString("mae_autuado"));
        autuado.setBiAutuado(rs.getString("bi_autuado"));
        autuado.setResidenciaAutuado(rs.getString("residencia_autuado"));
        autuado.setDataNascimentoAutuado(rs.getDate("data_nascimento_autuado"));

        String sexoAutuado = rs.getString("sexo_autuado");
        autuado.setSexo(Sexo.getExtensao(sexoAutuado));

        autuado.setProximidadeAutuado(rs.getString("proximidade_autuado"));
        autuado.setEstadoCivilAutuado(rs.getString("estado_civil_autuado"));
        autuado.setDataEmissaoBiAutuado(rs.getDate("data_emissao_bi_autuado"));
        autuado.setDataValidadeBiAutuado(rs.getDate("data_validade_bi_autuado"));
        autuado.setTelefoneAutuado(rs.getString("telefone_autuado"));

        Profissao profissao = new Profissao();
        profissao.setIdProfissao(rs.getInt("id_profissao"));
        profissao.setNomeProfissao(rs.getString("nome_profissao"));
        autuado.setProfissao(profissao);

        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(rs.getInt("id_municipio"));
        municipio.setNomeMunicipio(rs.getString("nome_municipio"));
        autuado.setMunicipio(municipio);
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
