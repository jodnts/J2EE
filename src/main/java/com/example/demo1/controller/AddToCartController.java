package com.example.demo1.controller;

import com.example.demo1.dao.CartDAO;

import com.example.demo1.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addtocart")
public class AddToCartController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier si l'utilisateur est connecté
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
            response.sendRedirect("login.jsp?notConnected=true");
            return; // Arrêter l'exécution du reste du code dans ce cas
        }

        // L'utilisateur est connecté, continuer le traitement
        String productIdString = request.getParameter("productId");
        int productId = Integer.parseInt(productIdString);
        System.out.println("ProductID : " + productId);

        int userId = user.getId();

        String quantityString = request.getParameter("quantity");
        int quantity = 1;

        if (quantityString != null && !quantityString.isEmpty()) {
            quantity = Integer.parseInt(quantityString);
        }
        else {
            System.out.println("Malaise");
        }

        CartDAO cartDAO = new CartDAO();
        cartDAO.addToCart(productId, userId, quantity); // Mettre à jour la méthode addToCart pour prendre en compte la quantité

        response.sendRedirect("panier");
    }
}





