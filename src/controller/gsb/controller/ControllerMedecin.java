package controller.gsb.controller;

import controller.Main;
import controller.gsb.modele.Medecin;
import controller.gsb.modele.dao.ConnexionMySql;
import controller.gsb.service.MedecinService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerMedecin implements Initializable {

    @FXML
    AnchorPane rootPane;

    @FXML
    Text txtNom, txtPrenom, txtCode, txtAdresse, txtCP, txtTel, txtSpe;

    @FXML
    Button btPrevious, btNext;

    private int counter;
    private ArrayList<Medecin> liste;
    Medecin medic  = null;


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
        Medecin med;
        if(medic == null){
            med = liste.get(0);
            counter = 0;
            remplirText(med);
        }
        else{
            med = medic;
            for(int i = 0; i < liste.size();i++){
                if(liste.get(i).getCodeMed() == med.getCodeMed()){
                    counter = i;
                }
            }
        }
    }

    @FXML
    void onClick(MouseEvent event) {
        if (event.getSource() == btPrevious) {
            if(counter > 0){
                counter = counter - 1;
                remplirText(liste.get(counter));
            }

        } else if (event.getSource() == btNext) {
            if(counter < liste.size()-1){
                counter = counter + 1;
                remplirText(liste.get(counter));
            }

        }
    }

    public void remplirText(Medecin med) {

        txtNom.setText(med.getNom());
        txtPrenom.setText(med.getPrenom());
        txtCode.setText(med.getCodeMed());
        txtAdresse.setText(med.getAdresse());
        txtTel.setText(med.getTelephone());
        txtCP.setText(med.getLaLocalite().getCodePostal());
        txtSpe.setText(med.getSpecialite());
    }
}
