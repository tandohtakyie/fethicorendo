/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.tables.UsersTable;
import com.corendon.luggage_finder.model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Vince de Leeuw
 */
public class UserSearchController implements Initializable {

    @FXML
    private TableView<User> tableview;

    @FXML
    private TextField userFilterSearchField;

    @FXML
    private Label searchInfoLabel;

    private ResourceBundle bundle;
    private UsersTable usersTable;
    private ObservableList<User> resultsList = FXCollections.observableArrayList();

    /**
     * This method initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
        usersTable = new UsersTable();
        setupResultsTableView();
        search();
    }

    /**
     * This method will bring you to the add user tab
     *
     * @param event the event
     */
    @FXML
    private void onAddUserButtonAction(ActionEvent event) {
        NavigationController.getInstance().changeContentNode("user_modification_navigation_tab.fxml");
    }

    /**
     * This method searches for the users
     *
     * @param event the event
     */
    @FXML
    private void onUserSearchButtonAction(ActionEvent event) {
        search();
    }

    /**
     * This method performs a search if the release key is equal to ENTER. The
     * method should be called by
     * {@link UserSearchController#userFilterSearchField}.
     *
     * @param event The event.
     */
    @FXML
    private void onSearchFieldKeyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            search();
        }
    }

    /**
     * This method allows you to double click on a name in the userSearch tab
     * which redirects you to that users information tab.
     *
     * @param event the event
     */
    @FXML
    private void onTableViewItemClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader fxmlLoader = Utils.createLoader("user_modification_navigation_tab.fxml");
                Parent umntParent = fxmlLoader.load();
                NavigationController.getInstance().changeContentParent(umntParent);

                UserModificationNavigationTabController umntController = fxmlLoader.getController();
                User user = resultsList.get(tableview.getSelectionModel().getFocusedIndex());

                umntController.setSelectedUser(user);
            } catch (IOException ex) {
                Logger.getLogger(UserSearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method retrieves the input from the
     * {@link UserSearchController#userFilterSearchField} and performs a search.
     */
    private void search() {
        long startTime = System.currentTimeMillis();

        List<User> users = usersTable.search(userFilterSearchField.getText());

        long endTime = System.currentTimeMillis() - startTime;

        if (users.size() == 1) {
            searchInfoLabel.setText(String.format(bundle.getString("general_search_single_result_statistics"), users.size(), endTime));
        } else {
            searchInfoLabel.setText(String.format(bundle.getString("general_search_multiple_results_statistics"), users.size(), endTime));
        }

        resultsList.setAll(users);
    }

    /**
     * This method shows the data in the userSearch tab which it pulls from the
     * database
     */
    private void setupResultsTableView() {
        TableColumn<User, String> nameColumn = new TableColumn<>(bundle.getString("users_results_column_name"));

        nameColumn.setCellValueFactory((param)
                -> new SimpleStringProperty(param.getValue().getFirstName()
                        + " " + param.getValue().getNamePreposition()
                        + " " + param.getValue().getSurname()));

        TableColumn<User, String> functionColumn = new TableColumn<>(bundle.getString("users_results_column_function"));
        functionColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getFunction().getName()));

        TableColumn<User, String> ageColumn = new TableColumn<>(bundle.getString("users_results_column_age"));
        ageColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getBirthDate().toString()));

        TableColumn<User, String> recruitmentColumn = new TableColumn<>(bundle.getString("users_results_column_enlistment"));
        recruitmentColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getEmploymentDate().toString()));

        TableColumn<User, String> locationColumn = new TableColumn<>(bundle.getString("users_results_column_location"));
        locationColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getDepartmentLocation().getName()));

        tableview.getColumns().setAll(nameColumn, functionColumn, ageColumn, recruitmentColumn, locationColumn);
        tableview.setItems(resultsList);
    }
}
