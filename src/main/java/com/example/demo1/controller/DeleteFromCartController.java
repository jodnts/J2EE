package com.example.demo1.controller;

import com.example.demo1.dao.CartDAO;
import com.example.demo1.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletefromcart")
public class DeleteFromCartController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();

        String productIdString = request.getParameter("productId");

        int productId = Integer.parseInt(productIdString);
        System.out.println("ProductID : " + productId);

        boolean removeAll = Boolean.parseBoolean(request.getParameter("removeAll"));

        CartDAO cartDAO = new CartDAO();
        cartDAO.removeFromCart(productId, removeAll, userId);

        response.sendRedirect("panier");
    }
}

