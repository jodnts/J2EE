package com.example.demo1.controller;

import com.example.demo1.dao.SignInDAO;
import com.example.demo1.model.User;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@WebServlet("/signin")
public class SignInController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        User user = new User();
        user.setMail(mail);
        user.setPassword(password);

        // Enregistrer l'utilisateur dans la base de données
        SignInDAO signInDAO = new SignInDAO();
        signInDAO.registerUser(user);


        boolean isAuthenticated = signInDAO.authenticateUser(mail, password);

        if (isAuthenticated) {
            // L'authentification a réussi
            // Envoyer un e-mail de confirmation
            sendConfirmationEmail(mail);
            // Rediriger l'utilisateur vers la page de connexion
            response.sendRedirect("/demo1_war_exploded/login.jsp?success=sign_succeeded");
        } else {
            // L'authentification a échoué
            // Afficher un message d'erreur sur la page de connexion
            response.sendRedirect("/demo1_war_exploded/signin.jsp?error=sign_failed");

        }
    }

    private void sendConfirmationEmail(String recipientEmail) throws ServletException, UnsupportedEncodingException {
        final String username = "whylolilol@gmail.com";
        final String myPassword = "shay ioqu srvv xmgq";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Créer une session de messagerie avec les propriétés configurées
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, myPassword);
            }
        });


        try {
            String senderName = "E-Tasty";
            InternetAddress fromAddress = new InternetAddress(username, senderName);
            jakarta.mail.Message message = new MimeMessage(session);
            message.setFrom(fromAddress);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Confirmation de création de compte");
            message.setText("Merci pour votre inscription. Votre compte a été créé avec succès.");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new ServletException("Erreur lors de l'envoi de l'e-mail de confirmation", e);
        }
    }
}
