package aplicacion.game.entities.spawner;

import aplicacion.GameProperties;
import aplicacion.game.components.RectangleCollider;
import aplicacion.game.components.Sprite;
import aplicacion.game.entities.Entity;
import aplicacion.game.entities.Player;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

public class PlayerBuilder {

    int zIndex;
    String name;

    GameProperties gameProperties;
    FieldSide side;
    EntitySpawner.Properties properties;
    Player player;

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
        player = new Player(name,
                properties.xPosition,
                properties.yPosition,
                properties.dimension,
                properties.dimension,
                side);
        player.addComponent(new RectangleCollider(player,
                new Vector2(properties.colOffsetX, properties.colOffsetY),
                new Vector2(properties.colWidth, properties.colHeight)));
        setSprite();
        //Entity.registerEntity(player);
    }

    private void setSprite() {
        String spritePath = null;
        if (side.equals(FieldSide.TOP)) {
            spritePath = gameProperties.getSelectedCharacters()[0].spritePath();
        } else if (side.equals(FieldSide.BOTTOM)) {
            spritePath = gameProperties.getSelectedCharacters()[1].spritePath();
            spritePath = spritePath.replace("front", "back");
        }
        player.addComponent(new Sprite(spritePath, zIndex));
    }
}
