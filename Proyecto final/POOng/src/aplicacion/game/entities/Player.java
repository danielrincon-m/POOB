package aplicacion.game.entities;

import aplicacion.game.enums.EntityName;

public class Player extends Entity {

    private float leftBound;
    private float rightBound;
    private float speed = 1f;

    private Ball ball;
    private Field field;

    public Player(EntityName name, float xPosition, float yPosition, float width, float height) {
        super(name, xPosition, yPosition, width, height);
    }

    @Override
    public void start() {
        ball = (Ball) Entity.find(EntityName.BALL);
        field = (Field) Entity.find(EntityName.FIELD);
        setLimits();
    }

    @Override
    public void update() {

    }

    public void moveHorizontal(int direction) {
        if (direction == -1 || direction == 1) {
            float newPosition = position.x + speed * direction;
            position.x = direction == -1 ? Math.max(leftBound, newPosition) : Math.min(rightBound, newPosition);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void checkBallHit() {
        if (collider.collidesWith(ball.collider)) {
            int direction = (name == EntityName.PLYER_ONE) ? 1 : -1;
        }
    }

    private void setLimits() {
        leftBound = field.getLeftBound();
        rightBound = field.getRightBound();
    }
}
