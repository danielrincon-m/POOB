package aplicacion.game.components.player;

import aplicacion.game.components.Component;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.CharacterPersonality;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Componente que maneja los estados alterados del jugador
 */
public class PlayerState extends Component {

    private CharacterPersonality personality;

    private final HashMap<Consumer<Void>, Long> stateChecks = new HashMap<>();

    private PlayerEnergy playerEnergy;
    private PlayerMovement playerMovement;

    /**
     * @param parent La Entidad que contiene este Componente
     * @param personality La personalidad el personaje
     */
    public PlayerState(Entity parent, CharacterPersonality personality) {
        super(parent);
        this.personality = personality;
    }

    @Override
    public void start() {
        playerEnergy = parent.getComponent(PlayerEnergy.class);
        playerMovement = parent.getComponent(personality.getMovementClass());
    }

    @Override
    public void update() {
        for (Consumer<Void> stateCheck : stateChecks.keySet()) {
            stateCheck(stateCheck);
        }
    }

    /**
     * Congela al jugador por un tiempo determinado
     *
     * @param time El tiempo en segundos que durará el congelamiento
     */
    public void freeze(float time) {
        playerMovement.setFreezed(true);
        addRemoveFunction(this::removeFreeze, time);
    }

    /**
     * Remueve la congelación
     *
     * @param v placeholder
     */
    private void removeFreeze(Void v) {
        playerMovement.setFreezed(false);
    }

    /**
     * Realentiza al jugador por un tiempo determinado
     *
     * @param time       El tiempo de realentización
     * @param percentage El porcentadje de reducción de la velocidad
     */
    public void slow(float time, float percentage) {
        playerMovement.setSlowedDown(true, percentage);
        addRemoveFunction(this::removeSlowness, time);
    }

    /**
     * Remueve la realentización del jugador pasado el tiempo
     *
     * @param v placeholder
     */
    private void removeSlowness(Void v) {
        playerMovement.setSlowedDown(false);
    }

    /**
     * Recupera energía para el jugador
     *
     * @param percentage El porcentaje de recuperación de energía con respecto a la
     *                   energía faltante
     */
    public void recoverEnergy(float percentage) {
        playerEnergy.recoverEnergy(percentage);
    }

    /**
     * Agrega una función de remoción para remover un efecto luego de cierto tiempo
     *
     * @param state La función de remoción a llamar
     * @param time  El tiempo en el que se debe llamar esa función
     */
    private void addRemoveFunction(Consumer<Void> state, float time) {
        long removalTime = System.currentTimeMillis() + (long) (time * 1000);
        stateChecks.put(state, removalTime);
    }

    /**
     * Verifica si se debe llamar la función de remoción
     *
     * @param state El estado alterado a verificar
     */
    private void stateCheck(Consumer<Void> state) {
        if (System.currentTimeMillis() >= stateChecks.get(state)) {
            state.accept(null);
            stateChecks.remove(state);
        }
    }
}
