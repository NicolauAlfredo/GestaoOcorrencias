<%@page import="java.net.URLEncoder"%>
<%@page import="modelo.Testemunha"%>
<%@page import="java.util.List"%>
<%@page import="dao.TestemunhaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/">

        <title>Testemunha</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>

        <%
            TestemunhaDAO testemunhaDAO = new TestemunhaDAO();

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
            List<Testemunha> testemunhas;

            if (tipoPesquisa.equalsIgnoreCase("bi")) {
                quantidadePaginas = testemunhaDAO.quantidadePaginasPorBi(termo);
                testemunhas = testemunhaDAO.consultarPaginaPorBi(termo, String.valueOf(paginaActual));

            } else if (tipoPesquisa.equalsIgnoreCase("data")) {
                try {
                    if (termo.trim().isEmpty()) {
                        quantidadePaginas = testemunhaDAO.quantidadePaginas();
                        testemunhas = testemunhaDAO.consultarPagina(String.valueOf(paginaActual));
                    } else {
                        java.sql.Date data = DateUtil.strToDate(termo);
                        quantidadePaginas = testemunhaDAO.quantidadePaginasPorData(data);
                        testemunhas = testemunhaDAO.consultarPaginaPorData(data, String.valueOf(paginaActual));
                    }
                } catch (Exception ex) {
                    quantidadePaginas = 1;
                    testemunhas = new java.util.ArrayList<Testemunha>();
                }

            } else {
                quantidadePaginas = testemunhaDAO.quantidadePaginasPorNome(termo);
                testemunhas = testemunhaDAO.consultarPaginaPorNome(termo, String.valueOf(paginaActual));
            }

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            request.setAttribute("testemunhas", testemunhas);

            String tipoPesquisaUrl = URLEncoder.encode(tipoPesquisa, "UTF-8");
            String termoUrl = URLEncoder.encode(termo, "UTF-8");
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>

                        <h1 class="page-header text-primary"
                            title="Registar testemunha">
                            <a href="paginas/testemunha/testemunha_registo.jsp">
                                Testemunha
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
                            <form action="paginas/testemunha/testemunha_listar.jsp" method="GET">
                                <div class="form-group">
                                    <select name="tipo_pesquisa" id="tipo_pesquisa_testemunha" class="form-control input-sm" style="max-width: 220px; background-color: #337ab7; color: white; border: none">
                                        <option value="nome" <%= "nome".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Nome</option>
                                        <option value="bi" <%= "bi".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>B.I.</option>
                                        <option value="data" <%= "data".equalsIgnoreCase(tipoPesquisa) ? "selected" : ""%>>Data de Nascimento</option>
                                    </select>
                                </div>

                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        name="termo"
                                        id="pesquisa_testemunha"
                                        class="form-control"
                                        placeholder="Pesquisar testemunha"
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
                                    <%@include file="testemunha_tabela.jsp" %>

                                    <%  request.setAttribute("paginaActual", paginaActual);
                                        request.setAttribute("quantidadePaginas", quantidadePaginas);
                                        request.setAttribute("urlBase", "paginas/testemunha/testemunha_listar.jsp");
                                        request.setAttribute("queryStringExtra", "tipo_pesquisa=" + tipoPesquisaUrl + "&termo=" + termoUrl);
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

                function pesquisarTestemunhas(pagina) {
                    var tipoPesquisa = $("#tipo_pesquisa_testemunha").val();
                    var termo = $("#pesquisa_testemunha").val();

                    $("#resultado-testemunhas-wrapper").load(
                            "paginas/testemunha/testemunha_listar.jsp?termo="
                            + encodeURIComponent(termo)
                            + "&pagina=" + encodeURIComponent(pagina)
                            + " #resultado-testemunhas-wrapper > *"
                            );
                }

                $("#pesquisa_testemunha").keyup(function () {
                    clearTimeout(tempoEspera);

                    tempoEspera = setTimeout(function () {
                        pesquisarTestemunhas(1);
                    }, 300);
                });

                $("#tipo_pesquisa_testemunha").change(function () {
                    pesquisarTestemunhas(1);
                });
            });
        </script>


    </body>
</html>