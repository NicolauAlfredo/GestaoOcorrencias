<%@page import="java.net.URLEncoder"%>
<%@page import="modelo.Municipio"%>
<%@page import="dao.MunicipioDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/">

        <title>Município</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%
            MunicipioDAO municipioDAO = new MunicipioDAO();

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
            List<Municipio> municipios;

            if (tipoPesquisa.equalsIgnoreCase("provincia")) {
                quantidadePaginas = municipioDAO.quantidadePaginasPorProvincia(termo);
                municipios = municipioDAO.consultarPaginaPorProvincia(termo, String.valueOf(paginaActual));
            } else {
                quantidadePaginas = municipioDAO.quantidadePaginasPorNome(termo);
                municipios = municipioDAO.consultarPaginaPorNome(termo, String.valueOf(paginaActual));
            }

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            request.setAttribute("municipios", municipios);

            String tipoPesquisaUrl = URLEncoder.encode(tipoPesquisa, "UTF-8");
            String termoUrl = URLEncoder.encode(termo, "UTF-8");
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>

                        <h1 class="page-header text-primary" title="Registar município">
                            <a href="paginas/municipio/municipio_registo.jsp">
                                Município
                            </a>
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
                        <div class="panel-body">

                            <form action="paginas/municipio/municipio_listar.jsp" method="GET">
                                <div class="form-group">
                                    <select
                                        name="tipo_pesquisa"
                                        id="tipo_pesquisa_municipio"
                                        class="form-control input-sm"
                                        style="max-width: 220px; background-color: #337ab7; color: white; border: none;">

                                        <option value="nome" <%= "nome".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Nome</option>
                                        <option value="provincia" <%= "provincia".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Província</option>
                                    </select>
                                </div>

                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        name="termo"
                                        id="pesquisa_municipio"
                                        class="form-control"
                                        placeholder="Pesquisar município"
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
                                <%@include file="municipio_tabela.jsp" %>

                                <%                                    request.setAttribute("paginaActual", paginaActual);
                                    request.setAttribute("quantidadePaginas", quantidadePaginas);
                                    request.setAttribute("urlBase", "paginas/municipio/municipio_listar.jsp");
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

                function pesquisarMunicipios(pagina) {
                    var tipoPesquisa = $("#tipo_pesquisa_municipio").val();
                    var termo = $("#pesquisa_municipio").val();

                    $("#resultado-municipios").load(
                            "municipioServlet?comando=pesquisar_ajax"
                            + "&tipo_pesquisa=" + encodeURIComponent(tipoPesquisa)
                            + "&termo=" + encodeURIComponent(termo)
                            + "&pagina=" + encodeURIComponent(pagina)
                            );
                }

                $("#pesquisa_municipio").keyup(function () {
                    clearTimeout(tempoEspera);

                    tempoEspera = setTimeout(function () {
                        pesquisarMunicipios(1);
                    }, 300);
                });

                $("#tipo_pesquisa_municipio").change(function () {
                    pesquisarMunicipios(1);
                });
            });
        </script>
    </body>
</html>