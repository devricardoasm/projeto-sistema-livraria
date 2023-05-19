/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.LoginDAO;
import modelo.dao.UsuarioDAO;
import modelos.Login;
import modelos.PerfilAcesso;
import modelos.Usuario;
import util.Email;

/**
 *
 * @author 11131103404
 */
public class ControleUsuario extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {

            String acao = request.getParameter("acao");
            if (acao.equals("Logar")) {
                Usuario usuario = new Usuario();
                Login log = new Login();
                log.setLogin(request.getParameter("txtLogin"));
                log.setSenha(request.getParameter("txtSenha"));

                LoginDAO login = new LoginDAO();
                Usuario usuarioAutenticado = login.autenticaUsuario(log);

                if (usuarioAutenticado != null) {
                    HttpSession sessaoUsuario = request.getSession();
                    sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);
                    response.sendRedirect("principal.jsp");
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    String mensagem = "Login ou Senha Incorreto!";
                    request.setAttribute("msg", mensagem);
                    rd.forward(request, response);
                }

            } else if (acao.equals("Sair")) {
                HttpSession sessaoUsuario = request.getSession();
                sessaoUsuario.removeAttribute("usuarioAutenticado");
                response.sendRedirect("index.jsp");

            } else if (acao.equals("Cadastrar")) {
                String email = request.getParameter("txtLogin");
                String senha = request.getParameter("txtSenha");
                String nome = request.getParameter("txtNome");
                String sobrenome = request.getParameter("txtSobrenome");
                String cpf = request.getParameter("txtCpf");
                String telefone = request.getParameter("txtTel");

                PerfilAcesso pacesso = new PerfilAcesso();
                pacesso.setId(2);

                Login log = new Login();
                log.setLogin(email);
                log.setSenha(senha);
                log.setPerfil(pacesso);

                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setSobrenome(sobrenome);
                usuario.setCpf(cpf);
                usuario.setTelefone(telefone);

                UsuarioDAO udao = new UsuarioDAO();
                Usuario verificaSeExisteCpf = udao.verificaSeExisteCpf(usuario);

                if (verificaSeExisteCpf.getCpf() == null) {
                    usuario.setCpf(cpf);
                } else {
                    String mensagem = "Esse CPF Já foi cadastrado anteriormente!";
                    request.setAttribute("msgJaExisteCpf", mensagem);
                    RequestDispatcher rd = request.getRequestDispatcher("cadastrarCliente.jsp");
                    rd.forward(request, response);
                    return;
                }

                LoginDAO ldao = new LoginDAO();

                Login verificaSeExisteEmail = ldao.verificaSeExisteEmail(log);

                if (verificaSeExisteEmail.getLogin() == null) {
                    log.setLogin(email);
                } else {
                    String mensagem = "Esse EMAIL Já foi cadastrado anteriormente!";
                    request.setAttribute("msgJaExisteEmail", mensagem);
                    RequestDispatcher rd = request.getRequestDispatcher("cadastrarCliente.jsp");
                    rd.forward(request, response);
                    return;
                }

                Login id_login = ldao.cadastraLogin(log);
                usuario.setLogin(id_login);
                udao.cadastrarUsuario(usuario);

                //Email notificao = new Email();
                //notificao.EnviarEmail(usuario);

                request.setAttribute("msgCadastrado", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("cadastroSucessoCliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("Excluir")) {
                String login = request.getParameter("txtLogin");
                String senha = request.getParameter("txtSenha");
                Login log = new Login();
                log.setLogin(login);
                log.setSenha(senha);
                UsuarioDAO udao = new UsuarioDAO();
                udao.excluir(log);
                response.sendRedirect("index.jsp");

            } else if (acao.equals("Atualizar")) {

                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");

                String login = request.getParameter("txtLogin");
                String nome = request.getParameter("txtNome");
                String sobrenome = request.getParameter("txtSobrenome");
                String telefone = request.getParameter("txtTel");

                Login log = new Login();
                log.setLogin(login);

                Usuario u = new Usuario();
                u.setId(usuario.getId());
                u.setNome(nome);
                u.setSobrenome(sobrenome);
                u.setTelefone(telefone);
                u.setLogin(log);

                UsuarioDAO uDAO = new UsuarioDAO();
                uDAO.atualizar(u);

                response.sendRedirect("principal.jsp");

            } //  ACESSAR A SESSAO VERIFICAR DADOS DO CLIENTE NA SESSAO E DEVOLVER PRA JSP ..
            else if (acao.equals("DadosClienteAtualizar")) {

                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");
                request.setAttribute("dadosCliente", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("atualizarCliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("DadosClienteAlterarSenha")) {

                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");
                request.setAttribute("dadosCliente", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("alterarSenha.jsp");
                rd.forward(request, response);

            } else if (acao.equals("DadosClienteExcluir")) {

                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");
                request.setAttribute("dadosCliente", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("excluirDadosCliente.jsp");
                rd.forward(request, response);
            } else if (acao.equals("Alterar")) {
                String login = request.getParameter("txtLogin");
                String senha = request.getParameter("txtSenha");
                String senha2 = request.getParameter("txtSenha2");

                if (senha.equals(senha2)) {
                    Login log = new Login();
                    log.setLogin(login);
                    log.setSenha(senha);

                    LoginDAO ldao = new LoginDAO();
                    ldao.alterarSenha(log);

                    response.sendRedirect("principal.jsp");

                } else {
                    String msg = "A senha que você digitou não é compativel";
                    request.setAttribute("invalido", msg);
                    RequestDispatcher rd = request.getRequestDispatcher("alterarSenha.jsp");
                    rd.forward(request, response);

                }
            }

        } catch (Exception erro) {
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("erro", erro);
            rd.forward(request, response);
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
