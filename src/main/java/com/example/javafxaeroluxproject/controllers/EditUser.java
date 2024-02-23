package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.Main;
import com.example.javafxaeroluxproject.models.User;
import com.example.javafxaeroluxproject.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.sql.SQLException;

public class EditUser {

    @FXML
    private Button btn_back_fx;

    @FXML
    private Button btn_reset_fx;
    @FXML
    private Button btn_edit_user;

    @FXML
    private TextField email_fx;

    @FXML
    private TextField first_name_fx;

    @FXML
    private TextField last_name_fx;

    @FXML
    private TextField role_fx;

    @FXML
    void back(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/DisplayUsers.fxml"));
        try {
            last_name_fx.getScene().setRoot(fxmlLoader.load());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void reset_data(ActionEvent event) {
        UserService userService = new UserService() ;
        try {
            User user = userService.getUserById(userId);
            first_name_fx.setText(user.getFirst_name());
            last_name_fx.setText(user.getLast_name());
            email_fx.setText(user.getEmail());
            role_fx.setText(user.getRole());

        }catch (SQLException e ){
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void edit_user(ActionEvent event) {
        UserService userService = new UserService() ;
        try {
            User user = userService.getUserById(userId);
            user.setFirst_name(first_name_fx.getText());
            user.setLast_name(last_name_fx.getText());
            user.setEmail(email_fx.getText());
            user.setRole(role_fx.getText());
            userService.update(user);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/DisplayUsers.fxml"));
            try {
                last_name_fx.getScene().setRoot(fxmlLoader.load());

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }catch (SQLException e ){
            System.err.println(e.getMessage());
        }

    }
    private int userId;

    public void initData(int userId) {
        this.userId = userId;
        // Vous pouvez utiliser cet ID pour effectuer des opérations spécifiques dans votre interface d'édition
        System.out.println("Initialisation avec l'ID de l'utilisateur : " + userId);

    }
    @FXML
    public void initializeUserData() {
       if (userId != 0) {
            UserService userService = new UserService();
            try {
                System.out.println("Correct ID");
                User user = userService.getUserById(userId);
                first_name_fx.setText(user.getFirst_name());
                last_name_fx.setText(user.getLast_name());
                email_fx.setText(user.getEmail());
                role_fx.setText(user.getRole());
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("Incorrect ID");
        }
    }
    @FXML
    void initialize() {

        if (userId != 0) {
            UserService userService = new UserService();
            try {
                System.out.println("Id correct");
                User user = userService.getUserById(userId);
                first_name_fx.setText(user.getFirst_name());
                last_name_fx.setText("z");
                email_fx.setText(user.getEmail());
                role_fx.setText(user.getRole());

            } catch (SQLException e) {

                System.err.println(e.getMessage());

            }

        }else {

            System.out.println("id non correct");

        }
    }

}

