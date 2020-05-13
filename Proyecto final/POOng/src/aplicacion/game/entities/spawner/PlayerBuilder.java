package aplicacion.game.entities.spawner;

import aplicacion.GameProperties;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.entities.Entity;
import aplicacion.game.entities.Player;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

public class PlayerBuilder {

    private int zIndex;
    private String name;
    private String spritePath;

    private GameProperties gameProperties;
    private FieldSide side;
    private EntitySpawner.Properties properties;
    private Player player;

    public PlayerBuilder(GameProperties gameProperties, String name,
                         EntitySpawner.Properties properties, FieldSide side,
                         int zIndex) {
        this.gameProperties = gameProperties;
        this.name = name;
        this.properties = properties;
        this.side = side;
        this.zIndex = zIndex;
        spawn();
    }

    private void spawn() {
        setSpriteInfo();
        player = new Player(name,
                properties.xPosition,
                properties.yPosition,
                properties.dimension,
                properties.dimension,
                side,
                spritePath,
                zIndex);
        player.addComponent(new RectangleCollider(player,
                new Vector2(properties.colOffsetX, properties.colOffsetY),
                new Vector2(properties.colWidth, properties.colHeight)));
        Entity.registerEntity(player);
    }

    private void setSpriteInfo() {
        if (side.equals(FieldSide.TOP)) {
            spritePath = gameProperties.getSelectedCharacters()[0].spritePath();
        } else if (side.equals(FieldSide.BOTTOM)) {
            spritePath = gameProperties.getSelectedCharacters()[1].spritePath();
            spritePath = spritePath.replace("front", "back");
        }
    }
}
