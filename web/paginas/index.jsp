<%-- 
    Document   : index
    Created on : 08/12/2019, 07:55:55
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <base href="<%=request.getContextPath()%>/">

        <title>Login</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>

    <body>
        <div class="container">

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../components/cabecalho.jsp" %>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">

                        <div class="panel-heading">
                            <h4 class="text-primary" style="text-align: center">
                                Comando da Polícia Nacional de Angola
                            </h4>
                        </div>

                        <div class="panel-body">
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

                                        <a href="<%=request.getContextPath()%>/paginas/ocorrencia/ocorrencia_registo.jsp">
                                            <div class="panel-footer">
                                                <span class="pull-left">Registar</span>
                                                <span class="pull-right">
                                                    <i class="glyphicon glyphicon-circle-arrow-right"></i>
                                                </span>
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

                                        <a href="<%=request.getContextPath()%>/paginas/autuado/autuado_registo.jsp">
                                            <div class="panel-footer">
                                                <span class="pull-left">Registar</span>
                                                <span class="pull-right">
                                                    <i class="glyphicon glyphicon-circle-arrow-right"></i>
                                                </span>
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

                                        <a href="<%=request.getContextPath()%>/paginas/autuante/autuante_registo.jsp">
                                            <div class="panel-footer">
                                                <span class="pull-left">Registar</span>
                                                <span class="pull-right">
                                                    <i class="glyphicon glyphicon-circle-arrow-right"></i>
                                                </span>
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

                                        <a href="<%=request.getContextPath()%>/paginas/testemunha/testemunha_registo.jsp">
                                            <div class="panel-footer">
                                                <span class="pull-left">Registar</span>
                                                <span class="pull-right">
                                                    <i class="glyphicon glyphicon-circle-arrow-right"></i>
                                                </span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </a>
                                    </div>
                                </div>

                            </div>

                            <img
                                class="img-responsive img-rounded center-block"
                                src="<%=request.getContextPath()%>/img/logo.jpg"
                                alt="Logotipo da Polícia Nacional de Angola"
                                />
                        </div>
                    </div>
                </div>

                <%@include file="../components/rodape.jsp" %>
            </div>
        </div>
    </body>
</html>