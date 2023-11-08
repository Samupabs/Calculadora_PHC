module com.example.calculadora7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calculadora7 to javafx.fxml;
    exports com.example.calculadora7;
}