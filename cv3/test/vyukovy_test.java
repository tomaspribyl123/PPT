import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class vyukovy_test {

    // Test for square root calculation
    @Test
    public void testSqrt() {
        double number = 16.0;
        double expected = 4.0;
        assertEquals(expected, Math.sqrt(number), 0.0001, "Square root of 16 should be 4");
    }

    // Test for sine and tangent values
    @Test
    public void testSinAndTan() {
        double degrees = 30.0;
        double radians = Math.toRadians(degrees);

        double expectedSin = 0.5;
        double expectedTan = Math.tan(radians);

        assertEquals(expectedSin, Math.sin(radians), 0.0001, "Sin of 30 degrees should be 0.5");
        assertEquals(expectedTan, Math.tan(radians), 0.0001, "Tangent value should match the calculation");
    }

    // Test for indexOf method in List
    @Test
    public void testListIndexOf() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals(1, list.indexOf("B"), "Index of 'B' should be 1");
        assertEquals(-1, list.indexOf("Z"), "Element 'Z' should not exist in the list");
    }

    // Test for adding values to a Map
    @Test
    public void testMapAdd() {
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);

        assertEquals(1, map.get("One"), "Value for key 'One' should be 1");
        assertEquals(2, map.get("Two"), "Value for key 'Two' should be 2");

        // Overwriting existing value
        map.put("One", 10);
        assertEquals(10, map.get("One"), "Value for key 'One' should be updated to 10");

        // Checking existence of keys
        assertTrue(map.containsKey("One"), "Map should contain key 'One'");
        assertFalse(map.containsKey("Three"), "Map should not contain key 'Three'");
    }
}
