package com.floof.linear_perspective.model;

public class Camera {
    private Vector3D position;
    private Vector3D direction;

    public Camera(Vector3D position, Vector3D direction) {
        this.position = position;
        this.direction = direction;
    }

    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public void setDirection(Vector3D direction) {
        this.direction = direction;
    }
}
