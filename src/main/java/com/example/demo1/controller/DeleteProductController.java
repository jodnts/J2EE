package com.example.demo1.controller;

import com.example.demo1.dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteproduct")
public class DeleteProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer l'ID du produit à supprimer à partir de la requête
            int productId = Integer.parseInt(request.getParameter("productId"));
            System.out.println("productID: " + productId);

            // Utiliser ProductDAO pour supprimer le produit de la base de données
            ProductDAO productDAO = new ProductDAO();
            productDAO.deleteProduct(productId);

            // Rediriger vers une page de confirmation ou une autre page appropriée
            response.sendRedirect("manageProducts?success=true"); // Remplacez "products" par votre page souhaitée
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les erreurs de manière appropriée
            response.sendRedirect("manageProducts?error=true"); // Rediriger avec un indicateur d'erreur
        }
    }
}
