/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.model.Luggage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dylan Tweebeeke
 * @author Yessin el Khaldi
 */
public class LuggageOpeningInfoController implements Initializable {

    @FXML
    private DatePicker dateDisplayField;

    @FXML
    private TextField timeDisplayField;

    @FXML
    private TextField airportDisplayField;

    @FXML
    private TextField nameDisplayField;

    @FXML
    private TextField addressDisplayField;

    @FXML
    private TextField placeDisplayField;

    @FXML
    private TextField areaCodeDisplayField;

    @FXML
    private TextField countryDisplayField;

    @FXML
    private TextField numberDisplayField;

    @FXML
    private TextField mailDisplayField;

    @FXML
    private TextField labelDisplayField;

    @FXML
    private TextField flightNumberDisplayField;

    @FXML
    private TextField typeDisplayField;

    @FXML
    private TextField brandDisplayField;

    @FXML
    private TextField primaryColorDisplayField;

    @FXML
    private TextField secondaryColorDisplayField;

    @FXML
    private TextField weightDisplayField;

    @FXML
    private TextField lengthDisplayField;

    @FXML
    private TextField widthDisplayField;

    @FXML
    private TextField heightDisplayField;

    @FXML
    private TextArea characteristicsDisplayField;
    
    @FXML
    private TextField insuranceCompanyDisplayField;

    private Luggage selectedLuggage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void updateLuggageTextFields() {
        if (selectedLuggage == null) {
            return;
        }

        LocalDate localDateDisplay = Utils.dateToLocalDate(selectedLuggage.getDateFound());
        dateDisplayField.setValue(localDateDisplay);

        airportDisplayField.setText(selectedLuggage.getLocationFound().getName());

        nameDisplayField.setText(selectedLuggage.getPassenger().getFirstName()+ " "
                + selectedLuggage.getPassenger().getMiddleName() + " "
                + selectedLuggage.getPassenger().getLastName());

        addressDisplayField.setText(selectedLuggage.getPassenger().getAddress());

        placeDisplayField.setText(selectedLuggage.getPassenger().getCity());

        areaCodeDisplayField.setText(selectedLuggage.getPassenger().getZipcode());

        countryDisplayField.setText(selectedLuggage.getPassenger().getCountry().getName());

        numberDisplayField.setText(selectedLuggage.getPassenger().getPhone());

        mailDisplayField.setText(selectedLuggage.getPassenger().getEmail());

        labelDisplayField.setText(selectedLuggage.getLabel().getTag());

        flightNumberDisplayField.setText(selectedLuggage.getLabel().getFlight().getFlightNumber());

        typeDisplayField.setText(selectedLuggage.getLuggagetType().getName());

        brandDisplayField.setText(selectedLuggage.getBrand().getName());

        primaryColorDisplayField.setText(selectedLuggage.getPrimaryColor().getName());

        secondaryColorDisplayField.setText(selectedLuggage.getSecondaryColor().getName());

        weightDisplayField.setText(selectedLuggage.getWeight().toString());

        lengthDisplayField.setText(selectedLuggage.getSizeLength().toString());

        widthDisplayField.setText(selectedLuggage.getSizeWidth().toString());

        heightDisplayField.setText(selectedLuggage.getSizeHeight().toString());

        characteristicsDisplayField.setText(selectedLuggage.getCharacteristics());
        
        insuranceCompanyDisplayField.setText(selectedLuggage.getInsurance().getName());
        
    }

    public void setSelectedLuggage(Luggage selectedLuggage) {
        this.selectedLuggage = selectedLuggage;
    }
}
