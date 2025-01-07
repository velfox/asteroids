module com.velfox {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    //transitif graphics

    opens com.velfox to javafx.fxml;
    exports com.velfox;
}
