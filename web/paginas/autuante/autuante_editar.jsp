<%-- 
    Document   : autuante_editar
    Created on : 24/02/2019, 12:02:29
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Sexo"%>
<%@page import="modelo.Patente"%>
<%@page import="dao.MunicipioDAO"%>
<%@page import="modelo.PostoTrabalho"%>
<%@page import="dao.PostoTrabalhoDAO"%>
<%@page import="java.util.List"%>
<%@page import="dao.PatenteDAO"%>
<%@page import="modelo.Autuante"%>
<%@page import="dao.PatenteDAO"%>
<%@page import="modelo.Municipio"%>
<%@page import="modelo.Municipio"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <base href="<%=request.getContextPath()%>/"> 

        <title>Autuante</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            Autuante autuante = (Autuante) request.getAttribute("autuante");

            PatenteDAO patenteDAO = new PatenteDAO();
            List<Patente> patentes = patenteDAO.findAll();

            MunicipioDAO municipioDAO = new MunicipioDAO();
            List<Municipio> municipios = municipioDAO.findAll();

            PostoTrabalhoDAO postoTrabalhoDAO = new PostoTrabalhoDAO();
            List<PostoTrabalho> postoTrabalhos = postoTrabalhoDAO.findAll();
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>
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
                                Editar dados do Autuante: <strong> <%=autuante.getNomeAutuante()%> </strong>
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
                                <form name="form_autuante" accept-charset="ISO-8859-1, UTF-8" role="form" action="autuanteServlet?comando=editar" method="POST">
                                    <div class="form-group">                                                
                                        <div class="col-xs-6">
                                            <label class="text-primary"> Id: </label>
                                            <input type="text" class="form-control" id="id_autuante" name="id_autuante" value="<%=autuante.getIdAutuante()%>" readonly="readonly"/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                  
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="nome_autuante"> &lowast; Nome Completo: </label>
                                            <input type="text" class="form-control" id="nome_autuante" name="nome_autuante" value="<%=autuante.getNomeAutuante()%>" required/>
                                        </div>   
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="pai_autuante"> Nome do Pai: </label>
                                            <input type="text" class="form-control" id="pai_autuante" name="pai_autuante" value="<%=autuante.getPaiAutuante()%>"/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="mae_autuante"> Nome da Mãe: </label>
                                            <input type="text" class="form-control" id="mae_autuante" name="mae_autuante" value="<%=autuante.getMaeAutuante()%>"/>
                                        </div>
                                    </div>                                 

                                    <div class="form-group"> 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="bi_autuante"> &lowast; Bilhete de Identidade Nº: </label>
                                            <input type="text" class="form-control" id="bi_autuante" name="bi_autuante" value="<%=autuante.getBiAutuante()%>" required pattern="^[0-9]{9}[A-Z]{2}[0-9]{3}$"/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="residencia_autuante"> &lowast; Residência: </label>
                                            <input type="text" class="form-control" id="residencia_autuante" name="residencia_autuante" value="<%=autuante.getResidenciaAutuante()%>"/>
                                        </div>
                                    </div>  

                                    <div class="form-group">                                               
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="select_municipio_autuante"> &lowast; Natural de:</label>
                                            <select class="form-control" id="select_municipio_autuante" name="select_municipio_autuante">
                                                <% for (Municipio municipio : municipios) {%>
                                                <option value="<%=municipio.getIdMunicipio()%>">
                                                    <%=municipio.getNomeMunicipio()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="sexo_autuante"> &lowast; Sexo:</label>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="sexo_autuante" id="sexo_autuante" value="<%= Sexo.FEMININO.getExtensao()%>"/><%=Sexo.FEMININO.getExtensao()%>       
                                                </label>
                                            </div>
                                            <div class="radio">
                                                <label>
                                                    <input type="radio" name="sexo_autuante" id="sexo_autuante" value="<%= Sexo.MASCULINO.getExtensao()%>" checked/><%=Sexo.MASCULINO.getExtensao()%>
                                                </label>
                                            </div>
                                        </div>
                                    </div>  

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="data_nascimento_autuante"> &lowast; Data de nascimento:</label>
                                            <input type="text" class="form-control" id="data_nascimento_autuante" name="data_nascimento_autuante" placeholder="dd/MM/yyyy" value="<%=DateUtil.formataData(autuante.getDataNascimentoAutuante())%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="altura_autuante"> &lowast; Altura (m): </label>
                                            <input type="text" class="form-control" id="altura_autuante" name="altura_autuante" value="<%=autuante.getAlturaAutuante()%>" required/>
                                        </div>
                                    </div> 

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="data_emissao_bi_autuante"> &lowast; B.I. Emitido em:</label>
                                            <input type="text" class="form-control" id="data_emissao_bi_autuante" name="data_emissao_bi_autuante" placeholder="dd/MM/yyyy"  value="<%=DateUtil.formataData(autuante.getDataEmissaoBiAutuante())%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="data_validade_bi_autuante"> &lowast; B.I. Válido até:</label>
                                            <input type="text" class="form-control" id="data_validade_bi_autuante" name="data_validade_bi_autuante" placeholder="dd/MM/yyyy"  value="<%=DateUtil.formataData(autuante.getDataValidadeBiAutuante())%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="nip_autuante"> &lowast; NIP </label>
                                            <input type="text" class="form-control" id="nip_autuante" name="nip_autuante" value="<%=autuante.getNipAutuante()%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="telefone_autuante">Contacto: </label>
                                            <input type="text" class="form-control" id="telefone_autuante" name="telefone_autuante" value="<%=autuante.getTelefoneAutuante()%>" pattern="^[9-9]{1}[1-9]{2}[0-9]{3}[0-9]{3}$"/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="select_patente_autuante"> &lowast; Patente:</label>
                                            <select class="form-control" id="select_patente_autuante" name="select_patente_autuante">
                                                <% for (Patente patente : patentes) {%>
                                                <option value="<%=patente.getIdPatente()%>">
                                                    <%=patente.getNomePatente()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="select_posto_trabalho_autuante"> &lowast; Posto de Trabalho:</label>
                                            <select class="form-control" id="select_posto_trabalho_autuante" name="select_posto_trabalho_autuante">
                                                <% for (PostoTrabalho postoTrabalho : postoTrabalhos) {%>
                                                <option value="<%=postoTrabalho.getIdPostoTrabalho()%>">
                                                    <%=postoTrabalho.getNomePostoTrabalho()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-info">Update</label>
                                            <button type="submit" class="form-control btn btn-primary" onclick="autuanteValidador()()">Actualizar</button>
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
