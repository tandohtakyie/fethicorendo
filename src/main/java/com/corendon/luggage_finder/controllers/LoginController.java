/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Corendon;
import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.tables.UsersTable;
import com.corendon.luggage_finder.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Vince de Leeuw
 */
public class LoginController implements Initializable {

    private ResourceBundle bundle;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
    }

    /**
     * This method allows the enter key to be used to login if login details
     * match and are filled in
     *
     * @param event the event
     */
    @FXML
    void onPasswordKeyUpAction(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }

    /**
     * This method allows you to login if login details match when the button is
     * pressed
     *
     * @param event the event
     */
    @FXML
    private void onLoginButtonAction(ActionEvent event) {
        login();
    }

    /**
     * This method will give a popup for forgotten passwords when the button is
     * pressed
     *
     * @param event the event
     */
    @FXML
    private void onPasswordButtonAction(ActionEvent event) {
        popup();
    }

    /**
     * This method checks if the login details matches with one of the users if
     * it doesn't a popup will show up.
     */
    private void login() {
        UsersTable usersTable = new UsersTable();

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        User activeUser = usersTable.login(username, password);

        Utils.setActiveUser(activeUser);

        if (Utils.getActiveUser() != null) {
            Corendon.setScreen("navigation.fxml");
        } else {
            popup();
        }
    }

    /**
     * This method will show the popup for the forgotten password
     */
    private void popup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(bundle.getString("login_password_alert_title"));
        alert.setHeaderText(bundle.getString("login_password_alert_header"));
        alert.setContentText(bundle.getString("login_password_alert_content"));

        alert.show();
    }
}
