package aplicacion.game.components.surprises;

import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.Collider;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.entities.Ball;
import aplicacion.game.entities.Entity;
import aplicacion.game.enums.SurpriseProperties;

public class FastBall extends Surprise {

    private BallMovement ballMovement;
    private Collider ballCollider;
    private Collider myCollider;

    public FastBall(Entity parent, SurpriseManager surpriseManager, SurpriseProperties surpriseProperties) {
        super(parent, surpriseManager, surpriseProperties);
    }

    @Override
    public void start() {
        super.start();
        Ball ball = (Ball) Entity.find("BALL");
        ballMovement = ball.getComponent(BallMovement.class);
        ballCollider = ball.getComponent(RectangleCollider.class);
        myCollider = parent.getComponent(RectangleCollider.class);
    }

    @Override
    public void update() {
        super.update();
        if (myCollider.collidesWith(ballCollider)) {
            ballMovement.fastBall();
            destroy();
        }
    }
}
