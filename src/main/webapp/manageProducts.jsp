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
    <h2><br>Gérer les produits</h2>
</section>

    <%-- Affichage du message d'erreur lors de l'ajout d'un article--%>
    <% if(request.getParameter("error") != null) { %>
    <div class="error">L'article n'a pas pu être supprimé. Veuillez réessayer.</div>
    <% }
    else if (request.getParameter("success") != null) { %>
    <div class="success">Supression de l'article réussie</div>
    <% } %>

    <div class="presentation">
        <table>
            <tr>
                <th>Posté par</th>
                <th>Photo</th>
                <th>Produit</th>
                <th>Prix</th>
                <th>Supprimer</th>

            </tr>

            <c:forEach var="productArray" items="${products}">

                <tr>
                    <td><c:out value='${productArray[4]}' /></td> <!--nom-->
                    <td><img src="<c:out value='${productArray[3]}' />" alt="<c:out value='${productArray[1]}' />"></td> <!--photo-->
                    <td><c:out value='${productArray[1]}' /></td> <!--nom-->
                    <td><c:out value='${productArray[2]}' />€</td> <!--prix-->
                    <td><form method="post" action="deleteproduct">
                        <input type="hidden" name="productId" value="${productArray[0]}" />
                        <button type="submit">
                            <img class="poubelle" src="https://media.discordapp.net/attachments/828747739156185178/1173737055638011964/poubelle-poubelle-icC3B4ne-de-la-poubelle.png" alt="poubelle">
                        </button>
                    </form></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <form action="formNewProduct.jsp" method="post">
    <button id="payer" style="margin-top: 20px;">Ajouter un produit</button>
    </form>

<%@include file="includes/footer.jsp"%>

</body>

</html>
