<%-- 
    Document   : provincia_listar
    Created on : 17/01/2019, 10:02:42
    Author     : user
--%>

<%@page import="modelo.Provincia"%>
<%@page import="dao.ProvinciaDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/">

        <title>Província</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%
            ProvinciaDAO provinciaDAO = new ProvinciaDAO();

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

            int quantidadePaginas = provinciaDAO.quantidadePaginas();

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Provincia> provincias = provinciaDAO.consultarPagina(String.valueOf(paginaActual));

            request.setAttribute("provincias", provincias);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>

                        <h1 class="page-header text-primary" title="Registar província">
                            <a href="paginas/provincia/provincia_registo.jsp">Província</a>
                        </h1>

                        <%                            String message = (String) request.getAttribute("message");

                            if (message != null && !message.trim().isEmpty()) {
                        %>
                        <div class="alert alert-info">
                            <p><%=message%></p>
                        </div>
                        <% }%>
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
                                        <a href="listaProvincias">
                                            <span class="glyphicon glyphicon-print"> Imprimir </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/provincia/provincia_listar_por_nome.jsp">
                                            <span class="glyphicon glyphicon-search"> Pesquisar </span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="panel-body">
                            <form>
                                <div class="table-responsive">

                                    <%@include file="provincia_tabela.jsp" %>

                                    <div class="text-center">
                                        <ul class="pagination">

                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                                <a href="<%=paginaActual <= 1
                                                        ? "javascript:void(0)"
                                                        : "paginas/provincia/provincia_listar.jsp?pagina=" + paginaAnterior%>">
                                                    &laquo;
                                                </a>
                                            </li>

                                            <%
                                                for (int i = 1; i <= quantidadePaginas; i++) {
                                            %>
                                            <li class="<%=i == paginaActual ? "active" : ""%>">
                                                <a href="paginas/provincia/provincia_listar.jsp?pagina=<%=i%>">
                                                    <%=i%>
                                                </a>
                                            </li>
                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                                <a href="<%=paginaActual >= quantidadePaginas
                                                        ? "javascript:void(0)"
                                                        : "paginas/provincia/provincia_listar.jsp?pagina=" + proximaPagina%>">
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