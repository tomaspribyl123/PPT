import org.example.HistogramCalculator;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HistogramCalculatorTest {

    HistogramCalculator calc = new HistogramCalculator();

    @Test
    void testEmptyInput() {
        Map<Character, Integer> result = calc.calculateAbsoluteHistogram("");
        assertTrue(result.isEmpty());
    }

    @Test
    void testOnlyUppercaseLetters() {
        Map<Character, Integer> result = calc.calculateAbsoluteHistogram("AABBC");
        assertEquals(2, result.get('A'));
        assertEquals(2, result.get('B'));
        assertEquals(1, result.get('C'));
        assertFalse(result.containsKey('#'));
    }

    @Test
    void testOnlyNonLetters() {
        Map<Character, Integer> result = calc.calculateAbsoluteHistogram("123!@#");
        assertEquals(6, result.get('#'));
    }

    @Test
    void testMixedCharacters() {
        Map<Character, Integer> result = calc.calculateAbsoluteHistogram("A1B2C!D");
        assertEquals(1, result.get('A'));
        assertEquals(1, result.get('B'));
        assertEquals(1, result.get('C'));
        assertEquals(1, result.get('D'));
        assertEquals(3, result.get('#'));
    }

    @Test
    void testRelativeHistogram() {
        Map<Character, Integer> abs = calc.calculateAbsoluteHistogram("AAB");
        Map<Character, Double> rel = calc.calculateRelativeHistogram(abs);
        assertEquals(2.0 / 3, rel.get('A'));
        assertEquals(1.0 / 3, rel.get('B'));
    }
}
