package com.example.demo1.dao;

import com.example.demo1.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignInDAO {

    private final SessionFactory sessionFactory;

    public SignInDAO() {
        // Configuration Hibernate à partir du fichier hibernate.cfg.xml
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    public void registerUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Integer maxId = (Integer) session.createNativeQuery("SELECT MAX(id) FROM user")
                    .uniqueResult();
            int newId = (maxId != null) ? maxId + 1 : 1; // Si maxId est null, démarrez à 1

            user.setId(newId); // Définissez le nouvel ID dans l'objet User

            session.doWork(new Work() {
                @Override
                public void execute(Connection connection) throws SQLException {
                    String sql = "INSERT INTO user (id, mail, password, role) VALUES (?, ?, ?, 'user')";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, newId);
                        statement.setString(2, user.getMail());
                        statement.setString(3, user.getPassword());
                        statement.executeUpdate();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean authenticateUser(String mail, String password) {
        try (Session session = sessionFactory.openSession()) {
            Long count = (Long) session.createNativeQuery("SELECT COUNT(*) FROM user WHERE mail = :mail AND password = :password")
                    .setParameter("mail", mail)
                    .setParameter("password", password)
                    .uniqueResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
