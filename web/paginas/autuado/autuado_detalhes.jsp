<%-- 
    Document   : autuante_detalhes
    Created on : 24/02/2019, 11:51:49
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Autuado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Autuado</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            Autuado autuado = (Autuado) request.getAttribute("autuado");
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary">Autuado</h1>
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
                                Detalhes do autuado:  <strong> <%=autuado.getNomeAutuado()%> </strong> 
                            </p>
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <!-- Área do corpo-->
                            <div class="col-lg-12">
                                <ul type="none">
                                    <li>
                                        <p class="text-primary">
                                            Id: <strong> <%=autuado.getIdAutuado()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Nome do Pai: <strong> <%=autuado.getPaiAutuado()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Nome da Mãe: <strong> <%=autuado.getMaeAutuado()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Bilhete de Identidade Nº: <strong> <%=autuado.getBiAutuado()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Residência: <strong> <%=autuado.getResidenciaAutuado()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Proxómo de: <strong> <%=autuado.getProximidadeAutuado()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Naturalidade: <strong> <%=autuado.getMunicipio().getNomeMunicipio()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Data de Nascimento: <strong> <%=DateUtil.formataData(autuado.getDataNascimentoAutuado())%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Sexo: <strong> <%=autuado.getSexo().getExtensao()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Data de Emissão do B.I.: <strong> <%=DateUtil.formataData(autuado.getDataEmissaoBiAutuado())%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Data de Válidade do B.I.: <strong> <%=DateUtil.formataData(autuado.getDataValidadeBiAutuado())%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Número de Telefone: <strong> <%=autuado.getTelefoneAutuado()%> </strong>
                                        </p>
                                    </li>

                                    <li>
                                        <p class="text-primary">
                                            Profissão: <strong> <%=autuado.getProfissao().getNomeProfissao()%> </strong>
                                        </p>
                                    </li>  

                                    <li>
                                        <a href="autuadoPorId?id_autuado=<%=autuado.getIdAutuado()%>" title="Imprimir">
                                            <span class="glyphicon glyphicon-print"></span>
                                        </a>
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
