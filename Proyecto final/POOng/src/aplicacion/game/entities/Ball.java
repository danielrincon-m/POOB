package aplicacion.game.entities;

import aplicacion.game.engine.time.GameTimer;
import aplicacion.game.enums.BallSpeed;
import aplicacion.game.enums.EntityName;
import aplicacion.game.utils.Vector2;

import java.util.Random;

public class Ball extends Entity {

    private float speed;

    private Vector2 direction;

    public Ball(EntityName name, float xPosition, float yPosition, float width, float height, BallSpeed speed) {
        super(name, xPosition, yPosition, width, height);
        setRandomDirection();
        setSpeed(speed);
    }

    @Override
    public void start() {
    }

    @Override
    public void update() {
        move();
    }

    public void hit(int direction, float centerDistancePercentage) {

    }

    private void move() {
        Vector2 displacement = direction.getMultiplied(speed).getMultiplied(GameTimer.getDeltaTime());
        position.add(displacement);
        System.out.println(position);
    }

    private void setRandomDirection() {
        float[] possibilities = {-1f, 1f};
        Random r = new Random();
        float ySpeed = possibilities[r.nextInt(2)];
        direction = new Vector2(0, ySpeed);
        direction.normalize();
    }

    private void setSpeed(BallSpeed speed) {
        this.speed = speed.initialSpeed();
        System.out.println(this.speed);
    }
}
