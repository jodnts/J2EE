<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 31/10/2023
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="shortcut icon" href="https://cdn.discordapp.com/attachments/828747739156185178/1180605825719488553/cup-with-tea-bag-icon-vector-15045708-removebg-preview-modified.png"  type="image/x-icon">

</head>

<body>

<%@include file="includes/header.jsp"%>
<div class="sub-bar2">
    <p></p>
</div>
<div class="background">
<div id="container">
    <!-- zone de connexion -->
    <form action="login" method="POST">
        <h1>Connexion</h1>

        <% if(request.getParameter("error") != null) { %>
        <div style="color: red;">Identifiants incorrects. Veuillez réessayer.</div>
        <% }
        else if (request.getParameter("success") != null) { %>
        <div style="color: darkgreen;">Inscription réussie. Un mail de confirmation vous a été envoyé.</div>
        <% }
        else if (request.getParameter("notConnected") != null) { %>
        <div style="color: darkgreen; text-align: center">Connectez-vous pour ajouter des articles au panier</div>
        <% } %>


        <label><b>Adresse mail</b></label>
        <input type="text" placeholder="Entrez votre adresse mail" name="mail" required>

        <label><b>Mot de passe</b></label>
        <input type="password" placeholder="Entrez votre mot de passe" name="password" required>

        <input type="submit" id='submit' value='CONNEXION' >

        <p>Pas encore de compte ? </p>
        <p><a href="signin.jsp">Créer un compte</a></p>
    </form>
</div>
</div>
<%@include file="includes/footer.jsp"%>

</body>

</html>
