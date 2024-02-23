package com.example.javafxaeroluxproject.controllers;
import com.example.javafxaeroluxproject.models.User;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;


public class ButtonCell extends TableCell<User, Void> {
    private final Button btn1;
    private final Button btn2;
    private final Button btn3;
    public ButtonCell(Button btn1 , Button btn2 , Button btn3) {
       this.btn1=btn1 ;
       this.btn2=btn2;
       this.btn3=btn3;

        btn1.setOnAction(event -> {

            System.out.println("Display button clicked");
        });

        btn2.setOnAction(event -> {
            System.out.println("Edit button clicked");

            User user=getTableView().getItems().get(getIndex());
           // ( (DisplayUsers) getTableView().getProperties().get("controller")).handleEditButton(user);


        });



       btn3.setOnAction(event -> {
            System.out.println("Delete button clicked");
            User user=getTableView().getItems().get(getIndex());
           // ( (DisplayUsers) getTableView().getProperties().get("controller")).handleDeleteButtuon(user);

        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);

        } else {
           /* setGraphic(btn1);
            setGraphic(btn2);
            setGraphic(btn3);*/
            // Créez de nouveaux boutons pour chaque cellule
            Button cellButton1 = new Button(btn1.getText());
            Button cellButton2 = new Button(btn2.getText());
            Button cellButton3 = new Button(btn3.getText());


            cellButton1.getStyleClass().add("btn-display");
            cellButton2.getStyleClass().add("btn-edit");
            cellButton3.getStyleClass().add("btn-delete");

            cellButton1.setOnAction(btn1.getOnAction());
            cellButton2.setOnAction(btn2.getOnAction());
            cellButton3.setOnAction(btn3.getOnAction());

            // Créez un conteneur pour les boutons (par exemple, un HBox)
            HBox buttonContainer = new HBox(cellButton1, cellButton2, cellButton3);

            setGraphic(buttonContainer);

        }
    }
}

