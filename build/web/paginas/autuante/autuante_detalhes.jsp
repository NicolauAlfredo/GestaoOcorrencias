<%-- 
    Document   : autuante_detalhes
    Created on : 24/02/2019, 11:51:49
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Autuante"%>
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
            Autuante autuante = (Autuante) request.getAttribute("autuante");
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary">Autuante</h1>
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
                                Detalhes do autuante:  <strong> <%=autuante.getNomeAutuante()%> </strong> 
                            </p>
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <!-- Área do corpo-->
                            <div class="col-lg-12">
                                <ul type="none">
                                    <li>
                                        <p class="text-primary">
                                            Id: <strong> <%=autuante.getIdAutuante()%> </strong>
                                        </p>
                                    </li>
                                    
                                    <li>
                                        <p class="text-primary">
                                            Nome do Pai: <strong> <%=autuante.getPaiAutuante()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Nome da Mãe: <strong> <%=autuante.getMaeAutuante()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Bilhete de Identidade Nº: <strong> <%=autuante.getBiAutuante()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Residência: <strong> <%=autuante.getResidenciaAutuante()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Naturalidade: <strong> <%=autuante.getMunicipio().getNomeMunicipio()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Data de Nascimento: <strong> <%=DateUtil.formataData(autuante.getDataNascimentoAutuante())%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Sexo: <strong> <%=autuante.getSexo().getExtensao()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Altura (m): <strong> <%=autuante.getAlturaAutuante()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Data de Emissão do B.I.: <strong> <%=DateUtil.formataData(autuante.getDataEmissaoBiAutuante())%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Data de Válidade do B.I.: <strong> <%=DateUtil.formataData(autuante.getDataValidadeBiAutuante())%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            NIP: <strong> <%=autuante.getNipAutuante()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Número de Telefone: <strong> <%=autuante.getTelefoneAutuante()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Patente: <strong> <%=autuante.getPatente().getNomePatente()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Posto de Trabalho: <strong> <%=autuante.getPostoTrabalho().getNomePostoTrabalho()%> </strong>
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
