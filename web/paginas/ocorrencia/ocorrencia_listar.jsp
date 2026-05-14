<%-- 
    Document   : ocorrencia_listar
    Created on : 24/02/2019, 20:29:11
    Author     : user
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Ocorrencia"%>
<%@page import="dao.OcorrenciaDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/">

        <title>Ocorrência</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%
            OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

            String tipoPesquisa = request.getParameter("tipo_pesquisa");
            String termo = request.getParameter("termo");
            String paginaParametro = request.getParameter("pagina");

            if (tipoPesquisa == null || tipoPesquisa.trim().isEmpty()) {
                tipoPesquisa = "autuado";
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
            List<Ocorrencia> ocorrencias;

            if (tipoPesquisa.equalsIgnoreCase("autuante")) {
                quantidadePaginas = ocorrenciaDAO.quantidadePaginasPorAutuante(termo);
                ocorrencias = ocorrenciaDAO.consultarPaginaPorAutuante(termo, String.valueOf(paginaActual));

            } else if (tipoPesquisa.equalsIgnoreCase("cidade")) {
                quantidadePaginas = ocorrenciaDAO.quantidadePaginasPorCidade(termo);
                ocorrencias = ocorrenciaDAO.consultarPaginaPorCidade(termo, String.valueOf(paginaActual));

            } else if (tipoPesquisa.equalsIgnoreCase("testemunha")) {
                quantidadePaginas = ocorrenciaDAO.quantidadePaginasPorTestemunha(termo);
                ocorrencias = ocorrenciaDAO.consultarPaginaPorTestemunha(termo, String.valueOf(paginaActual));

            } else if (tipoPesquisa.equalsIgnoreCase("tipo")) {
                quantidadePaginas = ocorrenciaDAO.quantidadePaginasPorTipo(termo);
                ocorrencias = ocorrenciaDAO.consultarPaginaPorTipo(termo, String.valueOf(paginaActual));

            } else if (tipoPesquisa.equalsIgnoreCase("data")) {
                try {
                    if (termo.trim().isEmpty()) {
                        quantidadePaginas = ocorrenciaDAO.quantidadePaginas();
                        ocorrencias = ocorrenciaDAO.consultarPagina(String.valueOf(paginaActual));
                    } else {
                        java.sql.Date data = DateUtil.strToDate(termo);
                        quantidadePaginas = ocorrenciaDAO.quantidadePaginasPorData(data);
                        ocorrencias = ocorrenciaDAO.consultarPaginaPorData(data, String.valueOf(paginaActual));
                    }
                } catch (Exception ex) {
                    quantidadePaginas = 1;
                    ocorrencias = new java.util.ArrayList<Ocorrencia>();
                }

            } else {
                quantidadePaginas = ocorrenciaDAO.quantidadePaginasPorAutuado(termo);
                ocorrencias = ocorrenciaDAO.consultarPaginaPorAutuado(termo, String.valueOf(paginaActual));
            }

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            request.setAttribute("ocorrencias", ocorrencias);

            String tipoPesquisaUrl = URLEncoder.encode(tipoPesquisa, "UTF-8");
            String termoUrl = URLEncoder.encode(termo, "UTF-8");
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>

                        <h1 class="page-header text-primary" title="Registar ocorrência">
                            <a href="paginas/ocorrencia/ocorrencia_registo.jsp">
                                Ocorrência
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

                            <form action="paginas/ocorrencia/ocorrencia_listar.jsp" method="GET">
                                <div class="form-group">
                                    <select
                                        name="tipo_pesquisa"
                                        id="tipo_pesquisa_ocorrencia"
                                        class="form-control input-sm"
                                        style="max-width: 220px; background-color: #337ab7; color: white; border-color: #2e6da4;">

                                        <option value="autuado" <%= "autuado".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Autuado</option>
                                        <option value="autuante" <%= "autuante".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Autuante</option>
                                        <option value="cidade" <%= "cidade".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Cidade</option>
                                        <option value="testemunha" <%= "testemunha".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Testemunha</option>
                                        <option value="tipo" <%= "tipo".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Tipo de Ocorrência</option>
                                        <option value="data" <%= "data".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Data</option>
                                    </select>
                                </div>

                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        name="termo"
                                        id="pesquisa_ocorrencia"
                                        class="form-control"
                                        placeholder="Pesquisar ocorrência"
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
                                <%@include file="ocorrencia_tabela.jsp" %>

                                <%
                                    request.setAttribute("paginaActual", paginaActual);
                                    request.setAttribute("quantidadePaginas", quantidadePaginas);
                                    request.setAttribute("urlBase", "paginas/ocorrencia/ocorrencia_listar.jsp");
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

                function pesquisarOcorrencias(pagina) {
                    var tipoPesquisa = $("#tipo_pesquisa_ocorrencia").val();
                    var termo = $("#pesquisa_ocorrencia").val();

                    $("#resultado-ocorrencias").load(
                            "ocorrenciaServlet?comando=pesquisar_ajax"
                            + "&tipo_pesquisa=" + encodeURIComponent(tipoPesquisa)
                            + "&termo=" + encodeURIComponent(termo)
                            + "&pagina=" + encodeURIComponent(pagina)
                            );
                }

                $("#pesquisa_ocorrencia").keyup(function () {
                    clearTimeout(tempoEspera);

                    tempoEspera = setTimeout(function () {
                        pesquisarOcorrencias(1);
                    }, 300);
                });

                $("#tipo_pesquisa_ocorrencia").change(function () {
                    pesquisarOcorrencias(1);
                });
            });
        </script>
    </body>
</html>