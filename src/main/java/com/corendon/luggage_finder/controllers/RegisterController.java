/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.tables.*;
import com.corendon.luggage_finder.excel.Excel;
import com.corendon.luggage_finder.model.*;
import com.corendon.luggage_finder.model.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class represents the registration screen. This class handles
 *
 * @author Fethi K. Tewelde
 */
public class RegisterController implements Initializable {

    @FXML
    private ChoiceBox<String> insuranceChoiceBox;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private TextField registrationIdTextField;

    @FXML
    private ChoiceBox<String> airportChoiceBox;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField middleNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField zipcodeTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField flightNumberTextField;

    @FXML
    private TextField labelNumberTextField;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private ChoiceBox<String> brandChoiceBox;

    @FXML
    private ChoiceBox<String> primaryColorChoiceBox;

    @FXML
    private ChoiceBox<String> secondaryColorChoiceBox;

    @FXML
    private ChoiceBox<String> statusChoiceBox;

    @FXML
    private TextField heightTextField;

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField lengthTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextArea characteristicsTextArea;

    @FXML
    private ImageView image;

    @FXML
    private BorderPane parentBorderPane;

    private ResourceBundle bundle;

    private LuggagesTable luggagesTable;

    private List<Color> colors;
    private List<Brand> brands;
    private List<LuggageType> types;
    private List<Airport> airports;
    private List<Status> statuses;
    private List<Insurance> insuranceCompanies;

    private Passenger selectedPassenger;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;

        luggagesTable = new LuggagesTable();
        ColorsTable colorsTable = new ColorsTable();
        BrandsTable brandsTable = new BrandsTable();
        LuggageTypesTable luggageTypesTable = new LuggageTypesTable();
        AirportsTable airportsTable = new AirportsTable();
        StatusesTable statusesTable = new StatusesTable();
        InsuranceCompaniesTable insuranceCompaniesTable = new InsuranceCompaniesTable();

        dateDatePicker.setValue(LocalDate.now());

        // setting colors ChoiceBox
        colors = colorsTable.getAll(1000);
        for (Color color : colors) {
            primaryColorChoiceBox.getItems().add(color.getName());
            secondaryColorChoiceBox.getItems().add(color.getName());
        }

        // setting brands choicebox
        brands = brandsTable.getAll(1000);
        for (Brand brand : brands) {
            brandChoiceBox.getItems().add(brand.getName());
        }

        // setting luggage type choicebox
        types = luggageTypesTable.getAll(1000);
        for (LuggageType type : types) {
            typeChoiceBox.getItems().add(type.getName());
        }

        // setting airports choicebox
        airports = airportsTable.getAll(1000);
        for (Airport airport : airports) {
            airportChoiceBox.getItems().add(airport.getName());
        }

        // setting status choicebox
        statuses = statusesTable.getAll(1000);
        for (Status status : statuses) {
            statusChoiceBox.getItems().add(status.getName());
        }

        // setting insurance choicebox
        //--
        insuranceCompanies = insuranceCompaniesTable.getAll(1000);
        for (Insurance insurance : insuranceCompanies) {
            insuranceChoiceBox.getItems().add(insurance.getName());
        }
    }

    @FXML
    void onPrintFormButtonAction(ActionEvent event) {

    }

    /**
     * This method is executed when the user clicks on the Opslaan button. and
     * insert a luggage in the luggagesTable.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    void onSaveButtonAction(ActionEvent event) {
        Luggage luggage = createLuggage();

        if (luggage == null) {
            return;
        }

        if (luggage.getLuggagetType() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle(bundle.getString("register_alert_missing_luggage_type_title"));
            alert.setHeaderText(bundle.getString("register_alert_missing_luggage_type_header"));
            alert.setContentText(bundle.getString("register_alert_missing_luggage_type_content"));

            alert.show();
            return;
        }

        if (luggagesTable.insert(luggage)) {
            clear();
        }
    }

    @FXML
    void onUploadButtonAction(ActionEvent event) {
        // showing file picker
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG (*.png)", "*.png");
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG (*.jpg)", "*.jpg");
        fileChooser.getExtensionFilters().addAll(pngFilter, jpgFilter);

        Window window = ((Node) event.getTarget()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
    }

    @FXML
    void onImportButtonAction(ActionEvent event) {
        // showing file picker
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(filter);

        Window window = ((Node) event.getTarget()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);

        // importing Excel
        try {
            Excel.fromExcel(file);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is executed when the user clicks on the Passagier selecteren
     * button. It loads the passenger_search.fxml
     *
     * @param event the event send from JavaFX
     */
    @FXML
    void onPassengerSelectButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = Utils.createLoader("passenger_search.fxml");
            fxmlLoader.setResources(Utils.getLanguageBundle());

            Stage passengerSelect = new Stage();
            Stage parentStage = (Stage) parentBorderPane.getScene().getWindow();
            Scene passengerSelectScene = new Scene(fxmlLoader.load());

            passengerSelect.initOwner(parentStage);
            passengerSelect.setScene(passengerSelectScene);
            passengerSelect.showAndWait();

            selectedPassenger = fxmlLoader.<PassengerSearchController>getController().getSelectedPassenger();

            firstNameTextField.setText(selectedPassenger.getFirstName());
            middleNameTextField.setText(selectedPassenger.getMiddleName());
            lastNameTextField.setText(selectedPassenger.getLastName());
            addressTextField.setText(selectedPassenger.getAddress());
            cityTextField.setText(selectedPassenger.getCity());
            zipcodeTextField.setText(selectedPassenger.getZipcode());
            countryTextField.setText(selectedPassenger.getCountry().getName());
            phoneNumberTextField.setText(selectedPassenger.getPhone());
            emailTextField.setText(selectedPassenger.getEmail());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method creates luggage.
     */
    private Luggage createLuggage() {
        int indexSelectedAirport = airportChoiceBox.getSelectionModel().getSelectedIndex();
        int indexSelectedLuggageType = typeChoiceBox.getSelectionModel().getSelectedIndex();
        int indexSelectedPrimaryColor = primaryColorChoiceBox.getSelectionModel().getSelectedIndex();
        int indexSelectedSecondaryColor = secondaryColorChoiceBox.getSelectionModel().getSelectedIndex();
        int indexSelectedLuggageBrand = brandChoiceBox.getSelectionModel().getSelectedIndex();
        int indexSelectedStatus = statusChoiceBox.getSelectionModel().getSelectedIndex();
        int indexSelectedInsuranceCompany = insuranceChoiceBox.getSelectionModel().getSelectedIndex();

        Airport airport = Utils.getOrNull(airports, indexSelectedAirport);
        LuggageType type = Utils.getOrNull(types, indexSelectedLuggageType);
        Color primaryColor = Utils.getOrNull(colors, indexSelectedPrimaryColor);
        Color secondaryColor = Utils.getOrNull(colors, indexSelectedSecondaryColor);
        Brand brand = Utils.getOrNull(brands, indexSelectedLuggageBrand);
        Status status = Utils.getOrNull(statuses, indexSelectedStatus);
        Insurance insurance = Utils.getOrNull(insuranceCompanies, indexSelectedInsuranceCompany);

        Integer length = Utils.parseInt(lengthTextField.getText());
        Integer width = Utils.parseInt(widthTextField.getText());
        Integer height = Utils.parseInt(heightTextField.getText());
        Integer weight = Utils.parseInt(weightTextField.getText());

        Date date = Utils.localDateToDate(dateDatePicker.getValue());
        String labelText = labelNumberTextField.getText();
        String characteristics = characteristicsTextArea.getText();
        String registrationId = registrationIdTextField.getText();

        // flight
        String flightNumber = flightNumberTextField.getText();
        Flight flight = new Flight(flightNumber);

        // label
        Label label = new Label(labelText, flight);

        return new Luggage(registrationId, date, airport, type, brand, label,
                status, width, length, height, weight, selectedPassenger,
                characteristics, primaryColor, secondaryColor, insurance);
    }

    /**
     * This method clears the fields.
     */
    private void clear() {
        dateDatePicker.setValue(LocalDate.now());
        registrationIdTextField.clear();
        airportChoiceBox.getSelectionModel().clearSelection();
        firstNameTextField.clear();
        middleNameTextField.clear();
        lastNameTextField.clear();
        addressTextField.clear();
        cityTextField.clear();
        zipcodeTextField.clear();
        countryTextField.clear();
        phoneNumberTextField.clear();
        emailTextField.clear();
        flightNumberTextField.clear();
        labelNumberTextField.clear();
        typeChoiceBox.getSelectionModel().clearSelection();
        brandChoiceBox.getSelectionModel().clearSelection();
        primaryColorChoiceBox.getSelectionModel().clearSelection();
        secondaryColorChoiceBox.getSelectionModel().clearSelection();
        statusChoiceBox.getSelectionModel().clearSelection();
        heightTextField.clear();
        widthTextField.clear();
        lengthTextField.clear();
        weightTextField.clear();
        characteristicsTextArea.clear();
        //--
        insuranceChoiceBox.getSelectionModel().clearSelection();
    }
}
