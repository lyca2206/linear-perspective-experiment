module com.floof.linear_perspective {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.math3;

    opens com.floof.linear_perspective to javafx.fxml;
    exports com.floof.linear_perspective;
}