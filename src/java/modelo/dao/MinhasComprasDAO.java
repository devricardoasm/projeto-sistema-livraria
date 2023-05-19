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
import modelos.MinhasCompras;
import modelos.Pedido;
import modelos.Produto;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class MinhasComprasDAO {

    private static final String INSERT = "insert into compra(id_pedido) values (?)";
    private static final String SELECT_MEUS_LIVROS = "select * from login as l, produto as p , pedido as pe, itens_pedido as i where l.id_login = pe.id_login and p.id_livro = i.id_livro and i.id_pedido = pe.id_pedido and l.login = (?) and pe.status = (?)";

    public void compra(ArrayList<Pedido> pedido) {
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            for (Pedido p : pedido) {

                pstmt.setInt(1, p.getId());
                pstmt.execute();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
    }

    public ArrayList<MinhasCompras> listarMeusLivros(Usuario usuario) {
        Connection conexao = null;
        try {

            ArrayList<MinhasCompras> listaProduto = new ArrayList<MinhasCompras>();

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_MEUS_LIVROS);
            pstmt.setString(1, usuario.getLogin().getLogin());
            pstmt.setString(2, "liberado");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setIsbn(rs.getString("isbn"));
                produto.setPdf(rs.getString("pdf"));
                produto.setImagem(rs.getString("imagem"));

                MinhasCompras compras = new MinhasCompras();
                compras.setStatus(rs.getString("status"));
                compras.setProduto(produto);

                listaProduto.add(compras);
            }
            
            return listaProduto;

        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex1) {
                throw new RuntimeException(ex1);
            }

        }
    }

}
