<%-- 
    Document   : autuante_tabela
    Created on : 12 mag 2026, 18:41:34
    Author     : Nicolau Alfredo
--%>

<%@page import="modelo.Autuante"%>
<%@page import="modelo.DateUtil"%>
<%@page import="java.util.List"%>

<%
    List<Autuante> listaAutuantes = (List<Autuante>) request.getAttribute("listaAutuantes");
%>

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
        <%
            if (listaAutuantes == null || listaAutuantes.isEmpty()) {
        %>
        <tr>
            <td colspan="12" class="text-center text-muted">
                Nenhum autuante encontrado.
            </td>
        </tr>
        <%
        } else {
            for (Autuante autuante : listaAutuantes) {
        %>
        <tr>
            <td><%=autuante.getIdAutuante()%></td>
            <td><%=autuante.getNomeAutuante()%></td>
            <td><%=autuante.getBiAutuante()%></td>
            <td><%=autuante.getResidenciaAutuante()%></td>
            <td><%=DateUtil.formataData(autuante.getDataNascimentoAutuante())%></td>

            <td>
                <%
                    if (autuante.getSexo() != null) {
                %>
                <%=autuante.getSexo().getExtensao()%>
                <%
                    }
                %>
            </td>

            <td><%=autuante.getTelefoneAutuante()%></td>

            <td>
                <%
                    if (autuante.getPatente() != null) {
                %>
                <%=autuante.getPatente().getNomePatente()%>
                <%
                    }
                %>
            </td>

            <td>
                <a href="autuanteServlet?comando=detalhes&id_autuante=<%=autuante.getIdAutuante()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="autuanteServlet?comando=detalhes&id_autuante=<%=autuante.getIdAutuante()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="autuanteServlet?comando=prepara_editar&id_autuante=<%=autuante.getIdAutuante()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="autuanteServlet?comando=eliminar&id_autuante=<%=autuante.getIdAutuante()%>"
                   onclick="return confirm('Deseja realmente eliminar este autuante?');">
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