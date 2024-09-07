module com.floof.linear_perspective {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.math3;

    opens com.floof.linear_perspective to javafx.fxml;
    opens com.floof.linear_perspective.collection to javafx.fxml;
    opens com.floof.linear_perspective.model to javafx.fxml;

    exports com.floof.linear_perspective;
    exports com.floof.linear_perspective.collection;
    exports com.floof.linear_perspective.library;
    exports com.floof.linear_perspective.model;
    exports com.floof.linear_perspective.adapter;
    opens com.floof.linear_perspective.adapter to javafx.fxml;
}