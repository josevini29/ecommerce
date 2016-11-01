<%@page import="br.com.controller.PedItemController"%>
<%@page import="br.com.controller.PedidoController"%>
<%@ page import="br.com.controller.LoginController" %>
<%@ page import="br.com.controller.ProdutoController" %>

<% if (LoginController.estaLogado(request)) { %>

<%@include  file="header.jsp" %>
<%        String user = LoginController.getUsuario(request);%>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Compras Efetuadas - Usuario: <%=user%>
        </h1>
    </div>
</div>
<div class="panel panel-default">
    <%   PedidoController ped = new PedidoController();
        ped.setIdUsuario(Integer.parseInt(user));
        ResultSet rst = ped.consulta();
        boolean tem = false;
        while (rst.next()) {
            tem = true;
            int idPedido = rst.getInt("id");
    %>
    <!-- Default panel contents -->
    <div class="panel-heading"><b>Pedido: <%=idPedido%></b></div>
    <table class="table">
        <tr>
            <th>Cod.</th>
            <th>Produto</th>
            <th>Quantidade</th>
            <th>Preço</th>
            <th>Total</th>
        </tr>

        <%
            PedItemController item = new PedItemController();
            item.setIdPedido(idPedido);
            ResultSet rs = item.consultaItem();
            while (rs.next()) {
                ProdutoController prod = new ProdutoController();
                int id = rs.getInt("id_produto");
                prod.consultaProduto(id);
                String descricao = prod.getNome();
                int quan = rs.getInt("quantidade");
                double unitario = rs.getDouble("vl_unitario");
                double total = quan * unitario;
        %>

        <tr>
            <td><%=id%></td>
            <td><%=descricao%></td>
            <td><%=quan%></td>
            <td style="color: #339b4d;"><h4><b>R$ <%=unitario%></b></h4></td>
            <td style="color: #339b4d;"><h4><b>R$ <%=total%></b></h4></td>
        </tr>

        <%}%>
    </table>
    <%}%>
    <%if (!tem){%>
        <h4 style="margin: 15px;">Nenhum pedido realizado!</h4>
    <%}%>
</div>    
<%@include  file="footer.html" %>

<% } else {
        response.sendRedirect("/e-commerce/index.jsp?tela=2");
    }
%>