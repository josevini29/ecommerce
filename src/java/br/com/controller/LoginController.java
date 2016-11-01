package br.com.controller;

import br.com.conexao.Mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class LoginController {

    public static Boolean login(String usuario, String senha) {
        return (usuario != null && senha != null && LoginController.existe(usuario, senha));
    }

    public static Cookie getCookie(String nome, String valor) {
        Cookie cookie = new Cookie(nome, valor);
        return cookie;
    }

    public static String getUsuario(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Connection con = Mysql.getConexaoMySQL();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE upper(usuario) = ? LIMIT 1");
            stmt.setString(1, cookies[1].getValue().toUpperCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static Boolean estaLogado(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        try {
            return cookies[1].getName().equals("usuario") && cookies[2].getName().equals("senha");
        } catch (Exception ex) {
            return false;
        }
    }

    public static Boolean existe(String usuario, String senha) {
        try {
            String usuarioBd = null;
            String senhaBd = null;

            Connection con = Mysql.getConexaoMySQL();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE usuario = ? AND senha = ? LIMIT 1");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usuarioBd = rs.getString("usuario");
                senhaBd = rs.getString("senha");
            }

            Mysql.FecharConexao();

            return (usuario.equals(usuarioBd) && senha.equals(senhaBd));
        } catch (SQLException ex) {
            return false;
        }
    }

    public static Cookie getCookie(String erro, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
