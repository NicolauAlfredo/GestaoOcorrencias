<%-- 
    Document   : index
    Created on : 08/12/2019, 07:55:55
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comando da Polícia Nacional de Angola</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    </head>
    <body>
        <!-- Container principal do Bootstrap -->
        <div class="container">            

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../menus/cabecalho.jsp" %>                        
                    </div>                 
                </div>
            </div>

            <!-- Linha de divisão -->
            <div class="row">
                <!-- Área da linha -->
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">                           
                            <h4 class="text-primary" style="text-align: center"> 
                                Comando da Polícia Nacional de Angola 
                            </h4>                                                  
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <!-- Área nova -->
                            <div class="row">
                                <div class="col-lg-3 col-md-6">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="glyphicon glyphicon-edit"></i>
                                                </div>
                                                <div class="col-xs-9 text-right">
                                                    <div>Nova Ocorrência</div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="<%=request.getContextPath()%>ocorrencia/ocorrencia_registo.jsp">
                                            <div class="panel-footer">
                                                <span class="pull-left">Registar</span>
                                                <span class="pull-right"><i class="glyphicon glyphicon-circle-arrow-right"></i></span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>

                                <div class="col-lg-3 col-md-6">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="glyphicon glyphicon-user"></i>
                                                </div>
                                                <div class="col-xs-9 text-right">
                                                    <div>Novo Autuado</div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="<%=request.getContextPath()%>autuado/autuado_registo.jsp">
                                            <div class="panel-footer">
                                                <span class="pull-left">Registar</span>
                                                <span class="pull-right"><i class="glyphicon glyphicon-circle-arrow-right"></i></span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>

                                <div class="col-lg-3 col-md-6">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="glyphicon glyphicon-user"></i>
                                                </div>
                                                <div class="col-xs-9 text-right">
                                                    <div>Novo Autuante</div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="<%=request.getContextPath()%>autuante/autuante_registo.jsp">
                                            <div class="panel-footer">
                                                <span class="pull-left">Registar</span>
                                                <span class="pull-right"><i class="glyphicon glyphicon-circle-arrow-right"></i></span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>      

                                <div class="col-lg-3 col-md-6">
                                    <div class="panel panel-primary">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="glyphicon glyphicon-user"></i>
                                                </div>
                                                <div class="col-xs-9 text-right">
                                                    <div>Nova Testemunha</div>
                                                </div>
                                            </div>
                                        </div>
                                        <a href="<%=request.getContextPath()%>testemunha/testemunha_registo.jsp">
                                            <div class="panel-footer">
                                                <span class="pull-left">Registar</span>
                                                <span class="pull-right"><i class="glyphicon glyphicon-circle-arrow-right"></i></span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>      
                            </div> 
                            <!-- Fim da nova área -->

                            <!-- lógotipo -->                            
                            <img class="img-responsive img-rounded center-block" src="<%=request.getContextPath()%>/img/logo.jpg">              
                            <!-- Fim do logotipo -->
                        </div>
                        <!-- Fim do corpo da página -->
                    </div>                   
                </div> 
                <!-- Fim da área da linha-->

                <!-- Rodapé -->
                <%@include file="../menus/rodape.jsp" %>
                <!-- Fim do Rodapé-->

            </div>
            <!-- Fim da linha de divisão -->
        </div>
        <!-- Fim do Container -->    
    </body>
</html>
