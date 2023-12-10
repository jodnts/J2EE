<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 11/11/2023
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>E-Tasty</title>
  <link rel="stylesheet" href="css/index.css">
  <link rel="stylesheet" href="css/panier.css">
  <link rel="shortcut icon" href="https://cdn.discordapp.com/attachments/828747739156185178/1180605825719488553/cup-with-tea-bag-icon-vector-15045708-removebg-preview-modified.png"  type="image/x-icon">

</head>

<body>

<%@include file="includes/header.jsp"%>

<section>
  <h2><br>Panier</h2>
</section>
<c:if test="${not empty cartItems}">


  <div class="presentation">
    <table>
      <tr>
        <th></th>
        <th>Produit</th>
        <th>Prix</th>
        <th>Quantité</th>
        <th>Total par produit</th>
        <th>Supprimer</th>

      </tr>
      <c:set var="grandTotal" value="0" />

      <c:forEach var="cartItem" items="${cartItems}">
        <tr>
          <td><img src="<c:out value='${cartItem[4]}' />" alt="<c:out value='${cartItem[2]}' />"></td> <!--photo-->
          <td><c:out value='${cartItem[2]}' /></td> <!--nom-->
          <td><c:out value='${cartItem[3]}' />€</td> <!--prix-->
          <td>
            <form method="post" action="deletefromcart">
              <input type="hidden" name="productId" value="${cartItem[0]}" />
              <input type="hidden" name="removeAll" value="false">
              <button class="less" type="submit">-</button>
            </form>
            <div class="quantity">
            <c:out value='${cartItem[1]}' /> <!--quantité-->            </div>
            <form method="post" action="addtocart">
            <input type="hidden" name="productId" value="${cartItem[0]}" />
            <button class="plus" type="submit">+</button>
          </form>
          </td>
          <td><c:out value="${cartItem[3] * cartItem[1]}" />€</td> <!--total-->
          <c:set var="grandTotal" value="${grandTotal + (cartItem[3] * cartItem[1])}" />
          <td><form method="post" action="deletefromcart">
            <input type="hidden" name="productId" value="${cartItem[0]}" />
            <input type="hidden" name="removeAll" value="true">
            <button type="submit">
              <img class="poubelle" src="https://media.discordapp.net/attachments/828747739156185178/1173737055638011964/poubelle-poubelle-icC3B4ne-de-la-poubelle.png" alt="poubelle">
            </button>
          </form></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <p id="total">Total : <c:out value="${grandTotal}" />€</p>
  <a href="checkoutt.jsp"><button id="payer">Commander</button></a>

</c:if>
<c:if test="${empty cartItems}">
  <p style="text-align: center">Le panier est vide.</p>
</c:if>
<%@include file="includes/footer.jsp"%>

</body>

</html>
