/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.model;

import com.corendon.luggage_finder.implementables.DatabaseEntry;

import java.util.Date;

/**
 * This class represents a entry from the table 'flights'.
 *
 * @author Tom J. Wassing
 */
public class Flight extends DatabaseEntry {

    private String flightNumber;

    public Flight(String flightNumber) {
        this(flightNumber, null);
    }

    public Flight(String flightNumber,  Integer id) {
        this(flightNumber, id, null, null);
    }

    public Flight(String flightNumber, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }


}
