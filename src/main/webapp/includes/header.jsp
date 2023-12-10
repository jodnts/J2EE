<%@ page import="com.example.demo1.model.User" %>
<%@ page import="com.example.demo1.dao.ModoDAO" %>
<%@ page import="com.example.demo1.model.Modo" %>

<header>
    <div class="header-content">
    <span>E-TASTY</span>
    <nav>
        <ul>
            <li><a href="../demo1_war_exploded/products">ACCUEIL</a></li>
            <li><a href="/demo1_war_exploded/products#boutique">Boutique</a></li>
            <%
                Object user = request.getSession().getAttribute("user");
                if (user != null) {
                    String role = ((User) user).getRole();
                    if ("admin".equals(role)) {
            %>
            <li><a href="manageProducts">PRODUITS</a></li>
            <li><a href="manageModos">Mod&eacuterateurs</a></li>
            <li><a href="allOrders">Commandes</a></li>
            <li><a href="logout">D&eacuteconnexion</a></li>
            <%
            } else if ("modo".equals(role)) {
                ModoDAO modoDAO = new ModoDAO();
                Object[] modo = modoDAO.getModoByUserID(((User) user).getId());
                if (modo != null && !"oui".equals(modo[2])) {
            %>
            <li><a href="allOrders">Commandes</a></li>
            <li><a href="logout">D&eacuteconnexion</a></li>
            <%
            } else {
            %>
            <li><a href="manageProducts">Produits</a></li>
            <li><a href="allOrders">Commandes</a></li>
            <li><a href="logout">D&eacuteconnexion</a></li>
            <%
                }
            } else {
            %>
            <li><a href="panier">Panier</a></li>
            <li><a href="myOrders">Commandes</a></li>
            <li><a href="logout">D&eacuteconnexion</a></li>
            <%
                }
            } else {
            %>
            <li><a href="../demo1_war_exploded/login.jsp">Connexion</a></li>
            <%
                }
            %>

        </ul>
    </nav>
    </div>
    <div class="sub-bar">
        .
    </div>
</header>