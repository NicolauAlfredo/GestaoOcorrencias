<%-- 
    Document   : municipio_listar_por_nome
    Created on : 16/03/2019, 08:15:04
    Author     : user
--%>

<%@page import="dao.MunicipioDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Municipio"%>
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

            String nome = request.getParameter("nome_municipio");
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

            int quantidadePaginas = municipioDAO.quantidadePaginasPorNome(nome);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Municipio> municipios = municipioDAO.consultarPaginaPorNome(
                    nome,
                    String.valueOf(paginaActual)
            );

            request.setAttribute("municipios", municipios);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String nomeUrl = java.net.URLEncoder.encode(nome, "UTF-8");
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>

                        <h1 class="page-header text-primary">Município</h1>

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
                                        <a href="paginas/municipio/municipio_listar.jsp">
                                            Listar Todos
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/municipio/municipio_listar_por_provincia.jsp">
                                            Nome Província
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="panel-body">
                            <form action="paginas/municipio/municipio_listar_por_nome.jsp" method="get">
                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        id="nome_municipio"
                                        name="nome_municipio"
                                        class="form-control"
                                        required
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
                                <div class="table-responsive">
                                    <%@include file="municipio_tabela.jsp" %>
                                </div>

                                <div class="text-center">
                                    <ul class="pagination">

                                        <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                            <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "paginas/municipio/municipio_listar_por_nome.jsp?nome_municipio=" + nomeUrl + "&pagina=" + paginaAnterior%>">
                                                &laquo;
                                            </a>
                                        </li>

                                        <%
                                            for (int i = 1; i <= quantidadePaginas; i++) {
                                        %>
                                        <li class="<%=i == paginaActual ? "active" : ""%>">
                                            <a href="paginas/municipio/municipio_listar_por_nome.jsp?nome_municipio=<%=nomeUrl%>&pagina=<%=i%>">
                                                <%=i%>
                                            </a>
                                        </li>
                                        <%
                                            }
                                        %>

                                        <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                            <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "paginas/municipio/municipio_listar_por_nome.jsp?nome_municipio=" + nomeUrl + "&pagina=" + proximaPagina%>">
                                                &raquo;
                                            </a>
                                        </li>

                                    </ul>

                                    <p class="text-muted">
                                        Página <%=paginaActual%> de <%=quantidadePaginas%>
                                    </p>
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
                $("#nome_municipio").keyup(function () {
                    var termo = $(this).val();

                    $.ajax({
                        url: "municipioServlet",
                        type: "GET",
                        data: {
                            comando: "pesquisar_ajax",
                            tipo_pesquisa: "nome",
                            termo: termo
                        },
                        success: function (resultado) {
                            $("#resultado-municipios").html(resultado);
                            $(".pagination").hide();
                        },
                        error: function () {
                            $("#resultado-municipios").html(
                                    "<tr><td colspan='7' class='text-center text-danger'>Erro ao pesquisar municípios.</td></tr>"
                                    );
                        }
                    });
                });
            });
        </script>
    </body>
</html>