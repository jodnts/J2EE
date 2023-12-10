package com.example.demo1.dao;

import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ModoDAO {

    private final SessionFactory sessionFactory;

    public ModoDAO() {
        // Configuration Hibernate à partir du fichier hibernate.cfg.xml
        this.sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Object[] getModoByUserID(int userID) {
        try (Session session = sessionFactory.openSession()) {
            String queryString = "SELECT * FROM modo WHERE userID = :userID";
            return (Object[]) session.createNativeQuery(queryString)
                    .setParameter("userID", userID)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Object[]> getAllModos() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String sql = "SELECT m.modoID, u.mail, m.rights " +
                    "FROM modo m " +
                    "JOIN user u ON m.userID = u.id";
            Query query = session.createNativeQuery(sql);
            List<Object[]> rows = query.getResultList();
            System.out.println("Number of modo items fetched: " + rows.size());

            transaction.commit();
            return rows;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    public void changeRights(int modoID, String rights) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            String queryString = "UPDATE modo SET rights = :rights WHERE modoID = :modoID";
            int updatedEntities = session.createNativeQuery(queryString)
                    .setParameter("rights", rights)
                    .setParameter("modoID", modoID)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteModo(int modoID, String mail) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                // Supprimer le produit en fonction de son ID
                session.createNativeQuery("DELETE FROM modo WHERE modoID = :modoID")
                        .setParameter("modoID", modoID)
                        .executeUpdate();

                session.createNativeQuery("DELETE FROM user WHERE mail = :mail and role = 'modo'")
                        .setParameter("mail", mail)
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

    public void addModo(String mail) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                Integer userID = getUserIdByMail(session, mail);
                System.out.println(userID);
                if (userID != null) {
                    // Obtenir le prochain modoID en trouvant le maximum actuel et en l'incrémentant de 1
                    Integer maxId = (Integer) session.createNativeQuery("SELECT MAX(modoID) FROM modo")
                            .uniqueResult();
                    int newId = (maxId != null) ? maxId + 1 : 1; // Si maxId est null, démarrez à 1

                    // Insérer un nouveau modérateur dans la table 'modo' avec le nouveau modoID
                    session.createNativeQuery("INSERT INTO modo (modoID, userID, rights) VALUES (?, ?, 'oui')")
                            .setParameter(1, newId)
                            .setParameter(2, userID)
                            .executeUpdate();

                    // Mettre à jour le rôle de l'utilisateur dans la table 'user'
                    session.createNativeQuery("UPDATE user SET role='modo' WHERE id = :userID")
                            .setParameter("userID", userID)
                            .executeUpdate();

                    transaction.commit();
                } else {
                    throw new IllegalArgumentException("Le mail spécifié ne correspond à aucun utilisateur.");
                }
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }


    public Integer getUserIdByMail(Session session, String mail) {
        try {
            String sql = "SELECT id FROM User WHERE mail = :mail AND role= 'user'";
            return (Integer) session.createNativeQuery(sql)
                    .setParameter("mail", mail)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions ou renvoyer une valeur par défaut
            return null;
        }
    }


}
