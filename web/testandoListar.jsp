<%-- 
    Document   : testandoListar
    Created on : 04/04/2016, 15:51:04
    Author     : 11131103404
--%>

<%@page import="java.util.ArrayList"%>
<%-- 
    Document   : testandoExcluir
    Created on : 03/04/2016, 02:59:52
    Author     : guilherme
--%>

<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- HTML5 Boilerplate -->
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

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
        <link rel="stylesheet" href="css/ListarLivro.css" media="all">
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
                background-color:#c5b8fc;

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
            #background-layout{background:url(image/livros.jpg);
                               background-size:300%;}
            /*                            #formCadastrarLivro{
                                        background:url(image/textura-input.jpg);
                                        background-size:100%;
                                        opacity:0,9;
                                        border-radius:30px 30px 30px 30px;
                                    }*/
        </style>


    <form action="ControleProduto" method="post">
        <input type="text" class="input2" placeholder="Consultar Livro pelo isbn" required="required" name="txtIsbn">
        <input type="submit" class="submitCadastrarLivroPesquisar" name="acao" value="consultar">
    </form>



</head>

<body id="background-layout">


    <%
        String msgNaoExisteIsbn = (String) request.getAttribute("msgNaoExisteIsbn");
        if (msgNaoExisteIsbn != null) {
    %>

    <font id="MsgNaoExiste" color="red">  <%=msgNaoExisteIsbn%></font>
    <%}%>


    <%
        String msgPdfComSucesso = (String) request.getAttribute("msgPdfComSucesso");
        if (msgPdfComSucesso != null) {
    %>

    <font id="MsgNaoExiste" color="red">  <%=msgPdfComSucesso%></font>
    <%}%>




    <%
        ArrayList<Produto> listaProduto = (ArrayList<Produto>) request.getAttribute("listaProduto");
        if (listaProduto == null) {

            request.getRequestDispatcher("/ControleProduto?acao=Listar").forward(request, response);
        }
    %>


    <nav id="nav">
        <ul>
            <li class="current"><form action="InserirPdf.jsp" method="post">
                    <input type="submit" class="submitCadastrarLivro" name="acao" value="Cadastrar">
                </form></li>
            <li>           
            <li><form action="testandoUmaTelaPrincipal.jsp" method="post">
                    <input type="submit" class="submitCadastrarLivro" name="acao" value="principal">
                </form></li> 
        </ul>
    </nav>



    <!--- Sistema de Grid, não alterar --->

    <div class="section group">
        <div class="col span_1_of_2">



        </div>
        <div class="col span_1_of_2">


        </div>
    </div>

    <span id="msg"></span>


    <form action="ControleProduto" method="post">
        <label>Listar pelo:</label>
        <select id="opcoes3"  name="cars">
            <option value="titulo">Titulo</option>
            <option value="data">Data</option>  
            <option value="preco">Preco</option>
        </select>
        <input id="opcoes3" type="submit" value="Listar"  name="acao">



        <div class="section group">
            <div class="col span_1_of_2">



                <form id="formCadastrarLivro" action="ControleProduto" method="POST">


                    <div id="box-inputs">
                        <caption> <h1 id="cabecalho">LIVROS CADASTRADOS</h1> </caption>
                        <table border="1" width="1010px" class="borda" id="t01">

                            <thead> 


                                <tr>
                                    <th width="150px">Isbn</th>                                
                                    <th width="280px">Titulo</th>
                                    <th width="160px">Autor</th>
                                    <th width="90px">Preço</th>
                                    <th width="120px">Lançamento</th>


                                </tr>
                            </thead>


                            <%
                                for (Produto p : listaProduto) {

                            %>
                            <tr>

                                <td> <%= p.getIsbn()%> </td>
                                <td> <%= p.getTitulo()%> </td>
                                <td> <%= p.getAutor()%> </td>
                                <td> <%= p.getPreco()%> </td> 
                                <td> <%= p.getLancamento()%> </td>

                                <td width="79px"> <a
                                        href="ControleProduto?acao=AtualizarLivro&isbn=<%=p.getIsbn()%>">
                                        <button type="button" id="opcoes2" onclick="alert('Altere somente os dados necessário')">Editar</button></td>

                                <td width="79px"> <a
                                        href="ControleProduto?acao=Deletar&isbn=<%=p.getIsbn()%>">
                                        <button type="button" id="opcoes" onclick="alert('Certifique que este é o livro que deseja excluir')">Excluir</button></td>

                                <td width="123px">   <a
                                        href="ControleProduto?acao=AdicionarPdf&isbn=<%=p.getIsbn()%>">
                                        <button type="button" id="opcoes"">Alterar pdf</button></td>

                            </tr> 
                            <%
                                }
                            %>


                        </table> 



                    </div>

                </form>

            </div>

        </div>


</body>
</html>

