<%@page import="com.sun.xml.internal.bind.v2.model.core.ID"%>
<%@ page import="br.com.controller.LoginController" %>
<%@ page import="br.com.controller.ProdutoController" %>

<% if (LoginController.estaLogado(request)) {%>

<%@include  file="header.jsp" %>

<%    String idProduto = request.getParameter("id_produto");
    ProdutoController prod = new ProdutoController();
    String idProd = "";
    String nmProd = "";
    String dsProd = "";
    String vlProd = "";
    String men = "Cadastrar Produto";
    if (idProduto != null && !idProduto.equals("")) {
        prod.consultaProduto(Integer.parseInt(idProduto));
        idProd = Integer.toString(prod.getId());
        nmProd = prod.getNome();
        dsProd = prod.getDescricao();
        vlProd = Double.toString(prod.getPreco());
        men = "Editar Produto";
    }
%>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header"><%=men%></h1>
    </div>
</div>

<%
    String erro = (String) request.getAttribute("errMsg");
    if (erro != null) {
        idProd = (String) request.getAttribute("id_produto");
        nmProd = (String) request.getAttribute("nm_produto");
        dsProd = (String) request.getAttribute("ds_produto");
        vlProd = (String) request.getAttribute("vl_produto");
%><div class="alert alert-danger" role="alert"><strong>Erro! </strong> <%=erro%></div><%
    }
%>

<div class="row">
    <div class="col-lg-12">
        <form action="alterarProduto" method="post" style="margin-bottom: 20px;">   
            <table>
                <tr>
                    <td>
                        <label class="control-label"> Cód.</label>
                    </td>
                    <td>
                        <label class="control-label"> Nome do Produto</label>
                    </td>
                    <td>
                        <label class="control-label"> Especificações do Produto</label>
                    </td>
                    <td>
                        <label class="control-label"> Preço R$</label>
                    </td>
                </tr>
                <tr>
                    <td style="width: 50px; padding: 5px;">
                        <input  type="text" name="id_produto" value="<%=idProd%>" class="form-control" aria-describedby="basic-addon1" readonly/>
                    </td>
                    <td style="width: 300px; padding: 5px;">
                        <input type="text" name="nm_produto" value="<%=nmProd%>" class="form-control" aria-describedby="basic-addon1"/>
                    </td>
                    <td style="width: 500px; padding: 5px;">
                        <input type="text" name="ds_produto" value="<%=dsProd%>" class="form-control" aria-describedby="basic-addon1"/>
                    </td>
                    <td style="width: 150px; padding: 5px;">
                        <input type="text" name="vl_produto" value="<%=vlProd%>" class="form-control" aria-describedby="basic-addon1"/>
                    </td>
                    <td style="width: 150px; padding: 5px;">
                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Gravar</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<div class="row">
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

            <%
                prod = new ProdutoController();
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
                    <form action="cadastro.jsp" method="post">
                        <input type="hidden" name="id_produto" value="<%=id%>">
                        <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Editar</button>
                    </form>
                </td>
                <td>
                    <form action="excluirProduto" method="post">
                        <input type="hidden" name="id_produto" value="<%=id%>">
                        <button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Excluir</button>
                    </form>
                </td>
            </tr>

            <%}%>
        </table>
    </div>   
</div>
<%@include  file="footer.html" %>

<% } else {
        response.sendRedirect("/e-commerce/index.jsp?tela=3");
    }
%>