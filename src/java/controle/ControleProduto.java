/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.MinhasComprasDAO;
import modelo.dao.ProdutoDAO;
import modelos.Produto;
import modelos.Usuario;
import modelos.MinhasCompras;

/**
 *
 * @author 11131103404
 */
public class ControleProduto extends HttpServlet {

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
            String cars = request.getParameter("cars");

            if (acao.equals("Cadastrar")) {

                String titulo = request.getParameter("txtTitulo");
                String autor = request.getParameter("txtAutor");
                Double preco = Double.parseDouble(request.getParameter("txtPreco"));
                String descricao = request.getParameter("txtDescricao");
                String lancamento = request.getParameter("txtLancamento");
                String isbn = request.getParameter("txtIsbn");
                String sinopse = request.getParameter("txtSinopse");
                String imagem = request.getParameter("txt_foto");

                Produto produto = new Produto();
                produto.setTitulo(titulo);
                produto.setAutor(autor);
                produto.setPreco(preco);
                produto.setDescricao(descricao);
                produto.setLancamento(lancamento);
                produto.setIsbn(isbn);
                produto.setSinopse(sinopse);
                produto.setImagem(imagem);

                ProdutoDAO pdao = new ProdutoDAO();

                pdao.verificaSeExisteIsbn(produto);

                if (isbn.equals(produto.getIsbn())) {

                    String mensagem = "Esse Isbn já foi cadastrado !!!";
                    request.setAttribute("msgJaExisteIsbn", mensagem);
                    RequestDispatcher rd = request.getRequestDispatcher("testandoUmaTelaPrincipal.jsp");
                    rd.forward(request, response);
                } else {

                    produto.setIsbn(isbn);
                    pdao.cadastrar(produto);

                    request.setAttribute("listaDePendencia", produto);
                    RequestDispatcher rd = request.getRequestDispatcher("ListarTodosPdf.jsp");
                    rd.forward(request, response);
                }

            } else if (acao.equals("Deletar")) {

                String isbn = request.getParameter("isbn");
                Produto produto = new Produto();
                produto.setIsbn(isbn);
                ProdutoDAO pdao = new ProdutoDAO();
                pdao.SelecionaTodosDadosComIsbn(produto);

                request.setAttribute("dadosExcluir", produto);
                RequestDispatcher rd = request.getRequestDispatcher("testandoExcluir.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Listar")) {

                ProdutoDAO pDAO = new ProdutoDAO();
                String opcao = cars;

                if (opcao == null) {
                    ArrayList<Produto> produtos = pDAO.listar();
                    request.setAttribute("listaProduto", produtos);
                    RequestDispatcher rd = request.getRequestDispatcher("testandoListar.jsp");
                    rd.forward(request, response);

                } else if (opcao.equals("titulo")) {
                    ArrayList<Produto> produtos = pDAO.listarTitulo();
                    request.setAttribute("listaProduto", produtos);
                    RequestDispatcher rd = request.getRequestDispatcher("testandoListar.jsp");
                    rd.forward(request, response);
                } else if (opcao.equals("data")) {
                    ArrayList<Produto> produtos = pDAO.listarData();
                    request.setAttribute("listaProduto", produtos);
                    RequestDispatcher rd = request.getRequestDispatcher("testandoListar.jsp");
                    rd.forward(request, response);
                } else if (opcao.equals("preco")) {
                    ArrayList<Produto> produtos = pDAO.listarPreco();
                    request.setAttribute("listaProduto", produtos);
                    RequestDispatcher rd = request.getRequestDispatcher("testandoListar.jsp");
                    rd.forward(request, response);
                }

            } else if (acao.equals("Atualizar")) {

                String titulo = request.getParameter("txtTitulo");
                String autor = request.getParameter("txtAutor");
                Double preco = Double.parseDouble(request.getParameter("txtPreco"));
                String descricao = request.getParameter("txtDescricao");
                String lancamento = request.getParameter("txtLancamento");
                String isbn = request.getParameter("txtIsbn");
                String sinopse = request.getParameter("txtSinopse");

                Produto produto = new Produto();
                produto.setTitulo(titulo);
                produto.setAutor(autor);
                produto.setPreco(preco);
                produto.setLancamento(lancamento);
                produto.setIsbn(isbn);
                produto.setSinopse(sinopse);
                produto.setDescricao(descricao);

                ProdutoDAO pDAO = new ProdutoDAO();

                pDAO.atualizar(produto);

                response.sendRedirect("testandoUmaTelaPrincipal.jsp");

            } else if (acao.equals("consultar")) {

                String isbn = request.getParameter("txtIsbn");

                Produto produto = new Produto();
                produto.setIsbn(isbn);

                ProdutoDAO pdao = new ProdutoDAO();

                pdao.verificaSeExisteIsbn(produto);

                if (produto.getIsbn() == "") {

                    String mensagem = "O livro que você consultou não existe !!";
                    request.setAttribute("msgNaoExisteIsbn", mensagem);
                    RequestDispatcher rd = request.getRequestDispatcher("testandoListar.jsp");
                    rd.forward(request, response);

                } else {

                    pdao.consultar(produto);

                    request.setAttribute("Consulta", produto);
                    RequestDispatcher rd = request.getRequestDispatcher("testandoResultadoPesquisa.jsp");
                    rd.forward(request, response);

                }

            } else if (acao.equals("Excluir")) {
                String isbn = request.getParameter("txtIsbn");
                Produto produto = new Produto();
                produto.setIsbn(isbn);
                ProdutoDAO pdao = new ProdutoDAO();
                pdao.excluir(produto);

                String mensagemm = "Excluido com sucesso!!";
                request.setAttribute("msgExcluido", mensagemm);
                RequestDispatcher rd = request.getRequestDispatcher("testandoUmaTelaPrincipal.jsp");
                rd.forward(request, response);

            } else if (acao.equals("AtualizarLivro")) {

                String isbn = request.getParameter("isbn");
                Produto produto = new Produto();
                produto.setIsbn(isbn);
                ProdutoDAO pdao = new ProdutoDAO();
                pdao.SelecionaTodosDadosComIsbn(produto);

                request.setAttribute("dadosAtualizar", produto);
                RequestDispatcher rd = request.getRequestDispatcher("testandoAtualizar.jsp");
                rd.forward(request, response);

            } else if (acao.equals("VerMeusLirvos")) {

                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");

                MinhasComprasDAO minhasCompras = new MinhasComprasDAO();

                ArrayList<MinhasCompras> p = minhasCompras.listarMeusLivros(usuario);

                request.setAttribute("listaProduto", p);
                RequestDispatcher rd = request.getRequestDispatcher("MeusLivros.jsp");
                rd.forward(request, response);

            } else if (acao.equals("AdicionarPdf")) {

                String isbn = (request.getParameter("isbn"));

                Produto p = new Produto();

                p.setIsbn(isbn);

                request.setAttribute("listaDePendencia", p);
                RequestDispatcher rd = request.getRequestDispatcher("ListarTodosPdf.jsp");
                rd.forward(request, response);

            } else if (acao.equals("ListarTodosPdf")) {

                ProdutoDAO pdao = new ProdutoDAO();

                ArrayList<Produto> produto = pdao.listarTodosPdf();
                request.setAttribute("listaProduto", produto);
                RequestDispatcher rd = request.getRequestDispatcher("ListarTodosPdf.jsp");
                rd.forward(request, response);

            } else if (acao.equals("AtualizarPdf")) {
                String isbn = (request.getParameter("isbn"));
                String pdf = (request.getParameter("nome"));

                Produto p = new Produto();

                p.setIsbn(isbn);
                p.setPdf(pdf);

                ProdutoDAO pdao = new ProdutoDAO();

                pdao.atualizarPDF(p);

                String msg = "Inserido pdf com sucesso!!";
                request.setAttribute("msgPdfComSucesso", msg);
                RequestDispatcher rd = request.getRequestDispatcher("testandoListar.jsp");
                rd.forward(request, response);

            }

        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
