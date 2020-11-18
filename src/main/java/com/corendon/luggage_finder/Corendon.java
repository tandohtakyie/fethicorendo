/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder;

import com.corendon.luggage_finder.database.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is the main class for the application. This class managed the
 * stage for the presented window.
 *
 * @author Tom J. Wassing
 * @author Dylan Tweebeeke
 */
public class Corendon extends Application {
    private static final int SCREEN_WIDTH = 1024;
    private static final int SCREEN_HEIGHT = 768;

    private static Stage mainStage;

    /**
     * Initializes the application.
     *
     * @param stage the stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        setMainStage(stage);

        JDBC.getInstance().connect();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screens/login.fxml"));
        loader.setResources(Utils.getLanguageBundle());
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMinWidth(SCREEN_WIDTH);
        stage.setMinHeight(SCREEN_HEIGHT);
        stage.setTitle("Luggage Finder");
        stage.show();
    }

    /**
     * Changes to current stage.
     *
     * @param stage the new stage.
     */
    private static void setMainStage(Stage stage) {
        Corendon.mainStage = stage;
    }

    /**
     * Loads a new screen into the stage.
     *
     * @param screenRes the screen file to be loaded (i.e. 'login.fxml')
     */
    public static void setScreen(String screenRes) {

        try {
            String resource = "/screens/" + screenRes;
            URL url = Utils.class.getResource(resource);

            FXMLLoader loader = new FXMLLoader(url);
            loader.setResources(Utils.getLanguageBundle());
            Parent root = loader.load();

            Scene scene = new Scene(root, mainStage.getWidth(), mainStage.getHeight());
            mainStage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(Corendon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
