package controller.gsb.controller;

import controller.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVisite implements Initializable {
     @FXML
    AnchorPane root;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.setPrefWidth(Main.WIDTHPANE);
        root.setPrefHeight(Main.HEIGHTPANE);

    }
}
