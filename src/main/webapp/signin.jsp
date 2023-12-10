<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 02/11/2023
  Time: 21:33
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

<div id="container">
    <!-- zone de connexion -->
    <form action="signin" method="POST">
        <h1>Inscription</h1>

            <%  if (request.getParameter("error") != null ) { %>
        <div style="color: red;">Inscription échouée, veuillez réessayer.</div>
            <% } %>

        <label><b>Entrez votre mail</b></label>
        <input type="text" placeholder="Entrer mail" name="mail" required>

        <label><b>Choisissez votre mot de passe</b></label>
        <input type="password" placeholder="Choisir mot de passe" name="password" required>

        <input type="submit" id='submit' value='INSCRIPTION'>

        <p>Vous avez déjà un compte ? </p>
        <p><a href="login.jsp">Connexion</a></p>
    </form>
</div>

<%@include file="includes/footer.jsp"%>

</body>

</html>

