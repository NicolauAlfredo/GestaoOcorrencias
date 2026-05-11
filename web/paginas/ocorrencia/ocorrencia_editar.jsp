<%-- 
    Document   : ocorrencia_editar
    Created on : 24/02/2019, 19:29:43
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Testemunha"%>
<%@page import="modelo.TipoOcorrencia"%>
<%@page import="modelo.Autuante"%>
<%@page import="modelo.Ocorrencia"%>
<%@page import="dao.PostoTrabalhoDAO"%>
<%@page import="modelo.PostoTrabalho"%>
<%@page import="dao.AutuadoDAO"%>
<%@page import="modelo.Autuado"%>
<%@page import="dao.AutuanteDAO"%>
<%@page import="dao.TipoOcorrenciaDAO"%>
<%@page import="java.util.List"%>
<%@page import="dao.TestemunhaDAO"%>
<%@page import="java.util.List"%>
<%@page import="dao.MunicipioDAO"%>
<%@page import="modelo.Municipio"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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

            PostoTrabalhoDAO postoTrabalhoDAO = new PostoTrabalhoDAO();
            List<PostoTrabalho> postoTrabalhos = postoTrabalhoDAO.findAll();

            AutuadoDAO autuadoDAO = new AutuadoDAO();
            List<Autuado> autuados = autuadoDAO.findAll();

            AutuanteDAO autuanteDAO = new AutuanteDAO();
            List<Autuante> autuantes = autuanteDAO.findAll();

            TipoOcorrenciaDAO tipoOcorrenciaDAO = new TipoOcorrenciaDAO();
            List<TipoOcorrencia> tipoOcorrencias = tipoOcorrenciaDAO.findAll();

            TestemunhaDAO testemunhaDAO = new TestemunhaDAO();
            List<Testemunha> testemunhas = testemunhaDAO.findAll();

            MunicipioDAO municipioDAO = new MunicipioDAO();
            List<Municipio> municipios = municipioDAO.findAll();
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">              
            <div id="page-wrapper">                
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
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
                                Editar dados da Ocorrência do Sr.: <strong> <%=ocorrencia.getAutuado().getNomeAutuado()%> </strong>
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
                                <form role="form" accept-charset="ISO-8859-1, UTF-8" action="ocorrenciaServlet?comando=editar" method="POST">
                                    <div class="form-group">                                                
                                        <div class="col-xs-6">
                                            <label class="text-primary"> Id: </label>
                                            <input type="text" class="form-control" id="id_ocorrencia" name="id_ocorrencia" value="<%=ocorrencia.getIdOcorrencia()%>" readonly="readonly"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" > 
                                            <label class="text-primary" for="data_ocorrencia"> &lowast; Data da Ocorrência:</label>
                                            <input type="text" class="form-control" id="data_ocorrencia" name="data_ocorrencia" placeholder="dd/MM/yyyy" value="<%=DateUtil.formataData(ocorrencia.getDataOcorrencia())%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="hora_ocorrencia"> &lowast; Hora da Ocorrência: </label>
                                            <input type="time" class="form-control" id="hora_ocorrencia" name="hora_ocorrencia" value="<%=ocorrencia.getHoraOcorrencia()%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="rua_ocorrencia"> &lowast; Rua da Ocorrência: </label>
                                            <input type="text" class="form-control" id="rua_ocorrencia" name="rua_ocorrencia" value="<%=ocorrencia.getRuaOcorrencia()%>" required/>
                                        </div>
                                    </div>   

                                    <div class="form-group"> 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="cidade_ocorrencia"> &lowast; Cidade da Ocorrência: </label>
                                            <input type="text" class="form-control" id="cidade_ocorrencia" name="cidade_ocorrencia"  value="<%=ocorrencia.getCidadeOcorrencia()%>" required/>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                
                                        <div class="col-xs-6" >
                                            <label class="text-primary"  for="select_autuante"> &lowast; Autuante:</label>
                                            <select class="form-control" id="select_autuante" name="select_autuante">
                                                <% for (Autuante autuante : autuantes) {%>
                                                <option value="<%=autuante.getIdAutuante()%>">
                                                    <%=autuante.getNomeAutuante()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-primary" for="descricao_ocorrencia"> &lowast; Descrição:</label>
                                            <textarea class="form-control" rows="5" id="descricao_ocorrencia" name="descricao_ocorrencia" value="<%=ocorrencia.getDescricaoOcorrencia()%>" required></textarea>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                
                                        <div class="col-xs-6" >
                                            <label class="text-primary"  for="select_tipo_ocorrencia"> &lowast; Tipo de Ocorrência:</label>
                                            <select class="form-control" id="select_tipo_ocorrencia" name="select_tipo_ocorrencia">
                                                <% for (TipoOcorrencia tipoOcorrencia : tipoOcorrencias) {%>
                                                <option value="<%=tipoOcorrencia.getIdTipoOcorrencia()%>">
                                                    <%=tipoOcorrencia.getNomeTipoOcorrencia()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                
                                        <div class="col-xs-6" >
                                            <label class="text-primary"  for="select_autuado"> &lowast; Autuado:</label>
                                            <select class="form-control" id="select_autuado" name="select_autuado">
                                                <% for (Autuado autuado : autuados) {%>
                                                <option value="<%=autuado.getIdAutuado()%>">
                                                    <%=autuado.getNomeAutuado()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="bairro_ocorrencia"> &lowast; Bairro da Ocorrência: </label>
                                            <input type="text" class="form-control" id="bairro_ocorrencia" name="bairro_ocorrencia" value="<%=ocorrencia.getBairroOcorrencia()%>" required/>
                                        </div>
                                    </div>  

                                    <div class="form-group">                                                 
                                        <div class="col-xs-6">
                                            <label class="text-primary" for="proximidade_ocorrencia"> &lowast; Proximidade de localização: </label>
                                            <input type="text" class="form-control" id="proximidade_ocorrencia" name="proximidade_ocorrencia" value="<%=ocorrencia.getProximidadeOcorrencia()%>" required/>
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
                                            <label class="text-primary"  for="select_testemunha"> &lowast; 1º Testemunha:</label>
                                            <select class="form-control" id="select_testemunha" name="select_testemunha">
                                                <% for (Testemunha testemunha : testemunhas) {%>
                                                <option value="<%=testemunha.getIdTestemunha()%>">
                                                    <%=testemunha.getNomeTestemunha()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">                                                
                                        <div class="col-xs-6" >
                                            <label class="text-primary"  for="select_testemunha_sec">2º Testemunha:</label>
                                            <select class="form-control" id="select_testemunha_sec" name="select_testemunha_sec">
                                                <% for (Testemunha testemunha1 : testemunhas) {%>
                                                <option value="<%=testemunha1.getIdTestemunha()%>">
                                                    <%=testemunha1.getNomeTestemunha()%>
                                                </option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-6" >
                                            <label class="text-info">Update</label>
                                            <button type="submit" class="form-control btn btn-primary" onclick="ocorrenciaValidador()">Actualizar</button>
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
