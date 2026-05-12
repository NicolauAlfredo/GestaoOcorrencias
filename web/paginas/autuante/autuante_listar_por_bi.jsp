<%-- 
    Document   : autuante_listar_por_bi
    Created on : 14/03/2019, 20:40:43
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

            String bi = request.getParameter("bi_autuante");
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

            int quantidadePaginas = autuanteDAO.quantidadePaginasPorBi(bi);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Autuante> autuantes = autuanteDAO.consultarPaginaPorBi(
                    bi,
                    String.valueOf(paginaActual)
            );

            request.setAttribute("listaAutuantes", autuantes);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String biUrl = URLEncoder.encode(bi, "UTF-8");
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
                                <a href="paginas/autuante/autuante_listar_por_data.jsp">
                                    Data de Nascimento
                                </a>
                            </li>

                            <li>
                                <a href="paginas/autuante/autuante_listar_por_nip.jsp">
                                    NIP
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="panel-body">
                    <form action="paginas/autuante/autuante_listar_por_bi.jsp" method="get">
                        <div class="form-group input-group">
                            <input
                                type="search"
                                id="bi_autuante"
                                name="bi_autuante"
                                class="form-control"
                                placeholder="Nº. Bilhete de Identidade"
                                value="<%=bi%>"
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
                                    <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "paginas/autuante/autuante_listar_por_bi.jsp?bi_autuante=" + biUrl + "&pagina=" + paginaAnterior%>">
                                        &laquo;
                                    </a>
                                </li>

                                <%
                                    for (int i = 1; i <= quantidadePaginas; i++) {
                                %>
                                <li class="<%=i == paginaActual ? "active" : ""%>">
                                    <a href="paginas/autuante/autuante_listar_por_bi.jsp?bi_autuante=<%=biUrl%>&pagina=<%=i%>">
                                        <%=i%>
                                    </a>
                                </li>
                                <%
                                    }
                                %>

                                <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                    <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "paginas/autuante/autuante_listar_por_bi.jsp?bi_autuante=" + biUrl + "&pagina=" + proximaPagina%>">
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

        <script type="text/javascript">
            $(document).ready(function () {
                $("#bi_autuante").keyup(function () {
                    var termo = $(this).val();

                    $.ajax({
                        url: "autuanteServlet",
                        type: "GET",
                        data: {
                            comando: "pesquisar_ajax",
                            tipo_pesquisa: "bi",
                            termo: termo
                        },
                        success: function (resultado) {
                            $("#resultado-autuantes").html(resultado);
                            $(".pagination").hide();
                        },
                        error: function () {
                            $("#resultado-autuantes").html(
                                    "<tr><td colspan='12' class='text-center text-danger'>Erro ao pesquisar autuantes.</td></tr>"
                                    );
                        }
                    });
                });
            });
        </script>
    </body> 
</html>