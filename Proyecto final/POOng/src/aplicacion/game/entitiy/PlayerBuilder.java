package aplicacion.game.entitiy;

import aplicacion.ApplicationManager;
import aplicacion.GameProperties;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.player.PlayerEnergy;
import aplicacion.game.components.player.PlayerHit;
import aplicacion.game.components.player.PlayerMovement;
import aplicacion.game.components.player.PlayerState;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

import java.io.Serializable;

public class PlayerBuilder implements Serializable {

    private final int zIndex;
    private final String name;
    private String spritePath;

    private final ApplicationManager applicationManager;
    private final EntityManager entityManager;
    private final GameProperties gameProperties;
    private final FieldSide side;
    private final EntitySpawner.Properties properties;

    /**
     * Genera una nueva entidad de jugador según las propiedades dadas
     * @param applicationManager El application manager del juego
     * @param name El nombre que se le asignará a la entidad del jugador
     * @param properties El objeto properties del jugador
     * @param side El lado en el que se ubicará el jugador
     * @param zIndex El zIndex de su sprite
     */
    public PlayerBuilder(ApplicationManager applicationManager, String name,
                         EntitySpawner.Properties properties, FieldSide side,
                         int zIndex) {
        this.applicationManager = applicationManager;
        gameProperties = applicationManager.getGameProperties();
        entityManager = applicationManager.getGameManager().getEntityManager();
        this.name = name;
        this.properties = properties;
        this.side = side;
        this.zIndex = zIndex;
        spawn();
    }

    private void spawn() {
        setSpriteInfo();
        Entity player = new Entity(applicationManager,
                name,
                properties.xPosition,
                properties.yPosition,
                properties.dimension,
                properties.dimension);
        player.addComponent(new RectangleCollider(player,
                new Vector2(properties.colOffsetX, properties.colOffsetY),
                new Vector2(properties.colWidth, properties.colHeight)));
        player.addComponent(new Sprite(player, spritePath, zIndex));
        player.addComponent(new PlayerMovement(player, side));
        player.addComponent(new PlayerHit(player, side));
        player.addComponent(new PlayerEnergy(player));
        player.addComponent(new PlayerState(player));
        entityManager.registerEntity(player);
    }

    /**
     * Genera la información de Sprite correspondiente al lado seleccionado del jugador
     */
    private void setSpriteInfo() {
        if (side.equals(FieldSide.TOP)) {
            spritePath = gameProperties.getSelectedCharacters()[0].spritePath();
        } else if (side.equals(FieldSide.BOTTOM)) {
            spritePath = gameProperties.getSelectedCharacters()[1].spritePath();
            spritePath = spritePath.replace("front", "back");
        }
    }
}
