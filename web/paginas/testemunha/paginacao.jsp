<%-- 
    Document   : paginacao
    Created on : 14 mag 2026, 21:33:53
    Author     : Nicolau Alfredo
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Integer paginaActual = (Integer) request.getAttribute("paginaActual");
    Integer quantidadePaginas = (Integer) request.getAttribute("quantidadePaginas");
    String urlBase = (String) request.getAttribute("urlBase");
    String queryStringExtra = (String) request.getAttribute("queryStringExtra");

    if (paginaActual == null) {
        paginaActual = 1;
    }

    if (quantidadePaginas == null || quantidadePaginas < 1) {
        quantidadePaginas = 1;
    }

    if (urlBase == null) {
        urlBase = "";
    }

    if (queryStringExtra == null) {
        queryStringExtra = "";
    }

    int paginaAnterior = paginaActual - 1;
    int proximaPagina = paginaActual + 1;

    String separador = queryStringExtra.trim().isEmpty() ? "?" : "?" + queryStringExtra + "&";
%>

<div class="text-center">
    <ul class="pagination">

        <li class="<%=paginaActual <= 1 ? "disabled" : ""%>">
            <a href="<%=paginaActual <= 1 ? "javascript:void(0)" : urlBase + separador + "pagina=" + paginaAnterior%>">
                &laquo;
            </a>
        </li>

        <%
            for (int i = 1; i <= quantidadePaginas; i++) {
        %>
        <li class="<%=i == paginaActual ? "active" : ""%>">
            <a href="<%=urlBase + separador + "pagina=" + i%>">
                <%=i%>
            </a>
        </li>
        <%
            }
        %>

        <li class="<%=paginaActual >= quantidadePaginas ? "disabled" : ""%>">
            <a href="<%=paginaActual >= quantidadePaginas ? "javascript:void(0)" : urlBase + separador + "pagina=" + proximaPagina%>">
                &raquo;
            </a>
        </li>

    </ul>

    <p class="text-muted">
        Página <%=paginaActual%> de <%=quantidadePaginas%>
    </p>
</div>