package com.example.javafxaeroluxproject.controllers;
import com.example.javafxaeroluxproject.models.Reservation;
import com.example.javafxaeroluxproject.services.ReservationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.O;

public class ReservationController {


    @FXML
    private TableView<Reservation> reservation_table;

    @FXML
    private TableColumn<Reservation, String> agency_name;

    @FXML
    private TableColumn<Reservation, Integer> id_reservation;

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
            id_reservation.setCellValueFactory(new PropertyValueFactory<>("id"));
            agency_name.setCellValueFactory(new PropertyValueFactory<>("agency_name"));
            id_trip.setCellValueFactory(new PropertyValueFactory<>("trip_id"));
            nb_seat.setCellValueFactory(new PropertyValueFactory<>("nb_seat"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            reservation_date.setCellValueFactory(new PropertyValueFactory<>("reservation_date"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            action.setCellFactory(column -> {
                return new TableCell<Reservation, Void>() {
                    private final Button acceptButton = new Button("Accept");
                    private final Button refuseButton = new Button("Refuse");
                    {
                        acceptButton.getStyleClass().add("accept-button");
                        refuseButton.getStyleClass().add("refuse-button");
                        acceptButton.setOnAction(event -> {
                            Reservation reservation = getTableView().getItems().get(getIndex());
                            try {
                                reservationService.updateStatusToAccepted(reservation.getId());
                                initialize();
                            } catch (SQLException e) {
                                System.err.println("Error updating reservation: " + e.getMessage());
                            }
                        });


                        refuseButton.setOnAction(event -> {
                            Reservation reservation = getTableView().getItems().get(getIndex());
                            try {
                                reservationService.updateStatusToRejected(reservation.getId());
                                initialize();
                            } catch (SQLException e) {
                                System.err.println("Error updating reservation: " + e.getMessage());
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(new HBox(acceptButton, refuseButton));
                        }
                    }
                };
            });



        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


}
