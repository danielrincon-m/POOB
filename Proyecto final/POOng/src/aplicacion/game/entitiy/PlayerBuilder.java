package aplicacion.game.entitiy;

import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.player.PlayerEnergy;
import aplicacion.game.components.player.PlayerHit;
import aplicacion.game.components.player.PlayerMovement;
import aplicacion.game.components.player.PlayerState;
import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PlayerBuilder {

    private final int zIndex;
    private final String name;
    private String spritePath;

    private final CharacterPersonality personality;
    private final EntityManager entityManager;
    private final FieldSide side;
    private final EntitySpawner.Properties properties;
    private Entity player;

    /**
     * Genera una nueva entidad de jugador según las propiedades dadas
     *
     * @param name       El nombre que se le asignará a la entidad del jugador
     * @param properties El objeto properties del jugador
     * @param side       El lado en el que se ubicará el jugador
     * @param zIndex     El zIndex de su sprite
     */
    public PlayerBuilder(EntityManager entityManager,
                         String name, CharacterPersonality personality,
                         EntitySpawner.Properties properties, FieldSide side, int zIndex) {
        this.entityManager = entityManager;
        this.name = name;
        this.personality = personality;
        this.properties = properties;
        this.side = side;
        this.zIndex = zIndex;
        spawn();
    }

    /**
     * Instancia al jugador
     */
    private void spawn() {
        setSpriteInfo();
        player = new Entity(name, entityManager);
        player.addComponent(new Transform(player,
                new Vector2(properties.xPosition, properties.yPosition),
                new Vector2(properties.dimension, properties.dimension)));
        player.addComponent(new RectangleCollider(player,
                new Vector2(properties.colOffsetX, properties.colOffsetY),
                new Vector2(properties.colWidth, properties.colHeight)));
        player.addComponent(new Sprite(player, spritePath, zIndex));
        setMovement();
        player.addComponent(new PlayerHit(player, side));
        player.addComponent(new PlayerEnergy(player, side));
        player.addComponent(new PlayerState(player, personality));
        entityManager.registerEntity(player);
    }

    /**
     * Establece el Componente de movimiento del jugador según su tipo
     */
    private void setMovement() {
        try {
            Constructor<? extends PlayerMovement> constructor = personality.getMovementClass().getConstructor(Entity.class,
                    FieldSide.class);
            PlayerMovement instance = constructor.newInstance(player, side);
            player.addComponent(instance);
        } catch (NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Genera la información de Sprite correspondiente al lado seleccionado del jugador
     */
    private void setSpriteInfo() {
        spritePath = personality.spritePath();
        if (side.equals(FieldSide.BOTTOM)) {
            spritePath = spritePath.replace("front", "back");
        }
    }
}
