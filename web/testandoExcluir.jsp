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

        <!--        <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
                <script src="js/jquery.validate.js"type="text/javascript"></script>
                <script src="js/validacaoLivroEXCLUIR.js"type="text/javascript"></script>-->

        <!-- Stylesheets -->
        <link rel="stylesheet" href="css/ExcluirLivro.css" media="all">
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
            #formCadastrarLivro{
                background:url(image/textura-input.jpg);
                background-size:100%;
                opacity:0.9;
                border-radius:30px 30px 30px 30px;
            }
        </style>


    <form action="ControleProduto" method="post">
        <input type="text" class="input2" placeholder="Consultar Livro pelo isbn" required="required" name="txtIsbn">
        <input type="submit" class="submitCadastrarLivroPesquisar" name="acao" value="consultar">
    </form>



</head>

<body id="background-layout">



    <%
        Produto dados = (Produto) request.getAttribute("dadosExcluir");

        if (dados == null) {
            return;
        }
    %>



    <nav id="nav">
        <ul>

            <li><form action="testandoUmaTelaPrincipal.jsp" method="post">
                    <input type="submit" class="submitCadastrarLivro" name="acao" value="principal">
                </form></li>

            <li class="current"><form action="InserirPdf.jsp" method="post">
                    <input type="submit" class="submitCadastrarLivro" name="acao" value="Cadastrar">
                </form></li>
            <li>
            <li><form action="testandoListar.jsp" method="post">
                    <input type="submit" class="submitCadastrarLivro" name="acao" value="Gerenciar Livros">
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

    <!--    <span id="msg"></span>-->


    <div class="section group">
        <div class="col span_1_of_2">



            <form id="formCadastrarLivro" action="ControleProduto" method="POST">


                <br>
                <h2 id="login">Excluir Livro.</h2>
                <br>
                <div id="box-inputs">

                    <table>

                        <tr>
                            <td><input type="text" class="input" value="<%=dados.getIsbn()%>" disabled><label id="TextoConsultarLivro">Isbn</label>   </td> 
                            <td></td>
                        </tr>


                        <tr>
                            <td><input type="hidden"  name="txtIsbn" value="<%=dados.getIsbn()%>"></td>                             
                        </tr>



                        <tr>

                            <td><input type="text" class="input"   name="txtTitulo" value="<%=dados.getTitulo()%>" disabled><label id="TextoConsultarLivro">Titulo</label></td> 
                            <td></td>
                        </tr>

                        <tr>

                            <td><input type="text" class="input"  name="txtAutor" value="<%=dados.getAutor()%>" disabled> <label id="TextoConsultarLivro">Autor</label> </td>   
                            <td></td>
                        </tr>



                        <tr>

                            <td><input type="text" class="input"  name="txtPreco" value="<%=dados.getPreco()%>" disabled><label id="TextoConsultarLivro">Preço</label> </td>   
                            <td></td>
                        </tr>


                        <tr>

                            <td><input type="text" class="input"  name="txtDescricao" value="<%=dados.getDescricao()%>" disabled><label id="TextoConsultarLivro">Descrição</label> </td>   
                            <td></td>
                        </tr>



                        <tr>

                            <td><input type="date" class="input" name="txtLancamento" value="<%=dados.getLancamento()%>" disabled><label id="TextoConsultarLivro">Data de Lançamento</label> </td>   
                            <td></td>
                        </tr>


                        <tr>


                    </table>

                    <table>


                        <label class="submit2"> Você deseja excluir este livro ? </label>
                        <input type="submit" value="Excluir" class="submit" name="acao">
                        <input type="submit" value="Cancelar" class="submit" name="acao">                    
                    </table>

                </div>

            </form>

        </div>

    </div>


</body>
</html>
