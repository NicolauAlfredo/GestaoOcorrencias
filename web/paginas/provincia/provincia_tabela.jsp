<%-- 
    Document   : provincia_tabela
    Created on : 14 mag 2026, 19:09:48
    Author     : Nicolau Alfredo
--%> 

<%@page import="modelo.Provincia"%>
<%@page import="java.util.List"%>

<%
    List<Provincia> listaProvincias = (List<Provincia>) request.getAttribute("provincias");
%>

<table class="table table-hover">
    <thead>
        <tr>
            <th class="text-primary">#</th>
            <th class="text-primary">Nome</th>
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody id="resultado-provincias">
        <%
            if (listaProvincias == null || listaProvincias.isEmpty()) {
        %>
        <tr>
            <td colspan="6" class="text-center text-muted">
                Nenhuma província encontrada.
            </td>
        </tr>
        <%
        } else {
            for (Provincia provincia : listaProvincias) {
        %>
        <tr>
            <td><%=provincia.getIdProvincia()%></td>
            <td><%=provincia.getNomeProvincia()%></td>

            <td>
                <a href="provinciaServlet?comando=detalhes&id_provincia=<%=provincia.getIdProvincia()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="provinciaServlet?comando=detalhes&id_provincia=<%=provincia.getIdProvincia()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="provinciaServlet?comando=prepara_editar&id_provincia=<%=provincia.getIdProvincia()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="provinciaServlet?comando=eliminar&id_provincia=<%=provincia.getIdProvincia()%>"
                   onclick="return confirm('Deseja realmente eliminar esta província?');">
                    <span class="glyphicon glyphicon-trash"></span>
                </a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </tbody>
</table>
