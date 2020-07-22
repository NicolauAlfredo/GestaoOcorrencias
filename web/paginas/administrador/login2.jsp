<%-- 
    Document   : login
    Created on : 09/06/2020, 18:17:09
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
    <body style=" color: rgba(0,0,0,1); font-family: Arial, sans-serif;">
        <div class="container">
            <div style="width: 940px; height: 400px; background: #ccccff; margin: 100px auto 0px auto; box-shadow: 0px 0px 10px rgba(0,0,0,.5); padding: 10px;">                
                <h1 style="margin-top: 50px;" class="text-primary text-center"> 
                    LOGIN
                </h1> 

                <form role="form" action="<%=request.getContextPath()%>/loginServlet?comando=entrar" method="POST">
                    <div class="form-group-lg">
                        <div class="col-xs-6">
                            <div style="margin-left: 260px; width: 370px; background: gainsboro;" class="alert alert-warning">
                                <p>${message}</p>
                            </div>
                            <br/>
                            
                            <input style="margin-left: 230px" class="form-control" type="text" id="username" name="username" placeholder="Nome de usuário" required autofocus/>
                            <br/>

                            <input style="margin-left: 230px" class="form-control" type="password" id="password" name="password" placeholder="Palavra-passe" required autofocus/>
                            <br/>                           
                            
                            <button style="margin-left: 230px" class="form-control btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-log-in"> ENTRAR </span>
                            </button>   
                        </div>
                    </div>  
                </form>
            </div>
        </div>
    </body>
</html>
