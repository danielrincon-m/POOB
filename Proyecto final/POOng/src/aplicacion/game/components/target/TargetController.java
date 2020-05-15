package aplicacion.game.components.target;

import aplicacion.game.components.Component;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entities.Entity;
import aplicacion.game.entities.Target;
import aplicacion.game.enums.FieldSide;

import java.util.HashMap;
import java.util.Random;

public class TargetController extends Component {

    private final int minGap = 2;
    private final int maxGap = 6;
    private final int maxScore;
    private float nextSpawnTime;
    private final String topTargetName = "TARGET_TOP";
    private final String bottomTargetName = "TARGET_BOTTOM";

    private final HashMap<FieldSide, Target> targets = new HashMap<>();


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
//        System.out.println("REMOVE: " + side);
        Entity.remove(name);
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
        int target = r.nextInt(2);
        FieldSide side = target == 0 ? FieldSide.TOP : FieldSide.BOTTOM;
        String name = target == 0 ? topTargetName : bottomTargetName;
        if (targets.get(side) == null) {
            Target t = new Target(applicationManager, name, side, maxScore, this);
            Entity.registerEntity(t);
            targets.put(side, t);
        }
    }

    private void calculateNextSpawnTime() {
        Random r = new Random();
        nextSpawnTime = r.nextInt((maxGap - minGap) + 1) + minGap;
    }

    private void populateHashMap() {
        targets.put(FieldSide.TOP, null);
        targets.put(FieldSide.BOTTOM, null);
    }
}
