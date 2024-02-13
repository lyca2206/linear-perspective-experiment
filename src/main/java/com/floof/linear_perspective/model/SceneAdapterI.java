package com.floof.linear_perspective.model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;

public class SceneAdapterI implements SceneAdapter {
    private Scene scene;
    private double radius;

    public SceneAdapterI(Scene scene, double radius) {
        this.scene = scene;
        this.radius = radius;
    }

    @Override
    public Node getRendering() {
        Pane view = new Pane();

        scene.getProjectedScene().forEach(geometry -> {
            List<Circle> vertices = geometry.getVertices().stream().map(vertex ->
                    new Circle(vertex.getX(), -vertex.getY(), radius)
            ).toList();

            List<Line> edges = geometry.getEdges().stream().map(edge -> {
                int fIndex = edge.getFirst();
                int sIndex = edge.getSecond();

                Vector3D first = geometry.getVertices().get(fIndex);
                Vector3D second = geometry.getVertices().get(sIndex);

                return new Line(first.getX(), -first.getY(), second.getX(), -second.getY());
            }).toList();
            view.getChildren().addAll(vertices);
            view.getChildren().addAll(edges);
        });

        return view;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
