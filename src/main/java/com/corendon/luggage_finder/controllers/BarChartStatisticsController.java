/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import com.corendon.luggage_finder.database.Statistics;
import com.corendon.luggage_finder.database.tables.LuggagesTable;
import com.corendon.luggage_finder.excel.Excel;
import com.corendon.luggage_finder.model.Luggage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class.
 *
 * @author Yessin el Khaldi
 * @author Peter Dimitrov
 */
public class BarChartStatisticsController implements Initializable {

    @FXML
    private ToggleGroup chartTypeToggleGroup;
    @FXML
    private PieChart pieChart;

    private enum SelectedStatistic {
        GENERATE_FOUND_PLACES_STATISTIC,
        GENERATE_LUGGAGE_STATUSES_STATISTIC,
        GENERATE_LOST_PER_DAY_STATISTIC,
        GENERATE_LOST_PER_MONTH_STATISTIC,
        GENERATE_LOST_PER_YEAR_STATISTIC
        
    }

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;

    private BarChart<String, Number> barChart;

    private ResourceBundle bundle;
    private SelectedStatistic statisticToGenerate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
        statisticToGenerate = SelectedStatistic.GENERATE_LUGGAGE_STATUSES_STATISTIC;

        Date from = new Date(1);
        Date to = getDate();

        generateStatistic(from, to);
    }

    @FXML
    private void onGenerateButtonAction(ActionEvent event) {
        Date from = fromDatePicker.getValue() != null ? Utils.localDateToDate(fromDatePicker.getValue()) : new Date(1);
        Date to = toDatePicker.getValue() != null ? Utils.localDateToDate(toDatePicker.getValue()) : getDate();

        generateStatistic(from, to);
    }

    @FXML
    private void onLostPerDayRadioAction(ActionEvent event) {
        statisticToGenerate = SelectedStatistic.GENERATE_LOST_PER_DAY_STATISTIC;
    }

    @FXML
    private void onLostPerMonthRadioAction(ActionEvent event) {
        statisticToGenerate = SelectedStatistic.GENERATE_LOST_PER_MONTH_STATISTIC;
    }

    @FXML
    private void onLostPerYearRadioAction(ActionEvent event) {
        statisticToGenerate = SelectedStatistic.GENERATE_LOST_PER_YEAR_STATISTIC;
    }

    @FXML
    private void onLuggageStatusesRadioAction(ActionEvent event) {
        statisticToGenerate = SelectedStatistic.GENERATE_LUGGAGE_STATUSES_STATISTIC;
    }

    @FXML
    private void onPlacesFoundRadioAction(ActionEvent event) {
        statisticToGenerate = SelectedStatistic.GENERATE_FOUND_PLACES_STATISTIC;
    }

    @FXML
    private void onExportButtonAction(ActionEvent event) {
        // checking is the dates are set
        LocalDate localStartDate = fromDatePicker.getValue();
        LocalDate localEndDate = toDatePicker.getValue();

        if (localStartDate == null || localEndDate == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle(bundle.getString("statistics_alert_missing_date_title"));
            alert.setHeaderText(bundle.getString("statistics_alert_missing_date_header"));
            alert.setContentText(bundle.getString("statistics_alert_missing_date_content"));

            alert.show();
            return;
        }

        // showing file picker
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(filter);

        Window window = ((Node) event.getTarget()).getScene().getWindow();
        File file = fileChooser.showSaveDialog(window);
        if(!file.getName().contains(".")) {
            file = new File(file.getAbsolutePath() + ".xlsx");
        }

        Date starDate = Utils.localDateToDate(fromDatePicker.getValue());
        Date endDate = Utils.localDateToDate(toDatePicker.getValue());

        // retrieving selection
        LuggagesTable luggagesTable = new LuggagesTable();
        List<Luggage> luggages = luggagesTable.getBetweenDates(starDate, endDate);

        try {
            Excel.toExcel(luggages, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateStatistic(Date fromDate, Date toDate) {
        Statistics stats = new Statistics();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        Map<String, Number> luggageStatuses = null;

        switch (statisticToGenerate) {
            case GENERATE_FOUND_PLACES_STATISTIC:
                luggageStatuses = stats.generateRapportFoundLocations(fromDate, toDate);
                break;

            case GENERATE_LUGGAGE_STATUSES_STATISTIC:
                luggageStatuses = stats.generateLuggageStatusRapport(fromDate, toDate);
                break;

            case GENERATE_LOST_PER_DAY_STATISTIC:
                luggageStatuses = stats.generateRapportByDay(fromDate, toDate);
                break;

            case GENERATE_LOST_PER_MONTH_STATISTIC:
                luggageStatuses = stats.generateRapportByMonth(fromDate, toDate);
                break;

            case GENERATE_LOST_PER_YEAR_STATISTIC:
                luggageStatuses = stats.generateRapportByYear(fromDate, toDate);
                break;

            default:
                break;
        }

        for (Map.Entry<String, Number> entry : luggageStatuses.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().setAll(series);
    }

    private Date getDate() {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DATE, 1);

        return cal.getTime();
    }
}
