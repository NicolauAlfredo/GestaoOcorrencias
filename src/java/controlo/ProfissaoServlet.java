/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.ProfissaoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Profissao;

/**
 *
 * @author user
 */
@WebServlet(name = "ProfissaoServlet", urlPatterns = {"/profissaoServlet"})
public class ProfissaoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String comando = request.getParameter("comando");

        if (comando == null) {
            comando = "principal";
        }

        ProfissaoDAO profissaoDAO = new ProfissaoDAO();

        if (comando.equalsIgnoreCase("pesquisar_ajax")) {
            pesquisarAjax(request, response, profissaoDAO);
            return;
        }

        Profissao profissao = new Profissao();

        if (!comando.equalsIgnoreCase("principal")) {
            try {
                String idProfissao = request.getParameter("id_profissao");

                if (idProfissao != null && !idProfissao.trim().isEmpty()) {
                    profissao.setIdProfissao(Integer.parseInt(idProfissao));
                }

            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter id da profissão: " + ex.getMessage());
            }
        }

        try {
            if (comando.equalsIgnoreCase("guardar")) {
                profissao.setNomeProfissao(request.getParameter("nome_profissao"));

                profissaoDAO.save(profissao);

                request.setAttribute("message", "Profissão cadastrada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/profissao/profissao_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                profissao.setIdProfissao(Integer.parseInt(request.getParameter("id_profissao")));
                profissao.setNomeProfissao(request.getParameter("nome_profissao"));

                profissaoDAO.update(profissao);

                request.setAttribute("message", "Profissão editada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/profissao/profissao_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                profissaoDAO.delete(profissao);

                request.setAttribute("message", "Profissão eliminada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/profissao/profissao_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                profissao = profissaoDAO.findById(profissao.getIdProfissao());

                request.setAttribute("profissao", profissao);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/profissao/profissao_editar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/profissao/profissao_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                profissao = profissaoDAO.findById(profissao.getIdProfissao());

                request.setAttribute("profissao", profissao);
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/profissao/profissao_detalhes.jsp");
                dispatcher.forward(request, response);

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
            ProfissaoDAO profissaoDAO
    ) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        String termo = request.getParameter("termo");

        if (termo == null) {
            termo = "";
        }

        List<Profissao> profissoes = profissaoDAO.findByNome(termo);

        PrintWriter out = response.getWriter();

        if (profissoes == null || profissoes.isEmpty()) {
            out.println("<tr>");
            out.println("<td colspan='6' class='text-center text-muted'>Nenhuma profissão encontrada.</td>");
            out.println("</tr>");
            return;
        }

        for (Profissao profissao : profissoes) {
            out.println("<tr>");

            out.println("<td>" + profissao.getIdProfissao() + "</td>");
            out.println("<td>" + valorSeguro(profissao.getNomeProfissao()) + "</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/profissaoServlet?comando=detalhes&id_profissao=" + profissao.getIdProfissao() + "'>");
            out.println("<span class='glyphicon glyphicon-print'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/profissaoServlet?comando=detalhes&id_profissao=" + profissao.getIdProfissao() + "'>");
            out.println("<span class='glyphicon glyphicon-zoom-in'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/profissaoServlet?comando=prepara_editar&id_profissao=" + profissao.getIdProfissao() + "'>");
            out.println("<span class='glyphicon glyphicon-edit'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a onclick=\"return confirm('Deseja realmente eliminar esta profissão?');\" href='" + request.getContextPath() + "/profissaoServlet?comando=eliminar&id_profissao=" + profissao.getIdProfissao() + "'>");
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
