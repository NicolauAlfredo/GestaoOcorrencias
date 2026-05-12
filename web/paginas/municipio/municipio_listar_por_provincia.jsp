<%-- 
    Document   : municipio_listar_por_provincia
    Created on : 23/03/2019, 21:28:24
    Author     : user
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Municipio"%>
<%@page import="dao.MunicipioDAO"%>
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

            String provincia = request.getParameter("nome_provincia");
            String paginaParametro = request.getParameter("pagina");

            if (provincia == null) {
                provincia = "";
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

            int quantidadePaginas = municipioDAO.quantidadePaginasPorProvincia(provincia);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Municipio> municipios = municipioDAO.consultarPaginaPorProvincia(
                    provincia,
                    String.valueOf(paginaActual)
            );

            request.setAttribute("municipios", municipios);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String provinciaUrl = URLEncoder.encode(provincia, "UTF-8");
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
                                        <a href="paginas/municipio/municipio_listar_por_nome.jsp">
                                            Nome Município
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="panel-body">
                            <form action="paginas/municipio/municipio_listar_por_provincia.jsp" method="get">
                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        id="nome_provincia"
                                        name="nome_provincia"
                                        class="form-control"
                                        required
                                        placeholder="Nome da Província"
                                        value="<%=provincia%>"
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

                                    <div class="text-center">
                                        <ul class="pagination">

                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                                <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "paginas/municipio/municipio_listar_por_provincia.jsp?nome_provincia=" + provinciaUrl + "&pagina=" + paginaAnterior%>">
                                                    &laquo;
                                                </a>
                                            </li>

                                            <%
                                                for (int i = 1; i <= quantidadePaginas; i++) {
                                            %>
                                            <li class="<%=i == paginaActual ? "active" : ""%>">
                                                <a href="paginas/municipio/municipio_listar_por_provincia.jsp?nome_provincia=<%=provinciaUrl%>&pagina=<%=i%>">
                                                    <%=i%>
                                                </a>
                                            </li>
                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                                <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "paginas/municipio/municipio_listar_por_provincia.jsp?nome_provincia=" + provinciaUrl + "&pagina=" + proximaPagina%>">
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
                $("#nome_provincia").keyup(function () {
                    var termo = $(this).val();

                    $.ajax({
                        url: "municipioServlet",
                        type: "GET",
                        data: {
                            comando: "pesquisar_ajax",
                            tipo_pesquisa: "provincia",
                            termo: termo
                        },
                        success: function (resultado) {
                            $("#resultado-municipios").html(resultado);
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