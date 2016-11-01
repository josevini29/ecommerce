/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.app;

import br.com.controller.LoginController;
import br.com.controller.ProdutoController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jose Vinicius
 */
public class alterarProduto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (LoginController.estaLogado(request)) {
            String idProduto = request.getParameter("id_produto");
            String nmProduto = request.getParameter("nm_produto");
            String dsProduto = request.getParameter("ds_produto");
            String vlProduto = request.getParameter("vl_produto");
            ProdutoController prod = new ProdutoController();

            if (idProduto == null || idProduto.equals("")) {
                prod.setNome(nmProduto);
                prod.setDescricao(dsProduto);
                prod.setPreco(Double.parseDouble(vlProduto));
                prod.inserirProduto();
            } else {
                prod.setId(Integer.parseInt(idProduto));
                prod.setNome(nmProduto);
                prod.setDescricao(dsProduto);
                prod.setPreco(Double.parseDouble(vlProduto));
                prod.alterarProduto();
            }
            response.sendRedirect("/e-commerce/cadastro.jsp");

        } else {
            response.sendRedirect("/e-commerce/index.jsp?tela=3");
        }
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
