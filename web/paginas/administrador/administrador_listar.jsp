<%@page import="modelo.Administrador"%>
<%@page import="dao.AdministradorDAO"%>
<%@page import="modelo.DateUtil"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Administrador</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%
            AdministradorDAO administradorDAO = new AdministradorDAO();

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

            int quantidadePaginas = administradorDAO.quantidadePaginas();

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Administrador> administradores = administradorDAO.consultarPagina(String.valueOf(paginaActual));

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;
        %>

        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>

                        <h1 class="page-header text-primary" title="Registar administrador">
                            <a href="paginas/administrador/administrador_registo.jsp">Administrador</a>
                        </h1>

                        <%String message = (String) request.getAttribute("message");
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
                                        <a href="listaOcorrencias">
                                            <span class="glyphicon glyphicon-print"> Imprimir </span>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="paginas/administrador/administrador_listar_por_nome.jsp">
                                            <span class="glyphicon glyphicon-search"> Pesquisar </span>
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
                                            <th class="text-primary">Data de Nascimento</th>
                                            <th class="text-primary">Sexo</th>
                                            <th class="text-primary">B.I.</th>
                                            <th class="text-primary">Email</th>
                                            <th class="text-primary">Telefone</th>
                                            <th class="text-primary" colspan="4">Operações</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            if (administradores == null || administradores.isEmpty()) {
                                        %>
                                        <tr>
                                            <td colspan="11" class="text-center text-muted">
                                                Nenhum administrador encontrado.
                                            </td>
                                        </tr>
                                        <%
                                        } else {
                                            for (Administrador administrador : administradores) {
                                        %>
                                        <tr>
                                            <td><%=administrador.getIdAdministrador()%></td>
                                            <td><%=administrador.getNomeAdministrador()%></td>
                                            <td><%=DateUtil.formataData(administrador.getDataNascimentoAdministrador())%></td>
                                            <td>
                                                <%
                                                    if (administrador.getSexo() != null) {
                                                %>
                                                <%=administrador.getSexo().getExtensao()%>
                                                <%
                                                    }
                                                %>
                                            </td>
                                            <td><%=administrador.getBiAdministrador()%></td>
                                            <td><%=administrador.getEmailAdministrador()%></td>
                                            <td><%=administrador.getTelefoneAdministrador()%></td>

                                            <td>
                                                <a href="administradorServlet?comando=detalhes&id_administrador=<%=administrador.getIdAdministrador()%>">
                                                    <span class="glyphicon glyphicon-print"></span>
                                                </a>
                                            </td>

                                            <td>
                                                <a href="administradorServlet?comando=detalhes&id_administrador=<%=administrador.getIdAdministrador()%>">
                                                    <span class="glyphicon glyphicon-zoom-in"></span>
                                                </a>
                                            </td>

                                            <td>
                                                <a href="administradorServlet?comando=prepara_editar&id_administrador=<%=administrador.getIdAdministrador()%>">
                                                    <span class="glyphicon glyphicon-edit"></span>
                                                </a>
                                            </td>

                                            <td>
                                                <a href="/administradorServlet?comando=eliminar&id_administrador=<%=administrador.getIdAdministrador()%>"
                                                   onclick="return confirm('Deseja realmente eliminar este administrador?');">
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
                                            <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "administrador_listar.jsp?pagina=" + paginaAnterior%>">
                                                &laquo;
                                            </a>
                                        </li>

                                        <%
                                            for (int i = 1; i <= quantidadePaginas; i++) {
                                        %>
                                        <li class="<%=i == paginaActual ? "active" : ""%>">
                                            <a href="administrador_listar.jsp?pagina=<%=i%>"><%=i%></a>
                                        </li>
                                        <%
                                            }
                                        %>

                                        <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                            <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "administrador_listar.jsp?pagina=" + proximaPagina%>">
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
    </body>
</html>