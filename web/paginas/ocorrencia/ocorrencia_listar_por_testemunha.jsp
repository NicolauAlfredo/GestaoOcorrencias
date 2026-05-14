<%-- 
    Document   : ocorrencia_listar_por_testemunha
    Created on : 14/03/2019, 21:14:34
    Author     : user
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Ocorrencia"%>
<%@page import="dao.OcorrenciaDAO"%>
<%@page import="java.util.List"%>
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
        <%
            OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

            String testemunha = request.getParameter("testemunha_ocorrencia");
            String paginaParametro = request.getParameter("pagina");

            if (testemunha == null) {
                testemunha = "";
            }

            int paginaActual = 1;

            if (paginaParametro != null && !paginaParametro.trim().isEmpty()) {
                try {
                    paginaActual = Integer.parseInt(paginaParametro);
                } catch (NumberFormatException ex) {
                    paginaActual = 1;
                }
            }

            if (paginaActual < 1) {
                paginaActual = 1;
            }

            int quantidadePaginas = ocorrenciaDAO.quantidadePaginasPorTestemunha(testemunha);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Ocorrencia> ocorrencias = ocorrenciaDAO.consultarPaginaPorTestemunha(
                    testemunha,
                    String.valueOf(paginaActual)
            );

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String dataUrl = java.net.URLEncoder.encode(testemunha, "UTF-8");
        %>

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
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_tipo.jsp">Tipo de Ocorrência</a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->   
                        <div class="panel-body">

                            <form action="paginas/ocorrencia/ocorrencia_listar_por_testemunha.jsp" method="get">
                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        id="pesquisa_testemunha"
                                        name="testemunha_ocorrencia"
                                        class="form-control"
                                        placeholder="Testemunha"
                                        value="<%=testemunha%>"
                                        >
                                    <span class="input-group-btn">
                                        <button class="btn btn-primary" type="submit">
                                            <i class="glyphicon glyphicon-search"></i>
                                        </button>
                                    </span>
                                </div>
                            </form>

                            <form>
                                <%
                                    request.setAttribute("ocorrencias", ocorrencias);
                                %>

                                <div id="resultado-ocorrencias-wrapper">
                                    <%@include file="ocorrencia_tabela.jsp" %>
                                </div>

                                <div class="text-center">
                                    <ul class="pagination">

                                        <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                            <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "paginas/ocorrencia/ocorrencia_listar_por_testemunha.jsp?testemunha_ocorrencia=" + dataUrl + "&pagina=" + paginaAnterior%>">
                                                &laquo;
                                            </a>
                                        </li>

                                        <%
                                            for (int i = 1; i <= quantidadePaginas; i++) {
                                        %>
                                        <li class="<%=i == paginaActual ? "active" : ""%>">
                                            <a href="paginas/ocorrencia/ocorrencia_listar_por_testemunha.jsp?testemunha_ocorrencia=<%=dataUrl%>&pagina=<%=i%>">
                                                <%=i%>
                                            </a>
                                        </li>
                                        <%
                                            }
                                        %>

                                        <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                            <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "paginas/ocorrencia/ocorrencia_listar_por_testemunha.jsp?testemunha_ocorrencia=" + dataUrl + "&pagina=" + proximaPagina%>">
                                                &raquo;
                                            </a>
                                        </li>

                                    </ul>

                                    <p class="text-muted">
                                        Página <%=paginaActual%> de <%=quantidadePaginas%>
                                    </p>
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

        <script type="text/javascript">
            $(document).ready(function () {

                var tempoEspera = null;

                $("#pesquisa_testemunha").keyup(function () {

                    clearTimeout(tempoEspera);

                    var termo = $(this).val();

                    tempoEspera = setTimeout(function () {

                        $("#resultado-ocorrencias-wrapper").load(
                                "paginas/ocorrencia/ocorrencia_listar_por_testemunha.jsp?testemunha_ocorrencia="
                                + encodeURIComponent(termo)
                                + " #resultado-ocorrencias-wrapper > *"
                                );

                    }, 300);

                });

            });
        </script>
    </body>
</html>





