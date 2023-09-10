/**********************************************************
* 
* CSCI 470/502        Assignment 6              Summer 2023
* 
* Developer(s): Bailey Appelhans     z1759158
*               Alexander Bertolasi  z1915589
* 
* Class Name: MileRedeemer
* 
* Purpose: Establishes an array of destinations, frequent
* flyer miles to use, and methods to redeem miles
*
**********************************************************/

package com.example.mileredeemerapp;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.Collections;

public class MileRedeemer {

  private Destination[] places; // array of destinations the customer can go to
  private int remainingMiles; // customer's remaining miles after miles have been redeemed

  // returns remaining miles
  public int getRemainingMiles() {
    return remainingMiles;
  }

  // return places array
  public Destination[] getPlaces() {
    return places;
  }

  // loops through input file of destinations, creates new Destination object for each line of input,
  // places Destination objects in places array, then sorts them in descending order by normalMiles
  public void readDestinations(Scanner filescanner) {
    String name; // city name
    int normalMiles, // miles needed during busy season
      discountMiles, // miles needed during off season
      upgradeMiles, // miles needed to upgrade
      offSznStart, // off season start month
      offSznEnd; // off season end month
    String line; // string holding an individual line of input at a time
    String[] input; // array for parsed line of input
    Destination[] temp; // temporary array to hold destination objects

    while (filescanner.hasNextLine()) {
      line = filescanner.nextLine(); // grab a line of input
      input = line.split(";"); // parse input into an array of substrings

      // assign values
      name = input[0];
      normalMiles = Integer.valueOf(input[1]);
      discountMiles = Integer.valueOf(input[2]);
      upgradeMiles = Integer.valueOf(input[3]);
      String[] offSzn = input[4].split("-");
      offSznStart = Integer.valueOf(offSzn[0]);
      offSznEnd = Integer.valueOf(offSzn[1]);

      // create new Destination object
      Destination currDest = new Destination(name, normalMiles, discountMiles, upgradeMiles, offSznStart, offSznEnd);

      // use temp array to add new Destination object to places array
      if(places != null) {
        temp = Arrays.copyOf(places, places.length + 1);
        temp[places.length] = currDest;
      } else {
        temp = new Destination[]{currDest};
      }
      places = temp;
    }

    // create an ArrayList of Destination objects
    List<Destination> destList = Arrays.asList(places);

    // sort ArrayList and array of Destination objects in descending order by normalMiles
    Collections.sort(destList, new MileageComparator());
  }

  // returns an array of strings of all city names in alphabetical order
  public String[] getCityNames() {
    String[] names = new String[places.length]; // string of city names

    // builds new array of city names
    for (int i = 0; i < places.length; ++i) {
      names[i] = places[i].getName();
    }
    // sorts string array
    Arrays.sort(names);
    return names;
  }

  // determines where a user can travel based on ff miles and month of travel, returns an array of strings
  // with information regarding where user can travel and in what class
  public String[] redeemMiles(int miles, int month) {
    remainingMiles = miles; // set remainingMiles to miles entered by user
    Destination[] travelDestinations = new Destination[0]; // array of destinations customer can travel to
    Destination[] temp; // temp array to help build new travelDestination array

    // loop through array of possible travel destinations
    for (int i = 0; i < places.length; ++i) {

      // if customer is traveling in off season
      if (month >= places[i].getOffSznStart() && month <= places[i].getOffSznEnd()) {
        // if customer has enough remaining miles to travel here, subtract discountMiles from
        // remainingMiles and add destination to travelDestinations array
        if (remainingMiles > places[i].getDiscountMiles()) {
          if (travelDestinations != null) {
            temp = Arrays.copyOf(travelDestinations, travelDestinations.length + 1);
            temp[travelDestinations.length] = places[i];
          } else {
            temp = new Destination[]{places[i]};
          }
          travelDestinations = temp;
          remainingMiles -= places[i].getDiscountMiles();
        }
      } else { // if customer is traveling in busy season
          // if customer has enough remaining miles to travel here, subtract normalMiles from
          // remainingMiles and add destination to travelDestinations array
          if (remainingMiles > places[i].getNormalMiles()) {
            if(travelDestinations != null) {
              temp = Arrays.copyOf(travelDestinations, travelDestinations.length + 1);
              temp[travelDestinations.length] = places[i];
            } else {
              temp = new Destination[]{places[i]};
            }
            travelDestinations = temp;
            remainingMiles -= places[i].getNormalMiles();
          }
      }
    }
    // array of strings with info about where customer can travel (and in what class)
    String[] results = new String[travelDestinations.length];

    // loop through travelDestinations array, determine if user has enough remainingMiles
    // to upgrade to first class for each destination. Subtract upgradeMiles from remainingMiles
    // if they do. Build new string array based on this information.
    for (int i = 0; i < travelDestinations.length; ++i) {
      if (remainingMiles > travelDestinations[i].getUpgradeMiles()) {
        remainingMiles -= travelDestinations[i].getUpgradeMiles();
        results[i] = "* A trip to " + travelDestinations[i].getName() + " in First Class";
      }
      else {
        results[i] = "* A trip to " + travelDestinations[i].getName() + " in Economy Class";
      }
    }
    return results;
  }
}