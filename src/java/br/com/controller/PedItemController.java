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
public class PedItemController {

    private int idItem;
    private int idPedido;
    private int idProduto;
    private int qtPedido;
    private double vlUnitario;

    public void salvarItemPed() {
        Connection con = Mysql.getConexaoMySQL();

        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("INSERT INTO PEDIDO_ITEM (id_pedido,id_produto,quantidade,vl_unitario) VALUES (?,?,?,?)");
            stmt.setInt(1, getIdPedido());
            stmt.setInt(2, getIdProduto());
            stmt.setInt(3, getQtPedido());
            stmt.setDouble(4, getVlUnitario());
            stmt.executeUpdate();
            Mysql.FecharConexao();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
     public ResultSet consultaItem() {
        Connection con = Mysql.getConexaoMySQL();

        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("SELECT * FROM PEDIDO_ITEM WHERE ID_PEDIDO = " + getIdPedido());
            Mysql.FecharConexao();
            return stmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQtPedido() {
        return qtPedido;
    }

    public void setQtPedido(int qtPedido) {
        this.qtPedido = qtPedido;
    }

    public double getVlUnitario() {
        return vlUnitario;
    }

    public void setVlUnitario(double vlUnitario) {
        this.vlUnitario = vlUnitario;
    }
}
