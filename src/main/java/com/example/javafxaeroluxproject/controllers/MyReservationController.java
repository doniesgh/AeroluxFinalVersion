package com.example.javafxaeroluxproject.controllers;
import com.example.javafxaeroluxproject.models.Reservation;
import com.example.javafxaeroluxproject.services.ReservationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MyReservationController {

    @FXML
    private TableView<Reservation> reservation_table;

    @FXML
    private TableColumn<Reservation, String> agency_name;



    @FXML
    private TableColumn<Reservation, Integer> id_trip;

    @FXML
    private TableColumn<Reservation, Integer> nb_seat;

    @FXML
    private TableColumn<Reservation, Float> price;

    @FXML
    private TableColumn<Reservation, Date> reservation_date;

    @FXML
    private TableColumn<Reservation, String> status;
    @FXML
    private TableColumn action;

    private ReservationService reservationService = new ReservationService();

    @FXML
    void initialize() {
        ReservationService reservationService1 = new ReservationService();
        try {
            List<Reservation> reservations = reservationService1.recuperer();
            ObservableList<Reservation> observableList = FXCollections.observableList(reservations);
            reservation_table.setItems(observableList);

            // Set cell value factories for each column
            agency_name.setCellValueFactory(new PropertyValueFactory<>("agency_name"));
            id_trip.setCellValueFactory(new PropertyValueFactory<>("trip_id"));
            nb_seat.setCellValueFactory(new PropertyValueFactory<>("nb_seat"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            reservation_date.setCellValueFactory(new PropertyValueFactory<>("reservation_date"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            action.setCellFactory(column -> {
                return new TableCell<Reservation, Void>() {
                    private final Button viewButton = new Button("View");
                    private final Button deleteButton = new Button("Delete");

                    {
                        viewButton.getStyleClass().add("view-button");
                        deleteButton.getStyleClass().add("delete-button");

                        viewButton.setOnAction(event -> {
                            Reservation reservation = getTableView().getItems().get(getIndex());
                            Stage popupStage = new Stage();
                            popupStage.initModality(Modality.APPLICATION_MODAL);
                            popupStage.setTitle("Reservation Details");
                            VBox vbox = new VBox();
                            vbox.setAlignment(Pos.CENTER);
                            vbox.setSpacing(10);
                            Label idLabel = new Label("ID: " + reservation.getId());
                            Label agencyLabel = new Label("Agency: " + reservation.getAgency_name());
                            Label tripIdLabel = new Label("Trip ID: " + reservation.getTrip_id());
                            Label seatLabel = new Label("Number of Seats: " + reservation.getNb_seat());
                            Label priceLabel = new Label("Price: " + reservation.getPrice());
                            Label dateLabel = new Label("Reservation Date: " + reservation.getReservation_date());
                            Label statusLabel = new Label("Status: " + reservation.getStatus());
                            vbox.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10px;");
                            vbox.getChildren().addAll(idLabel, agencyLabel, tripIdLabel, seatLabel, priceLabel, dateLabel, statusLabel);
                            Scene scene = new Scene(vbox, 300, 200);
                            popupStage.setScene(scene);
                            popupStage.show();
                        });


                        deleteButton.setOnAction(event -> {
                            Reservation reservation = getTableView().getItems().get(getIndex());
                            try {
                                reservationService.supprimer(reservation.getId());
                                initialize();
                            } catch (SQLException e) {
                                System.err.println("Error deleting reservation: " + e.getMessage());
                            }
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(new HBox(viewButton, deleteButton));
                        }
                    }
                };
            });
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}
