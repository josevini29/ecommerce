<%@page import="java.util.ArrayList"%>
<%@ page import="br.com.controller.LoginController" %>
<%@ page import="br.com.controller.ProdutoController" %>

<!--< if (LoginController.estaLogado(request)) { %>-->

<%@include  file="header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Por favor conclua sua compra!</h1>
    </div>
</div>
<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><b>Carrinho de Compras</b></div>

    <%
        session = request.getSession();
        ArrayList<ProdutoController> produtos = new ArrayList<>();
        produtos = (ArrayList<ProdutoController>) session.getAttribute("produtos");
        if (produtos != null && produtos.size() != 0) {%>
    <table class="table">
        <tr>
            <th>Cod.</th>
            <th>Produto</th>
            <th style="text-align: center;">Quantidade</th>
            <th style="text-align: center;">Preço</th>
            <th style="text-align: center;">Total<th>
        </tr>


        <%
            for (ProdutoController prod : produtos) {
        %>

        <tr>
            <td><%=prod.getId()%></td>
            <td><%=prod.getNome()%></td>
            <td style="text-align: center;"><h4><b><%=(int) prod.getQtde()%></b></h4></td>
            <td style="color: #339b4d; text-align: center;"><h4><b>R$ <%=prod.getPreco()%></b></h4></td>
            <td style="color: #000b8b; text-align: center;"><h4><b>R$ <%=(prod.getQtde() * prod.getPreco())%></b></h4></td>
            <td>
                <form action="remCarrinho" method="post">
                    <input type="hidden" name="id_produto" value="<%=prod.getId()%>">
                    <button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button>
                </form>
            </td>
        </tr>

        <% }%>    </table>
        <%} else {%>
    <h4 style="margin: 15px;">Carrinho Vazio!</h4>
    <%}%>

</div>   
<% if (produtos != null && produtos.size() != 0) {%>
<div class="row"> 
    <form action="ServletPedido" method="POST">
        <div class="col-md-12">
            <button type="submit" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Confirmar Pedido</button>
        </div>
    </form>
</div>
<%}%>
<%@include  file="footer.html" %>


<!--< } else {
        response.sendRedirect("/e-commerce");
    }
%>-->