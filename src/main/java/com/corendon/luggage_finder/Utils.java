/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder;

import com.corendon.luggage_finder.database.tables.FunctionsTable;
import com.corendon.luggage_finder.implementables.DatabaseEntry;
import com.corendon.luggage_finder.model.User;
import javafx.fxml.FXMLLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * This class contains all kinds of utility functions.
 *
 * @author Tom J. Wassing
 * @author Peter Dimitrov
 */
public final class Utils {

    private static User activeUser;

    /**
     * Convert a {@link LocalDate} to {@link Date}.
     *
     * @param localDate LocalDate to convert.
     * @return Converted Date.
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Convert a {@link Date} to {@link LocalDate}.
     *
     * @param date Date to convert.
     * @return Converted LocalDate.
     */
    public static LocalDate dateToLocalDate(Date date) {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    /**
     * Get currently logged in user.
     *
     * @return {@link User} that is currently logged in.
     */
    public static User getActiveUser() {
        return Utils.activeUser;
    }

    /**
     * Set the currently logged in user.
     *
     * @param activeUser {@link User} that is logging in.
     */
    public static void setActiveUser(User activeUser) {
        Utils.activeUser = activeUser;
    }

    /**
     * Returns the active Locale.
     *
     * @return The active Locale.
     */
    public static Locale getCurrentLocale() {
        return new Locale("en", "EN");
    }

    /**
     * Returns the localized bundle.
     *
     * @return The localized bundle.
     */
    public static ResourceBundle getLanguageBundle() {
        return ResourceBundle.getBundle("bundles.lang", getCurrentLocale());
    }

    public static FXMLLoader createLoader(String screenFile) {
        String path = "/screens/" + screenFile;

        FXMLLoader fxmlLoader = new FXMLLoader(Utils.class.getResource(path));
        fxmlLoader.setResources(Utils.getLanguageBundle());

        return fxmlLoader;
    }

    /**
     * Checks if string is empty and not null. White spaces are trimmed.
     *
     * @param string String to check.
     * @return Whether string exists and is not empty.
     */
    public static boolean isStringFilled(String string) {
        return string != null && string.trim().length() > 0;
    }

    /**
     *
     * Converts a date to an String.
     *
     * @param date The date to be converted.
     * @return newly made string containing an date
     *
     */
    public static String dateToString(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return formatter.format(date);
    }

    /**
     * Changes the time of a date.
     *
     * @param date The date to be changed.
     * @param newTime The date the contains the new time.
     * @return The changed date with the new time.
     */
    public static Date changeTime(Date date, Date newTime) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(newTime);

        c1.set(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR_OF_DAY));
        c1.set(Calendar.MINUTE, c2.get(Calendar.MINUTE));
        c1.set(Calendar.SECOND, c2.get(Calendar.SECOND));
        c1.set(Calendar.MILLISECOND, c2.get(Calendar.MILLISECOND));

        return c1.getTime();
    }

    /**
     * Converts a date to a time only {@link String} (i.e. 13:44).
     *
     * @param date The date te be converted.
     * @return The time {@link String}.
     */
    public static String dateToTimeString(Date date) {
        DateFormat formatter = new SimpleDateFormat("HH:mm");

        return formatter.format(date);
    }

    /**
     * Returns null if the index is below 0 else the object at the index is
     * returned.
     *
     * @param <T> the list type
     * @param index the index
     * @return the object
     */
    public static <T> T getOrNull(List<T> list, int index) {
        return index >= 0 ? list.get(index) : null;
    }

    /**
     * Returns the id of a {@link DatabaseEntry} if its not null, if the entry
     * is null, the returned {@link Integer} will be null.
     *
     * @param entry the database entry
     * @return the id
     */
    public static Integer getIdOrNull(DatabaseEntry entry) {
        return entry != null ? entry.getId() : null;
    }

    /**
     * Parses a {@link String} to a {@link Integer} and returns it. If the
     * {@code string} could not be parsed (i.e. a exception is thrown) then the
     * return is null.
     *
     * @param string
     * @return a Integer on sucees and null on faillure
     */
    public static Integer parseInt(String string) {
        Integer integer = null;
        try {
            integer = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            // do nothing
        }

        return integer;
    }
    
    /**
     * The user functions as enum.
     */
    public enum Permissions {
        MANAGER (1),
        DESK_EMPLOYEE (2),
        TEAMLEADER (3);
        
        private final int positionId;
        
        Permissions(int positionId) {
            this.positionId = positionId;
        }
        
        public int getId() {
            return positionId;
        }
    }
}