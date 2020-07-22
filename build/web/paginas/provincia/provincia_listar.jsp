<%-- 
    Document   : provincia_listar
    Created on : 17/01/2019, 10:02:42
    Author     : user
--%>

<%@page import="modelo.Provincia"%>
<%@page import="dao.ProvinciaDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Província</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            ProvinciaDAO provinciaDAO = new ProvinciaDAO();
            List<Provincia> provincias = provinciaDAO.findAll();
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary" title="Registar província"><a href="provincia_registo.jsp">Província</a></h1>
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
                                    <li><a href="<%=request.getContextPath()%>/listaOcorrencias"> <span class="glyphicon glyphicon-print"> Imprimir </span> </a></li>
                                    <li><a href="ocorrencia_listar_por_autuado.jsp"> <span class="glyphicon glyphicon-search"> Pesquisar </span> </a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <a href="provincia_listar_por_nome.jsp">
                                <p style="text-align: right"> <span class="glyphicon glyphicon-search"> Pesquisar </span> </p>
                            </a>
                            <form>
                                <div class="table-responsive">                        
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-primary">#</th>
                                                <th class="text-primary">Nome</th>
                                                <th class="text-primary" colspan="4">Operações</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%for (Provincia provincia : provincias) {%>
                                            <tr>
                                                <td><%=provincia.getIdProvincia()%></td>
                                                <td><%=provincia.getNomeProvincia()%></td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/provinciaServlet?comando=detalhes&id_provincia=<%=provincia.getIdProvincia()%>">
                                                        <span class="glyphicon glyphicon-print"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/provinciaServlet?comando=detalhes&id_provincia=<%=provincia.getIdProvincia()%>">
                                                        <span class="glyphicon glyphicon-zoom-in"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/provinciaServlet?comando=prepara_editar&id_provincia=<%=provincia.getIdProvincia()%>">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/provinciaServlet?comando=eliminar&id_provincia=<%=provincia.getIdProvincia()%>">
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
