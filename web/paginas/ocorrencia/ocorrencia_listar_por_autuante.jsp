<%-- 
    Document   : ocorrencia_listar_por_autuante
    Created on : 14/03/2019, 21:13:46
    Author     : user
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="modelo.Ocorrencia"%>
<%@page import="java.util.List"%>
<%@page import="dao.OcorrenciaDAO"%>
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

            String autuante = request.getParameter("autuante_ocorrencia");
            String paginaParametro = request.getParameter("pagina");

            if (autuante == null) {
                autuante = "";
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

            int quantidadePaginas = ocorrenciaDAO.quantidadePaginasPorAutuante(autuante);

            if (paginaActual > quantidadePaginas) {
                paginaActual = quantidadePaginas;
            }

            List<Ocorrencia> ocorrencias = ocorrenciaDAO.consultarPaginaPorAutuante(
                    autuante,
                    String.valueOf(paginaActual)
            );

            request.setAttribute("ocorrencias", ocorrencias);

            int paginaAnterior = paginaActual - 1;
            int proximaPagina = paginaActual + 1;

            String autuanteUrl = URLEncoder.encode(autuante, "UTF-8");
        %>

        <div class="container">
            <%@include file="../../components/cabecalho.jsp" %>

            <h1 class="page-header text-primary">Ocorrência</h1>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-search"> Pesquisar </span>
                            <span class="caret"></span>
                        </button>

                        <ul class="dropdown-menu">
                            <li><a href="paginas/ocorrencia/ocorrencia_listar.jsp">Listar Todas</a></li>
                            <li><a href="paginas/ocorrencia/ocorrencia_listar_por_autuado.jsp">Nome Autuado</a></li>
                            <li><a href="paginas/ocorrencia/ocorrencia_listar_por_data.jsp">Data Ocorrência</a></li>
                            <li><a href="paginas/ocorrencia/ocorrencia_listar_por_cidade.jsp">Cidade</a></li>
                            <li><a href="paginas/ocorrencia/ocorrencia_listar_por_testemunha.jsp">Testemunha</a></li>
                            <li><a href="paginas/ocorrencia/ocorrencia_listar_por_tipo.jsp">Tipo de Ocorrência</a></li>
                        </ul>
                    </div>
                </div>

                <div class="panel-body">

                    <form action="paginas/ocorrencia/ocorrencia_listar_por_autuante.jsp" method="get">
                        <div class="form-group input-group">
                            <input
                                type="search"
                                id="pesquisa_autuante"
                                name="autuante_ocorrencia"
                                class="form-control"
                                placeholder="Nome do autuante"
                                value="<%=autuante%>"
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
                                        <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : "paginas/ocorrencia/ocorrencia_listar_por_data.jsp?data_ocorrencia=" + autuanteUrl + "&pagina=" + paginaAnterior%>">
                                            &laquo;
                                        </a>
                                    </li>

                                    <%
                                        for (int i = 1; i <= quantidadePaginas; i++) {
                                    %>
                                    <li class="<%=i == paginaActual ? "active" : ""%>">
                                        <a href="paginas/ocorrencia/ocorrencia_listar_por_data.jsp?data_ocorrencia=<%=autuanteUrl%>&pagina=<%=i%>">
                                            <%=i%>
                                        </a>
                                    </li>
                                    <%
                                        }
                                    %>

                                    <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
                                        <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : "paginas/ocorrencia/ocorrencia_listar_por_data.jsp?data_ocorrencia=" + autuanteUrl + "&pagina=" + proximaPagina%>">
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

            <div class="row">
                <%@include file="../../components/rodape.jsp" %>
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                var tempoEspera = null;

                $("#pesquisa_autuante").keyup(function () {
                    clearTimeout(tempoEspera);

                    var termo = $(this).val();

                    tempoEspera = setTimeout(function () {
                        $("#resultado-ocorrencias-wrapper").load(
                                "paginas/ocorrencia/ocorrencia_listar_por_autuante.jsp?autuante_ocorrencia="
                                + encodeURIComponent(termo)
                                + " #resultado-ocorrencias-wrapper > *"
                                );
                    }, 300);
                });
            });
        </script>

    </body>
</html>