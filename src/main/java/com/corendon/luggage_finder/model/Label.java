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
 * This class represents a entry from the table 'labels'.
 *
 * @author Tom J. Wassing
 */
public class Label extends DatabaseEntry {

    private String tag;
    private Flight flight;

    public Label(String tag, Flight flight) {
        this(tag, flight, null);
    }

    public Label(String tag, Flight flight, Integer id) {
        this(tag, flight, id, null, null);
    }

    public Label(String tag, Flight flight, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.tag = tag;
        this.flight = flight;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
