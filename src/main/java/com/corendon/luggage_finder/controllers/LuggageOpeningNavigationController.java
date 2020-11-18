/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.model.Luggage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dylan Tweebeeke
 */
public class LuggageOpeningNavigationController implements Initializable {

    @FXML
    private AnchorPane infoAnchorPane;

    @FXML
    private AnchorPane statusAnchorPane;

    private Luggage selectedLuggage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onBackButtonAction(ActionEvent event) {
        NavigationController.getInstance().changeContentNode("search.fxml");
    }

    public void setSelectedLuggage(Luggage selectedLuggage) {
        this.selectedLuggage = selectedLuggage;

        constructInfoTab();
        constructStatusTab();
    }

    public void constructInfoTab() {
        try {
            FXMLLoader fxmlLoader = Utils.createLoader("luggage_opening_info.fxml");
            AnchorPane infoParent = fxmlLoader.load();

            AnchorPane.setTopAnchor(infoParent, 0.0);
            AnchorPane.setBottomAnchor(infoParent, 0.0);
            AnchorPane.setLeftAnchor(infoParent, 0.0);
            AnchorPane.setRightAnchor(infoParent, 0.0);

            infoAnchorPane.getChildren().setAll(infoParent);

            if (selectedLuggage != null) {
                LuggageOpeningInfoController loiController = fxmlLoader.getController();
                loiController.setSelectedLuggage(selectedLuggage);
                loiController.updateLuggageTextFields();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void constructStatusTab() {
        try {
            FXMLLoader fxmlLoader = Utils.createLoader("luggage_opening_status.fxml");
            AnchorPane infoParent = fxmlLoader.load();

            AnchorPane.setTopAnchor(infoParent, 0.0);
            AnchorPane.setBottomAnchor(infoParent, 0.0);
            AnchorPane.setLeftAnchor(infoParent, 0.0);
            AnchorPane.setRightAnchor(infoParent, 0.0);

            statusAnchorPane.getChildren().setAll(infoParent);

            if (selectedLuggage != null) {
                LuggageOpeningStatusController controller = fxmlLoader.getController();

                controller.setLuggage(selectedLuggage);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
