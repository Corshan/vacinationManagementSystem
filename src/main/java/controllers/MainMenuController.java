package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import main.Driver;

import java.io.IOException;

public class MainMenuController {

    public void switchToAddMenu() throws IOException {
        FXMLLoader addMenuLoader = new FXMLLoader(Driver.class.getResource("addMenu.fxml"));
        Driver.mainStage.setScene(new Scene(addMenuLoader.load(), 600, 400));
        //Driver.mainStage.setScene(Driver.addMenu);
    }

    public void switchToSearchMenu() throws IOException {
        FXMLLoader searchMenuLoader = new FXMLLoader(Driver.class.getResource("searchMenu.fxml"));
        Driver.mainStage.setScene(new Scene(searchMenuLoader.load(), 600, 490));
        //Driver.mainStage.setScene(Driver.searchMenu);
    }

    public void switchToAppointmentMenu() throws IOException {
        FXMLLoader appointmentLoader = new FXMLLoader(Driver.class.getResource("appointmentMenu.fxml"));
        Driver.mainStage.setScene(new Scene(appointmentLoader.load(), 600, 400));
        //Driver.mainStage.setScene(Driver.appointmentMenu);
    }

    public void switchToDeleteMenu() throws IOException {
        FXMLLoader deleteMenuLoader = new FXMLLoader(Driver.class.getResource("deleteMenu.fxml"));
        Driver.mainStage.setScene(new Scene(deleteMenuLoader.load(), 600, 490));
        //Driver.mainStage.setScene(Driver.deleteMenu);
    }

    public void save() {
        try {
            Driver.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            Driver.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(1);
    }

    public void clear() {
        Driver.vaccinationAPI.clear();
        try {
            Driver.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
