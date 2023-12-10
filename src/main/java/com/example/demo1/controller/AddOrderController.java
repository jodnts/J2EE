package com.example.demo1.controller;

import com.example.demo1.dao.OrderDAO;
import com.example.demo1.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addOrder")
public class AddOrderController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String postalCode = request.getParameter("postalCode");
            String mail = request.getParameter("mail");

            System.out.println("mail = " +mail);

            Order order = new Order();
            order.setUserId(userId);
            order.setFirstName(firstName);
            order.setLastName(lastName);
            order.setAddress(address);
            order.setCity(city);
            order.setPostalCode(postalCode);

            OrderDAO orderDAO = new OrderDAO();
            orderDAO.addOrder(order);

            List<Object[]> products = orderDAO.getProductsByUser(userId);

            orderDAO.sendReceipt(mail, order, products);

            //orderDAO.deleteOrderFromCart(userId);

            response.sendRedirect("orderConfirmation.jsp"); // Rediriger vers une page de confirmation
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions et rediriger vers une page d'erreur si nécessaire
            response.sendRedirect("error.jsp");
        }
    }


}