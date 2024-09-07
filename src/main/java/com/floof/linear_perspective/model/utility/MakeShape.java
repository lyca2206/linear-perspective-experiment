package com.floof.linear_perspective.model.utility;

import com.floof.linear_perspective.collection.Pair;
import com.floof.linear_perspective.collection.Vector3D;
import com.floof.linear_perspective.model.Geometry;

import java.util.List;

public class MakeShape {
    public static Geometry makeCube(float x, float y, float z, float scale) {
        return new Geometry(vertices, edges, new Vector3D(x, y, z), scale);
    }

    private static final List<Vector3D> vertices = List.of(
            new Vector3D(0.5f, 0.5f, 0.5f),
            new Vector3D(0.5f, 0.5f, -0.5f),
            new Vector3D(0.5f, -0.5f, 0.5f),
            new Vector3D(0.5f, -0.5f, -0.5f),
            new Vector3D(-0.5f, 0.5f, 0.5f),
            new Vector3D(-0.5f, 0.5f, -0.5f),
            new Vector3D(-0.5f, -0.5f, 0.5f),
            new Vector3D(-0.5f, -0.5f, -0.5f)
    );

    private static final List<Pair<Integer, Integer>> edges = List.of(
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
    );
}