/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.CarrinhoDeCompra;
import modelos.ItemDeCompra;
import modelos.Login;
import modelos.Pedido;
import modelos.Produto;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class PedidoDAO {

    private static final String SELECT = "SELECT id_pedido FROM pedido";
    //private static final String SELECT_PAGOS = "SELECT id_pedido,status FROM pedido where status = (?) ";    
    private static final String SELECT_PAGOS = "select * from pedido as pe, login as l , usuario as u where pe.status = 'pago' and pe.id_login = l.id_login and l.id_login = u.id_usuario";
    private static final String SELECT_CANCELADO = "select * from pedido as pe, login as l , usuario as u where pe.status = 'pendente' and pe.ate = (?) and pe.id_login = l.id_login and l.id_login = u.id_usuario";
    private static final String SELECT_MEUS_PEDIDOS = "select u.nome,l.login,p.titulo,pe.id_pedido,pe.status from login as l, produto as p , pedido as pe, itens_pedido as i, usuario as u where l.id_login = pe.id_login and p.id_livro = i.id_livro and i.id_pedido = pe.id_pedido and u.id_usuario = l.id_login and l.login = (?)";
    private static final String LIBERAR_CONTEUDO = "update pedido set status='liberado' where id_pedido = (?) ";
    private static final String CANCELAR_CONTEUDO = "update pedido set status='cancelado' where id_pedido = (?) ";
    private static final String INSERT_PEDIDO = "INSERT INTO pedido (id_login,status,de,ate,valor_total) VALUES (?,?,?,?,?)";
    private static final String INSERT_ITENS = "INSERT INTO itens_pedido (id_livro,id_pedido,valor_unitario) VALUES (?,?,?)";
    

    public ArrayList<Pedido> listarPagos() {
        Connection conexao = null;
        try {
            ArrayList<Pedido> listaPago = new ArrayList<Pedido>();

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_PAGOS);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Login login = new Login();
                login.setLogin(rs.getString("login"));

                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setLogin(login);

                Pedido pedidoPago = new Pedido();
                pedidoPago.setId(rs.getInt("id_pedido"));
                pedidoPago.setStatus(rs.getString("status"));
                pedidoPago.setUsuario(usuario);

                listaPago.add(pedidoPago);

            }
            return listaPago;

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

    public void LiberarConteudo(ArrayList<Pedido> pendentes) throws SQLException {
        Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(LIBERAR_CONTEUDO);

            for (Pedido p : pendentes) {

                pstmt.setInt(1, p.getId());
                pstmt.execute();
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
    }

    public ArrayList<Pedido> listarCancelados(Date hoje) {
        Connection conexao = null;

        try {
            ArrayList<Pedido> listaCancelados = new ArrayList<Pedido>();

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_CANCELADO);
            pstmt.setDate(1, hoje);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Login login = new Login();
                login.setLogin(rs.getString("login"));

                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setLogin(login);

                Pedido pedidoCancelado = new Pedido();
                pedidoCancelado.setId(rs.getInt("id_pedido"));
                pedidoCancelado.setStatus(rs.getString("status"));
                pedidoCancelado.setUsuario(usuario);

                listaCancelados.add(pedidoCancelado);

            }
            return listaCancelados;

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

    public void CancelarConteudo(ArrayList<Pedido> pedidosCancelar) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(CANCELAR_CONTEUDO);

            for (Pedido p : pedidosCancelar) {

                pstmt.setInt(1, p.getId());
                pstmt.execute();
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
    }

//    public int DecobrirUltimoId() {
//        int x = 0;
//        try {
//            Connection conexao = ConectaBanco.getConexao();
//            PreparedStatement pstmt = conexao.prepareStatement(SELECT);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                x = Integer.parseInt(rs.getString("id_pedido"));
//            }
//        } catch (SQLException ex) {
//
//        }
//        return x ;
//    }
    public Pedido CriarPedido(Pedido pedido) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, pedido.getUsuario().getId());
            pstmt.setString(2, "pendente");
            pstmt.setDate(3, (Date) pedido.getDe());
            pstmt.setDate(4, (Date) pedido.getAte());
            pstmt.setDouble(5, pedido.getCarrinho().getTotal());
            pstmt.execute();

            ResultSet id = pstmt.getGeneratedKeys();
            id.next();
            int id_pedido = id.getInt(1);
            pedido.setId(id_pedido);
            return pedido;

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

    public void CriarItensCompra(Pedido pedido) {
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT_ITENS);

            for (ItemDeCompra item : pedido.getCarrinho().getItens()) {
                pstmt.setInt(1, item.getProduto().getId());
                pstmt.setInt(2, pedido.getId());
                pstmt.setDouble(3, item.getTotal());
                pstmt.execute();
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

    }

    public ArrayList<Pedido> MeusPedidos(Pedido pedido) {
        Connection conexao = null;
        try {
            ArrayList<Pedido> meusPedidos = new ArrayList<Pedido>();

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_MEUS_PEDIDOS);
            pstmt.setString(1, pedido.getUsuario().getLogin().getLogin());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();
                produto.setTitulo(rs.getString("titulo"));

                ItemDeCompra item = new ItemDeCompra();
                item.setProduto(produto);

                CarrinhoDeCompra carrinho = new CarrinhoDeCompra();
                carrinho.addNovoItem(item);

                Pedido meuPedido = new Pedido();
                meuPedido.setId(rs.getInt("id_pedido"));
                meuPedido.setStatus(rs.getString("status"));
                meuPedido.setCarrinho(carrinho);
                meusPedidos.add(meuPedido);

            }
            return meusPedidos;

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
