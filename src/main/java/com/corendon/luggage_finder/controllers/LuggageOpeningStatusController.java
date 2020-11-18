/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.tables.StatusHistoryTable;
import com.corendon.luggage_finder.database.tables.StatusesTable;
import com.corendon.luggage_finder.model.Luggage;
import com.corendon.luggage_finder.model.Status;
import com.corendon.luggage_finder.model.StatusHistory;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dylan Tweebeeke
 */
public class LuggageOpeningStatusController implements Initializable {

    //declaring the fxml:id attributes;
    @FXML
    private Label baggageIdLabel;

    @FXML
    private Label currentStatusLabel;

    @FXML
    private TableView<StatusHistory> historyTableView;

    @FXML
    private HBox radioButtonHBox;

    private ResourceBundle bundle;

    private StatusHistoryTable statusHistory;
    private Luggage luggage;

    //adding a toggle group so the user can only check one radio button
    private final ToggleGroup togglegroup = new ToggleGroup();

    //declaring new lists
    private List<RadioButton> radioButtons;
    private List<Status> statuses;

    /**
     * Initializes the controller class
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;

        //making new tables
        StatusesTable statusTable = new StatusesTable();
        statuses = statusTable.getAll(500);
        radioButtons = new ArrayList<>(statuses.size());
        statusHistory = new StatusHistoryTable();

        //adding in all the radioButtons into the v-boxes from the database
        for (Status status : statuses) {
            RadioButton box = new RadioButton();
            box.setText(status.getName());
            box.setToggleGroup(togglegroup);
            radioButtonHBox.getChildren().add(box);
            radioButtons.add(box);
        }

        setupResultTableView();
    }

    public void setLuggage(Luggage luggage) {
        this.luggage = luggage;
        String luggageId = luggage.getLabel().getTag();

        //setting the label to the luggage id
        baggageIdLabel.setText(luggageId);

        //setting the status label to the most recent status
        currentStatusLabel.setText(luggage.getMostRecentStatus().getName());
        loadStatusHistory();
    }

    private void loadStatusHistory() {
        if (luggage != null) {
            List<StatusHistory> statusesHistory = statusHistory.getHistoryByLuggageId(luggage.getId());
            historyTableView.getItems().setAll(statusesHistory);
        }
    }

    /**
     * making the status history table from data from the database
     *
     * @return status history table
     */
    private void setupResultTableView() {
        //making the columns for the status history table
        TableColumn<StatusHistory, String> dateColumn = new TableColumn<>(bundle.getString("luggage_opening_status_history_column_date"));
        dateColumn.setCellValueFactory((param) -> {
            StatusHistory historyStatus = param.getValue();
            String timeStamp = Utils.dateToString(historyStatus.getCreated());

            return new SimpleStringProperty(timeStamp);
        });

        TableColumn<StatusHistory, String> stateColumn = new TableColumn<>(bundle.getString("luggage_opening_status_history_column_status"));
        stateColumn.setCellValueFactory((param) -> {
            StatusHistory historyStatus = param.getValue();
            String status = historyStatus.getStatus().getName();

            return new SimpleStringProperty(status);
        });

        historyTableView.getColumns().setAll(dateColumn, stateColumn);
    }

    @FXML
    private void onSaveButtonAction(ActionEvent action) {

        for (int i = 0; 1 < radioButtons.size(); i++) {

            //getting the status from the radiobutton list
            RadioButton radio = radioButtons.get(i);

            //checking if the radiobutton is checked
            if (radio.isSelected()) {
                //getting the status from the box that is checked
                Status status = statuses.get(i);
                StatusHistory history = new StatusHistory(luggage.getId(), status);

                //updating it to the luggage table
                statusHistory.insert(history);
                currentStatusLabel.setText(status.getName());
                break;
            }
        }

        loadStatusHistory();
    }
}
