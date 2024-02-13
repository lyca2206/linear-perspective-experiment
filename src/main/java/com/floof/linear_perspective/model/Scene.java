package com.floof.linear_perspective.model;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private Camera camera;
    private List<Geometry> geometries;

    public Scene(Camera camera, List<Geometry> geometries) {
        this.camera = camera;
        this.geometries = geometries;
    }

    public List<Geometry> getProjectedScene() {
        List<Geometry> projectedGeometries = new ArrayList<>(geometries.size());

        geometries.forEach(geometry -> {
            List<Vector3D> projectedVertices = geometry.getVertices().stream().map(this::getProjection).toList();
            projectedGeometries.add(new Geometry(projectedVertices, geometry.getEdges(), geometry.getPosition()));
        });

        return projectedGeometries;
    }

    private Vector3D getProjection(Vector3D vector3D) {
        Vector3D e = camera.getPosition();
        Vector3D t = camera.getDirection();

        SimpleMatrix matrix = new SimpleMatrix(new float[][]{
                {t.getX(), t.getY(), t.getZ(), 0},
                {1, 0, 0, -vector3D.getX() + e.getX()},
                {0, 1, 0, -vector3D.getY() + e.getY()},
                {0, 0, 1, -vector3D.getZ() + e.getZ()}
        });

        SimpleMatrix b = new SimpleMatrix(new float[]{
                (e.getX() + t.getX())*t.getX() + (e.getY() + t.getY())*t.getY() + (e.getZ() + t.getZ())*t.getZ(),
                e.getX(),
                e.getY(),
                e.getZ()
        });

        SimpleMatrix result = matrix.solve(b);

        return new Vector3D(
                (float) result.get(0),
                (float) result.get(1),
                (float) result.get(2)
        );
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
