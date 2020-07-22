<%-- 
    Document   : autuante_listar_por_bi
    Created on : 14/03/2019, 20:40:43
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Autuante"%>
<%@page import="java.util.List"%>
<%@page import="dao.AutuanteDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autuante</title>
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
                        <h1 class="page-header text-primary">Autuante</h1>
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
                                    <li><a href="autuante_listar_por_data.jsp">Data de Nascimento</a></li>
                                    <li><a href="autuante_listar_por_nome.jsp">Nome Autuante</a></li>
                                    <li><a href="autuante_listar_por_numero.jsp">Número autuante</a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->   
                        <div class="panel-body">

                            <form action="autuante_listar_por_bi.jsp" method="post">
                                <!-- Div com o campo de pesquisa -->
                                <div class="form-group input-group">
                                    <input type="search" name="bi_autuante" class="form-control" required pattern="^[0-9]{9}[A-Z]{2}[0-9]{3}$" placeholder="Nº. Bilhete de Identidade">
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                                <!-- Fim da div com o campo de pesquisa -->
                            </form>

                            <%
                                AutuanteDAO autuanteDAO = new AutuanteDAO();
                                String numeroBI = request.getParameter("bi_autuante");
                                List<Autuante> autuantes = autuanteDAO.findByBi(numeroBI);

                            %>

                            <form method="post">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th class="text-primary">#</th>
                                                <th class="text-primary">Nome</th>
                                                <th class="text-primary">B.I.</th>
                                                <th class="text-primary">Residência</th>
                                                <th class="text-primary">Data de Nascimento</th>
                                                <th class="text-primary">Sexo</th>
                                                <th class="text-primary">Contacto</th>
                                                <th class="text-primary">Patente</th>
                                                <th class="text-primary" colspan="4">Operações</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%for (Autuante autuante : autuantes) {%>
                                            <tr>
                                                <td><%=autuante.getIdAutuante()%></td>
                                                <td><%=autuante.getNomeAutuante()%></td>
                                                <td><%=autuante.getBiAutuante()%></td>
                                                <td><%=autuante.getResidenciaAutuante()%></td>                                                
                                                <td><%=DateUtil.formataData(autuante.getDataNascimentoAutuante())%></td>
                                                <td><%=autuante.getSexo().getExtensao()%></td>
                                                <td><%=autuante.getTelefoneAutuante()%></td>
                                                <td><%=autuante.getPatente().getNomePatente()%></td>
                                                <td>
                                                    <a href="<%= request.getContextPath()%>/autuanteServlet?comando=detalhes&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-print"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/autuanteServlet?comando=detalhes&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-zoom-in"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/autuanteServlet?comando=prepara_editar&id_autuante=<%=autuante.getIdAutuante()%>">
                                                        <span class="glyphicon glyphicon-edit"></span>
                                                    </a>
                                                </td>

                                                <td>
                                                    <a href="<%= request.getContextPath()%>/autuanteServlet?comando=eliminar&id_autuante=<%=autuante.getIdAutuante()%>">
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


