<%-- 
    Document   : MeusPedidos
    Created on : 20/05/2016, 16:06:30
    Author     : 11131103404
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

            ArrayList<Pedido> pedido = (ArrayList<Pedido>) request.getAttribute("meusPedidos");

        %>
        <table border="1" width="1010px" align="center">

            <thead> 


                <tr>
                    <th width="150px" align="center">Numero do pedido</th>
                    <th width="150px" align="center">Item / Titulo </th>
                    <th width="280px" align="center">Status do pedido</th> 
                </tr>
            </thead>



            <%             for (Pedido p : pedido) {

            %>
            <tr>                
               

                <td align="center"> <%= p.getId()%> </td>
                <td align="center"> <%= p.getCarrinho().getItens().get(0).getProduto().getTitulo()%> </td>
                <%

                    if (p.getStatus().equals("pendente")) {
                %>    

                <td align="center">Aguardando pagamento</td>

                <%} else if (p.getStatus().equals("pago")) {%>

                <td align="center">Pagamento Confirmado / aguardando liberação</td>

                <%} else if (p.getStatus().equals("liberado")) { %>

                <td align="center">Livro Liberado</td>  

                <%} else if (p.getStatus().equals("cancelado")) { %>

                <td align="center">Pedido Cancelado</td>

                <% }%>
                </td>



            </tr>


            <%
                }
            %>

        </table>
    </body>
</html>
