package controller.gsb.controller;

import controller.Main;
import controller.gsb.modele.Medecin;
import controller.gsb.modele.dao.ConnexionMySql;
import controller.gsb.service.MedecinService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerRecherche implements Initializable {

    @FXML
    AnchorPane rootPane;
    AnchorPane pane;

    @FXML
    TextField tfSearch;

    @FXML
    Text txtResult;

    @FXML
    Button btSearch;

    private ArrayList<Medecin> liste;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConnexionMySql.connecterBd();

        rootPane.setPrefHeight(Main.HEIGHTPANE);
        rootPane.setPrefWidth(Main.WIDTHPANE);

        liste = new ArrayList<>();
        try {
            liste = MedecinService.RechercherToutMedecins();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClick(MouseEvent event) throws IOException{
        if(event.getSource() == btSearch){
            Main.search = tfSearch.getText();
            for (int i = 0; i<liste.size(); i++){
                if(Main.search.equals(liste.get(i).getCodeMed())){
                    System.out.println("nice");
                    pane = FXMLLoader.load(getClass().getResource("/fxml/medecinSupprView.fxml"));
                    rootPane.getChildren().setAll(pane);
                }
                else{
                    txtResult.setText(" Ce medecin n'existe pas ");
                }
            }
        }
    }
}
