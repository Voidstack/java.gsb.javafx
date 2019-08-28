package controller.gsb.controller;

import controller.Main;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerSplashView implements Initializable {

    @FXML
    ImageView imageView;

    @FXML
    StackPane stackPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stackPane.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0);"+
                "-fx-effect: dropshadow(gaussian, black, 50, 0, 0, 0);");

        if (!Main.test) {
            loadSplashScreen();
        }
    }

    public void openMain() {

        // Platform.exit();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/mainView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root, Main.WIDTH, Main.HEIGHT));
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.show();
    }

    private void loadSplashScreen() {
        System.out.println("test");
        Main.test = true;
        try {
            Image image = new Image("/images/logo_gsb.png");
            imageView.setImage(image);

            imageView.setX(50);
            imageView.setY(50);

            FadeTransition fadeIn = new FadeTransition();
            fadeIn.setNode(imageView);
            fadeIn.setDuration(Duration.seconds(2));
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition();
            fadeOut.setNode(imageView);
            fadeOut.setDuration(Duration.seconds(2));
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            FadeTransition fadeNothing = new FadeTransition();
            fadeNothing.setNode(imageView);
            fadeNothing.setDuration(Duration.seconds(1));
            fadeNothing.setFromValue(0);
            fadeNothing.setToValue(0);
            fadeNothing.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                fadeNothing.play();
            });

            fadeNothing.setOnFinished((e) -> {
                openMain();
            });

        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
