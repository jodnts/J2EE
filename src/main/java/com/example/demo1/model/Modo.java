package com.example.demo1.model;

import javax.persistence.*;

@Entity
@Table(name = "modo")
public class Modo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modoID")
    private int modoID;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @Column(name = "rights")
    private String rights;

    public Modo() {
        // Constructeur par défaut nécessaire pour Hibernate
    }

    public Modo(User user, String rights) {
        this.user = user;
        this.rights = rights;
    }

    public int getModoID() {
        return modoID;
    }

    public void setModoID(int modoID) {
        this.modoID = modoID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}
