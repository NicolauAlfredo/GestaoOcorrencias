/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.AdministradorDAO;
import java.io.IOException;
import java.sql.SQLException;
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
import javax.servlet.http.HttpSession;
import modelo.Administrador;
import modelo.DateUtil;

/**
 *
 * @author user
 */
@WebServlet(name = "AdministradorServlet", urlPatterns = {"/administradorServlet"})
public class AdministradorServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String comando = request.getParameter("comando");
        if (comando == null) {
            comando = "principal";
        }

        AdministradorDAO administradorDAO;
        Administrador administrador = new Administrador();
        List<Administrador> administradores;
        if (comando == null || !comando.equalsIgnoreCase(
                "principal")) {
            try {
                String idAdministrador = request.getParameter("id_administrador");
                if (idAdministrador != null) {
                    administrador.setIdAdministrador(Integer.parseInt(idAdministrador));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }

        try {
            administradorDAO = new AdministradorDAO();
            if (comando.equalsIgnoreCase("guardar")) {
                administrador.setNomeAdministrador(request.getParameter("nome_administrador"));
                administrador.setDataNascimentoAdministrador(DateUtil.strToDate(request.getParameter("data_nascimento_administrador")));
                administrador.setSexo(administrador.getSexo().getExtensao(request.getParameter("sexo_administrador")));
                administrador.setBiAdministrador(request.getParameter("bi_administrador"));
                administrador.setNipAdministrador(Integer.parseInt(request.getParameter("nip_administrador")));
                administrador.setEmailAdministrador(request.getParameter("email_administrador"));
                administrador.setTelefoneAdministrador(request.getParameter("telefone_administrador"));
                administrador.setPalavraPasseAdministrador(request.getParameter("palavra_passe_administrador"));

                administradorDAO.save(administrador);
                request.setAttribute("message", "Administrador cadastrado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/administrador/administrador_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                administrador.setIdAdministrador(Integer.parseInt(request.getParameter("id_administrador")));
                administrador.setNomeAdministrador(request.getParameter("nome_administrador"));
                administrador.setDataNascimentoAdministrador(DateUtil.strToDate(request.getParameter("data_nascimento_administrador")));
                administrador.setSexo(administrador.getSexo().getExtensao(request.getParameter("sexo_administrador")));
                administrador.setBiAdministrador(request.getParameter("bi_administrador"));
                administrador.setNipAdministrador(Integer.parseInt(request.getParameter("nip_administrador")));
                administrador.setEmailAdministrador(request.getParameter("email_administrador"));
                administrador.setTelefoneAdministrador(request.getParameter("telefone_administrador"));
                administrador.setPalavraPasseAdministrador(request.getParameter("palavra_passe_administrador"));

                administradorDAO.update(administrador);
                request.setAttribute("message", "Administrador editado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/administrador/administrador_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("entrar")) {
                String nip = request.getParameter("nip_administrador");
                String palavraPasse = request.getParameter("palavra_passe_administrador");

                try {
                    administrador = administradorDAO.verificarAcesso(nip, palavraPasse);
                    String pagina = "paginas/administrador/login.jsp";

                    if (administrador != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("administrador", administrador);
                        pagina = "paginas/index.jsp";
                    } else {
                        request.setAttribute("message", "O NIP ou a palavra-passe está errada.");
                    }
                    RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
                    dispatcher.forward(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    throw new ServletException(ex);
                }

            } else if (comando.equalsIgnoreCase("sair")) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.removeAttribute("administrador");
                    session.invalidate();

                    RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/administrador/login.jsp");
                    dispatcher.forward(request, response);
                }
            } else if (comando.equalsIgnoreCase("eliminar")) {
                administradorDAO.delete(administrador);

                request.setAttribute("message", "Administrador eliminado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/administrador/administrador_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                administrador = administradorDAO.findById(administrador.getIdAdministrador());
                request.setAttribute("administrador", administrador);
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/administrador/administrador_editar.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/administrador/administrador_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                administrador = administradorDAO.findById(administrador.getIdAdministrador());
                request.setAttribute("administrador", administrador);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/administrador/administrador_detalhes.jsp");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(AdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdministradorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
