<%-- 
    Document   : ocorrencia_detalhes
    Created on : 24/02/2019, 19:29:32
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Ocorrencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Ocorrência</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            Ocorrencia ocorrencia = (Ocorrencia) request.getAttribute("ocorrencia");
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>
                        <h1 class="page-header text-primary">Ocorrência</h1>
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
                                Detalhes da ocorrência do Sr.: <strong> <%=ocorrencia.getAutuado().getNomeAutuado()%> </strong> 
                            </p>
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <!-- Área do corpo-->
                            <div class="col-lg-12">
                                <ul type="none">
                                    <li>
                                        <p class="text-primary">
                                            Id: <strong> <%=ocorrencia.getIdOcorrencia()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Data da ocorrência: <strong> <%=DateUtil.formataData(ocorrencia.getDataOcorrencia())%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Hora da Ocorrência: <strong> <%=ocorrencia.getHoraOcorrencia()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Descrição da Ocorrência: <strong> <%=ocorrencia.getDescricaoOcorrencia()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Rua da Ocorrência: <strong> <%=ocorrencia.getRuaOcorrencia()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Cidade da Ocorrência: <strong> <%=ocorrencia.getCidadeOcorrencia()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Bairro da Ocorrência: <strong> <%=ocorrencia.getBairroOcorrencia()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Proximidade de Localização: <strong> <%=ocorrencia.getProximidadeOcorrencia()%> </strong>
                                        </p>
                                    </li>       

                                    <li>
                                        <p class="text-primary">
                                            Autuado:
                                            <a href="autuadoServlet?comando=detalhes&id_autuado=<%=ocorrencia.getAutuado().getIdAutuado()%>">
                                                <strong> <%=ocorrencia.getAutuado().getNomeAutuado()%> </strong>
                                            </a>  
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Autuante:
                                            <a href="autuanteServlet?comando=detalhes&id_autuante=<%=ocorrencia.getAutuante().getIdAutuante()%>">
                                                <strong> <%=ocorrencia.getAutuante().getNomeAutuante()%> </strong>  
                                            </a>                                            
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Tipo de Ocorrência: <strong> <%=ocorrencia.getTipoOcorrencia().getNomeTipoOcorrencia()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            1º Testemunha:
                                            <a href="testemunhaServlet?comando=detalhes&id_testemunha=<%=ocorrencia.getTestemunha().getIdTestemunha()%>">
                                                <strong> <%=ocorrencia.getTestemunha().getNomeTestemunha()%> </strong>
                                            </a>                                             
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            2º Testemunha:
                                            <a href="testemunhaServlet?comando=detalhes&id_testemunha=<%=ocorrencia.getTestemunha().getIdTestemunha()%>">
                                                <strong> <%=ocorrencia.getTestemunha().getNomeTestemunha()%> </strong>
                                            </a>                                             
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Município: <strong> <%=ocorrencia.getMunicipio().getNomeMunicipio()%> </strong>
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
