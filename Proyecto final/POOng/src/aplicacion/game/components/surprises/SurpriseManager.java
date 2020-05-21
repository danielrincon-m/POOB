package aplicacion.game.components.surprises;

import aplicacion.game.components.Component;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.SurpriseProperties;
import aplicacion.game.utils.Vector2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Componente que se encarga de instanciar y manejar las sorpresas
 */
public class SurpriseManager extends Component {

    private final int LOWER_SPAWN_BOUND = 5;
    private final int HIGHER_SPAWN_BOUND = 12;
    private float nextSpawnTime;

    private final EnumSet<SurpriseProperties> surprisePool = EnumSet.allOf(SurpriseProperties.class);

    private FieldBounds fieldBounds;

    /**
     * @param parent La Entidad que contiene este Componente
     */
    public SurpriseManager(Entity parent) {
        super(parent);
    }

    @Override
    public void start() {
        fieldBounds = entityManager.find("FIELD").getComponent(FieldBounds.class);
        calculateNextSpawnTime();
    }

    @Override
    public void update() {
        checkSpawnTime();
    }

    /**
     * Instancia una nueva sorpresa aleatoria
     */
    private void spawnSurprise() {
        SurpriseProperties surprise = getRandomSurpriseFromPool();
        if (surprise != null) {
            Entity e = createBaseEntity(surprise);
            addComponents(surprise, e);
            surprisePool.remove(surprise);
            entityManager.registerEntity(e);
        }
    }

    /**
     * Remueve las sorpresa dada del juego y la agrega a la pool de sorpresas
     * @param sp La sorpresa a remover
     */
    public void removeSurprise(SurpriseProperties sp) {
        entityManager.remove(sp.getName());
        surprisePool.add(sp);
    }

    /**
     * @return Una sorpresa aleatoria de las sopresas disponibles en la pool
     */
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

    /**
     * Crea una entidad básica con el nombre de la sorpresa
     * @param surprise La sorpresa que se creará
     * @return la entidad creada
     *
     */
    private Entity createBaseEntity(SurpriseProperties surprise) {
        return new Entity(surprise.getName(), entityManager);
    }

    /**
     * Agrega los componentes necesarios a la entidad básica
     * @param surprise La sorpresa que se está creando
     * @param entity La entidad básica
     */
    private void addComponents(SurpriseProperties surprise, Entity entity) {
        float size = 40;
        Vector2 position = fieldBounds.getRandomPositionCloseToCenter();
        entity.addComponent(new Transform(entity,
                new Vector2(position.x - size / 2f, position.y - size / 2f),
                new Vector2(size, size)));
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

    /**
     * Verifica si ya es hora de instanciar una nueva sorpresa y la instancia si es posible
     */
    private void checkSpawnTime() {
        nextSpawnTime -= GameTimer.deltaTime();
        if (nextSpawnTime <= 0) {
            spawnSurprise();
            calculateNextSpawnTime();
        }
    }

    /**
     * Calcula en cuanto tiempo se instanciará la siguiente sorpresa
     */
    private void calculateNextSpawnTime() {
        nextSpawnTime = ThreadLocalRandom.current().nextInt(LOWER_SPAWN_BOUND, HIGHER_SPAWN_BOUND + 1);
    }
}
