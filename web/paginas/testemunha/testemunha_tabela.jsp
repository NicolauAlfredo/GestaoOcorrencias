<%-- 
    Document   : testemunha_tabela
    Created on : 14 mag 2026, 19:24:41
    Author     : Nicolau Alfredo
--%> 

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Testemunha"%>
<%@page import="java.util.List"%>

<%
    List<Testemunha> listaTestemunhas = (List<Testemunha>) request.getAttribute("testemunhas");
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
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody id="resultado-testemunhas">
        <%
            if (listaTestemunhas == null || listaTestemunhas.isEmpty()) {
        %>
        <tr>
            <td colspan="11" class="text-center text-muted">
                Nenhuma testemunha encontrada.
            </td>
        </tr>
        <%
        } else {
            for (Testemunha testemunha : listaTestemunhas) {
        %>
        <tr>
            <td><%=testemunha.getIdTestemunha()%></td>
            <td><%=testemunha.getNomeTestemunha()%></td>
            <td><%=testemunha.getBiTestemunha()%></td>
            <td><%=testemunha.getResidenciaTestemunha()%></td>
            <td><%=DateUtil.formataData(testemunha.getDataNascimentoTestemunha())%></td>

            <td>
                <%
                    if (testemunha.getSexo() != null) {
                %>
                <%=testemunha.getSexo().getExtensao()%>
                <%
                    }
                %>
            </td>

            <td><%=testemunha.getTelefoneTestemunha()%></td>

            <td>
                <a href="testemunhaServlet?comando=detalhes&id_testemunha=<%=testemunha.getIdTestemunha()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="testemunhaServlet?comando=detalhes&id_testemunha=<%=testemunha.getIdTestemunha()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="testemunhaServlet?comando=prepara_editar&id_testemunha=<%=testemunha.getIdTestemunha()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="testemunhaServlet?comando=eliminar&id_testemunha=<%=testemunha.getIdTestemunha()%>"
                   onclick="return confirm('Deseja realmente eliminar esta testemunha?');">
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