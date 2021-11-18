package controllers;

import Utils.Utilities;
import javafx.scene.control.*;
import main.Driver;
import javafx.fxml.FXML;
import models.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchMenuController {

    @FXML
    ListView<VaccinationCentre> centreListView;

    @FXML
    ListView<VaccinationBooth> boothListView;

    @FXML
    ListView<Patient> patientListView;

    @FXML
    ListView<VaccinationRecord> patientRecordsListView;

    @FXML
    ListView<VaccinationAppointment> pendingListView;

    @FXML
    ListView<String> completedListView;

    @FXML
    ChoiceBox<VaccinationCentre> centreChoiceBox;

    @FXML
    TextField centreName, centreEircode;

    @FXML
    TextField editCentreName, editCentreAddress, editCentreEircode, editCentreParkingNum;

    @FXML
    Label invalidParkingNum;

    @FXML
    TextField boothIdentifier, floorLevel;

    @FXML
    TextField editBoothIdentifier, editFloorLevel, editAccessibility;

    @FXML
    TextField searchPPSN, searchDateOfBirth;

    @FXML
    TextField editPPSN, editPatientName, editPatientAddress, editPatientPhoneNum, editPatientEmail, editPatientAccessibility;

    @FXML
    DatePicker editPatientDateOfBirth;

    @FXML
    Label invalidData, warningLabel;

    @FXML
    TextField batchNum;

    @FXML
    ComboBox<String> vaccineType;

    @FXML
    Label searchByLabel, pendingLabel, completedLabel;

    public void initialize(){
        vaccineType.getItems().addAll("AstraZeneca", "Moderna", "Janssen", "Pfizer");
    }

    public void switchToMainMenu() throws IOException {
         Driver.mainStage.setScene(Driver.mainMenu);
    }

    public void showAllCentres() {
        centreListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            centreListView.getItems().add(centre);
        }
    }

    public void showAllBooths() {
        boothListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            for (VaccinationBooth booth : centre.getBooths()) {
                boothListView.getItems().add(booth);
            }
        }
    }

    public void showAllPatients() {
        patientListView.getItems().clear();
        patientRecordsListView.getItems().clear();
        for (Patient patient : Driver.vaccinationAPI.getPatients()) {
            patientListView.getItems().add(patient);
        }
        editPPSN.clear();
        editPatientName.clear();
        editPatientDateOfBirth.setValue(null);
        editPatientAddress.clear();
        editPatientPhoneNum.clear();
        editPatientEmail.clear();
        editPatientAccessibility.clear();
    }

    public void searchCentreByName() {
        centreListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            if (centre.getName().toLowerCase().contains(centreName.getText().toLowerCase())) {
                centreListView.getItems().add(centre);
            }
        }
    }

    public void searchCentreEircode() {
        centreListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            if (centre.getEircode().toLowerCase().contains(centreEircode.getText().toLowerCase())) {
                centreListView.getItems().add(centre);
            }
        }
    }

    public void selectCentre() {
        VaccinationCentre centre = centreListView.getItems().get(centreListView.getSelectionModel().getSelectedIndex());
        editCentreName.setText(centre.getName());
        editCentreAddress.setText(centre.getAddress());
        editCentreEircode.setText(centre.getEircode());
        editCentreParkingNum.setText(centre.getParkingSpaces() + "");
    }

    public void editCentre() {
        try {
            VaccinationCentre centre = centreListView.getItems().get(centreListView.getSelectionModel().getSelectedIndex());
            centre.setName(editCentreName.getText());
            centre.setAddress(editCentreAddress.getText());
            centre.setEircode(editCentreEircode.getText());
            centre.setParkingSpaces(Integer.parseInt(editCentreParkingNum.getText()));
            invalidParkingNum.setText("");
            centreListView.getItems().clear();
            centreListView.getItems().add(centre);
            editCentreName.clear();
            editCentreEircode.clear();
            editCentreAddress.clear();
            editCentreParkingNum.clear();
            Driver.save();
        } catch (Exception e) {
            e.printStackTrace();
            invalidParkingNum.setText("Please Enter a Number");
        }
    }

    public void updateVaccinationCentre() {
        centreChoiceBox.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            centreChoiceBox.getItems().add(centre);
        }
    }

    public void showCentreBooths() {
        boothListView.getItems().clear();
        VaccinationCentre centre = centreChoiceBox.getValue();
        for (VaccinationBooth booth : centre.getBooths()) {
            boothListView.getItems().add(booth);
        }
        centreChoiceBox.setValue(null);
    }

    public void searchBoothIdentifier() {
        boothListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            for (VaccinationBooth booth : centre.getBooths()) {
                if (booth.getIdentifier().toLowerCase().contains(boothIdentifier.getText().toLowerCase())) {
                    boothListView.getItems().add(booth);
                }
            }
        }
    }

    public void searchFloorLevel() {
        boothListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            for (VaccinationBooth booth : centre.getBooths()) {
                if (Integer.parseInt(floorLevel.getText()) == booth.getFloorLevel()) {
                    boothListView.getItems().add(booth);
                }
            }
        }
    }

    public void selectBooth() {
        VaccinationBooth booth = boothListView.getItems().get(boothListView.getSelectionModel().getSelectedIndex());
        editBoothIdentifier.setText(booth.getIdentifier());
        editFloorLevel.setText(booth.getFloorLevel() + "");
        editAccessibility.setText(booth.getAccessibility());
    }

    public void editBooth() {
        try {
            if (Utilities.validBoothIdentifier(editBoothIdentifier.getText())) {
                VaccinationBooth booth = boothListView.getItems().get(boothListView.getSelectionModel().getSelectedIndex());
                booth.setIdentifier(editBoothIdentifier.getText());
                booth.setFloorLevel(Integer.parseInt(editFloorLevel.getText()));
                booth.setAccessibility(editAccessibility.getText());
                boothListView.getItems().clear();
                boothListView.getItems().add(booth);
                editBoothIdentifier.clear();
                editFloorLevel.clear();
                editAccessibility.clear();
                invalidData.setText("");
                Driver.save();
            } else {
                invalidData.setText("Invalid Booth Identifier");
            }
        } catch (Exception e) {
            e.printStackTrace();
            invalidData.setText("Please Enter a Valid Floor Level");
        }
    }

    public void searchPPSN() {
        patientListView.getItems().clear();
        for (Patient patient : Driver.vaccinationAPI.getPatients()) {
            if (patient.getPPSN().contains(searchPPSN.getText())) {
                patientListView.getItems().add(patient);
            }
        }
    }

    public void searchDateOfBirth() {
        patientListView.getItems().clear();
        for (Patient patient : Driver.vaccinationAPI.getPatients()) {
            if (patient.getDateOfBirth().contains(searchDateOfBirth.getText())) {
                patientListView.getItems().add(patient);
            }
        }
    }

    public void selectPatient() {
        Patient patient = patientListView.getItems().get(patientListView.getSelectionModel().getSelectedIndex());
        editPPSN.setText(patient.getPPSN());
        editPatientName.setText(patient.getName());
        editPatientDateOfBirth.setValue(LocalDate.parse(patient.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        editPatientAddress.setText(patient.getAddress());
        editPatientPhoneNum.setText(patient.getPhoneNum());
        editPatientEmail.setText(patient.getEmail());
        editPatientAccessibility.setText(patient.getAccessibility());
        showPatientRecords(patient);
    }

    public void editPatient() {
        if (Utilities.validPPS(editPPSN.getText())) {
            if (Utilities.validEmail(editPatientEmail.getText())) {
                Patient patient = patientListView.getItems().get(patientListView.getSelectionModel().getSelectedIndex());
                patient.setPPSN(editPPSN.getText());
                patient.setName(editPatientName.getText());
                patient.setDateOfBirth(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(editPatientDateOfBirth.getValue()));//https://stackoverflow.com/questions/18480633/java-util-date-format-conversion-yyyy-mm-dd-to-mm-dd-yyyy
                patient.setAddress(editPatientAddress.getText());
                patient.setPhoneNum(editPatientPhoneNum.getText());
                patient.setEmail(editPatientEmail.getText());
                patient.setAccessibility(editPatientAccessibility.getText());
                warningLabel.setText("");
                patientListView.getItems().clear();
                patientListView.getItems().add(patient);
                editPPSN.clear();
                editPatientName.clear();
                editPatientDateOfBirth.setValue(null);
                editPatientAddress.clear();
                editPatientPhoneNum.clear();
                editPatientEmail.clear();
                editPatientAccessibility.clear();
                try {
                    Driver.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                warningLabel.setText("Invalid Email Format");
            }
        } else {
            warningLabel.setText("Invalid PPSN");
        }
    }

    public void showPatientRecords(Patient patient) {
        patientRecordsListView.getItems().clear();
        for (VaccinationRecord record : patient.getRecords()) {
            patientRecordsListView.getItems().add(record);
        }
    }

    public void searchBatchNum() {
        vaccineType.setValue("");
        batchNumPending();
        batchNumCompleted();
        searchByLabel.setText("Batch Type");
        pendingLabel.setText(pendingListView.getItems().size() + "");
        completedLabel.setText(completedListView.getItems().size() + "");
    }

    public void batchNumPending() {
        pendingListView.getItems().clear();
        try {
            int batchNumber = Integer.parseInt(batchNum.getText());
            for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
                for (VaccinationBooth booth : centre.getBooths()) {
                    for (VaccinationAppointment appointment : booth.getAppointments()) {
                        if (appointment.getBatchNumber() == batchNumber) {
                            pendingListView.getItems().add(appointment);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void batchNumCompleted() {
        completedListView.getItems().clear();
        try {
            int batchNumber = Integer.parseInt(batchNum.getText());
            for (Patient patient : Driver.vaccinationAPI.getPatients()) {
                for (VaccinationRecord record : patient.getRecords()) {
                    if (record.getBatchNumber() == batchNumber){
                        completedListView.getItems().add(record + ", PPSN: " + patient.getPPSN());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchVaccineType(){
        batchNum.clear();
        vaccineTypePending();
        vaccineTypeCompleted();
        searchByLabel.setText("Vaccine Type");
        pendingLabel.setText(pendingListView.getItems().size() + "");
        completedLabel.setText(completedListView.getItems().size() + "");
    }

    public void vaccineTypePending(){
        pendingListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            for (VaccinationBooth booth : centre.getBooths()) {
                for (VaccinationAppointment appointment : booth.getAppointments()) {
                    if (appointment.getVaccinationType().equalsIgnoreCase(vaccineType.getValue())) {
                        pendingListView.getItems().add(appointment);
                    }
                }
            }
        }
    }

    public void vaccineTypeCompleted(){
        completedListView.getItems().clear();
        for (Patient patient : Driver.vaccinationAPI.getPatients()) {
            for (VaccinationRecord record : patient.getRecords()) {
                if (record.getVaccinationType().equalsIgnoreCase(vaccineType.getValue())){
                    completedListView.getItems().add(record + ", PPSN: " + patient.getPPSN());
                }
            }
        }
    }

}
