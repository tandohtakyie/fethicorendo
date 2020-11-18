/* 
 *Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.model;

import com.corendon.luggage_finder.implementables.DatabaseEntry;
import java.util.Date;

/**
 *
 * @author Yessin el Khaldi
 */
public class Insurance extends DatabaseEntry {

    private String name;

    public Insurance(String insuranceCompany) {
        this(insuranceCompany, null);
    }

    public Insurance(String insuranceCompany, Integer id) {
        this(insuranceCompany, id, null, null);
    }

    public Insurance(String insurancyCompany, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.name = insurancyCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
