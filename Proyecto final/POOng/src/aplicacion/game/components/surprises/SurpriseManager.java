package aplicacion.game.components.surprises;

import aplicacion.game.components.Component;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entities.Entity;
import aplicacion.game.enums.SurpriseProperties;
import aplicacion.game.utils.Vector2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public class SurpriseManager extends Component {

    private final int LOWER_SPAWN_BOUND = 8;
    private final int HIGHER_SPAWN_BOUND = 16;
    private float nextSpawnTime;

    private final EnumSet<SurpriseProperties> surprisePool = EnumSet.allOf(SurpriseProperties.class);

    private FieldBounds fieldBounds;

    public SurpriseManager(Entity parent) {
        super(parent);
    }

    @Override
    public void start() {
        fieldBounds = Entity.find("FIELD").getComponent(FieldBounds.class);
        calculateNextSpawnTime();
    }

    @Override
    public void update() {
        checkSpawnTime();
    }

    private void spawnSurprise() {
        SurpriseProperties surprise = getRandomSurpriseFromPool();
        if (surprise != null) {
            Entity e = createBaseEntity(surprise);
            addComponents(surprise, e);
            surprisePool.remove(surprise);
            Entity.registerEntity(e);
        }
    }

    public void removeSurprise(SurpriseProperties sp) {
        Entity.remove(sp.getName());
        surprisePool.add(sp);
    }

    private SurpriseProperties getRandomSurpriseFromPool() {
        if (!surprisePool.isEmpty()) {
            int setSize = surprisePool.size();
            int index = ThreadLocalRandom.current().nextInt(0, setSize);
            int i = 0;
            for (SurpriseProperties sp : surprisePool) {
                if (i == index) {
                    return sp;
                }
                i++;
            }
        }
        return null;
    }

    private Entity createBaseEntity(SurpriseProperties surprise) {
        Vector2 position = fieldBounds.getRandomPositionCloseToCenter();
        return new Entity(applicationManager, surprise.getName(), position.x, position.y, 40, 40);
    }

    private void addComponents(SurpriseProperties surprise, Entity entity) {
        entity.addComponent(new RectangleCollider(entity));
        entity.addComponent(new Sprite(entity, surprise.spritePath(), 2));
        try {
            Constructor<? extends Surprise> constructor = surprise.className().getConstructor(Entity.class,
                    SurpriseManager.class, SurpriseProperties.class);
            Surprise instance = constructor.newInstance(entity, this, surprise);
            entity.addComponent(instance);
        } catch (NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void checkSpawnTime() {
        nextSpawnTime -= GameTimer.deltaTime();
        if (nextSpawnTime <= 0) {
            spawnSurprise();
            calculateNextSpawnTime();
        }
    }

    private void calculateNextSpawnTime() {
        nextSpawnTime = ThreadLocalRandom.current().nextFloat() *
                (HIGHER_SPAWN_BOUND - LOWER_SPAWN_BOUND) +
                LOWER_SPAWN_BOUND;
    }
}
