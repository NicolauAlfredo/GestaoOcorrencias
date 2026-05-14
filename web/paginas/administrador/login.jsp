<%-- 
    Document   : login
    Created on : 05/07/2020, 23:12:25
    Author     : user
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <base href="<%=request.getContextPath()%>/">

        <title>Login | Sistema Policial</title>

        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="Bootstrap/js/jquery-1.12.3.min.js"></script>
        <script src="Bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../../css/login.css"/>

    </head>

    <body>
        <div class="login-wrapper">
            <div class="login-card">
                <div class="row" style="margin: 0;">

                    <div class="col-sm-5 login-brand">
                        <div class="brand-icon">
                            <span class="glyphicon glyphicon-shield"></span>
                        </div>

                        <h1>Sistema Policial</h1>

                        <p>
                            Plataforma de gestão de ocorrências, autuados, autuantes,
                            testemunhas e dados administrativos.
                        </p>

                        <div class="login-social-links">
                            <a href="#" title="Facebook">
                                <span class="glyphicon glyphicon-thumbs-up"></span>
                            </a>

                            <a href="#" title="LinkedIn">
                                <span class="glyphicon glyphicon-briefcase"></span>
                            </a>

                            <a href="#" title="Email">
                                <span class="glyphicon glyphicon-envelope"></span>
                            </a>

                            <a href="#" title="Website">
                                <span class="glyphicon glyphicon-globe"></span>
                            </a>
                        </div>
                    </div>

                    <div class="col-sm-7 login-form-area">
                        <h2>Bem-vindo</h2>

                        <p class="subtitle">
                            Entre com as suas credenciais para acessar o painel administrativo.
                        </p>

                        <%
                            String message = (String) request.getAttribute("message");

                            if (message != null && !message.trim().isEmpty()) {
                        %>
                        <div class="alert alert-warning">
                            <p><%=message%></p>
                        </div>
                        <% }%>

                        <form role="form" action="administradorServlet?comando=entrar" method="POST">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </span>

                                <input
                                    class="form-control"
                                    type="text"
                                    id="nip_administrador"
                                    name="nip_administrador"
                                    placeholder="NIP"
                                    required
                                    autofocus>
                            </div>

                            <div class="input-group">
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </span>

                                <input
                                    class="form-control"
                                    type="password"
                                    id="palavra_passe_administrador"
                                    name="palavra_passe_administrador"
                                    placeholder="Palavra-passe"
                                    required>
                            </div>

                            <button class="btn btn-primary btn-block btn-login" type="submit">
                                <span class="glyphicon glyphicon-log-in"></span>
                                Entrar
                            </button>

                            <div class="login-extra clearfix">
                                <label class="pull-left">
                                    <input type="checkbox"> Lembrar-me
                                </label>

                                <a href="#" class="pull-right">
                                    Esqueceu a palavra-passe?
                                </a>
                            </div>
                        </form>

                        <div class="social-login-title">
                            Links institucionais
                        </div>

                        <div class="row">
                            <div class="col-sm-6">
                                <a href="#" class="btn btn-default btn-block btn-social">
                                    <span class="glyphicon glyphicon-globe"></span>
                                    Website
                                </a>
                            </div>

                            <div class="col-sm-6">
                                <a href="#" class="btn btn-default btn-block btn-social">
                                    <span class="glyphicon glyphicon-envelope"></span>
                                    Contacto
                                </a>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>