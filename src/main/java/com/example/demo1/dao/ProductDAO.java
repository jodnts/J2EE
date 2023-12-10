package com.example.demo1.dao;

import com.example.demo1.model.Product;
import com.example.demo1.model.User;
import com.mysql.cj.Query;
import jakarta.servlet.ServletException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.query.NativeQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Collections;
import java.util.List;

public class ProductDAO {
    private final SessionFactory sessionFactory;

    public ProductDAO() {
        // Configuration Hibernate à partir du fichier hibernate.cfg.xml
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void addProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Integer maxId = (Integer) session.createNativeQuery("SELECT MAX(id) FROM product")
                    .uniqueResult();
            int newId = (maxId != null) ? maxId + 1 : 1; // Si maxId est null, démarrez à 1

            product.setId(newId); // Définissez le nouvel ID dans l'objet Product

            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    String sql = "INSERT INTO product (id, name, price, image, userID) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, newId);
                        statement.setString(2, product.getName());
                        statement.setDouble(3, product.getPrice());
                        statement.setString(4, product.getImage());
                        statement.setInt(5, product.getUserId());
                        statement.executeUpdate();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object[]> getAllProducts() throws ServletException {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT p.id, p.name, p.price, p.image, u.mail " +
                    "FROM product p " +
                    "JOIN user u ON p.userID = u.id";
            List<Object[]> rows = session.createNativeQuery(sql).getResultList();

            return rows;
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des produits : " + e.getMessage());
        }
    }

    public List<Object[]> getAllProductsManage(int userId) throws ServletException {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT p.id, p.name, p.price, p.image, u.mail " +
                        "FROM product p " +
                        "JOIN user u ON p.userID = u.id " +
                        "WHERE u.id = :userId";

            List<Object[]> rows = session.createNativeQuery(sql)
                    .setParameter("userId", userId) // Lier le paramètre userId
                    .getResultList();

            return rows;
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération des produits : " + e.getMessage());
        }
    }


    public void deleteProduct(int productId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // Supprimer le produit en fonction de son ID
                session.createNativeQuery("DELETE FROM product WHERE id = :productId")
                        .setParameter("productId", productId)
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

    public Product getProductById(int productId) throws ServletException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, productId);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la récupération du produit : " + e.getMessage());
        }
    }
}
