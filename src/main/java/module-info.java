module com.example.mileredeemerapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mileredeemerapp to javafx.fxml;
    exports com.example.mileredeemerapp;
}