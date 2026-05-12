<%-- 
    Document   : ocorrencia_listar
    Created on : 24/02/2019, 20:29:11
    Author     : user
--%>

<%@page import="modelo.Testemunha"%>
<%@page import="dao.OcorrenciaDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Ocorrencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Ocorrência</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

            String paginaParametro = request.getParameter("pagina");

            int paginaActual = 1;

            if (paginaParametro != null && !paginaParametro.trim().isEmpty()) {
                try {
                    paginaActual = Integer.parseInt(paginaParametro);
                } catch (NumberFormatException ex) {
                    paginaActual = 1;
                }
            }

            if (paginaActual < 1) {
                paginaActual = 1;
            }

            int quantidadePaginas = ocorrenciaDAO.quantidadePaginas();

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Ocorrencia> ocorrencias = ocorrenciaDAO.consultarPagina(String.valueOf(paginaActual));

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String urlListar = "paginas/ocorrencia/ocorrencia_listar.jsp";
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary" title="Registar ocorrência"><a href="paginas/ocorrencia/ocorrencia_registo.jsp">Ocorrência</a></h1>
                        <div class="alert alert-info">
                            <p>${message}</p>
                        </div>
                    </div>                 
                </div>
            </div>

            <!-- Linha de divisão -->
            <div class="row">
                <!-- Área da linha -->
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">                            
                            <!-- Botão Suspenso -->
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                    <span class="glyphicon glyphicon-menu-down"> Operações </span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="listaOcorrencias"> <span class="glyphicon glyphicon-print"> Imprimir </span> </a></li>
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_autuado.jsp"> <span class="glyphicon glyphicon-search"> Pesquisar </span> </a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <form>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-primary">#</th>
                                                <th class="text-primary">Data</th>
                                                <th class="text-primary">Hora</th>
                                                <th class="text-primary">Cidade</th>
                                                <th class="text-primary">Autuado</th>
                                                <th class="text-primary">Autuante</th>                                                
                                                <th class="text-primary">Tipo de Ocorrência</th>
                                                <th class="text-primary">Testemunha</th>
                                                <th class="text-primary" colspan="4">Operações</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%        if (ocorrencias == null || ocorrencias.isEmpty()) {
                                            %>
                                            <tr>
                                                <td colspan="12" class="text-center text-muted">
                                                    Nenhuma ocorrência encontrada.
                                                </td>
                                            </tr>
                                            <%
                                            } else {
                                                for (Ocorrencia ocorrencia : ocorrencias) {
                                            %>
                                            <tr>
                                                <td><%=ocorrencia.getIdOcorrencia()%></td>
                                                <td><%=DateUtil.formataData(ocorrencia.getDataOcorrencia())%></td>
                                                <td><%=ocorrencia.getHoraOcorrencia()%></td>
                                                <td><%=ocorrencia.getCidadeOcorrencia()%></td>

                                                <td>
                                                    <a href="autuadoServlet?comando=detalhes&id_autuado=<%=ocorrencia.getAutuado().getIdAutuado()%>">
                                                        <%=ocorrencia.getAutuado().getNomeAutuado()%>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="autuanteServlet?comando=detalhes&id_autuante=<%=ocorrencia.getAutuante().getIdAutuante()%>">
                                                        <%=ocorrencia.getAutuante().getNomeAutuante()%>
                                                    </a>
                                                </td>

                                                <td><%=ocorrencia.getTipoOcorrencia().getNomeTipoOcorrencia()%></td>

                                                <td>
                                                    <a href="testemunhaServlet?comando=detalhes&id_testemunha=<%=ocorrencia.getTestemunha().getIdTestemunha()%>">
                                                        <%=ocorrencia.getTestemunha().getNomeTestemunha()%>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="ocorrenciaComParametro?id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>" title="Imprimir">
                                                        <span class="glyphicon glyphicon-print"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="ocorrenciaServlet?comando=detalhes&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>" title="Detalhes">
                                                        <span class="glyphicon glyphicon-zoom-in"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="ocorrenciaServlet?comando=prepara_editar&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>" title="Editar dados">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="ocorrenciaServlet?comando=eliminar&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>"
                                                       title="Eliminar"
                                                       onclick="return confirm('Deseja realmente eliminar esta ocorrência?');">
                                                        <span class="glyphicon glyphicon-trash"></span>
                                                    </a>
                                                </td>
                                            </tr>
                                            <%
                                                    }
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                    <!-- Paginação -->
                                    <div class="text-center">
                                        <ul class="pagination">

                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                                <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : urlListar + "?pagina=" + paginaAnterior%>">
                                                    &laquo;
                                                </a>
                                            </li>

                                            <%
                                                for (int i = 1; i <= quantidadePaginas; i++) {
                                            %>
                                            <li class="<%=i == paginaActual ? "active" : ""%>">
                                                <a href="<%=urlListar%>?pagina=<%=i%>"><%=i%></a>
                                            </li>
                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                                <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : urlListar + "?pagina=" + proximaPagina%>">
                                                    &raquo;
                                                </a>
                                            </li>

                                        </ul>

                                        <p class="text-muted">
                                            Página <%=paginaActual%> de <%=quantidadePaginas%>
                                        </p>
                                    </div>
                                    <!-- Fim da Paginação-->
                                </div> 
                            </form>
                        </div>
                    </div>                   
                </div> 
                <!-- Fim da área da linha-->

                <!-- Rodapé -->
                <%@include file="../../menus/rodape.jsp" %>
                <!-- Fim do Rodapé-->

            </div>
            <!-- Fim da linha de divisão -->
        </div>
        <!-- Fim do Container -->
    </body>
</html>
