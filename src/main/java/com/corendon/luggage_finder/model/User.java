/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.model;

import com.corendon.luggage_finder.implementables.DatabaseEntry;

import java.io.InputStream;
import java.util.Date;

/**
 * This class represents a entry from the table 'users'.
 *
 * @author Tom J. Wassing
 * @author Petar Dimitrov
 */
public class User extends DatabaseEntry {

    private String username;
    private String firstName;
    private String namePreposition;
    private String surname;
    private Date birthDate;
    private String email;
    private InputStream image;

    private Function function;
    private Date employmentDate;
    private Country departmentLocation;

    private String password;
    private boolean resetPassword;

    public User(String username, String firstName, String namePreposition, String surname, Date birthDate, String email, Function function, Date employmentDate, Country departmentLocation, String password, InputStream image, boolean resetPassword) {
        this(username, firstName, namePreposition, surname, birthDate, email, function, employmentDate, departmentLocation, password, image, resetPassword, null);
    }

    public User(String username, String firstName, String namePreposition, String surname, Date birthDate, String email, Function function, Date employmentDate, Country departmentLocation, String password, InputStream image, boolean resetPassword, Integer id) {
        this(username, firstName, namePreposition, surname, birthDate, email, function, employmentDate, departmentLocation, password, image, resetPassword, id, null, null);
    }

    public User(String username, String firstName, String namePreposition, String surname, Date birthDate, String email, Function function, Date employmentDate, Country departmentLocation, String password, InputStream image, boolean resetPassword, Integer id, Date created, Date modified) {
        super(id, created, modified);
        this.username = username;
        this.firstName = firstName;
        this.namePreposition = namePreposition;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.function = function;
        this.employmentDate = employmentDate;
        this.departmentLocation = departmentLocation;
        this.image = image;
        this.password = password;
        this.resetPassword = resetPassword;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNamePreposition() {
        return namePreposition;
    }

    public void setNamePreposition(String namePreposition) {
        this.namePreposition = namePreposition;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Country getDepartmentLocation() {
        return departmentLocation;
    }

    public void setDepartmentLocation(Country departmentLocation) {
        this.departmentLocation = departmentLocation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }
}
