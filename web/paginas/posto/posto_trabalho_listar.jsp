<%-- 
    Document   : posto_trabalho_listar
    Created on : 26/02/2019, 14:08:39
    Author     : user
--%>

<%@page import="java.net.URLEncoder"%>
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

            String tipoPesquisa = request.getParameter("tipo_pesquisa");
            String termo = request.getParameter("termo");
            String paginaParametro = request.getParameter("pagina");

            if (tipoPesquisa == null || tipoPesquisa.trim().isEmpty()) {
                tipoPesquisa = "nome";
            }

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
            List<PostoTrabalho> postoTrabalhos;

            if (tipoPesquisa.equalsIgnoreCase("municipio")) {
                quantidadePaginas = postoTrabalhoDAO.quantidadePaginasPorMunicipio(termo);
                postoTrabalhos = postoTrabalhoDAO.consultarPaginaPorMunicipio(termo, String.valueOf(paginaActual));

            } else if (tipoPesquisa.equalsIgnoreCase("numero")) {
                try {
                    if (termo.trim().isEmpty()) {
                        quantidadePaginas = postoTrabalhoDAO.quantidadePaginas();
                        postoTrabalhos = postoTrabalhoDAO.consultarPagina(String.valueOf(paginaActual));
                    } else {
                        Integer numero = Integer.parseInt(termo.trim());
                        quantidadePaginas = postoTrabalhoDAO.quantidadePaginasPorNumero(numero);
                        postoTrabalhos = postoTrabalhoDAO.consultarPaginaPorNumero(numero, String.valueOf(paginaActual));
                    }
                } catch (NumberFormatException ex) {
                    quantidadePaginas = 1;
                    postoTrabalhos = new java.util.ArrayList<PostoTrabalho>();
                }

            } else {
                quantidadePaginas = postoTrabalhoDAO.quantidadePaginasPorNome(termo);
                postoTrabalhos = postoTrabalhoDAO.consultarPaginaPorNome(termo, String.valueOf(paginaActual));
            }

            if (quantidadePaginas < 1) {
                quantidadePaginas = 1;
            }

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            request.setAttribute("postoTrabalhos", postoTrabalhos);

            String tipoPesquisaUrl = URLEncoder.encode(tipoPesquisa, "UTF-8");
            String termoUrl = URLEncoder.encode(termo, "UTF-8");
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>

                        <h1 class="page-header text-primary" title="Registar posto trabalho">
                            <a href="paginas/posto/posto_trabalho_registo.jsp">
                                Posto de Trabalho
                            </a>
                        </h1>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">

                            <form action="paginas/posto/posto_trabalho_listar.jsp" method="GET">
                                <div class="form-group">
                                    <select
                                        name="tipo_pesquisa"
                                        id="tipo_pesquisa_posto"
                                        class="form-control input-sm"
                                        style="max-width: 220px; background-color: #337ab7; color: white; border-color: #2e6da4;">

                                        <option value="nome" <%= "nome".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Nome</option>
                                        <option value="numero" <%= "numero".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Número</option>
                                        <option value="municipio" <%= "municipio".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Município</option>
                                    </select>
                                </div>

                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        name="termo"
                                        id="pesquisa_posto"
                                        class="form-control"
                                        placeholder="Pesquisar posto de trabalho"
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
                                <%@include file="posto_trabalho_tabela.jsp" %>

                                <%                                    request.setAttribute("paginaActual", paginaActual);
                                    request.setAttribute("quantidadePaginas", quantidadePaginas);
                                    request.setAttribute("urlBase", "paginas/posto/posto_trabalho_listar.jsp");
                                    request.setAttribute(
                                            "queryStringExtra",
                                            "tipo_pesquisa=" + tipoPesquisaUrl + "&termo=" + termoUrl
                                    );
                                %>

                                <%@include file="../../components/paginacao.jsp" %>
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

                function pesquisarPostos(pagina) {
                    var tipoPesquisa = $("#tipo_pesquisa_posto").val();
                    var termo = $("#pesquisa_posto").val();

                    $("#resultado-postos-trabalho").load(
                            "postoTrabalhoServlet?comando=pesquisar_ajax"
                            + "&tipo_pesquisa=" + encodeURIComponent(tipoPesquisa)
                            + "&termo=" + encodeURIComponent(termo)
                            + "&pagina=" + encodeURIComponent(pagina)
                            );
                }

                $("#pesquisa_posto").keyup(function () {
                    clearTimeout(tempoEspera);

                    tempoEspera = setTimeout(function () {
                        pesquisarPostos(1);
                    }, 300);
                });

                $("#tipo_pesquisa_posto").change(function () {
                    pesquisarPostos(1);
                });
            });
        </script>
    </body>
</html>