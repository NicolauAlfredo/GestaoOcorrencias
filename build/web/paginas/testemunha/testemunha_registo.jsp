<%-- 
    Document   : testemunha_registar
    Created on : 24/02/2019, 21:30:38
    Author     : user
--%>

<%@page import="modelo.Profissao"%>
<%@page import="dao.ProfissaoDAO"%>
<%@page import="modelo.Sexo"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Municipio"%>
<%@page import="modelo.Municipio"%>
<%@page import="dao.MunicipioDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Testemunha</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
        <script src="testemunha_validador.js" type="text/javascript"></script>
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
                        <h1 class="page-header text-primary" title="Ver registos"><a href="testemunha_listar.jsp">Testemunha</a></h1>
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
                                Registo de Testemunha 
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
                                <form name="form_testemunha" accept-charset="ISO-8859-1, UTF-8" role="form" action="<%=request.getContextPath()%>/testemunhaServlet?comando=guardar" method="POST">
                                    <div class="form-group">                                                  
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="nome_testemunha"> &lowast; Nome Completo: </label>
                                            <input type="text" class="form-control" id="nome_testemunha" name="nome_testemunha" required/>
                                        </div>   
                                    </div>    

                                    <div class="form-group">                                                  
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="pai_testemunha"> Nome do Pai: </label>
                                            <input type="text" class="form-control" id="pai_testemunha" name="pai_testemunha" required/>
                                        </div>   
                                    </div>    

                                    <div class="form-group">                                                  
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="mae_testemunha"> Nome da Mãe: </label>
                                            <input type="text" class="form-control" id="mae_testemunha" name="mae_testemunha" required/>
                                        </div>   
                                    </div>    

                                    <div class="form-group"> 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="bi_testemunha"> &lowast; Bilhete de Identidade Nº: </label>
                                            <input type="text" class="form-control" id="bi_testemunha" name="bi_testemunha" required pattern="^[0-9]{9}[A-Z]{2}[0-9]{3}$"/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                  
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="residencia_testemunha"> &lowast; Residência: </label>
                                            <input type="text" class="form-control" id="residencia_testemunha" name="residencia_testemunha" required/>
                                        </div>   
                                    </div>    

                                    <div class="form-group">                                               
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="select_municipio_testemunha"> &lowast; Natural de:</label>
                                            <select class="form-control" id="select_municipio_testemunha" name="select_municipio_testemunha">
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
                                            <label class="text-primary" for="select_profissao_testemunha"> &lowast; Profissão:</label>
                                            <select class="form-control" id="select_profissao_testemunha" name="select_profissao_testemunha">
                                                <% for (Profissao profissao : profissoes) {%>
                                                <option value="<%=profissao.getIdProfissao()%>">
                                                    <%=profissao.getNomeProfissao()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="sexo_testemunha"> &lowast; Sexo:</label>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="sexo_testemunha" id="sexo_testemunha" value="<%= Sexo.FEMININO.getExtensao()%>"/><%=Sexo.FEMININO.getExtensao()%>       
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="sexo_testemunha" id="sexo_testemunha" value="<%= Sexo.MASCULINO.getExtensao()%>" checked/><%=Sexo.MASCULINO.getExtensao()%>
                                                </label>
                                            </div>
                                        </div>
                                    </div>   

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="data_nascimento_testemunha"> &lowast; Data de Nascimento:</label>
                                            <input type="text" class="form-control" id="data_nascimento_testemunha" name="data_nascimento_testemunha" placeholder="dd/MM/yyyy" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                  
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="altura_testemunha"> &lowast; Altura (m): </label>
                                            <input type="text" class="form-control" id="altura_testemunha" name="altura_testemunha" required/>
                                        </div>   
                                    </div> 

                                    <div class="form-group">                                                  
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="estado_civil_testemunha"> &lowast; Estado Civil: </label>
                                            <input type="text" class="form-control" id="estado_civil_testemunha" name="estado_civil_testemunha" required/>
                                        </div>   
                                    </div> 

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="data_emissao_bi_testemunha"> &lowast; B.I. Emitido em:</label>
                                            <input type="text" class="form-control" id="data_emissao_bi_testemunha" name="data_emissao_bi_testemunha" placeholder="dd/MM/yyyy" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="data_validade_bi_testemunha"> &lowast; B.I. Válido até:</label>
                                            <input type="text" class="form-control" id="data_validade_bi_testemunha" name="data_validade_bi_testemunha" placeholder="dd/MM/yyyy" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="telefone_testemunha">Contacto: </label>
                                            <input type="text" class="form-control" id="telefone_testemunha" name="telefone_testemunha" pattern="^[9-9]{1}[1-9]{2}[0-9]{3}[0-9]{3}$"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-info">Save</label>
                                            <button type="submit" class="form-control btn btn-primary" onclick="testemunhaValidador()">Salvar</button>
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
