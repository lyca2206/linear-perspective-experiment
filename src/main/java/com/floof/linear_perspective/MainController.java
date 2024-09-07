package com.floof.linear_perspective;

import com.floof.linear_perspective.adapter.SceneAdapter;
import com.floof.linear_perspective.adapter.JavaFXSceneAdapter;
import com.floof.linear_perspective.collection.Vector3D;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import static com.floof.linear_perspective.model.utility.MakeScene.makeSceneWithCube;

public class MainController {

    @FXML
    private Pane mainView;

    @FXML
    void initialize() {
        Vector3D position = new Vector3D(1.5f, 0, -10);
        float xAxisRotation = 0;
        float yAxisRotation = -0.15f;
        float zAxisRotation = 0;

        SceneAdapter javaFXSceneAdapter = new JavaFXSceneAdapter(
                makeSceneWithCube(position, xAxisRotation, yAxisRotation, zAxisRotation),
                2,
                1280,
                720,
                1024
        );

        mainView.getChildren().add(javaFXSceneAdapter.getRendering());
    }
}
