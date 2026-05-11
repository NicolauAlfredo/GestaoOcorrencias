<%-- 
    Document   : autuado_tabela
    Created on : 11 mag 2026, 04:07:35
    Author     : Nicolau Alfredo
--%>

<%@page import="modelo.Autuado"%>
<%@page import="modelo.DateUtil"%>
<%@page import="java.util.List"%>

<%
    List<Autuado> listaAutuados = (List<Autuado>) request.getAttribute("autuados");
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
            <th class="text-primary">Profissão</th>
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody>
        <%
            if (listaAutuados == null || listaAutuados.isEmpty()) {
        %>
        <tr>
            <td colspan="12" class="text-center text-muted">
                Nenhum autuado encontrado.
            </td>
        </tr>
        <%
        } else {
            for (Autuado autuado : listaAutuados) {
        %>
        <tr>
            <td><%=autuado.getIdAutuado()%></td>
            <td><%=autuado.getNomeAutuado()%></td>
            <td><%=autuado.getBiAutuado()%></td>
            <td><%=autuado.getResidenciaAutuado()%></td>
            <td><%=DateUtil.formataData(autuado.getDataNascimentoAutuado())%></td>

            <td>
                <%
                    if (autuado.getSexo() != null) {
                %>
                <%=autuado.getSexo().getExtensao()%>
                <%
                    }
                %>
            </td>

            <td><%=autuado.getTelefoneAutuado()%></td>

            <td>
                <%
                    if (autuado.getProfissao() != null) {
                %>
                <%=autuado.getProfissao().getNomeProfissao()%>
                <%
                    }
                %>
            </td>

            <td>
                <a href="<%=request.getContextPath()%>/autuadoPorId?id_autuado=<%=autuado.getIdAutuado()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="<%=request.getContextPath()%>/autuadoServlet?comando=detalhes&id_autuado=<%=autuado.getIdAutuado()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="<%=request.getContextPath()%>/autuadoServlet?comando=prepara_editar&id_autuado=<%=autuado.getIdAutuado()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="<%=request.getContextPath()%>/autuadoServlet?comando=eliminar&id_autuado=<%=autuado.getIdAutuado()%>"
                   onclick="return confirm('Deseja realmente eliminar este autuado?');">
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