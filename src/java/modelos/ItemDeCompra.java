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
public class ItemDeCompra {

    private Integer id;
    private Produto produto;
    private Integer quantidade;
    private double total;
    private double total_unitario;
    
    

    public double getTotal_unitario() {
        return total_unitario;
    }

    public void setTotal_unitario(double total_unitario) {
        this.total_unitario = total_unitario;
    }
    

    public void setTotal(double total) {
        this.total = total;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {

        this.total = this.quantidade * this.produto.getPreco();
        return total;

    }

}
