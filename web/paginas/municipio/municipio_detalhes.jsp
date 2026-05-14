<%-- 
    Document   : municipio_detalhes
    Created on : 20/01/2019, 18:31:52
    Author     : user
--%>

<%@page import="modelo.Municipio"%>
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
            Municipio municipio = (Municipio) request.getAttribute("municipio");
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>
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
                                Detalhes do Município: <strong> <%=municipio.getNomeMunicipio()%> </strong> 
                            </p>
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <!-- Área do corpo-->
                            <div class="col-lg-12">
                                <ul type="none">
                                    <li>
                                        <p class="text-primary">
                                            Id: <strong> <%=municipio.getIdMunicipio()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Província: <strong> <%=municipio.getProvincia().getNomeProvincia()%> </strong>
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
                <%@include file="../../components/rodape.jsp" %>
                <!-- Fim do Rodapé-->

            </div>   
            <!-- Fim da Linha de Divisão -->
        </div>
        <!-- Fim do Container -->
    </body>
</html>
