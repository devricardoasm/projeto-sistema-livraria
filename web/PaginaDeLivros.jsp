<%-- 
    Document   : index
    Created on : 07/03/2016, 16:15:37
    Author     : 11131103404
--%>

<%@page import="modelos.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/tamanhoimg.css" media="all">
        <link rel="stylesheet" href="css/PaginaDeLivros.css" media="all">
        <link rel="stylesheet" href="css/modal.css" media="all">
        <link rel="stylesheet" href="css/modalSinopse.css" media="all"> 
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script language="javascript" src="js/jquery-1.7.2.js"></script>
        <style type="text/css">

            #background-layout{background:url(image/livros.jpg);
                               background-size:300%;
            }

            #formCarrinhoDeCompras2{                                        
                background-size:100%;
                background:#000;
                opacity:0,9;
                border-radius:30px 30px 30px 30px;
            }    

        </style>

     
        <title>Livraria Virtual Book</title>
    </head>
    <body id="background-layout"> 
        <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");

            if (usuario == null) {

        %>

        <button id="myBtn">Entrar / cadastre-se</button>

        <div id="myModal" class="modal">


            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">×</span>
                    <h2>Faça seu login</h2>
                </div>
                <div class="modal-body">

                    <form action="ControleAcesso" method="post">
                        <table>

                            <tr>
                                <td><input type="email" class="inputemail" placeholder="email@email.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required="required" name="txtLogin">   </td> 
                                <td></td>
                            </tr>

                            <tr>

                                <td><input type="password" class="inputsenha" placeholder="Senha" required="required" name="txtSenha"> </td>   
                                <td></td>
                            </tr>                       
                        </table>
                        <input type="submit" value="Logar" class="submitlogar" name="acao">
                        <a href="cadastrarCliente.jsp"><button class="submitcadastrar" type="button">Cadastre-se</button></a>
                    </form>
                </div>

            </div>

        </div>


        <script src="js/abrirCadastro.js"type="text/javascript"></script>



        <%} else {%>

        <a href="principal.jsp"><button type="button" id="entreoucadastrese">Meu cadastro</button></a>

        <label id="ola"> olá <%=usuario.getNome()%></label> 

        <%}%>



        <form action="#" method="post">
            <input type="text" class="input" placeholder="Oque você procura?" required="required" name="txtIsbn">
            <input type="submit" class="submitPesquisarCarrinho" name="acao" value="#">
        </form>

        <form id="formCarrinhoDeCompras2">


        </form>


        <%
            String msg = (String) request.getAttribute("msgexiste");
            if (msg != null) {
        %>

        <font color="red"> <%=msg%> <label>caso queira ver Clique</label><a href="ControleCarrinho?acao=vercarrinho"> AQUI</a></font>
        <%}%>


        <%
            String msgsemcarrinho = (String) request.getAttribute("msgg");
            if (msgsemcarrinho != null) {
        %>

        <font id="verCarrinhoTexto2" color="black"> <%=msgsemcarrinho%></font>
        <%}%>


        <%
            // Recupera os produtos do request
            ArrayList<Produto> produtos = (ArrayList<Produto>) request.getAttribute("produtos");
            if (produtos == null) {
                //envia requisição para a servlet 
                request.getRequestDispatcher("/ControleCarrinho?acao=listar").forward(request, response);
            }
        %>

        <form action="ControleCarrinho" method="post">
            <label >Listar pelo:</label>
            <select id="opcoes3"  name="escolha">
                <option value="titulo">Titulo</option>             
                <option value="precoMaior">Preco menor</option>
                <option value="precoMenor">Preco maior</option>
            </select>
            <input id="opcoes3" type="submit" value="listar"  name="acao">
        </form>


        <form action="ControleCarrinho" method="post">

            <label id="verCarrinhoTexto">Ver carrinho</label>
            <input type="submit" class="verCarrinho" name="acao" value="vercarrinho">


        </form>




        <table border="0" cellpadding="5" align="center">
            <%
                int contadorColuna = 1;
                for (Produto produto : produtos) {
                    //se é o primeiro produto, cria o inicio da linha
                    if (contadorColuna == 1) {
                        out.println("<tr>");
                    }
            %>

            <td align="center"> 
                <div class="container">
                    <img src="imagens/<%=produto.getImagem()%>" alt="Imagem do produto<%=produto.getImagem()%>"/> <br/>
                </div>
                <label id="nomeProduto"> <%=produto.getTitulo()%></label><br/>
                <label id="nomeProduto"><label>R$</label><%=produto.getPreco()%></label><br/>
                <a href="ControleCarrinho?acao=addProduto&idProduto=<%=produto.getId()%>"> <button type="button" id="opcaoComprar">Comprar</button></a>            
                <a href="ControleCarrinho?acao=verSinopse&idProduto=<%=produto.getId()%>"> <button type="button" id="opcaoComprar2">Ver sinopse</button></a>

            </td>            
            <%
                    //se completou 3 colunas, fecha a primeira linha
                    if (contadorColuna == 5) {
                        out.println("</tr>");
                        contadorColuna = 0;
                    }
                    //atualiza o contador de colunas
                    contadorColuna++;

                }//fim do for
            %>  
        </table>





        <%
            Produto sinopse = (Produto) request.getAttribute("sinopse");
            if (sinopse != null) {
        %>   


        <div id="myModalSinopse" class="modalSinopse">


            <div class="modal-contentSinopse">
                <div class="modal-headerSinopse">
                    <span class="closeSinopse">×</span>
                    <h2>Leia a sinopse</h2>
                </div>
                <div class="modal-bodySinopse">


                    <P id="titulo">Titulo: <label id="titulo2">  <%=sinopse.getTitulo()%> </label> <P>   
                        <textarea id="sinopse" disabled="true"><%= sinopse.getSinopse()%> </textarea>


                </div>

            </div>



        </div>


        <script src="js/abrirSinopse.js"type="text/javascript"></script>



        <%}%>

    </body>
</html>
