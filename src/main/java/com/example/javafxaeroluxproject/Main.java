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

import java.sql.SQLException;
import java.util.List;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            VolService volService = new VolService();


            List<Vol> vols = volService.recuperer();

            System.out.println("Retrieved vols:");
            for (Vol vol : vols) {
                System.out.println(vol);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while adding or retrieving data: " + e.getMessage());
        }

        try {
            ReservationService rs = new ReservationService();
            List<Reservation> reservations = rs.recuperer();
            System.out.println("Retrieved reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while adding reservation: " + e.getMessage());
        }
        launch( args);

    }

    @Override

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("authentification.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("reservationForm.fxml"));

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
