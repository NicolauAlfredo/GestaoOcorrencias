<%-- 
    Document   : login
    Created on : 05/07/2020, 23:12:25
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>       
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
    </head>
    <body>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="login-panel panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title text-center">LOGIN</h3>
                            <br>
                            <div class="alert alert-warning">
                                <p class="text-center">${message}</p>
                            </div> 
                        </div>

                        <div class="panel-body">
                            <form role="form" action="<%=request.getContextPath()%>/administradorServlet?comando=entrar" method="POST">
                                <div class="form-group-lg">
                                    <div class="col-xs-12">
                                        <input class="form-control" type="text" id="nip_administrador" name="nip_administrador" placeholder="NIP" required autofocus/>
                                        <br>

                                        <input class="form-control" type="password" id="palavra_passe_administrador" name="palavra_passe_administrador" placeholder="Palavra-passe" required autofocus/>
                                        <br>

                                        <button class="form-control btn btn-primary" type="submit">
                                            <span class="glyphicon glyphicon-log-in"> ENTRAR </span>
                                        </button>  
                                    </div>
                                </div>
                            </form>
                        </div>
                                
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
