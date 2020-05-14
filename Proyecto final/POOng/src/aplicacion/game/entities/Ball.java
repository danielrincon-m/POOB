package aplicacion.game.entities;

import aplicacion.ApplicationManager;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.enums.BallType;

public class Ball extends Entity {

    private BallType type;

    public Ball(ApplicationManager applicationManager, String name, float xPosition, float yPosition, float width,
                float height, BallType type) {
        super(applicationManager, name, xPosition, yPosition, width, height);
        this.type = type;
    }

    @Override
    protected void defineComponents() {
        addComponent(new RectangleCollider(this));
        addComponent(new Sprite(this, type.spritePath(), 2));
        addComponent(new BallMovement(this, type));
    }
}
