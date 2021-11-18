package controllers;

import main.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Patient;
import models.VaccinationBooth;
import models.VaccinationCentre;
import Utils.Utilities;

import java.time.format.DateTimeFormatter;

public class AddMenuController {

    private VaccinationAPI vaccinationAPI = Driver.vaccinationAPI;
    @FXML
    TextField centreName, centreAddress, centreEircode, centreParkingSpaceNum;

    @FXML
    TextField boothIdentifier, floorLevel, accessibility;

    @FXML
    TextField patientName, PPSN, phoneNum, patientAddress, patientEmail, patientAccessibility;

    @FXML
    Label invalidPPSN, invalidPhoneNum, invalidEmail, invalidDateOfBirth;

    @FXML
    DatePicker dateOfBirth;

    @FXML
    Label parkingSpaceNumWarning, floorLevelWarning, boothIdentifierWarning;

    @FXML
    ChoiceBox<VaccinationCentre> centreChoiceBox;


    public void switchToMainMenu() {
        Driver.mainStage.setScene(Driver.mainMenu);
    }

    public void addVaccinationCentre() {
        try {
            vaccinationAPI.addVaccinationCentre(new VaccinationCentre(centreName.getText(), centreAddress.getText(), centreEircode.getText(), Integer.parseInt(centreParkingSpaceNum.getText())));
            centreName.clear();
            centreAddress.clear();
            centreEircode.clear();
            centreParkingSpaceNum.clear();
            parkingSpaceNumWarning.setText("");
            Driver.save();
        } catch (Exception e) {
            e.printStackTrace();
            parkingSpaceNumWarning.setText("Please Enter a Number!");
        }
    }

    public void addVaccinationBooth() {
        try {
            if (Utilities.validBoothIdentifier(boothIdentifier.getText())) {
                VaccinationBooth booth = new VaccinationBooth(boothIdentifier.getText(), Integer.parseInt(floorLevel.getText()), accessibility.getText());
                vaccinationAPI.addVaccinationBooth(centreChoiceBox.getValue(), booth);
                centreChoiceBox.setValue(null);
                boothIdentifier.clear();
                floorLevel.clear();
                accessibility.clear();
                floorLevelWarning.setText("");
                boothIdentifierWarning.setText("");
                Driver.save();
            }else {
                boothIdentifierWarning.setText("Invalid Identifier");
            }
        } catch (Exception e) {
            e.printStackTrace();
            floorLevelWarning.setText("Please Enter a number!");
        }
    }

    public void addPatient() {
        if (Utilities.validPPS(PPSN.getText())) {
            if (Utilities.onlyContainsNumbers(phoneNum.getText())) {
                if (Utilities.validEmail(patientEmail.getText())) {
                    if (dateOfBirth.getValue() != null) {
                        Patient patient = new Patient(PPSN.getText(), patientName.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy").format(dateOfBirth.getValue()), patientAddress.getText(), phoneNum.getText(), patientEmail.getText(), patientAccessibility.getText()); //https://stackoverflow.com/questions/18480633/java-util-date-format-conversion-yyyy-mm-dd-to-mm-dd-yyyy
                        vaccinationAPI.addPatient(patient);
                        PPSN.clear();
                        patientName.clear();
                        dateOfBirth.getEditor().clear();
                        patientAddress.clear();
                        phoneNum.clear();
                        patientAddress.clear();
                        patientEmail.clear();
                        patientAccessibility.clear();
                        invalidPPSN.setText("");
                        invalidPhoneNum.setText("");
                        invalidEmail.setText("");
                        invalidDateOfBirth.setText("");
                        try {
                            Driver.save();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    } else {
                        invalidDateOfBirth.setText("Enter Date");
                    }
                } else {
                    invalidEmail.setText("INVALID EMAIL");
                }
            } else {
                invalidPhoneNum.setText("INVALID NUMBER");
            }
        } else {
            invalidPPSN.setText("INVALID PPSN");
        }
    }

    public void updateVaccinationCentre() {
        centreChoiceBox.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            centreChoiceBox.getItems().add(centre);
        }
    }

    public void clear() {
        centreName.clear();
        centreAddress.clear();
        centreEircode.clear();
        centreParkingSpaceNum.clear();
        centreChoiceBox.setValue(null);
        boothIdentifier.clear();
        floorLevel.clear();
        accessibility.clear();
        PPSN.clear();
        patientName.clear();
        dateOfBirth.getEditor().clear();
        patientAddress.clear();
        phoneNum.clear();
        patientEmail.clear();
        patientAccessibility.clear();
    }
}
