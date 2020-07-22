/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.TestemunhaDAO;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DateUtil;
import modelo.Municipio;
import modelo.Profissao;
import modelo.Testemunha;

/**
 *
 * @author user
 */
@WebServlet(name = "TestemunhaServlet", urlPatterns = {"/testemunhaServlet"})
public class TestemunhaServlet extends HttpServlet {

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
        TestemunhaDAO testemunhaDAO;
        Testemunha testemunha = new Testemunha();
        if (comando == null || !comando.equalsIgnoreCase("principal")) {
            try {
                String idTestemunha = request.getParameter("id_testemunha");
                if (idTestemunha != null) {
                    testemunha.setIdTestemunha(Integer.parseInt(idTestemunha));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            testemunhaDAO = new TestemunhaDAO();
            if (comando.equalsIgnoreCase("guardar")) {
                testemunha.setNomeTestemunha(request.getParameter("nome_testemunha"));
                testemunha.setPaiTestemunha(request.getParameter("pai_testemunha"));
                testemunha.setMaeTestemunha(request.getParameter("mae_testemunha"));
                testemunha.setBiTestemunha(request.getParameter("bi_testemunha"));
                testemunha.setResidenciaTestemunha(request.getParameter("residencia_testemunha"));
                testemunha.setDataNascimentoTestemunha(DateUtil.strToDate(request.getParameter("data_nascimento_testemunha")));
                testemunha.setSexo(testemunha.getSexo().getExtensao(request.getParameter("sexo_testemunha")));
                testemunha.setProximidadeTestemunha(request.getParameter("proximidade_testemunha"));
                testemunha.setEstadoCivilTestemunha(request.getParameter("estado_civil_testemunha"));
                testemunha.setDataEmissaoBiTestemunha(DateUtil.strToDate(request.getParameter("data_emissao_bi_testemunha")));
                testemunha.setDataValidadeBiTestemunha(DateUtil.strToDate(request.getParameter("data_validade_bi_testemunha")));
                testemunha.setTelefoneTestemunha(request.getParameter("telefone_testemunha"));

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio_testemunha")));
                testemunha.setMunicipio(municipio);

                Profissao profissao = new Profissao();
                profissao.setIdProfissao(Integer.parseInt(request.getParameter("select_profissao_testemunha")));
                testemunha.setProfissao(profissao);

                testemunhaDAO.save(testemunha);

                request.setAttribute("message", "Testemunha cadastrada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/testemunha/testemunha_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                testemunha.setIdTestemunha(Integer.parseInt(request.getParameter("id_testemunha")));
                testemunha.setNomeTestemunha(request.getParameter("nome_testemunha"));
                testemunha.setPaiTestemunha(request.getParameter("pai_testemunha"));
                testemunha.setMaeTestemunha(request.getParameter("mae_testemunha"));
                testemunha.setBiTestemunha(request.getParameter("bi_testemunha"));
                testemunha.setResidenciaTestemunha(request.getParameter("residencia_testemunha"));
                testemunha.setDataNascimentoTestemunha(DateUtil.strToDate(request.getParameter("data_nascimento_testemunha")));
                testemunha.setSexo(testemunha.getSexo().getExtensao(request.getParameter("sexo_testemunha")));
                testemunha.setProximidadeTestemunha(request.getParameter("proximidade_testemunha"));
                testemunha.setEstadoCivilTestemunha(request.getParameter("estado_civil_testemunha"));
                testemunha.setDataEmissaoBiTestemunha(DateUtil.strToDate(request.getParameter("data_emissao_bi_testemunha")));
                testemunha.setDataValidadeBiTestemunha(DateUtil.strToDate(request.getParameter("data_validade_bi_testemunha")));
                testemunha.setTelefoneTestemunha(request.getParameter("telefone_testemunha"));

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio_testemunha")));
                testemunha.setMunicipio(municipio);

                Profissao profissao = new Profissao();
                profissao.setIdProfissao(Integer.parseInt(request.getParameter("select_profissao_testemunha")));
                testemunha.setProfissao(profissao);

                testemunhaDAO.update(testemunha);
                request.setAttribute("message", "Testemunha editada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/testemunha/testemunha_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                testemunhaDAO.delete(testemunha);
                request.setAttribute("message", "Testemunha eliminada!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/testemunha/testemunha_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                testemunha = testemunhaDAO.findById(testemunha.getIdTestemunha());
                request.setAttribute("testemunha", testemunha);
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/testemunha/testemunha_editar.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/testemunha/testemunha_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                testemunha = testemunhaDAO.findById(testemunha.getIdTestemunha());

                request.setAttribute("testemunha", testemunha);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/testemunha/testemunha_detalhes.jsp");
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
            Logger.getLogger(TestemunhaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TestemunhaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
