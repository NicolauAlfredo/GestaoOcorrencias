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
        <style>

            body {
                min-height: 100vh;
                background: linear-gradient(135deg, #0f2f57 0%, #1f5f9f 50%, #eef4fb 50%);
                font-family: Arial, sans-serif;
            }

            .login-wrapper {
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                padding: 30px 15px;
            }

            .login-card {
                width: 100%;
                max-width: 900px;
                background: #fff;
                border-radius: 14px;
                overflow: hidden;
                box-shadow: 0 20px 45px rgba(0, 0, 0, 0.25);
            }

            .login-brand {
                min-height: 500px;
                background: #0f3d70;
                color: #fff;
                padding: 50px 35px;
                display: flex;
                flex-direction: column;
                justify-content: center;
            }

            .login-brand .brand-icon {
                width: 70px;
                height: 70px;
                border-radius: 50%;
                background: rgba(255, 255, 255, 0.15);
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 32px;
                margin-bottom: 25px;
            }

            .login-brand h1 {
                font-size: 30px;
                font-weight: bold;
                margin-bottom: 15px;
            }

            .login-brand p {
                font-size: 15px;
                line-height: 1.7;
                color: #dceeff;
            }

            .login-social-links {
                margin-top: 30px;
            }

            .login-social-links a {
                display: inline-block;
                width: 38px;
                height: 38px;
                line-height: 38px;
                text-align: center;
                border-radius: 50%;
                color: #fff;
                border: 1px solid rgba(255, 255, 255, 0.4);
                margin-right: 8px;
                text-decoration: none;
            }

            .login-social-links a:hover {
                background: #fff;
                color: #0f3d70;
            }

            .login-form-area {
                padding: 50px 40px;
            }

            .login-form-area h2 {
                margin-top: 0;
                font-weight: bold;
                color: #0f3d70;
            }

            .login-form-area .subtitle {
                color: #777;
                margin-bottom: 30px;
            }

            .input-group {
                margin-bottom: 18px;
            }

            .input-group-addon {
                background: #f5f8fb;
                color: #0f3d70;
                border-color: #d9e2ec;
            }

            .form-control {
                height: 44px;
                border-color: #d9e2ec;
                box-shadow: none;
            }

            .form-control:focus {
                border-color: #337ab7;
                box-shadow: 0 0 0 2px rgba(51, 122, 183, 0.15);
            }

            .btn-login {
                height: 46px;
                font-weight: bold;
                border-radius: 4px;
            }

            .login-extra {
                margin-top: 18px;
                font-size: 13px;
            }

            .login-extra a {
                color: #337ab7;
                text-decoration: none;
            }

            .login-extra a:hover {
                text-decoration: underline;
            }

            .social-login-title {
                margin: 25px 0 15px;
                text-align: center;
                color: #999;
                font-size: 13px;
            }

            .btn-social {
                margin-bottom: 10px;
                text-align: left;
                font-weight: bold;
            }

            .btn-social .glyphicon {
                margin-right: 8px;
            }

            @media (max-width: 767px) {
                .login-brand {
                    min-height: auto;
                    padding: 35px 25px;
                    text-align: center;
                }

                .login-brand .brand-icon {
                    margin-left: auto;
                    margin-right: auto;
                }

                .login-form-area {
                    padding: 35px 25px;
                }
            }
        </style>
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

                                <%
                                    String nipGuardado = "";
                                    Cookie[] cookies = request.getCookies();
                                    if (cookies != null) {
                                        for (Cookie cookie : cookies) {
                                            if ("nip_administrador".equals(cookie.getName())) {
                                                nipGuardado = cookie.getValue();
                                                break;
                                            }
                                        }
                                    }
                                %>

                                <input
                                    class="form-control"
                                    type="text"
                                    id="nip_administrador"
                                    name="nip_administrador"
                                    placeholder="NIP"
                                    value="<%=nipGuardado%>"
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