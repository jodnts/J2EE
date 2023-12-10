package com.example.demo1.controller;

import com.example.demo1.dao.CartDAO;
import com.example.demo1.dao.ProductDAO;
import com.example.demo1.model.CartItem;
import com.example.demo1.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class SummaryController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = ((User) request.getSession().getAttribute("user")).getId(); // Récupérer l'ID de l'utilisateur de la session
        CartDAO cartDAO = new CartDAO();
        List<Object[]> cartItems = cartDAO.getAllCartItem(userId);

        // Ajoutez la liste des produits à l'objet request
        request.setAttribute("cartItems", cartItems);

        // Log
        System.out.println("Number of cartItems fetched: " + cartItems.size());

        // Redirigez vers votre JSP d'affichage des produits
        RequestDispatcher dispatcher = request.getRequestDispatcher("checkoutt.jsp");
        dispatcher.forward(request, response);
    }
}
