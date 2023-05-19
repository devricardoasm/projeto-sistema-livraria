<%-- 
    Document   : testandoInputLivro
    Created on : 03/04/2016, 02:18:25
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
        <link rel="stylesheet" href="css/PrincipalADM.css" media="all">
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


            body { padding:2em; font : 100%/1.4 'Helvetica Neue', arial, helvetica, helve, sans-serif; 	
            }
            h1 { font-size:2.2em; padding:0 0 .5em 0; }
            h2 { font-size:1.5em; }
            .header { padding:1em 0; }
            /*
            .col { background: #ccc; padding:1em 0; text-align:center;}
            */
            #background-layout{background:url(image/livros.jpg);                               
                               background-size:110%;}
            #formInserirLivro1{
                background:#000;
                background-size:100%;
                opacity:1;
                border-radius:30px 30px 30px 30px;
            }
            #formInserirLivro2{
                background: #000;
                background-size:100%;
                opacity:1;
                border-radius:30px 30px 30px 30px;
            }
            /*            #formInserirLivro3{
                            background: #000;
                            background-size:100%;
                            opacity:1;
                            border-radius:30px 30px 30px 30px;
                        }
                        #formInserirLivro4{
                            background: #000;
                            background-size:100%;
                            opacity:1;
                            border-radius:30px 30px 30px 30px;
                        }*/
        </style>



        <%
            String msgExcluido = (String) request.getAttribute("msgExcluido");
            if (msgExcluido != null) {
        %>

    <font id="MsgNaoExiste" color="red"> <%=msgExcluido%></font>
    <%}%>



    <%
        String msgJaExisteIsbn = (String) request.getAttribute("msgJaExisteIsbn");
        if (msgJaExisteIsbn != null) {
    %>

    <font id="MsgNaoExiste" color="red"> <%=msgJaExisteIsbn%></font>
    <%}%>

    <form action="ControleProduto" method="post">
        <input type="text" class="input2" placeholder="Consulte aqui o Livro pelo seu isbn" required="required" name="txtIsbn">
        <input type="submit" class="submitCadastrarLivroPesquisar" name="acao" value="consultar">
    </form>

</head>

<body id="background-layout">




    <!--- Sistema de Grid, não alterar --->

    <div class="section group">
        <div class="col span_1_of_2">



        </div>
        <div class="col span_1_of_2">


        </div>
    </div>

    <span id="msg"></span>






    <div class="section group">
        <div class="col span_1_of_2">



            <form id="formInserirLivro1" action="InserirPdf.jsp" method="post">

                <input type="submit" class="submitInserirLivro" name="acao" value="Quer Cadastrar um livro?">

            </form>


            <form id="formInserirLivro2" action="testandoListar.jsp" method="post">
                <input type="submit" class="submitInserirLivro" name="acao" value="Quer Gerenciar seus livros cadastrados ?">
            </form>


        </div>

    </div>


</body>
</html>