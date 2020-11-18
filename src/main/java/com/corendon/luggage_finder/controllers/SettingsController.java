/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Slider;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * FXML Controller class
 *
 * @author Yessin el Khaldi & Dylan Tweebeeke
 */
public class SettingsController implements Initializable {

    private ResourceBundle bundle;

    @FXML
    private ChoiceBox<String> settingsLanguageBox;

    @FXML
    private ChoiceBox<String> settingsTimezoneBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;

        settingsLanguageBox.getItems().add(rb.getString("settings_combo_box_item_lang_en"));
        settingsLanguageBox.getItems().add(rb.getString("settings_combo_box_item_lang_nl"));

        settingsTimezoneBox.getItems().setAll(getTimeZones());

    }

    private List<String> getTimeZones() {
        String[] ids = TimeZone.getAvailableIDs();
        List<String> timezones = new ArrayList<>(ids.length);

        for (String id : ids) {
            String parseTimeZone = parseTimeZone(TimeZone.getTimeZone(id));
            timezones.add(parseTimeZone);
        }

        return timezones;
    }

    private String parseTimeZone(TimeZone tz) {
        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                - TimeUnit.HOURS.toMinutes(hours);
        // avoid -4:-30 issue
        minutes = Math.abs(minutes);

        String result = "";
        if (hours > 0) {
            result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
        } else {
            result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
        }

        return result;
    }

}
