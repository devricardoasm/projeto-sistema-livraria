/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author 11131103404
 */
public class CarrinhoDeCompra {

    private Integer id;
    private List<ItemDeCompra> itens;
    private double total;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
      

    public void addNovoItem(ItemDeCompra item) {
        if (this.itens == null) {
            this.itens = new ArrayList<ItemDeCompra>();
        }

        this.itens.add(item);
    }

    public void removerItem(ItemDeCompra itemRemove) {
        for (Iterator i = itens.iterator(); i.hasNext();) {
            ItemDeCompra item = (ItemDeCompra) i.next();
            if (item.getProduto().getId() == itemRemove.getProduto().getId()) {
                i.remove();
            }
        }
    }

    public double calculaTotal() {
        double vtotal = 0;
        for (ItemDeCompra item : this.itens) {
            vtotal += item.getTotal();
        }
        this.total = vtotal;
        return total;
    }

    public Integer getId() {
        return id;
    }

    public void setItens(List<ItemDeCompra> itens) {
        this.itens = itens;
    }

    
    public List<ItemDeCompra> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

}
