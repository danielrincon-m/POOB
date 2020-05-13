package aplicacion.game.entities;

import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.field.FieldBounds;

public class Field extends Entity {

    private final float screenVerticalPercentage;


    public Field(String name, float xPosition, float yPosition, float width, float height, float screenVerticalPercentage) {
        super(name, xPosition, yPosition, width, height);
        this.screenVerticalPercentage = screenVerticalPercentage;
    }

    @Override
    protected void defineComponents() {
        addComponent(new FieldBounds(this, screenVerticalPercentage));
        addComponent(new Sprite(this, "/resources/fondotablero.png", 0));
    }
}
