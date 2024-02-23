package com.example.javafxaeroluxproject.controllers;

import com.example.javafxaeroluxproject.Main;
import com.example.javafxaeroluxproject.models.User;
import com.example.javafxaeroluxproject.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button ;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.event.ActionEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DisplayUsers {

    @FXML
    private TableColumn<User, String> email_col;

    @FXML
    private TableColumn<User, String> first_name_col;

    @FXML
    private Button fx_btn_download;
    @FXML
    private TableColumn<User, String> last_name_col;

    @FXML
    private TableColumn<User, String> role_col;
    @FXML
    private TableColumn <User , Void > actions_col;
    @FXML
    private TableView<User> users_table;
    @FXML
    private Button bt1 = new Button("\u270E") ;
    @FXML
    private Button bt2 = new Button("\u270F")  ;
    @FXML
    private Button  bt3 = new Button("\u2716") ;

    @FXML
    void add_user_btn(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/UsersView.fxml"));
        try {
            users_table.getScene().setRoot(fxmlLoader.load());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    private void loadUsersList() {
        // Charger la liste initiale des utilisateurs
        UserService userService = new UserService();
        try {
            List<User> users = userService.retrieve();
            users_table.setItems(FXCollections.observableList(users));
        } catch (SQLException e) {
            System.err.println("Error loading users list: " + e.getMessage());
        }
    }

    @FXML
    void handleEditButton(User user){
        System.out.println("Edit button clicked for the user id" + user.getId());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/javafxaeroluxproject/editUser.fxml"));
        try{
            users_table.getScene().setRoot(fxmlLoader.load());
            // Passer l'ID au contrôleur editUser
            EditUser editUserController = fxmlLoader.getController();
            editUserController.initData(user.getId());
            editUserController.initializeUserData();// Call this explicitly after initData

        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }



    @FXML
    void handleDeleteButtuon(User user){
        List<User> userList = users_table.getItems();

        UserService us = new UserService();
        try {
            us.delete(user.getId());
            System.out.println("the user with id : "+ user.getId() + "is deleted");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        try{
            us.delete(user.getId());

            loadUsersList();


        }catch (SQLException e){

        }
    }

    @FXML
    void download(ActionEvent event) {
        UserService us = new UserService();
       try {
           List<User> userList = us.retrieve() ;
           try (Workbook workbook = new XSSFWorkbook()) {
               Sheet sheet = workbook.createSheet("Utilisateurs");

               // Ajouter des en-têtes
               Row headerRow = sheet.createRow(0);
               headerRow.createCell(0).setCellValue("Nom");
               headerRow.createCell(1).setCellValue("Email");

               // Remplir les données
               int rowNum = 1;
               for (User user : userList) {
                   Row row = sheet.createRow(rowNum++);
                   row.createCell(0).setCellValue(user.getLast_name());
                   row.createCell(1).setCellValue(user.getEmail());
               }

               // Sauvegarder le fichier Excel
               try (FileOutputStream fileOut = new FileOutputStream("utilisateurs.xlsx")) {
                   workbook.write(fileOut);
               }

               System.out.println("Fichier Excel généré avec succès.");

           } catch (IOException e) {
               e.printStackTrace();
           }

       }catch (SQLException e)
       {
           System.err.println(e.getMessage());
       }

    }


@FXML
    void initialize() {
        try {
            users_table.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
            System.out.println("_______Style appliqué____________");

        }catch (Exception e){


            System.err.println(e.getMessage());
            System.out.println("_______Fin de getMessage____________");

        }

        UserService userService = new UserService() ;
        users_table.getProperties().put("controller",this);
        try {

            List<User> users = userService.retrieve() ;
            ObservableList<User> listeUsers = FXCollections.observableList(users) ;
            users_table.setItems(listeUsers);
            first_name_col.setCellValueFactory(new PropertyValueFactory<>("first_name"));

            last_name_col.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            role_col.setCellValueFactory(new PropertyValueFactory<>("role"));


            bt2.setOnAction(event -> {
                User selectedUser = users_table.getSelectionModel().getSelectedItem();
                handleEditButton(selectedUser);

            });


            bt3.setOnAction(event -> {
                User selectedUser = users_table.getSelectionModel().getSelectedItem();
                handleDeleteButtuon(selectedUser);

            });


             actions_col.setCellFactory(column -> new ButtonCell(bt1,bt2,bt3)) ;





        }catch (SQLException e){
            System.err.println("Error in displaying users");

        }



    }

}




