/**********************************************************
 *
 * CSCI 470/502        Assignment 6              Summer 2023
 *
 * Developer(s): Bailey Appelhans     z1759158
 *               Alexander Bertolasi  z1915589
 *
 * Due Date: 8/10/23
 *
 * Purpose: Runs an application that displays information about
 * possible travel destinations and redeems frequent flyer miles
 * for a customer.
 *
 **********************************************************/

package com.example.mileredeemerapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class MileRedeemerApp extends Application
{
    public static void main(String[] args)
    {
        // create a MileRedeemerApp object and call its start method
        launch(args);
    }

    // Starts/launches MileRedeemer UI
    @Override
    public void start(Stage stage) throws IOException
    {
        Parent root =
                FXMLLoader.load(getClass().getResource("MileRedeemerApp.fxml"));

        Scene scene = new Scene(root); // attach scene graph to scene
        stage.setTitle("Mile Redeemer"); //displayed in window's title
        stage.setScene(scene); // attach scene to stage
        stage.show(); // display the stage
    }
}