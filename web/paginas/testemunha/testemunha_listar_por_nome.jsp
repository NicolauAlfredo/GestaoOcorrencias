<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Testemunha"%>
<%@page import="java.util.List"%>
<%@page import="dao.TestemunhaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <base href="<%=request.getContextPath()%>/">

        <title>Testemunha</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>

                        <h1 class="page-header text-primary">Testemunha</h1>

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
                                    <span class="glyphicon glyphicon-search"> Pesquisar </span>
                                    <span class="caret"></span>
                                </button>

                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="paginas/testemunha/testemunha_listar.jsp">Listar Todas</a>
                                    </li>
                                    <li>
                                        <a href="paginas/testemunha/testemunha_listar_por_data.jsp">Data de Nascimento</a>
                                    </li>
                                    <li>
                                        <a href="paginas/testemunha/testemunha_listar_por_bi.jsp">Nº do B.I</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="panel-body">

                            <form action="paginas/testemunha/testemunha_listar_por_nome.jsp" method="get">
                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        name="nome_testemunha"
                                        class="form-control"
                                        placeholder="Nome"
                                        value="<%=request.getParameter("nome_testemunha") != null ? request.getParameter("nome_testemunha") : ""%>"
                                        >

                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                            </form>

                            <%
                                TestemunhaDAO testemunhaDAO = new TestemunhaDAO();

                                String nome = request.getParameter("nome_testemunha");

                                if (nome == null) {
                                    nome = "";
                                }

                                List<Testemunha> testemunhas = testemunhaDAO.findByNome(nome);
                            %>

                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th class="text-primary">#</th>
                                            <th class="text-primary">Nome</th>
                                            <th class="text-primary">B.I.</th>
                                            <th class="text-primary">Residência</th>
                                            <th class="text-primary">Data de Nascimento</th>
                                            <th class="text-primary">Sexo</th>
                                            <th class="text-primary">Contacto</th>
                                            <th class="text-primary" colspan="4">Operações</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <%
                                            if (testemunhas == null || testemunhas.isEmpty()) {
                                        %>
                                        <tr>
                                            <td colspan="11" class="text-center text-muted">
                                                Nenhuma testemunha encontrada.
                                            </td>
                                        </tr>
                                        <%
                                        } else {
                                            for (Testemunha testemunha : testemunhas) {
                                        %>
                                        <tr>
                                            <td><%=testemunha.getIdTestemunha()%></td>
                                            <td><%=testemunha.getNomeTestemunha()%></td>
                                            <td><%=testemunha.getBiTestemunha()%></td>
                                            <td><%=testemunha.getResidenciaTestemunha()%></td>
                                            <td><%=DateUtil.formataData(testemunha.getDataNascimentoTestemunha())%></td>

                                            <td>
                                                <%
                                                    if (testemunha.getSexo() != null) {
                                                %>
                                                <%=testemunha.getSexo().getExtensao()%>
                                                <%
                                                    }
                                                %>
                                            </td>

                                            <td><%=testemunha.getTelefoneTestemunha()%></td>

                                            <td>
                                                <a href="testemunhaServlet?comando=detalhes&id_testemunha=<%=testemunha.getIdTestemunha()%>">
                                                    <span class="glyphicon glyphicon-print"></span>
                                                </a>
                                            </td>

                                            <td>
                                                <a href="testemunhaServlet?comando=detalhes&id_testemunha=<%=testemunha.getIdTestemunha()%>">
                                                    <span class="glyphicon glyphicon-zoom-in"></span>
                                                </a>
                                            </td>

                                            <td>
                                                <a href="testemunhaServlet?comando=prepara_editar&id_testemunha=<%=testemunha.getIdTestemunha()%>">
                                                    <span class="glyphicon glyphicon-edit"></span>
                                                </a>
                                            </td>

                                            <td>
                                                <a href="testemunhaServlet?comando=eliminar&id_testemunha=<%=testemunha.getIdTestemunha()%>"
                                                   onclick="return confirm('Deseja realmente eliminar esta testemunha?');">
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
                            </div>

                        </div>
                    </div>
                </div>

                <%@include file="../../menus/rodape.jsp" %>
            </div>
        </div>
    </body>
</html>