import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NadrzTest {

    private Nadrz nadrz;

    @BeforeEach
    void setUp() {
        nadrz = new Nadrz(100.0); // Nastavení nádrže s kapacitou 100
    }

    @Test
    void testConstructor() {
        assertEquals(100.0, nadrz.get_kapacita(), "Kapacita by měla být 100");
    }

    @Test
    void testSetKapacitaNegative() {
        Nadrz nadrzNeg = new Nadrz(-50.0);
        assertNotEquals(-50.0, nadrzNeg.get_kapacita(), "Kapacita by neměla být záporná");
    }

    @Test
    void testAddValid() {
        assertTrue(nadrz.add(50.0), "Mělo by být možné přidat 50");
        assertTrue(nadrz.add(30.0), "Mělo by být možné přidat dalších 30");
    }

    @Test
    void testAddTooMuch() {
        assertFalse(nadrz.add(150.0), "Nemělo by být možné přidat více než kapacita");
    }

    @Test
    void testAddNegative() {
        assertFalse(nadrz.add(-10.0), "Nemělo by být možné přidat zápornou hodnotu");
    }

    @Test
    void testRemoveValid() {
        nadrz.add(80.0);
        assertTrue(nadrz.remove(30.0), "Mělo by být možné odebrat 30");
        assertTrue(nadrz.remove(50.0), "Mělo by být možné odebrat zbývajících 50");
    }

    @Test
    void testRemoveTooMuch() {
        nadrz.add(20.0);
        assertFalse(nadrz.remove(30.0), "Nemělo by být možné odebrat více než aktuální stav");
    }

    @Test
    void testRemoveNegative() {
        assertFalse(nadrz.remove(-10.0), "Nemělo by být možné odebrat zápornou hodnotu");
    }
}
