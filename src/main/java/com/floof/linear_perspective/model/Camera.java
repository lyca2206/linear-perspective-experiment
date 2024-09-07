package com.floof.linear_perspective.model;

import com.floof.linear_perspective.collection.Vector3D;
import com.floof.linear_perspective.library.MatrixOperations;

public class Camera {
    private final Vector3D position;
    private final Vector3D xDirection;
    private final Vector3D yDirection;
    private final Vector3D zDirection;

    public Camera(
            MatrixOperations matrixOperations,
            Vector3D position,
            float xAxisRotation,
            float yAxisRotation,
            float zAxisRotation
    ) {
        this.position = position;

        this.xDirection = matrixOperations.applyRotationToVector3D(
                new Vector3D(1, 0, 0), xAxisRotation, yAxisRotation, zAxisRotation
        );

        this.yDirection = matrixOperations.applyRotationToVector3D(
                new Vector3D(0, 1, 0), xAxisRotation, yAxisRotation, zAxisRotation
        );

        this.zDirection = matrixOperations.applyRotationToVector3D(
                new Vector3D(0, 0, 1), xAxisRotation, yAxisRotation, zAxisRotation
        );
    }

    public Vector3D getPosition() {
        return position;
    }

    public Vector3D getXDirection() {
        return xDirection;
    }

    public Vector3D getYDirection() {
        return yDirection;
    }

    public Vector3D getZDirection() {
        return zDirection;
    }
}
