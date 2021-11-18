package controllers;

import javafx.scene.control.*;
import main.Driver;
import javafx.fxml.FXML;
import models.*;

import java.time.format.DateTimeFormatter;

public class AppointmentMenuController {

    @FXML
    ChoiceBox<VaccinationCentre> centreChoiceBoxAdd, centreChoiceBoxSearch;

    @FXML
    ChoiceBox<VaccinationBooth> boothChoiceBoxSearch;

    @FXML
    ComboBox<String> timeComboBox, vaccineComboBox;

    @FXML
    DatePicker date;

    @FXML
    TextField patientPPSN, batchNum, medicalPersonal, searchPPSN;

    @FXML
    Label invalidPPSN, invalidTime, invalidBatchNum, warningLabel, noOfAppointments;

    @FXML
    ListView<VaccinationAppointment> searchListView;

    public void initialize() {
        timeComboBox.getItems().addAll("9:10", "9:20", "9:30", "9:40", "9:50",
                "10:00", "10:10", "10:20", "10:30", "10:40", "10:50",
                "11:00", "11:10", "11:20", "11:30", "11:40", "11:50",
                "12:00", "12:10", "12:20", "12:30", "12:40", "12:50",
                "13:00", "13:10", "13:20", "13:30", "13:40", "13:50",
                "14:00", "14:10", "14:20", "14:30", "14:40", "14:50",
                "15:00", "15:10", "15:20", "15:30", "15:40", "15:50",
                "16:00", "16:10", "16:20", "16:30", "16:40", "16:50");
        timeComboBox.setVisibleRowCount(5);
        vaccineComboBox.getItems().addAll("AstraZeneca", "Moderna", "Janssen", "Pfizer");
        updateVaccinationCentreSearch();
    }

    public void switchToMainMenu() {
        Driver.mainStage.setScene(Driver.mainMenu);
    }

    public void updateVaccinationCentreAdd() {
        centreChoiceBoxAdd.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            centreChoiceBoxAdd.getItems().add(centre);
        }
    }

    public void updateVaccinationCentreSearch() {
        centreChoiceBoxSearch.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            centreChoiceBoxSearch.getItems().add(centre);
        }
    }

    public void updateVaccinationBoothSearch() {
        boothChoiceBoxSearch.getItems().clear();
        VaccinationCentre centre = centreChoiceBoxSearch.getValue();
        for (VaccinationBooth booth : centre.getBooths()) {
            boothChoiceBoxSearch.getItems().add(booth);
        }
    }

    public void searchCentre() {
        searchListView.getItems().clear();
        VaccinationCentre centre = centreChoiceBoxSearch.getValue();
        for (VaccinationBooth booth : centre.getBooths()) {
            for (VaccinationAppointment appointment : booth.getAppointments()) {
                searchListView.getItems().add(appointment);
            }
        }
        numberOfAppointments();
    }

    public void searchBooth() {
        searchListView.getItems().clear();
        VaccinationBooth booth = boothChoiceBoxSearch.getValue();
        for (VaccinationAppointment appointment : booth.getAppointments()) {
            searchListView.getItems().add(appointment);
        }
        numberOfAppointments();
    }

    public void addAppointment() {
        VaccinationCentre centre = centreChoiceBoxAdd.getValue();
        if (Driver.vaccinationAPI.findPatient(patientPPSN.getText()) != null) {
            invalidPPSN.setText("");
            Patient patient = Driver.vaccinationAPI.findPatient(patientPPSN.getText().toUpperCase());
            if (!centre.getBooths().isEmpty()) {
                warningLabel.setText("");
                try {
                    VaccinationAppointment appointment = new VaccinationAppointment(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(date.getValue()), timeComboBox.getValue(), vaccineComboBox.getValue(), Integer.parseInt(batchNum.getText()), medicalPersonal.getText(), patient);
                    invalidBatchNum.setText("");
                    for (VaccinationBooth booth : centre.getBooths()) {
                        if (!centre.checkAppointment(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(date.getValue()), timeComboBox.getValue(), booth)) {
                            booth.addAppointment(appointment);
                            patientPPSN.clear();
                            centreChoiceBoxAdd.setValue(null);
                            date.setValue(null);
                            timeComboBox.setValue(null);
                            vaccineComboBox.setValue(null);
                            batchNum.clear();
                            medicalPersonal.clear();
                            warningLabel.setText("Appointment made");
                            Driver.save();
                            return;
                        }
                    }
                    warningLabel.setText("Unable to make Appointment");
                } catch (Exception e) {
                    e.printStackTrace();
                    invalidBatchNum.setText("Invalid Batch Number");
                }
            } else {
                warningLabel.setText("No Booth Available");
            }

        } else {
            invalidPPSN.setText("Unable to find Patient");
        }

    }

    public void searchPPSN() {
        searchListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            for (VaccinationBooth booth : centre.getBooths()) {
                for (VaccinationAppointment appointment : booth.getAppointments()) {
                    if (appointment.getPatient().getPPSN().equals(searchPPSN.getText())) {
                        searchListView.getItems().add(appointment);
                    }
                }
            }
        }
        numberOfAppointments();
    }

    public void cancelAppointment() {
        VaccinationAppointment appointment = searchListView.getItems().get(searchListView.getSelectionModel().getSelectedIndex());
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()) {
            for (VaccinationBooth booth : centre.getBooths()) {
                if (booth.getAppointments().remove(appointment)) {
                    searchListView.getItems().remove(appointment);
                    try{
                        Driver.save();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
    }

    public void completeAppointment() {
        VaccinationAppointment appointment = searchListView.getItems().get(searchListView.getSelectionModel().getSelectedIndex());
        VaccinationRecord record = new VaccinationRecord(appointment.getDate(), appointment.getTime(), appointment.getVaccinationType(), appointment.getBatchNumber());
        appointment.getPatient().getRecords().add(record);
        searchListView.getItems().remove(appointment);

        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()){
            for (VaccinationBooth booth : centre.getBooths()){
                if (booth.getAppointments().remove(appointment)){
                    return;
                }
            }
        }
        try{
            Driver.save();
        }catch (Exception e){
            e.printStackTrace();
        }
        numberOfAppointments();
    }

    public void numberOfAppointments() {
        noOfAppointments.setText(searchListView.getItems().size() + "");
    }

    public void showAllAppointments(){
        searchListView.getItems().clear();
        for (VaccinationCentre centre : Driver.vaccinationAPI.getVaccinationCentres()){
            for (VaccinationBooth booth : centre.getBooths()){
                for (VaccinationAppointment appointment : booth.getAppointments()){
                    searchListView.getItems().add(appointment);
                }
            }
        }
        numberOfAppointments();
    }
}
