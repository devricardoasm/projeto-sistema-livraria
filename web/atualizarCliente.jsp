

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

            <!-- Header -->
            <div id="header-wrapper">
                <header id="header" class="container">

                </header>
            </div>

            <!-- Banner -->
            <div id="banner-wrapper">
                <div id="banner" class="box container">
                    <div class="row">
                        <div class="7u 12u(medium)">

                            <%
                                //PUXAR DA SESSAO O USUARIO 
                                Usuario usuario = (Usuario) request.getAttribute("dadosCliente");
                                if (usuario == null) {
                                    request.getRequestDispatcher("/ControleAcesso?acao=DadosClienteAtualizar").forward(request, response);
                                }
                            %>


                            <form id="form" action="ControleAcesso" method="post">

                                <table>


                                    <tr>
                                        <td><label for="email" ></label>seu email</td>
                                        <td><input  type="email" id="email" value="<%= usuario.getLogin().getLogin()%>" disabled></td>
                                        <td><input  type="hidden" id="email" name="txtLogin" value=<%= usuario.getLogin().getLogin()%> ></td>
                                        <td></td>

                                    </tr>


                                    <tr>
                                        <td><label for="cpf" ></label>seu cpf</td>
                                        <td><input  type="text" id="cpf" value=<%= usuario.getCpf()%> disabled></td>                                        
                                        <td></td>

                                    </tr>





                                    <tr>
                                        <td><label for="nome" ></label>Nome</td>
                                        <td><input type="text" id="nome" name="txtNome" value="<%=usuario.getNome()%>"></td>
                                        <td></td>

                                    </tr>
                                    <tr>

                                        <td><label for="sobrenome" ></label>Sobrenome</td>
                                        <td><input type="text" id="sobrenome" name="txtSobrenome" value="<%=usuario.getSobrenome()%>"></td>
                                        <td></td>
                                    </tr>

                                    <tr>

                                        <td><label for="telefone" ></label>Telefone</td>
                                        <td><input type="text" id="telefone" name="txtTel" value="<%=usuario.getTelefone()%>"></td>
                                        <td></td>
                                    </tr>

                                    <tr>

                                    <a href="alterarSenha.jsp">Quer alterar sua senha?</a>   
                                    </tr>
                                    <tr>
                                        <td></td> 

                                        <td></td>  
                                </table>
                                <input type="submit" name="acao" value="Atualizar">
                                </tr>
                            </form>

                        </div>
                        <div class="5u 12u(medium)">
                            -
                        </div>
                    </div>

                </div>




                <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
                <script src="js/jquery.validate.js"type="text/javascript"></script>
                <script src="js/validacao.js"type="text/javascript"></script>
                <link href="css/css.css" rel="stylesheet" type="text/css"/>

                </body>
                </html>
