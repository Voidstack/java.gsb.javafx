package controller.gsb.controller;

import controller.Main;
import controller.gsb.modele.Localite;
import controller.gsb.modele.Medecin;
import controller.gsb.modele.dao.ConnexionMySql;
import controller.gsb.modele.dao.LocaliteDao;
import controller.gsb.modele.dao.MedecinDao;
import controller.gsb.utils.ValidationUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMedecinAdd implements Initializable {

    @FXML
    AnchorPane rootPane;

    @FXML
    TextField tfCM, tfPre, tfNom, tfAdr, tfTel, tfPot, tfSpe, tfCP, tfVille;

    @FXML
    Text txtResult;

    @FXML
    Button btAdd;

    Boolean validation = false;

    private Medecin med = new Medecin(null, null, null, null, null, null
            , null, null);
    private Localite loc = new Localite(null, null);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ConnexionMySql.connecterBd();
        rootPane.setPrefHeight(Main.HEIGHTPANE);
        rootPane.setPrefWidth(Main.WIDTHPANE);
    }

    @FXML
    void onClick(MouseEvent event) {


        if (event.getSource() == btAdd) {
            if (ValidationUtils.isTelephoneValide(tfTel.getText())) {
                med.setTelephone(tfTel.getText());
                System.out.println("tel");
                if (ValidationUtils.isCodePostalValide(tfCP.getText())) {
                    loc.setCodePostal(tfCP.getText());
                    System.out.println("cp");

                    if (ValidationUtils.isNomValide(tfNom.getText())) {
                        med.setNom(tfNom.getText());
                        System.out.println("nom");

                        if (ValidationUtils.isNomValide(tfPre.getText())) {
                            med.setPrenom(tfPre.getText());
                            System.out.println("prenom");

                            if (ValidationUtils.isCodeMedValide(tfCM.getText())) {
                                med.setCodeMed(tfCM.getText());
                                System.out.println("code medecin");

                                if (ValidationUtils.isNomValide(tfVille.getText())) {
                                    String ville = tfVille.getText();
                                    System.out.println("ville");

                                    loc.setVille(ville);
                                    validation = true;
                                }
                            }
                        }
                    }
                }
            }
            med.setAdresse(tfAdr.getText());
            med.setLaLocalite(loc);
            med.setPotentiel(tfPot.getText());
            med.setSpecialite(tfSpe.getText());

            if (validation) {
                LocaliteDao.AjouterLocalite(loc);
                MedecinDao.AjouterMedecin(med);
                txtResult.setText("Le medecin a ete ajoute");
                validation = false;
            } else {
                txtResult.setText("erreur");
            }
        }
    }
}
