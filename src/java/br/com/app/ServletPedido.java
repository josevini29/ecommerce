package br.com.app;

import br.com.controller.LoginController;
import br.com.controller.PedItemController;
import br.com.controller.PedidoController;
import br.com.controller.ProdutoController;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletPedido extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (LoginController.estaLogado(request)) {
            String user = LoginController.getUsuario(request);
            PedidoController ped = new PedidoController();
            ped.setIdUsuario(Integer.parseInt(user));
            ped.salvarPedido();

            HttpSession session = request.getSession();
            ArrayList<ProdutoController> produtos = new ArrayList<>();
            produtos = (ArrayList<ProdutoController>) session.getAttribute("produtos");
            for (ProdutoController produ : produtos) {
                PedItemController item = new PedItemController();
                item.setIdPedido(ped.getIdPedido());
                item.setIdProduto(produ.getId());
                item.setQtPedido((int) produ.getQtde());
                item.setVlUnitario(produ.getPreco());
                item.salvarItemPed();
            }
            session.setAttribute("produtos", null);
            response.sendRedirect("/e-commerce/carrinho.jsp");
        } else {
            response.sendRedirect("/e-commerce/index.jsp?tela=1");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
