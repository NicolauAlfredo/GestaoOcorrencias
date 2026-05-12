<%-- 
    Document   : autuante_registo
    Created on : 24/02/2019, 12:16:11
    Author     : user
--%>

<%@page import="modelo.Profissao"%>
<%@page import="dao.ProfissaoDAO"%>
<%@page import="modelo.Sexo"%>
<%@page import="dao.MunicipioDAO"%>
<%@page import="modelo.Municipio"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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
            MunicipioDAO municipioDAO = new MunicipioDAO();
            List<Municipio> municipios = municipioDAO.findAll();

            ProfissaoDAO profissaoDAO = new ProfissaoDAO();
            List<Profissao> profissoes = profissaoDAO.findAll();
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary" title="Ver registos"><a href="paginas/autuado/autuado_listar.jsp">Autuado</a></h1>
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
                                Registo de Autuado 
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
                                <form name="form_autuado" accept-charset="ISO-8859-1, UTF-8" role="form" action="autuadoServlet?comando=guardar" method="POST">
                                    <div class="form-group">                                                  
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="nome_autuado"> &lowast; Nome Completo: </label>
                                            <input type="text" class="form-control" id="nome_autuado" name="nome_autuado" required/>
                                        </div>   
                                    </div>

                                    <div class="form-group">                                  
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="pai_autuado"> Nome do Pai: </label>
                                            <input type="text" class="form-control" id="pai_autuado" name="pai_autuado"/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="mae_autuado"> Nome da Mãe: </label>
                                            <input type="text" class="form-control" id="mae_autuado" name="mae_autuado"/>
                                        </div>
                                    </div>                                 

                                    <div class="form-group"> 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="bi_autuado"> &lowast; Bilhete de Identidade Nº: </label>
                                            <input type="text" class="form-control" id="bi_autuado" name="bi_autuado" required pattern="^[0-9]{9}[A-Z]{2}[0-9]{3}$"/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                                                                                                                                
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="residencia_autuado"> &lowast; Residência: </label>
                                            <input type="text" class="form-control" id="residencia_autuado" name="residencia_autuado"/>
                                        </div>
                                    </div>  

                                    <div class="form-group">
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="sexo_autuado"> &lowast; Sexo:</label>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="sexo_autuado" id="sexo_autuado" value="<%= Sexo.FEMININO.getExtensao()%>"/><%=Sexo.FEMININO.getExtensao()%>       
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="sexo_autuado" id="sexo_autuado" value="<%= Sexo.MASCULINO.getExtensao()%>" checked/><%=Sexo.MASCULINO.getExtensao()%>
                                                </label>
                                            </div>
                                        </div>
                                    </div>  

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="proximidade_autuado"> &lowast; Próximo de: </label>
                                            <input type="text" class="form-control" id="proximidade_autuado" name="proximidade_autuado" required/>
                                        </div>
                                    </div> 

                                    <div class="form-group">                                               
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="select_municipio_autuado"> &lowast; Natural de:</label>
                                            <select class="form-control" id="select_municipio_autuado" name="select_municipio_autuado">
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
                                            <label class="text-primary" for="data_nascimento_autuado"> &lowast; Data de nascimento:</label>
                                            <input type="text" class="form-control" id="data_nascimento_autuado" name="data_nascimento_autuado" placeholder="dd/MM/yyyy" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="data_emissao_bi_autuado"> &lowast; B.I. Emitido em:</label>
                                            <input type="text" class="form-control" id="data_emissao_bi_autuado" name="data_emissao_bi_autuado" placeholder="dd/MM/yyyy" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="data_validade_bi_autuado"> &lowast; B.I. Válido até:</label>
                                            <input type="text" class="form-control" id="data_validade_bi_autuado" name="data_validade_bi_autuado" placeholder="dd/MM/yyyy" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="estado_civil_autuado"> &lowast; Estado cívil: </label>
                                            <input type="text" class="form-control" id="estado_civil_autuado" name="estado_civil_autuado" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="telefone_autuado">Contacto: </label>
                                            <input type="text" class="form-control" id="telefone_autuado" name="telefone_autuado" pattern="^[9-9]{1}[1-9]{2}[0-9]{3}[0-9]{3}$"/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="select_profissao_autuado">Profissão:</label>
                                            <select class="form-control" id="select_profissao_autuado" name="select_profissao_autuado">
                                                <% for (Profissao profissao : profissoes) {%>
                                                <option value="<%=profissao.getIdProfissao()%>">
                                                    <%=profissao.getNomeProfissao()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-info">Save</label>
                                            <button type="submit" class="form-control btn btn-primary" onclick="autuadoValidador()"> Salvar </button>
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
