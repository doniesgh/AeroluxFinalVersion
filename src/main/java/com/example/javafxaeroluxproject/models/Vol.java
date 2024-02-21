package com.example.javafxaeroluxproject.models;

import java.sql.Date;

public class Vol {

        private Integer id;
        private Date dateDepart;

        private Date dateArrive;
        private String description;

    private String numVol;
    private Integer piloteId;

    private String lieuArrivee;
    private String lieuDepart;
    private Integer placeDispo;
    public Vol(Integer id, Date dateDepart, Date dateArrive, String description, String numVol, Integer piloteId, String lieuArrivee, String lieuDepart, Integer placeDispo) {
        this.id = id;
        this.dateDepart = dateDepart;
        this.dateArrive = dateArrive;
        this.description = description;
        this.numVol = numVol;
        this.piloteId = piloteId;
        this.lieuArrivee = lieuArrivee;
        this.lieuDepart = lieuDepart;
        this.placeDispo = placeDispo;
    }
    public Vol() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(Date dateArrive) {
        this.dateArrive = dateArrive;
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

    public void setPlaceDispo(Integer placeDispo) {
        this.placeDispo = placeDispo;
    }
    @Override
    public String toString() {
        return "Vol{" +
                "numVol='" + numVol + '\'' +
                ", lieuArrivee='" + lieuArrivee + '\'' +
                ", piloteId=" + piloteId +
                ", lieuDepart='" + lieuDepart + '\'' +
                ", placeDispo=" + placeDispo +
                ", description='" + description + '\'' +
                ", dateArrive=" + dateArrive +
                ", dateDepart=" + dateDepart +
                '}';
    }


}
