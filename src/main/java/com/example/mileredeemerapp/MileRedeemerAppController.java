/**********************************************************
 *
 * CSCI 470/502        Assignment 6              Summer 2023
 *
 * Developer(s): Bailey Appelhans     z1759158
 *               Alexander Bertolasi  z1915589
 *
 * Class Name: MileRedeemerAppController
 *
 * Purpose: Controller class responsible for instance variables
 * and methods in the MileRedeemer UI
 *
 **********************************************************/

package com.example.mileredeemerapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MileRedeemerAppController {

    FileChooser fileChooser = new FileChooser(); // FileChooser to select file

    MileRedeemer mr = new MileRedeemer(); // instantiate MileRedeemer object
    Destination[] places; // array of Destination objects for easy access

    // array of strings representing departure month
    ObservableList<String> months = FXCollections.observableArrayList("January", "February",
            "March", "April", "May", "June", "July", "August", "September", "October",
            "November", "December");

    // departure month choice box
    @FXML
    private ChoiceBox<String> departMonthChoiceBox;

    // initialize departure month choice box with string array of months
    public void initialize() {
        departMonthChoiceBox.setItems(months);
    }

    // list of possible destinations to visit
    @FXML
    private ListView<String> destinationList;

    // text field for current destination's normal miles
    @FXML
    private TextField normalMilesTextField;

    // text field for user's remaining miles after redeeming miles
    @FXML
    private TextField remainingMilesTextField;

    // ListView area to display results after redeeming miles
    @FXML
    private ListView<String> resultsTextField;

    // text field for current destination's supersaver miles
    @FXML
    private TextField ssMilesTextField;

    // text field for current destination's supersaver months
    @FXML
    private TextField ssMonthsTextField;

    // text field for current destination's miles needed for upgrade
    @FXML
    private TextField upgradeMilesTextField;

    // text field where user enters the number of miles they wish to redeem
    @FXML
    private TextField userMilesTextField;

    // event occurs when user clicks "Redeem Miles" button - takes their input
    // and passes it into MileRedeemer's redeemMiles function to return a String
    // array of results, then displays results in results ListView area
    @FXML
    void redeemMiles(ActionEvent event) {
        // clear listView of any previous results
        resultsTextField.getItems().clear();

        String[] results; // array of Strings representing results
        try {
            results = mr.redeemMiles(Integer.valueOf(userMilesTextField.getText()),
                    convertMonth(departMonthChoiceBox.getValue()));

            // loop through results array and print it in results listView
            for (int i = 0; i < results.length; ++i) {
                resultsTextField.getItems().add(results[i]);
            }

            // if results array is empty/user doesn't have enough miles to make any trips,
            // place message inside results listView
            if (results.length == 0) {
                resultsTextField.getItems().add("*** Your client has not accumulated enough Frequent Flyer Miles ***");
            }

            // set remaining miles textfield to user's remaining miles
            remainingMilesTextField.setText(Integer.toString(mr.getRemainingMiles()));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    // event occurs when user clicks "Select Destinations File" button - opens new window
    // prompting user to select a .txt file of destinations. Displays destinations inside
    // destinations ListView area.
    @FXML
    void selectFile(ActionEvent event) {

        // clears listView of anything already present
        destinationList.getItems().clear();

        File file = fileChooser.showOpenDialog(new Stage()); // file that user selects
        Scanner sc = null; // scanner used to read input from file
        try { // try to read file
            sc = new Scanner(file);
        } catch (FileNotFoundException e) { // print error if file not found
            System.out.println(e);
        } finally {
            mr.readDestinations(sc); // read in destinations from file
            places = mr.getPlaces(); // an array of Destination objects read in from file
            String cities[] = mr.getCityNames(); // String array of city names

            // Loops through cities array and adds each item to the destinations ListView
            for (int i = 0; i < cities.length; ++i) {
                destinationList.getItems().add(cities[i]);
            }
        }
    }

    // displays destination information when a destination is clicked in the
    // destinations listView
    @FXML
    void showDestinationInfo(MouseEvent event) {
        // String representing selected destination
        String currentDestination = destinationList.getSelectionModel().getSelectedItem();

        // loop through places array until destination selected matches index
        for (int i = 0; i < places.length; ++i) {
            if (currentDestination.equals(places[i].getName())) {
                // set miles textfields to their corresponding values in the array
                normalMilesTextField.setText(Integer.toString(places[i].getNormalMiles()));
                ssMilesTextField.setText(Integer.toString(places[i].getDiscountMiles()));
                upgradeMilesTextField.setText(Integer.toString(places[i].getUpgradeMiles()));

                // convert off season months from int to Strings
                String startMonth = convertMonth(places[i].getOffSznStart());
                String endMonth = convertMonth(places[i].getOffSznEnd());

                // set off season months textfield to corresponding months
                ssMonthsTextField.setText(startMonth + " - " + endMonth);
            }
        }
    }

    // converts a month in int form to its corresponding name as a String
    String convertMonth(int month) {
        String monthString; // String result representing name of month once converted

        switch (month) {
            case 1:  monthString = "January";
                break;
            case 2:  monthString = "February";
                break;
            case 3:  monthString = "March";
                break;
            case 4:  monthString = "April";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "June";
                break;
            case 7:  monthString = "July";
                break;
            case 8:  monthString = "August";
                break;
            case 9:  monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }
        return monthString;
    }

    // converts month in String form to its corresponding number as an int
    int convertMonth(String month) {
        int numMonth = 0; // int result representing month in number format once converted

        switch (month) {
            case "January":  numMonth = 1;
                break;
            case "February": numMonth = 2;
                break;
            case "March":  numMonth = 3;
                break;
            case "April":  numMonth = 4;
                break;
            case "May":  numMonth = 5;
                break;
            case "June":  numMonth = 6;
                break;
            case "July":  numMonth = 7;
                break;
            case "August":  numMonth = 8;
                break;
            case "September":  numMonth = 9;
                break;
            case "October": numMonth = 10;
                break;
            case "November": numMonth = 11;
                break;
            case "December": numMonth = 12;
                break;
        }

        return numMonth;
    }
}
