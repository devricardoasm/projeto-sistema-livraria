<%-- 
    Document   : alterarSenha
    Created on : 03/05/2016, 16:35:51
    Author     : 11131103404
--%>


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
                                    request.getRequestDispatcher("/ControleAcesso?acao=DadosClienteAlterarSenha").forward(request, response);
                                }
                            %>

                            <%
                                String msg = (String) request.getAttribute("invalido");
                                if (msg != null) {
                            %>

                            <font color="red"> <%=msg%></font>
                            <%}%>


                            <form id="form" action="ControleAcesso" method="post">

                                <table>


                                    <tr>
                                        <td><label for="email" ></label>seu email</td>
                                        <td><input  type="email" id="email" value="<%= usuario.getLogin().getLogin()%>" disabled></td>
                                        <td><input  type="hidden" id="email" name="txtLogin" value=<%= usuario.getLogin().getLogin()%> ></td>
                                        <td></td>

                                    </tr>



                                    <tr>
                                        <td><label for="senha" ></label>Nova senha</td>
                                        <td><input type="password" id="senha" name="txtSenha" ></td>
                                        <td></td>

                                    </tr>

                                    <tr>
                                        <td><label for="senha" ></label>Digite novamente a nova senha</td>
                                        <td><input type="password" id="senha" name="txtSenha2" ></td>
                                        <td></td>

                                    </tr>

                                    <tr>
                                        <td></td> 

                                        <td></td>  
                                </table>
                                <input type="submit" name="acao" value="Alterar">
                                </tr>
                            </form>

                        </div>
                        <div class="5u 12u(medium)">
                            -
                        </div>
                    </div>

                </div>



                </body>
                </html>
