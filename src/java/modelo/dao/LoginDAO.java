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
import java.sql.Statement;
import modelos.Login;
import modelos.PerfilAcesso;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class LoginDAO {

    private static final String AUTENTICA_USUARIO = "select u.nome,u.sobrenome,u.cpf,u.telefone,l.login,l.senha,p.descricao,p.id_perfil,l.id_login,u.id_usuario from usuario as u , login as l , perfilacesso as p where p.id_perfil = l.perfil_id and u.id_usuario = l.id_login and l.login = (?) and l.senha = (?)";
    private static final String SELECT_EMAIL = "select login from login where login =(?)";
    private static final String UPDATE_SENHA = "update login set senha=(?) where login = (?)";
    private static final String INSERT = "INSERT INTO login (login,senha,perfil_id) VALUES (?,?,?)";

    public Usuario autenticaUsuario(Login login) {
        Usuario usuarioAutenticado = null;

        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsUsuario = null;
        try {

            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(AUTENTICA_USUARIO);
            pstmt.setString(1, login.getLogin());
            pstmt.setString(2, login.getSenha());

            rsUsuario = pstmt.executeQuery();

            if (rsUsuario.next()) {

                PerfilAcesso pacesso = new PerfilAcesso();
                pacesso.setId(rsUsuario.getInt("id_perfil"));
                pacesso.setDescricao(rsUsuario.getString("descricao"));

                Login log = new Login();
                log.setLogin(rsUsuario.getString("login"));
                log.setSenha(rsUsuario.getString("senha"));
                log.setId(rsUsuario.getInt("id_login"));
                log.setPerfil(pacesso);

                usuarioAutenticado = new Usuario();

                usuarioAutenticado.setNome(rsUsuario.getString("nome"));
                usuarioAutenticado.setSobrenome(rsUsuario.getString("sobrenome"));
                usuarioAutenticado.setCpf(rsUsuario.getString("cpf"));
                usuarioAutenticado.setTelefone(rsUsuario.getString("telefone"));
                usuarioAutenticado.setId(rsUsuario.getInt("id_usuario"));

                usuarioAutenticado.setLogin(log);

            }

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            }
        }

        return usuarioAutenticado;

    }

    public Login verificaSeExisteEmail(Login log) {

        Login loginExiste = new Login();
        try {

            Connection conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_EMAIL);
            pstmt.setString(1, log.getLogin());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                loginExiste.setLogin(rs.getString("login"));
            }
            pstmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return loginExiste;
    }

    public Login cadastraLogin(Login log) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, log.getLogin());
            pstmt.setString(2, log.getSenha());
            pstmt.setInt(3, log.getPerfil().getId());
            pstmt.execute();

            ResultSet id = pstmt.getGeneratedKeys();
            id.next();
            int id_login = id.getInt(1);
            log.setId(id_login);
            return log;

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

    public void alterarSenha(Login login) {
        Connection conexao = null;
        try {
             conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_SENHA);

            pstmt.setString(1, login.getSenha());
            pstmt.setString(2, login.getLogin());

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
    }

}
