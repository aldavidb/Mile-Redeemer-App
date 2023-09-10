package com.example.mileredeemerapp;

/**********************************************************
* 
* CSCI 470/502        Assignment 6              Summer 2023
* 
* Developer(s): Bailey Appelhans     z1759158
*               Alexander Bertolasi  z1915589
* 
* Class Name: Destination
* 
* Purpose: Declares class to represent destinations to
* travel to.
*
**********************************************************/

public class Destination {
  private String name; // name of city
  private int normalMiles; // frequent flyer miles needed during busy season
  private int discountMiles; // frequent flyer miles needed during off season
  private int upgradeMiles; // frequent flyer miles needed to upgrade to first class
  private int offSznStart; // off season start month
  private int offSznEnd; // off season end month

  // constructor
  public Destination(String name, int normalMiles, int discountMiles, int upgradeMiles, int offSznStart, int offSznEnd) {
    this.name = name;
    this.normalMiles = normalMiles;
    this.discountMiles = discountMiles;
    this.upgradeMiles = upgradeMiles;
    this.offSznStart = offSznStart;
    this.offSznEnd = offSznEnd;
  }

  // returns Destination name
  public String getName() {
    return name;
  }

  // sets name
  public void setName(String name) {
    this.name = name;
  }

  // returns normalMiles
  public int getNormalMiles() {
    return normalMiles;
  }

  // sets normalMiles
  public void setNormalMiles(int normalMiles) {
    this.normalMiles = normalMiles;
  }

  // returns discountMiles
  public int getDiscountMiles() {
    return discountMiles;
  }

  // sets discountMiles
  public void setDiscountMiles(int discountMiles) {
    this.discountMiles = discountMiles;
  }

  // returns upgradeMiles
  public int getUpgradeMiles() {
    return upgradeMiles;
  }

  // sets upgradeMiles
  public void setUpgradeMiles(int upgradeMiles) {
    this.upgradeMiles = upgradeMiles;
  }

  // returns offSznStart
  public int getOffSznStart() {
    return offSznStart;
  }

  // sets offSznStart
  public void setOffSznStart(int offSznStart) {
    this.offSznStart = offSznStart;
  }

  // returns offSznEnd
  public int getOffSznEnd() {
    return offSznEnd;
  }

  // sets offSznEnd
  public void setOffSznEnd(int offSznEnd) {
    this.offSznEnd = offSznEnd;
  }
}