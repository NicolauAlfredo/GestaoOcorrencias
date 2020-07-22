/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.ProfissaoDAO;
import java.io.IOException;
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
        ProfissaoDAO profissaoDAO;
        Profissao profissao = new Profissao();
        if (comando == null || !comando.equalsIgnoreCase("principal")) {
            try {
                String idProfissao = request.getParameter("id_profissao");
                if (idProfissao != null) {
                    profissao.setIdProfissao(Integer.parseInt(idProfissao));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            profissaoDAO = new ProfissaoDAO();
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
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/profissao/profissao_editar.jsp");
                rd.forward(request, response);
            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/profissao/profissao_listar.jsp");
            } else if (comando.equalsIgnoreCase("detalhes")) {
                profissao = profissaoDAO.findById(profissao.getIdProfissao());
                request.setAttribute("profissao", profissao);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/profissao/profissao_detalhes.jsp");
                rd.forward(request, response);
            } else if (comando.equalsIgnoreCase("principal")) {
                response.sendRedirect("paginas/index.jsp");
            }
        } catch (IOException | ServletException ex) {
            System.err.println("Erro na leitura dos dados: " + ex.getMessage());
        }
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
