package aplicacion.game.components.surprises;

import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.player.PlayerState;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.enums.SurpriseProperties;
import aplicacion.game.utils.GameUtils;

/**
 * Componente principal de la sorpresa Energy
 */
public class Energy extends Surprise {

    private BallMovement ballMovement;

    /**
     * @param parent             La Entidad padre de la sorpresa
     * @param surpriseManager    El surpriseManager del juego
     * @param surpriseProperties Las propiedades de la sorpresa
     */
    public Energy(Entity parent, SurpriseManager surpriseManager, SurpriseProperties surpriseProperties) {
        super(parent, surpriseManager, surpriseProperties);
    }

    @Override
    public void start() {
        super.start();
        ballMovement = ball.getComponent(BallMovement.class);
    }

    @Override
    protected void takeAction() {
        FieldSide hitterSide = (ballMovement.getLastHitterSide());
        PlayerState playerState = entityManager.find(GameUtils.getPlayerNameBySide(hitterSide)).
                getComponent(PlayerState.class);
        playerState.recoverEnergy(0.5f);
    }
}
