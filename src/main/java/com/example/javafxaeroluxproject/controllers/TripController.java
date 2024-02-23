package com.example.javafxaeroluxproject.controllers;
import com.example.javafxaeroluxproject.models.Vol;
import com.example.javafxaeroluxproject.services.VolService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TripController {
    @FXML
    private TextField numVolTextField;

    @FXML
    private TextField price;

    @FXML
    private TextField lieuDepartTextField;

    @FXML
    private TextField lieuArriveeTextField;

    @FXML
    private TextField piloteIdTextField;

    @FXML
    private TextField placeDispoTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private DatePicker dateArriveeTextField;

    @FXML
    private DatePicker dateDepartTextField;
    @FXML
    private Pane contentArea;
    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane cardsContainer;
    private VolService volService;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane gridPane;
    private String imagePath;


    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
            imagePath = selectedFile.getAbsolutePath();
        }
    }
/*
    @FXML
    void ajouterVol(ActionEvent event) {
        String numVol = numVolTextField.getText();
        String lieuDepart = lieuDepartTextField.getText();
        String lieuArrivee = lieuArriveeTextField.getText();
        Integer placeDispo = Integer.valueOf(placeDispoTextField.getText());
        String description = descriptionTextField.getText();
        String priceText = price.getText();
        Float priceValue = null;

        try {
            priceValue = Float.parseFloat(priceText);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        LocalDate dateArriveeValue = dateArriveeTextField.getValue();
        LocalDate dateDepartValue = dateDepartTextField.getValue();

        if (dateArriveeValue == null || dateDepartValue == null) {

            return;
        }

        String dateArrivee = dateArriveeValue.toString();
        String dateDepart = dateDepartValue.toString();
        Integer piloteId = Integer.valueOf(piloteIdTextField.getText());
        byte[] imageBytes = null;
        if (imageView.getImage() != null) {
            String imagePath = imageView.getImage().getUrl();
            if (imagePath.startsWith("file:/")) {
                imagePath = imagePath.substring(6);
            }
            imageBytes = readImageAsBytes(imagePath);
        }
        Vol vol = new Vol(numVol, lieuDepart, lieuArrivee, placeDispo, description, dateArrivee, dateDepart, piloteId, priceValue, imageBytes);
        try {
            volService.ajouter(vol);
            fermerPopup();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    private byte[] readImageAsBytes(String imagePath) {
        try {
            String decodedImagePath = URLDecoder.decode(imagePath, StandardCharsets.UTF_8);
            File imageFile = new File(decodedImagePath);
            return Files.readAllBytes(imageFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

@FXML
void ajouterVol(ActionEvent event) {
    String numVol = numVolTextField.getText();
    String lieuDepart = lieuDepartTextField.getText();
    String lieuArrivee = lieuArriveeTextField.getText();
    Integer placeDispo = Integer.valueOf(placeDispoTextField.getText());
    String description = descriptionTextField.getText();
    String priceText = price.getText();
    Float priceValue = null;

    try {
        priceValue = Float.parseFloat(priceText);
    } catch (NumberFormatException e) {
        e.printStackTrace();
        return;
    }

    LocalDate dateArriveeValue = dateArriveeTextField.getValue();
    LocalDate dateDepartValue = dateDepartTextField.getValue();

    if (dateArriveeValue == null || dateDepartValue == null) {
        return;
    }

    java.sql.Date dateArrivee = java.sql.Date.valueOf(dateArriveeValue);
    java.sql.Date dateDepart = java.sql.Date.valueOf(dateDepartValue);

    Integer piloteId = Integer.valueOf(piloteIdTextField.getText());
    byte[] imageBytes = null;

    if (imageView.getImage() != null) {
        String imagePath = imageView.getImage().getUrl();
        if (imagePath.startsWith("file:/")) {
            imagePath = imagePath.substring(6);
        }
        imageBytes = readImageAsBytes(imagePath);
    }

    Vol vol = new Vol(numVol, lieuDepart, lieuArrivee, placeDispo, description, dateArrivee, dateDepart, piloteId, priceValue, imageBytes);

    try {
        volService.ajouter(vol);
        fermerPopup();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @FXML
    void fermerPopup() {
        numVolTextField.getScene().getWindow().hide();
    }

    @FXML
    void afficherPopupAjoutVol(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxaeroluxproject/addTrip.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add Trip");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
     /*   volService = new VolService();
        gridPane = new GridPane();
        scrollPane.setContent(gridPane);
        loadVolData();*/
        volService = new VolService();
        gridPane = new GridPane();
        if (scrollPane != null) {
            scrollPane.setContent(gridPane);
        } else {
            System.err.println("ScrollPane is null. Check FXML configuration.");
        }
        loadVolData();
    }

    private void loadVolData() {
        try {
            List<Vol> volList = volService.recuperer();
            int column = 0;
            int row = 0;
            for (Vol vol : volList) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxaeroluxproject/item.fxml"));
                AnchorPane itemPane = loader.load();

                ItemController itemController = loader.getController();
                itemController.setVolData(vol);

                gridPane.add(itemPane, column, row);

                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    private Vol selectedVol;


/*
    @FXML
    private void bookButtonClicked() {
        if (selectedVol != null) {
            try {
                // Load the TripInformation.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/path/to/TripInformation.fxml"));
                Pane tripInfoPane = loader.load();

                // Get the controller associated with the new scene
                TripController tripInfoController = loader.getController();

                // Pass the selected trip information to the controller
                tripInfoController.setTripInfo(selectedVol.getDescription() + " - " + selectedVol.getLieuDepart() + " to " + selectedVol.getLieuArrivee() + ", Price: " + selectedVol.getPrice()); // Modify this as per your requirements

                // Show the new scene
                Stage stage = new Stage();
                stage.setScene(new Scene(tripInfoPane));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Handle case where no trip is selected
            System.out.println("Please select a trip before booking.");
        }
    }*/
@FXML
private Label tripInfoLabel;
    public void setTripInfo(String tripInfo) {
        tripInfoLabel.setText(tripInfo);
    }
@FXML
private void bookButtonClicked() {
    if (selectedVol != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxaeroluxproject/reservationData.fxml"));
            Pane tripInfoPane = loader.load();
            TripController tripInfoController = loader.getController();
            tripInfoController.setTripInfo(selectedVol.getDescription() + " - " + selectedVol.getLieuDepart() + " to " + selectedVol.getLieuArrivee() + ", Price: " + selectedVol.getPrice());
            Stage stage = new Stage();
            stage.setScene(new Scene(tripInfoPane));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } else {
        System.out.println("Please select a trip before booking.");
    }
}
    @FXML
    private void leftButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/javafxaeroluxproject/reservationForm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            //stage.setTitle("Reservation Form");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleTripSelection(Vol vol) {
        if (vol != null) {
            String tripInfo = vol.getDescription() + " - " + vol.getLieuDepart() + " to " + vol.getLieuArrivee() + ", Price: " + vol.getPrice();
            System.out.println("Selected Trip: " + tripInfo);
        } else {
            System.out.println("No trip selected.");
        }
    }



}
