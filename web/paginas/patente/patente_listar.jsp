<%-- 
    Document   : patente_listar
    Created on : 24/02/2019, 21:08:46
    Author     : user
--%>

<%@page import="modelo.Patente"%>
<%@page import="java.util.List"%>
<%@page import="dao.PatenteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Patente</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            PatenteDAO patenteDAO = new PatenteDAO();

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

            int quantidadePaginas = patenteDAO.quantidadePaginas();

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Patente> patentes = patenteDAO.consultarPagina(
                    String.valueOf(paginaActual)
            );

            request.setAttribute("patentes", patentes);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary" title="Registar patente"><a href="paginas/patente/patente_registo.jsp">Patente</a></h1>
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
                                    <li><a href="listaPatentes"> <span class="glyphicon glyphicon-print"> Imprimir </span> </a></li>
                                    <li><a href="paginas/patente/patente_listar_por_nome.jsp"> <span class="glyphicon glyphicon-search"> Pesquisar </span> </a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <form>
                                <div id="resultado-patentes-wrapper">
                                    <%@include file="patente_tabela.jsp" %>


                                    <div class="text-center">

                                        <ul class="pagination">

                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                                <a href="<%=paginaActual <= 1
                                                        ? "javascript:void(0)"
                                                        : "paginas/patente/patente_listar.jsp?pagina=" + paginaAnterior%>">
                                                    &laquo;
                                                </a>
                                            </li>

                                            <%
                                                for (int i = 1; i <= quantidadePaginas; i++) {
                                            %>

                                            <li class="<%=i == paginaActual ? "active" : ""%>">
                                                <a href="paginas/patente/patente_listar.jsp?pagina=<%=i%>">
                                                    <%=i%>
                                                </a>
                                            </li>

                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                                <a href="<%=paginaActual >= quantidadePaginas
                                                        ? "javascript:void(0)"
                                                        : "paginas/patente/patente_listar.jsp?pagina=" + proximaPagina%>">
                                                    &raquo;
                                                </a>
                                            </li>

                                        </ul>

                                        <p class="text-muted">
                                            Página <%=paginaActual%> de <%=quantidadePaginas%>
                                        </p>

                                    </div>
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
