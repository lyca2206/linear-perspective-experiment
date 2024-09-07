package com.floof.linear_perspective.library;

import com.floof.linear_perspective.collection.Vector3D;
import org.apache.commons.math3.linear.*;

public class ApacheMatrixOperations implements MatrixOperations{
    @Override
    public Vector3D applyRotationToVector3D(
            Vector3D vector3D,
            float xAxisRotation,
            float yAxisRotation,
            float zAxisRotation
    ) {
        RealVector vector = vector3DToRealVector(vector3D);

        vector = applyRotationToRealVector(
                vector,
                xAxisRotation,
                yAxisRotation,
                zAxisRotation
        );

        return realVectorToVector3D(vector);
    }

    @Override
    public double[] solve(double[][] equations, double[] vector) {
        RealMatrix equationMatrix = MatrixUtils.createRealMatrix(equations);
        RealVector realVector = MatrixUtils.createRealVector(vector);

        DecompositionSolver solver = new LUDecomposition(equationMatrix).getSolver();
        RealVector solution = solver.solve(realVector);

        return solution.toArray();
    }

    private static RealVector vector3DToRealVector(Vector3D vector3D) {
        return MatrixUtils.createRealVector(new double[] {
                vector3D.getX(),
                vector3D.getY(),
                vector3D.getZ()
        });
    }

    private static Vector3D realVectorToVector3D(RealVector realVector) {
        return new Vector3D(
                (float) realVector.getEntry(0),
                (float) realVector.getEntry(1),
                (float) realVector.getEntry(2)
        );
    }

    private static RealVector applyRotationToRealVector(
            RealVector realVector,
            float xAxisRotation,
            float yAxisRotation,
            float zAxisRotation
    ) {
        return xRotationMatrix(xAxisRotation)
                .multiply(yRotationMatrix(yAxisRotation))
                .multiply(zRotationMatrix(zAxisRotation))
                .operate(realVector);
    }

    private static RealMatrix xRotationMatrix(float rotation) {
        return MatrixUtils.createRealMatrix(new double[][] {
                {1, 0, 0},
                {0, Math.cos(rotation), -Math.sin(rotation)},
                {0, Math.sin(rotation), Math.cos(rotation)}
        });
    }

    private static RealMatrix yRotationMatrix(float rotation) {
        return MatrixUtils.createRealMatrix(new double[][] {
                {Math.cos(rotation), 0, Math.sin(rotation)},
                {0, 1, 0},
                {-Math.sin(rotation), 0, Math.cos(rotation)}
        });
    }

    private static RealMatrix zRotationMatrix(float rotation) {
        return MatrixUtils.createRealMatrix(new double[][] {
                {Math.cos(rotation), -Math.sin(rotation), 0},
                {Math.sin(rotation), Math.cos(rotation), 0},
                {0, 0, 1}
        });
    }
}