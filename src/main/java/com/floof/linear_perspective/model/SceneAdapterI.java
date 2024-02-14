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
    public Node getRendering(float screenWidth, float screenHeight, float zoomCoefficient) {
        Pane view = new Pane();

        Vector3D center = scene.getProjection(new Vector3D(0, 0, 1));
        Vector3D position = scene.getCamera().getPosition();
        Vector3D direction = scene.getCamera().getDirection();

        Vector3D vectorDisplacement = center.subtract(position.add(direction));

        float xDisplacement = (screenWidth / 2) + vectorDisplacement.getX() * zoomCoefficient;
        float yDisplacement = (screenHeight / 2) + vectorDisplacement.getY() * zoomCoefficient;

        scene.getProjectedScene().forEach(geometry -> {
            List<Circle> vertices = geometry.getVertices().stream().map(vertex ->
                    new Circle(vertex.getX() * zoomCoefficient + xDisplacement, -vertex.getY() * zoomCoefficient + yDisplacement, radius)
            ).toList();

            List<Line> edges = geometry.getEdges().stream().map(edge -> {
                int fIndex = edge.getFirst();
                int sIndex = edge.getSecond();

                Vector3D first = geometry.getVertices().get(fIndex);
                Vector3D second = geometry.getVertices().get(sIndex);

                return new Line(
                        first.getX() * zoomCoefficient + xDisplacement, -first.getY() * zoomCoefficient + yDisplacement,
                        second.getX() * zoomCoefficient + xDisplacement, -second.getY() * zoomCoefficient + yDisplacement
                );
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
