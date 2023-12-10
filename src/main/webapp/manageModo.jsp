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
    <link rel="stylesheet" href="css/manageModo.css">
    <link rel="shortcut icon" href="https://cdn.discordapp.com/attachments/828747739156185178/1180605825719488553/cup-with-tea-bag-icon-vector-15045708-removebg-preview-modified.png"  type="image/x-icon">

</head>

<body>

<%@include file="includes/header.jsp"%>

<section>
    <h2><br>Gérer les modérateurs</h2>
</section>

<%-- Affichage du message d'erreur lors de l'ajout d'un article--%>
<% if(request.getParameter("error") != null) { %>
<div class="error">L'action sur le modérateur n'a pas pu être effectuée. Veuillez réessayer.</div>
<% }
else if (request.getParameter("success") != null) { %>
<div class="success">L'action sur le modérateur a été réussie !</div>
<% } %>

<div class="presentation">
    <table>
        <tr>
            <th>Mail du modérateur</th>
            <th>Droits</th>
            <th>Changer les droits</th>
            <th>Supprimer le modérateur</th>
        </tr>

        <c:forEach var="modos" items="${modos}">
            <tr>
                <td><c:out value="${modos[1]}" /></td> <!--mail -->
                <td>
                    <c:choose>
                        <c:when test="${modos[2] eq 'oui'}">Oui</c:when>
                        <c:otherwise>Non</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="changeRights">
                        <input type="hidden" name="modoID" value="${modos[0]}" />
                        <input type="radio" id="oui" name="rights" value="oui" <c:if test="${modos[2] eq 'oui'}">checked</c:if>>
                        <label for="oui">Mettre les droits</label>
                        <input type="radio" id="non" name="rights" value="non" <c:if test="${modos[2] eq 'non'}">checked</c:if>>
                        <label for="non">Enlever les droits</label>
                        <button type="submit">Sauvegarder</button>
                    </form>
                </td>
                <td><form method="post" action="deleteModo">
                    <input type="hidden" name="modoID" value="${modos[0]}" />
                    <input type="hidden" name="mail" value="${modos[1]}" />
                    <button type="submit">
                        <img class="poubelle" src="https://media.discordapp.net/attachments/828747739156185178/1173737055638011964/poubelle-poubelle-icC3B4ne-de-la-poubelle.png" alt="poubelle">
                    </button>
                </form></td>
            </tr>
        </c:forEach>
    </table>
</div>


<section>
    <h2>Promouvoir un utilisateur au rang de modérateur</h2>
</section>
<div class="manage">
<form action="addModo" method="post">
    <label for="mail">Mail de l'utilisateur à promouvoir : </label>
    <input type="text" id="mail" name="mail"><br>
    <input type="submit" value="Promouvoir">
</form>
</div>
<%@include file="includes/footer.jsp"%>

</body>

</html>
