<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 20/11/2023
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/checkout.css">
    <link rel="shortcut icon" href="https://cdn.discordapp.com/attachments/828747739156185178/1180605825719488553/cup-with-tea-bag-icon-vector-15045708-removebg-preview-modified.png"  type="image/x-icon">


</head>
<body>
<%@include file="includes/header.jsp"%>

<h1>Entrez votre adresse de livraison</h1>
<form action="addOrder" method="post">
    <label for="lastName"></label>
    <input type="text" id="lastName" name="lastName" required placeholder="Nom"><br><br>

    <label for="firstName"></label>
    <input type="text" id="firstName" name="firstName" placeholder="Prénom"><br><br>

    <label for="address"></label>
    <input type="text" id="address" name="address" placeholder="Adresse de livraison"><br><br>

    <label for="city"></label>
    <input type="text" id="city" name="city" placeholder="Ville" required><br><br>

    <label for="postalCode"></label>
    <input type="text" id="postalCode" name="postalCode" placeholder="Code Postal"><br><br>

    <input type="hidden" name="userId" value="<%= ((com.example.demo1.model.User) session.getAttribute("user")).getId() %>">
    <input type="hidden" name="mail" value="<%= ((com.example.demo1.model.User) session.getAttribute("user")).getMail() %>">

    <h1>Entrez vos informations de paiements</h1>

        <label for="cardNumber">Numéro de carte :</label>
        <input type="text" id="cardNumber" name="cardNumber" placeholder="1234 5678 9012 3456" required><br><br>

        <label for="cardHolder">Titulaire de la carte :</label>
        <input type="text" id="cardHolder" name="cardHolder" placeholder="Titulaire de la carte" required><br><br>

        <label for="expiryDate">Date d'expiration :</label>
        <input type="text" id="expiryDate" name="expiryDate" placeholder="MM/YY" required><br><br>

        <label for="cvv">CVV :</label>
        <input type="text" id="cvv" name="cvv" placeholder="123" required><br><br>

    <input type="submit" value="Confirmer la commande">
</form>


<%@include file="includes/footer.jsp"%>

</body>
</html>
