package Utils;

import models.VaccinationCentre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorListTest {

    private CorList<VaccinationCentre> list = new CorList<>();
    private VaccinationCentre centre1, centre2, centre3, centre4, centre5, centre6, centre7, centre8;

    @BeforeEach
    void setup(){
        centre1 = new VaccinationCentre("Wit","Arenea","e91 y64", 200);
        centre2 = new VaccinationCentre("Waterford hospital", "Waterford", "X91 G56", 100);
        centre3 = new VaccinationCentre("De la Salle", "Waterford", "X91 G56", 100);
        centre4 = new VaccinationCentre("Waterpark", "Waterford", "X91 G56", 100);
        centre5 = new VaccinationCentre("Ballygunner", "Waterford", "X91 G56", 100);
        centre6 = new VaccinationCentre("New Town", "Waterford", "X91 Y65", 100);
        centre7 = new VaccinationCentre("Hospital", "Waterford", "X91 6Y7", 100);
        centre8 = new VaccinationCentre("Mulligans", "Waterford", "X91 Y78", 100);
        list.add(centre1);
        list.add(centre2);
        list.add(centre3);
        list.add(centre4);
        list.add(centre5);
    }

    @AfterEach
    void tearDown(){
        centre1 = null;
        centre2 = null;
        centre3 = null;
        centre4 = null;
        centre5 = null;
        list.clear();
    }

    @Test
    void add() {
        assertEquals(centre1, list.get(0));
        assertEquals(centre2, list.get(1));
        assertEquals(centre3, list.get(2));
    }

    @Test
    void clear() {
        assertEquals(5,list.size());
        list.clear();
        assertEquals(0, list.size());
        assertNull(list.get(0));
    }

    @Test
    void remove(){
        assertEquals(centre1.getName(), list.get(0).getName());
        assertEquals(centre2.getName(), list.get(1).getName());
        assertEquals(centre1, list.remove(0));
        assertEquals(centre2.getName(), list.get(0).getName());
        assertEquals(centre3.getName(), list.get(1).getName());
        assertEquals(centre4.getName(), list.get(2).getName());
    }

    @Test
    void removeByObject(){
        assertEquals(centre2, list.get(1));
        list.remove(centre2);
        assertEquals(centre3, list.get(1));
        assertEquals(centre5, list.get(3));
        list.remove(centre5);
        assertNull(list.get(4));
        assertEquals(centre1, list.get(0));
        list.remove(centre1);
        assertEquals(centre3, list.get(0));
    }

    @Test
    void size(){
        assertEquals(5, list.size());
        list.remove(1);
        assertEquals(4, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void indexOf(){
        assertEquals(0, list.indexOf(centre1));
        assertEquals(1, list.indexOf(centre2));
        assertEquals(2, list.indexOf(centre3));
        assertEquals(3, list.indexOf(centre4));
        assertEquals(4, list.indexOf(centre5));
        list.clear();
        assertEquals(-1, list.indexOf(centre1));
    }

    @Test
    void addElement(){
        list.clear();
        list.addElement(centre1);
        list.addElement(centre2);
        assertEquals(centre2, list.get(0));
        list.addElement(centre3);
        assertEquals(centre3, list.get(0));
    }

    @Test
    void set(){
        assertEquals(centre1, list.get(0));
        assertEquals(centre1, list.set(0, centre6));
        assertEquals(centre6, list.get(0));

        assertEquals(centre3, list.get(2));
        assertEquals(centre3, list.set(2, centre7));
        assertEquals(centre7, list.get(2));

        assertEquals(centre5, list.get(4));
        assertEquals(centre5, list.set(4, centre8));
        assertEquals(centre8, list.get(4));
    }

    @Test
    void addAtIndex(){
        assertEquals(centre5, list.get(4));
        list.add(4, centre8);
        assertEquals(centre8, list.get(4));

        assertEquals(centre3, list.get(2));
        list.add(2, centre6);
        assertEquals(centre6, list.get(2));
        assertEquals(centre3, list.get(3));
        assertEquals(7, list.size());

        assertEquals(centre1, list.get(0));
        list.add(0, centre7);
        assertEquals(centre7, list.get(0));
        assertEquals(centre1, list.get(1));
        assertEquals(8, list.size());
    }

    @Test
    void contains(){
        assertTrue(list.contains(centre1));
        assertTrue(list.contains(centre5));
        assertFalse(list.contains(centre6));
    }
}