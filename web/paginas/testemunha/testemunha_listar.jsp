<%-- 
    Document   : testemunha_listar
    Created on : 02/03/2019, 15:37:59
    Author     : user
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="modelo.Testemunha"%>
<%@page import="modelo.DateUtil"%>
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

            String nome = request.getParameter("nome_testemunha");
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

            int quantidadePaginas;

            List<Testemunha> testemunhas;

            if (nome.trim().isEmpty()) {
                quantidadePaginas = testemunhaDAO.quantidadePaginas();
                testemunhas = testemunhaDAO.consultarPagina(
                        String.valueOf(paginaActual)
                );
            } else {
                quantidadePaginas = testemunhaDAO.quantidadePaginasPorNome(nome);

                testemunhas = testemunhaDAO.consultarPaginaPorNome(
                        nome,
                        String.valueOf(paginaActual)
                );
            }

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            request.setAttribute("testemunhas", testemunhas);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String nomeUrl = URLEncoder.encode(nome, "UTF-8");
        %>

        <div class="container">

            <div id="page-wrapper">

                <div class="row">

                    <div class="col-lg-12">

                        <%@include file="../../menus/cabecalho.jsp" %>

                        <h1 class="page-header text-primary"
                            title="Registar testemunha">

                            <a href="paginas/testemunha/testemunha_registo.jsp">
                                Testemunha
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

                        <div class="panel-heading">

                            <div class="btn-group">

                                <button type="button"
                                        class="btn btn-primary dropdown-toggle"
                                        data-toggle="dropdown">

                                    <span class="glyphicon glyphicon-menu-down">
                                        Operações
                                    </span>

                                    <span class="caret"></span>

                                </button>

                                <ul class="dropdown-menu">

                                    <li>
                                        <a href="testemunhas">
                                            <span class="glyphicon glyphicon-print">
                                                Imprimir
                                            </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/testemunha/testemunha_listar_por_nome.jsp">
                                            <span class="glyphicon glyphicon-search">
                                                Pesquisar
                                            </span>
                                        </a>
                                    </li>

                                </ul>

                            </div>

                        </div>

                        <div class="panel-body">

                            <form action="paginas/testemunha/testemunha_listar.jsp"
                                  method="GET">

                                <div class="form-group input-group">

                                    <input
                                        type="search"
                                        name="nome_testemunha"
                                        id="pesquisa_testemunha"
                                        class="form-control"
                                        placeholder="Pesquisar testemunha"
                                        autocomplete="off"
                                        value="<%=nome%>">

                                    <span class="input-group-btn">

                                        <button class="btn btn-primary"
                                                type="submit">

                                            <i class="glyphicon glyphicon-search"></i>

                                        </button>

                                    </span>

                                </div>

                            </form>

                            <div class="table-responsive">

                                <%@include file="testemunha_tabela.jsp" %>

                                <div class="text-center">

                                    <ul class="pagination">

                                        <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">

                                            <a href="<%=paginaActual <= 1
                                                    ? "javascript:void(0)"
                                                    : "paginas/testemunha/testemunha_listar.jsp?nome_testemunha="
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

                                            <a href="paginas/testemunha/testemunha_listar.jsp?nome_testemunha=<%=nomeUrl%>&pagina=<%=i%>">

                                                <%=i%>

                                            </a>

                                        </li>

                                        <%
                                            }
                                        %>

                                        <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">

                                            <a href="<%=paginaActual >= quantidadePaginas
                                                    ? "javascript:void(0)"
                                                    : "paginas/testemunha/testemunha_listar.jsp?nome_testemunha="
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

                        </div>

                    </div>

                </div>

                <%@include file="../../menus/rodape.jsp" %>

            </div>

        </div>

        <script type="text/javascript">

            $(document).ready(function () {

                var tempoEspera = null;

                $("#pesquisa_testemunha").keyup(function () {

                    clearTimeout(tempoEspera);

                    var termo = $(this).val();

                    tempoEspera = setTimeout(function () {

                        $.ajax({

                            url: "testemunhaServlet",

                            type: "GET",

                            data: {
                                comando: "pesquisar_ajax",
                                termo: termo
                            },

                            success: function (resultado) {

                                $("#resultado-testemunhas").html(resultado);

                            },

                            error: function () {

                                $("#resultado-testemunhas").html(
                                        "<tr>" +
                                        "<td colspan='11' class='text-center text-danger'>" +
                                        "Erro ao pesquisar testemunhas." +
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