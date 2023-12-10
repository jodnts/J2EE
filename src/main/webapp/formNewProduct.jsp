<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 14/11/2023
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Formulaire produit</title>
  <link rel="stylesheet" href="css/index.css">
  <link rel="stylesheet" href="css/form.css">
  <link rel="shortcut icon" href="https://cdn.discordapp.com/attachments/828747739156185178/1180605825719488553/cup-with-tea-bag-icon-vector-15045708-removebg-preview-modified.png"  type="image/x-icon">

</head>

<body>

<%@include file="includes/header.jsp"%>

<section>
  <h2><br>Ajouter un nouveau produit </h2>
</section>

<form action="addproduct" method="post">
  <label for="productName">Nom du Produit:</label>
  <input type="text" id="productName" name="productName" required><br>

  <label for="productPrice">Prix:</label>
  <input type="number" id="productPrice" name="productPrice" step="0.01" required><br>

  <label for="productImage" class="productimage">Lien image de référence:</label>
  <input type="text" id="productImage" name="productImage" required><br>

  <label for="productDescription" class="productDescription">Description du produit:</label>
  <input type="text" id="productDescription" name="productImage" required><br>

  <!-- Champ caché pour l'ID de l'utilisateur -->
  <input type="hidden" name="userId" value="<%= ((com.example.demo1.model.User) session.getAttribute("user")).getId() %>">

  <input type="submit" value="Ajouter le Produit">
</form>

<%@include file="includes/footer.jsp"%>

</body>
</html>
