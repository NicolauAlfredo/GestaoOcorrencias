<%-- 
    Document   : administrador_tabela
    Created on : 14 mag 2026, 21:57:16
    Author     : Nicolau Alfredo
--%>

<%@page import="modelo.DateUtil"%>
<%@page import="modelo.Administrador"%>
<%@page import="java.util.List"%>

<%
    List<Administrador> listaAdministradores = (List<Administrador>) request.getAttribute("administradores");
%>

<table class="table table-hover">
    <thead>
        <tr>
            <th class="text-primary">#</th>
            <th class="text-primary">Nome</th>
            <th class="text-primary">Data de Nascimento</th>
            <th class="text-primary">Sexo</th>
            <th class="text-primary">B.I.</th>
            <th class="text-primary">Email</th>
            <th class="text-primary">Telefone</th>
            <th class="text-primary" colspan="4">Operações</th>
        </tr>
    </thead>

    <tbody id="resultado-administradores">

        <%
            if (listaAdministradores == null || listaAdministradores.isEmpty()) {
        %>

        <tr>
            <td colspan="11" class="text-center text-muted">
                Nenhum administrador encontrado.
            </td>
        </tr>

        <%
        } else {
            for (Administrador administrador : listaAdministradores) {
        %>

        <tr>
            <td><%=administrador.getIdAdministrador()%></td>

            <td>
                <%=administrador.getNomeAdministrador() != null
                        ? administrador.getNomeAdministrador()
                        : ""%>
            </td>

            <td>
                <%=DateUtil.formataData(administrador.getDataNascimentoAdministrador())%>
            </td>

            <td>
                <%=administrador.getSexo() != null
                        ? administrador.getSexo().getExtensao()
                        : ""%>
            </td>

            <td>
                <%=administrador.getBiAdministrador() != null
                        ? administrador.getBiAdministrador()
                        : ""%>
            </td>

            <td>
                <%=administrador.getEmailAdministrador() != null
                        ? administrador.getEmailAdministrador()
                        : ""%>
            </td>

            <td>
                <%=administrador.getTelefoneAdministrador() != null
                        ? administrador.getTelefoneAdministrador()
                        : ""%>
            </td>

            <td>
                <a href="administradorServlet?comando=detalhes&id_administrador=<%=administrador.getIdAdministrador()%>">
                    <span class="glyphicon glyphicon-print"></span>
                </a>
            </td>

            <td>
                <a href="administradorServlet?comando=detalhes&id_administrador=<%=administrador.getIdAdministrador()%>">
                    <span class="glyphicon glyphicon-zoom-in"></span>
                </a>
            </td>

            <td>
                <a href="administradorServlet?comando=prepara_editar&id_administrador=<%=administrador.getIdAdministrador()%>">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>

            <td>
                <a href="administradorServlet?comando=eliminar&id_administrador=<%=administrador.getIdAdministrador()%>"
                   onclick="return confirm('Deseja realmente eliminar este administrador?');">
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
