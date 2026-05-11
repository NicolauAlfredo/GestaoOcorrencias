<%-- 
    Document   : testemunha_listar
    Created on : 02/03/2019, 15:37:59
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Testemunha"%>
<%@page import="dao.TestemunhaDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Testemunha</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
            List<Testemunha> testemunhas = testemunhaDAO.findAll();
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary" title="Registar testemunha"><a href="paginas/testemunha/testemunha_registo.jsp">Testemunha</a></h1>
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
                                    <li><a href="testemunhas"> <span class="glyphicon glyphicon-print"> Imprimir </span> </a></li>
                                    <li><a href="paginas/testemunha/testemunha_listar_por_nome.jsp"> <span class="glyphicon glyphicon-search"> Pesquisar </span> </a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <a href="paginas/testemunha/testemunha_listar_por_nome.jsp">
                                <p style="text-align: right"> <span class="glyphicon glyphicon-search"> Pesquisar </span> </p>
                            </a>
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
                                                <th class="text-primary" colspan="4">Operações</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%for (Testemunha testemunha : testemunhas) {%>
                                            <tr>
                                                <td><%=testemunha.getIdTestemunha()%></td>
                                                <td><%=testemunha.getNomeTestemunha()%></td>
                                                <td><%=testemunha.getBiTestemunha()%></td>
                                                <td><%=testemunha.getResidenciaTestemunha()%></td>
                                                <td><%=DateUtil.formataData(testemunha.getDataNascimentoTestemunha())%></td>
                                                <td><%=testemunha.getSexo().getExtensao()%></td>                                              
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
                                                    <a href="testemunhaServlet?comando=eliminar&id_testemunha=<%=testemunha.getIdTestemunha()%>">
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
