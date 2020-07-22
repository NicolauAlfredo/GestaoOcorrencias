/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.PostoTrabalhoDAO;
import java.io.IOException;
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
