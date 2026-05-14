<%-- 
    Document   : provincia_listar
    Created on : 17/01/2019, 10:02:42
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

            String termo = request.getParameter("termo");
            String paginaParametro = request.getParameter("pagina");

            if (termo == null) {
                termo = "";
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

            int quantidadePaginas;
            List<Provincia> provincias;

            if (termo.trim().isEmpty()) {
                quantidadePaginas = provinciaDAO.quantidadePaginas();
                provincias = provinciaDAO.consultarPagina(String.valueOf(paginaActual));
            } else {
                quantidadePaginas = provinciaDAO.quantidadePaginasPorNome(termo);
                provincias = provinciaDAO.consultarPaginaPorNome(termo, String.valueOf(paginaActual));
            }

            if (quantidadePaginas < 1) {
                quantidadePaginas = 1;
            }

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            request.setAttribute("provincias", provincias);

            String termoUrl = URLEncoder.encode(termo, "UTF-8");
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>

                        <h1 class="page-header text-primary" title="Registar província">
                            <a href="paginas/provincia/provincia_registo.jsp">
                                Província
                            </a>
                        </h1>

                        <% String message = (String) request.getAttribute("message");
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
                        <div class="panel-body">

                            <form action="paginas/provincia/provincia_listar.jsp" method="GET">
                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        name="termo"
                                        id="pesquisa_provincia"
                                        class="form-control"
                                        placeholder="Pesquisar província"
                                        autocomplete="off"
                                        value="<%=termo%>">

                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                            </form>

                            <div class="table-responsive">
                                <div id="resultado-provincias-wrapper">
                                    <%@include file="provincia_tabela.jsp" %>

                                    <%  request.setAttribute("paginaActual", paginaActual);
                                        request.setAttribute("quantidadePaginas", quantidadePaginas);
                                        request.setAttribute("urlBase", "paginas/provincia/provincia_listar.jsp");
                                        request.setAttribute("queryStringExtra", "termo=" + termoUrl);
                                    %>

                                    <%@include file="../../components/paginacao.jsp" %>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <%@include file="../../components/rodape.jsp" %>
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                var tempoEspera = null;

                function pesquisarProvincias(pagina) {
                    var termo = $("#pesquisa_provincia").val();

                    $("#resultado-provincias-wrapper").load(
                            "paginas/provincia/provincia_listar.jsp?termo="
                            + encodeURIComponent(termo)
                            + "&pagina=" + encodeURIComponent(pagina)
                            + " #resultado-provincias-wrapper > *"
                            );
                }

                $("#pesquisa_provincia").keyup(function () {
                    clearTimeout(tempoEspera);

                    tempoEspera = setTimeout(function () {
                        pesquisarProvincias(1);
                    }, 300);
                });
            });
        </script>
    </body>
</html>