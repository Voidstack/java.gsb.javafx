package controller;

import controller.gsb.modele.dao.ConnexionMySql;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.awt.*;

public class Main extends Application {

    double x = 0;
    double y = 0;

    public static int WIDTHPANE;
    public static int HEIGHTPANE;

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static int WIDTH = (int) screenSize.getWidth()/2;
    public static int HEIGHT = (int) screenSize.getHeight()/2;

    public static Boolean test = false;

    public static String search = "";

    @Override
    public void start(Stage stage) throws Exception {

        ConnexionMySql.connecterBd();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/splashView.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);
        stage.setTitle("GSB");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();


        root.setOnMousePressed(e -> {
            x = stage.getX() - e.getScreenX();
            y = stage.getY() - e.getScreenY();
        });
        root.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() + x);
            stage.setY(e.getScreenY() + y);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
