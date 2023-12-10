package com.example.demo1.controller;

import com.example.demo1.dao.ProductDAO;
import com.example.demo1.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addproduct")
public class AddProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer les données du formulaire
            String productName = request.getParameter("productName");
            double productPrice = Double.parseDouble(request.getParameter("productPrice"));
            String productImage = request.getParameter("productImage");
            int userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println("product Name : "+productName);
            System.out.println("product Price : "+productPrice);
            System.out.println("Product Image : "+productImage);
            System.out.println("userID : "+userId);


            // Créer un objet Product avec les données du formulaire
            Product newProduct = new Product();
            newProduct.setName(productName);
            newProduct.setPrice(productPrice);
            newProduct.setImage(productImage);
            newProduct.setUserId(userId);

            // Ajouter le produit à la base de données
            ProductDAO productDAO = new ProductDAO();
            productDAO.addProduct(newProduct);

            // Rediriger vers une page de confirmation ou une autre page appropriée
            response.sendRedirect("/demo1_war_exploded/products?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les erreurs de manière appropriée
            response.sendRedirect("/demo1_war_exploded/products?error=true");
        }
    }
}
