<%-- 
    Document   : autuante_listar_por_nip
    Created on : 16/03/2019, 08:12:27
    Author     : Nicolau Alfredo
--%>

<%@page import="java.net.URLEncoder"%>
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

            String nipTexto = request.getParameter("nip_autuante");
            String paginaParametro = request.getParameter("pagina");

            if (nipTexto == null) {
                nipTexto = "";
            }

            Integer nip = null;

            if (!nipTexto.trim().isEmpty()) {
                try {
                    nip = Integer.parseInt(nipTexto.trim());
                } catch (NumberFormatException ex) {
                    nip = null;
                }
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

            int quantidadePaginas = autuanteDAO.quantidadePaginasPorNip(nip);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Autuante> autuantes = autuanteDAO.consultarPaginaPorNip(
                    nip,
                    String.valueOf(paginaActual)
            );

            request.setAttribute("listaAutuantes", autuantes);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String nipUrl = URLEncoder.encode(nipTexto, "UTF-8");
        %>

        <div class="container">
            <%@include file="../../menus/cabecalho.jsp" %>

            <h1 class="page-header text-primary">
                Autuante
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
                            <span class="glyphicon glyphicon-search"> Pesquisar </span>
                            <span class="caret"></span>
                        </button>

                        <ul class="dropdown-menu">
                            <li>
                                <a href="paginas/autuante/autuante_listar.jsp">
                                    <span class="glyphicon glyphicon-list-alt"> Listar Todos </span>
                                </a>
                            </li>

                            <li>
                                <a href="paginas/autuante/autuante_listar_por_nome.jsp">
                                    Nome Autuante
                                </a>
                            </li>

                            <li>
                                <a href="paginas/autuante/autuante_listar_por_bi.jsp">
                                    Nº do B.I.
                                </a>
                            </li>

                            <li>
                                <a href="paginas/autuante/autuante_listar_por_data.jsp">
                                    Data de Nascimento
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="panel-body">
                    <form action="paginas/autuante/autuante_listar_por_nip.jsp" method="get">
                        <div class="form-group input-group">
                            <input
                                type="search"
                                name="nip_autuante"
                                class="form-control"
                                placeholder="NIP do autuante"
                                value="<%=nipTexto%>"
                                >

                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>

                    <div class="table-responsive">
                        <%@include file="autuante_tabela.jsp" %>

                        <div class="text-center">
                            <ul class="pagination">

                                <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                    <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "paginas/autuante/autuante_listar_por_numero.jsp?nip_autuante=" + nipUrl + "&pagina=" + paginaAnterior%>">
                                        &laquo;
                                    </a>
                                </li>

                                <%
                                    for (int i = 1; i <= quantidadePaginas; i++) {
                                %>
                                <li class="<%=i == paginaActual ? "active" : ""%>">
                                    <a href="paginas/autuante/autuante_listar_por_numero.jsp?nip_autuante=<%=nipUrl%>&pagina=<%=i%>">
                                        <%=i%>
                                    </a>
                                </li>
                                <%
                                    }
                                %>

                                <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                    <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "paginas/autuante/autuante_listar_por_numero.jsp?nip_autuante=" + nipUrl + "&pagina=" + proximaPagina%>">
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