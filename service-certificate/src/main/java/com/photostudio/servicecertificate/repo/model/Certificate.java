package com.photostudio.servicecertificate.repo.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "certificate")
public final class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Integer price;
    private String user_login, studioName;

    public Certificate() {
    }

    public Certificate(Integer price, String user_login, String studioName) {
        this.price = price;
        this.user_login = user_login;
        this.studioName = studioName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }
}

