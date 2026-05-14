<%-- 
    Document   : posto_trabalho_listar
    Created on : 26/02/2019, 14:08:39
    Author     : user
--%>

<%@page import="modelo.PostoTrabalho"%>
<%@page import="java.util.List"%>
<%@page import="dao.PostoTrabalhoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Posto de Trabalho</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%
            PostoTrabalhoDAO postoTrabalhoDAO = new PostoTrabalhoDAO();

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

            int quantidadePaginas = postoTrabalhoDAO.quantidadePaginas();

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<PostoTrabalho> postoTrabalhos = postoTrabalhoDAO.consultarPagina(
                    String.valueOf(paginaActual)
            );

            request.setAttribute("postoTrabalhos", postoTrabalhos);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>

                        <h1 class="page-header text-primary" title="Registar posto trabalho">
                            <a href="paginas/posto/posto_trabalho_registo.jsp">
                                Posto de Trabalho
                            </a>
                        </h1>

                        <div class="alert alert-info">
                            <p>${message}</p>
                        </div>
                    </div>                 
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                    <span class="glyphicon glyphicon-menu-down"> Operações </span>
                                    <span class="caret"></span>
                                </button>

                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="listaOcorrencias">
                                            <span class="glyphicon glyphicon-print"> Imprimir </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/posto/posto_trabalho_listar_por_nome.jsp">
                                            <span class="glyphicon glyphicon-search"> Pesquisar por Nome </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/posto/posto_trabalho_listar_por_numero.jsp">
                                            <span class="glyphicon glyphicon-search"> Pesquisar por Número </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/posto/posto_trabalho_listar_por_municipio.jsp">
                                            <span class="glyphicon glyphicon-search"> Pesquisar por Município </span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="panel-body">
                            <form>
                                <div class="table-responsive">
                                    <%@include file="posto_trabalho_tabela.jsp" %>

                                    <div class="text-center">
                                        <ul class="pagination">

                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                                <a href="<%=paginaActual <= 1
                                                        ? "javascript:void(0)"
                                                        : "paginas/posto/posto_trabalho_listar.jsp?pagina=" + paginaAnterior%>">
                                                    &laquo;
                                                </a>
                                            </li>

                                            <%
                                                for (int i = 1; i <= quantidadePaginas; i++) {
                                            %>
                                            <li class="<%=i == paginaActual ? "active" : ""%>">
                                                <a href="paginas/posto/posto_trabalho_listar.jsp?pagina=<%=i%>">
                                                    <%=i%>
                                                </a>
                                            </li>
                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                                <a href="<%=paginaActual >= quantidadePaginas
                                                        ? "javascript:void(0)"
                                                        : "paginas/posto/posto_trabalho_listar.jsp?pagina=" + proximaPagina%>">
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

                <%@include file="../../menus/rodape.jsp" %>
            </div>
        </div>
    </body>
</html>