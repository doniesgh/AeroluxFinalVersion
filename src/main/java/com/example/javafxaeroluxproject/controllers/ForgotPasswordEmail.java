package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.Main;
import com.example.javafxaeroluxproject.models.User;
import com.example.javafxaeroluxproject.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


import java.io.IOException;



import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;


public class ForgotPasswordEmail {
    private static final String API_KEY = "fb12e16d";
    private static final String API_SECRET = "thSA2sBsqkfke1Vr";

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_change_password;

    @FXML
    private TextField fx_user_email;
    @FXML
    private TextField fx_code_input;

    @FXML
    private Text fx_incorrect_code_msg;



    @FXML
    private StackPane fx_incorrect_mail_msg;
    @FXML
    private StackPane fx_sending_code_msg;
    @FXML
    private StackPane fx_write_code_msg;


    @FXML
    private Button btn_verify_code;


   private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    @FXML
    void backToAuth(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/authentification.fxml"));
        try {
            fx_user_email.getScene().setRoot(fxmlLoader.load());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }











    public static String generateRandomString() {
        return UUID.randomUUID().toString();
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
    private String code = generateRandomString().substring(0,5) ;

    public boolean emailExist(String email){
         UserService us = new UserService();
         boolean trouve = false ;

        try{
            List<User> listOfUsers = us.retrieve() ;
            for (User user: listOfUsers
            ) {

                if(email.equals(user.getEmail() ) ) {
                    System.out.println("Email existant");
                    trouve = true ;

                    break;
                }else {
                    System.out.println("Authentification data invalide" + user.getEmail());
                }
            }

        }catch (
                SQLException e){
            System.err.println(e.getMessage());
        }








        return trouve;
    }
    @FXML
    void sendLink(ActionEvent event) {

       if( emailExist(fx_user_email.getText())) {
           try {

               sendEmail(fx_user_email.getText(), "Code réinitialisation mot de passe  ", code);
               System.out.println("Email envoyé");
               fx_incorrect_mail_msg.setVisible(false);

               fx_user_email.setVisible(false);
               fx_sending_code_msg.setVisible(false);
               fx_code_input.setVisible(true);
               fx_write_code_msg.setVisible(true);
               btn_verify_code.setVisible(true);
               btn_change_password.setVisible(false);

           } catch (MessagingException e) {
               System.out.println("Email non envoyé");
               System.err.println(e.getMessage());
           }
       }else {
           fx_incorrect_mail_msg.setVisible(true);
       }


    }
    void changePassword(){
        System.out.println("Fonction changmement mot de passe déclenché");
        this.userEmail=fx_user_email.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/newPassword.fxml"));
        try {
            fx_user_email.getScene().setRoot(fxmlLoader.load());
            NewPassword np = fxmlLoader.getController();
            np.setUserEmail(fx_user_email.getText());


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void check_code(ActionEvent event) {
        if (fx_code_input.getText().equals(code)) {
            System.out.println("succè : Le code saisi est : " + fx_code_input.getText() + "et le code est : " + code);
            fx_incorrect_code_msg.setVisible(false);

            changePassword();
        } else {
            fx_incorrect_code_msg.setVisible(true);
            System.out.println("échec : Le code saisi est : " + fx_code_input.getText() + "et le code est : " + code);


        }
    }

    @FXML
    void initialize() {
        fx_code_input.setVisible(false);
        btn_verify_code.setVisible(false);


    }
}
