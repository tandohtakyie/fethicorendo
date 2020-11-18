/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.tables.PassengersTable;
import com.corendon.luggage_finder.model.Passenger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Passenger search screen controller.
 *
 * @author Yessin el Khaldi
 * @author Petar Dimitrov
 */
public class PassengerSearchController implements Initializable {

    @FXML
    private AnchorPane parentAnchorPane;

    @FXML
    private TextField passengerSearchText;

    @FXML
    private TableView<Passenger> tableview;

    @FXML
    private Label searchInfoLabel;

    private ResourceBundle bundle;

    private PassengersTable passengersTable;

    private ObservableList<Passenger> resultsList = FXCollections.observableArrayList();
    private Passenger selectedPassenger;

    /* (non-Javadoc)
     * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
     */
    /**
     * This method initializes the controller class
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bundle = resources;
        passengersTable = new PassengersTable();

        setupTableView();
        search();
    }

    /**
     * This method adds a passenger
     *
     * @param event
     */
    @FXML
    private void onAddPassengerButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/screens/passenger_modification.fxml"));
            Parent pmParent = fxmlLoader.load();

            Stage mainStage = (Stage) parentAnchorPane.getScene().getWindow();
            Scene scene = new Scene(pmParent, mainStage.getWidth(), mainStage.getHeight());
            mainStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPassengerModifyButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = Utils.createLoader("passenger_modification.fxml");
            Parent pmParent = fxmlLoader.load();

            Stage mainStage = (Stage) parentAnchorPane.getScene().getWindow();
            Scene scene = new Scene(pmParent, mainStage.getWidth(), mainStage.getHeight());
            mainStage.setScene(scene);

            PassengerModificationController pmController = fxmlLoader.getController();
            Passenger passenger = resultsList.get(tableview.getSelectionModel().getFocusedIndex());

            pmController.setSelectedPassenger(passenger);
        } catch (IOException ex) {
            Logger.getLogger(UserSearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onTableViewItemClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            selectedPassenger = resultsList.get(tableview.getSelectionModel().getFocusedIndex());

            parentAnchorPane.getScene().getWindow().hide();
        }
    }

    @FXML
    private void onUserSearchButtonAction(ActionEvent event) {
        search();
    }

    /**
     * This method is executed after the user releases the ENTER button inside
     * the {@link PassengerSearchController#passengerSearchText},
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onSearchFieldKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            search();
        }
    }

    /**
     * This method retrieves the input from the
     * {@link PassengerSearchController#passengerSearchText} and performs a
     * search.
     */
    private void search() {
        long startTime = System.currentTimeMillis();

        List<Passenger> passengers = passengersTable.search(passengerSearchText.getText());

        long endTime = System.currentTimeMillis() - startTime;

        if (passengers.size() == 1) {
            searchInfoLabel.setText(String.format(bundle.getString("general_search_single_result_statistics"), passengers.size(), endTime));
        } else {
            searchInfoLabel.setText(String.format(bundle.getString("general_search_multiple_results_statistics"), passengers.size(), endTime));
        }

        resultsList.setAll(passengers);
    }

    private void setupTableView() {
        TableColumn<Passenger, String> nameColumn = new TableColumn<>(bundle.getString("passenger_search_results_column_name"));

        nameColumn.setCellValueFactory((param)
                -> new SimpleStringProperty(param.getValue().getFirstName()
                        + " " + param.getValue().getMiddleName()
                        + " " + param.getValue().getLastName()));

        TableColumn<Passenger, String> addressColumn = new TableColumn<>(bundle.getString("passenger_search_results_column_address"));
        addressColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getAddress()));

        TableColumn<Passenger, String> zipcodeColumn = new TableColumn<>(bundle.getString("passenger_search_results_column_zip_code"));
        zipcodeColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getZipcode()));

        TableColumn<Passenger, String> cityColumn = new TableColumn<>(bundle.getString("passenger_search_results_column_city"));
        cityColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCity()));

        TableColumn<Passenger, String> countryColumn = new TableColumn<>(bundle.getString("passenger_search_results_column_country"));
        countryColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCountry().getName()));

        tableview.getColumns().setAll(nameColumn, addressColumn, zipcodeColumn, cityColumn, countryColumn);
        tableview.setItems(resultsList);
    }

    /**
     * This method returns selected passenger
     *
     * @return the selectedPassenger
     */
    public Passenger getSelectedPassenger() {
        return selectedPassenger;
    }
}
