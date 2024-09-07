package com.floof.linear_perspective.model;

import com.floof.linear_perspective.collection.Vector3D;
import com.floof.linear_perspective.library.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final MatrixOperations matrixOperations;
    private Camera camera;
    private List<Geometry> geometries;

    public Scene(MatrixOperations matrixOperations, Camera camera, List<Geometry> geometries) {
        this.matrixOperations = matrixOperations;
        this.camera = camera;
        this.geometries = geometries;
    }

    public List<Geometry> getProjectedScene() {
        List<Geometry> projectedGeometries = new ArrayList<>(geometries.size());

        geometries.forEach(geometry -> {
            List<Vector3D> projectedVertices = geometry.getVertices().stream().map(this::getProjection).toList();
            projectedGeometries.add(new Geometry(projectedVertices, geometry.getEdges(), geometry.getPosition(), geometry.getScale()));
        });

        return projectedGeometries;
    }

    public Vector3D getProjection(Vector3D vector3D) {
        Vector3D cameraPosition = camera.getPosition();
        Vector3D cameraDirection = camera.getZDirection();

        double[] pointInPlane = getPointInPlane(vector3D, cameraPosition, cameraDirection);
        double[] planeProjection = getPlaneProjection(pointInPlane, cameraPosition, cameraDirection);

        return new Vector3D(
                (float) planeProjection[0],
                (float) planeProjection[1],
                (float) planeProjection[2]
        );
    }

    private double[] getPointInPlane(Vector3D vector3D, Vector3D pos, Vector3D dir) {
        double[][] equations = new double[][] {
                {dir.getX(), dir.getY(), dir.getZ(), 0},
                {1, 0, 0, -vector3D.getX() + pos.getX()},
                {0, 1, 0, -vector3D.getY() + pos.getY()},
                {0, 0, 1, -vector3D.getZ() + pos.getZ()}
        };

        double[] value = new double[] {
                ( pos.getX() + dir.getX() ) * dir.getX() + ( pos.getY() + dir.getY() ) * dir.getY() + ( pos.getZ() + dir.getZ() ) * dir.getZ(),
                pos.getX(),
                pos.getY(),
                pos.getZ()
        };

        return matrixOperations.solve(equations, value);
    }

    private double[] getPlaneProjection(double[] pointInPlane, Vector3D pos, Vector3D dir) {
        double[][] planeVectorBase = new double[][] {
                {camera.getXDirection().getX(), camera.getYDirection().getX(), camera.getZDirection().getX()},
                {camera.getXDirection().getY(), camera.getYDirection().getY(), camera.getZDirection().getY()},
                {camera.getXDirection().getZ(), camera.getYDirection().getZ(), camera.getZDirection().getZ()}
        };

        double[] planeVector = new double[] {
                pointInPlane[0] - pos.getX() - dir.getX(),
                pointInPlane[1] - pos.getY() - dir.getY(),
                pointInPlane[2] - pos.getZ() - dir.getZ()
        };
        return matrixOperations.solve(planeVectorBase, planeVector);
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public List<Geometry> getGeometries() {
        return geometries;
    }

    public void setGeometries(List<Geometry> geometries) {
        this.geometries = geometries;
    }
}
