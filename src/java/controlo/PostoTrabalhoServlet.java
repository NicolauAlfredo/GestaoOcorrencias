/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.PostoTrabalhoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Municipio;
import modelo.PostoTrabalho;

/**
 *
 * @author user
 */
@WebServlet(name = "PostoTrabalhoServlet", urlPatterns = {"/postoTrabalhoServlet"})
public class PostoTrabalhoServlet extends HttpServlet {

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
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String comando = request.getParameter("comando");

        if (comando == null) {
            comando = "principal";
        }

        PostoTrabalhoDAO postoTrabalhoDAO;

        postoTrabalhoDAO = new PostoTrabalhoDAO();

        if (comando.equalsIgnoreCase("pesquisar_ajax")) {
            pesquisarAjax(request, response, postoTrabalhoDAO);
            return;
        }

        PostoTrabalho postoTrabalho = new PostoTrabalho();

        if (comando == null || !comando.equalsIgnoreCase("principal")) {
            try {
                String idPostoTrabalho = request.getParameter("id_posto_trabalho");
                if (idPostoTrabalho != null) {
                    postoTrabalho.setIdPostoTrabalho(Integer.parseInt(idPostoTrabalho));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            postoTrabalhoDAO = new PostoTrabalhoDAO();
            if (comando.equalsIgnoreCase("guardar")) {
                postoTrabalho.setNomePostoTrabalho(request.getParameter("nome_posto_trabalho"));
                postoTrabalho.setNumeroPostoTrabalho(Integer.parseInt(request.getParameter("numero_posto_trabalho")));

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio")));
                postoTrabalho.setMunicipio(municipio);

                postoTrabalhoDAO.save(postoTrabalho);
                request.setAttribute("message", "Posto de trabalho cadastrado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/posto/posto_trabalho_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                postoTrabalho.setIdPostoTrabalho(Integer.parseInt(request.getParameter("id_posto_trabalho")));
                postoTrabalho.setNomePostoTrabalho(request.getParameter("nome_posto_trabalho"));
                postoTrabalho.setNumeroPostoTrabalho(Integer.parseInt(request.getParameter("numero_posto_trabalho")));

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio")));
                postoTrabalho.setMunicipio(municipio);

                postoTrabalhoDAO.update(postoTrabalho);
                request.setAttribute("message", "Posto de trabalho editado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/posto/posto_trabalho_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                postoTrabalhoDAO.delete(postoTrabalho);
                request.setAttribute("message", "Posto de trabalho eliminado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/posto/posto_trabalho_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                postoTrabalho = postoTrabalhoDAO.findById(postoTrabalho.getIdPostoTrabalho());
                request.setAttribute("postoTrabalho", postoTrabalho);
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/posto/posto_trabalho_editar.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/posto/posto_trabalho_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                postoTrabalho = postoTrabalhoDAO.findById(postoTrabalho.getIdPostoTrabalho());
                request.setAttribute("postoTrabalho", postoTrabalho);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/posto/posto_trabalho_detalhes.jsp");
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
            PostoTrabalhoDAO postoTrabalhoDAO
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

        List<PostoTrabalho> postosTrabalho;

        if (tipoPesquisa.equalsIgnoreCase("municipio")) {
            postosTrabalho = postoTrabalhoDAO.consultarPaginaPorMunicipio(termo, pagina);

        } else if (tipoPesquisa.equalsIgnoreCase("numero")) {
            try {
                if (termo.trim().isEmpty()) {
                    postosTrabalho = postoTrabalhoDAO.consultarPagina(pagina);
                } else {
                    Integer numero = Integer.parseInt(termo.trim());
                    postosTrabalho = postoTrabalhoDAO.consultarPaginaPorNumero(numero, pagina);
                }
            } catch (NumberFormatException ex) {
                postosTrabalho = new java.util.ArrayList<PostoTrabalho>();
            }

        } else {
            postosTrabalho = postoTrabalhoDAO.consultarPaginaPorNome(termo, pagina);
        }

        PrintWriter out = response.getWriter();

        if (postosTrabalho == null || postosTrabalho.isEmpty()) {
            out.println("<tr>");
            out.println("<td colspan='8' class='text-center text-muted'>Nenhum posto de trabalho encontrado.</td>");
            out.println("</tr>");
            return;
        }

        for (PostoTrabalho postoTrabalho : postosTrabalho) {
            out.println("<tr>");
            out.println("<td>" + postoTrabalho.getIdPostoTrabalho() + "</td>");
            out.println("<td>" + valorSeguro(postoTrabalho.getNomePostoTrabalho()) + "</td>");
            out.println("<td>" + postoTrabalho.getNumeroPostoTrabalho() + "</td>");

            if (postoTrabalho.getMunicipio() != null) {
                out.println("<td>" + valorSeguro(postoTrabalho.getMunicipio().getNomeMunicipio()) + "</td>");
            } else {
                out.println("<td></td>");
            }

            out.println("<td><a href='" + request.getContextPath() + "/postoTrabalhoServlet?comando=detalhes&id_posto_trabalho=" + postoTrabalho.getIdPostoTrabalho() + "'><span class='glyphicon glyphicon-print'></span></a></td>");
            out.println("<td><a href='" + request.getContextPath() + "/postoTrabalhoServlet?comando=detalhes&id_posto_trabalho=" + postoTrabalho.getIdPostoTrabalho() + "'><span class='glyphicon glyphicon-zoom-in'></span></a></td>");
            out.println("<td><a href='" + request.getContextPath() + "/postoTrabalhoServlet?comando=prepara_editar&id_posto_trabalho=" + postoTrabalho.getIdPostoTrabalho() + "'><span class='glyphicon glyphicon-edit'></span></a></td>");
            out.println("<td><a onclick=\"return confirm('Deseja realmente eliminar este posto de trabalho?');\" href='" + request.getContextPath() + "/postoTrabalhoServlet?comando=eliminar&id_posto_trabalho=" + postoTrabalho.getIdPostoTrabalho() + "'><span class='glyphicon glyphicon-trash'></span></a></td>");
            out.println("</tr>");
        }
    }

    private String valorSeguro(String valor) {
        return valor == null ? "" : valor;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
