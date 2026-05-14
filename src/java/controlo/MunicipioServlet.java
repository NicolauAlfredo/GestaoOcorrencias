/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.MunicipioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Municipio;
import modelo.Provincia;

/**
 *
 * @author user
 */
@WebServlet(name = "MunicipioServlet", urlPatterns = {"/municipioServlet"})
public class MunicipioServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String comando = request.getParameter("comando");

        if (comando == null) {
            comando = "principal";
        }
        MunicipioDAO municipioDAO;
        Municipio municipio = new Municipio();
        if (comando == null || !comando.equalsIgnoreCase("principal")) {
            try {
                String idMunicipio = request.getParameter("id_municipio");
                if (idMunicipio != null) {
                    municipio.setIdMunicipio(Integer.parseInt(idMunicipio));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            municipioDAO = new MunicipioDAO();

            if (comando.equalsIgnoreCase("pesquisar_ajax")) {
                pesquisarAjax(request, response, municipioDAO);
                return;
            }

            if (comando.equalsIgnoreCase("guardar")) {
                Provincia provincia = new Provincia();
                municipio.setNomeMunicipio(request.getParameter("nome_municipio"));
                provincia.setIdProvincia(Integer.parseInt(request.getParameter("select_provincia")));
                municipio.setProvincia(provincia);

                municipioDAO.save(municipio);
                request.setAttribute("message", "Município cadastrado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/municipio/municipio_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                Provincia provincia = new Provincia();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("id_municipio")));
                municipio.setNomeMunicipio(request.getParameter("nome_municipio"));
                provincia.setIdProvincia(Integer.parseInt(request.getParameter("select_provincia")));
                municipio.setProvincia(provincia);

                municipioDAO.update(municipio);
                request.setAttribute("message", "Município editado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/municipio/municipio_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                municipioDAO.delete(municipio);

                request.setAttribute("message", "Município eliminado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/municipio/municipio_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                municipio = municipioDAO.findById(municipio.getIdMunicipio());
                request.setAttribute("municipio", municipio);
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/municipio/municipio_editar.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/municipio/municipio_listar.jsp");
            } else if (comando.equalsIgnoreCase("detalhes")) {
                municipio = municipioDAO.findById(municipio.getIdMunicipio());
                request.setAttribute("municipio", municipio);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/municipio/municipio_detalhes.jsp");
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
            MunicipioDAO municipioDAO
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

        List<Municipio> municipios;

        if (tipoPesquisa.equalsIgnoreCase("provincia")) {
            municipios = municipioDAO.consultarPaginaPorProvincia(termo, pagina);
        } else {
            municipios = municipioDAO.consultarPaginaPorNome(termo, pagina);
        }

        PrintWriter out = response.getWriter();

        if (municipios == null || municipios.isEmpty()) {
            out.println("<tr>");
            out.println("<td colspan='7' class='text-center text-muted'>Nenhum município encontrado.</td>");
            out.println("</tr>");
            return;
        }

        for (Municipio municipio : municipios) {
            out.println("<tr>");

            out.println("<td>" + municipio.getIdMunicipio() + "</td>");
            out.println("<td>" + valorSeguro(municipio.getNomeMunicipio()) + "</td>");

            if (municipio.getProvincia() != null) {
                out.println("<td>" + valorSeguro(municipio.getProvincia().getNomeProvincia()) + "</td>");
            } else {
                out.println("<td></td>");
            }

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/municipioServlet?comando=detalhes&id_municipio=" + municipio.getIdMunicipio() + "'>");
            out.println("<span class='glyphicon glyphicon-print'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/municipioServlet?comando=detalhes&id_municipio=" + municipio.getIdMunicipio() + "'>");
            out.println("<span class='glyphicon glyphicon-zoom-in'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/municipioServlet?comando=prepara_editar&id_municipio=" + municipio.getIdMunicipio() + "'>");
            out.println("<span class='glyphicon glyphicon-edit'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a onclick=\"return confirm('Deseja realmente eliminar este município?');\" href='" + request.getContextPath() + "/municipioServlet?comando=eliminar&id_municipio=" + municipio.getIdMunicipio() + "'>");
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
        } catch (Exception ex) {
            Logger.getLogger(MunicipioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(MunicipioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
