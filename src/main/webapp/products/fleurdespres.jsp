<%--
  Created by IntelliJ IDEA.
  User: CYTech Student
  Date: 02/12/2023
  Time: 00:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gardenia</title>
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/product.css">
    <link rel="shortcut icon" href="https://cdn.discordapp.com/attachments/828747739156185178/1180605825719488553/cup-with-tea-bag-icon-vector-15045708-removebg-preview-modified.png"  type="image/x-icon">

</head>
<body>

<%@include file="../includes/headerBack.jsp"%>

<div class="product-grid">
    <div class="product-image">
        <img src="https://cdn.discordapp.com/attachments/828747739156185178/1180253757578162226/eau-acidulee-epicee-ginger-power.jpg" alt="Sakura Vert">
    </div>
    <div class="product-details">
        <h2>Fleurs des prés</h2>
        <p>            THÉ VERT - FRAMBOISE, VIOLETTE (MAISON MARGARET)
        </p>
        <p>
            Un savoureux mélange de thé vert et blanc se dégustant à toute heure de la journée. Fruité et acidulé grâce aux framboises entières et aux fleurs de violettes, ce mélange singulier en a déjà conquis plus d’une !
        </p>
        <p class="price">8€</p>
        <label for="quantity">Quantité :</label>
        <form action="../addtocart" method="post">
            <div class="quantity-container">
                <input type="hidden" name="productId" value="1">
                <button type="button" class="quantity-btn minus">-</button>
                <input type="number" id="quantity" name="quantity" min="1" value="1" readonly>
                <button type="button" class="quantity-btn plus">+</button>
            </div>
            <button type="submit" class="add-to-cart">Ajouter au panier</button>
        </form>
    </div>
</div>


<%@include file="../includes/footer.jsp"%>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const minusBtn = document.querySelector('.minus');
        const plusBtn = document.querySelector('.plus');
        const quantityInput = document.getElementById('quantity');

        minusBtn.addEventListener('click', () => {
            const currentValue = parseInt(quantityInput.value);
            if (currentValue > 1) {
                quantityInput.value = currentValue - 1;
            }
        });

        plusBtn.addEventListener('click', () => {
            const currentValue = parseInt(quantityInput.value);
            quantityInput.value = currentValue + 1;
        });
    });
</script>
</body>
</html>
