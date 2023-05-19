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
import modelos.Produto;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class ProdutoDAO {

    private static final String INSERT = "INSERT INTO produto (titulo, autor, preco,descricao, lancamento, isbn, sinopse,imagem) VALUES (?,?,?,?,?,?,?,?)";
    private static final String INSERT_PDF = "INSERT INTO pdf (nome) VALUES (?)";
    private static final String SELECT_ALL = "SELECT * FROM produto order by id_livro";   
    private static final String SELECT_ALL_ORDERDATA = "SELECT * FROM produto order by lancamento";
    private static final String SELECT_ALL_ORDERPRECO = "SELECT * FROM produto order by preco";
    private static final String SELECT_ALL_PDF = "SELECT nome FROM pdf";
    private static final String DELETE = "delete from produto where isbn=?";
    private static final String UPDATE_PDF = "update produto set pdf=(?) where isbn=(?)";
    private static final String UPDATE = "update produto set titulo=(?), autor=(?) , preco=(?) , lancamento=(?) , sinopse=(?), descricao=(?) where isbn=(?)";
    private static final String SELECT = "select * from produto where isbn =(?)";
    private static final String SELECT_ISBN = "select isbn from produto where isbn =(?)";
    private static final String SELECT_ALL_ISBN = "select * from produto where isbn =(?)";
    private static final String SELECTALL = "select * from produto";
    private static final String SELECTALL_TITULO = "select * from produto order by titulo";
    private static final String SELECTALL_PRECOMAIOR = "select * from produto order by preco ASC";
    private static final String SELECTALL_PRECOMENOR = "select * from produto order by preco DESC";
    private static final String SELECTID = "select * from produto where id_livro = (?)";
    private static final String SELECTSINOPSE = "select * from produto where id_livro = (?)";

    public void cadastrar(Produto produto) {
        Connection conexao = null;
        try {
            
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, produto.getTitulo());
            pstmt.setString(2, produto.getAutor());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setString(4, produto.getDescricao());
            pstmt.setString(5, produto.getLancamento());
            pstmt.setString(6, produto.getIsbn());
            pstmt.setString(7, produto.getSinopse());
            pstmt.setString(8, produto.getImagem());
            pstmt.execute();

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

    public ArrayList<Produto> listar() throws SQLException {

        ArrayList<Produto> listaProduto = new ArrayList<Produto>();

        Connection conexao = ConectaBanco.getConexao();

        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Produto novoProduto = new Produto();
            novoProduto.setId(rs.getInt("id_livro"));
            novoProduto.setTitulo(rs.getString("titulo"));
            novoProduto.setAutor(rs.getString("autor"));
            novoProduto.setPreco(rs.getDouble("preco"));
            novoProduto.setLancamento(rs.getString("lancamento"));
            novoProduto.setIsbn(rs.getString("isbn"));
            novoProduto.setSinopse(rs.getString("sinopse"));
            novoProduto.setPdf(rs.getString("pdf"));

            listaProduto.add(novoProduto);

        }
        return listaProduto;
    }

    public void atualizar(Produto produto) {

        try {
            Connection conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, produto.getTitulo());
            pstmt.setString(2, produto.getAutor());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setString(4, produto.getLancamento());
            pstmt.setString(5, produto.getSinopse());
            pstmt.setString(6, produto.getDescricao());
            pstmt.setString(7, produto.getIsbn());
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println("erro de sql:" + e.getMessage());
        }
    }

    public void excluir(Produto produto) {

        try {
            Connection conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setString(1, produto.getIsbn());
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println("erro de sql:" + e.getMessage());
        }
    }

    public Produto consultar(Produto produto) {
        try {

            Connection conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT);
            pstmt.setString(1, produto.getIsbn());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                produto.setId(rs.getInt("id_livro"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setLancamento(rs.getString("lancamento"));
                produto.setIsbn(rs.getString("isbn"));
                produto.setSinopse(rs.getString("sinopse"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setImagem(rs.getString("imagem"));
                produto.setPdf(rs.getString("pdf"));
            }
            pstmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return produto;
    }

    public Produto SelecionaTodosDadosComIsbn(Produto produto) {
        try {

            Connection conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL_ISBN);
            pstmt.setString(1, produto.getIsbn());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                produto.setId(rs.getInt("id_livro"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setLancamento(rs.getString("lancamento"));
                produto.setIsbn(rs.getString("isbn"));
                produto.setSinopse(rs.getString("sinopse"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setImagem(rs.getString("imagem"));
                produto.setPdf(rs.getString("pdf"));
            } //else {
//                produto.setIsbn("");
//            }
            pstmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return produto;
    }

    public Produto verificaSeExisteIsbn(Produto produto) {
        try {

            Connection conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ISBN);
            pstmt.setString(1, produto.getIsbn());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                produto.setIsbn(rs.getString("isbn"));

            } else {
                produto.setIsbn("");
            }
            pstmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return produto;
    }

    public ArrayList<Produto> listarTitulo() throws SQLException {

        ArrayList<Produto> listaProduto = new ArrayList<Produto>();

        Connection conexao = ConectaBanco.getConexao();

        PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_TITULO);
         
        

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Produto novoProduto = new Produto();
            novoProduto.setId(rs.getInt("id_livro"));
            novoProduto.setTitulo(rs.getString("titulo"));
            novoProduto.setAutor(rs.getString("autor"));
            novoProduto.setPreco(rs.getDouble("preco"));
            novoProduto.setLancamento(rs.getString("lancamento"));
            novoProduto.setIsbn(rs.getString("isbn"));
            novoProduto.setSinopse(rs.getString("sinopse"));
            novoProduto.setImagem(rs.getString("imagem"));
            novoProduto.setPdf(rs.getString("pdf"));

            listaProduto.add(novoProduto);

        }
        return listaProduto;
    }

    public ArrayList<Produto> listarData() throws SQLException {

        ArrayList<Produto> listaProduto = new ArrayList<Produto>();

        Connection conexao = ConectaBanco.getConexao();

        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL_ORDERDATA);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Produto novoProduto = new Produto();
            novoProduto.setId(rs.getInt("id_livro"));
            novoProduto.setTitulo(rs.getString("titulo"));
            novoProduto.setAutor(rs.getString("autor"));
            novoProduto.setPreco(rs.getDouble("preco"));
            novoProduto.setLancamento(rs.getString("lancamento"));
            novoProduto.setIsbn(rs.getString("isbn"));
            novoProduto.setSinopse(rs.getString("sinopse"));
            novoProduto.setImagem(rs.getString("imagem"));
            novoProduto.setPdf(rs.getString("pdf"));

            listaProduto.add(novoProduto);

        }
        return listaProduto;
    }

    public ArrayList<Produto> listarPreco() throws SQLException {

        ArrayList<Produto> listaProduto = new ArrayList<Produto>();

        Connection conexao = ConectaBanco.getConexao();

        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL_ORDERPRECO);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Produto novoProduto = new Produto();
            novoProduto.setId(rs.getInt("id_livro"));
            novoProduto.setTitulo(rs.getString("titulo"));
            novoProduto.setAutor(rs.getString("autor"));
            novoProduto.setPreco(rs.getDouble("preco"));
            novoProduto.setLancamento(rs.getString("lancamento"));
            novoProduto.setIsbn(rs.getString("isbn"));
            novoProduto.setSinopse(rs.getString("sinopse"));
            novoProduto.setImagem(rs.getString("imagem"));
            novoProduto.setPdf(rs.getString("pdf"));

            listaProduto.add(novoProduto);

        }
        return listaProduto;
    }

    public void inserirPDF(Produto produto) {
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT_PDF);

            pstmt.setString(1, produto.getPdf());
            pstmt.execute();

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

    public ArrayList<Produto> listarTodosPdf() throws SQLException {

        ArrayList<Produto> listaProduto = new ArrayList<Produto>();

        Connection conexao = ConectaBanco.getConexao();

        PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL_PDF);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            Produto novoProduto = new Produto();

            novoProduto.setPdf(rs.getString("nome"));

            listaProduto.add(novoProduto);

        }
        return listaProduto;
    }

    public void atualizarPDF(Produto produto) {

        try {
            Connection conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_PDF);

            pstmt.setString(1, produto.getPdf());
            pstmt.setString(2, produto.getIsbn());
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println("erro de sql:" + e.getMessage());
        }
    }

    public ArrayList<Produto> listarLivros() {

        Connection conexao = null;
        ArrayList<Produto> listaProduto = new ArrayList<Produto>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id_livro"));
                prod.setTitulo(rs.getString("titulo"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setImagem(rs.getString("imagem"));

                listaProduto.add(prod);
            }
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

        //retorna a lista
        return listaProduto;
    }

    public ArrayList<Produto> listarLivrosTitulo() {

        Connection conexao = null;
        ArrayList<Produto> listaProduto = new ArrayList<Produto>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_TITULO);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id_livro"));
                prod.setTitulo(rs.getString("titulo"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setImagem(rs.getString("imagem"));

                listaProduto.add(prod);
            }
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

        //retorna a lista
        return listaProduto;
    }

    public ArrayList<Produto> listarLivrosPrecoMaior() {

        Connection conexao = null;
        ArrayList<Produto> listaProduto = new ArrayList<Produto>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_PRECOMAIOR);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id_livro"));
                prod.setTitulo(rs.getString("titulo"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setImagem(rs.getString("imagem"));

                listaProduto.add(prod);
            }
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

        //retorna a lista
        return listaProduto;
    }

    public ArrayList<Produto> listarLivrosPrecoMenor() {

        Connection conexao = null;
        ArrayList<Produto> listaProduto = new ArrayList<Produto>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_PRECOMENOR);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id_livro"));
                prod.setTitulo(rs.getString("titulo"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setImagem(rs.getString("imagem"));

                listaProduto.add(prod);
            }
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

        //retorna a lista
        return listaProduto;
    }

    public Produto consultarPorId(int id) {
        Connection conexao = null;
        Produto produto = new Produto();
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECTID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                produto.setId(rs.getInt("id_livro"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setLancamento(rs.getString("lancamento"));
                produto.setIsbn(rs.getString("isbn"));
                produto.setSinopse(rs.getString("sinopse"));
                produto.setImagem(rs.getString("imagem"));
                produto.setPdf(rs.getString("pdf"));

            }

        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }

        }

        return produto;
    }

    public Produto verSinopse(Produto produto) {
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECTSINOPSE);
            pstmt.setInt(1, produto.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                produto.setSinopse(rs.getString("sinopse"));
                produto.setTitulo(rs.getString("titulo"));

            }

        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }

        }

        return produto;
    }

}
