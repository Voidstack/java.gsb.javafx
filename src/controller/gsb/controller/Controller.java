package controller.gsb.controller;

import controller.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    BorderPane root;

    @FXML
    AnchorPane rootPane;

    @FXML
    VBox boxMenu;

    AnchorPane pane;

    @FXML
    Button btMedecin, btMedicament, btVisite;

    @FXML
    Button btMedCons, btMedAdd, btMediAdd, btMediCons, btVisCons, btVisAdd, btMedSuppr, btMedSearch;

    private double x, y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void onClick(MouseEvent event) throws IOException {
        Main.HEIGHTPANE = (int) rootPane.getHeight();
        Main.WIDTHPANE = (int) rootPane.getWidth();

        // Medecin
        if (event.getSource() == btMedCons) {
            pane = FXMLLoader.load(getClass().getResource("/fxml/medecinView.fxml"));
        } else if (event.getSource() == btMedSuppr) {
            pane = FXMLLoader.load(getClass().getResource("/fxml/medecinSupprView.fxml"));
        } else if (event.getSource() == btMedAdd) {
            pane = FXMLLoader.load(getClass().getResource("/fxml/medecinAddView.fxml"));
        } else if (event.getSource() == btMedSearch) {
            pane = FXMLLoader.load(getClass().getResource("/fxml/medecinRechercheView.fxml"));
        }
        // Medicament
        else if (event.getSource() == btMediAdd) {
            pane = FXMLLoader.load(getClass().getResource("/fxml/medicamentView.fxml"));
        } else if (event.getSource() == btMediCons) {
            pane = FXMLLoader.load(getClass().getResource("/fxml/medicamentView.fxml"));
        }
        // Visite
        else if (event.getSource() == btVisAdd) {
            pane = FXMLLoader.load(getClass().getResource("/fxml/visiteView.fxml"));
        } else if (event.getSource() == btVisCons) {
            pane = FXMLLoader.load(getClass().getResource("/fxml/visiteView.fxml"));
        }
        if (pane != null) {
            rootPane.getChildren().setAll(pane);
        }

    }

    @FXML
    void onEntered(MouseEvent event) {
        if (event.getSource() == btMedecin) {
            btMedAdd.setPrefHeight(30);
            btMedCons.setPrefHeight(30);
            btMedSuppr.setPrefHeight(30);
            btMedSearch.setPrefHeight(30);
            btVisCons.setPrefHeight(0);
            btVisAdd.setPrefHeight(0);
            btMediAdd.setPrefHeight(0);
            btMediCons.setPrefHeight(0);
        } else if (event.getSource() == btMedicament) {
            btMediAdd.setPrefHeight(30);
            btMediCons.setPrefHeight(30);
            btMedSearch.setPrefHeight(0);

            btMedSuppr.setPrefHeight(0);
            btVisCons.setPrefHeight(0);
            btVisAdd.setPrefHeight(0);
            btMedAdd.setPrefHeight(0);
            btMedCons.setPrefHeight(0);
        } else if (event.getSource() == btVisite) {
            btVisCons.setPrefHeight(30);
            btVisAdd.setPrefHeight(30);
            btMedSuppr.setPrefHeight(0);
            btMediAdd.setPrefHeight(0);
            btMedSearch.setPrefHeight(0);

            btMediCons.setPrefHeight(0);
            btMedAdd.setPrefHeight(0);
            btMedCons.setPrefHeight(0);
        }
    }


    @FXML
    void onExited(MouseEvent event) {
        if (event.getSource() == boxMenu) {
            btVisCons.setPrefHeight(0);
            btVisAdd.setPrefHeight(0);
            btMedSuppr.setPrefHeight(0);
            btMedSearch.setPrefHeight(0);

            btMediAdd.setPrefHeight(0);
            btMediCons.setPrefHeight(0);
            btMedAdd.setPrefHeight(0);
            btMedCons.setPrefHeight(0);
        }
    }

    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void close(MouseEvent event) {
        /* Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); */
        Platform.exit();
    }

    @FXML
    void min(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}
