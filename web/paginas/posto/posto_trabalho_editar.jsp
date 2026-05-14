<%-- 
    Document   : posto_trabalho_editar
    Created on : 26/02/2019, 14:09:11
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="modelo.Municipio"%>
<%@page import="dao.MunicipioDAO"%>
<%@page import="modelo.PostoTrabalho"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <base href="<%=request.getContextPath()%>/"> 

        <title>Posto de Trabalho</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            PostoTrabalho postoTrabalho = (PostoTrabalho) request.getAttribute("postoTrabalho");

            MunicipioDAO municipioDAO = new MunicipioDAO();
            List<Municipio> municipios = municipioDAO.findAll();
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>
                        <h1 class="page-header text-primary">Posto de Trabalho</h1>
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
                                Editar dados do Posto de Trabalho: <strong> <%=postoTrabalho.getNomePostoTrabalho()%> </strong>
                            </p>         
                            <div class="alert alert-success">
                                <p>${message}</p>
                            </div>
                            <p class="text-info"> &lowast; Campos de preenchimento obrigatório</p>
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <!-- Área do corpo-->
                            <div class="col-lg-12">
                                <form role="form" accept-charset="ISO-8859-1, UTF-8" action="postoTrabalhoServlet?comando=editar" method="POST">
                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary">Id:</label>
                                            <input type="text" class="form-control" name="id_posto_trabalho" id="id_posto_trabalho" value="<%=postoTrabalho.getIdPostoTrabalho()%>" readonly="readonly"/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="nome_posto_trabalho"> &lowast; Nome do Posto de Trabalho: </label>
                                            <input type="text" class="form-control" id="nome_posto_trabalho" name="nome_posto_trabalho" value="<%=postoTrabalho.getNomePostoTrabalho()%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="numero_posto_trabalho"> &lowast; Número do Posto de Trabalho: </label>
                                            <input type="text" class="form-control" id="numero_posto_trabalho" name="numero_posto_trabalho" value="<%=postoTrabalho.getNumeroPostoTrabalho()%>" required/>
                                        </div>
                                    </div> 

                                    <div class="form-group">                                               
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="select_municipio"> &lowast; Município:</label>
                                            <select class="form-control" id="select_municipio" name="select_municipio">
                                                <% for (Municipio municipio : municipios) {%>
                                                <option value="<%=municipio.getIdMunicipio()%>">
                                                    <%=municipio.getNomeMunicipio()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-info">Save</label>
                                            <button type="submit" class="form-control btn btn-primary" onclick="postoTrabalhoValidador()">Salvar</button>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-info">Clean</label>
                                            <button type="reset" class="form-control btn btn-primary">Limpar</button>
                                        </div>
                                    </div>
                                </form>    
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
