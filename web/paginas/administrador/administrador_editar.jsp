<%-- 
    Document   : login_editar
    Created on : 22/06/2020, 15:13:05
    Author     : user
--%>

<%@page import="modelo.Sexo"%>
<%@page import="modelo.DateUtil"%>
<%@page import="dao.AdministradorDAO"%>
<%@page import="modelo.Administrador"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Administrador</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            Administrador administrador = (Administrador) request.getAttribute("administrador");
            AdministradorDAO administradorDAO = new AdministradorDAO();
            List<Administrador> administradores = administradorDAO.findAll();
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>
                        <h1 class="page-header text-primary" title="Ver registos"><a href="paginas/administrador/login_listar.jsp">Administrador</a></h1>
                        <div class="alert alert-success">
                            <p>${message}</p>
                        </div>
                        <p class="text-info"> &lowast; Campos de preenchimento obrigatório</p>
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
                                Editar dados do Município: <strong> <%=administrador.getNomeAdministrador()%> </strong>
                            </p>
                        </div>

                        <!-- Corpo da página -->
                        <div class="panel-body">
                            <!-- Área do corpo-->
                            <div class="col-lg-12">
                                <form role="form" accept-charset="ISO-8859-1, UTF-8" name="form_administrador" action="administradorServlet?comando=editar" method="POST">
                                    <div class="form-group">                                                
                                        <div class="col-xs-6">
                                            <label class="text-primary"> Id: </label>
                                            <input type="text" class="form-control" id="id_administrador" name="id_administrador" value="<%=administrador.getIdAdministrador()%>" readonly="readonly"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="nome_administrador"> &lowast; Nome: </label>
                                            <input type="text" class="form-control" id="nome_administrador" name="nome_administrador" value="<%=administrador.getNomeAdministrador()%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="data_nascimento_administrador"> &lowast; Data de nascimento: </label>
                                            <input type="text" class="form-control" id="data_nascimento_administrador" name="data_nascimento_administrador" value="<%=DateUtil.formataData(administrador.getDataNascimentoAdministrador())%>" placeholder="dd/MM/yyyy" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="sexo_administrador"> &lowast; Sexo:</label>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="sexo_administrador" id="sexo_administrador" value="<%= Sexo.FEMININO.getExtensao()%>"/><%=Sexo.FEMININO.getExtensao()%>       
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="sexo_administrador" id="sexo_administrador" value="<%= Sexo.MASCULINO.getExtensao()%>" checked/><%=Sexo.MASCULINO.getExtensao()%>
                                                </label>
                                            </div>
                                        </div>
                                    </div>  

                                    <div class="form-group"> 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="bi_administrador"> &lowast; Bilhete de Identidade Nº: </label>
                                            <input type="text" class="form-control" id="bi_administrador" name="bi_administrador" value="<%=administrador.getBiAdministrador()%>" required pattern="^[0-9]{9}[A-Z]{2}[0-9]{3}$"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="nip_administrador"> &lowast; NIP: </label>
                                            <input type="text" class="form-control" id="nip_administrador" name="nip_administrador" value="<%=administrador.getNipAdministrador()%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="email_administrador"> E-mail: </label>
                                            <input type="email" class="form-control" id="email_administrador" name="email_administrador" value="<%=administrador.getEmailAdministrador()%>"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="telefone_administrador"> Nº Telefone:</label>
                                            <input type="text" class="form-control" id="telefone_administrador" name="telefone_administrador" value="<%=administrador.getTelefoneAdministrador()%>" pattern="^[9-9]{1}[1-9]{2}[0-9]{3}[0-9]{3}$"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="palavra_passe_administrador"> &lowast; Palavra-passe:</label>
                                            <input type="text" class="form-control" id="palavra_passe_administrador" name="palavra_passe_administrador" value="<%=administrador.getPalavraPasseAdministrador()%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-info">Update</label>
                                            <button type="submit" class="form-control btn btn-primary" onclick="loginValidador()">Actualizar</button>
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
