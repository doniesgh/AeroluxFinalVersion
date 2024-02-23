package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.Main;
import com.example.javafxaeroluxproject.models.User;
import com.example.javafxaeroluxproject.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class Authentification {
    @FXML
    private PasswordField fx_user_password;
    @FXML
    private Button fx_btn_connect;
    @FXML
    private StackPane fx_incorrect_credentials;
    @FXML
    private Label fx_forgot_password;

    @FXML
    private TextField fx_user_email;

  /*  @FXML
    private TextField fx_user_password;
*/

   private UserService us = new UserService();

    @FXML
    void changePassword(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/forgotPasswordEmail.fxml"));
        try {
            fx_user_email.getScene().setRoot(fxmlLoader.load());
            System.out.println("Redirection vers l'interface mot de passe oublié");

        } catch (IOException e) {
            e.printStackTrace();

            System.err.println(e.getMessage());
            System.out.println("Echec Redirection vers l'interface mot de passe oublié");

        }
    }

    @FXML
    void connect(ActionEvent event) {
        System.out.println("email saisi : " + fx_user_email.getText() + "Mot de passe saisi : "+ fx_user_password.getText());
        boolean validCredentials = false;

        try{
        List<User> listOfUsers = us.retrieve() ;
        for (User user: listOfUsers
        ) {
                 if(fx_user_email.getText().equals(user.getEmail()) && fx_user_password.getText().equals(user.getPassword())){
                     System.out.println("Authentification affectuée avec succée");
                     validCredentials = true ;
                     if(user.getRole().equals("admin")) {
                         FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/homePageAdmin.fxml"));
                         try {
                             fx_user_email.getScene().setRoot(fxmlLoader.load());

                         } catch (IOException e) {
                             System.err.println(e.getMessage());
                         }
                     }else if (user.getRole().equals("agence")) {
                         FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/homePageAgency.fxml"));
                         try {
                             fx_user_email.getScene().setRoot(fxmlLoader.load());

                         } catch (IOException e) {
                             System.err.println(e.getMessage());
                         }
                     }else {
                             FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/homePageCompany.fxml"));
                             try {
                                 fx_user_email.getScene().setRoot(fxmlLoader.load());

                             } catch (IOException e) {
                                 System.err.println(e.getMessage());
                             }

                     }
                     break;
                 }else {
                     System.out.println("Authentification data invalide" + user.getEmail());
                 }

        }

}catch (
    SQLException e){
        System.err.println(e.getMessage());
    }
        fx_incorrect_credentials.setVisible(! validCredentials);

    }

}
