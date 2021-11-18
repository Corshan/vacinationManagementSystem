package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccinationCentreTest {

    VaccinationCentre centre;
    VaccinationBooth booth1, booth2;

    @BeforeEach
    void setUp() {
        centre = new VaccinationCentre("WIT", "ASD", "X91 P6Y1", 12);
        booth1 = new VaccinationBooth("1A", 2, "None");
        booth2 = new VaccinationBooth("2A", 1, "None");
    }

    @AfterEach
    void tearDown() {
        centre = null;
        booth1 = null;
        booth2 = null;
    }

    @Test
    void isInCentre() {
        assertFalse(centre.isInCentre(booth1));
        centre.getBooths().add(booth1);
        assertTrue(centre.isInCentre(booth1));
    }
}