<%-- 
    Document   : ocorrencia_listar_por_tipo
    Created on : 14/03/2019, 21:15:37
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="dao.OcorrenciaDAO"%>
<%@page import="modelo.Ocorrencia"%>
<%@page import="modelo.DateUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../menus/cabecalho.jsp" %>
                        <h1 class="page-header text-primary">Ocorrência</h1>
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
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">  
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_data.jsp">Data Ocorrência</a></li>
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_autuado.jsp">Nome Autuado</a></li>
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_autuante.jsp">Nome Autuante</a></li>
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_cidade.jsp">Nome Cidade</a></li>                                        
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_testemunha.jsp">Nome Testemunha</a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->   
                        <div class="panel-body">

                            <form action="ocorrencia_listar_por_tipo.jsp" method="post">
                                <!-- Div com o campo de pesquisa -->
                                <div class="form-group input-group">
                                    <input type="search" name="tipo_ocorrencia" class="form-control" required placeholder="Tipo de ocorrência">
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                                <!-- Fim da div com o campo de pesquisa -->
                            </form>

                            <%                                OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
                                String tipo = request.getParameter("tipo_ocorrencia");
                                List<Ocorrencia> ocorrencias = ocorrenciaDAO.findByTipo(tipo);
                            %>

                            <form>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-primary">#</th>
                                                <th class="text-primary">Data</th>
                                                <th class="text-primary">Hora</th>
                                                <th class="text-primary">Cidade</th>
                                                <th class="text-primary">Autuado</th>
                                                <th class="text-primary">Autuante</th>                                                
                                                <th class="text-primary">Tipo de Ocorrência</th>
                                                <th class="text-primary">Testemunha</th>
                                                <th class="text-primary" colspan="4">Operações</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%for (Ocorrencia ocorrencia : ocorrencias) {%>
                                            <tr>
                                                <td><%=ocorrencia.getIdOcorrencia()%></td>
                                                <td><%=DateUtil.formataData(ocorrencia.getDataOcorrencia())%></td>
                                                <td><%=ocorrencia.getHoraOcorrencia()%></td>
                                                <td><%=ocorrencia.getCidadeOcorrencia()%></td>
                                                <td>
                                                    <a href="autuadoServlet?comando=detalhes&id_autuado=<%=ocorrencia.getAutuado().getIdAutuado()%>">
                                                        <%=ocorrencia.getAutuado().getNomeAutuado()%>
                                                    </a>                                                  
                                                </td>
                                                <td>
                                                    <a href="autuanteServlet?comando=detalhes&id_autuante=<%=ocorrencia.getAutuante().getIdAutuante()%>">
                                                        <%=ocorrencia.getAutuante().getNomeAutuante()%>     
                                                    </a>                                                  
                                                </td>
                                                <td><%=ocorrencia.getTipoOcorrencia().getNomeTipoOcorrencia()%></td>
                                                <td>
                                                    <a href="testemunhaServlet?comando=detalhes&id_testemunha=<%=ocorrencia.getTestemunha().getIdTestemunha()%>">
                                                        <%=ocorrencia.getTestemunha().getNomeTestemunha()%>
                                                    </a>                                                  
                                                </td>
                                                <td>
                                                    <a href="ocorrenciaServlet?comando=detalhes&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>">
                                                        <span class="glyphicon glyphicon-print"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="ocorrenciaServlet?comando=detalhes&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>">
                                                        <span class="glyphicon glyphicon-zoom-in"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="ocorrenciaServlet?comando=prepara_editar&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="ocorrenciaServlet?comando=eliminar&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>">
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
