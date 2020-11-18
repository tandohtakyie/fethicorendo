/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Corendon;
import com.corendon.luggage_finder.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

/**
 * This class represents the navigation screen. This class handles navigation
 * between screens and nested navigation inside screens.
 *
 * @author Tom J. Wassing
 */
public class NavigationController implements Initializable {

    private static NavigationController instance;

    @FXML
    private StackPane content;

    @FXML
    private ImageView userImage;

    @FXML
    private MenuButton userMenuButton;
    
    @FXML
    private Button statisticsButton;

    @FXML
    private Button usersButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        changeContentNode("home.fxml");
        instance = this;

        // Create image clip
        float imgClipRad = (float) (userImage.getFitWidth() < userImage.getFitHeight()
                ? userImage.getFitWidth() / 2
                : userImage.getFitHeight() / 2);

        Circle imageClip = new Circle(imgClipRad);
        imageClip.setCenterX(userImage.getX() + (userImage.getFitWidth() / 2));
        imageClip.setCenterY(userImage.getY() + (userImage.getFitHeight() / 2));
        userImage.setClip(imageClip);

        userMenuButton.setText(Utils.getActiveUser().getFirstName());
        
        if (Utils.getActiveUser().getFunction().getId() != Utils.Permissions.MANAGER.getId()) {
            statisticsButton.setVisible(false);
            usersButton.setVisible(false);
        }
    }

    /**
     * This method returns the current active {@link NavigationController}
     *
     * @return the controller
     */
    public static NavigationController getInstance() {
        return instance;
    }

    /**
     * This method is executed when a user clicked on the edit profile item from
     * the {@link NavigationController#userMenuButton}. The method navigates the
     * user to the edit user screen.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onEditProfileAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = Utils.createLoader("user_modification_navigation_tab.fxml");
            Parent umntParent = fxmlLoader.load();

            UserModificationNavigationTabController umntController = fxmlLoader.getController();

            umntController.setSelectedUser(Utils.getActiveUser());

            NavigationController.getInstance().changeContentParent(umntParent);
        } catch (IOException ex) {
            Logger.getLogger(UserSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is executed when a user clicked on the logout item from the
     * {@link NavigationController#userMenuButton}. The methods logs the user
     * out and returns the user to the login screen.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onLogOutAction(ActionEvent event) {
        Corendon.setScreen("login.fxml");
    }

    /**
     * This method is executed when a user clicked on the home navigation
     * button. The methods navigates the user to the home screen.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onHomeButtonAction(ActionEvent event) {
        changeContentNode("home.fxml");
    }

    /**
     * This method is executed when a user clicked on the search navigation
     * button. The methods navigates the user to the search screen.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onSearchButtonAction(ActionEvent event) {
        changeContentNode("search.fxml");
    }

    /**
     * This method is executed when a user clicked on the register navigation
     * button. The methods navigates the user to the register luggage screen.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onRegisterButtonAction(ActionEvent event) {
        changeContentNode("register.fxml");
    }

    /**
     * This method is executed when a user clicked on the statistics navigation
     * button. The methods navigates the user to the statistics screen.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onStatisticsButtonAction(ActionEvent event) {
        changeContentNode("statistics_tab.fxml");
    }

    /**
     * This method is executed when a user clicked on the users navigation
     * button. The methods navigates the user to the users screen.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onUsersButtonAction(ActionEvent event) {
        changeContentNode("user_search.fxml");
    }

    /**
     * This method is executed when a user clicked on the settings icon. The
     * methods navigates the user to the settings screen.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onSettingsMouseAction(MouseEvent event) {
        changeContentNode("settings.fxml");
    }

    /**
     * This methods changes the current active screen inside the controller and
     * returns the associated controller.
     *
     * @param screenFileName the screen file (i.e. 'login.fxml')
     * @param <T> the class of the associated controller
     * @return the controller
     */
    public <T extends Initializable> T changeContentNode(String screenFileName) {
        FXMLLoader loader = Utils.createLoader(screenFileName);

        Pane parent;
        try {
            parent = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();

            return null;
        }

        content.getChildren().setAll(parent);
        return loader.getController();
    }

    /**
     * This method changes the current active screen inside the
     * {@link NavigationController#content} and replaces it with the new parent.
     *
     * @param parent Parent to change the content to.
     */
    public void changeContentParent(Parent parent) {
        content.getChildren().setAll(parent);
    }
}
