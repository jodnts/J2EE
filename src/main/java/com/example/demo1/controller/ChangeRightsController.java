package com.example.demo1.controller;

import com.example.demo1.dao.ModoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/changeRights")
public class ChangeRightsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int modoID = Integer.parseInt(request.getParameter("modoID"));
            String rights = request.getParameter("rights");

            ModoDAO modoDAO = new ModoDAO();
            modoDAO.changeRights(modoID, rights);

            // Rediriger vers une page de confirmation ou une autre page appropriée
            response.sendRedirect("/demo1_war_exploded/manageModos?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les erreurs de manière appropriée
            response.sendRedirect("/demo1_war_exploded/manageModos?error=true");
        }
    }
}
