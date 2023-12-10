package com.example.demo1.controller;

import com.example.demo1.dao.ModoDAO;
import com.example.demo1.dao.ProductDAO;
import com.example.demo1.model.Modo;
import com.example.demo1.model.Product;
import com.example.demo1.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/manageModos")
public class ManageModoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérez le rôle et l'ID de l'utilisateur depuis la session
        String userRole = ((User) request.getSession().getAttribute("user")).getRole();
        int userId = ((User) request.getSession().getAttribute("user")).getId();
        System.out.println("userID :"+ userId);
        System.out.println("userRole :"+ userRole);


        ModoDAO modoDAO = new ModoDAO();
        List<Object[]> modos;
        if ("admin".equals(userRole)) {
            modos = modoDAO.getAllModos();
        } else {
            modos = Collections.emptyList();
        }

        // Ajoutez la liste des produits à l'objet request
        request.setAttribute("modos", modos);

        // Log
        System.out.println("Number of modos fetched: " + modos.size());

        // Redirigez vers votre JSP d'affichage des produits
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageModo.jsp");
        dispatcher.forward(request, response);
    }
}

