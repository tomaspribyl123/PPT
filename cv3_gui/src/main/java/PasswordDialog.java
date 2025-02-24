import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PasswordDialog extends Application {

    @Override
    public void start(Stage primaryStage) {
        PasswordField passwordField = new PasswordField();
        TextField passwordVisible = new TextField();
        passwordVisible.setManaged(false);
        passwordVisible.setVisible(false);

        CheckBox showPassword = new CheckBox("Zobrazit heslo");
        Label strengthLabel = new Label("Síla hesla: ");

        passwordField.textProperty().addListener((obs, oldVal, newVal) -> {
            passwordVisible.setText(newVal);
            strengthLabel.setText("Síla hesla: " + checkPasswordStrength(newVal));
        });

        passwordVisible.textProperty().addListener((obs, oldVal, newVal) -> {
            passwordField.setText(newVal);
        });

        showPassword.setOnAction(e -> {
            boolean show = showPassword.isSelected();
            passwordField.setVisible(!show);
            passwordVisible.setVisible(show);
            passwordVisible.setManaged(show);
        });

        VBox root = new VBox(10, passwordField, passwordVisible, showPassword, strengthLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setTitle("Heslo");
        primaryStage.show();
    }

    private String checkPasswordStrength(String password) {
        if (password.length() > 8) return "Silné";
        if (password.length() > 4) return "Střední";
        return "Slabé";
    }

    public static void main(String[] args) {
        launch(args);
    }
}
