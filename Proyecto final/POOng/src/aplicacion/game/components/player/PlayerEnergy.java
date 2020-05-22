package aplicacion.game.components.player;

import aplicacion.game.components.Component;
import aplicacion.game.components.winner.WinNotifier;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;

/**
 * Componente del jugador que representa su energía
 */
public class PlayerEnergy extends Component {

    private final float MAX_ENERGY = 100;
    private final float MIN_ENERGY = 50;
    private float energy;
    private final float energyDecreaseRate = 1;

    private FieldSide side;

    private WinNotifier winNotifier;

    /**
     * @param parent La Entidad que contiene este componente
     * @param side   El lado del jugador
     */
    public PlayerEnergy(Entity parent, FieldSide side) {
        super(parent);
        this.side = side;
    }

    @Override
    public void start() {
        winNotifier = entityManager.find("WIN_NOTIFIER").getComponent(WinNotifier.class);
        resetEnergy();
    }

    @Override
    public void update() {
        checkDefeat();
    }

    /**
     * @return La energía actual del jugador
     */
    public float getEnergy() {
        return energy;
    }

    /**
     * Reduce la energía en la cantidad definida
     */
    protected void wasteEnergy() {
        energy -= energyDecreaseRate * GameTimer.deltaTime();
    }

    /**
     * Recupera energía en cierto porcentaje
     * @param percentage El porcentaje de la energía faltante recuperado
     */
    protected void recoverEnergy(float percentage) {
        energy = Math.min(MAX_ENERGY, energy + (MAX_ENERGY - energy) * percentage);
    }

    private void checkDefeat() {
        if (energy <= MIN_ENERGY) {
            winNotifier.winGame(GameUtils.getOtherSide(side), "Energía menor al 50%");
        }
    }

    /**
     * Reinicia la energía del jugador al máximo
     */
    private void resetEnergy() {
        energy = MAX_ENERGY;
    }
}
