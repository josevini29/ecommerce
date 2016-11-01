<%-- 
    Document   : galeria
    Created on : 13/10/2016, 23:15:28
    Author     : Jose Vinicius
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.controller.ProdutoController"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>E-commerce</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/3-col-portfolio.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="main.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Home</a>
                    <a class="navbar-brand" href="pedidos.jsp"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Meus Pedidos</a>
                    <%
                        session = request.getSession();
                        ArrayList<ProdutoController> produto = new ArrayList<>();
                        produto = (ArrayList<ProdutoController>) session.getAttribute("produtos");
                        int qtde;
                        if (produto == null){
                            qtde = 0;
                        }else{
                            qtde = produto.size();
                        }
                    %>
                    <a class="navbar-brand" href="carrinho.jsp"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Carrinho (<%=qtde%>)</a>
                   <!-- <a class="navbar-brand" href="cadastro.jsp"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span> Cadastrar Produto</a>-->
                </div>
            </div>
        </nav>
        <div class="container">
