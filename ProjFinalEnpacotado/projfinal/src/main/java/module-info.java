module com.feevale {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.feevale.controlador to javafx.fxml;
    opens com.feevale.modelo to javafx.base;
    opens com.feevale to javafx.fxml;

    exports com.feevale;
}