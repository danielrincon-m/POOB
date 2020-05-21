package aplicacion.game.components.player;

import aplicacion.game.components.Component;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;

/**
 * Componente del jugador que representa su energía
 */
public class PlayerEnergy extends Component {

    private final float MAX_ENERGY = 100;
    private final float MIN_ENERGY = 50;
    private float energy;
    private final float energyDecreaseRate = 1;

    /**
     * @param parent La Entidad que contiene este componente
     */
    public PlayerEnergy(Entity parent) {
        super(parent);
    }

    @Override
    public void start() {
        resetEnergy();
    }

    @Override
    public void update() {
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

    /**
     * Reinicia la energía del jugador al máximo
     */
    private void resetEnergy() {
        energy = MAX_ENERGY;
    }
}
