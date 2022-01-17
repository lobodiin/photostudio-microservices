package com.photostudio.servicestudio.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "studio")
public final class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String studioName;
    private Integer price;

    public Studio() {
    }

    public Studio(String studioName, Integer price) {
        this.studioName = studioName;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
