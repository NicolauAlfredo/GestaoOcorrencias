<%-- 
    Document   : profissao_tabela
    Author     : Nicolau Alfredo
--%>

<%@page import="modelo.Profissao"%>
<%@page import="java.util.List"%>

<%
    List<Profissao> listaProfissoes = (List<Profissao>) request.getAttribute("profissoes");
%>

<table class="table table-hover">
    <thead>
        <tr>
            <th class="text-primary">#</th>
            <th class="text-primary">Nome</th>
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody id="resultado-profissoes">
        <%
            if (listaProfissoes == null || listaProfissoes.isEmpty()) {
        %>
        <tr>
            <td colspan="6" class="text-center text-muted">
                Nenhuma profissão encontrada.
            </td>
        </tr>
        <%
        } else {
            for (Profissao profissao : listaProfissoes) {
        %>
        <tr>
            <td><%=profissao.getIdProfissao()%></td>
            <td><%=profissao.getNomeProfissao()%></td>

            <td>
                <a href="profissaoServlet?comando=detalhes&id_profissao=<%=profissao.getIdProfissao()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="profissaoServlet?comando=detalhes&id_profissao=<%=profissao.getIdProfissao()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="profissaoServlet?comando=prepara_editar&id_profissao=<%=profissao.getIdProfissao()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="profissaoServlet?comando=eliminar&id_profissao=<%=profissao.getIdProfissao()%>"
                   onclick="return confirm('Deseja realmente eliminar esta profissão?');">
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