package aplicacion.game.components.player;

import aplicacion.game.components.Component;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entitiy.Entity;

public class PlayerEnergy extends Component {

    private final float MAX_ENERGY = 100;
    private final float MIN_ENERGY = 50;
    private float energy;
    private final float energyDecreaseRate = 1;

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

    public float getEnergy() {
        return energy;
    }

    protected void wasteEnergy() {
        energy -= energyDecreaseRate * GameTimer.deltaTime();
    }

    protected void recoverEnergy(float percentage) {
        energy = Math.min(MAX_ENERGY, energy + (MAX_ENERGY - energy) * percentage);
    }

    private void resetEnergy() {
        energy = MAX_ENERGY;
    }
}
