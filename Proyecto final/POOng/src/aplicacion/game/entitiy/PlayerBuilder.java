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

public class PlayerBuilder {

    private final int zIndex;
    private final String name;
    private String spritePath;

    private final ApplicationManager applicationManager;
    private final GameProperties gameProperties;
    private final FieldSide side;
    private final EntitySpawner.Properties properties;

    public PlayerBuilder(ApplicationManager applicationManager, String name,
                         EntitySpawner.Properties properties, FieldSide side,
                         int zIndex) {
        this.applicationManager = applicationManager;
        this.gameProperties = applicationManager.getGameProperties();
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
        Entity.registerEntity(player);
    }

    private void setSpriteInfo() {
        if (side.equals(FieldSide.TOP)) {
            spritePath = gameProperties.getSelectedCharacters()[0].spriteName();
        } else if (side.equals(FieldSide.BOTTOM)) {
            spritePath = gameProperties.getSelectedCharacters()[1].spriteName();
            spritePath = spritePath.replace("front", "back");
        }
    }
}
