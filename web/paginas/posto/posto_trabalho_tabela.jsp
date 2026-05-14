<%-- 
    Document   : posto_trabalho_tabela
    Created on : 14 mag 2026
    Author     : Nicolau Alfredo
--%>

<%@page import="modelo.PostoTrabalho"%>
<%@page import="java.util.List"%>

<%
    List<PostoTrabalho> listaPostosTrabalho
            = (List<PostoTrabalho>) request.getAttribute("postoTrabalhos");
%>

<table class="table table-hover">
    <thead>
        <tr>
            <th class="text-primary">#</th>
            <th class="text-primary">Nome</th>
            <th class="text-primary">Número</th>
            <th class="text-primary">Município</th>
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody id="resultado-postos-trabalho">
        <%
            if (listaPostosTrabalho == null || listaPostosTrabalho.isEmpty()) {
        %>
        <tr>
            <td colspan="8" class="text-center text-muted">
                Nenhum posto de trabalho encontrado.
            </td>
        </tr>
        <%
        } else {
            for (PostoTrabalho postoTrabalho : listaPostosTrabalho) {
        %>
        <tr>
            <td><%=postoTrabalho.getIdPostoTrabalho()%></td>
            <td><%=postoTrabalho.getNomePostoTrabalho()%></td>
            <td><%=postoTrabalho.getNumeroPostoTrabalho()%></td>

            <td>
                <%
                    if (postoTrabalho.getMunicipio() != null) {
                %>
                <%= postoTrabalho.getMunicipio() != null
                        ? postoTrabalho.getMunicipio().getNomeMunicipio()
                        : ""%>
                <%
                    }
                %>
            </td>

            <td>
                <a href="postoTrabalhoServlet?comando=detalhes&id_posto_trabalho=<%=postoTrabalho.getIdPostoTrabalho()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="postoTrabalhoServlet?comando=detalhes&id_posto_trabalho=<%=postoTrabalho.getIdPostoTrabalho()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="postoTrabalhoServlet?comando=prepara_editar&id_posto_trabalho=<%=postoTrabalho.getIdPostoTrabalho()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="postoTrabalhoServlet?comando=eliminar&id_posto_trabalho=<%=postoTrabalho.getIdPostoTrabalho()%>"
                   onclick="return confirm('Deseja realmente eliminar este posto de trabalho?');">
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