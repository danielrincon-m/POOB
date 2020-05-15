package aplicacion.game.components.surprises;

import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.SurpriseProperties;

public class FlashBall extends Surprise {

    BallMovement ballMovement;

    public FlashBall(Entity parent, SurpriseManager surpriseManager, SurpriseProperties surpriseProperties) {
        super(parent, surpriseManager, surpriseProperties);
    }

    @Override
    public void start() {
        super.start();
        ballMovement = ball.getComponent(BallMovement.class);
    }

    @Override
    protected void takeAction() {
        ballMovement.flashBall();
    }
}
