/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.CarrinhoDeCompra;
import modelos.ItemDeCompra;
import modelos.Pedido;
import modelos.Produto;
import modelos.RelatorioDeVendas;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class RelatorioDeVendasDAO {

    private static final String RELATORIO = "select * from login as l, produto as p , pedido as pe, itens_pedido as i, usuario as u where l.id_login = pe.id_login and p.id_livro = i.id_livro and i.id_pedido = pe.id_pedido and u.id_usuario = l.id_login and pe.status = 'pago'";
    private static final String SELECT_TOTAL = "Select SUM(valor_unitario) from itens_pedido";

    public ArrayList<RelatorioDeVendas> listarRelatorio() throws SQLException {

        ArrayList<RelatorioDeVendas> listaProduto = new ArrayList<RelatorioDeVendas>();

        Connection conexao = ConectaBanco.getConexao();

        double valorTotalDeVendas = 0;

        PreparedStatement pstmt = conexao.prepareStatement(RELATORIO);
        ResultSet rs = pstmt.executeQuery();

        PreparedStatement pstmtt = conexao.prepareStatement(SELECT_TOTAL);
        ResultSet rss = pstmtt.executeQuery();

        while (rss.next()) {

            valorTotalDeVendas = (rss.getDouble("sum"));

        }

        while (rs.next()) {

            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("nome"));

            Produto produto = new Produto();
            produto.setTitulo(rs.getString("titulo"));
            produto.setId(rs.getInt("id_livro"));

            ItemDeCompra item = new ItemDeCompra();
            item.setId(rs.getInt("id_itens"));
            item.setTotal_unitario(rs.getDouble("valor_unitario"));
            item.setProduto(produto);

            CarrinhoDeCompra carrinho = new CarrinhoDeCompra();
            carrinho.setTotal(rs.getDouble("valor_total"));
            carrinho.addNovoItem(item);

            Pedido pedido = new Pedido();
            pedido.setId(rs.getInt("id_pedido"));
            pedido.setDe(rs.getDate("de"));
            pedido.setCarrinho(carrinho);
            pedido.setUsuario(usuario);

            RelatorioDeVendas relatorio = new RelatorioDeVendas();
            relatorio.setTotal_vendas(valorTotalDeVendas);
            relatorio.setPedido(pedido);

            listaProduto.add(relatorio);

        }

        return listaProduto;
    }

//     public double calcularTotal() {
//        Connection conexao = null;
//
//        try {
//
//            double valor = 0;
//            conexao = ConectaBanco.getConexao();
//
//            PreparedStatement pstmt = conexao.prepareStatement(SELECT_TOTAL);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//
//                valor = (rs.getDouble("sum"));
//
//            }
//
//            return valor;
//
//        } catch (SQLException ex1) {
//            throw new RuntimeException(ex1);
//        } finally {
//            try {
//                if (conexao != null) {
//                    conexao.close();
//                }
//            } catch (SQLException ex1) {
//                throw new RuntimeException(ex1);
//            }
//
//        }
//    }
}
