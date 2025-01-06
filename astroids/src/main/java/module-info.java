module com.velfox {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.velfox to javafx.fxml;
    exports com.velfox;
}
