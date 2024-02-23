package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.Main;
import com.example.javafxaeroluxproject.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.SQLException;

public class NewPassword {
UserService us = new UserService();
    @FXML
    private Button btn_change_password;

    @FXML
    private TextField new_password_confirmation_input;

    @FXML
    private TextField new_password_input;


    @FXML
    private StackPane passwordConfirmation_txt;

  private String userEmail ;
    public void setUserEmail(String email) {

        System.out.println("Email transféré : "+ email);
        userEmail=email ;
    }




    @FXML
    void changePassword(ActionEvent event) {
          // ForgotPasswordEmail f = new ForgotPasswordEmail();
         //  String userEmail = f.getUserEmail();

           System.out.println("Le mail transmis est : " + userEmail);
        if (new_password_input.getText().equals(new_password_confirmation_input
                .getText())  && userEmail != null ){
            System.out.println("Les mots de passe sont identiques et userEmail n'est pas null");
            try{
                us.updateUserPassword(userEmail,new_password_confirmation_input.getText());
                System.out.println("Mot de passe modifié !");
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/DisplayUsers.fxml"));
                try {
                    passwordConfirmation_txt.getScene().setRoot(fxmlLoader.load()); //Display profile

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }catch (SQLException e){
                System.out.println("Erreur de modification mot de passe");
                System.err.println(e.getMessage());
            }


        }else {
            System.out.println("Les mots de passe ne sont pas identiques ");

            passwordConfirmation_txt.setVisible(true);
        }

    }

}
