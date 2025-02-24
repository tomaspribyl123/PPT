import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.control.*;

public class CalculatorAppTest extends ApplicationTest {

    private TextField num1Field;
    private TextField num2Field;
    private ComboBox<String> operationBox;
    private Label resultLabel;
    private Button calculateButton;

    @Override
    public void start(Stage stage) {
        CalculatorApp app = new CalculatorApp();
        app.start(stage);

        num1Field = lookup(".text-field").nth(0).query();
        num2Field = lookup(".text-field").nth(1).query();
        operationBox = lookup(".combo-box").query();
        calculateButton = lookup(".button").query();
        resultLabel = lookup(".label").nth(1).query();
    }

    @Test
    public void testAddition() {
        interact(() -> {
            num1Field.setText("5");
            num2Field.setText("3");
            operationBox.setValue("+");
            calculateButton.fire();
        });
        assertEquals("Výsledek: 8.0", resultLabel.getText());
    }

    @Test
    public void testDivisionByZero() {
        interact(() -> {
            num1Field.setText("10");
            num2Field.setText("0");
            operationBox.setValue("/");
            calculateButton.fire();
        });
        assertEquals("Výsledek: NaN", resultLabel.getText());
    }
}
