<%-- 
    Document   : autuante_listar
    Created on : 24/02/2019, 12:16:37
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Autuante"%>
<%@page import="java.util.List"%>
<%@page import="dao.AutuanteDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autuante</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            AutuanteDAO autuanteDAO = new AutuanteDAO();
            List<Autuante> autuantes = autuanteDAO.findAll();
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary" title="Registar autuante"><a href="autuante_registo.jsp">Autuante</a></h1>
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
                                    <li><a href="<%=request.getContextPath()%>/autuantes"> <span class="glyphicon glyphicon-print"> Imprimir </span> </a></li>
                                    <li><a href="autuante_listar_por_nome.jsp"> <span class="glyphicon glyphicon-search"> Pesquisar </span> </a></li>
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
                                                <td><%=autuante.getSexo()%></td>
                                                <td><%=autuante.getTelefoneAutuante()%></td>
                                                <td><%=autuante.getPatente().getNomePatente()%></td>
                                                <td>
                                                    <a href="<%= request.getContextPath()%>/autuanteServlet?comando=detalhes&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-print"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/autuanteServlet?comando=detalhes&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-zoom-in"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/autuanteServlet?comando=prepara_editar&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/autuanteServlet?comando=eliminar&id_autuante=<%=autuante.getIdAutuante()%>">
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
