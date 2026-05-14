<%-- 
    Document   : tipo_ocorrencia_tabela
    Created on : 15 mag 2026, 01:02:30
    Author     : Nicolau Alfredo
--%>

<%@page import="modelo.TipoOcorrencia"%>
<%@page import="java.util.List"%>

<%
    List<TipoOcorrencia> listaTiposOcorrencia
            = (List<TipoOcorrencia>) request.getAttribute("tipoOcorrencias");
%>

<table class="table table-hover">
    <thead>
        <tr>
            <th class="text-primary">#</th>
            <th class="text-primary">Nome</th>
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody id="resultado-tipos-ocorrencia">
        <%
            if (listaTiposOcorrencia == null || listaTiposOcorrencia.isEmpty()) {
        %>
        <tr>
            <td colspan="6" class="text-center text-muted">
                Nenhum tipo de ocorrência encontrado.
            </td>
        </tr>
        <%
        } else {
            for (TipoOcorrencia tipoOcorrencia : listaTiposOcorrencia) {
        %>
        <tr>
            <td><%=tipoOcorrencia.getIdTipoOcorrencia()%></td>
            <td><%=tipoOcorrencia.getNomeTipoOcorrencia()%></td>

            <td>
                <a href="tipoOcorrenciaServlet?comando=detalhes&id_tipo_ocorrencia=<%=tipoOcorrencia.getIdTipoOcorrencia()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="tipoOcorrenciaServlet?comando=detalhes&id_tipo_ocorrencia=<%=tipoOcorrencia.getIdTipoOcorrencia()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="tipoOcorrenciaServlet?comando=prepara_editar&id_tipo_ocorrencia=<%=tipoOcorrencia.getIdTipoOcorrencia()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="tipoOcorrenciaServlet?comando=eliminar&id_tipo_ocorrencia=<%=tipoOcorrencia.getIdTipoOcorrencia()%>"
                   onclick="return confirm('Deseja realmente eliminar este tipo de ocorrência?');">
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