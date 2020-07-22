<%-- 
    Document   : municipio_editar
    Created on : 17/01/2019, 11:08:49
    Author     : user
--%>

<%@page import="modelo.Provincia"%>
<%@page import="modelo.Provincia"%>
<%@page import="dao.ProvinciaDAO"%>
<%@page import="modelo.Municipio"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Municipio</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
        <script src="municipio_validador.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            Municipio municipio = (Municipio) request.getAttribute("municipio");
            ProvinciaDAO provinciaDAO = new ProvinciaDAO();
            List<Provincia> provincias = provinciaDAO.findAll();
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
                                Editar dados do Município: <strong> <%=municipio.getNomeMunicipio()%> </strong>
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
                                <form role="form" accept-charset="ISO-8859-1, UTF-8" action="<%=request.getContextPath()%>/municipioServlet?comando=editar" method="POST">
                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary">Id:</label>
                                            <input type="text" class="form-control" name="id_municipio" id="id_municipio" value="<%=municipio.getIdMunicipio()%>" readonly="readonly"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="nome_municipio"> &lowast; Nome:</label>
                                            <input type="text" class="form-control" id="nome_municipio" name="nome_municipio"  value="<%=municipio.getNomeMunicipio()%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                               
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="select_provincia"> &lowast; Província:</label>
                                            <select class="form-control" id="select_municipio" name="select_provincia">
                                                <% for (Provincia provincia : provincias) {%>
                                                <option value="<%=provincia.getIdProvincia()%>">
                                                    <%=provincia.getNomeProvincia()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-info">Update</label>
                                            <button type="submit" class="form-control btn btn-primary" onclick="municipioValidador()">Actualizar</button>
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
                <%@include file="../../menus/rodape.jsp" %>
                <!-- Fim do Rodapé-->

            </div>
            <!-- Fim da Linha de Divisão -->
        </div>
        <!-- Fim do Container -->
    </body>
</html>
