<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 18/11/2023
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erreur accès</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="shortcut icon" href="https://cdn.discordapp.com/attachments/828747739156185178/1180605825719488553/cup-with-tea-bag-icon-vector-15045708-removebg-preview-modified.png"  type="image/x-icon">

</head>

<body>

<%@include file="includes/header.jsp"%>
<div class="pageErreur">
<p>Erreur 404</p>
    <a href="products">
        <button >Retourner à la page des produits</button>
    </a>
    <br><br>
    <img src="https://cdn.discordapp.com/attachments/828747739156185178/1175515030087815340/image.png">
</div>

<%@include file="includes/footer.jsp"%>

</body>
</html>
