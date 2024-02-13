package com.floof.linear_perspective.model;

public class Vector3D {
    private float x;
    private float y;
    private float z;

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D add(Vector3D vector3D){
        return new Vector3D(x + vector3D.x, y + vector3D.y, z + vector3D.z);
    }

    public Vector3D multiply(float value) {
        return new Vector3D(x * value, y * value, z * value);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
