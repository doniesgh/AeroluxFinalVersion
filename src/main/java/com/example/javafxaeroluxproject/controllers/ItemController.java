package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.models.Vol;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

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
}
