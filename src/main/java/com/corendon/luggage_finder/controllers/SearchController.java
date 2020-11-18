/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.tables.BrandsTable;
import com.corendon.luggage_finder.database.tables.ColorsTable;
import com.corendon.luggage_finder.database.tables.InsuranceCompaniesTable;
import com.corendon.luggage_finder.database.tables.LuggageTypesTable;
import com.corendon.luggage_finder.database.tables.LuggagesTable;
import com.corendon.luggage_finder.model.Brand;
import com.corendon.luggage_finder.model.Color;
import com.corendon.luggage_finder.model.Insurance;
import com.corendon.luggage_finder.model.Luggage;
import com.corendon.luggage_finder.model.LuggageType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the search screen. The class handles all the UI actions
 * and interacts with the database.
 *
 * @author Tom J. Wassing
 */
public class SearchController implements Initializable {

    @FXML
    private ChoiceBox<String> insuranceChoiceBox;
    
    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker untilDatePicker;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private ChoiceBox<String> primaryColorChoiceBox;

    @FXML
    private ChoiceBox<String> secondaryColorChoiceBox;

    @FXML
    private ChoiceBox<String> brandChoiceBox;

    @FXML
    private TextField fromWeightTextField;

    @FXML
    private TextField untilWeightTextField;

    @FXML
    private TextField searchTextField;

    @FXML
    private Label searchInfoLabel;

    @FXML
    private TableView<Luggage> resultsTableView;

    private ResourceBundle bundle;

    private List<LuggageType> types;
    private List<Color> colors;
    private List<Brand> brands;
    private List<Insurance> insuranceCompanies;

    private LuggagesTable luggagesTable;

    private ObservableList<Luggage> resultsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;

        luggagesTable = new LuggagesTable();
        LuggageTypesTable luggageTypesTable = new LuggageTypesTable();
        ColorsTable colorsTable = new ColorsTable();
        BrandsTable brandsTable = new BrandsTable();
        InsuranceCompaniesTable insuranceCompaniesTable = new InsuranceCompaniesTable();

        // setting luggage type choice box
        types = luggageTypesTable.getAll(100);
        for (LuggageType type : types) {
            typeChoiceBox.getItems().add(type.getName());
        }

        // setting colors choice box
        colors = colorsTable.getAll(100);
        for (Color color : colors) {
            primaryColorChoiceBox.getItems().add(color.getName());
            secondaryColorChoiceBox.getItems().add(color.getName());
        }

        // setting brands choice box
        brands = brandsTable.getAll(100);
        for (Brand brand : brands) {
            brandChoiceBox.getItems().add(brand.getName());
        }
        
        
        // setting insurance choicebox
        //--
        insuranceCompanies = insuranceCompaniesTable.getAll(1000);
        for (Insurance insurance : insuranceCompanies) {
            insuranceChoiceBox.getItems().add(insurance.getName());
        }

        // preparing results table view
        resultsList = FXCollections.observableArrayList();
        resultsTableView.setItems(resultsList);

        setupResultsTableView();
        search();
    }

    /**
     * This method is executed when the user clicks on the search button.
     *
     * @param event the event send from JavaFX
     */
    @FXML
    private void onSearchButtonAction(ActionEvent event) {
        search();
    }

    /**
     * This method is executed after the user releases the ENTER button inside
     * the {@link SearchController#searchTextField},
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
     * This method is executed when the user has clicked on a item inside the
     * {@link SearchController@resultsTableView}.
     *
     * @param event the event sent from JavaFX
     */
    @FXML
    private void onResultsTableViewItemClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader fxmlLoader = Utils.createLoader("luggage_opening_navigation.fxml");
                Parent lonParent = fxmlLoader.load();
                NavigationController.getInstance().changeContentParent(lonParent);

                LuggageOpeningNavigationController lonController = fxmlLoader.getController();

                int selectedIndex = resultsTableView.getSelectionModel().getFocusedIndex();
                Luggage luggage = resultsList.get(selectedIndex);

                lonController.setSelectedLuggage(luggage);
            } catch (IOException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method handles search and retrieve results from the database.
     */
    private void search() {
        String query = searchTextField.getText();

        LocalDate fromLocalDate = fromDatePicker.getValue();
        LocalDate untilLocalDate = untilDatePicker.getValue();

        Date fromDate = null;
        Date untilDate = null;
        if (fromLocalDate != null) {
            fromDate = Utils.localDateToDate(fromLocalDate);
        }

        if (untilLocalDate != null) {
            untilDate = Utils.localDateToDate(untilLocalDate);
        }

        int selectedTypeIndex = typeChoiceBox.getSelectionModel().getSelectedIndex();
        int selectedPrimaryColorIndex = primaryColorChoiceBox.getSelectionModel().getSelectedIndex();
        int selectedSecondaryColorIndex = secondaryColorChoiceBox.getSelectionModel().getSelectedIndex();
        int selectedBrandIndex = brandChoiceBox.getSelectionModel().getSelectedIndex();
        int selectedInsuranceCompany = insuranceChoiceBox.getSelectionModel().getSelectedIndex();
        
        LuggageType type = Utils.getOrNull(types, selectedTypeIndex);
        Color primaryColor = Utils.getOrNull(colors, selectedPrimaryColorIndex);
        Color secondaryColor = Utils.getOrNull(colors, selectedSecondaryColorIndex);
        Brand brand = Utils.getOrNull(brands, selectedBrandIndex);
        Insurance insurance = Utils.getOrNull(insuranceCompanies, selectedInsuranceCompany);
        
        String fromWeightText = fromWeightTextField.getText();
        String untilWeightText = untilWeightTextField.getText();

        Integer fromWeight = null;
        Integer untilWeight = null;
        if (Utils.isStringFilled(fromWeightText)) {
            try {
                fromWeight = Integer.valueOf(fromWeightText);
            } catch (NumberFormatException e) {
                fromWeightTextField.clear();
                return;
            }
        }

        if (Utils.isStringFilled(untilWeightText)) {
            try {
                untilWeight = Integer.valueOf(untilWeightText);
            } catch (NumberFormatException e) {
                untilWeightTextField.clear();
                return;
            }
        }

        long startTime = System.currentTimeMillis();

        List<Luggage> luggages = luggagesTable.search(query, fromDate, untilDate,
                type, primaryColor, secondaryColor, brand, fromWeight, untilWeight,
                insurance, 500);

        long endTime = System.currentTimeMillis() - startTime;

        if (luggages.size() == 1) {
            searchInfoLabel.setText(String.format(bundle.getString("general_search_single_result_statistics"), luggages.size(), endTime));
        } else {
            searchInfoLabel.setText(String.format(bundle.getString("general_search_multiple_results_statistics"), luggages.size(), endTime));
        }

        resultsList.setAll(luggages);
    }

    /**
     * Prepares the {@link SearchController#resultsTableView} to receive
     * results.
     */
    private void setupResultsTableView() {
        TableColumn<Luggage, String> idColumn = new TableColumn<>(bundle.getString("search_table_id"));
        idColumn.setCellValueFactory((value) -> {
            String registrationId = value.getValue().getRegistrationId();
            return new SimpleStringProperty(registrationId);
        });

        TableColumn<Luggage, String> statusColumn = new TableColumn<>(bundle.getString("search_table_status"));
        statusColumn.setCellValueFactory((value) -> {
            if (value.getValue().getMostRecentStatus() == null) {
                return new SimpleStringProperty(bundle.getString("search_table_unknown_value"));
            }

            String statusName = value.getValue().getMostRecentStatus().getName();
            return new SimpleStringProperty(statusName);
        });

        TableColumn<Luggage, String> lastChangeColumn = new TableColumn<>(bundle.getString("search_table_last_change"));
        lastChangeColumn.setCellValueFactory((value) -> {
            if (value.getValue().getMostRecentStatus().getCreated() == null) {
                return new SimpleStringProperty(bundle.getString("search_table_unknown_value"));
            }

            Date date = value.getValue().getMostRecentStatus().getCreated();
            String parsedDate = Utils.dateToString(date);
            return new SimpleStringProperty(parsedDate);
        });

        TableColumn<Luggage, String> typeColumn = new TableColumn<>(bundle.getString("search_table_type"));
        typeColumn.setCellValueFactory((value) -> {
            if (value.getValue().getLuggagetType() == null) {
                return new SimpleStringProperty(bundle.getString("search_table_unknown_value"));
            }

            String typeName = value.getValue().getLuggagetType().getName();
            return new SimpleStringProperty(typeName);
        });

        TableColumn<Luggage, String> primaryColorColumn = new TableColumn<>(bundle.getString("search_table_primary_color"));
        primaryColorColumn.setCellValueFactory((value) -> {
            if (value.getValue().getPrimaryColor() == null) {
                return new SimpleStringProperty(bundle.getString("search_table_unknown_value"));
            }

            String primaryColorName = value.getValue().getPrimaryColor().getName();
            return new SimpleStringProperty(primaryColorName);
        });

        TableColumn<Luggage, String> secondaryColorColumn = new TableColumn<>(bundle.getString("search_table_secondary_color"));
        secondaryColorColumn.setCellValueFactory((value) -> {
            if (value.getValue().getSecondaryColor() == null) {
                return new SimpleStringProperty(bundle.getString("search_table_unknown_value"));
            }

            String secondaryColor = value.getValue().getSecondaryColor().getName();
            return new SimpleStringProperty(secondaryColor);
        });
        
                TableColumn<Luggage, String> insuranceCompanyColumn = new TableColumn<>(bundle.getString("search_table_insurance"));
        insuranceCompanyColumn.setCellValueFactory((value) -> {
            if (value.getValue().getInsurance()== null) {
                return new SimpleStringProperty(bundle.getString("search_table_unknown_value"));
            }

            String insuranceCompany = value.getValue().getInsurance().getName();
            return new SimpleStringProperty(insuranceCompany);
        });
        

        resultsTableView.getColumns().setAll(idColumn, lastChangeColumn, statusColumn, primaryColorColumn, secondaryColorColumn, insuranceCompanyColumn);
    }
}
