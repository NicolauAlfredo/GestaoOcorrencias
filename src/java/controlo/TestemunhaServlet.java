/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.TestemunhaDAO;
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
import modelo.DateUtil;
import modelo.Municipio;
import modelo.Profissao;
import modelo.Sexo;
import modelo.Testemunha;

/**
 *
 * @author user
 */
@WebServlet(name = "TestemunhaServlet", urlPatterns = {"/testemunhaServlet"})
public class TestemunhaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        TestemunhaDAO testemunhaDAO = new TestemunhaDAO();

        if (comando.equalsIgnoreCase("pesquisar_ajax")) {
            pesquisarAjax(request, response, testemunhaDAO);
            return;
        }

        Testemunha testemunha = new Testemunha();

        if (!comando.equalsIgnoreCase("principal")) {
            try {
                String idTestemunha = request.getParameter("id_testemunha");

                if (idTestemunha != null && !idTestemunha.trim().isEmpty()) {
                    testemunha.setIdTestemunha(Integer.parseInt(idTestemunha));
                }

            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter id da testemunha: " + ex.getMessage());
            }
        }

        try {
            if (comando.equalsIgnoreCase("guardar")) {
                preencherTestemunhaComParametros(request, testemunha);

                testemunhaDAO.save(testemunha);

                request.setAttribute("message", "Testemunha cadastrada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/testemunha/testemunha_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                testemunha.setIdTestemunha(Integer.parseInt(request.getParameter("id_testemunha")));
                preencherTestemunhaComParametros(request, testemunha);

                testemunhaDAO.update(testemunha);

                request.setAttribute("message", "Testemunha editada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/testemunha/testemunha_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                testemunhaDAO.delete(testemunha);

                request.setAttribute("message", "Testemunha eliminada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/testemunha/testemunha_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                testemunha = testemunhaDAO.findById(testemunha.getIdTestemunha());

                request.setAttribute("testemunha", testemunha);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/testemunha/testemunha_editar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/testemunha/testemunha_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                testemunha = testemunhaDAO.findById(testemunha.getIdTestemunha());

                request.setAttribute("testemunha", testemunha);
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/testemunha/testemunha_detalhes.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("principal")) {
                response.sendRedirect("paginas/index.jsp");
            }

        } catch (IOException | ServletException ex) {
            System.err.println("Erro na leitura dos dados da testemunha: " + ex.getMessage());
        }
    }

    private void preencherTestemunhaComParametros(HttpServletRequest request, Testemunha testemunha)
            throws ParseException {

        testemunha.setNomeTestemunha(request.getParameter("nome_testemunha"));
        testemunha.setPaiTestemunha(request.getParameter("pai_testemunha"));
        testemunha.setMaeTestemunha(request.getParameter("mae_testemunha"));
        testemunha.setBiTestemunha(request.getParameter("bi_testemunha"));
        testemunha.setResidenciaTestemunha(request.getParameter("residencia_testemunha"));

        testemunha.setDataNascimentoTestemunha(
                DateUtil.strToDate(request.getParameter("data_nascimento_testemunha"))
        );

        testemunha.setSexo(
                Sexo.getExtensao(request.getParameter("sexo_testemunha"))
        );

        testemunha.setProximidadeTestemunha(request.getParameter("proximidade_testemunha"));
        testemunha.setEstadoCivilTestemunha(request.getParameter("estado_civil_testemunha"));

        testemunha.setDataEmissaoBiTestemunha(
                DateUtil.strToDate(request.getParameter("data_emissao_bi_testemunha"))
        );

        testemunha.setDataValidadeBiTestemunha(
                DateUtil.strToDate(request.getParameter("data_validade_bi_testemunha"))
        );

        testemunha.setTelefoneTestemunha(request.getParameter("telefone_testemunha"));

        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio_testemunha")));
        testemunha.setMunicipio(municipio);

        Profissao profissao = new Profissao();
        profissao.setIdProfissao(Integer.parseInt(request.getParameter("select_profissao_testemunha")));
        testemunha.setProfissao(profissao);
    }

    private void pesquisarAjax(
            HttpServletRequest request,
            HttpServletResponse response,
            TestemunhaDAO testemunhaDAO
    ) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        String tipoPesquisa = request.getParameter("tipo_pesquisa");
        String termo = request.getParameter("termo");
        String pagina = request.getParameter("pagina");

        if (tipoPesquisa == null) {
            tipoPesquisa = "nome";
        }

        if (termo == null) {
            termo = "";
        }

        if (pagina == null || pagina.trim().isEmpty()) {
            pagina = "1";
        }

        List<Testemunha> testemunhas;
        int totalPaginas;

        if (tipoPesquisa.equalsIgnoreCase("bi")) {
            testemunhas = testemunhaDAO.consultarPaginaPorBi(termo, pagina);
            totalPaginas = testemunhaDAO.quantidadePaginasPorBi(termo);

        } else if (tipoPesquisa.equalsIgnoreCase("data")) {
            try {
                if (termo.trim().isEmpty()) {
                    testemunhas = testemunhaDAO.consultarPagina(pagina);
                    totalPaginas = testemunhaDAO.quantidadePaginas();
                } else {
                    java.sql.Date data = DateUtil.strToDate(termo);
                    testemunhas = testemunhaDAO.consultarPaginaPorData(data, pagina);
                    totalPaginas = testemunhaDAO.quantidadePaginasPorData(data);
                }
            } catch (Exception ex) {
                testemunhas = new java.util.ArrayList<Testemunha>();
                totalPaginas = 1;
            }

        } else {
            testemunhas = testemunhaDAO.consultarPaginaPorNome(termo, pagina);
            totalPaginas = testemunhaDAO.quantidadePaginasPorNome(termo);
        }

        PrintWriter out = response.getWriter();

        if (testemunhas == null || testemunhas.isEmpty()) {
            out.println("<tr data-total-paginas='1'>");
            out.println("<td colspan='12' class='text-center text-muted'>Nenhuma testemunha encontrada.</td>");
            out.println("</tr>");
            return;
        }

        boolean primeiraLinha = true;

        for (Testemunha testemunha : testemunhas) {
            if (primeiraLinha) {
                out.println("<tr data-total-paginas='" + totalPaginas + "'>");
                primeiraLinha = false;
            } else {
                out.println("<tr>");
            }

            out.println("<td>" + testemunha.getIdTestemunha() + "</td>");
            out.println("<td>" + valorSeguro(testemunha.getNomeTestemunha()) + "</td>");
            out.println("<td>" + valorSeguro(testemunha.getBiTestemunha()) + "</td>");
            out.println("<td>" + valorSeguro(testemunha.getResidenciaTestemunha()) + "</td>");
            out.println("<td>" + DateUtil.formataData(testemunha.getDataNascimentoTestemunha()) + "</td>");

            if (testemunha.getSexo() != null) {
                out.println("<td>" + testemunha.getSexo().getExtensao() + "</td>");
            } else {
                out.println("<td></td>");
            }

            out.println("<td>" + valorSeguro(testemunha.getTelefoneTestemunha()) + "</td>");

            if (testemunha.getProfissao() != null) {
                out.println("<td>" + valorSeguro(testemunha.getProfissao().getNomeProfissao()) + "</td>");
            } else {
                out.println("<td></td>");
            }

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/testemunhaServlet?comando=detalhes&id_testemunha=" + testemunha.getIdTestemunha() + "'>");
            out.println("<span class='glyphicon glyphicon-print'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/testemunhaServlet?comando=detalhes&id_testemunha=" + testemunha.getIdTestemunha() + "'>");
            out.println("<span class='glyphicon glyphicon-zoom-in'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/testemunhaServlet?comando=prepara_editar&id_testemunha=" + testemunha.getIdTestemunha() + "'>");
            out.println("<span class='glyphicon glyphicon-edit'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a onclick=\"return confirm('Deseja realmente eliminar esta testemunha?');\" href='" + request.getContextPath() + "/testemunhaServlet?comando=eliminar&id_testemunha=" + testemunha.getIdTestemunha() + "'>");
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TestemunhaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(TestemunhaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
