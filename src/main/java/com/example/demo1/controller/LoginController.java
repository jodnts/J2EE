package com.example.demo1.controller;

import com.example.demo1.dao.UserDAO;
import com.example.demo1.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = null;
        try {
            user = userDAO.getUser(mail, password);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        if (user != null) {
            //Session activée
            request.getSession().setAttribute("user", user);
            // L'authentification a réussi, redirigez vers la page d'accueil ou une autre page sécurisée
            response.sendRedirect("/demo1_war_exploded/products");
        } else {
            // L'authentification a échoué, redirigez vers la page de connexion avec un message d'erreur
            response.sendRedirect("/demo1_war_exploded/login.jsp?error=auth_failed");
        }
    }
}
