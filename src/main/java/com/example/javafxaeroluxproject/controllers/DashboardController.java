package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.models.Vol;
import com.example.javafxaeroluxproject.services.VolService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DashboardController {
    @FXML
    private Pane contentArea;

    @FXML
    void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxaeroluxproject/clientHome.fxml"));
            Parent homeClient = loader.load();

            contentArea.getChildren().add(homeClient);
        } catch (IOException e) {
            e.printStackTrace();
    }
    }
    public void myhome(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/javafxaeroluxproject/clientHome.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
    public void mytrip(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/javafxaeroluxproject/mytrip.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
    public void myreservation(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/javafxaeroluxproject/myreservation.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
    public void myprofil(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/javafxaeroluxproject/myprofil.fxml"));
        contentArea.getChildren().clear();
        contentArea.getChildren().add(fxml);
    }
}

