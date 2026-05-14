<%-- 
    Document   : provincia_listar_por_nome
    Created on : 16/03/2019, 08:40:37
    Author     : user
--%>

<%@page import="java.net.URLEncoder"%>
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

            String nome = request.getParameter("nome_provincia");
            String paginaParametro = request.getParameter("pagina");

            if (nome == null) {
                nome = "";
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

            int quantidadePaginas = provinciaDAO.quantidadePaginasPorNome(nome);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Provincia> provincias = provinciaDAO.consultarPaginaPorNome(
                    nome,
                    String.valueOf(paginaActual)
            );

            request.setAttribute("provincias", provincias);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String nomeUrl = URLEncoder.encode(nome, "UTF-8");
        %>

        <div class="container">

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">

                        <%@include file="../../menus/cabecalho.jsp" %>

                        <h1 class="page-header text-primary">
                            Província
                        </h1>

                        <%
                            String message = (String) request.getAttribute("message");

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

                                    <span class="glyphicon glyphicon-search">
                                        Pesquisar
                                    </span>

                                </button>
                            </div>

                        </div>

                        <div class="panel-body">

                            <form action="paginas/provincia/provincia_listar_por_nome.jsp" method="GET">

                                <div class="form-group input-group">

                                    <input
                                        type="search"
                                        name="nome_provincia"
                                        id="pesquisa_provincia"
                                        class="form-control"
                                        placeholder="Nome"
                                        value="<%=nome%>"
                                        autocomplete="off">

                                    <span class="input-group-btn">

                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>

                                    </span>

                                </div>

                            </form>

                            <form>

                                <div class="table-responsive">

                                    <%@include file="provincia_tabela.jsp" %>

                                    <div class="text-center">

                                        <ul class="pagination">

                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">

                                                <a href="<%=paginaActual <= 1
                                                        ? "javascript:void(0)"
                                                        : "paginas/provincia/provincia_listar_por_nome.jsp?nome_provincia="
                                                        + nomeUrl
                                                        + "&pagina="
                                                        + paginaAnterior%>">

                                                    &laquo;

                                                </a>

                                            </li>

                                            <%
                                                for (int i = 1; i <= quantidadePaginas; i++) {
                                            %>

                                            <li class="<%=i == paginaActual ? "active" : ""%>">

                                                <a href="paginas/provincia/provincia_listar_por_nome.jsp?nome_provincia=<%=nomeUrl%>&pagina=<%=i%>">

                                                    <%=i%>

                                                </a>

                                            </li>

                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">

                                                <a href="<%=paginaActual >= quantidadePaginas
                                                        ? "javascript:void(0)"
                                                        : "paginas/provincia/provincia_listar_por_nome.jsp?nome_provincia="
                                                        + nomeUrl
                                                        + "&pagina="
                                                        + proximaPagina%>">

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

        <script type="text/javascript">

            $(document).ready(function () {

                var tempoEspera = null;

                $("#pesquisa_provincia").keyup(function () {

                    clearTimeout(tempoEspera);

                    var termo = $(this).val();

                    tempoEspera = setTimeout(function () {

                        $.ajax({

                            url: "provinciaServlet",

                            type: "GET",

                            data: {
                                comando: "pesquisar_ajax",
                                termo: termo
                            },

                            success: function (resultado) {

                                $("#resultado-provincias").html(resultado);

                            },

                            error: function () {

                                $("#resultado-provincias").html(
                                        "<tr>" +
                                        "<td colspan='6' class='text-center text-danger'>" +
                                        "Erro ao pesquisar províncias." +
                                        "</td>" +
                                        "</tr>"
                                        );

                            }

                        });

                    }, 300);

                });

            });

        </script>

    </body>
</html>