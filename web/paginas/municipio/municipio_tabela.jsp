<%-- 
    Document   : municipio_tabela.jsp
    Created on : 12 mag 2026, 21:03:31
    Author     : Nicolau Alfredo
--%>

<%@page import="modelo.Municipio"%>
<%@page import="java.util.List"%>

<%
    List<Municipio> listaMunicipios = (List<Municipio>) request.getAttribute("municipios");
%>

<table class="table table-hover">
    <thead>
        <tr>
            <th class="text-primary">#</th>
            <th class="text-primary">Nome</th>
            <th class="text-primary">Província</th>
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody id="resultado-municipios">
        <%
            if (listaMunicipios == null || listaMunicipios.isEmpty()) {
        %>
        <tr>
            <td colspan="7" class="text-center text-muted">
                Nenhum município encontrado.
            </td>
        </tr>
        <%
        } else {
            for (Municipio municipio : listaMunicipios) {
        %>
        <tr>
            <td><%=municipio.getIdMunicipio()%></td>
            <td><%=municipio.getNomeMunicipio()%></td>

            <td>
                <%
                    if (municipio.getProvincia() != null) {
                %>
                <%=municipio.getProvincia().getNomeProvincia()%>
                <%
                    }
                %>
            </td>

            <td>
                <a href="municipioServlet?comando=detalhes&id_municipio=<%=municipio.getIdMunicipio()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="municipioServlet?comando=detalhes&id_municipio=<%=municipio.getIdMunicipio()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="municipioServlet?comando=prepara_editar&id_municipio=<%=municipio.getIdMunicipio()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="municipioServlet?comando=eliminar&id_municipio=<%=municipio.getIdMunicipio()%>"
                   onclick="return confirm('Deseja realmente eliminar este município?');">
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