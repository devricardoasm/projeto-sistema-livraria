<%-- 
    Document   : MeusLivros
    Created on : 23/04/2016, 00:33:29
    Author     : guilherme
--%>

<%@page import="modelos.MinhasCompras"%>
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

        <!-- Stylesheets -->
        <link rel="stylesheet" href="css/MeusLivros.css" media="all">
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


/*            table#t01 tr:nth-child(even) {
                background-color:#c5b8fc;

            }*/
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





    </head>

    <body id="background-layout">




        <%

            ArrayList<MinhasCompras> listaDePendencia = (ArrayList<MinhasCompras>) request.getAttribute("listaProduto");
            if (listaDePendencia == null) {

                request.getRequestDispatcher("/ControleProduto?acao=VerMeusLirvos").forward(request, response);

            }

            if (listaDePendencia.size() == 0) {
        %> 


        <text id="cabecalho2">Você ainda não possui nenhum Livro. </text><br>

        <a
            href="principal.jsp">
            <button type="button" id="opcoes5">Voltar</button>



            <% return;
            }%>





            <!--- Sistema de Grid, não alterar --->

            <div class="section group">
                <div class="col span_1_of_2">



                </div>
                <div class="col span_1_of_2">


                </div>
            </div>



            <div class="section group">
                <div class="col span_1_of_2">



                    <form id="formCadastrarLivro" action="ControleProduto" method="POST">


                        <div id="box-inputs">
                            <caption> <h1 id="cabecalho">Meus Livros</h1> </caption>
                            <table border="1" width="1010px" class="borda" id="t01">

                                <thead> 


                                    <tr>
                                        <th width="120px"> </th>
                                        <th width="160px">Titulo</th>
                                        <th width="180px">Autor</th>
                                        <th width="120px">isbn</th>
                                        <th width="120px">Status</th>
                                        <th width="120px"></th>                                    

                                    </tr>
                                </thead>


                                <%
                                    for (MinhasCompras p : listaDePendencia) {

                                %>
                                <tr>
                                    <td>
                                        <div class="container">
                                            <img src="imagens/<%=p.getProduto().getImagem()%>" alt="Imagem do produto<%=p.getProduto().getImagem()%>"/>  </div> </td>
                                    <td> <%= p.getProduto().getTitulo()%> </td>
                                    <td> <%= p.getProduto().getAutor()%> </td>                  
                                    <td> <%= p.getProduto().getIsbn()%> </td> 
                                    <td> <%= p.getStatus()%> </td>                                                                 

                                    <td width="200px"> <a
                                            href="pdf/<%=p.getProduto().getPdf()%>">
                                            <button type="button" id="opcoes4">Exibir conteudo</button></td>

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


