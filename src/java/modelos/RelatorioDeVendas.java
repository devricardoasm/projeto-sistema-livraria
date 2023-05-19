/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author 11131103404
 */
public class RelatorioDeVendas {
    
    private Pedido pedido;
    private double total_vendas;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public double getTotal_vendas() {
        return total_vendas;
    }

    public void setTotal_vendas(double total_vendas) {
        this.total_vendas = total_vendas;
    }
    
    
    
}
