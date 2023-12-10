<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Tasty</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="shortcut icon" href="https://cdn.discordapp.com/attachments/828747739156185178/1180605825719488553/cup-with-tea-bag-icon-vector-15045708-removebg-preview-modified.png"  type="image/x-icon">
</head>

<body>

<%@include file="includes/header.jsp"%>
<div class="sub-bar">
    <p>Livraison offerte d&egraves 50&euro; d'achats</p>
</div>


    <div class="carre">
        <div class="top-left">
            <img src="https://cdn.discordapp.com/attachments/828747739156185178/1180477756526579784/the-vert-aux-feuilles-the_758367-3404.png" alt="Image">
        </div>
        <div class="top-right">
            <h2>L'art du thé</h2>
            <p>Découvrez l'art et la saveur dans chaque infusion. Chez nous, le thé n'est pas seulement une boisson, c'est une expérience. Parcourez notre large sélection de thés soigneusement sélectionnés à travers le monde. De délicats thés verts aux puissants thés noirs, nous vous offrons un voyage sensoriel unique à chaque tasse.</p>
            <a href="#boutique" class="button">Découvrir</a>
        </div>
    </div>


    <section>
    <h2 id="boutique"><br>NOS PRODUITS</h2>
    <hr>
        <div class="texte-produits">
            Plongez dans une expérience sensorielle unique avec notre sélection de thés soigneusement choisis à travers le monde. Explorez des saveurs délicates, des arômes envoûtants et des mélanges authentiques qui sauront ravir vos papilles.
        </div>
    </section>

    <%-- Affichage du message d'erreur lors de l'ajout d'un article--%>
    <% if(request.getParameter("error") != null) { %>
    <div class="error">L'article n'a pas pu être ajouté. Veuillez réessayer.</div>
    <% }
    else if (request.getParameter("success") != null) { %>
    <div class="success">Ajout de l'article réussi</div>
    <% } %>

    <div class="presentation">

        <c:forEach var="productArray" items="${products}">
            <div class="product">
                <a href="products/${productArray[1].toLowerCase().replace(' ', '').replace('é', 'e').replace('è', 'e').concat('.jsp')}">
                <form action="addtocart" method="post">
                    <input type="hidden" name="productId" value="${productArray[0]}">
                    <img src="<c:out value='${productArray[3]}' />" alt="<c:out value='${productArray[1]}' />">
                    <h3><c:out value='${productArray[1]}' /></h3>
                    <p>Prix: <c:out value='${productArray[2]}' />€</p>
                    <button type="submit">Ajouter au panier</button>
                </form>
            </div>
        </c:forEach>

    </div>
<%@include file="includes/footer.jsp"%>

</body>

</html>
