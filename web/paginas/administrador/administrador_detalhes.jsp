<%-- 
    Document   : login_detalhes
    Created on : 22/06/2020, 15:13:19
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    </head>
    <body>
       <%
            Administrador administrador = (Administrador) request.getAttribute("administrador");
        %>
        
        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary">Município</h1>
                    </div>
                </div>
            </div>

            <!-- Linha de divisão -->
            <div class="row">
                <!-- Área da linha -->
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="text-primary"> 
                                Detalhes do Administrador: <strong> <%=administrador.getNomeAdministrador()%> </strong> 
                            </p>
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <!-- Área do corpo-->
                            <div class="col-lg-12">
                                <ul type="none">
                                    <li>
                                        <p class="text-primary">
                                            Id: <strong> <%=administrador.getIdAdministrador()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Nome: <strong> <%=administrador.getNomeAdministrador()%> </strong>
                                        </p>
                                    </li>
                                    
                                    <li>
                                        <p class="text-primary">
                                            Data de Nascimento: <strong> <%=DateUtil.formataData(administrador.getDataNascimentoAdministrador())%> </strong>
                                        </p>
                                    </li>
                                    
                                    <li>
                                        <p class="text-primary">
                                            Sexo: <strong> <%=administrador.getSexo().getExtensao()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                              Bilhete de Identidade Nº: <strong> <%=administrador.getBiAdministrador()%> </strong>
                                        </p>
                                    </li>
                                    
                                    <li>
                                        <p class="text-primary">
                                            Email: <strong> <%=administrador.getEmailAdministrador()%> </strong>
                                        </p>
                                    </li>
                                    
                                    <li>
                                        <p class="text-primary">
                                              Número de Telefone: <strong> <%=administrador.getTelefoneAdministrador()%> </strong>
                                        </p>
                                    </li>
                                    
                                </ul>
                            </div>
                            <!-- Fim da área do Corpo -->
                        </div>
                        <!-- Fim do Corpo -->
                    </div>
                </div>
                <!-- Fim da área da linha -->

                <!-- Rodapé -->
                <%@include file="../../menus/rodape.jsp" %>
                <!-- Fim do Rodapé-->

            </div>   
            <!-- Fim da Linha de Divisão -->
        </div>
        <!-- Fim do Container -->
    </body>
</html>
