package aplicacion;

import aplicacion.collision.CollisionSystem;
import aplicacion.gameObject.Ball;

import java.util.ArrayList;

public class GameManager {
    private static CollisionSystem collisionSystem;
    private static Ball ball;
    private static ArrayList<Character> players;

    private float boardWidth;
    private float boardHeight;

    public GameManager(float width, float height) {
        collisionSystem = new CollisionSystem();
        ball = new Ball();
        players = new ArrayList<>();
        boardWidth = width;
        boardHeight = height;
    }

    public static CollisionSystem getCollisionSystem() {
        return collisionSystem;
    }
}
