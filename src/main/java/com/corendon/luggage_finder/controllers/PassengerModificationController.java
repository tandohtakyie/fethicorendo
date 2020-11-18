/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.tables.CountriesTable;
import com.corendon.luggage_finder.database.tables.PassengersTable;
import com.corendon.luggage_finder.model.Country;
import com.corendon.luggage_finder.model.Passenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Passenger modification screen controller.
 *
 * @author Yessin el Khaldi
 * @author Petar Dimitrov
 */
public class PassengerModificationController implements Initializable {

    @FXML
    private AnchorPane parentAnchorPane;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField prepositionTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField areacodeTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private ChoiceBox countryChoiceBox;

    private Passenger selectedPassenger;

    private List<Country> countries = new CountriesTable().getAll(100);

    /* (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
    /**
     * This method initializes the controller class
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        for (Country country : countries) {
            countryChoiceBox.getItems().add(country.getName());
        }
    }

    /**
     * This method returns the user to the previous page
     *
     * @param event
     */
    @FXML
    private void onBackButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = Utils.createLoader("passenger_search.fxml");
            Parent psParent = fxmlLoader.load();

            Stage mainStage = (Stage) parentAnchorPane.getScene().getWindow();
            Scene scene = new Scene(psParent, mainStage.getWidth(), mainStage.getHeight());
            mainStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will delete a passenger
     *
     * @param event
     */
    @FXML
    private void onDeleteButtonAction(ActionEvent event) {
        if (selectedPassenger == null) {
            // TODO cant delete non existing passenger
            return;
        }

        PassengersTable passengersTable = new PassengersTable();

        passengersTable.delete(selectedPassenger);

        onBackButtonAction(event);
    }

    /**
     * This method will save information about a passenger
     *
     * @param event
     */
    @FXML
    private void onSaveButtonAction(ActionEvent event) {
        if (selectedPassenger == null
                && !(Utils.isStringFilled(firstNameTextField.getText())
                || Utils.isStringFilled(lastNameTextField.getText())
                || Utils.isStringFilled(addressTextField.getText())
                || Utils.isStringFilled(areacodeTextField.getText())
                || countryChoiceBox.getValue() != null)) {

            System.out.println("Not saved");
            return;
        }

        PassengersTable passengersTable = new PassengersTable();

        // Disgustingly nice pt.2
        String firstName = firstNameTextField.getText().isEmpty()
                ? selectedPassenger.getFirstName() : firstNameTextField.getText();
        String preposition = prepositionTextField.getText();
        String lastName = lastNameTextField.getText().isEmpty()
                ? selectedPassenger.getLastName() : lastNameTextField.getText();
        String email = emailTextField.getText().isEmpty()
                ? selectedPassenger.getEmail() : emailTextField.getText();
        String address = addressTextField.getText().isEmpty()
                ? selectedPassenger.getEmail() : addressTextField.getText();
        String zipcode = areacodeTextField.getText().isEmpty()
                ? selectedPassenger.getZipcode() : areacodeTextField.getText();
        String city = cityTextField.getText().isEmpty()
                ? selectedPassenger.getCity() : cityTextField.getText();
        Country country = countries.get(countryChoiceBox.getSelectionModel().getSelectedIndex());
        // End chunk text

        if (selectedPassenger != null) {
            selectedPassenger.setFirstName(firstName);
            selectedPassenger.setMiddleName(preposition);
            selectedPassenger.setLastName(lastName);
            selectedPassenger.setEmail(email);
            selectedPassenger.setAddress(address);
            selectedPassenger.setZipcode(zipcode);
            selectedPassenger.setCity(city);
            selectedPassenger.setCountry(country);

            passengersTable.update(selectedPassenger);
        } else {
            Passenger newPassenger = new Passenger(firstName, preposition, lastName, address, zipcode, city, country, email, zipcode);

            passengersTable.insert(newPassenger);
        }

        onBackButtonAction(new ActionEvent());
    }

    /**
     * This method selects a passenger and shows infromation about the selected
     * passenger
     *
     * @param selectedPassenger
     */
    public void setSelectedPassenger(Passenger selectedPassenger) {
        this.selectedPassenger = selectedPassenger;

        constructDetails();
    }

    /**
     * This method fills in the fields with information about the passenger
     *
     */
    private void constructDetails() {
        if (selectedPassenger == null) {
            return;
        }

        firstNameTextField.setText(selectedPassenger.getFirstName());
        prepositionTextField.setText(selectedPassenger.getMiddleName());
        lastNameTextField.setText(selectedPassenger.getLastName());
        emailTextField.setText(selectedPassenger.getEmail());
        addressTextField.setText(selectedPassenger.getAddress());
        areacodeTextField.setText(selectedPassenger.getZipcode());
        cityTextField.setText(selectedPassenger.getCity());
        countryChoiceBox.setValue(selectedPassenger.getCountry().getName());
    }
}
