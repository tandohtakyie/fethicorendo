/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author Peter Dimitrov
 */
public class UserModificationNavigationTabController implements Initializable {

    @FXML
    private AnchorPane detailsAnchorPane;

    @FXML
    private AnchorPane permissionsAnchorPane;

    @FXML
    private TabPane userTabPane;

    private User selectedUser;

    /**
     * Initialises the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        constructDetailsTab();

        if (Utils.getActiveUser().getFunction().getId() != Utils.Permissions.MANAGER.getId()) {
            userTabPane.getTabs().remove(1);
        } else {
            constructPermissionsTab();
        }
    }

    @FXML
    private void onBackButtonAction(ActionEvent event) {
        NavigationController.getInstance().changeContentNode("user_search.fxml");
    }

    /**
     * Sets the current selected user.
     *
     * @param selectedUser New user.
     */
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;

        constructDetailsTab();
        constructPermissionsTab();
    }

    /**
     * Constructs the details tab content.
     */
    public void constructDetailsTab() {
        try {
            FXMLLoader fxmlLoader = Utils.createLoader("user_modification_details_tab.fxml");
            AnchorPane detailsParent = fxmlLoader.load();

            AnchorPane.setTopAnchor(detailsParent, 0.0);
            AnchorPane.setBottomAnchor(detailsParent, 0.0);
            AnchorPane.setLeftAnchor(detailsParent, 0.0);
            AnchorPane.setRightAnchor(detailsParent, 0.0);

            detailsAnchorPane.getChildren().setAll(detailsParent);

            if (this.selectedUser != null) {
                UserModificationDetailsTabController umdtController = fxmlLoader.getController();

                umdtController.setSelectedUser(this.selectedUser);
                umdtController.updateUserTextFields();
            }
        } catch (IOException ex) {
            Logger.getLogger(UserModificationNavigationTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Constructs the permissions tab content.
     */
    public void constructPermissionsTab() {
        try {
            FXMLLoader fxmlLoader = Utils.createLoader("user_modification_permission_tab.fxml");

            AnchorPane permissionsParent = fxmlLoader.load();

            AnchorPane.setTopAnchor(permissionsParent, 0.0);
            AnchorPane.setBottomAnchor(permissionsParent, 0.0);
            AnchorPane.setLeftAnchor(permissionsParent, 0.0);
            AnchorPane.setRightAnchor(permissionsParent, 0.0);

            permissionsAnchorPane.getChildren().setAll(permissionsParent);
            
            if (this.selectedUser != null) {
                UserModificationPermissionTabController umptController = fxmlLoader.getController();

                umptController.setSelectedUser(this.selectedUser);
                umptController.generateTreeView();
            }
        } catch (IOException ex) {
            Logger.getLogger(UserModificationNavigationTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
