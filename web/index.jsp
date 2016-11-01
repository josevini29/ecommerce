<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>E-Commerce</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- JS -->
        <script type="text/javascript" src="js/lib/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="js/lib/bootstrap.min.js"></script>

        <!-- CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/custom.css">
    </head>
    <body>
        <div class="row">
            <div class="col-sm-6 col-md-4 col-md-offset-4">
                <div class="account-wall">
                    <img class="profile-img" src="img/profile.jpg" alt="">
                    <form class="form-signin" action="ServletLogin?tela=<%=request.getParameter("tela")%>" method="POST">
                        <input type="text" class="form-control" placeholder="Usuário" name="usuario" autofocus required>
                        <input type="password" class="form-control" placeholder="Senha" name="senha" required>
                        <button class="btn btn-lg btn-black btn-block" type="submit">Entrar</button>
                    </form>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="js/app.js"></script>
    </body>
</html>