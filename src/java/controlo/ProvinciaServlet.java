/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.ProvinciaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Provincia;

/**
 *
 * @author user
 */
@WebServlet(name = "ProvinciaServlet", urlPatterns = {"/provinciaServlet"})
public class ProvinciaServlet extends HttpServlet {

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
        ProvinciaDAO provinciaDAO;
        Provincia provincia = new Provincia();
        if (comando == null || !comando.equalsIgnoreCase("principal")) {
            try {
                String idProvincia = request.getParameter("id_provincia");
                if (idProvincia != null) {
                    provincia.setIdProvincia(Integer.parseInt(idProvincia));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            provinciaDAO = new ProvinciaDAO();
            if (comando.equalsIgnoreCase("guardar")) {
                provincia.setNomeProvincia(request.getParameter("nome_provincia"));

                provinciaDAO.save(provincia);
                request.setAttribute("message", "Província cadastrada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/provincia/provincia_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                provincia.setIdProvincia(Integer.parseInt(request.getParameter("id_provincia")));
                provincia.setNomeProvincia(request.getParameter("nome_provincia"));

                provinciaDAO.update(provincia);
                request.setAttribute("message", "Província editada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/provincia/provincia_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                provinciaDAO.delete(provincia);

                request.setAttribute("message", "Província eliminada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/provincia/provincia_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                provincia = provinciaDAO.findById(provincia.getIdProvincia());
                request.setAttribute("provincia", provincia);
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/provincia/provincia_editar.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/provincia/provincia_listar.jsp");
            } else if (comando.equalsIgnoreCase("detalhes")) {
                provincia = provinciaDAO.findById(provincia.getIdProvincia());
                request.setAttribute("provincia", provincia);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/provincia/provincia_detalhes.jsp");
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
