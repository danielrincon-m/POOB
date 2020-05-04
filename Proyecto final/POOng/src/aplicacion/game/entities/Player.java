package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.components.RectangleCollider;
import aplicacion.game.engine.Input;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.enums.EntityName;
import aplicacion.game.utils.Vector2;

import java.awt.event.KeyEvent;

public class Player extends Entity {

    private float leftBound;
    private float rightBound;
    private float speed = 150f;
    private int fieldSide; // 1 -> TOP, -1 -> BOTTOM
    private int leftKey;
    private int rightKey;

    private Ball ball;
    private Field field;
    private RectangleCollider collider;

    public Player(EntityName name, float xPosition, float yPosition, float width, float height, int fieldSide) {
        super(name, xPosition, yPosition, width, height);
        setFieldSide(fieldSide);
        setControls();
    }

    @Override
    public void start() {
        ball = (Ball) Entity.find(EntityName.BALL);
        field = (Field) Entity.find(EntityName.FIELD);
        collider = getComponent(RectangleCollider.class);
        setLimits();
        //System.out.println(transform.position);
    }

    @Override
    public void update() {
        checkBallHit();
        checkMovement();
    }

    private void checkMovement() {
        Vector2 movement = new Vector2(speed * GameTimer.deltaTime(), 0);
        if (Input.getInstance().isKeyDown(leftKey)) {
            movement.multiply(-1);
            transform.translate(movement);
        } else if (Input.getInstance().isKeyDown(rightKey)) {
            transform.translate(movement);
        }
        //System.out.println(transform.position);
        checkOutOfBounds();
    }

    private void checkBallHit() {
        if (collider.collidesWith(ball.getComponent(RectangleCollider.class))) {
            //System.out.println("HIT!!!!!!!!!!!!!!!!!!!!!!!!!");
            float maxDist = transform.getWidth() / 2f;
            float absoluteCenterDist = Math.min(Math.abs(ball.transform.getCenterPosition().x - transform.getCenterPosition().x), maxDist);
            float centerDistPercentage = absoluteCenterDist / maxDist;
            centerDistPercentage = ball.transform.getCenterPosition().x < transform.getCenterPosition().x ? -centerDistPercentage : centerDistPercentage;
            ball.hit(fieldSide, centerDistPercentage);
        }
    }

    private void checkOutOfBounds() {
        if (transform.getCenterPosition().x < leftBound) {
            transform.setPosition(
                    new Vector2(leftBound - transform.getWidth() / 2f, transform.getPosition().y)
            );
        }
        if (transform.getCenterPosition().x > rightBound) {
            transform.setPosition(
                    new Vector2(rightBound - transform.getSize().x / 2f, transform.getPosition().y)
            );
        }
    }

    private void setLimits() {
        leftBound = field.getLeftBound();
        rightBound = field.getRightBound();
    }

    private void setFieldSide(int fieldSide) {
        if (fieldSide != 1 && fieldSide != -1) {
            throw new EntityException(EntityException.INVALID_FIELD_SIDE);
        }
        this.fieldSide = fieldSide;
    }

    private void setControls() {
        if (fieldSide == 1) {
            leftKey = KeyEvent.VK_A;
            rightKey = KeyEvent.VK_D;
        } else if (fieldSide == -1) {
            leftKey = KeyEvent.VK_LEFT;
            rightKey = KeyEvent.VK_RIGHT;
        } else {
            throw new EntityException(EntityException.INVALID_NAME);
        }
    }
}
