package aplicacion.game.entities;

import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.player.PlayerHit;
import aplicacion.game.components.player.PlayerMovement;
import aplicacion.game.enums.FieldSide;

public class Player extends Entity {

    private final String spritePath;
    private final int zIndex;

    private final FieldSide fieldSide; // 1 -> TOP, -1 -> BOTTOM

    public Player(String name, float xPosition, float yPosition, float width, float height,
                  FieldSide fieldSide, String spritePath, int zIndex) {
        super(name, xPosition, yPosition, width, height);
        this.fieldSide = fieldSide;
        this.spritePath = spritePath;
        this.zIndex = zIndex;
    }

    @Override
    protected void defineComponents() {
        addComponent(new Sprite(this, spritePath, zIndex));
        addComponent(new PlayerMovement(this, fieldSide));
        addComponent(new PlayerHit(this, fieldSide));
    }
}
