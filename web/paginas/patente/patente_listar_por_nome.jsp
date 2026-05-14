<%-- 
    Document   : patente_listar_por_nome
    Created on : 16/03/2019, 08:32:14
    Author     : user
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%> 
<%@page import="dao.PatenteDAO"%>
<%@page import="modelo.Patente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <base href="<%=request.getContextPath()%>/"> 

        <title>Patente</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            PatenteDAO patenteDAO = new PatenteDAO();

            String nome = request.getParameter("nome_patente");
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

            int quantidadePaginas = patenteDAO.quantidadePaginasPorNome(nome);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Patente> patentes = patenteDAO.consultarPaginaPorNome(
                    nome,
                    String.valueOf(paginaActual)
            );

            request.setAttribute("patentes", patentes);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String nomeUrl = URLEncoder.encode(nome, "UTF-8");
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary">Patente</h1>
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
                                    <span class="glyphicon glyphicon-search"> Pesquisar </span>                                       
                                </button>  
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->   
                        <div class="panel-body">

                            <form action="paginas/patente/patente_listar_por_nome.jsp" method="get">
                                <!-- Div com o campo de pesquisa -->
                                <div class="form-group input-group">
                                    <input 
                                        type="search" 
                                        name="nome_patente" 
                                        id="pesquisa_patente" 
                                        class="form-control" 
                                        placeholder="Nome" 
                                        value="<%=nome%>">

                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                                <!-- Fim da div com o campo de pesquisa -->
                            </form>


                            <form>
                                <div id="resultado-patentes-wrapper">
                                    <%@include file="patente_tabela.jsp" %>

                                    <div class="text-center">
                                        <ul class="pagination">
                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                                <a href="<%=paginaActual <= 1
                                                        ? "javascript:void(0)"
                                                        : "paginas/patente/patente_listar_por_nome.jsp?nome_patente="
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
                                                <a href="paginas/patente/patente_listar_por_nome.jsp?nome_patente=<%=nomeUrl%>&pagina=<%=i%>">
                                                    <%=i%>
                                                </a>
                                            </li>

                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                                <a href="<%=paginaActual >= quantidadePaginas
                                                        ? "javascript:void(0)"
                                                        : "paginas/patente/patente_listar_por_nome.jsp?nome_patente="
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

                $("#pesquisa_patente").keyup(function () {
                    clearTimeout(tempoEspera);

                    var termo = $(this).val();

                    tempoEspera = setTimeout(function () {
                        $.ajax({
                            url: "patenteServlet",
                            type: "GET",
                            data: {
                                comando: "pesquisar_ajax",
                                termo: termo
                            },
                            success: function (resultado) {
                                $("#resultado-patentes").html(resultado);
                            },
                            error: function () {
                                $("#resultado-patentes").html(
                                        "<tr>" +
                                        "<td colspan='6' class='text-center text-danger'>" +
                                        "Erro ao pesquisar patentes." +
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

