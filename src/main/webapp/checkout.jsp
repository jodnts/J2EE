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

<h1>Entrez vos informations de paiements</h1>
<form action="" method="post">
    <label for="cardNumber">Numéro de carte :</label>
    <input type="text" id="cardNumber" name="cardNumber" placeholder="1234 5678 9012 3456" required><br><br>

    <label for="cardHolder">Titulaire de la carte :</label>
    <input type="text" id="cardHolder" name="cardHolder" placeholder="Titulaire de la carte" required><br><br>

    <label for="expiryDate">Date d'expiration :</label>
    <input type="text" id="expiryDate" name="expiryDate" placeholder="MM/YY" required><br><br>

    <label for="cvv">CVV :</label>
    <input type="text" id="cvv" name="cvv" placeholder="123" required><br><br>

    <input type="submit" value="Confirmer le paiement">
</form>

<div class="cart-items">
    <c:set var="grandTotal" value="0" />

    <c:forEach var="cartItem" items="${cartItems}">
        <div class="cart-item">
            <div class="item-image">
                <img src="<c:out value='${cartItem[4]}' />" alt="<c:out value='${cartItem[2]}' />">
            </div>
            <div class="item-details">
                <p><c:out value='${cartItem[2]}' /></p> <!--nom-->
                <p>Quantité: <c:out value='${cartItem[1]}' /></p> <!--quantité-->
                <p>Prix: <c:out value='${cartItem[3]}' />€</p> <!--prix-->
            </div>
        </div>
        <c:set var="grandTotal" value="${grandTotal + (cartItem[3] * cartItem[1])}" />
    </c:forEach>
</div>


<%@include file="includes/footer.jsp"%>

</body>
</html>
