package aplicacion.game.components.target;

import aplicacion.game.components.Component;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TargetController extends Component {

    private final int minGap = 2;
    private final int maxGap = 6;
    private final int maxScore;
    private float nextSpawnTime;
    private final String topTargetName = "TARGET_TOP";
    private final String bottomTargetName = "TARGET_BOTTOM";

    private final HashMap<FieldSide, Entity> targets = new HashMap<>();


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

    public void removeTarget(FieldSide side, String name) {
        entityManager.remove(name);
        targets.put(side, null);
    }

    private void checkSpawn() {
        nextSpawnTime -= GameTimer.deltaTime();
        if (nextSpawnTime <= 0) {
            spawnTarget();
            calculateNextSpawnTime();
        }
    }

    private void spawnTarget() {
        Random r = new Random();
        int targetSide = r.nextInt(2);
        FieldSide side = targetSide == 0 ? FieldSide.TOP : FieldSide.BOTTOM;
        String name = targetSide == 0 ? topTargetName : bottomTargetName;
        if (targets.get(side) == null) {
            Entity target = new Entity(applicationManager, name);
            //addComponent(new Sprite(this, "resources/sprites/not_implemented.png", 1));
            target.addComponent(new RectangleCollider(target));
            target.addComponent(new TargetBehaviour(target, side, maxScore, this));
            entityManager.registerEntity(target);
            targets.put(side, target);
        }
    }

    private void calculateNextSpawnTime() {
        nextSpawnTime = ThreadLocalRandom.current().nextInt(minGap, maxGap + 1);
    }

    private void populateHashMap() {
        targets.put(FieldSide.TOP, null);
        targets.put(FieldSide.BOTTOM, null);
    }
}
