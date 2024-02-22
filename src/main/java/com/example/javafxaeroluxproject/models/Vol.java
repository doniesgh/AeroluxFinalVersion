package com.example.javafxaeroluxproject.models;

import java.sql.Date;
import java.util.Arrays;
public class Vol {
    private byte[] imageData;
    private Integer id;
    private Date dateDepart;
    private Date dateArrivee;
    private String description;
    private String numVol;
    private Integer piloteId;
    private String lieuArrivee;
    private String lieuDepart;
    private Integer placeDispo;
    private Float price;

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Vol(String numVol, String lieuDepart, String lieuArrivee, Integer placeDispo, String description,
               Date dateArrivee, Date dateDepart, Integer piloteId , Float price , byte[] imageData) {
        this.numVol = numVol;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.placeDispo = placeDispo;
        this.description = description;
        this.piloteId = piloteId;
        this.price = price;
        this.imageData = imageData;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
    }
    public Vol() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumVol() {
        return numVol;
    }

    public void setNumVol(String numVol) {
        this.numVol = numVol;
    }

    public Integer getPiloteId() {
        return piloteId;
    }

    public void setPiloteId(Integer piloteId) {
        this.piloteId = piloteId;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }


    public Integer getPlaceDispo() {
        return placeDispo;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setPlaceDispo(Integer placeDispo) {
        this.placeDispo = placeDispo;
    }
    @Override
    public String toString() {

        String imageStr = (imageData != null) ? Arrays.toString(imageData) : "null";

        return "Vol{" +
                "numVol='" + numVol + '\'' +
                ", lieuArrivee='" + lieuArrivee + '\'' +
                ", piloteId=" + piloteId +
                ", lieuDepart='" + lieuDepart + '\'' +
                ", placeDispo=" + placeDispo +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dateArrive=" + dateArrivee +
                ", dateDepart=" + dateDepart +
                ", image=" + imageStr +
                '}';
    }

}
