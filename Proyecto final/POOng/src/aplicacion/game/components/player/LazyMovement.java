package aplicacion.game.components.player;

import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Transform;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

public class LazyMovement extends PlayerMovement {

    protected double centerDelta;

    protected float movementHeadroom = 2f;

    protected BallMovement ballMovement;
    protected Transform ballTransform;
    protected RectangleCollider myCollider;

    /**
     * @param parent    La Entidad que contiene este componente
     * @param fieldSide El lado del campo en el que se encuentra el jugador padre
     */
    public LazyMovement(Entity parent, FieldSide fieldSide) {
        super(parent, fieldSide);
    }

    @Override
    public void start() {
        ballMovement = entityManager.find("BALL").getComponent(BallMovement.class);
        ballTransform = entityManager.find("BALL").getComponent(Transform.class);
        myCollider = parent.getComponent(RectangleCollider.class);
        super.start();
    }

    @Override
    protected void checkMovement() {
        Vector2 movement = new Vector2(speed * GameTimer.deltaTime(), 0);
        int direction = 0;
        if (ballMovement.getLastHitterSide() != fieldSide) {
            Vector2 ballArrivalPosition = calculateBallArrivalPosition();
            if (ballArrivalPosition.x < transform.getCenterPosition().x + centerDelta - movementHeadroom) {
                direction = -1;
            } else if (ballArrivalPosition.x > transform.getCenterPosition().x + centerDelta + movementHeadroom) {
                direction = 1;
            }
        }
        move(movement, direction);
    }

    protected float calculateHitPointY() {
        Vector2 colliderPosition = myCollider.getPosition();
        Vector2 colliderSize = myCollider.getSize();
        return fieldSide == FieldSide.BOTTOM ? colliderPosition.y : colliderPosition.y + colliderSize.y;
    }

    private Vector2 calculateBallArrivalPosition() {
        Vector2 ballPosition = ballTransform.getCenterPosition();
        Vector2 ballDirection = ballMovement.getDirection();
        float hitPointY = calculateHitPointY();
        float scale = Math.abs(ballTransform.getCenterPosition().y - hitPointY);
        return ballPosition.getAdded(ballDirection.getMultiplied(scale));
    }
}
