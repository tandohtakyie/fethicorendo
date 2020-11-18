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
 *
 * @author Tom J. Wassing
 */
public class Passenger extends DatabaseEntry {

    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String zipcode;
    private String city;
    private Country country;
    private String email;
    private String phone;

    public Passenger(String firstName, String middleName, String lastName, String address, String zipcode, String city, Country country, String email, String phone) {
        this(firstName, middleName, lastName, address, zipcode, city, country, email, phone, null);
    }

    public Passenger(String firstName, String middleName, String lastName, String address, String zipcode, String city, Country country, String email, String phone, Integer id) {
        this(firstName, middleName, lastName, address, zipcode, city, country, email, phone, id, null, null);
    }

    public Passenger(String firstName, String middleName, String lastName, String address, String zipcode, String city, Country country, String email, String phone, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
