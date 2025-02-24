import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class dvojbran_test {
    @Test
    public void testCalculateOutput() {
        ElDvojbran network = new ElDvojbran(2.0, 3.0, 1.0, 4.0);
        double[] result = network.calculateOutput(10.0, 2.0, 5.0);

        assertEquals(16.25, result[0], 0.01);
        assertEquals(3.25, result[1], 0.01);
    }
}
