<%-- 
    Document   : carrinho
    Created on : 07/03/2016, 16:15:45
    Author     : 11131103404
--%>

<%@page import="modelos.ItemDeCompra"%>
<%@page import="modelos.CarrinhoDeCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>Virtual Book</title>

        <meta http-equiv="cleartype" content="on">

        <link rel="shortcut icon" href="/favicon.ico">
        <meta name="MobileOptimized" content="320">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"type="text/javascript"></script>
        <script src="js/validacaoLogin.js"type="text/javascript"></script>

        <!-- Stylesheets -->
        <link rel="stylesheet" href="css/CarrinhoDeCompras.css" media="all">
        <link rel="stylesheet" href="css/html5reset.css" media="all">
        <link rel="stylesheet" href="css/col.css" media="all">
        <link rel="stylesheet" href="css/2cols.css" media="all">
        <link rel="stylesheet" href="css/3cols.css" media="all">
        <link rel="stylesheet" href="css/4cols.css" media="all">
        <link rel="stylesheet" href="css/5cols.css" media="all">
        <link rel="stylesheet" href="css/6cols.css" media="all">
        <link rel="stylesheet" href="css/7cols.css" media="all">
        <link rel="stylesheet" href="css/8cols.css" media="all">
        <link rel="stylesheet" href="css/9cols.css" media="all">
        <link rel="stylesheet" href="css/10cols.css" media="all">
        <link rel="stylesheet" href="css/11cols.css" media="all">
        <link rel="stylesheet" href="css/12cols.css" media="all">
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <style type="text/css">

            th, td {
                padding: 2px;
            }

            th {
                text-align: left;
            }


            table#t01 tr:nth-child(even) {
                background-color:#65ffba;

            }
            table#t01 tr:nth-child(odd) {

            }

            body { padding:2em; font : 100%/1.4 'Helvetica Neue', arial, helvetica, helve, sans-serif; 	
            }
            h1 { font-size:2.2em; padding:0 0 .5em 0; }
            h2 { font-size:1.5em; }
            .header { padding:1em 0; }
            /*
            .col { background: #ccc; padding:1em 0; text-align:center;}
            */
            /*                        #background-layout{background:url(image/livros.jpg);
                                                       background-size:100%;}*/
            #formCarrinhoDeCompras{                                        
                background-size:100%;
                background:#65ffba;
                opacity:0,9;
                border-radius:30px 30px 30px 30px;
            }
            #formCarrinhoDeCompras2{                                        
                background-size:100%;
                background:#65ffba;
                opacity:0,9;
                border-radius:30px 30px 30px 30px;
            }
        </style>


    </head>
    <body id="background-layout">

        <form id="formCarrinhoDeCompras2">


        </form>


        <h1 id="cabecalho">Carrinho de Compras!</h1>
        <table border="1" cellpadding="2" class="borda" id="t01" >

            <thead> 

                <tr>
                    <th width="150px">Imagem</font></th>
                    <th width="280px">Titulo</font></th>
                    <th width="180px">Autor</font></th>
                    <th width="140px" >QTD</font></th>                
                    <th width="180px" >Valor do livro</th> 
                    <th width="160px" >Isbn</font></th>

                </tr>

            </thead>
            <%
                //recupera os produtos do carrinho da sessao
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) session.getAttribute("carrinho");
                
                if(carrinho == null){
                    return;
                }
                
                
                for (ItemDeCompra item : carrinho.getItens()) {

            %>
            <tr>
                <td>
                    <div class="container">
                        <img src="imagens/<%=item.getProduto().getImagem()%>" alt="Imagem do produto<%=item.getProduto().getImagem()%>"/>  </div> </td>

                <td height="100"><%=item.getProduto().getTitulo()%></td>                
                <td height="100"><%=item.getProduto().getAutor()%></td>                
                <td height="100"><%=item.getQuantidade()%></td>                 
                <td height="100">R$ <%=item.getProduto().getPreco()%></td>                
                <td height="100"><%=item.getProduto().getIsbn()%></td>

                <td><a
                        href="ControleCarrinho?acao=removeProduto&idProduto=<%=item.getProduto().getId()%>">
                        <button type="button" id="opcoes4">Excluir</button></td>


            </tr>

            <%
                }
            %>

        </table>


        <a href="PaginaDeLivros.jsp"><button type="button" id="opcoes3" > Continue comprando </button></a>


        <form id="formCarrinhoDeCompras">

            <strong id="valortotal">Valor Total: <%=carrinho.calculaTotal()%></strong>
            <a href="ControleCarrinho?acao=finalizarCompra"><button type="button" id="opcoes2" > FINALIZAR COMPRA</button></a>

            <a href="ControleCarrinho?acao=cancelaCompra"><button type="button" id="opcoes5" > CANCELAR</button></a> 

        </form> 


    </body>
</html>
