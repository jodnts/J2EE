package com.example.demo1.dao;
import com.example.demo1.model.CartItem;
import com.example.demo1.model.Order;
import com.example.demo1.dao.CartDAO;

import com.example.demo1.model.Product;
import com.example.demo1.model.User;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class OrderDAO {

    private final SessionFactory sessionFactory;

    public OrderDAO() {
        // Configuration Hibernate à partir du fichier hibernate.cfg.xml
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void addOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Integer maxId = (Integer) session.createNativeQuery("SELECT MAX(orderID) FROM orders")
                    .uniqueResult();
            int newId = (maxId != null) ? maxId + 1 : 1; // Si maxId est null, démarrez à 1

            order.setOrderId(newId); // Définissez le nouvel ID dans l'objet Order

            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    String sql = "INSERT INTO orders (orderID, userID, firstname, lastname, address, city, CP, date, state) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), 'Confirmée')";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, newId);
                        statement.setInt(2, order.getUserId());
                        statement.setString(3, order.getFirstName());
                        statement.setString(4, order.getLastName());
                        statement.setString(5, order.getAddress());
                        statement.setString(6, order.getCity());
                        statement.setString(7, order.getPostalCode());
                        statement.executeUpdate();
                    }
                }
            });
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions
        }
    }

    public void deleteOrderFromCart(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                session.createNativeQuery("DELETE FROM cart WHERE userId = :userID")
                        .setParameter("userId", userId)
                        .executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendReceipt(String recipientEmail, Order order, List<Object[]> products) throws ServletException {
        final String username = "whylolilol@gmail.com";
        final String myPassword = "shay ioqu srvv xmgq";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Créer une session de messagerie avec les propriétés configurées
        jakarta.mail.Session session = jakarta.mail.Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, myPassword);
            }
        });


        try {
            System.out.println("en cours debut");

            String senderName = "E-Tasty"; // Remplacez par le nom de l'expéditeur souhaité
            InternetAddress fromAddress = new InternetAddress(username, senderName);
            jakarta.mail.Message message = new MimeMessage(session);
            message.setFrom(fromAddress);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Confirmation de commande E-Tasty");

            String htmlContent = "<html><body style='font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px;'>";

            htmlContent += "<div>";
            htmlContent += "<h2>Votre commande est confirmée !</h2><br>";
            htmlContent += "<p style='font-size: 14px;'>Chère/Cher " + order.getFirstName() + ",</p>";
            htmlContent += "<p style='font-size: 14px;'>Merci d'avoir passé votre commande auprès de E-Tasty. Nous expédierons vos articles dès qu'ils seront prêts.</p>";
            htmlContent += "<p style='font-size: 14px;'>Commande numéro : " + order.getOrderId() + "</p><br>";
            htmlContent += "<p style='font-size: 14px;'>Vous pouvez consulter toutes les informations relatives à votre commande ci-dessous</p><br>";
            htmlContent += "</div>";


            htmlContent += "<div>";
            htmlContent += "<h2>Adresse de livraison</h2>";
            htmlContent += "<p style='font-size: 14px;'>" + order.getFirstName() + " " + order.getLastName() + "<br>";
            htmlContent +=  order.getAddress() + "<br>";
            htmlContent +=  order.getCity() +  " " + order.getPostalCode() + "<br>";
            htmlContent += recipientEmail + "</p>";
            htmlContent += "</div>";

            htmlContent += "<div style='clear: both'>";
            htmlContent += "<h2>Liste des Produits</h2>";
            htmlContent += "<table style='width: 100%; border-collapse: collapse;'>";

// Entêtes de colonnes
            htmlContent += "<tr style='background-color: #ddd;'>";
            htmlContent += "<th style='padding: 10px; border: 1px solid #aaa;'>Nom du Produit</th>";
            htmlContent += "<th style='padding: 10px; border: 1px solid #aaa;'>Quantité</th>";
            htmlContent += "<th style='padding: 10px; border: 1px solid #aaa;'>Prix</th>";
            // Ajoutez d'autres entêtes de colonnes au besoin
            htmlContent += "</tr>";


            int totalQuantity = 0;
            Double totalPrice = 0.0;

            // Parcours des produits
            for (Object[] product : products) {
                String productName = (String) product[0];
                int quantity = (int) product[2];
                Double price = (Double) product[3];
                Double totalCurrentPrice = price*quantity;
                totalPrice += totalCurrentPrice;
                int totalCurrentQuantity = quantity;
                totalQuantity += totalCurrentQuantity;



                // Ligne pour chaque produit
                htmlContent += "<tr>";
                htmlContent += "<td style='padding: 10px; border: 1px solid #aaa;'>" + productName + "</td>";
                htmlContent += "<td style='padding: 10px; border: 1px solid #aaa;'>" + quantity + "</td>";
                htmlContent += "<td style='padding: 10px; border: 1px solid #aaa;'>" + price*quantity +"&euro;</td>";
                htmlContent += "</tr>";
            }

// Exemple de produits (remplacer par une boucle pour parcourir les produits de la commande)
            htmlContent += "<tr>";
            htmlContent += "<td style='padding: 10px; border: 1px solid #aaa;'>Total</td>";
            htmlContent += "<td style='padding: 10px; border: 1px solid #aaa;'>"+totalQuantity+"</td>";
            htmlContent += "<td style='padding: 10px; border: 1px solid #aaa;'>"+totalPrice+"&euro;</td>";

// Ajoutez d'autres informations sur les produits au besoin
            htmlContent += "</tr>";
            htmlContent += "</table>";
            htmlContent += "</div>";

// Pied de page typique de l'e-mail
            htmlContent += "<div style='background-color: #f5f5f5; padding: 20px;'>";
            htmlContent += "<p style='font-size: 12px; color: #666;'>Cet e-mail a été envoyé par E-Tasty. <br>Pour toute question, veuillez contacter notre service client au 01 23 45 67 89 ou par e-mail à contact@etasty.com.</p>";
            htmlContent += "<p style='font-size: 12px; color: #666;'>Suivez-nous sur <a href='#'>Facebook</a>, <a href='#'>Twitter</a>, et <a href='#'>LinkedIn</a> pour rester informé de nos dernières offres et mises à jour.</p>";
            htmlContent += "<p style='font-size: 12px; color: #666;'>Vous recevez cet e-mail car vous êtes inscrit à notre liste de diffusion. Si vous souhaitez vous désabonner, <a href='#'>cliquez ici</a>.</p>";
            htmlContent += "<p style='font-size: 12px; color: #666;'>Consultez notre <a href='#'>politique de confidentialité</a> pour plus d'informations sur la manière dont nous protégeons et gérons vos données.</p>";
            htmlContent += "<p style='font-size: 12px; color: #666;'>© 2023 E-Tasty. Tous droits réservés.</p>";
            htmlContent += "<p style='font-size: 12px; color: #666;'>Merci pour votre confiance et votre intérêt pour nos services.</p>";
            htmlContent += "</div><br><br><br>";


            htmlContent += "</body></html>";



            message.setContent(htmlContent, "text/html");

            Transport.send(message);

            System.out.println("E-mail envoyé avec succès.");

        } catch (MessagingException e) {
            throw new ServletException("Erreur lors de l'envoi du reçu", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> getProductsByUser(int userID) throws ServletException {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT p.name, p.image, c.quantity, p.price FROM cart c JOIN product p ON c.productID = p.id WHERE c.userID = :userID";
            List<Object[]> rows = session.createNativeQuery(sql)
                    .setParameter("userID", userID)
                    .getResultList();

            return rows;
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des produits : " + e.getMessage());
        }
    }


    public List<Object[]> getUserOrders(int userID) throws ServletException {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT orderID, date, state FROM orders WHERE userID = :userID";
            List<Object[]> rows = session.createNativeQuery(sql)
                    .setParameter("userID", userID)
                    .getResultList();

            return rows;
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des produits : " + e.getMessage());
        }
    }

    public List<Object[]> getAllOrders() throws ServletException {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT orderID, date, state FROM orders";
            List<Object[]> rows = session.createNativeQuery(sql)
                    .getResultList();

            return rows;
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des produits : " + e.getMessage());
        }
    }
}
