<%-- 
    Document   : autuante_listar
    Created on : 24/02/2019, 12:16:37
    Author     : user
--%>

<%@page import="modelo.Autuante"%>
<%@page import="dao.AutuanteDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/">

        <title>Autuante</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%
            AutuanteDAO autuanteDAO = new AutuanteDAO();

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

            int quantidadePaginas = autuanteDAO.quantidadePaginas();

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Autuante> autuantes = autuanteDAO.consultarPagina(String.valueOf(paginaActual));

            request.setAttribute("listaAutuantes", autuantes);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;
        %>

        <div class="container">
            <%@include file="../../menus/cabecalho.jsp" %>

            <h1 class="page-header text-primary" title="Registar autuante">
                <a href="paginas/autuante/autuante_registo.jsp">Autuante</a>
            </h1>

            <%                String message = (String) request.getAttribute("message");

                if (message != null && !message.trim().isEmpty()) {
            %>
            <div class="alert alert-info">
                <p><%=message%></p>
            </div>
            <% }%>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-menu-down"> Operações </span>
                            <span class="caret"></span>
                        </button>

                        <ul class="dropdown-menu">
                            <li>
                                <a href="autuantes">
                                    <span class="glyphicon glyphicon-print"> Imprimir </span>
                                </a>
                            </li>

                            <li>
                                <a href="paginas/autuante/autuante_listar_por_nome.jsp">
                                    <span class="glyphicon glyphicon-search"> Pesquisar por Nome </span>
                                </a>
                            </li>

                            <li>
                                <a href="paginas/autuante/autuante_listar_por_bi.jsp">
                                    <span class="glyphicon glyphicon-search"> Pesquisar por B.I. </span>
                                </a>
                            </li>

                            <li>
                                <a href="paginas/autuante/autuante_listar_por_data.jsp">
                                    <span class="glyphicon glyphicon-search"> Pesquisar por Data </span>
                                </a>
                            </li>

                            <li>
                                <a href="paginas/autuante/autuante_listar_por_nip.jsp">
                                    <span class="glyphicon glyphicon-search"> Pesquisar por NIP </span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="panel-body">
                    <div class="table-responsive">
                        <%@include file="autuante_tabela.jsp" %>

                        <div class="text-center">
                            <ul class="pagination">

                                <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                    <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "paginas/autuante/autuante_listar.jsp?pagina=" + paginaAnterior%>">
                                        &laquo;
                                    </a>
                                </li>

                                <%
                                    for (int i = 1; i <= quantidadePaginas; i++) {
                                %>
                                <li class="<%=i == paginaActual ? "active" : ""%>">
                                    <a href="paginas/autuante/autuante_listar.jsp?pagina=<%=i%>">
                                        <%=i%>
                                    </a>
                                </li>
                                <%
                                    }
                                %>

                                <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                    <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "paginas/autuante/autuante_listar.jsp?pagina=" + proximaPagina%>">
                                        &raquo;
                                    </a>
                                </li>

                            </ul>

                            <p class="text-muted">
                                Página <%=paginaActual%> de <%=quantidadePaginas%>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <%@include file="../../menus/rodape.jsp" %>
            </div>
        </div>
    </body>
</html>