<%-- 
    Document   : profissao_listar
    Created on : 26/02/2019, 14:31:05
    Author     : user
--%>

<%@page import="modelo.Profissao"%>
<%@page import="java.util.List"%>
<%@page import="dao.ProfissaoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Profissão</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%
            ProfissaoDAO profissaoDAO = new ProfissaoDAO();

            String paginaParametro = request.getParameter("pagina");
            String termoPesquisa = request.getParameter("nome_profissao");

            if (termoPesquisa == null) {
                termoPesquisa = "";
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

            List<Profissao> profissoes;

            if (termoPesquisa.trim().isEmpty()) {
                quantidadePaginas = profissaoDAO.quantidadePaginas();

                if (paginaActual > quantidadePaginas) {
                    paginaActual = quantidadePaginas;
                }

                profissoes = profissaoDAO.consultarPagina(String.valueOf(paginaActual));
            } else {
                quantidadePaginas = profissaoDAO.quantidadePaginasPorNome(termoPesquisa);

                if (paginaActual > quantidadePaginas) {
                    paginaActual = quantidadePaginas;
                }

                profissoes = profissaoDAO.consultarPaginaPorNome(
                        termoPesquisa,
                        String.valueOf(paginaActual)
                );
            }

            request.setAttribute("profissoes", profissoes);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String termoUrl = java.net.URLEncoder.encode(termoPesquisa, "UTF-8");
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>

                        <h1 class="page-header text-primary" title="Registar profissão">
                            <a href="paginas/profissao/profissao_registo.jsp">Profissão</a>
                        </h1>

                        <div class="alert alert-info">
                            <p>${message}</p>
                        </div>
                    </div>                 
                </div>
            </div>

            <!-- Linha de divisão -->
            <div class="row">
                <!-- Área da linha -->
                <div class="col-lg-12">
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <!-- Botão Suspenso -->
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                    <span class="glyphicon glyphicon-menu-down"> Operações </span>
                                    <span class="caret"></span>
                                </button>

                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="listaProfissoes">
                                            <span class="glyphicon glyphicon-print"> Imprimir </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/profissao/profissao_listar_por_nome.jsp">
                                            <span class="glyphicon glyphicon-search"> Pesquisar </span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">

                            <form action="paginas/profissao/profissao_listar.jsp" method="get">
                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        id="pesquisa_profissao"
                                        name="nome_profissao"
                                        class="form-control"
                                        placeholder="Pesquisar profissão"
                                        value="<%=termoPesquisa%>"
                                        >

                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                            </form>

                            <form>
                                <div id="resultado-profissoes-wrapper">
                                    <%@include file="profissao_tabela.jsp" %>

                                    <div class="text-center">
                                        <ul class="pagination">

                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                                <a href="<%=paginaActual <= 1
                                                        ? "javascript:void(0)"
                                                        : "paginas/profissao/profissao_listar.jsp?nome_profissao="
                                                        + termoUrl
                                                        + "&pagina="
                                                        + paginaAnterior%>">
                                                    &laquo;
                                                </a>
                                            </li>

                                            <%
                                                for (int i = 1; i <= quantidadePaginas; i++) {
                                            %>
                                            <li class="<%=i == paginaActual ? "active" : ""%>">
                                                <a href="paginas/profissao/profissao_listar.jsp?nome_profissao=<%=termoUrl%>&pagina=<%=i%>">
                                                    <%=i%>
                                                </a>
                                            </li>
                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                                <a href="<%=paginaActual >= quantidadePaginas
                                                        ? "javascript:void(0)"
                                                        : "paginas/profissao/profissao_listar.jsp?nome_profissao="
                                                        + termoUrl
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
                <!-- Fim da área da linha-->

                <!-- Rodapé -->
                <%@include file="../../menus/rodape.jsp" %>
                <!-- Fim do Rodapé-->

            </div>
            <!-- Fim da linha de divisão -->
        </div>
        <!-- Fim do Container -->

        <script type="text/javascript">
            $(document).ready(function () {
                var tempoEspera = null;

                $("#pesquisa_profissao").keyup(function () {
                    clearTimeout(tempoEspera);

                    var termo = $(this).val();

                    tempoEspera = setTimeout(function () {
                        $("#resultado-profissoes-wrapper").load(
                                "paginas/profissao/profissao_listar.jsp?nome_profissao="
                                + encodeURIComponent(termo)
                                + " #resultado-profissoes-wrapper > *"
                                );
                    }, 300);
                });
            });
        </script>
    </body>
</html>