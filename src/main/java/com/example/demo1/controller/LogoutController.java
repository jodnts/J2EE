package com.example.demo1.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la session de l'utilisateur
        request.getSession().invalidate(); // Invalidate la session (déconnexion)

        // Rediriger vers la page de connexion après la déconnexion
        response.sendRedirect("/demo1_war_exploded/login.jsp");
    }
}
