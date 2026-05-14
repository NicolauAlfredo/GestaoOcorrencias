/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.PatenteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Patente;

/**
 *
 * @author user
 */
@WebServlet(name = "PatenteServlet", urlPatterns = {"/patenteServlet"})
public class PatenteServlet extends HttpServlet {

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

        PatenteDAO patenteDAO = new PatenteDAO();

        if (comando.equalsIgnoreCase("pesquisar_ajax")) {
            pesquisarAjax(request, response, patenteDAO);
            return;
        }

        Patente patente = new Patente();

        if (!comando.equalsIgnoreCase("principal")) {
            try {
                String idPatente = request.getParameter("id_patente");

                if (idPatente != null && !idPatente.trim().isEmpty()) {
                    patente.setIdPatente(Integer.parseInt(idPatente));
                }

            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter id da patente: " + ex.getMessage());
            }
        }

        try {
            if (comando.equalsIgnoreCase("guardar")) {
                patente.setNomePatente(request.getParameter("nome_patente"));

                patenteDAO.save(patente);

                request.setAttribute("message", "Patente cadastrada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/patente/patente_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                patente.setIdPatente(Integer.parseInt(request.getParameter("id_patente")));
                patente.setNomePatente(request.getParameter("nome_patente"));

                patenteDAO.update(patente);

                request.setAttribute("message", "Patente editada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/patente/patente_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                patenteDAO.delete(patente);

                request.setAttribute("message", "Patente eliminada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/patente/patente_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                patente = patenteDAO.findById(patente.getIdPatente());

                request.setAttribute("patente", patente);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/patente/patente_editar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/patente/patente_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                patente = patenteDAO.findById(patente.getIdPatente());

                request.setAttribute("patente", patente);
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/patente/patente_detalhes.jsp");
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
            PatenteDAO patenteDAO
    ) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        String termo = request.getParameter("termo");
        String pagina = request.getParameter("pagina");

        if (termo == null) {
            termo = "";
        }

        if (pagina == null || pagina.trim().isEmpty()) {
            pagina = "1";
        }

        List<Patente> patentes;

        if (termo.trim().isEmpty()) {
            patentes = patenteDAO.consultarPagina(pagina);
        } else {
            patentes = patenteDAO.consultarPaginaPorNome(termo, pagina);
        }

        PrintWriter out = response.getWriter();

        if (patentes == null || patentes.isEmpty()) {
            out.println("<tr>");
            out.println("<td colspan='6' class='text-center text-muted'>Nenhuma patente encontrada.</td>");
            out.println("</tr>");
            return;
        }

        for (Patente patente : patentes) {
            out.println("<tr>");

            out.println("<td>" + patente.getIdPatente() + "</td>");
            out.println("<td>" + valorSeguro(patente.getNomePatente()) + "</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/patenteServlet?comando=detalhes&id_patente=" + patente.getIdPatente() + "'>");
            out.println("<span class='glyphicon glyphicon-print'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/patenteServlet?comando=detalhes&id_patente=" + patente.getIdPatente() + "'>");
            out.println("<span class='glyphicon glyphicon-zoom-in'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/patenteServlet?comando=prepara_editar&id_patente=" + patente.getIdPatente() + "'>");
            out.println("<span class='glyphicon glyphicon-edit'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a onclick=\"return confirm('Deseja realmente eliminar esta patente?');\" href='" + request.getContextPath() + "/patenteServlet?comando=eliminar&id_patente=" + patente.getIdPatente() + "'>");
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
