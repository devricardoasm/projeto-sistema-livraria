
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Virtual book</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />        
        <link rel="stylesheet" href="assets/css/main_1.css" />

    </head>
    <body class="homepage">
        <div id="page-wrapper">

            <div id="header-wrapper">
                <header id="header" class="container">

                </header>
            </div>
    </body>


    <%
        //PUXAR DA SESSAO O USUARIO 
        Usuario usuario = (Usuario) request.getAttribute("dadosCliente");
        if (usuario == null) {
            request.getRequestDispatcher("/ControleAcesso?acao=DadosClienteExcluir").forward(request, response);
        }
    %>



    <!-- Banner -->
    <div id="banner-wrapper">
        <div id="banner" class="box container">
            <div class="row">
                <div class="7u 12u(medium)">

                    <form id="form" action="ControleAcesso" method="post">

                        <table>


                            <tr>
                                <td><label for="email" ></label>seu email</td>
                                <td><input  type="email" id="email" value=<%= usuario.getLogin()%> disabled></td>
                                <td><input  type="hidden" id="email" name="txtLogin" value=<%= usuario.getLogin()%> ></td>
                                <td></td>

                            </tr> 





                            <tr>
                                <td><label for="id" ></label>Digite sua senha</td>
                                <td><input type="password" id="id" name="txtSenha"/></td>
                                <td></td>
                            </tr> 

                            <tr>
                                <td></td>                        
                        </table>
                        <input type="submit" name="acao" value="Excluir">
                        </tr>

                    </form>
                </div>
                <span id="msg"></span>  
                </body>


            </div>
            <div class="5u 12u(medium)">
                -
            </div>
        </div>

    </div>



</div>



<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="js/jquery.validate.js"type="text/javascript"></script>
<script src="js/validacao.js"type="text/javascript"></script>
<link href="css/css.css" rel="stylesheet" type="text/css"/>

</html>
