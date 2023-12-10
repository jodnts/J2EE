package com.example.demo1.dao;

import jakarta.servlet.ServletException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

public class CartDAO {
    private final SessionFactory sessionFactory;

    public CartDAO() {
        // Configuration Hibernate à partir du fichier hibernate.cfg.xml
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }


    /*public CartItem getCartItemByProductId(int productId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM cart WHERE productId = :productId";
            return session.createQuery(hql, CartItem.class)
                    .setParameter("productId", productId)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/

    public void addToCart(int productId, int userId, int quantity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            // Vérifiez si le produit existe déjà dans le panier de l'utilisateur
            Long existingCartItem = (Long) session.createNativeQuery("SELECT COUNT(*) FROM cart WHERE productID = :productId AND userID = :userId")
                    .setParameter("productId", productId)
                    .setParameter("userId", userId)
                    .uniqueResult();

            if (existingCartItem != null && existingCartItem > 0) {
                // Le produit existe déjà dans le panier de l'utilisateur, mettez à jour la quantité
                session.createNativeQuery("UPDATE cart SET quantity = quantity + :quantity WHERE productID = :productId AND userID = :userId")
                        .setParameter("productId", productId)
                        .setParameter("userId", userId)
                        .setParameter("quantity", quantity)
                        .executeUpdate();
            } else {
                // Le produit n'existe pas dans le panier, ajoutez-le
                Integer maxCartId = (Integer) session.createNativeQuery("SELECT MAX(cartId) FROM cart")
                        .uniqueResult();
                int newCartId = (maxCartId != null) ? maxCartId + 1 : 1;

                session.doWork(new Work() {
                    @Override
                    public void execute(Connection connection) throws SQLException {
                        String sql = "INSERT INTO cart (cartID, productID, quantity, userID) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement statement = connection.prepareStatement(sql)) {
                            statement.setInt(1, newCartId);
                            statement.setInt(2, productId);
                            statement.setInt(3, quantity);
                            statement.setInt(4, userId);
                            statement.executeUpdate();
                        }
                    }
                });
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void removeFromCart(int productId, boolean removeAll, int userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            // Récupérer l'ID du panier pour le produit spécifié
            Integer cartId = (Integer) session.createNativeQuery("SELECT cartId FROM cart WHERE productID = :productId AND userID = :userId")
                    .setParameter("productId", productId)
                    .setParameter("userId", userId)
                    .uniqueResult();

            if (cartId != null) {
                if (removeAll || (getCartItemQuantity(cartId) == 1)) {
                    // Supprimer complètement l'article du panier si removeAll est true ou si la quantité est égale à 1
                    session.createNativeQuery("DELETE FROM cart WHERE cartId = :cartId")
                            .setParameter("cartId", cartId)
                            .executeUpdate();
                } else {
                    // Diminuer la quantité de 1
                    session.createNativeQuery("UPDATE cart SET quantity = quantity - 1 WHERE cartId = :cartId")
                            .setParameter("cartId", cartId)
                            .executeUpdate();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getCartItemQuantity(int cartId) {
        try (Session session = sessionFactory.openSession()) {
            // Récupérer la quantité actuelle de l'article dans le panier
            return ((Number) session.createNativeQuery("SELECT quantity FROM cart WHERE cartId = :cartId")
                    .setParameter("cartId", cartId)
                    .uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Object[]> getAllCartItem(int userId) throws ServletException {
        try (Session session = sessionFactory.openSession()) {
            // Modifiez la requête SQL pour inclure la jointure avec la table product
            String sql = "SELECT c.productID, c.quantity, p.name, p.price, p.image " +
                    "FROM cart c " +
                    "JOIN product p ON c.productID = p.id "+
                    "WHERE c.userID = :userId";
            List<Object[]> rows = session.createNativeQuery(sql).setParameter("userId", userId).getResultList();
            System.out.println("Number of cart items fetched: " + rows.size());
            return rows;
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des produits : " + e.getMessage());
        }
    }

}

