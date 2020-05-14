package aplicacion.game.entities;

import aplicacion.ApplicationManager;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.enums.BallType;

public class Ball extends Entity {

    private BallType speed;

    public Ball(ApplicationManager applicationManager, String name, float xPosition, float yPosition, float width, float height, BallType speed) {
        super(applicationManager, name, xPosition, yPosition, width, height);
        this.speed = speed;
    }

    @Override
    protected void defineComponents() {
        addComponent(new RectangleCollider(this));
        addComponent(new Sprite(this, speed.spritePath(), 2));
        addComponent(new BallMovement(this, speed));
    }
}
