package com.example.javafxaeroluxproject;

import com.example.javafxaeroluxproject.models.Reservation;
import com.example.javafxaeroluxproject.models.Vol;
import com.example.javafxaeroluxproject.services.ReservationService;
import com.example.javafxaeroluxproject.services.VolService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Vol volToAdd = new Vol();
        volToAdd.setNumVol("ABC123");
        volToAdd.setLieuArrivee("Destination");
        volToAdd.setPiloteId(1);
        volToAdd.setLieuDepart("Departure");
        volToAdd.setPlaceDispo(100);
        volToAdd.setDescription("Sample description");
        volToAdd.setDateArrive(new Date(124 - 1900, 1 - 1, 18));
        volToAdd.setDateDepart(new Date(124 - 1900, 1 - 1, 18));
        try {
            VolService volService = new VolService();

            volService.ajouter(volToAdd);

            List<Vol> vols = volService.recuperer();

            System.out.println("Retrieved vols:");
            for (Vol vol : vols) {
                System.out.println(vol);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while adding or retrieving data: " + e.getMessage());
        }
        Reservation reservationToAdd = new Reservation();
        reservationToAdd.setTrip_id(1);
        reservationToAdd.setAgency_name("Example Agency");
        reservationToAdd.setStatus("Confirmed");
        reservationToAdd.setNb_seat(2);
        reservationToAdd.setPrice(100.00f);
        Date reservationDate = new Date(2024 - 1900, 1, 18);
        reservationToAdd.setReservation_date(reservationDate);

        try {
            ReservationService rs = new ReservationService();
            rs.ajouter(reservationToAdd);
            List<Reservation> reservations = rs.recuperer();
            System.out.println("Retrieved reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while adding reservation: " + e.getMessage());
        }
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
