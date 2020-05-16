package aplicacion.game.components.player;

import aplicacion.game.components.Component;
import aplicacion.game.entitiy.Entity;

import java.util.HashMap;
import java.util.function.Consumer;

public class PlayerState extends Component {

    private final HashMap<Consumer<Void>, Long> stateChecks = new HashMap<>();

    private PlayerEnergy playerEnergy;
    private PlayerMovement playerMovement;

    public PlayerState(Entity parent) {
        super(parent);
    }

    @Override
    public void start() {
        playerEnergy = parent.getComponent(PlayerEnergy.class);
        playerMovement = parent.getComponent(PlayerMovement.class);
    }

    @Override
    public void update() {
        for (Consumer<Void> stateCheck : stateChecks.keySet()) {
            stateCheck(stateCheck);
        }
    }

    public void freeze(float time) {
        playerMovement.setFreezed(true);
        addRemoveFunction(this::removeFreeze, time);
    }

    private void removeFreeze(Void v) {
        playerMovement.setFreezed(false);
    }

    public void slow(float time, float percentage) {
        playerMovement.setSlowedDown(true, percentage);
        addRemoveFunction(this::removeSlowness, time);
    }

    private void removeSlowness(Void v) {
        playerMovement.setSlowedDown(false);
    }

    public void recoverEnergy(float percentage) {
        playerEnergy.recoverEnergy(percentage);
    }

    private void addRemoveFunction(Consumer<Void> state, float time) {
        long removalTime = System.currentTimeMillis() + (long) (time * 1000);
        stateChecks.put(state, removalTime);
    }

    private void stateCheck(Consumer<Void> state) {
        if (System.currentTimeMillis() >= stateChecks.get(state)) {
            state.accept(null);
            stateChecks.remove(state);
        }
    }
}
