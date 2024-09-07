package com.floof.linear_perspective.adapter;

import com.floof.linear_perspective.model.Geometry;
import com.floof.linear_perspective.model.Scene;
import com.floof.linear_perspective.collection.Vector3D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;

public class JavaFXSceneAdapter implements SceneAdapter {
    private final Scene scene;
    private final double radius;
    private final float xDisplacement;
    private final float yDisplacement;
    private final float zoomScalar;

    public JavaFXSceneAdapter(
            Scene scene,
            double radius,
            float screenWidth,
            float screenHeight,
            float zoomScalar
    ) {
        this.scene = scene;
        this.radius = radius;
        this.xDisplacement = screenWidth / 2;
        this.yDisplacement = screenHeight / 2;
        this.zoomScalar = zoomScalar;
    }

    @Override
    public Node getRendering() {
        Pane view = new Pane();

        scene.getProjectedScene().forEach(geometry -> {
            List<Circle> vertices = getVertices(geometry);
            List<Line> edges = getEdges(geometry);

            view.getChildren().addAll(vertices);
            view.getChildren().addAll(edges);
        });

        return view;
    }

    private List<Circle> getVertices(Geometry geometry) {
        return geometry.getVertices().stream().map(
                vertex -> new Circle(
                        vertex.getX() * zoomScalar + xDisplacement,
                        -vertex.getY() * zoomScalar + yDisplacement,
                        radius
                )
        ).toList();
    }

    private List<Line> getEdges(Geometry geometry) {
        return geometry.getEdges().stream().map(edge -> {
            int i = edge.getFirst();
            int j = edge.getSecond();

            Vector3D first = geometry.getVertices().get(i);
            Vector3D second = geometry.getVertices().get(j);

            return new Line(
                    first.getX() * zoomScalar + xDisplacement, -first.getY() * zoomScalar + yDisplacement,
                    second.getX() * zoomScalar + xDisplacement, -second.getY() * zoomScalar + yDisplacement
            );
        }).toList();
    }
}