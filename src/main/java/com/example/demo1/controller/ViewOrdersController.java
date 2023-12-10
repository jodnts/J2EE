package com.example.demo1.controller;

import com.example.demo1.dao.OrderDAO;
import com.example.demo1.model.Order;
import com.example.demo1.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/allOrders")
public class ViewOrdersController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = ((User) request.getSession().getAttribute("user")).getId();
        OrderDAO orderDAO = new OrderDAO();
        List<Object[]> userOrders = orderDAO.getAllOrders();

        request.setAttribute("userOrders", userOrders);

        // Log
        System.out.println("Number of user orders fetched: " + userOrders.size());

        RequestDispatcher dispatcher = request.getRequestDispatcher("myOrders.jsp");
        dispatcher.forward(request, response);
    }
}
