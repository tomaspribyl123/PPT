import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.control.*;

public class PasswordDialogTest extends ApplicationTest {

    private PasswordField passwordField;
    private TextField passwordVisible;
    private CheckBox showPassword;
    private Label strengthLabel;

    @Override
    public void start(Stage stage) {
        PasswordDialog app = new PasswordDialog();
        app.start(stage);

        passwordField = lookup(".password-field").query();
        passwordVisible = lookup(".text-field").query();
        showPassword = lookup(".check-box").query();
        strengthLabel = lookup(".label").nth(1).query();
    }

    @Test
    public void testPasswordStrength() {
        interact(() -> passwordField.setText("12345"));
        assertEquals("Síla hesla: Střední", strengthLabel.getText());
    }

    @Test
    public void testShowPassword() {
        interact(() -> {
            passwordField.setText("tajneheslo");
            showPassword.fire();
        });
        assertEquals("tajneheslo", passwordVisible.getText());
        assertEquals("Síla hesla: Silné", strengthLabel.getText());
    }
}
