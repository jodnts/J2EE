package com.example.demo1.dao;

import com.example.demo1.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jakarta.servlet.ServletException;
import java.util.List;

public class UserDAO {

    private final SessionFactory sessionFactory;

    public UserDAO() {
        // Configuration Hibernate à partir du fichier hibernate.cfg.xml
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public User getUser(String mail, String password) throws ServletException {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT id, mail, password, role FROM user WHERE mail = :mail AND password = :password";
            List<Object[]> rows = session.createNativeQuery(sql)
                    .setParameter("mail", mail)
                    .setParameter("password", password)
                    .getResultList();

            if (!rows.isEmpty()) {
                Object[] row = rows.get(0);
                int id = (int) row[0];
                String role = (String) row[3];
                return new User(id, mail, password, role);
            }
            return null;
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'authentification : " + e.getMessage());
        }
    }


}
