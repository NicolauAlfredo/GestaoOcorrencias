/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlo;

import dao.AutuanteDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
import modelo.Sexo;

/**
 *
 * @author user
 */
@WebServlet(name = "AutuanteServlet", urlPatterns = {"/autuanteServlet"})
public class AutuanteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        response.setContentType("text/html;charset=UTF-8");

        String comando = request.getParameter("comando");

        if (comando == null) {
            comando = "principal";
        }

        AutuanteDAO autuanteDAO = new AutuanteDAO();

        if (comando.equalsIgnoreCase("pesquisar_ajax")) {
            pesquisarAjax(request, response, autuanteDAO);
            return;
        }

        Autuante autuante = new Autuante();

        if (!comando.equalsIgnoreCase("principal")) {
            try {
                String idAutuante = request.getParameter("id_autuante");

                if (idAutuante != null && !idAutuante.trim().isEmpty()) {
                    autuante.setIdAutuante(Integer.parseInt(idAutuante));
                }

            } catch (NumberFormatException ex) {
                System.err.println("Erro ao converter id do autuante: " + ex.getMessage());
            }
        }

        try {
            if (comando.equalsIgnoreCase("guardar")) {
                preencherAutuanteComParametros(request, autuante);

                autuanteDAO.save(autuante);

                request.setAttribute("message", "Autuante cadastrado!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuante/autuante_registo.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("editar")) {
                autuante.setIdAutuante(Integer.parseInt(request.getParameter("id_autuante")));
                preencherAutuanteComParametros(request, autuante);

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
                RequestDispatcher dispatcher = request.getRequestDispatcher("/paginas/autuante/autuante_editar.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("listar")) {
                response.sendRedirect("paginas/autuante/autuante_listar.jsp");

            } else if (comando.equalsIgnoreCase("detalhes")) {
                autuante = autuanteDAO.findById(autuante.getIdAutuante());

                request.setAttribute("autuante", autuante);
                RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/autuante/autuante_detalhes.jsp");
                dispatcher.forward(request, response);

            } else if (comando.equalsIgnoreCase("principal")) {
                response.sendRedirect("paginas/index.jsp");
            }

        } catch (IOException | ServletException ex) {
            System.err.println("Erro na leitura dos dados: " + ex.getMessage());
        }
    }

    private void preencherAutuanteComParametros(HttpServletRequest request, Autuante autuante)
            throws ParseException {

        autuante.setNomeAutuante(request.getParameter("nome_autuante"));
        autuante.setPaiAutuante(request.getParameter("pai_autuante"));
        autuante.setMaeAutuante(request.getParameter("mae_autuante"));
        autuante.setBiAutuante(request.getParameter("bi_autuante"));
        autuante.setResidenciaAutuante(request.getParameter("residencia_autuante"));
        autuante.setDataNascimentoAutuante(DateUtil.strToDate(request.getParameter("data_nascimento_autuante")));
        autuante.setSexo(Sexo.getExtensao(request.getParameter("sexo_autuante")));

        String altura = request.getParameter("altura_autuante");

        if (altura != null && !altura.trim().isEmpty()) {
            autuante.setAlturaAutuante(Double.parseDouble(altura));
        }

        autuante.setDataEmissaoBiAutuante(DateUtil.strToDate(request.getParameter("data_emissao_bi_autuante")));
        autuante.setDataValidadeBiAutuante(DateUtil.strToDate(request.getParameter("data_validade_bi_autuante")));

        String nip = request.getParameter("nip_autuante");

        if (nip != null && !nip.trim().isEmpty()) {
            autuante.setNipAutuante(Integer.parseInt(nip));
        }

        autuante.setTelefoneAutuante(request.getParameter("telefone_autuante"));

        Patente patente = new Patente();
        patente.setIdPatente(Integer.parseInt(request.getParameter("select_patente_autuante")));
        autuante.setPatente(patente);

        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(Integer.parseInt(request.getParameter("select_municipio_autuante")));
        autuante.setMunicipio(municipio);

        PostoTrabalho postoTrabalho = new PostoTrabalho();
        postoTrabalho.setIdPostoTrabalho(Integer.parseInt(request.getParameter("select_posto_trabalho_autuante")));
        autuante.setPostoTrabalho(postoTrabalho);
    }

    private void pesquisarAjax(
            HttpServletRequest request,
            HttpServletResponse response,
            AutuanteDAO autuanteDAO
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

        List<Autuante> autuantes;

        if (tipoPesquisa.equalsIgnoreCase("bi")) {
            autuantes = autuanteDAO.consultarPaginaPorBi(termo, pagina);

        } else if (tipoPesquisa.equalsIgnoreCase("data")) {
            try {
                if (termo.trim().isEmpty()) {
                    autuantes = autuanteDAO.consultarPagina(pagina);
                } else {
                    java.sql.Date data = DateUtil.strToDate(termo);
                    autuantes = autuanteDAO.consultarPaginaPorData(data, pagina);
                }
            } catch (Exception ex) {
                autuantes = new java.util.ArrayList<Autuante>();
            }

        } else if (tipoPesquisa.equalsIgnoreCase("nip")) {
            try {
                if (termo.trim().isEmpty()) {
                    autuantes = autuanteDAO.consultarPagina(pagina);
                } else {
                    autuantes = autuanteDAO.consultarPaginaPorNip(
                            Integer.parseInt(termo.trim()),
                            pagina
                    );
                }
            } catch (Exception ex) {
                autuantes = new java.util.ArrayList<Autuante>();
            }

        } else {
            autuantes = autuanteDAO.consultarPaginaPorNome(termo, pagina);
        }

        PrintWriter out = response.getWriter();

        if (autuantes == null || autuantes.isEmpty()) {
            out.println("<tr>");
            out.println("<td colspan='12' class='text-center text-muted'>Nenhum autuante encontrado.</td>");
            out.println("</tr>");
            return;
        }

        for (Autuante autuante : autuantes) {
            out.println("<tr>");

            out.println("<td>" + autuante.getIdAutuante() + "</td>");
            out.println("<td>" + valorSeguro(autuante.getNomeAutuante()) + "</td>");
            out.println("<td>" + valorSeguro(autuante.getBiAutuante()) + "</td>");
            out.println("<td>" + valorSeguro(autuante.getResidenciaAutuante()) + "</td>");
            out.println("<td>" + DateUtil.formataData(autuante.getDataNascimentoAutuante()) + "</td>");

            if (autuante.getSexo() != null) {
                out.println("<td>" + autuante.getSexo().getExtensao() + "</td>");
            } else {
                out.println("<td></td>");
            }

            out.println("<td>" + valorSeguro(autuante.getTelefoneAutuante()) + "</td>");

            if (autuante.getPatente() != null) {
                out.println("<td>" + valorSeguro(autuante.getPatente().getNomePatente()) + "</td>");
            } else {
                out.println("<td></td>");
            }

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/autuanteServlet?comando=detalhes&id_autuante=" + autuante.getIdAutuante() + "'>");
            out.println("<span class='glyphicon glyphicon-print'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/autuanteServlet?comando=detalhes&id_autuante=" + autuante.getIdAutuante() + "'>");
            out.println("<span class='glyphicon glyphicon-zoom-in'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a href='" + request.getContextPath() + "/autuanteServlet?comando=prepara_editar&id_autuante=" + autuante.getIdAutuante() + "'>");
            out.println("<span class='glyphicon glyphicon-edit'></span>");
            out.println("</a>");
            out.println("</td>");

            out.println("<td>");
            out.println("<a onclick=\"return confirm('Deseja realmente eliminar este autuante?');\" href='" + request.getContextPath() + "/autuanteServlet?comando=eliminar&id_autuante=" + autuante.getIdAutuante() + "'>");
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
