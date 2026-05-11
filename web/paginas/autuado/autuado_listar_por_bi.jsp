<%-- 
    Document   : autuante_listar_por_bi
    Created on : 14/03/2019, 20:40:43
    Author     : user
--%>

<%@page import="modelo.Autuado"%>
<%@page import="dao.AutuadoDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autuado</title>

        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </head>

    <body>
        <%
            AutuadoDAO autuadoDAO = new AutuadoDAO();

            String bi = request.getParameter("bi_autuado");
            String paginaParametro = request.getParameter("pagina");

            if (bi == null) {
                bi = "";
            }

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

            int quantidadePaginas = autuadoDAO.quantidadePaginasPorBi(bi);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Autuado> autuados = autuadoDAO.consultarPaginaPorBi(bi, String.valueOf(paginaActual));

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String biUrl = java.net.URLEncoder.encode(bi, "UTF-8");
        %>

        <div class="container">
            <%@include file="../../menus/cabecalho.jsp" %>

            <h1 class="page-header text-primary">Autuado</h1>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-search"> Pesquisar </span>
                            <span class="caret"></span>
                        </button>

                        <ul class="dropdown-menu">
                            <li><a href="autuado_listar.jsp">Listar Todos</a></li>
                            <li><a href="autuado_listar_por_nome.jsp">Nome Autuado</a></li>
                            <li><a href="autuado_listar_por_data.jsp">Data de Nascimento</a></li>
                        </ul>
                    </div>
                </div>

                <div class="panel-body">
                    <form action="autuado_listar_por_bi.jsp" method="get">
                        <div class="form-group input-group">
                            <input type="search" name="bi_autuado" class="form-control" placeholder="Nº. Bilhete de Identidade" value="<%=bi%>">

                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>

                    <div class="table-responsive">
                        <%
                            request.setAttribute("autuados", autuados);
                        %>

                        <%@include file="autuado_tabela.jsp" %>

                        <div class="text-center">
                            <ul class="pagination">
                                <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                    <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "autuado_listar_por_bi.jsp?bi_autuado=" + biUrl + "&pagina=" + paginaAnterior%>">
                                        &laquo;
                                    </a>
                                </li>

                                <%
                                    for (int i = 1; i <= quantidadePaginas; i++) {
                                %>
                                <li class="<%=i == paginaActual ? "active" : ""%>">
                                    <a href="autuado_listar_por_bi.jsp?bi_autuado=<%=biUrl%>&pagina=<%=i%>"><%=i%></a>
                                </li>
                                <%
                                    }
                                %>

                                <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                    <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "autuado_listar_por_bi.jsp?bi_autuado=" + biUrl + "&pagina=" + proximaPagina%>">
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