<%@ page import="br.com.controller.LoginController" %>
<%@ page import="br.com.controller.ProdutoController" %>

<!--< if (LoginController.estaLogado(request)) { %>-->

<%@include  file="header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Comércio e Varejo
            <small>Linha Automotiva</small>
        </h1>
    </div>
</div>

<div class="row" style="margin-bottom: 20px;">
    <form action="main.jsp" method="get">
        <div class="input-group col-md-offset-2 col-md-8">     
            <input placeholder="Busque o que precisa..." type="text" name="consulta" class="form-control" aria-label="...">
            <div class="input-group-btn">
                <button  type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
            </div>      
        </div>
    </form>
</div>

<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading"><b>Produtos</b></div>
    <table class="table">
        <tr>
            <th>Cod.</th>
            <th>Produto</th>
            <th>Especificações</th>
            <th>Preço</th>
        </tr>

        <%            ProdutoController prod = new ProdutoController();
            String consulta = request.getParameter("consulta");
            if (consulta != null) {
                prod.setNome(consulta);
            }
            ResultSet rs = prod.consulta();
            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                String informacao = rs.getString("informacao");
                double valor = rs.getDouble("valor");
        %>

        <tr>
            <td><%=id%></td>
            <td><%=descricao%></td>
            <td><%=informacao%></td>
            <td style="color: #339b4d;"><h4><b>R$ <%=valor%></b></h4></td>
            <td>
                <form action="addCarrinho" method="post">
                    <input type="hidden" name="id_produto" value="<%=id%>">
                    <button type="submit" class="btn btn-primary">Comprar</button>
                </form>
            </td>
        </tr>

        <%}%>
    </table>
</div>    
<%@include  file="footer.html" %>

<!--< } else {
        response.sendRedirect("/e-commerce");
    }
%>-->