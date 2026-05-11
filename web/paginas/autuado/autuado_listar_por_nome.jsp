<%-- 
    Document   : autuante_listar_por_nome
    Created on : 14/03/2019, 20:40:22
    Author     : user
--%>

<%@page import="modelo.Autuado"%>
<%@page import="dao.AutuadoDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Autuado</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%
            AutuadoDAO autuadoDAO = new AutuadoDAO();

            String nome = request.getParameter("nome_autuado");
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

            int quantidadePaginas = autuadoDAO.quantidadePaginasPorNome(nome);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Autuado> autuados = autuadoDAO.consultarPaginaPorNome(nome, String.valueOf(paginaActual));

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String nomeUrl = java.net.URLEncoder.encode(nome, "UTF-8");
        %>

        <div class="container">
            <%@include file="../../menus/cabecalho.jsp" %>

            <h1 class="page-header text-primary">Autuado</h1>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-search"> Pesquisar </span>
                            <span class="caret"></span>
                        </button>

                        <ul class="dropdown-menu">
                            <li><a href="paginas/autuadoautuado_listar.jsp">Listar Todos</a></li>
                            <li><a href="paginas/autuadoautuado_listar_por_bi.jsp">Nº do B.I.</a></li>
                            <li><a href="paginas/autuadoautuado_listar_por_data.jsp">Data de Nascimento</a></li>
                        </ul>
                    </div>
                </div>

                <div class="panel-body">
                    <form action="autuado_listar_por_nome.jsp" method="get">
                        <div class="form-group input-group">
                            <input
                                type="search"
                                id="pesquisa_autuado"
                                name="nome_autuado"
                                class="form-control"
                                placeholder="Nome"
                                value="<%=nome%>"
                                />

                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </span>
                        </div>
                    </form>

                    <div class="table-responsive">
                        <%
                            request.setAttribute("autuados", autuados);
                        %>

                        <%@include file="autuado_tabela.jsp" %>

                        <div class="text-center">
                            <ul class="pagination">
                                <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                    <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "autuado_listar_por_nome.jsp?nome_autuado=" + nomeUrl + "&pagina=" + paginaAnterior%>">
                                        &laquo;
                                    </a>
                                </li>

                                <%
                                    for (int i = 1; i <= quantidadePaginas; i++) {
                                %>
                                <li class="<%=i == paginaActual ? "active" : ""%>">
                                    <a href="autuado_listar_por_nome.jsp?nome_autuado=<%=nomeUrl%>&pagina=<%=i%>"><%=i%></a>
                                </li>
                                <%
                                    }
                                %>

                                <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                    <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "autuado_listar_por_nome.jsp?nome_autuado=" + nomeUrl + "&pagina=" + proximaPagina%>">
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

            <div class="row">
                <%@include file="../../menus/rodape.jsp" %>
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                $("#pesquisa_autuado").keyup(function () {
                    var termo = $(this).val();

                    $.ajax({
                        url: "autuadoServlet",
                        type: "GET",
                        data: {
                            comando: "pesquisar_ajax",
                            tipo_pesquisa: "nome",
                            termo: termo
                        },
                        success: function (resultado) {
                            $("#resultado-autuados").html(resultado);
                            $(".pagination").hide();
                        },
                        error: function () {
                            $("#resultado-autuados").html(
                                    "<tr><td colspan='12' class='text-center text-danger'>Erro ao pesquisar autuados.</td></tr>"
                                    );
                        }
                    });
                });
            });
        </script>
    </body>
</html>