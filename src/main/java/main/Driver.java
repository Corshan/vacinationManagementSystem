package main;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import controllers.VaccinationAPI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Driver extends Application {

    public static VaccinationAPI vaccinationAPI;
    public static Stage mainStage;
    public static Scene mainMenu;

    public void start(Stage stage) throws IOException {
        try {
            load();
        } catch (Exception e) {
            System.out.println(e);
        }
        mainStage = stage;
        stage.setResizable(false);

        FXMLLoader mainMenuLoader = new FXMLLoader(Driver.class.getResource("mainMenu.fxml"));
        mainMenu = new Scene(mainMenuLoader.load(), 600, 400);

        mainStage.setTitle("Vaccination Management System");
        mainStage.setScene(mainMenu);
        mainStage.show();

    }

    public static void main(String[] args) {
        launch();
    }

    public static void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("VaccinationCentre.xml"));
        out.writeObject(vaccinationAPI);
        out.close();
    }

    public static void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        xstream.addPermission(AnyTypePermission.ANY); //https://stackoverflow.com/questions/30812293/com-thoughtworks-xstream-security-forbiddenclassexception
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("VaccinationCentre.xml"));
        vaccinationAPI = (VaccinationAPI) is.readObject();
        is.close();
    }
}
