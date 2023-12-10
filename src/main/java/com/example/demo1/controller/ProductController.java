package com.example.demo1.controller;

import com.example.demo1.dao.ProductDAO;
import com.example.demo1.model.Product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class ProductController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Object[]> products = productDAO.getAllProducts();

        // Ajoutez la liste des produits à l'objet request
        request.setAttribute("products", products);

        // Log
        System.out.println("Number of products fetched: " + products.size());

        // Redirigez vers votre JSP d'affichage des produits
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
