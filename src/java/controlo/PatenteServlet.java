/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.PatenteDAO;
import java.io.IOException;
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
        PatenteDAO patenteDAO;
        Patente patente = new Patente();
        if (comando == null || !comando.equalsIgnoreCase("principal")) {
            try {
                String idPatente = request.getParameter("id_patente");
                if (idPatente != null) {
                    patente.setIdPatente(Integer.parseInt(idPatente));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            patenteDAO = new PatenteDAO();
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
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/patente/patente_editar.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/patente/patente_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                patente = patenteDAO.findById(patente.getIdPatente());
                request.setAttribute("patente", patente);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/patente/patente_detalhes.jsp");
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
