package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomePageAdmin {

    @FXML
    private Button btn_see_agency_list;

    @FXML
    private Button fx_btn_deconnect;

    @FXML
    private Button fx_btn_see_profile;

    @FXML
    private Button fx_btn_users_list;

    @FXML
    void AgencyList(ActionEvent event) {

    }

    @FXML
    void UsersList(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/DisplayUsers.fxml"));
        try {
            fx_btn_users_list.getScene().setRoot(fxmlLoader.load());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void deconnect(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/authentification.fxml"));
        try {
            fx_btn_deconnect.getScene().setRoot(fxmlLoader.load());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void seeProfile(ActionEvent event) {

    }

}
