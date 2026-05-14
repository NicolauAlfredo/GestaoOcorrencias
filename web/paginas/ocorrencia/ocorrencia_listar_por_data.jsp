<%-- 
    Document   : ocorrencia_listar_por_data
    Created on : 14/03/2019, 21:14:12
    Author     : user
--%>

<%@page import="dao.OcorrenciaDAO"%>
<%@page import="java.util.List"%>
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
        <%
            OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

            String dataTexto = request.getParameter("data_ocorrencia");
            String paginaParametro = request.getParameter("pagina");

            if (dataTexto == null) {
                dataTexto = "";
            }

            java.sql.Date data = null;

            if (!dataTexto.trim().isEmpty()) {
                try {
                    data = DateUtil.strToDate(dataTexto);
                } catch (Exception ex) {
                    data = null;
                }
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

            int quantidadePaginas = ocorrenciaDAO.quantidadePaginasPorData(data);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Ocorrencia> ocorrencias = ocorrenciaDAO.consultarPaginaPorData(
                    data,
                    String.valueOf(paginaActual)
            );

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String dataUrl = java.net.URLEncoder.encode(dataTexto, "UTF-8");
        %>

        <!-- Container principal do Bootstrap -->
        <div class="container">
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../../components/cabecalho.jsp" %>
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
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_autuado.jsp">Nome Autuado</a></li>
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_autuante.jsp">Nome Autuante</a></li>
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_testemunha.jsp">Nome Testemunha</a></li>
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_cidade.jsp">Nome Cidade</a></li>
                                    <li><a href="paginas/ocorrencia/ocorrencia_listar_por_tipo.jsp">Tipo de Ocorrência</a></li>
                                </ul>
                            </div>
                            <!-- Fim do Botão Suspenso -->
                        </div>

                        <!-- Corpo da página -->   
                        <div class="panel-body">

                            <form action="paginas/ocorrencia/ocorrencia_listar_por_data.jsp" method="get">
                                <div class="form-group input-group">
                                    <input
                                        type="search"
                                        id="pesquisa_data"
                                        name="data_ocorrencia"
                                        class="form-control"
                                        placeholder="dd/MM/yyyy"
                                        value="<%=dataTexto%>"
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

                                    <div class="text-center">
                                        <ul class="pagination">

                                            <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
                                                <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "paginas/ocorrencia/ocorrencia_listar_por_data.jsp?data_ocorrencia=" + dataUrl + "&pagina=" + paginaAnterior%>">
                                                    &laquo;
                                                </a>
                                            </li>

                                            <%
                                                for (int i = 1; i <= quantidadePaginas; i++) {
                                            %>
                                            <li class="<%=i == paginaActual ? "active" : ""%>">
                                                <a href="paginas/ocorrencia/ocorrencia_listar_por_data.jsp?data_ocorrencia=<%=dataUrl%>&pagina=<%=i%>">
                                                    <%=i%>
                                                </a>
                                            </li>
                                            <%
                                                }
                                            %>

                                            <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                                <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "paginas/ocorrencia/ocorrencia_listar_por_data.jsp?data_ocorrencia=" + dataUrl + "&pagina=" + proximaPagina%>">
                                                    &raquo;
                                                </a>
                                            </li>

                                        </ul>

                                        <p class="text-muted">
                                            Página <%=paginaActual%> de <%=quantidadePaginas%>
                                        </p>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>                   
                </div> 
                <!-- Fim da área da linha-->

                <!-- Rodapé -->
                <%@include file="../../components/rodape.jsp" %>
                <!-- Fim do Rodapé-->

            </div>
            <!-- Fim da linha de divisão -->
        </div>
        <!-- Fim do Container -->

        <script type="text/javascript">
            $(document).ready(function () {

                var tempoEspera = null;

                $("#pesquisa_data").keyup(function () {

                    clearTimeout(tempoEspera);

                    var termo = $(this).val().trim();

                    tempoEspera = setTimeout(function () {

                        if (termo.length > 0 && termo.length < 10) {
                            return;
                        }

                        $("#resultado-ocorrencias-wrapper").load(
                                "paginas/ocorrencia/ocorrencia_listar_por_data.jsp?data_ocorrencia="
                                + encodeURIComponent(termo)
                                + " #resultado-ocorrencias-wrapper > *"
                                );

                    }, 300);

                });

            });
        </script>
    </body>
</html>




