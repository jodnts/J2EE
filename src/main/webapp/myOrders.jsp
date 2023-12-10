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
  <link rel="stylesheet" href="css/myOrders.css">
  <link rel="shortcut icon" href="https://cdn.discordapp.com/attachments/828747739156185178/1180605825719488553/cup-with-tea-bag-icon-vector-15045708-removebg-preview-modified.png"  type="image/x-icon">

</head>

<body>

<%@include file="includes/header.jsp"%>

<section>
  <%
      String role = ((User) user).getRole();
      if ("admin".equals(role) || "modo".equals(role)) {
  %>
      <h2>Commandes clients</h2>
  <%
  } else {
  %>
      <h2>Mes commandes</h2>
<%
    } %>
</section>

<c:choose>
  <c:when test="${not empty userOrders}">
    <div class="orders-container">
      <c:forEach items="${userOrders}" var="order">
        <div class="order-box">
          <p class="orderID">Commande n°${order[0]}</p>
          <p class="date">Faite le ${order[1]}</p>
          <p class="state">${order[2]}</p>
        </div>
      </c:forEach>
    </div>
  </c:when>
  <c:otherwise>
    <p class="noOrder">Aucune commande effectuée pour le moment.</p>
  </c:otherwise>
</c:choose>

</body>
</html>
