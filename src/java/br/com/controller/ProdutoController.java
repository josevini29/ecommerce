package br.com.controller;

import br.com.conexao.Mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoController {

    private int id;
    private String descricao;
    private String informacao;
    private double valor;
    private double qtde;

    public ResultSet consulta() {
        Connection con = Mysql.getConexaoMySQL();

        PreparedStatement stmt;
        try {
            String SQL;
            if (getNome() != null) {
                SQL = "SELECT * FROM PRODUTOS WHERE UPPER(DESCRICAO) LIKE UPPER('%"+getNome()+"%')";                
            } else {
                SQL =  "SELECT * FROM PRODUTOS";
            }
            stmt = con.prepareStatement(SQL);
            Mysql.FecharConexao();

            return stmt.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void consultaProduto(int id) {
        Connection con = Mysql.getConexaoMySQL();

        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("SELECT * FROM PRODUTOS WHERE ID = " + id);
            Mysql.FecharConexao();

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id");
                this.descricao = rs.getString("descricao");
                this.informacao = rs.getString("informacao");
                this.valor = rs.getDouble("valor");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alterarProduto() {
        Connection con = Mysql.getConexaoMySQL();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("UPDATE PRODUTOS SET DESCRICAO = ?, INFORMACAO = ?, VALOR = ? WHERE ID = " + getId());
            stmt.setString(1, getNome().toUpperCase());
            stmt.setString(2, getDescricao().toUpperCase());
            stmt.setDouble(3, getPreco());
            stmt.executeUpdate();
            Mysql.FecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inserirProduto() {
        Connection con = Mysql.getConexaoMySQL();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("INSERT INTO PRODUTOS (DESCRICAO,INFORMACAO,VALOR) VALUES (?,?,?)");
            stmt.setString(1, getNome().toUpperCase());
            stmt.setString(2, getDescricao().toUpperCase());
            stmt.setDouble(3, getPreco());
            stmt.executeUpdate();
            Mysql.FecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void excluirProduto(int id) {
        Connection con = Mysql.getConexaoMySQL();
        PreparedStatement stmt;
        try {
            stmt = con.prepareStatement("DELETE FROM PRODUTOS WHERE ID = " + id);
            stmt.executeUpdate();
            Mysql.FecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return descricao;
    }

    public void setNome(String nome) {
        this.descricao = nome;
    }

    public double getPreco() {
        return valor;
    }

    public void setPreco(double preco) {
        this.valor = preco;
    }

    public String getDescricao() {
        return informacao;
    }

    public void setDescricao(String descricao) {
        this.informacao = descricao;
    }

    public double getQtde() {
        return qtde;
    }

    public void setQtde(double qtde) {
        this.qtde = qtde;
    }

}
