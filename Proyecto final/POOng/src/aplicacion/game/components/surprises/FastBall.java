package aplicacion.game.components.surprises;

import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.SurpriseProperties;

/**
 * Componente principal de la sorpresa FastBall
 */
public class FastBall extends Surprise {

    private BallMovement ballMovement;

    /**
     * @param parent             La Entidad padre de la sorpresa
     * @param surpriseManager    El surpriseManager del juego
     * @param surpriseProperties Las propiedades de la sorpresa
     */
    public FastBall(Entity parent, SurpriseManager surpriseManager, SurpriseProperties surpriseProperties) {
        super(parent, surpriseManager, surpriseProperties);
    }

    @Override
    public void start() {
        super.start();
        ballMovement = ball.getComponent(BallMovement.class);
    }

    @Override
    protected void takeAction() {
        ballMovement.fastBall();
    }
}
