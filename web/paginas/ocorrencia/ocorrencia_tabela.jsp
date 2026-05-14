<%-- 
    Document   : ocorrencia_tabela
    Created on : 14 mag 2026, 13:55:53
    Author     : Nicolau Alfredo
--%>

<%@page import="modelo.Ocorrencia"%>
<%@page import="modelo.DateUtil"%>
<%@page import="java.util.List"%>

<%
    List<Ocorrencia> listaOcorrencias = (List<Ocorrencia>) request.getAttribute("ocorrencias");
%>

<table class="table table-hover">
    <thead>
        <tr>
            <th class="text-primary">#</th>
            <th class="text-primary">Data</th>
            <th class="text-primary">Hora</th>
            <th class="text-primary">Cidade</th>
            <th class="text-primary">Autuado</th>
            <th class="text-primary">Autuante</th>
            <th class="text-primary">Tipo de Ocorrência</th>
            <th class="text-primary">Testemunha</th>
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody id="resultado-ocorrencias">
        <%
            if (listaOcorrencias == null || listaOcorrencias.isEmpty()) {
        %>
        <tr>
            <td colspan="12" class="text-center text-muted">
                Nenhuma ocorrência encontrada.
            </td>
        </tr>
        <%
        } else {
            for (Ocorrencia ocorrencia : listaOcorrencias) {
        %>
        <tr>
            <td><%=ocorrencia.getIdOcorrencia()%></td>
            <td><%=DateUtil.formataData(ocorrencia.getDataOcorrencia())%></td>
            <td><%=ocorrencia.getHoraOcorrencia() != null ? ocorrencia.getHoraOcorrencia() : ""%></td>
            <td><%=ocorrencia.getCidadeOcorrencia() != null ? ocorrencia.getCidadeOcorrencia() : ""%></td>

            <td>
                <%
                    if (ocorrencia.getAutuado() != null) {
                %>
                <a href="autuadoServlet?comando=detalhes&id_autuado=<%=ocorrencia.getAutuado().getIdAutuado()%>">
                    <%=ocorrencia.getAutuado().getNomeAutuado() != null ? ocorrencia.getAutuado().getNomeAutuado() : ""%>
                </a>
                <%
                    }
                %>
            </td>

            <td>
                <%
                    if (ocorrencia.getAutuante() != null) {
                %>
                <a href="autuanteServlet?comando=detalhes&id_autuante=<%=ocorrencia.getAutuante().getIdAutuante()%>">
                    <%=ocorrencia.getAutuante().getNomeAutuante() != null ? ocorrencia.getAutuante().getNomeAutuante() : ""%>
                </a>
                <%
                    }
                %>
            </td>

            <td>
                <%
                    if (ocorrencia.getTipoOcorrencia() != null) {
                %>
                <%=ocorrencia.getTipoOcorrencia().getNomeTipoOcorrencia() != null ? ocorrencia.getTipoOcorrencia().getNomeTipoOcorrencia() : ""%>
                <%
                    }
                %>
            </td>

            <td>
                <%
                    if (ocorrencia.getTestemunha() != null) {
                %>
                <a href="testemunhaServlet?comando=detalhes&id_testemunha=<%=ocorrencia.getTestemunha().getIdTestemunha()%>">
                    <%=ocorrencia.getTestemunha().getNomeTestemunha() != null ? ocorrencia.getTestemunha().getNomeTestemunha() : ""%>
                </a>
                <%
                    }
                %>
            </td>

            <td>
                <a href="ocorrenciaComParametro?id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="ocorrenciaServlet?comando=detalhes&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="ocorrenciaServlet?comando=prepara_editar&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="ocorrenciaServlet?comando=eliminar&id_ocorrencia=<%=ocorrencia.getIdOcorrencia()%>"
                   onclick="return confirm('Deseja realmente eliminar esta ocorrência?');">
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