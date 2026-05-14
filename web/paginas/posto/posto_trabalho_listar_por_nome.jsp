<%-- 
    Document   : posto_trabalho_listar_por_nome
    Created on : 16/03/2019, 08:35:05
    Author     : user
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="dao.PostoTrabalhoDAO"%>
<%@page import="modelo.PostoTrabalho"%>
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

            String nome = request.getParameter("nome_posto");
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

            int quantidadePaginas = postoTrabalhoDAO.quantidadePaginasPorNome(nome);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<PostoTrabalho> postoTrabalhos = postoTrabalhoDAO.consultarPaginaPorNome(
                    nome,
                    String.valueOf(paginaActual)
            );

            request.setAttribute("postoTrabalhos", postoTrabalhos);

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
                            Posto de Trabalho
                        </h1>

                        <div class="alert alert-info">
                            <p>${message}</p>
                        </div>
                    </div>                 
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                    <span class="glyphicon glyphicon-search"> Pesquisar </span>
                                    <span class="caret"></span>
                                </button>

                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="paginas/posto/posto_trabalho_listar.jsp">
                                            Listar Todos
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/posto/posto_trabalho_listar_por_municipio.jsp">
                                            Nome Município
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/posto/posto_trabalho_listar_por_numero.jsp">
                                            Número do Posto
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="panel-body">

                            <form action="paginas/posto/posto_trabalho_listar_por_nome.jsp" method="get">
                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        id="pesquisa_posto_nome"
                                        name="nome_posto"
                                        class="form-control"
                                        placeholder="Nome"
                                        value="<%=nome%>"
                                        >

                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                            </form>

                            <form>
                                <div id="resultado-postos-wrapper">
                                    <%@include file="posto_trabalho_tabela.jsp" %>

                                    <div class="text-center">
                                        <ul class="pagination">

                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                                <a href="<%=paginaActual <= 1
                                                        ? "javascript:void(0)"
                                                        : "paginas/posto/posto_trabalho_listar_por_nome.jsp?nome_posto="
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
                                                <a href="paginas/posto/posto_trabalho_listar_por_nome.jsp?nome_posto=<%=nomeUrl%>&pagina=<%=i%>">
                                                    <%=i%>
                                                </a>
                                            </li>
                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                                <a href="<%=paginaActual >= quantidadePaginas
                                                        ? "javascript:void(0)"
                                                        : "paginas/posto/posto_trabalho_listar_por_nome.jsp?nome_posto="
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

                $("#pesquisa_posto_nome").keyup(function () {
                    clearTimeout(tempoEspera);

                    var termo = $(this).val();

                    tempoEspera = setTimeout(function () {
                        $("#resultado-postos-wrapper").load(
                                "paginas/posto/posto_trabalho_listar_por_nome.jsp?nome_posto="
                                + encodeURIComponent(termo)
                                + " #resultado-postos-wrapper > *"
                                );
                    }, 300);
                });
            });
        </script>
    </body>
</html>