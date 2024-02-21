package com.example.javafxaeroluxproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btn_reservations;
    public void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToReservation(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("landing.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*@FXML*/
    /*void Reservation(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("landing.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.show();
    }*/
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource()==btn_reservations){
            stage = (Stage) btn_reservations.getScene().getWindow();
            root=FXMLLoader.load((getClass().getResource("landing.fxml")));
        }
    }
    /*@FXML
    void pageReservation(ActionEvent event){
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("landing.fxml"));
        try{
            Parent root = loader1.load();
            SceneController controller= loader1.getController();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }*/
}
