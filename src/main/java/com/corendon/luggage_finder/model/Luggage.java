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
 * This class represents a entry from the table 'luggages'.
 *
 * @author Tom J. Wassing
 */
public class Luggage extends DatabaseEntry {

    private String registrationId;
    private Date dateFound;
    private Airport locationFound;
    private LuggageType luggagetType;
    private Brand brand;
    private Label label;
    private Status mostRecentStatus;
    private Integer sizeWidth;
    private Integer sizeLength;
    private Integer sizeHeight;
    private Integer weight; // kg
    private Passenger passenger;
    private String characteristics;
    private Color primaryColor;
    private Color secondaryColor;
    private Insurance insurance;

    public Luggage(String registrationId,
            Date dateFound,
            Airport locationFound,
            LuggageType luggagetType,
            Brand brand,
            Label label,
            Status mostRecentStatus,
            Integer sizeWidth,
            Integer sizeLength,
            Integer sizeHeight,
            Integer weight,
            Passenger passenger,
            String characteristics,
            Color primaryColor,
            Color secondaryColor,
            Insurance insurance) {

        this(registrationId,
                dateFound,
                locationFound,
                luggagetType,
                brand,
                label,
                mostRecentStatus,
                sizeWidth,
                sizeLength,
                sizeHeight,
                weight,
                passenger,
                characteristics,
                primaryColor,
                secondaryColor,
                insurance,
                null);
        
        
    }

    public Luggage(String registrationId,
            Date dateFound,
            Airport locationFound,
            LuggageType luggagetType,
            Brand brand,
            Label label,
            Status mostRecentStatus,
            Integer sizeWidth,
            Integer sizeLength,
            Integer sizeHeight,
            Integer weight,
            Passenger passenger,
            String characteristics,
            Color primaryColor,
            Color secondaryColor,
            Insurance insurance,
            Integer id
            ) {

        this(registrationId,
                dateFound,
                locationFound,
                luggagetType,
                brand,
                label,
                mostRecentStatus,
                sizeWidth,
                sizeLength,
                sizeHeight,
                weight,
                passenger,
                characteristics,
                primaryColor,
                secondaryColor,
                id,
                insurance,
                null,
                null);
    }

    public Luggage(String registrationId,
            Date dateFound,
            Airport locationFound,
            LuggageType luggagetType,
            Brand brand,
            Label label,
            Status mostRecentStatus,
            Integer sizeWidth,
            Integer sizeLength,
            Integer sizeHeight,
            Integer weight,
            Passenger passenger,
            String characteristics,
            Color primaryColor,
            Color secondaryColor,
            Integer id,
            Insurance insurance,
            Date created,
            Date modified) {

        super(id, created, modified);
        this.registrationId = registrationId;
        this.dateFound = dateFound;
        this.locationFound = locationFound;
        this.luggagetType = luggagetType;
        this.brand = brand;
        this.label = label;
        this.mostRecentStatus = mostRecentStatus;
        this.sizeWidth = sizeWidth;
        this.sizeLength = sizeLength;
        this.sizeHeight = sizeHeight;
        this.weight = weight;
        this.passenger = passenger;
        this.characteristics = characteristics;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.insurance = insurance;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public Date getDateFound() {
        return dateFound;
    }

    public void setDateFound(Date dateFound) {
        this.dateFound = dateFound;
    }

    public Airport getLocationFound() {
        return locationFound;
    }

    public void setLocationFound(Airport locationFound) {
        this.locationFound = locationFound;
    }

    public LuggageType getLuggagetType() {
        return luggagetType;
    }

    public void setLuggagetType(LuggageType luggagetType) {
        this.luggagetType = luggagetType;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Status getMostRecentStatus() {
        return mostRecentStatus;
    }

    public Integer getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(Integer sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public Integer getSizeLength() {
        return sizeLength;
    }

    public void setSizeLength(Integer sizeLength) {
        this.sizeLength = sizeLength;
    }

    public Integer getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(Integer sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    public Color getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

}
