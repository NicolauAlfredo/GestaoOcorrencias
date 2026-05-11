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

            String paginaParametro = request.getParameter("pagina");
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

            int quantidadePaginas = municipioDAO.quantidadePagina();

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Municipio> municipios = municipioDAO.consultarPagina(String.valueOf(paginaActual));

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String urlListar = request.getContextPath() + "/paginas/municipio/municipio_listar.jsp";
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>

                        <h1 class="page-header text-primary" title="Registar município">
                            <a href="paginas/municipio/municipio_registo.jsp">Município</a>
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
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                    <span class="glyphicon glyphicon-menu-down"> Operações </span>
                                    <span class="caret"></span>
                                </button>

                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="/municipios">
                                            <span class="glyphicon glyphicon-print"> Imprimir </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="/paginas/municipio/municipio_listar_por_nome.jsp">
                                            <span class="glyphicon glyphicon-search"> Pesquisar por Nome </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="/paginas/municipio/municipio_listar_por_provincia.jsp">
                                            <span class="glyphicon glyphicon-search"> Pesquisar por Província </span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th class="text-primary">#</th>
                                            <th class="text-primary">Nome</th>
                                            <th class="text-primary">Província</th>
                                            <th class="text-primary" colspan="4">Operações</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            if (municipios == null || municipios.isEmpty()) {
                                        %>
                                        <tr>
                                            <td colspan="7" class="text-center text-muted">
                                                Nenhum município encontrado.
                                            </td>
                                        </tr>
                                        <%
                                        } else {
                                            for (Municipio municipio : municipios) {
                                        %>
                                        <tr>
                                            <td><%=municipio.getIdMunicipio()%></td>
                                            <td><%=municipio.getNomeMunicipio()%></td>
                                            <td>
                                                <%
                                                    if (municipio.getProvincia() != null) {
                                                %>
                                                <%=municipio.getProvincia().getNomeProvincia()%>
                                                <%
                                                    }
                                                %>
                                            </td>

                                            <td>
                                                <a href="municipioServlet?comando=detalhes&id_municipio=<%=municipio.getIdMunicipio()%>">
                                                    <span class="glyphicon glyphicon-print"></span>
                                                </a>
                                            </td>

                                            <td>
                                                <a href="municipioServlet?comando=detalhes&id_municipio=<%=municipio.getIdMunicipio()%>">
                                                    <span class="glyphicon glyphicon-zoom-in"></span>
                                                </a>
                                            </td>

                                            <td>
                                                <a href="municipioServlet?comando=prepara_editar&id_municipio=<%=municipio.getIdMunicipio()%>">
                                                    <span class="glyphicon glyphicon-edit"></span>
                                                </a>
                                            </td>

                                            <td>
                                                <a href="municipioServlet?comando=eliminar&id_municipio=<%=municipio.getIdMunicipio()%>"
                                                   onclick="return confirm('Deseja realmente eliminar este município?');">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </a>
                                            </td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>

                                <div class="text-center">
                                    <ul class="pagination">

                                        <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                            <%
                                                if (paginaActual <= 1) {
                                            %>
                                            <a href="javascript:void(0);">&laquo;</a>
                                            <%
                                            } else {
                                            %>
                                            <a href="<%=urlListar%>?pagina=<%=paginaAnterior%>">&laquo;</a>
                                            <%
                                                }
                                            %>
                                        </li>

                                        <%
                                            for (int i = 1; i <= quantidadePaginas; i++) {
                                        %>
                                        <li class="<%=i == paginaActual ? "active" : ""%>">
                                            <a href="<%=urlListar%>?pagina=<%=i%>"><%=i%></a>
                                        </li>
                                        <%
                                            }
                                        %>

                                        <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                            <%
                                                if (paginaActual >= quantidadePaginas) {
                                            %>
                                            <a href="javascript:void(0);">&raquo;</a>
                                            <%
                                            } else {
                                            %>
                                            <a href="<%=urlListar%>?pagina=<%=proximaPagina%>">&raquo;</a>
                                            <%
                                                }
                                            %>
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
    </body>
</html>