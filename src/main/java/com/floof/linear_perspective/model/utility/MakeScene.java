package com.floof.linear_perspective.model.utility;

import com.floof.linear_perspective.collection.Vector3D;
import com.floof.linear_perspective.library.ApacheMatrixOperations;
import com.floof.linear_perspective.library.MatrixOperations;
import com.floof.linear_perspective.model.Camera;
import com.floof.linear_perspective.model.Geometry;
import com.floof.linear_perspective.model.Scene;

import java.util.List;

import static com.floof.linear_perspective.model.utility.MakeShape.makeCube;

public class MakeScene {
    public static Scene makeSceneWithCube(
            Vector3D position,
            float xAxisRotation,
            float yAxisRotation,
            float zAxisRotation
    ) {
        MatrixOperations matrixOperations = new ApacheMatrixOperations();
        Camera camera = new Camera(matrixOperations, position, xAxisRotation, yAxisRotation, zAxisRotation);
        return new Scene(matrixOperations, camera, geometries);
    }

    private static final List<Geometry> geometries = List.of(
            makeCube(0, 0, 8, 1.2f),
            makeCube(0.2f, 0, 12, 1.2f),
            makeCube(-0.2f, 0, 12, 1.2f),
            makeCube(0, -0.2f, 16, 1.2f),
            makeCube(0, 0.2f, 16, 1.2f)
    );
}
