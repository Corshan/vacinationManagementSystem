module com.example.vacinationmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    opens main to javafx.fxml, xstream;
    exports main;
    exports controllers;
    exports Utils;
    exports models;
    opens controllers to javafx.fxml, xstream;
    opens models to xstream;
    opens Utils to xstream;
}