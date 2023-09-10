/**********************************************************
* 
* CSCI 470/502        Assignment 6              Summer 2023
* 
* Developer(s): Bailey Appelhans     z1759158
*               Alexander Bertolasi  z1915589
* 
* Class Name: MileageComparator
* 
* Purpose: Establishes comparator for normal miles of a
* Destination object
*
**********************************************************/

package com.example.mileredeemerapp;
import java.util.Comparator;

public class MileageComparator implements Comparator<Destination> {

    // compares normalMiles of two separate Destination objects,
    // returns difference between miles
    @Override
    public int compare(Destination d1, Destination d2) {

        return (d2.getNormalMiles() - d1.getNormalMiles());
    }
}