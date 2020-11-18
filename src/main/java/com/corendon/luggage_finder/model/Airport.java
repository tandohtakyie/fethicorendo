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
 * This class represents a entry from the table 'airports'.
 *
 * @author Tom J. Wassing
 */
public class Airport extends DatabaseEntry {

    private String name;
    private Country country;
    private int timezone;
    private boolean daylightSaving;

    public Airport(String name, Country country, int timezone, boolean daylightSaving) {
        this(name, country, timezone, daylightSaving, null);
    }

    public Airport(String name, Country country, int timezone, boolean daylightSaving, Integer id) {
        this(name, country, timezone, daylightSaving, id, null, null);
    }

    public Airport(String name, Country country, int timezone, boolean daylightSaving, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.name = name;
        this.country = country;
        this.timezone = timezone;
        this.daylightSaving = daylightSaving;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public boolean isDaylightSaving() {
        return daylightSaving;
    }

    public void setDaylightSaving(boolean daylightSaving) {
        this.daylightSaving = daylightSaving;
    }
}
