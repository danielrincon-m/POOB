package aplicacion.game.components.player;

import aplicacion.game.components.Component;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entities.Entity;

public class PlayerEnergy extends Component {

    private float energy = 100;
    private final float energyDecreaseRate = 1;

    public PlayerEnergy(Entity parent) {
        super(parent);
    }

    @Override
    public void start() {
    }

    @Override
    public void update() {
    }

    public void wasteEnergy() {
        energy -= energyDecreaseRate * GameTimer.deltaTime();
    }
}
