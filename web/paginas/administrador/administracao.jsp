<%-- 
    Document   : administracao
    Created on : 06/07/2020, 18:37:23
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administração</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>       
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp"%>  
                        <h1 class="page-header text-primary" title="Administração do sistema">Administração</h1>
                    </div>                 
                </div>
            </div>

            <div class="row">
                <!-- Área da linha -->
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <p class="text-primary"> 
                                Gestão do Sistema 
                            </p>
                        </div>

                        <ul class="list-group"> 
                            <li class="list-group-item"><a href="<%=request.getContextPath()%>/paginas/administrador/administrador_registo.jsp">Administrador</a></li>
                            <li class="list-group-item"><a href="<%=request.getContextPath()%>/paginas/municipio/municipio_registo.jsp">Município</a></li>
                            <li class="list-group-item"><a href="<%=request.getContextPath()%>/paginas/patente/patente_registo.jsp">Patente</a></li> 
                            <li class="list-group-item"><a href="<%=request.getContextPath()%>/paginas/posto/posto_trabalho_registo.jsp">Posto Trabalho</a></li>
                            <li class="list-group-item"><a href="<%=request.getContextPath()%>/paginas/profissao/profissao_registo.jsp">Profissão</a></li> 
                            <li class="list-group-item"><a href="<%=request.getContextPath()%>/paginas/provincia/provincia_registo.jsp">Província</a></li> 
                            <li class="list-group-item"><a href="<%=request.getContextPath()%>/paginas/tipo/tipo_ocorrencia_registo.jsp">Tipo Ocorrência</a></li> 
                        </ul>
                    </div>
                </div>
                <!-- Fim da Linha de Divisão -->

                <!-- Rodapé -->
                <%@include file="../../menus/rodape.jsp" %>
                <!-- Fim do Rodapé-->
            </div>
            <!-- Fim do Container -->    
    </body>
</html>
