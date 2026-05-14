package controlo;

import dao.AutuadoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Autuado;
import modelo.DateUtil;
import modelo.Municipio;
import modelo.Profissao;
import modelo.Sexo;

@WebServlet(name = "AutuadoServlet", urlPatterns = {"/autuadoServlet"})
public class AutuadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String comando = request.getParameter("comando");

        if (comando == null) {
            comando = "principal";
        }

        AutuadoDAO autuadoDAO = new AutuadoDAO();

        if (comando.equalsIgnoreCase("pesquisar_ajax")) {
            pesquisarAjax(request, response, autuadoDAO);
            return;
        }

        Autuado autuado = new Autuado();

        if (!comando.equalsIgnoreCase("principal")) {
            try {
                String idAutuado = request.getParameter("id_autuado");

                if (idAutuado != null && !idAutuado.trim().isEmpty()) {
                    autuado.setIdAutuado(Integer.parseInt(idAutuado));
                }

            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter id do autuado: " + ex.getMessage());
            }
        }

        try {
            if (comando.equalsIgnoreCase("guardar")) {
                preencherAutuadoComParametros(request, autuado);

                autuadoDAO.save(autuado);

                request.setAttribute("message", "Autuado cadastrado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuado/autuado_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                autuado.setIdAutuado(Integer.parseInt(request.getParameter("id_autuado")));
                preencherAutuadoComParametros(request, autuado);

                autuadoDAO.update(autuado);

                request.setAttribute("message", "Autuado editado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuado/autuado_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                autuadoDAO.delete(autuado);

                request.setAttribute("message", "Autuado eliminado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuado/autuado_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                autuado = autuadoDAO.findById(autuado.getIdAutuado());

                request.setAttribute("autuado", autuado);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/autuado/autuado_editar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/autuado/autuado_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                autuado = autuadoDAO.findById(autuado.getIdAutuado());

                request.setAttribute("autuado", autuado);
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuado/autuado_detalhes.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("principal")) {
                response.sendRedirect("paginas/index.jsp");
            }

        } catch (IOException | ServletException ex) {
            System.err.println("Erro na leitura dos dados: " + ex.getMessage());
        }
    }

    private void preencherAutuadoComParametros(HttpServletRequest request, Autuado autuado)
            throws ParseException {

        autuado.setNomeAutuado(request.getParameter("nome_autuado"));
        autuado.setPaiAutuado(request.getParameter("pai_autuado"));
        autuado.setMaeAutuado(request.getParameter("mae_autuado"));
        autuado.setBiAutuado(request.getParameter("bi_autuado"));
        autuado.setResidenciaAutuado(request.getParameter("residencia_autuado"));
        autuado.setDataNascimentoAutuado(DateUtil.strToDate(request.getParameter("data_nascimento_autuado")));
        autuado.setSexo(Sexo.getExtensao(request.getParameter("sexo_autuado")));
        autuado.setProximidadeAutuado(request.getParameter("proximidade_autuado"));
        autuado.setEstadoCivilAutuado(request.getParameter("estado_civil_autuado"));
        autuado.setDataEmissaoBiAutuado(DateUtil.strToDate(request.getParameter("data_emissao_bi_autuado")));
        autuado.setDataValidadeBiAutuado(DateUtil.strToDate(request.getParameter("data_validade_bi_autuado")));
        autuado.setTelefoneAutuado(request.getParameter("telefone_autuado"));

        Profissao profissao = new Profissao();
        profissao.setIdProfissao(Integer.parseInt(request.getParameter("select_profissao_autuado")));
        autuado.setProfissao(profissao);

        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio_autuado")));
        autuado.setMunicipio(municipio);
    }

    private void pesquisarAjax(
            HttpServletRequest request,
            HttpServletResponse response,
            AutuadoDAO autuadoDAO
    ) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        String tipoPesquisa = request.getParameter("tipo_pesquisa");
        String termo = request.getParameter("termo");
        String pagina = request.getParameter("pagina");

        if (tipoPesquisa == null || tipoPesquisa.trim().isEmpty()) {
            tipoPesquisa = "nome";
        }

        if (termo == null) {
            termo = "";
        }

        if (pagina == null || pagina.trim().isEmpty()) {
            pagina = "1";
        }

        List<Autuado> autuados;

        if (tipoPesquisa.equalsIgnoreCase("bi")) {
            autuados = autuadoDAO.consultarPaginaPorBi(termo, pagina);

        } else if (tipoPesquisa.equalsIgnoreCase("data")) {
            try {
                if (termo.trim().isEmpty()) {
                    autuados = autuadoDAO.consultarPagina(pagina);
                } else {
                    java.sql.Date data = DateUtil.strToDate(termo);
                    autuados = autuadoDAO.consultarPaginaPorData(data, pagina);
                }
            } catch (Exception ex) {
                autuados = new java.util.ArrayList<Autuado>();
            }

        } else {
            autuados = autuadoDAO.consultarPaginaPorNome(termo, pagina);
        }

        PrintWriter out = response.getWriter();

        if (autuados == null || autuados.isEmpty()) {
            out.println("<tr>");
            out.println("<td colspan='12' class='text-center text-muted'>Nenhum autuado encontrado.</td>");
            out.println("</tr>");
            return;
        }

        for (Autuado autuado : autuados) {
            out.println("<tr>");

            out.println("<td>" + autuado.getIdAutuado() + "</td>");
            out.println("<td>" + valorSeguro(autuado.getNomeAutuado()) + "</td>");
            out.println("<td>" + valorSeguro(autuado.getBiAutuado()) + "</td>");
            out.println("<td>" + valorSeguro(autuado.getResidenciaAutuado()) + "</td>");
            out.println("<td>" + DateUtil.formataData(autuado.getDataNascimentoAutuado()) + "</td>");

            if (autuado.getSexo() != null) {
                out.println("<td>" + autuado.getSexo().getExtensao() + "</td>");
            } else {
                out.println("<td></td>");
            }

            out.println("<td>" + valorSeguro(autuado.getTelefoneAutuado()) + "</td>");

            if (autuado.getProfissao() != null) {
                out.println("<td>" + valorSeguro(autuado.getProfissao().getNomeProfissao()) + "</td>");
            } else {
                out.println("<td></td>");
            }

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/autuadoPorId?id_autuado=" + autuado.getIdAutuado() + "'>");
            out.println("<span class='glyphicon glyphicon-print'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/autuadoServlet?comando=detalhes&id_autuado=" + autuado.getIdAutuado() + "'>");
            out.println("<span class='glyphicon glyphicon-zoom-in'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/autuadoServlet?comando=prepara_editar&id_autuado=" + autuado.getIdAutuado() + "'>");
            out.println("<span class='glyphicon glyphicon-edit'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a onclick=\"return confirm('Deseja realmente eliminar este autuado?');\" href='" + request.getContextPath() + "/autuadoServlet?comando=eliminar&id_autuado=" + autuado.getIdAutuado() + "'>");
            out.println("<span class='glyphicon glyphicon-trash'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("</tr>");
        }
    }

    private String valorSeguro(String valor) {
        if (valor == null) {
            return "";
        }

        return valor;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AutuadoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AutuadoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Autuado Servlet";
    }
}
