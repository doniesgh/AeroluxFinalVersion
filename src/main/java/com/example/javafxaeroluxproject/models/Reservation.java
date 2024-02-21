package com.example.javafxaeroluxproject.models;

import javafx.scene.control.Button;

import java.util.Date;

public class Reservation {
    private  int id;
    private int trip_id;
    private String agency_name;
    private String status;
    private int nb_seat;
    private float price;
    private Date reservation_date;
    private Button accept;
    private Button refuse ;

    public Button getAccept() {
        return accept;
    }

    public void setAccept(Button accept) {
        this.accept = accept;
    }

    public Button getRefuse() {
        return refuse;
    }

    public void setRefuse(Button refuse) {
        this.refuse = refuse;
    }

    public Reservation() {

    }
    public Reservation(int id, int trip_id, String agency_name, String status, int nb_seat, float price, Date reservation_date) {
        this.id = id;
        this.trip_id = trip_id;
        this.agency_name = agency_name;
        this.status = status;
        this.nb_seat = nb_seat;
        this.price = price;
        this.reservation_date= reservation_date;

    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNb_seat() {
        return nb_seat;
    }

    public void setNb_seat(int nb_seat) {
        this.nb_seat = nb_seat;
    }

    public Date getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(Date reservation_date) {
        this.reservation_date = reservation_date;
    }
    @Override

    public String toString(){
        return "Reservation : "
                + "Id" + id
                + ", Agence :"+ agency_name
                + ", Nombre de places :" + nb_seat
                + ", Etat :" + status
                + ", Id Vol :" + trip_id
                + ", Prix " + price
                + ", Date de réservation " + reservation_date;

    }


}
