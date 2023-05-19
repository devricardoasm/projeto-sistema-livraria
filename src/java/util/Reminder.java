/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import modelo.dao.MinhasComprasDAO;
import modelo.dao.PedidoDAO;
import modelos.Pedido;

/**
 *
 * @author 11131103404
 */
public class Reminder {

    Timer timer;

    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    class RemindTask extends TimerTask {

        public void run() {

            new Reminder(60);
            try {
                verificacaoPGTO();
            } catch (SQLException ex) {

            }

        }
    }

    public static void main(String args[]) {
        new Reminder(5);
    }

    public void verificacaoPGTO() throws SQLException {
        
        //Recupera a data de HOJE
        GregorianCalendar gc = new GregorianCalendar();
        java.sql.Date hoje = new java.sql.Date(gc.getTime().getTime());

        PedidoDAO pendenciadao = new PedidoDAO();
        
        //Recupera no banco TODOS os usuarios que efetuou pagamento
        //na variavel "resultadoDosPagantes" é um array com todos usuarios que pagaram e nele CONTEM login,status e ID
        //No liberarConteudo passa como parametro o resultado como dito acima para que possa mudar o status de pedente para liberado
        ArrayList<Pedido> resultadoDosPagantes = pendenciadao.listarPagos();
        pendenciadao.LiberarConteudo(resultadoDosPagantes);

        //Adiciona na tabela COMPRA os cliente que efetuaram pagamento e o livro estiver como liberado
        MinhasComprasDAO minhascompras = new MinhasComprasDAO();
        minhascompras.compra(resultadoDosPagantes);
        
        
        //Recupera uma lista de cliente que no qual nao efetuaram pagamento em 5 dias
        //Na variavel "resultadoCancelados" contem a lista.
        //é cancelado todos o usuarios no quais nao pagaram em 5 dias e o status ainda estiver pendente
        ArrayList<Pedido> resultadoCancelados = pendenciadao.listarCancelados(hoje);
        pendenciadao.CancelarConteudo(resultadoCancelados);
        
        
        // LISTA DE REPETIÇÃO PARA ENVIAR UMA NOTIFICAÇÃO AOS USUARIOS QUE EFETUARAM PAGAMENTO
         for (Pedido pagos : resultadoDosPagantes) {

            //Email pedidoPago = new Email();
           // pedidoPago.EnviarNotificacaoPedidoPago(pagos);

        } 
         
         
         //LISTA DE REPETIÇÃO PARA ENVIAR UMA NOTIFICAÇÃO AOS USUARIOS QUE EFETUARAM A COMPRA FOI CANCELADA
        for (Pedido cancelados : resultadoCancelados) {

            //Email pedidoCancelado = new Email();
            //pedidoCancelado.EnviarNotificacaoPedidoCancelado(cancelados);

        }
        
        System.out.println("teste");
        
    }

}
