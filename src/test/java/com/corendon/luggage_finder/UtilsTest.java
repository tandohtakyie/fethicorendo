package com.corendon.luggage_finder;

import static com.corendon.luggage_finder.Utils.getCurrentLocale;
import com.corendon.luggage_finder.implementables.DatabaseEntry;
import com.corendon.luggage_finder.model.Color;
import com.corendon.luggage_finder.model.Country;
import com.corendon.luggage_finder.model.Function;
import com.corendon.luggage_finder.model.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Tom J. Wassing
 */
public class UtilsTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parseInt method, of class Utils.
     */
    @Test
    public void testParseInt() {
        Integer result = Utils.parseInt("xvsdfsdf");
        assertNull(result);

        result = Utils.parseInt("");
        assertNull(result);

        result = Utils.parseInt("1");
        Integer expectedResult = 1;
        assertEquals(expectedResult, result);
    }

    /**
     * Test of localDateToDate method, of class Utils.
     */
    @Test
    public void testLocalDateToDate() {
        try {
            System.out.println("localDateToDate");
            LocalDate localDate = LocalDate.now();

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date expResult = formatter.parse(formatter.format(new Date()));

            Date result = Utils.localDateToDate(localDate);
            assertEquals(expResult, result);
        } catch (ParseException ex) {
            Logger.getLogger(UtilsTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of dateToLocalDate method, of class Utils.
     */
    @Test
    public void testDateToLocalDate() {
        System.out.println("dateToLocalDate");
        Date date = new Date();
        LocalDate expResult = LocalDate.now();
        LocalDate result = Utils.dateToLocalDate(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of getActiveUser method, of class Utils.
     */
    @Test
    public void testGetActiveUser() {
        System.out.println("getActiveUser");
        User expResult = new User("sjors", "Sjors", "de", "Snor", new Date(), "sjors@business.com", new Function("Manager"), new Date(), new Country("NL", "Nederland"), "wachtwoord", null, false);
        Utils.setActiveUser(expResult);
        User result = Utils.getActiveUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of setActiveUser method, of class Utils.
     */
    @Test
    public void testSetActiveUser() {
        System.out.println("setActiveUser");
        User activeUser = new User("sjors", "Sjors", "de", "Snor", new Date(), "sjors@business.com", new Function("Manager"), new Date(), new Country("NL", "Nederland"), "wachtwoord", null, false);
        Utils.setActiveUser(activeUser);
        User result = Utils.getActiveUser();
        assertEquals(activeUser, result);
    }

    /**
     * Test of isStringFilled method, of class Utils.
     */
    @Test
    public void testIsStringFilled() {
        System.out.println("isStringFilled");
        String string = "";
        boolean expResult = false;
        boolean result = Utils.isStringFilled(string);
        assertEquals(expResult, result);

        string = "not empty";
        expResult = true;
        result = Utils.isStringFilled(string);
        assertEquals(expResult, result);

        string = " ";
        expResult = false;
        result = Utils.isStringFilled(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of dateToString method, of class Utils.
     */
    @Test
    public void testDateToString() {
        System.out.println("dateToString");
        Date date = new Date(1000);
        String expResult = "01/01/1970 01:00:01";
        String result = Utils.dateToString(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrNull method, of class Utils.
     */
    @Test
    public void testGetOrNull() {
        System.out.println("getOrNull");

        List<Object> entryList = new ArrayList<>();
        entryList.add(new Object());
        Object expResult = entryList.get(0);

        Object result = Utils.getOrNull(entryList, 0);
        assertEquals(expResult, result);

        expResult = null;
        result = Utils.getOrNull(entryList, -1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdOrNull method, of class Utils.
     */
    @Test
    public void testGetIdOrNull() {
        System.out.println("getIdOrNull");
        DatabaseEntry entry = new Color("Red", 3024, 1);
        Integer expResult = 1;
        Integer result = Utils.getIdOrNull(entry);
        assertEquals(expResult, result);

        entry = null;
        result = Utils.getIdOrNull(entry);
        assertNull(result);
    }

    /**
     * Test of dateToTimeString method, of class Utils.
     */
    @Test
    public void dateToTimeString() {
        System.out.println("dateToTimeString");

        Date date = new Date(1000);
        String expResult = "01:00";
        String result = Utils.dateToTimeString(date);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentLocale method, of class Utils.
     */
    @Test
    public void testGetCurrentLocale() {
        System.out.println("getCurrentLocale");
        Locale expResult = new Locale("en", "EN");
        Locale result = Utils.getCurrentLocale();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLanguageBundle method, of class Utils.
     */
    @Test
    public void testGetLanguageBundle() {
        System.out.println("getLanguageBundle");
        ResourceBundle expResult = ResourceBundle.getBundle("bundles.lang", getCurrentLocale());
        ResourceBundle result = Utils.getLanguageBundle();
        assertEquals(expResult, result);
    }

    /**
     * Test of createLoader method, of class Utils.
     */
    @Test
    public void testCreateLoader() {
        System.out.println("createLoader");
        String screenFile = "/screens/home.fxml";
        FXMLLoader result = Utils.createLoader(screenFile);
        assertNotEquals(result.getResources(), null);
    }

    /**
     * Test of dateToTimeString method, of class Utils.
     */
    @Test
    public void testDateToTimeString() {
        System.out.println("dateToTimeString");
        Date date = new Date(1000);
        String expResult = "01:00";
        String result = Utils.dateToTimeString(date);
        assertEquals(expResult, result);
    }
}