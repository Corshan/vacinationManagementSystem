package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import main.Driver;
import models.*;

public class DeleteMenuController {

    @FXML
    ListView<VaccinationCentre> centreListView;

    @FXML
    ListView<VaccinationBooth> boothListView;

    @FXML
    ListView<Patient> patientListView;

    @FXML
    ListView<VaccinationRecord> recordListView;

    @FXML
    ListView<VaccinationAppointment> appointmentListView;

    @FXML
    ChoiceBox<VaccinationCentre> centreChoiceBox;

    @FXML
    TextField centreName, centreEircode, searchPPSN, searchName, boothIdentifier;

    @FXML
    Label warningLabel;

    public void switchToMainMenu(){
        Driver.mainStage.setScene(Driver.mainMenu);
    }

    public void showAllCentres(){
        centreListView.getItems().clear();
        centreName.clear();
        centreEircode.clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()){
            centreListView.getItems().add(centre);
        }
    }

    public void searchCentreName(){
        centreListView.getItems().clear();
        centreEircode.clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()){
            if (centre.getName().toLowerCase().contains(centreName.getText().toLowerCase())){
                centreListView.getItems().add(centre);
            }
        }
    }

    public void searchCentreEircode(){
        centreListView.getItems().clear();
        centreName.clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()){
            if (centre.getEircode().toLowerCase().contains(centreEircode.getText().toLowerCase())){
                centreListView.getItems().add(centre);
            }
        }
    }

    public void deleteCentre(){
        VaccinationCentre centre = centreListView.getItems().get(centreListView.getSelectionModel().getSelectedIndex());
        centreListView.getItems().remove(centre);
        Driver.vaccinationAPI.removeCentre(centre);
        try{
            Driver.save();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAllBooths(){
        boothListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            for (VaccinationBooth booth : centre.getBooths()) {
                boothListView.getItems().add(booth);
            }
        }
    }

    public void updateCentres(){
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

    public void showAllAppointments(){
        appointmentListView.getItems().clear();
        VaccinationBooth booth = boothListView.getItems().get(boothListView.getSelectionModel().getSelectedIndex());
        for (VaccinationAppointment appointment : booth.getAppointments()){
            appointmentListView.getItems().add(appointment);
        }
    }

    public void deleteBooth(){
        VaccinationBooth booth = boothListView.getItems().get(boothListView.getSelectionModel().getSelectedIndex());
        if (!Driver.vaccinationAPI.removeBooth(booth)) warningLabel.setText("Unable to Transfer Appointments");
        else boothListView.getItems().remove(booth);
        try {
            Driver.save();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAllPatients(){
        patientListView.getItems().clear();
        searchPPSN.clear();
        searchName.clear();
        for (Patient patient : Driver.vaccinationAPI.getPatients()){
            patientListView.getItems().add(patient);
        }
    }

    public void searchPatientPPSN(){
        patientListView.getItems().clear();
        searchName.clear();
        for (Patient patient : Driver.vaccinationAPI.getPatients()){
            if (patient.getPPSN().toLowerCase().contains(searchPPSN.getText().toLowerCase())){
                patientListView.getItems().add(patient);
            }
        }
    }

    public void searchPatientName(){
        patientListView.getItems().clear();
        searchPPSN.clear();
        for (Patient patient : Driver.vaccinationAPI.getPatients()){
            if (patient.getName().toLowerCase().contains(searchName.getText().toLowerCase())){
                patientListView.getItems().add(patient);
            }
        }
    }

    public void deletePatient(){
        Patient patient = patientListView.getItems().get(patientListView.getSelectionModel().getSelectedIndex());
        Driver.vaccinationAPI.removePatient(patient);
        patientListView.getItems().remove(patient);
        try{
            Driver.save();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deletePatientRecord(){
        VaccinationRecord record = recordListView.getItems().get(recordListView.getSelectionModel().getSelectedIndex());
        Driver.vaccinationAPI.removePatientRecord(record);
        recordListView.getItems().remove(record);
        try{
            Driver.save();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showPatientRecords(){
        recordListView.getItems().clear();
        Patient patient = patientListView.getItems().get(patientListView.getSelectionModel().getSelectedIndex());

        for (VaccinationRecord record : patient.getRecords()){
            recordListView.getItems().add(record);
        }
    }
}
