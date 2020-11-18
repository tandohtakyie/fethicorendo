/*
 * Fasten Your Seatbelt
 * Corendon
 *
 * 2017 (c) IS108 Groep 4 - Tom J. Wassing, Vince de Leeuw, Dylan Tweebeeke, Yessin el Khaldi, Fethi K. Tewelde, Petar Dimitrov
 */
package com.corendon.luggage_finder.controllers;

import com.corendon.luggage_finder.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Dylan Tweebeeke
 */
public class HomeController implements Initializable {

    //loading in the arrays needed in this controller
    private final static String quotes[] = new String[]{" ", "When you reach the end of your rope, tie a knot in it and hang on.\n"
        + "-Franklin D. Roosevelt", "You cannot shake hands with a clenched fist.\n"
        + "-dira Gandhi", "It is better to be feared than loved, if you cannot be both.\n"
        + "-Niccolo Machiavelli", "If you cannot do great things, do small things in a great way.\n"
        + "-Napoleon Hill", "The supreme art of war is to subdue the enemy without fighting.\n"
        + "-Sun Tzu", "Independence is happiness.\n"
        + "-Susan B. Anthony", "Happiness can exist only in acceptance.\n"
        + "-George Orwell", "The journey of a thousand miles begins with one step.\n"
        + "-Lao Tzu"
        + " ", "I have not failed. I've just found 10,000 ways that won't work.\n"
        + "-Thomas A. Edison", "A leader is one who knows the way, goes the way, and shows the way.\n"
        + "-John C. Maxwell", "The secret of getting ahead is getting started.\n"
        + "-Mark Twain", "If opportunity doesn't knock, build a door.\n"
        + "-Milton Berle", "The World is my country, all mankind are my brethren, and to do good is my religion.\n"
        + "-Thomas Paine", "Problems are not stop signs, they are guidelines.\n"
        + "-Robert H. Schuller", "A single rose can be my garden... a single friend, my world.\n"
        + "-Leo Buscaglia", "You don't choose your family. They are God's gift to you, as you are to them.\n"
        + "-Desmond Tutu"
        + " ", "Friends show their love in times of trouble, not in happiness.\n"
        + "-Euripides", "Life is not a problem to be solved, but a reality to be experienced.\n"
        + "-Soren Kierkegaard", "The only true wisdom is in knowing you know nothing.\n"
        + "-Socrates", "Believe you can and you're halfway there.\n"
        + "-Theodore Roosevelt", "Education is the most powerful weapon which you can use to change the world.\n"
        + "-Nelson Mandela", "Change your thoughts and you change your world.\n"
        + "-Norman Vincent Peale", "Try to be a rainbow in someone's cloud.\n"
        + "-Maya Angelou", "The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.\n"
        + "-Helen Keller"
        + " ", "The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.\n"
        + "-Helen Keller", "Find a place inside where there's joy, and the joy will burn out the pain.\n"
        + "-Joseph Campbell", "Wise men speak because they have something to say; Fools because they have to say something.\n"
        + "-Plato", "There is only one corner of the universe you can be certain of improving, and that's your own self.\n"
        + "-Aldous Huxley", "Good judgment comes from experience, and a lot of that comes from bad judgment.\n"
        + "-Will Rogers", "Learning never exhausts the mind.\n"
        + "Leonardo da Vinci", "Let us sacrifice our today so that our children can have a better tomorrow.\n"
        + "-A. P. J. Abdul Kalam"};

    //declaring the fxml:id atributes;
    @FXML
    private Label quoteLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private Label greetingLabel;

    @FXML
    private ImageView timeImage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //setting the name of the user that is logged in
        userNameLabel.setText(Utils.getActiveUser().getFirstName());

        //loading in the images
        Image nightImage = new Image("images/moon_icon.png");
        Image dayImage = new Image("images/sun_icon.png");

        //getting the time 
        GregorianCalendar time = new GregorianCalendar();
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int day = time.get(Calendar.DAY_OF_MONTH);
        int day2 = time.get(Calendar.DAY_OF_MONTH);

        //checking how late it is and depending the greeting on that
        String greetings[] = new String[]{
                rb.getString("home_greeting_good_morning"),
                rb.getString("home_greeting_good_afternoon"),
                rb.getString("home_greeting_good_evening")};

        if (hour < 12) {
            greetingLabel.setText(greetings[0]);
            timeImage.setImage(dayImage);
        } else if (hour <= 18 && hour >= 12) {
            greetingLabel.setText(greetings[1]);
            timeImage.setImage(dayImage);
        } else {
            greetingLabel.setText(greetings[2]);
            timeImage.setImage(nightImage);
        }

        //setting the quote by the day of the month
        quoteLabel.setText(quotes[day]);

    }
}
