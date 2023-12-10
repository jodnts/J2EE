package com.example.demo1.controller;

import com.example.demo1.dao.ModoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addModo")
public class AddModoController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer le mail de l'utilisateur à promouvoir
            String mail = request.getParameter("mail");
            System.out.println("mail: " + mail);

            // Utiliser ProductDAO pour supprimer le produit de la base de données
            ModoDAO modoDAO = new ModoDAO();
            modoDAO.addModo(mail);

            // Rediriger vers une page de confirmation ou une autre page appropriée
            response.sendRedirect("manageModos?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les erreurs de manière appropriée
            response.sendRedirect("manageModos?error=true"); //
        }
    }
}
