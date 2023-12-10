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

@WebServlet("/panier")
public class CartController extends HttpServlet {

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = ((User) request.getSession().getAttribute("user")).getId();
        CartDAO cartDAO = new CartDAO();
        List<Object[]> cartItems = cartDAO.getAllCartItem(userId);

        request.setAttribute("cartItems", cartItems);

        // Log
        System.out.println("Number of cartItems fetched: " + cartItems.size());

        RequestDispatcher dispatcher = request.getRequestDispatcher("panier.jsp");
        dispatcher.forward(request, response);
    }
}
