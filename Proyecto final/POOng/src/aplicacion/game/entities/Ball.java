package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.engine.time.GameTimer;
import aplicacion.game.enums.BallSpeed;
import aplicacion.game.enums.EntityName;
import aplicacion.game.utils.Vector2;

import java.util.Random;

public class Ball extends Entity {

    private float speed;
    private final float maxDeviationAngle = 30;

    private Vector2 direction;

    public Ball(EntityName name, float xPosition, float yPosition, float width, float height, BallSpeed speed) {
        super(name, xPosition, yPosition, width, height);
        setRandomDirection();
        setSpeed(speed);
    }

    @Override
    public void start() {
        //hit(-1, 0.5f);
    }

    @Override
    public void update() {
        move();
    }

    public void hit(int direction, float centerDistancePercentage) {
        if (direction != 1 && direction != -1) {
            throw new EntityException(EntityException.INVALID_DIRECTION);
        }
        float deviationAngle = maxDeviationAngle * centerDistancePercentage;
        float xSpeed = speed * (float) Math.sin(Math.toRadians(deviationAngle));
        float ySpeed = speed * (float) (Math.cos(Math.toRadians(deviationAngle)) * direction);
        //System.out.println(xSpeed + "," + ySpeed);
        this.direction = new Vector2(xSpeed, ySpeed).getNormalized();
    }

    private void move() {
        Vector2 displacement = direction.getMultiplied(speed).getMultiplied(GameTimer.getDeltaTime());
        position.add(displacement);
        //System.out.println(direction);
        //System.out.println(position);
    }

    private void setRandomDirection() {
        float[] possibilities = {-1f, 1f};
        Random r = new Random();
        float ySpeed = possibilities[r.nextInt(2)];
        direction = new Vector2(0, ySpeed);
        direction.normalize();
        //System.out.println(direction);
    }

    private void setSpeed(BallSpeed speed) {
        this.speed = speed.initialSpeed();
        //System.out.println(this.speed);
    }
}
