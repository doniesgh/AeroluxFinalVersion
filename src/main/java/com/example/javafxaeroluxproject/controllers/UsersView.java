package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.Main;
import com.example.javafxaeroluxproject.models.User;
import com.example.javafxaeroluxproject.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.ComboBox;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class UsersView {
    @FXML
    private Button btn_add_fx;

    @FXML
    private Button btn_back_fx;

    @FXML
    private Button btn_reset_fx;

    @FXML
    private TextField email_fx;

    @FXML
    private TextField first_name_fx;

    @FXML
    private TextField last_name_fx;

   /* @FXML
    private TextField role_fx;*/
    @FXML
    private ComboBox<String> role_fx;
    @FXML
    void showOptions(ActionEvent event) {


    }


    private static final String EMAIL_USERNAME = "emna5722@gmail.com";
    private static final String EMAIL_PASSWORD = "pkqn gvts hlup cmgd";

    public static void sendEmail(String to, String subject, String message) throws MessagingException {
        // Configuration des propriétés pour la connexion SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Utilisez le serveur SMTP approprié
        properties.put("mail.smtp.port", "587"); // Utilisez le port SMTP approprié

        // Création de la session avec authentification
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });


        // Création du message
        Message emailMessage = new MimeMessage(session);
        emailMessage.setFrom(new InternetAddress(EMAIL_USERNAME));
        emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        emailMessage.setSubject(subject);
        emailMessage.setText(message);

        // Envoi du message
        Transport.send(emailMessage);
        System.out.println("Email envoyé avec succès.");
    }



    @FXML
    void add_data(ActionEvent event) {
        UserService us = new UserService() ;
        String password = first_name_fx.getText() + last_name_fx.getText()+"xyzA?";
        User user = new User(first_name_fx.getText(),last_name_fx.getText(),email_fx.getText(),password,role_fx.getValue()) ;
       try{ us.add(user);
           try {
           sendEmail(email_fx.getText(),
                   "informations d'identification",
                   "Bienvenue chez Aorolux ,'\n'Utilisez les informations suivantes pour vous connecter : '\n'" + "identifiant : "+ email_fx.getText()+ "Mot de passe "+ password );
           } catch (MessagingException e) {
               System.out.println("Email non envoyé");
               System.out.println("Email non envoyé : " + e.getMessage());
               e.printStackTrace();
               System.err.println(e.getMessage());
           }
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("succès");
           alert.setContentText("Utilisateur ajouté");
           alert.showAndWait();
           FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/DisplayUsers.fxml"));
           try{
               last_name_fx.getScene().setRoot(fxmlLoader.load());

           }catch (IOException e){
               System.err.println(e.getMessage());
           }

       }
       catch (SQLException e){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setContentText(e.getMessage());
           alert.showAndWait();
       }
    }

    @FXML
    void back(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/DisplayUsers.fxml"));
        try{
            last_name_fx.getScene().setRoot(fxmlLoader.load());

        }catch (IOException e){
            System.err.println(e.getMessage());
        }


    }

    @FXML
    void reset_data(ActionEvent event) {
        first_name_fx.setText("");
        last_name_fx.setText("");
        email_fx.setText("");
       role_fx.getSelectionModel().clearSelection();

    }
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("admin", "agence", "compagnie");
        role_fx.setItems(options);
    }

}
