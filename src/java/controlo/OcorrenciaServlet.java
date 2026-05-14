/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.OcorrenciaDAO;
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
import modelo.Autuante;
import modelo.DateUtil;
import modelo.Municipio;
import modelo.Ocorrencia;
import modelo.Testemunha;
import modelo.TipoOcorrencia;

/**
 *
 * @author user
 */
@WebServlet(name = "OcorrenciaServlet", urlPatterns = {"/ocorrenciaServlet"})
public class OcorrenciaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String comando = request.getParameter("comando");

        if (comando == null) {
            comando = "principal";
        }

        OcorrenciaDAO ocorrenciaDAO;
        Ocorrencia ocorrencia = new Ocorrencia();
        if (comando == null || !comando.equalsIgnoreCase("principal")) {

            try {
                String idOcorrencia = request.getParameter("id_ocorrencia");
                if (idOcorrencia != null) {
                    ocorrencia.setIdOcorrencia(Integer.parseInt(idOcorrencia));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            ocorrenciaDAO = new OcorrenciaDAO();
            if (comando.equalsIgnoreCase("pesquisar_ajax")) {
                pesquisarAjax(request, response, ocorrenciaDAO);
                return;
            } else if (comando.equalsIgnoreCase("guardar")) {
                ocorrencia.setDataOcorrencia(DateUtil.strToDate(request.getParameter("data_ocorrencia")));
                ocorrencia.setHoraOcorrencia(request.getParameter("hora_ocorrencia"));
                ocorrencia.setDescricaoOcorrencia(request.getParameter("descricao_ocorrencia"));
                ocorrencia.setRuaOcorrencia(request.getParameter("rua_ocorrencia"));
                ocorrencia.setCidadeOcorrencia(request.getParameter("cidade_ocorrencia"));
                ocorrencia.setBairroOcorrencia(request.getParameter("bairro_ocorrencia"));
                ocorrencia.setProximidadeOcorrencia(request.getParameter("proximidade_ocorrencia"));

                Autuado autuado = new Autuado();
                autuado.setIdAutuado(Integer.parseInt(request.getParameter("select_autuado")));
                ocorrencia.setAutuado(autuado);

                Autuante autuante = new Autuante();
                autuante.setIdAutuante(Integer.parseInt(request.getParameter("select_autuante")));
                ocorrencia.setAutuante(autuante);

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio")));
                ocorrencia.setMunicipio(municipio);

                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                tipoOcorrencia.setIdTipoOcorrencia(Integer.parseInt(request.getParameter("select_tipo_ocorrencia")));
                ocorrencia.setTipoOcorrencia(tipoOcorrencia);

                Testemunha testemunha = new Testemunha();
                testemunha.setIdTestemunha(Integer.parseInt(request.getParameter("select_testemunha")));
                ocorrencia.setTestemunha(testemunha);

                Testemunha testemunha1 = new Testemunha();
                testemunha1.setIdTestemunha(Integer.parseInt(request.getParameter("select_testemunha_sec")));
                ocorrencia.setTestemunha1(testemunha1);

                ocorrenciaDAO.save(ocorrencia);
                request.setAttribute("message", "Ocorrência cadastrada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/ocorrencia/ocorrencia_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                ocorrencia.setIdOcorrencia(Integer.parseInt(request.getParameter("id_ocorrencia")));
                ocorrencia.setDataOcorrencia(DateUtil.strToDate(request.getParameter("data_ocorrencia")));
                ocorrencia.setHoraOcorrencia(request.getParameter("hora_ocorrencia"));
                ocorrencia.setDescricaoOcorrencia(request.getParameter("descricao_ocorrencia"));
                ocorrencia.setRuaOcorrencia(request.getParameter("rua_ocorrencia"));
                ocorrencia.setCidadeOcorrencia(request.getParameter("cidade_ocorrencia"));
                ocorrencia.setBairroOcorrencia(request.getParameter("bairro_ocorrencia"));
                ocorrencia.setProximidadeOcorrencia(request.getParameter("proximidade_ocorrencia"));

                Autuado autuado = new Autuado();
                autuado.setIdAutuado(Integer.parseInt(request.getParameter("select_autuado")));
                ocorrencia.setAutuado(autuado);

                Autuante autuante = new Autuante();
                autuante.setIdAutuante(Integer.parseInt(request.getParameter("select_autuante")));
                ocorrencia.setAutuante(autuante);

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio")));
                ocorrencia.setMunicipio(municipio);

                TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
                tipoOcorrencia.setIdTipoOcorrencia(Integer.parseInt(request.getParameter("select_tipo_ocorrencia")));
                ocorrencia.setTipoOcorrencia(tipoOcorrencia);

                Testemunha testemunha = new Testemunha();
                testemunha.setIdTestemunha(Integer.parseInt(request.getParameter("select_testemunha")));
                ocorrencia.setTestemunha(testemunha);

                Testemunha testemunha1 = new Testemunha();
                testemunha1.setIdTestemunha(Integer.parseInt(request.getParameter("select_testemunha_sec")));
                ocorrencia.setTestemunha1(testemunha1);

                ocorrenciaDAO.update(ocorrencia);
                request.setAttribute("message", "Ocorrência editada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/ocorrencia/ocorrencia_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                ocorrenciaDAO.delete(ocorrencia);

                request.setAttribute("message", "Ocorrência eliminada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/ocorrencia/ocorrencia_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                ocorrencia = ocorrenciaDAO.findById(ocorrencia.getIdOcorrencia());
                request.setAttribute("ocorrencia", ocorrencia);
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/ocorrencia/ocorrencia_editar.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/ocorrencia/ocorrencia_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                ocorrencia = ocorrenciaDAO.findById(ocorrencia.getIdOcorrencia());
                request.setAttribute("ocorrencia", ocorrencia);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/ocorrencia/ocorrencia_detalhes.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("principal")) {
                response.sendRedirect("paginas/index.jsp");
            }
        } catch (IOException | ServletException ex) {
            System.err.println("Erro na leitura dos dados: " + ex.getMessage());
        }

    }

    private void pesquisarAjax(
            HttpServletRequest request,
            HttpServletResponse response,
            OcorrenciaDAO ocorrenciaDAO
    ) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        String tipoPesquisa = request.getParameter("tipo_pesquisa");
        String termo = request.getParameter("termo");
        String pagina = request.getParameter("pagina");

        if (tipoPesquisa == null || tipoPesquisa.trim().isEmpty()) {
            tipoPesquisa = "autuado";
        }

        if (termo == null) {
            termo = "";
        }

        if (pagina == null || pagina.trim().isEmpty()) {
            pagina = "1";
        }

        List<Ocorrencia> ocorrencias;

        if (tipoPesquisa.equalsIgnoreCase("autuante")) {
            ocorrencias = ocorrenciaDAO.consultarPaginaPorAutuante(termo, pagina);

        } else if (tipoPesquisa.equalsIgnoreCase("cidade")) {
            ocorrencias = ocorrenciaDAO.consultarPaginaPorCidade(termo, pagina);

        } else if (tipoPesquisa.equalsIgnoreCase("testemunha")) {
            ocorrencias = ocorrenciaDAO.consultarPaginaPorTestemunha(termo, pagina);

        } else if (tipoPesquisa.equalsIgnoreCase("tipo")) {
            ocorrencias = ocorrenciaDAO.consultarPaginaPorTipo(termo, pagina);

        } else if (tipoPesquisa.equalsIgnoreCase("data")) {
            try {
                if (termo.trim().isEmpty()) {
                    ocorrencias = ocorrenciaDAO.consultarPagina(pagina);
                } else {
                    java.sql.Date data = DateUtil.strToDate(termo);
                    ocorrencias = ocorrenciaDAO.consultarPaginaPorData(data, pagina);
                }
            } catch (Exception ex) {
                ocorrencias = new java.util.ArrayList<Ocorrencia>();
            }

        } else {
            ocorrencias = ocorrenciaDAO.consultarPaginaPorAutuado(termo, pagina);
        }

        PrintWriter out = response.getWriter();

        if (ocorrencias == null || ocorrencias.isEmpty()) {
            out.println("<tr>");
            out.println("<td colspan='12' class='text-center text-muted'>Nenhuma ocorrência encontrada.</td>");
            out.println("</tr>");
            return;
        }

        for (Ocorrencia ocorrencia : ocorrencias) {
            out.println("<tr>");

            out.println("<td>" + ocorrencia.getIdOcorrencia() + "</td>");
            out.println("<td>" + DateUtil.formataData(ocorrencia.getDataOcorrencia()) + "</td>");
            out.println("<td>" + valorSeguro(ocorrencia.getHoraOcorrencia()) + "</td>");
            out.println("<td>" + valorSeguro(ocorrencia.getCidadeOcorrencia()) + "</td>");

            out.println("<td>");
            if (ocorrencia.getAutuado() != null) {
                out.println("<a href='" + request.getContextPath() + "/autuadoServlet?comando=detalhes&id_autuado=" + ocorrencia.getAutuado().getIdAutuado() + "'>");
                out.println(valorSeguro(ocorrencia.getAutuado().getNomeAutuado()));
                out.println("</a>");
            }
            out.println("</td>");

            out.println("<td>");
            if (ocorrencia.getAutuante() != null) {
                out.println("<a href='" + request.getContextPath() + "/autuanteServlet?comando=detalhes&id_autuante=" + ocorrencia.getAutuante().getIdAutuante() + "'>");
                out.println(valorSeguro(ocorrencia.getAutuante().getNomeAutuante()));
                out.println("</a>");
            }
            out.println("</td>");

            out.println("<td>");
            if (ocorrencia.getTipoOcorrencia() != null) {
                out.println(valorSeguro(ocorrencia.getTipoOcorrencia().getNomeTipoOcorrencia()));
            }
            out.println("</td>");

            out.println("<td>");
            if (ocorrencia.getTestemunha() != null) {
                out.println("<a href='" + request.getContextPath() + "/testemunhaServlet?comando=detalhes&id_testemunha=" + ocorrencia.getTestemunha().getIdTestemunha() + "'>");
                out.println(valorSeguro(ocorrencia.getTestemunha().getNomeTestemunha()));
                out.println("</a>");
            }
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/ocorrenciaComParametro?id_ocorrencia=" + ocorrencia.getIdOcorrencia() + "'>");
            out.println("<span class='glyphicon glyphicon-print'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/ocorrenciaServlet?comando=detalhes&id_ocorrencia=" + ocorrencia.getIdOcorrencia() + "'>");
            out.println("<span class='glyphicon glyphicon-zoom-in'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/ocorrenciaServlet?comando=prepara_editar&id_ocorrencia=" + ocorrencia.getIdOcorrencia() + "'>");
            out.println("<span class='glyphicon glyphicon-edit'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a onclick=\"return confirm('Deseja realmente eliminar esta ocorrência?');\" href='" + request.getContextPath() + "/ocorrenciaServlet?comando=eliminar&id_ocorrencia=" + ocorrencia.getIdOcorrencia() + "'>");
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
            Logger.getLogger(OcorrenciaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OcorrenciaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
