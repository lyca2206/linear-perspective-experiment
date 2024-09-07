package com.floof.linear_perspective.library;

import com.floof.linear_perspective.collection.Vector3D;

public interface MatrixOperations {
    Vector3D applyRotationToVector3D(
            Vector3D vector3D,
            float xAxisRotation,
            float yAxisRotation,
            float zAxisRotation
    );
    double[] solve(double[][] equations, double[] vector);
}