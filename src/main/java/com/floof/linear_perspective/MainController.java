package com.floof.linear_perspective;

import com.floof.linear_perspective.model.*;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;

public class MainController {

    @FXML
    private Pane mainView;

    @FXML
    void initialize() {
        SceneAdapter sceneAdapter = new SceneAdapterI(makeSceneWithCube(), 2);
        mainView.getChildren().add(sceneAdapter.getRendering(1280, 720, 1024));
    }

    private Scene makeSceneWithCube() {
        Vector3D cameraPosition = new Vector3D(0, 1, 0);
        Vector3D cameraDirection = new Vector3D(0, -0.05f, 0.99f);
        Camera camera = new Camera(cameraPosition, cameraDirection);

        Geometry cube1 = makeCube(0, 0, 8);
        Geometry cube2 = makeCube(0.2f, 0, 8);
        Geometry cube3 = makeCube(-0.2f, 0, 8);
        Geometry cube4 = makeCube(0, -0.2f, 8);
        Geometry cube5 = makeCube(0, 0.2f, 8);
        ArrayList<Geometry> geometries = new ArrayList<>();
        geometries.add(cube1);
        geometries.add(cube2);
        geometries.add(cube3);
        geometries.add(cube4);
        geometries.add(cube5);

        return new Scene(camera, geometries);
    }

    private Geometry makeCube(float x, float y, float z) {
        return new Geometry(Arrays.asList(
                new Vector3D(0.5f, 0.5f, 0.5f),
                new Vector3D(0.5f, 0.5f, -0.5f),
                new Vector3D(0.5f, -0.5f, 0.5f),
                new Vector3D(0.5f, -0.5f, -0.5f),
                new Vector3D(-0.5f, 0.5f, 0.5f),
                new Vector3D(-0.5f, 0.5f, -0.5f),
                new Vector3D(-0.5f, -0.5f, 0.5f),
                new Vector3D(-0.5f, -0.5f, -0.5f)
        ), new ArrayList<>(Arrays.asList(
                new Pair<>(0, 1),
                new Pair<>(2, 3),
                new Pair<>(4, 5),
                new Pair<>(6, 7),
                new Pair<>(0, 4),
                new Pair<>(1, 5),
                new Pair<>(2, 6),
                new Pair<>(3, 7),
                new Pair<>(0, 2),
                new Pair<>(4, 6),
                new Pair<>(1, 3),
                new Pair<>(5, 7)
        )), new Vector3D(x, y, z), 1);
    }
}
