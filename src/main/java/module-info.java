module com.floof.linear_perspective {
    requires javafx.controls;
    requires javafx.fxml;
    requires ejml.simple;

    opens com.floof.linear_perspective to javafx.fxml;
    exports com.floof.linear_perspective;
}