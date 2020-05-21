package aplicacion.game.components.player;

import aplicacion.game.components.Component;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Transform;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;

/**
 * Componente del jugador que golpea la pelota
 */
public class PlayerHit extends Component {

    private BallMovement ballMovement;
    private Transform ballTransform;
    private final FieldSide fieldSide;
    private RectangleCollider ballCollider;
    private RectangleCollider collider;

    /**
     * @param parent La Entidad que contiene este componente
     * @param fieldSide El lado del campo en el que se encuentra el jugador padre
     */
    public PlayerHit(Entity parent, FieldSide fieldSide) {
        super(parent);
        this.fieldSide = fieldSide;
    }

    @Override
    public void start() {
        Entity ball = entityManager.find("BALL");
        ballCollider = ball.getComponent(RectangleCollider.class);
        ballTransform = ball.getComponent(Transform.class);
        ballMovement = ball.getComponent(BallMovement.class);
        collider = parent.getComponent(RectangleCollider.class);
    }

    @Override
    public void update() {
        checkBallHit();
    }

    /**
     * Verifica si se debe golpear la bola, y si es así, calcula los parámetros con los que la golpeará
     * y la golpeará
     */
    private void checkBallHit() {
        if (collider.collidesWith(ballCollider)) {
            float maxDist = transform.getWidth() / 2f;
            float absoluteCenterDist = Math.min(Math.abs(ballTransform.getCenterPosition().x - transform.getCenterPosition().x), maxDist);
            float centerDistPercentage = absoluteCenterDist / maxDist;
            centerDistPercentage = ballTransform.getCenterPosition().x < transform.getCenterPosition().x ? -centerDistPercentage : centerDistPercentage;
            ballMovement.hit(fieldSide, centerDistPercentage);
        }
    }
}
