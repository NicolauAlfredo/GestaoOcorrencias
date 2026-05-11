/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.AutuadoDAO;
import java.io.IOException;
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
import modelo.Autuado;
import modelo.DateUtil;
import modelo.Municipio;
import modelo.Profissao;

/**
 *
 * @author user
 */
@WebServlet(name = "AutuadoServlet", urlPatterns = {"/autuadoServlet"})
public class AutuadoServlet extends HttpServlet {

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
        AutuadoDAO autuadoDAO;
        Autuado autuado = new Autuado();
        List<Autuado> autuados;
        if (comando == null || !comando.equalsIgnoreCase("principal")) {
            try {
                String idAutuado = request.getParameter("id_autuado");
                if (idAutuado != null) {
                    autuado.setIdAutuado(Integer.parseInt(idAutuado));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            autuadoDAO = new AutuadoDAO();
            if (comando.equalsIgnoreCase("guardar")) {
                autuado.setNomeAutuado(request.getParameter("nome_autuado"));
                autuado.setPaiAutuado(request.getParameter("pai_autuado"));
                autuado.setMaeAutuado(request.getParameter("mae_autuado"));
                autuado.setBiAutuado(request.getParameter("bi_autuado"));
                autuado.setResidenciaAutuado(request.getParameter("residencia_autuado"));
                autuado.setDataNascimentoAutuado(DateUtil.strToDate(request.getParameter("data_nascimento_autuado")));
                autuado.setSexo(autuado.getSexo().getExtensao(request.getParameter("sexo_autuado")));
                autuado.setProximidadeAutuado(request.getParameter("proximidade_autuado"));
                autuado.setEstadoCivilAutuado(request.getParameter("estado_civil_autuado"));
                autuado.setDataEmissaoBiAutuado(DateUtil.strToDate(request.getParameter("data_emissao_bi_autuado")));
                autuado.setDataValidadeBiAutuado(DateUtil.strToDate(request.getParameter("data_validade_bi_autuado")));
                autuado.setTelefoneAutuado(request.getParameter("telefone_autuado"));

                Profissao profissao = new Profissao();
                profissao.setIdProfissao(Integer.parseInt(request.getParameter("select_profissao_autuado")));
                autuado.setProfissao(profissao);

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio_autuado")));
                autuado.setMunicipio(municipio);

                autuadoDAO.save(autuado);
                request.setAttribute("message", "Autuado cadastrado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuado/autuado_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                autuado.setIdAutuado(Integer.parseInt(request.getParameter("id_autuado")));
                autuado.setNomeAutuado(request.getParameter("nome_autuado"));
                autuado.setPaiAutuado(request.getParameter("pai_autuado"));
                autuado.setMaeAutuado(request.getParameter("mae_autuado"));
                autuado.setBiAutuado(request.getParameter("bi_autuado"));
                autuado.setResidenciaAutuado(request.getParameter("residencia_autuado"));
                autuado.setDataNascimentoAutuado(DateUtil.strToDate(request.getParameter("data_nascimento_autuado")));
                autuado.setSexo(modelo.Sexo.getExtensao(request.getParameter("sexo_autuado")));
                autuado.setProximidadeAutuado(request.getParameter("proximidade_autuado"));
                autuado.setEstadoCivilAutuado(request.getParameter("estado_civil_autuado"));
                autuado.setDataEmissaoBiAutuado(DateUtil.strToDate(request.getParameter("data_emissao_bi_autuado")));
                autuado.setDataValidadeBiAutuado(DateUtil.strToDate(request.getParameter("data_validade_bi_autuado")));
                autuado.setTelefoneAutuado(request.getParameter("telefone_autuado"));

                Profissao profissao = new Profissao();
                profissao.setIdProfissao(Integer.parseInt(request.getParameter("select_profissao_autuado")));
                autuado.setProfissao(profissao);

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_profissao_autuado")));
                autuado.setMunicipio(municipio);

                autuadoDAO.update(autuado);
                request.setAttribute("message", "Autuado editado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuado/autuado_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                autuadoDAO.delete(autuado);

                request.setAttribute("message", "Autuado eliminado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuado/autuado_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                autuado = autuadoDAO.findById(autuado.getIdAutuado());
                request.setAttribute("autuado", autuado);
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/autuado/autuado_editar.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/autuado/autuado_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                autuado = autuadoDAO.findById(autuado.getIdAutuado());
                request.setAttribute("autuado", autuado);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/autuado/autuado_detalhes.jsp");
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
            Logger.getLogger(AutuadoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AutuadoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
