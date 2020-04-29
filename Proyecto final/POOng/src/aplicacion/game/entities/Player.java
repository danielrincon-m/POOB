package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.engine.input.Input;
import aplicacion.game.engine.time.GameTimer;
import aplicacion.game.enums.EntityName;
import aplicacion.game.utils.Vector2;

import java.awt.event.KeyEvent;

public class Player extends Entity {

    private float leftBound;
    private float rightBound;
    private float speed = 19f;
    private int leftKey;
    private int rightKey;

    private Ball ball;
    private Field field;

    public Player(EntityName name, float xPosition, float yPosition, float width, float height) {
        super(name, xPosition, yPosition, width, height);
        setControls();
    }

    @Override
    public void start() {
        ball = (Ball) Entity.find(EntityName.BALL);
        field = (Field) Entity.find(EntityName.FIELD);
        setLimits();
        System.out.println(position);
    }

    @Override
    public void update() {
        checkBallHit();
        checkMovement();
    }

    private void checkMovement() {
        Vector2 movement = new Vector2(speed * GameTimer.getDeltaTime(), 0);
        if (Input.getInstance().isKeyDown(leftKey)) {
            position.subtract(movement);
            System.out.println(position);
        } else if (Input.getInstance().isKeyDown(rightKey)) {
            position.add(movement);
            System.out.println(position);
        }
        checkOutOfBounds();
    }

    private void checkBallHit() {
        if (collider.collidesWith(ball.collider)) {
            int direction = (name == EntityName.PLYER_ONE) ? 1 : -1;
            float maxDist = getWidth() / 2f;
            float absoluteCenterDist = Math.min(Math.abs(ball.getCenterXPosition() - getCenterXPosition()), maxDist);
            float centerDistPercentage = absoluteCenterDist / maxDist;
            centerDistPercentage = ball.getCenterXPosition() < getCenterXPosition() ? -centerDistPercentage : centerDistPercentage;
            ball.hit(direction, centerDistPercentage);
        }
    }

    private void setLimits() {
        leftBound = field.getLeftBound();
        rightBound = field.getRightBound();
    }

    private void checkOutOfBounds() {
        if (position.x < leftBound) {
            position.x = leftBound;
        }
        if (position.x + size.x > rightBound) {
            position.x = rightBound - size.x;
        }
    }

    private void setControls() {
        if (name.equals(EntityName.PLYER_ONE)) {
            leftKey = KeyEvent.VK_A;
            rightKey = KeyEvent.VK_D;
        } else if (name.equals(EntityName.PLAYER_TWO)) {
            leftKey = KeyEvent.VK_LEFT;
            rightKey = KeyEvent.VK_RIGHT;
        } else {
            throw new EntityException(EntityException.INVALID_NAME);
        }
    }
}
