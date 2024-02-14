package com.floof.linear_perspective.model;

import java.util.List;

public class Geometry {
    private final List<Vector3D> vertices;
    private final List<Pair<Integer, Integer>> edges;
    private Vector3D position;
    private float scale;

    public Geometry(List<Vector3D> vertices, List<Pair<Integer, Integer>> edges, Vector3D position, float scale) {
        this.vertices = vertices;
        this.edges = edges;
        this.position = position;
        this.scale = scale;
    }

    public List<Vector3D> getVertices() {
        return vertices.stream().map(vector3D -> vector3D.multiply(scale).add(position)).toList();
    }

    public List<Pair<Integer, Integer>> getEdges() {
        return edges;
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
