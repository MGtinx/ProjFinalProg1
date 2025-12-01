module com.feevale {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.feevale to javafx.fxml;
    exports com.feevale;
}