/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.PedidoDAO;
import modelo.dao.ProdutoDAO;
import modelos.CarrinhoDeCompra;
import modelos.ItemDeCompra;
import modelos.Pedido;
import modelos.Produto;
import modelos.Usuario;
import util.Email;

/**
 *
 * @author 11131103404
 */
public class ControleCarrinho extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            String acao = request.getParameter("acao");
            String escolha = request.getParameter("escolha");

            if (acao.equals("addProduto")) {
                //recupera o id do produto que deve ser add no carrinho
                int idProduto = Integer.parseInt(request.getParameter("idProduto"));

                boolean existe = false;

                //recupera a sessão pertencente ao request
                HttpSession sessao = request.getSession();

                //recupera um carrinho de produtos da sessão
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");

                //se não exite um carrinho na sessão o valor será igual a null
                if (carrinho == null) {
                    //cria um carrinho 
                    carrinho = new CarrinhoDeCompra();
                    sessao.setAttribute("carrinho", carrinho);
                }

                //verifica se o produto existe no carrinho
                if (carrinho.getItens() != null) {
                    for (ItemDeCompra item : carrinho.getItens()) {
                        if (item.getProduto().getId() == idProduto) {

                            RequestDispatcher rd = request.getRequestDispatcher("/PaginaDeLivros.jsp");
                            String mensagem = "Este livro já existe em seu carrinho";
                            request.setAttribute("msgexiste", mensagem);
                            rd.forward(request, response);

                            existe = true;
                        }
                    }
                }

                //se não existe o item ou produto, cria um novo 
                if (existe == false) {
                    //encontra o produto no banco
                    ProdutoDAO pdao = new ProdutoDAO();
                    Produto produto = pdao.consultarPorId(idProduto);

                    //cria um novo item
                    ItemDeCompra novoItem = new ItemDeCompra();
                    novoItem.setProduto(produto);
                    novoItem.setQuantidade(1);
                    //adiciona novo item
                    carrinho.addNovoItem(novoItem);

                }
                //redireciona para o carrinho
                request.getRequestDispatcher("/CarrinhoDeCompras.jsp").forward(request, response);

            }//fim addProduto
            else if (acao.equals("cancelaCompra")) {
                //recupera a sessão pertencente ao request
                HttpSession sessao = request.getSession();

                //remove o carrinho da sessão
                sessao.removeAttribute("carrinho");

                //redireciona para pagina principal
                response.sendRedirect("PaginaDeLivros.jsp");

            } else if (acao.equals("removeProduto")) {
                //recupera a sessão pertencente ao request
                HttpSession sessao = request.getSession();
                //recupera um carrinho de produtos da sessão
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");
                //recupera o id do produto
                int idProduto = Integer.parseInt(request.getParameter("idProduto"));
                ItemDeCompra itemRemove = new ItemDeCompra();
                Produto prodRemove = new Produto();
                prodRemove.setId(idProduto);
                itemRemove.setProduto(prodRemove);
                carrinho.removerItem(itemRemove);

                
                //Não muda de pagina e continua no carrinho
                response.sendRedirect("CarrinhoDeCompras.jsp");

            } else if (acao.equals("listar")) {
                String opcao = escolha;

                if (opcao == null) {
                    ArrayList<Produto> produtos = new ProdutoDAO().listarLivros();
                    request.setAttribute("produtos", produtos);
                    request.getRequestDispatcher("/PaginaDeLivros.jsp").forward(request, response);
                } else if (opcao.equals("titulo")) {
                    ArrayList<Produto> produtos = new ProdutoDAO().listarLivrosTitulo();
                    request.setAttribute("produtos", produtos);
                    request.getRequestDispatcher("/PaginaDeLivros.jsp").forward(request, response);
                } else if (opcao.equals("precoMaior")) {
                    ArrayList<Produto> produtos = new ProdutoDAO().listarLivrosPrecoMaior();
                    request.setAttribute("produtos", produtos);
                    request.getRequestDispatcher("/PaginaDeLivros.jsp").forward(request, response);
                } else if (opcao.equals("precoMenor")) {
                    ArrayList<Produto> produtos = new ProdutoDAO().listarLivrosPrecoMenor();
                    request.setAttribute("produtos", produtos);
                    request.getRequestDispatcher("/PaginaDeLivros.jsp").forward(request, response);
                }

            } else if (acao.equals("vercarrinho")) {

                //recupera a sessão pertencente ao request
                HttpSession sessao = request.getSession();

                //recupera um carrinho de produtos da sessão
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");

                //se não exite um carrinho na sessão o valor será igual a null
                if (carrinho == null) {
                    //Não tem nada no carrinho
                    RequestDispatcher rd = request.getRequestDispatcher("/PaginaDeLivros.jsp");
                    String mensagemSemCarrinho = "Não há nada em seu carrinho!!";
                    request.setAttribute("msgg", mensagemSemCarrinho);
                    rd.forward(request, response);

                }

                request.getRequestDispatcher("/CarrinhoDeCompras.jsp").forward(request, response);

            } else if (acao.equals("finalizarCompra")) {

                //recupera USUARIO da sessao atual.
                HttpSession sessaoUsuario = request.getSession();
                Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

                //recupera o carrinho da sessão atual.
                HttpSession sessao = request.getSession();
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");

                //caso nao tenha nenhum usuario na sessão e direcionado para pagina de login.
                if (usuario == null) {
                    response.sendRedirect("FacaLogin.jsp");
                } else {

                    //pega a data de hoje (sistema) joga na variavel "de"
                    GregorianCalendar data_hoje = new GregorianCalendar();
                    data_hoje.getTime();
                    java.sql.Date de = new java.sql.Date(data_hoje.getTime().getTime());
                    
                    //pega a data de hoje(sistema) + 5 dias a frente, joga na variavel "ate"
                    GregorianCalendar hoje_5 = new GregorianCalendar();                     
                    hoje_5.add(hoje_5.DAY_OF_MONTH, 5);                    
                    java.sql.Date ate = new java.sql.Date(hoje_5.getTime().getTime());

                    //joga dentro da classe pedido as datas,usuario da sessao e valor total.
                    Pedido pedido = new Pedido();
                    pedido.setDe(de);
                    pedido.setAte(ate);
                    pedido.setUsuario(usuario);                    
                    pedido.setCarrinho(carrinho);

                    PedidoDAO pedidodao = new PedidoDAO();
                    //Dentro da classe pedido agora tem : Um usuario,data da compra & vencimento,e valor total da compra
                    //dentro da variavel id_pedido tem o resultado do ULTIMO id_pedido criado                   
                    pedidodao.CriarPedido(pedido);
                    //criar ITENS do pedido passa os itens que ta no carrinho & o id do pedido criado acima.                    
                    pedidodao.CriarItensCompra(pedido);
                    
                    //envia uma notificação que foi realizado um pedido com sucesso.
                    //Email email = new Email();
                    //email.EnviarNotificacaoPedidoRealizado(pedido);
                    
                    //direciona para pagina que a compra foi realizada com sucesso.                    
                    response.sendRedirect("CompraRealizadaComSucesso.jsp");
                    
                }

            } else if (acao.equals("verSinopse")) {
                
                int idProduto = Integer.parseInt(request.getParameter("idProduto"));

                Produto produto = new Produto();

                produto.setId(idProduto);

                ProdutoDAO pdao = new ProdutoDAO();

                Produto sinopse = pdao.verSinopse(produto);

                RequestDispatcher rd = request.getRequestDispatcher("/PaginaDeLivros.jsp");
                request.setAttribute("sinopse", sinopse);
                rd.forward(request, response);

            }else if (acao.equals("MeusPedidos")) {
               
                //recupera USUARIO da sessao atual.
                HttpSession sessaoUsuario = request.getSession();
                Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
                
                Pedido pedido = new Pedido();
                pedido.setUsuario(usuario);
                
                PedidoDAO pedidodao = new PedidoDAO();
                
                ArrayList<Pedido> resultado = pedidodao.MeusPedidos(pedido);
                
                RequestDispatcher rd = request.getRequestDispatcher("MeusPedidos.jsp");
                request.setAttribute("meusPedidos", resultado);
                rd.forward(request, response);
                
                
            }

        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
