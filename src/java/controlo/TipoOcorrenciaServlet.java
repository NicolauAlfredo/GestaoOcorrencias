/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.TipoOcorrenciaDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.TipoOcorrencia;

/**
 *
 * @author user
 */
@WebServlet(name = "TipoOcorrenciaServlet", urlPatterns = {"/tipoOcorrenciaServlet"})
public class TipoOcorrenciaServlet extends HttpServlet {

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
        TipoOcorrenciaDAO tipoOcorrenciaDAO;
        TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
        if (comando == null || !comando.equalsIgnoreCase("principal")) {
            try {
                String idTipoOcorrencia = request.getParameter("id_tipo_ocorrencia");
                if (idTipoOcorrencia != null) {
                    tipoOcorrencia.setIdTipoOcorrencia(Integer.parseInt(idTipoOcorrencia));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            tipoOcorrenciaDAO = new TipoOcorrenciaDAO();
            if (comando.equalsIgnoreCase("guardar")) {
                tipoOcorrencia.setNomeTipoOcorrencia(request.getParameter("nome_tipo_ocorrencia"));

                tipoOcorrenciaDAO.save(tipoOcorrencia);
                request.setAttribute("message", "Tipo de ocorrência cadastrada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/tipo/tipo_ocorrencia_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                tipoOcorrencia.setIdTipoOcorrencia(Integer.parseInt(request.getParameter("id_tipo_ocorrencia")));
                tipoOcorrencia.setNomeTipoOcorrencia(request.getParameter("nome_tipo_ocorrencia"));

                tipoOcorrenciaDAO.update(tipoOcorrencia);
                request.setAttribute("message", "Tipo de ocorrência editada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/tipo/tipo_ocorrencia_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                tipoOcorrenciaDAO.delete(tipoOcorrencia);
                request.setAttribute("message", "Tipo de ocorrência eliminada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/tipo/tipo_ocorrencia_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                tipoOcorrencia = tipoOcorrenciaDAO.findById(tipoOcorrencia.getIdTipoOcorrencia());
                request.setAttribute("tipoOcorrencia", tipoOcorrencia);
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/tipo/tipo_ocorrencia_editar.jsp");
                rd.forward(request, response);
                
            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/tipo/tipo_ocorrencia_listar.jsp");
                
            } else if (comando.equalsIgnoreCase("detalhes")) {
                tipoOcorrencia = tipoOcorrenciaDAO.findById(tipoOcorrencia.getIdTipoOcorrencia());
                request.setAttribute("tipoOcorrencia", tipoOcorrencia);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/tipo/tipo_ocorrencia_detalhes.jsp");
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
