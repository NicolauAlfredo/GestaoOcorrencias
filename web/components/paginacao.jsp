<%-- 
    Document   : paginacao
    Created on : 14 mag 2026, 21:33:53
    Author     : Nicolau Alfredo
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Integer componentePaginaActual = (Integer) request.getAttribute("paginaActual");
    Integer componenteQuantidadePaginas = (Integer) request.getAttribute("quantidadePaginas");
    String componenteUrlBase = (String) request.getAttribute("urlBase");
    String componenteQueryStringExtra = (String) request.getAttribute("queryStringExtra");

    if (componentePaginaActual == null) {
        componentePaginaActual = 1;
    }

    if (componentePaginaActual == null || componenteQuantidadePaginas < 1) {
        componenteQuantidadePaginas = 1;
    }

    if (componenteUrlBase == null) {
        componenteUrlBase = "";
    }

    if (componenteQueryStringExtra == null) {
        componenteQueryStringExtra = "";
    }

    int paginaAnterior = componentePaginaActual - 1;
    int proximaPagina = componentePaginaActual + 1;

    String separador = componenteQueryStringExtra.trim().isEmpty() ? "?" : "?" + componenteQueryStringExtra + "&";
%>

<div class="text-center">
    <ul class="pagination">

        <li class="<%=componentePaginaActual <= 1 ? "disabled" : ""%>">
            <a href="<%=componentePaginaActual <= 1 ? "javascript:void(0)" : componenteUrlBase + separador + "pagina=" + paginaAnterior%>">
                &laquo;
            </a>
        </li>

        <%
            for (int i = 1; i <= componenteQuantidadePaginas; i++) {
        %>
        <li class="<%=i == componentePaginaActual ? "active" : ""%>">
            <a href="<%=componenteUrlBase + separador + "pagina=" + i%>">
                <%=i%>
            </a>
        </li>
        <%
            }
        %>

        <li class="<%=componentePaginaActual >= componenteQuantidadePaginas ? "disabled" : ""%>">
            <a href="<%=componentePaginaActual >= componenteQuantidadePaginas ? "javascript:void(0)" : componenteUrlBase + separador + "pagina=" + proximaPagina%>">
                &raquo;
            </a>
        </li>

    </ul>

    <p class="text-muted">
        Página <%=componentePaginaActual%> de <%=componenteQuantidadePaginas%>
    </p>
</div>