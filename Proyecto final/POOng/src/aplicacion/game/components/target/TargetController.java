package aplicacion.game.components.target;

import aplicacion.game.components.Component;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Componente que se encarga de instanciar y controlar los blancos
 */
public class TargetController extends Component {

    private final int minGap = 1;
    private final int maxGap = 3;
    private final int maxScore;
    private float nextSpawnTime;
    private final String topTargetName = "TARGET_TOP";
    private final String bottomTargetName = "TARGET_BOTTOM";

    private final HashMap<FieldSide, Entity> targets = new HashMap<>();


    /**
     * @param parent   La Entidad que contiene este componente
     * @param maxScore El puntaje máximo que se le dará al blanco
     */
    public TargetController(Entity parent, int maxScore) {
        super(parent);
        this.maxScore = maxScore;
    }

    @Override
    public void start() {
        populateHashMap();
        calculateNextSpawnTime();
    }

    @Override
    public void update() {
        checkSpawn();
    }

    /**
     * Retorna el target instanciado del lado dado
     *
     * @param side El lado en el que se busca el target
     * @return La Entidad del targed buscado o null si no hay ninguno
     */
    public Entity getTarget(FieldSide side) {
        return targets.get(side);
    }

    /**
     * Remueve un blanco del campo de juego y lo agrega a la pool
     *
     * @param side El lado del blanco que se removió
     * @param name El nombre de la entidad del blanco
     */
    public void removeTarget(FieldSide side, String name) {
        entityManager.remove(name);
        targets.put(side, null);
    }

    /**
     * Verifica si es momento de instanciar un nuevo blanco y lo hace
     */
    private void checkSpawn() {
        nextSpawnTime -= GameTimer.deltaTime();
        if (nextSpawnTime <= 0) {
            spawnTarget();
            calculateNextSpawnTime();
        }
    }

    /**
     * Instancia un nuevo blanco
     */
    private void spawnTarget() {
        Random r = new Random();
        int targetSide = r.nextInt(2);
        FieldSide side = targetSide == 0 ? FieldSide.TOP : FieldSide.BOTTOM;
        String name = targetSide == 0 ? topTargetName : bottomTargetName;
        if (targets.get(side) == null) {
            Entity target = new Entity(name, entityManager);
            target.addComponent(new Transform(target));
            target.addComponent(new Sprite(target, "resources/sprites/blanco.png", 2));
            target.addComponent(new RectangleCollider(target));
            target.addComponent(new TargetBehaviour(target, side, maxScore, this));
            entityManager.registerEntity(target);
            targets.put(side, target);
        }
    }

    /**
     * Calcula el tiempo en el que se instanciará el próximo blanco
     */
    private void calculateNextSpawnTime() {
        nextSpawnTime = ThreadLocalRandom.current().nextInt(minGap, maxGap + 1);
    }

    /**
     * Inicialeiza el HasMap de blancos con los blancos disponibles
     */
    private void populateHashMap() {
        targets.put(FieldSide.TOP, null);
        targets.put(FieldSide.BOTTOM, null);
    }
}
