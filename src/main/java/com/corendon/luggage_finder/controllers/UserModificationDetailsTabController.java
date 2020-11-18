/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.tables.CountriesTable;
import com.corendon.luggage_finder.database.tables.FunctionsTable;
import com.corendon.luggage_finder.database.tables.UsersTable;
import com.corendon.luggage_finder.model.Country;
import com.corendon.luggage_finder.model.Function;
import com.corendon.luggage_finder.model.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Peter Dimitrov
 */
public class UserModificationDetailsTabController implements Initializable {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField prepositionTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField emailTextField;

    @FXML
    private ImageView userImage;

    @FXML
    private ChoiceBox<String> jobFunctionChoiceBox;

    @FXML
    private DatePicker employmentDatePicker;

    @FXML
    private ChoiceBox<String> jobLocationChoiceBox;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox updatePasswordCheckBox;
    
    @FXML
    private Label updatePasswordLabel;
    
    @FXML
    private Button deleteUserButton;

    private User selectedUser = null;
    private FileInputStream imageStream = null;

    List<Function> functions = new FunctionsTable().getAll(100);
    List<Country> countries = new CountriesTable().getAll(100);

    /**
     * Initialises the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Add changed focus listener to email text field.
        // It will handle checking for valid email adress.
        emailTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    String emailText = emailTextField.getText();

                    if (!emailText.matches(".+@.+\\..+") || emailText.contains(" ")) {
                        emailTextField.setStyle("-fx-text-inner-color: red;");

                        // TODO popup
                    }
                } else {
                    emailTextField.setStyle("-fx-text-inner-color: black;");
                }
            }
        });

        // Set job functions choice box
        for (Function function : functions) {
            jobFunctionChoiceBox.getItems().add(function.getName());
        }

        // Set job locations choice box
        for (Country country : countries) {
            jobLocationChoiceBox.getItems().add(country.getName());
        }
        
        if (Utils.getActiveUser().getFunction().getId() != Utils.Permissions.MANAGER.getId()) {
            firstNameTextField.setDisable(true);
            prepositionTextField.setDisable(true);
            surnameTextField.setDisable(true);
            birthDatePicker.setDisable(true);
            jobFunctionChoiceBox.setDisable(true);
            employmentDatePicker.setDisable(true);
            jobLocationChoiceBox.setDisable(true);
            updatePasswordLabel.setVisible(false);
            updatePasswordCheckBox.setVisible(false);
            deleteUserButton.setVisible(false);
        }
    }

    @FXML
    private void onDeleteButtonAction(ActionEvent event) {
        NavigationController.getInstance().changeContentNode("user_search.fxml");
    }

    @FXML
    private void onSaveButtonAction(ActionEvent event) {
        if (selectedUser == null
                && !(Utils.isStringFilled(usernameTextField.getText())
                || Utils.isStringFilled(passwordField.getText())
                || Utils.isStringFilled(firstNameTextField.getText())
                || Utils.isStringFilled(surnameTextField.getText())
                || Utils.isStringFilled(emailTextField.getText())
                || employmentDatePicker.getValue() != null
                || birthDatePicker.getValue() != null)) {

            System.out.println("Not saved");
            return;
        }

        UsersTable usersTable = new UsersTable();

        // Disgustingly nice
        String username = usernameTextField.getText().isEmpty()
                ? selectedUser.getUsername() : usernameTextField.getText();
        String firstName = firstNameTextField.getText().isEmpty()
                ? selectedUser.getFirstName() : firstNameTextField.getText();
        String namePreposition = prepositionTextField.getText();
        String surname = surnameTextField.getText().isEmpty()
                ? selectedUser.getSurname() : surnameTextField.getText();
        Date birthDate = birthDatePicker.getValue() == null
                ? selectedUser.getBirthDate() : Utils.localDateToDate(birthDatePicker.getValue());
        String email = emailTextField.getText().isEmpty()
                ? selectedUser.getEmail() : emailTextField.getText();
        Function function = functions.get(jobFunctionChoiceBox.getSelectionModel().getSelectedIndex());
        Date employmentDate = employmentDatePicker.getValue() == null
                ? selectedUser.getEmploymentDate() : Utils.localDateToDate(employmentDatePicker.getValue());
        Country country = countries.get(jobLocationChoiceBox.getSelectionModel().getSelectedIndex());
        String password = passwordField.getText().isEmpty()
                ? selectedUser.getPassword() : passwordField.getText();
        boolean passwordReset = updatePasswordCheckBox.isSelected();
        // End chunk text

        if (selectedUser != null) {
            selectedUser.setFirstName(firstName);
            selectedUser.setNamePreposition(namePreposition);
            selectedUser.setSurname(surname);
            selectedUser.setBirthDate(birthDate);
            selectedUser.setFunction(function);
            selectedUser.setEmail(email);
            selectedUser.setEmploymentDate(employmentDate);
            selectedUser.setDepartmentLocation(country);
            selectedUser.setUsername(username);
            selectedUser.setPassword(password);
            selectedUser.setImage(imageStream);
            selectedUser.setResetPassword(passwordReset);

            usersTable.update(selectedUser);
        } else {
            User newUser = new User(username, firstName, namePreposition, surname, birthDate, email, function, employmentDate, country, password, imageStream, passwordReset, 0);

            usersTable.insert(newUser);
        }

        NavigationController.getInstance().changeContentNode("user_search.fxml");
    }

    @FXML
    private void loadImage(ActionEvent event) {
        FileChooser browser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPEG Files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG Files (*.png)", "*.png");

        browser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File file = browser.showOpenDialog(null);

        if (file != null) {
            try {
                imageStream = new FileInputStream(file);
                Image img = new Image(imageStream);
                userImage.setImage(img);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UserModificationDetailsTabController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Update the user details text fields.
     */
    public void updateUserTextFields() {
        if (selectedUser == null) {
            return;
        }

        firstNameTextField.setText(selectedUser.getFirstName());
        prepositionTextField.setText(selectedUser.getNamePreposition());
        surnameTextField.setText(selectedUser.getSurname());

        LocalDate localBirthDate = Utils.dateToLocalDate(selectedUser.getBirthDate());
        birthDatePicker.setValue(localBirthDate);

        emailTextField.setText(selectedUser.getEmail());
        jobFunctionChoiceBox.setValue(selectedUser.getFunction().getName());

        LocalDate localEmploymentDate = Utils.dateToLocalDate(selectedUser.getEmploymentDate());
        employmentDatePicker.setValue(localEmploymentDate);

        jobLocationChoiceBox.setValue(selectedUser.getDepartmentLocation().getName());
        usernameTextField.setText(selectedUser.getUsername());
        //usernameTextField.setDisable(true);

        if (selectedUser.getImage() != null) {
            Image img = new Image(selectedUser.getImage());
            userImage.setImage(img);
        }

        updatePasswordCheckBox.setSelected(selectedUser.isResetPassword());
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}
