/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.AutuanteDAO;
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
import modelo.Autuante;
import modelo.DateUtil;
import modelo.Municipio;
import modelo.Patente;
import modelo.PostoTrabalho;

/**
 *
 * @author user
 */
@WebServlet(name = "AutuanteServlet", urlPatterns = {"/autuanteServlet"})
public class AutuanteServlet extends HttpServlet {

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
        AutuanteDAO autuanteDAO;
        Autuante autuante = new Autuante();
        if (comando == null || !comando.equalsIgnoreCase("principal")) {
            try {
                String idAutuante = request.getParameter("id_autuante");
                if (idAutuante != null) {
                    autuante.setIdAutuante(Integer.parseInt(idAutuante));
                }
            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter dado: " + ex.getMessage());
            }
        }
        try {
            autuanteDAO = new AutuanteDAO();
            if (comando.equalsIgnoreCase("guardar")) {
                autuante.setNomeAutuante(request.getParameter("nome_autuante"));
                autuante.setPaiAutuante(request.getParameter("pai_autuante"));
                autuante.setMaeAutuante(request.getParameter("mae_autuante"));
                autuante.setBiAutuante(request.getParameter("bi_autuante"));
                autuante.setResidenciaAutuante(request.getParameter("residencia_autuante"));
                autuante.setDataNascimentoAutuante(DateUtil.strToDate(request.getParameter("data_nascimento_autuante")));
                autuante.setSexo(autuante.getSexo().getExtensao(request.getParameter("sexo_autuante")));
                autuante.setAlturaAutuante(Double.parseDouble(request.getParameter("altura_autuante")));
                autuante.setDataEmissaoBiAutuante(DateUtil.strToDate(request.getParameter("data_emissao_bi_autuante")));
                autuante.setDataValidadeBiAutuante(DateUtil.strToDate(request.getParameter("data_validade_bi_autuante")));
                autuante.setNipAutuante(Integer.parseInt(request.getParameter("nip_autuante")));
                autuante.setTelefoneAutuante(request.getParameter("telefone_autuante"));

                Patente patente = new Patente();
                patente.setIdPatente(Integer.parseInt(request.getParameter("select_patente_autuante")));
                autuante.setPatente(patente);

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_posto_trabalho_autuante")));
                autuante.setMunicipio(municipio);

                PostoTrabalho postoTrabalho = new PostoTrabalho();
                postoTrabalho.setIdPostoTrabalho(Integer.parseInt(request.getParameter("select_municipio_autuante")));
                autuante.setPostoTrabalho(postoTrabalho);

                autuanteDAO.save(autuante);
                request.setAttribute("message", "Autuante cadastrado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuante/autuante_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                autuante.setIdAutuante(Integer.parseInt(request.getParameter("id_autuante")));
                autuante.setNomeAutuante(request.getParameter("nome_autuante"));
                autuante.setPaiAutuante(request.getParameter("pai_autuante"));
                autuante.setMaeAutuante(request.getParameter("mae_autuante"));
                autuante.setBiAutuante(request.getParameter("bi_autuante"));
                autuante.setResidenciaAutuante(request.getParameter("residencia_autuante"));
                autuante.setDataNascimentoAutuante(DateUtil.strToDate(request.getParameter("data_nascimento_autuante")));
                autuante.setSexo(autuante.getSexo().getExtensao(request.getParameter("sexo_autuante")));
                autuante.setAlturaAutuante(Double.parseDouble(request.getParameter("altura_autuante")));
                autuante.setDataEmissaoBiAutuante(DateUtil.strToDate(request.getParameter("data_emissao_bi_autuante")));
                autuante.setDataValidadeBiAutuante(DateUtil.strToDate(request.getParameter("data_validade_bi_autuante")));
                autuante.setNipAutuante(Integer.parseInt(request.getParameter("nip_autuante")));
                autuante.setTelefoneAutuante(request.getParameter("telefone_autuante"));

                Patente patente = new Patente();
                patente.setIdPatente(Integer.parseInt(request.getParameter("select_patente_autuante")));
                autuante.setPatente(patente);

                Municipio municipio = new Municipio();
                municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_posto_trabalho_autuante")));
                autuante.setMunicipio(municipio);

                PostoTrabalho postoTrabalho = new PostoTrabalho();
                postoTrabalho.setIdPostoTrabalho(Integer.parseInt(request.getParameter("select_municipio_autuante")));
                autuante.setPostoTrabalho(postoTrabalho);

                autuanteDAO.update(autuante);
                request.setAttribute("message", "Autuante editado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuante/autuante_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("eliminar")) {
                autuanteDAO.delete(autuante);

                request.setAttribute("message", "Autuante eliminado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuante/autuante_listar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("prepara_editar")) {
                autuante = autuanteDAO.findById(autuante.getIdAutuante());
                request.setAttribute("autuante", autuante);
                RequestDispatcher rd = request.getRequestDispatcher("/paginas/autuante/autuante_editar.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/autuante/autuante_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                autuante = autuanteDAO.findById(autuante.getIdAutuante());
                request.setAttribute("autuante", autuante);
                RequestDispatcher rd = request.getRequestDispatcher("paginas/autuante/autuante_detalhes.jsp");
                rd.forward(request, response);

            } else if (comando.equalsIgnoreCase("pesquisa_dinamica_nome")) {

                String nome = request.getParameter("nome_autuante");

                if (nome == null) {
                    nome = "";
                }

                List<Autuante> autuantes = autuanteDAO.findByNome(nome);

                request.setAttribute("listaAutuantes", autuantes);

                RequestDispatcher dispatcher = request.getRequestDispatcher(
                        "paginas/autuante/autuante_tabela.jsp"
                );

                dispatcher.forward(request, response);
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
            Logger.getLogger(AutuanteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AutuanteServlet.class.getName()).log(Level.SEVERE, null, ex);
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
