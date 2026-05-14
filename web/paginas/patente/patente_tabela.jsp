<%-- 
    Document   : patente_tabela
    Created on : 14 mag 2026, 15:34:30
    Author     : Nicolau Alfredo
--%> 

<%@page import="modelo.Patente"%>
<%@page import="java.util.List"%>

<%
    List<Patente> listaPatentes = (List<Patente>) request.getAttribute("patentes");
%>

<table class="table table-hover">
    <thead>
        <tr>
            <th class="text-primary">#</th>
            <th class="text-primary">Nome</th>
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody id="resultado-patentes">
        <%
            if (listaPatentes == null || listaPatentes.isEmpty()) {
        %>
        <tr>
            <td colspan="6" class="text-center text-muted">
                Nenhuma patente encontrada.
            </td>
        </tr>
        <%
        } else {
            for (Patente patente : listaPatentes) {
        %>
        <tr>
            <td><%=patente.getIdPatente()%></td>
            <td><%=patente.getNomePatente()%></td>

            <td>
                <a href="patenteServlet?comando=detalhes&id_patente=<%=patente.getIdPatente()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="patenteServlet?comando=detalhes&id_patente=<%=patente.getIdPatente()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="patenteServlet?comando=prepara_editar&id_patente=<%=patente.getIdPatente()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="patenteServlet?comando=eliminar&id_patente=<%=patente.getIdPatente()%>"
                   onclick="return confirm('Deseja realmente eliminar esta patente?');">
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
