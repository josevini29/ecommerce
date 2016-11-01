package br.com.app;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.controller.LoginController;

public class ServletLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");
        String tela = request.getParameter("tela");

        if (LoginController.login(usuario, senha)) {
            response.addCookie(LoginController.getCookie("usuario", usuario));
            response.addCookie(LoginController.getCookie("senha", senha));

            switch (tela) {
                case "1":
                    response.sendRedirect("/e-commerce/carrinho.jsp");
                    break;
                case "2":
                    response.sendRedirect("/e-commerce/pedidos.jsp");
                    break;
                case "3":
                    response.sendRedirect("/e-commerce/cadastro.jsp");
                    break;
                default:
                    response.sendRedirect("/e-commerce/main.jsp");
            }

        } else {
            response.sendRedirect("/e-commerce");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
