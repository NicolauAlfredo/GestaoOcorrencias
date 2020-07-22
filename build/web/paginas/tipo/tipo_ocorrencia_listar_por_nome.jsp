<%-- 
    Document   : tipo_ocorrencia_listar_por_nome
    Created on : 16/03/2019, 08:49:38
    Author     : user
--%>

<%@page import="modelo.TipoOcorrencia"%>
<%@page import="java.util.List"%>
<%@page import="dao.TipoOcorrenciaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tipo de Ocorrência</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
    </head>
    <body>
        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary">Tipo de Ocorrência</h1>
                         <div class="alert alert-info">
                            <p>${message}</p>
                        </div>
                    </div>                 
                </div>
            </div>

            <!-- Linha de divisão -->
            <div class="row">
                <!-- Área da linha -->
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <!-- Botão Suspenso -->
                            <div class="btn-group">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                    <span class="glyphicon glyphicon-search"> Pesquisar </span>                                       
                                </button>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->   
                        <div class="panel-body">

                            <form action="tipo_ocorrencia_listar_por_nome.jsp" method="post">
                                <!-- Div com o campo de pesquisa -->
                                <div class="form-group input-group">
                                    <input type="search" name="nome_tipo" class="form-control" required placeholder="Nome">
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                                <!-- Fim da div com o campo de pesquisa -->
                            </form>


                            <%

                                TipoOcorrenciaDAO tipoOcorrenciaDAO = new TipoOcorrenciaDAO();
                                String nome = request.getParameter("nome_tipo");
                                List<TipoOcorrencia> tipoOcorrencias = tipoOcorrenciaDAO.findByNome(nome);

                            %>

                            <form>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-primary">#</th>
                                                <th class="text-primary">Nome</th>
                                                <th class="text-primary" colspan="4">Operações</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%for (TipoOcorrencia tipoOcorrencia : tipoOcorrencias) {%>
                                            <tr>
                                                <td><%=tipoOcorrencia.getIdTipoOcorrencia()%></td>
                                                <td><%=tipoOcorrencia.getNomeTipoOcorrencia()%></td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/tipoOcorrenciaServlet?comando=detalhes&id_tipo_ocorrencia=<%=tipoOcorrencia.getIdTipoOcorrencia()%>">
                                                        <span class="glyphicon glyphicon-print"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/tipoOcorrenciaServlet?comando=detalhes&id_tipo_ocorrencia=<%=tipoOcorrencia.getIdTipoOcorrencia()%>">
                                                        <span class="glyphicon glyphicon-zoom-in"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/tipoOcorrenciaServlet?comando=prepara_editar&id_tipo_ocorrencia=<%=tipoOcorrencia.getIdTipoOcorrencia()%>">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/tipoOcorrenciaServlet?comando=eliminar&id_tipo_ocorrencia=<%=tipoOcorrencia.getIdTipoOcorrencia()%>">
                                                        <span class="glyphicon glyphicon-trash"></span>
                                                    </a>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div> 
                            </form>
                        </div>
                    </div>                   
                </div> 
                <!-- Fim da área da linha-->

                <!-- Rodapé -->
                <%@include file="../../menus/rodape.jsp" %>
                <!-- Fim do Rodapé-->

            </div>
            <!-- Fim da linha de divisão -->
        </div>
        <!-- Fim do Container -->
    </body>
</html>

