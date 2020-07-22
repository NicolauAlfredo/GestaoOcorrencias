<%-- 
    Document   : cabecalho
    Created on : 08/12/2019, 07:52:38
    Author     : user
--%>

<%@page import="modelo.Administrador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cabeçalho</title>
        <link href="<%=request.getContextPath()%>/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>       
        <script src="<%=request.getContextPath()%>/Bootstrap/js/jquery-1.12.3.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/Bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body>
        <nav class="navbar navbar-default" role="navigation"> 
            <div class="navbar-header"> 
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#example-navbar-collapse"> 
                    <span class="sr-only">Toggle navigation</span> 
                    <span class="icon-bar"></span> 
                    <span class="icon-bar"></span> 
                    <span class="icon-bar"></span> 
                </button> 
                <a class="navbar-brand" href="<%=request.getContextPath()%>/paginas/index.jsp"><span class="glyphicon glyphicon-home"> Home </span></a>                     
            </div> 

            <div class="collapse navbar-collapse" id="example-navbar-collapse"> 
                <ul class="nav navbar-nav navbar-left">                       
                    <li class="dropdown"> 
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-edit"> Ocorrência </span> <b class="caret"></b></a> 
                        <ul class="dropdown-menu"> 
                            <li><a href="<%=request.getContextPath()%>/paginas/ocorrencia/ocorrencia_registo.jsp"><span class="glyphicon glyphicon-plus"> Registar </span></a></li>   
                            <li><a href="<%=request.getContextPath()%>/paginas/ocorrencia/ocorrencia_listar.jsp"><span class="glyphicon glyphicon-list-alt"> Registos </span></a></li>   
                        </ul> 
                    </li> 

                    <li class="dropdown"> 
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="glyphicon glyphicon-user"> Autuado </span> <b class="caret"></b></a> 
                        <ul class="dropdown-menu"> 
                            <li><a href="<%=request.getContextPath()%>/paginas/autuado/autuado_registo.jsp"><span class="glyphicon glyphicon-plus"> Registar </span></a></li>   
                            <li><a href="<%=request.getContextPath()%>/paginas/autuado/autuado_listar.jsp"><span class="glyphicon glyphicon-list-alt"> Registos </span></a></li>   
                        </ul> 
                    </li> 

                    <li class="dropdown"> 
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"> Autuante </span> <b class="caret"></b></a> 
                        <ul class="dropdown-menu"> 
                            <li><a href="<%=request.getContextPath()%>/paginas/autuante/autuante_registo.jsp"><span class="glyphicon glyphicon-plus"> Registar </span></a></li>   
                            <li><a href="<%=request.getContextPath()%>/paginas/autuante/autuante_listar.jsp"><span class="glyphicon glyphicon-list-alt"> Registos </span></a></li>   
                        </ul> 
                    </li> 

                    <li class="dropdown"> 
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"> Testemunha </span> <b class="caret"></b></a> 
                        <ul class="dropdown-menu"> 
                            <li><a href="<%=request.getContextPath()%>/paginas/testemunha/testemunha_registo.jsp"><span class="glyphicon glyphicon-plus"> Registar </span></a></li>   
                            <li><a href="<%=request.getContextPath()%>/paginas/testemunha/testemunha_listar.jsp"><span class="glyphicon glyphicon-list-alt"> Registos </span></a></li>   
                        </ul> 
                    </li> 
                </ul>

                <ul class="nav navbar-nav navbar-right"> 
                    <li class="dropdown"> 
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="Listas"> <span class="glyphicon glyphicon-list-alt"> </span> <b class="caret"></b></a>                     
                        <ul class="dropdown-menu"> 
                            <li><a href="<%=request.getContextPath()%>/paginas/municipio/municipio_listar.jsp">Município</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/patente/patente_listar.jsp">Patente</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/posto/posto_trabalho_listar.jsp">Posto Trabalho</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/profissao/profissao_listar.jsp">Profissão</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/provincia/provincia_listar.jsp">Província</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/tipo/tipo_ocorrencia_listar.jsp">Tipo Ocorrência</a></li>
                        </ul> 
                    </li> 

                    <li class="dropdown"> 
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" title="Pesquisas"> <span class="glyphicon glyphicon-search"> </span> <b class="caret"></b></a>                     
                        <ul class="dropdown-menu"> 
                            <li><a href="<%=request.getContextPath()%>/paginas/autuado/autuado_listar_por_nome.jsp">Autuado</a></li>   
                            <li><a href="<%=request.getContextPath()%>/paginas/autuante/autuante_listar_por_nome.jsp">Autuante</a></li> 
                            <li><a href="<%=request.getContextPath()%>/paginas/municipio/municipio_listar_por_nome.jsp">Município</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/ocorrencia/ocorrencia_listar_por_autuado.jsp">Ocorrência</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/patente/patente_listar_por_nome.jsp">Patente</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/posto/posto_trabalho_listar_por_nome.jsp">Posto de Trabalho</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/profissao/profissao_listar_por_nome.jsp">Profissão</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/provincia/provincia_listar_por_nome.jsp">Província</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/testemunha/testemunha_listar_por_nome.jsp">Testemunha</a></li>
                            <li><a href="<%=request.getContextPath()%>/paginas/tipo/tipo_ocorrencia_listar_por_nome.jsp">Tipo de Ocorrência</a></li>
                        </ul> 
                    </li> 

                    <li> 
                        <a href="<%=request.getContextPath()%>/paginas/administrador/administracao.jsp" title="Administração">  <span class="glyphicon glyphicon-option-horizontal"> </span> </a>                     
                    </li> 

                    <li>  
                        <a href="<%=request.getContextPath()%>/administradorServlet?comando=sair" title="Terminar sessão"> <span class="glyphicon glyphicon-log-out"> <%Administrador usuario = (Administrador)session.getAttribute("administrador"); out.print(usuario.getNomeAdministrador());%> </span> </a>                      
                    </li>
                </ul>
            </div>
        </nav>
    </body>
</html>
