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
import modelos.Login;
import modelos.Produto;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class UsuarioDAO {

    private static final String INSERT = "INSERT INTO usuario (id_usuario,nome,sobrenome,cpf,telefone) VALUES (?,?,?,?,?)";
    private static final String SELECT_CPF = "select cpf from usuario where cpf =(?)";
    private static final String DELETE = "delete from usuario where login=? AND senha= ? ";
    private static final String UPDATE = "update usuario set nome=(?), sobrenome=(?) , telefone=(?) where id_usuario = (?)";
   
    
    public void atualizar(Usuario usuario) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getSobrenome());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setInt(4, usuario.getId());

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
    }

    public void excluir(Login login) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setString(1, login.getLogin());
            pstmt.setString(2, login.getSenha());
            pstmt.execute();            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
    }

    public void cadastrarUsuario(Usuario usuario) {
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setInt(1, usuario.getLogin().getId());
            pstmt.setString(2, usuario.getNome());
            pstmt.setString(3, usuario.getSobrenome());
            pstmt.setString(4, usuario.getCpf());
            pstmt.setString(5, usuario.getTelefone());
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

    public Usuario verificaSeExisteCpf(Usuario usuario) {
        Usuario cpfExiste = new Usuario();
        try {

            Connection conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_CPF);
            pstmt.setString(1, usuario.getCpf());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cpfExiste.setCpf(rs.getString("cpf"));
            }
            pstmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cpfExiste;
    }
    
    
      

}
