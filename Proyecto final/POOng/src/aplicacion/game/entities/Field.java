package aplicacion.game.entities;

import aplicacion.game.enums.EntityName;

public class Field extends Entity {
    public Field(EntityName name, float xPosition, float yPosition, float width, float height) {
        super(name, xPosition, yPosition, width, height);
    }

    @Override
    protected void start() {
    }

    @Override
    protected void update() {
    }

    public float getLeftBound() {
        return position.x;
    }

    public float getRightBound() {
        return position.x + size.x;
    }
}
