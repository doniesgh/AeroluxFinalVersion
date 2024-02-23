package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.models.Vol;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ItemController {

    @FXML
    private Label lieuArrivee;

    @FXML
    private Label priceTrip;
    @FXML
    private ImageView imageView;

    public void setVolData(Vol vol) {
        lieuArrivee.setText(vol.getLieuArrivee());
        priceTrip.setText(String.valueOf(vol.getPrice()));

        byte[] imageData = vol.getImageData();
        if (imageData != null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            Image image = new Image(bis);
            imageView.setImage(image);
        }

    }
    @FXML
    private void handleBookAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxaeroluxproject/reservationData.fxml"));
            Parent tripDetailsParent = loader.load();
            TripController tripDetailsController = loader.getController();
            Stage stage = (Stage) lieuArrivee.getScene().getWindow();
            Scene scene = new Scene(tripDetailsParent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
