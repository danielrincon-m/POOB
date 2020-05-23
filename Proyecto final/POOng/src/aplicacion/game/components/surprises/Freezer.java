package aplicacion.game.components.surprises;

import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.player.PlayerState;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.enums.SurpriseProperties;
import aplicacion.game.utils.GameUtils;

/**
 * Componente principal de la sorpresa Freezer
 */
public class Freezer extends Surprise {

    private BallMovement ballMovement;

    /**
     * @param parent             La Entidad padre de la sorpresa
     * @param surpriseManager    El surpriseManager del juego
     * @param surpriseProperties Las propiedades de la sorpresa
     */
    public Freezer(Entity parent, SurpriseManager surpriseManager, SurpriseProperties surpriseProperties) {
        super(parent, surpriseManager, surpriseProperties);
    }

    @Override
    public void start() {
        super.start();
        ballMovement = ball.getComponent(BallMovement.class);
    }

    @Override
    protected void takeAction() {
        FieldSide rivalSide = GameUtils.getOtherSide(ballMovement.getLastHitterSide());
        PlayerState playerState = entityManager.find(GameUtils.getPlayerNameBySide(rivalSide)).
                getComponent(PlayerState.class);
        playerState.freeze(2);
    }
}
