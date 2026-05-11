<%-- 
    Document   : autuante_listar_por_nome
    Created on : 14/03/2019, 20:40:22
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Autuante"%>
<%@page import="java.util.List"%>
<%@page import="dao.AutuanteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Autuante</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary">Autuante</h1>
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
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a href="paginas/autuante/autuante_listar_por_data.jsp">Data de Nascimento</a></li>
                                    <li><a href="paginas/autuante/autuante_listar_por_bi.jsp">Nº do B.I</a></li>
                                    <li><a href="paginas/autuante/autuante_listar_por_numero.jsp">Número autuante</a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->   
                        <div class="panel-body">

                            <form action="autuante_listar_por_nome.jsp" method="post">
                                <!-- Div com o campo de pesquisa -->
                                <div class="form-group input-group">
                                    <input type="search" name="nome_autuante" class="form-control" required placeholder="Nome">
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                                <!-- Fim da div com o campo de pesquisa -->
                            </form>

                            <%                                AutuanteDAO autuanteDAO = new AutuanteDAO();
                                String nome = request.getParameter("nome_autuante");
                                List<Autuante> autuantes = autuanteDAO.findByNome(nome);

                            %>

                            <form method="post">
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
                                                <th class="text-primary">Patente</th>
                                                <th class="text-primary" colspan="4">Operações</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%for (Autuante autuante : autuantes) {%>
                                            <tr>
                                                <td><%=autuante.getIdAutuante()%></td>
                                                <td><%=autuante.getNomeAutuante()%></td>
                                                <td><%=autuante.getBiAutuante()%></td>
                                                <td><%=autuante.getResidenciaAutuante()%></td>                                                
                                                <td><%=DateUtil.formataData(autuante.getDataNascimentoAutuante())%></td>
                                                <td><%=autuante.getSexo().getExtensao()%></td>
                                                <td><%=autuante.getTelefoneAutuante()%></td>
                                                <td><%=autuante.getPatente().getNomePatente()%></td>
                                                <td>
                                                    <a href="autuanteServlet?comando=detalhes&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-print"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="autuanteServlet?comando=detalhes&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-zoom-in"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="autuanteServlet?comando=prepara_editar&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="autuanteServlet?comando=eliminar&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-trash"></span>
                                                    </a>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
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
    </body>
</html>

