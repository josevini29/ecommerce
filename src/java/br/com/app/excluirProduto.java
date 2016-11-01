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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jose Vinicius
 */
@WebServlet(name = "excluirProduto", urlPatterns = {"/excluirProduto"})
public class excluirProduto extends HttpServlet {

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

        if (LoginController.estaLogado(request)) {
            String idProduto = request.getParameter("id_produto");
            if (idProduto != null) {
                ProdutoController prod = new ProdutoController();
                prod.excluirProduto(Integer.parseInt(idProduto));
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
