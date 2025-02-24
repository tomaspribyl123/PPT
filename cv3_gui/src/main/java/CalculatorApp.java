import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CalculatorApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField num1Field = new TextField();
        TextField num2Field = new TextField();
        ComboBox<String> operationBox = new ComboBox<>();
        Label resultLabel = new Label("Výsledek: ");
        Button calculateButton = new Button("Vypočítat");

        operationBox.getItems().addAll("+", "-", "*", "/");
        operationBox.setValue("+");

        calculateButton.setOnAction(e -> {
            try {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                String operation = operationBox.getValue();
                double result = switch (operation) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num2 != 0 ? num1 / num2 : Double.NaN;
                    default -> 0;
                };
                resultLabel.setText("Výsledek: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Neplatné číslo!");
            }
        });

        VBox root = new VBox(10, num1Field, operationBox, num2Field, calculateButton, resultLabel);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setTitle("Kalkulačka");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
