/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.conexao.Mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose Vinicius
 */
public class PedidoController {

    private int idPedido;
    private int idUsuario;

    private int codPedido() {
        Connection con = Mysql.getConexaoMySQL();

        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("SELECT COALESCE(MAX(ID), 0) AS ID_NOVO FROM PEDIDO");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID_NOVO") + 1;
            }
            Mysql.FecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void salvarPedido() {
        Connection con = Mysql.getConexaoMySQL();

        PreparedStatement stmt;
        try {
            int codigo = codPedido();
            setIdPedido(codigo);
            stmt = con.prepareStatement("INSERT INTO PEDIDO (id, id_usuario) VALUES (?, ?)");
            stmt.setInt(1, getIdPedido());
            stmt.setInt(2, getIdUsuario());
            stmt.executeUpdate();
            Mysql.FecharConexao();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public ResultSet consulta() {
        Connection con = Mysql.getConexaoMySQL();

        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("SELECT * FROM PEDIDO WHERE ID_USUARIO = " + getIdUsuario());
            Mysql.FecharConexao();
            return stmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
