package aplicacion.game.utils;

import java.io.Serializable;

public class Vector2 implements Serializable {
    public float x;
    public float y;

    //TODO: Sería bueno hacerlo inmutable :p

    public Vector2() {
    }

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        set(v);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void setZero() {
        x = 0;
        y = 0;
    }

    public float[] getComponents() {
        return new float[]{x, y};
    }

    public float magnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float magnitudeSq() {
        return x * x + y * y;
    }

    public void normalize() {
        float magnitude = magnitude();
        x /= magnitude;
        y /= magnitude;
    }

    public Vector2 getNormalized() {
        float magnitude = magnitude();
        return new Vector2(x / magnitude, y / magnitude);
    }

    public void add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void add(float vx, float vy) {
        this.x += vx;
        this.y += vy;
    }

    public static Vector2 add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public Vector2 getAdded(Vector2 v) {
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    public void subtract(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void subtract(float vx, float vy) {
        this.x -= vx;
        this.y -= vy;
    }

    public static Vector2 subtract(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x - v2.x, v1.y - v2.y);
    }

    public Vector2 getSubtracted(Vector2 v) {
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    public void multiply(float scalar) {
        x *= scalar;
        y *= scalar;
    }

    public Vector2 getMultiplied(float scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    public void divide(float scalar) {
        x /= scalar;
        y /= scalar;
    }

    public Vector2 getDivided(float scalar) {
        return new Vector2(x / scalar, y / scalar);
    }

    public Vector2 getPerp() {
        return new Vector2(-y, x);
    }

    public float dot(Vector2 v) {
        return (this.x * v.x + this.y * v.y);
    }

    public float dot(float vx, float vy) {
        return (this.x * vx + this.y * vy);
    }

    public static float dot(Vector2 v1, Vector2 v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public float cross(Vector2 v) {
        return (this.x * v.y - this.y * v.x);
    }

    public float cross(float vx, float vy) {
        return (this.x * vy - this.y * vx);
    }

    public static float cross(Vector2 v1, Vector2 v2) {
        return (v1.x * v2.y - v1.y * v2.x);
    }

    public float project(Vector2 v) {
        return (this.dot(v) / this.magnitude());
    }

    public float project(float vx, float vy) {
        return (this.dot(vx, vy) / this.magnitude());
    }

    public static float project(Vector2 v1, Vector2 v2) {
        return (dot(v1, v2) / v1.magnitude());
    }

    public Vector2 getProjectedVector(Vector2 v) {
        return this.getNormalized().getMultiplied(this.dot(v) / this.magnitude());
    }

    public Vector2 getProjectedVector(float vx, float vy) {
        return this.getNormalized().getMultiplied(this.dot(vx, vy) / this.magnitude());
    }

    public static Vector2 getProjectedVector(Vector2 v1, Vector2 v2) {
        return v1.getNormalized().getMultiplied(Vector2.dot(v1, v2) / v1.magnitude());
    }

    public void rotateBy(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        float rx = x * cos - y * sin;
        y = x * sin + y * cos;
        x = rx;
    }

    public Vector2 getRotatedBy(float angle) {
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        return new Vector2(x * cos - y * sin, x * sin + y * cos);
    }

    public void reverse() {
        x = -x;
        y = -y;
    }

    public Vector2 getReversed() {
        return new Vector2(-x, -y);
    }

    @Override
    public Vector2 clone() {
        return new Vector2(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Vector2) {
            Vector2 v = (Vector2) obj;
            return (x == v.x) && (y == v.y);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vector2d[" + x + ", " + y + "]";
    }
}
