import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NadrzTest {

    private Nadrz nadrz;

    @BeforeEach
    void setUp() {
        nadrz = new Nadrz(100.0);
    }

    @Test
    void testConstructor() {
        assertEquals(100.0, nadrz.get_kapacita(), "Kapacita by měla být 100");
    }

    @Test
    void testConstructorExtremeCapacity() {
        Nadrz nadrzBig = new Nadrz(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, nadrzBig.get_kapacita(), "Kapacita by měla být maximální možná");
    }

    @Test
    void testConstructorNegativeCapacity() {
        Nadrz nadrzNeg = new Nadrz(-50.0);
        assertNotEquals(-50.0, nadrzNeg.get_kapacita(), "Kapacita by neměla být záporná");
    }

    @Test
    void testAddExactlyFull() throws Exceptions.PlnaNadrzException {
        assertTrue(nadrz.add(100.0), "Mělo by být možné naplnit nádrž přesně na kapacitu");
    }

    @Test
    void testAddJustBeforeFull() throws Exceptions.PlnaNadrzException {
        assertTrue(nadrz.add(99.0), "Mělo by být možné přidat 99");
        assertTrue(nadrz.add(1.0), "Mělo by být možné přidat 1, čímž se dosáhne kapacity");
    }

    @Test
    void testAddTooMuch() {
        assertThrows(Exceptions.PlnaNadrzException.class, () -> nadrz.add(150.0),
                "Měla by být vyhozena výjimka při pokusu o překročení kapacity");
    }

    @Test
    void testAddExtremeValue() {
        assertThrows(Exceptions.PlnaNadrzException.class, () -> nadrz.add(Double.MAX_VALUE),
                "Pokus o naplnění maximální hodnotou by měl vyvolat výjimku");
    }

    @Test
    void testAddNegative() throws Exceptions.PlnaNadrzException {
        assertFalse(nadrz.add(-10.0), "Nemělo by být možné přidat zápornou hodnotu");
    }

    @Test
    void testRemoveExactlyZero() throws Exceptions.PlnaNadrzException, Exceptions.PrazdnaNadrzException {
        nadrz.add(100.0);
        assertTrue(nadrz.remove(100.0), "Mělo by být možné odebrat přesně celou kapacitu");
    }

    @Test
    void testRemoveJustAboveZero() throws Exceptions.PlnaNadrzException, Exceptions.PrazdnaNadrzException {
        nadrz.add(100.0);
        assertTrue(nadrz.remove(99.0), "Mělo by být možné odebrat 99");
        assertTrue(nadrz.remove(1.0), "Mělo by být možné odebrat poslední jednotku");
    }

    @Test
    void testRemoveTooMuch() throws Exceptions.PlnaNadrzException {
        nadrz.add(20.0);
        assertThrows(Exceptions.PrazdnaNadrzException.class, () -> nadrz.remove(30.0),
                "Měla by být vyhozena výjimka při pokusu odebrat více než je stav");
    }

    @Test
    void testRemoveExtremeValue() throws Exceptions.PlnaNadrzException {
        nadrz.add(100.0);
        assertThrows(Exceptions.PrazdnaNadrzException.class, () -> nadrz.remove(Double.MAX_VALUE),
                "Pokus o odebrání maximální hodnoty by měl vyvolat výjimku");
    }

    @Test
    void testRemoveNegative() throws Exceptions.PrazdnaNadrzException {
        assertFalse(nadrz.remove(-10.0), "Nemělo by být možné odebrat zápornou hodnotu");
    }

    @Test
    void testRemoveFromEmptyTank() {
        assertThrows(Exceptions.PrazdnaNadrzException.class, () -> nadrz.remove(1.0),
                "Měla by být vyhozena výjimka při pokusu odebrat z prázdné nádrže");
    }
}
