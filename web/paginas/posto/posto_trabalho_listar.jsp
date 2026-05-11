<%-- 
    Document   : posto_trabalho_listar
    Created on : 26/02/2019, 14:08:39
    Author     : user
--%>

<%@page import="modelo.PostoTrabalho"%>
<%@page import="java.util.List"%>
<%@page import="dao.PostoTrabalhoDAO"%>
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
            List<PostoTrabalho> postoTrabalhos = postoTrabalhoDAO.findAll();
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary" title="Registar posto trabalho"><a href="paginas/posto/posto_trabalho_registo.jsp">Posto de Trabalho</a></h1>
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
                                    <li><a href="listaOcorrencias"> <span class="glyphicon glyphicon-print"> Imprimir </span> </a></li>
                                    <li><a href="paginas/posto/posto_trabalho_listar_por_nome.jsp"> <span class="glyphicon glyphicon-search"> Pesquisar </span> </a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <form>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-primary">#</th>
                                                <th class="text-primary">Nome</th>
                                                <th class="text-primary">Número</th>
                                                <th class="text-primary">Município</th>
                                                <th class="text-primary" colspan="4">Operações</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%for (PostoTrabalho postoTrabalho : postoTrabalhos) {%>
                                            <tr>
                                                <td><%=postoTrabalho.getIdPostoTrabalho()%></td>
                                                <td><%=postoTrabalho.getNomePostoTrabalho()%></td>
                                                <td><%=postoTrabalho.getNumeroPostoTrabalho()%></td>
                                                <td><%=postoTrabalho.getMunicipio().getNomeMunicipio()%></td>

                                                <td>
                                                    <a href="postoTrabalhoServlet?comando=detalhes&id_posto_trabalho=<%=postoTrabalho.getIdPostoTrabalho()%>">
                                                        <span class="glyphicon glyphicon-print"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="postoTrabalhoServlet?comando=detalhes&id_posto_trabalho=<%=postoTrabalho.getIdPostoTrabalho()%>">
                                                        <span class="glyphicon glyphicon-zoom-in"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="postoTrabalhoServlet?comando=prepara_editar&id_posto_trabalho=<%=postoTrabalho.getIdPostoTrabalho()%>">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="postoTrabalhoServlet?comando=eliminar&id_posto_trabalho=<%=postoTrabalho.getIdPostoTrabalho()%>">
                                                        <span class="glyphicon glyphicon-trash"></span>
                                                    </a>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                    <!-- Paginação -->
                                    <ul class="pagination"> 
                                        <li><a href="#">&laquo;</a></li> 
                                        <li><a href="#">1</a></li>
                                        <li><a href="#">2</a></li> 
                                        <li><a href="#">3</a></li> 
                                        <li><a href="#">4</a></li> 
                                        <li><a href="#">5</a></li> 
                                        <li><a href="#">&raquo;</a></li> 
                                    </ul>
                                    <!-- Fim da Paginação-->
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
