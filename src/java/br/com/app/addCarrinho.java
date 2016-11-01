/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.app;

import br.com.controller.LoginController;
import br.com.controller.ProdutoController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jose Vinicius
 */
public class addCarrinho extends HttpServlet {

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

        //if (LoginController.estaLogado(request)) {
            int id = Integer.parseInt(request.getParameter("id_produto"));
            ProdutoController prod = new ProdutoController();
            prod.consultaProduto(id);
            HttpSession session = request.getSession(true);
            ArrayList<ProdutoController> produtos = new ArrayList<>();
            produtos = (ArrayList<ProdutoController>) session.getAttribute("produtos");
            boolean tem = false;
            if (produtos == null || produtos.size() == 0) {
                produtos = new ArrayList<>();
            } else {
                for (ProdutoController produ : produtos) {
                    if (produ.getId() == id) {
                        produ.setQtde(produ.getQtde() + 1);
                        tem = true; //caso ja tenha na session
                        break;
                    }
                }
            }
            if (!tem) {
                prod.setQtde(1);
                produtos.add(prod);
            }
            session.setAttribute("produtos", produtos);
            response.sendRedirect("/e-commerce/main.jsp");
        /*} else {
            response.sendRedirect("/e-commerce");
        }*/

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
