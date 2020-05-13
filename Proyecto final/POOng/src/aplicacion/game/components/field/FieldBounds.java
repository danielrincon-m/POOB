package aplicacion.game.components.field;

import aplicacion.game.components.Component;
import aplicacion.game.components.common.Transform;
import aplicacion.game.entities.Entity;
import aplicacion.game.utils.Vector2;

public class FieldBounds extends Component {

    private final float screenVerticalPercentage;
    private float horizontalHeadroom;
    private float verticalHeadroom;

    public FieldBounds (Entity parent, float screenVerticalPercentage) {
        super(parent);
        this.screenVerticalPercentage = screenVerticalPercentage;
    }

    @Override
    public void start() {
        calculateHeadrooms();
    }

    @Override
    public void update() {

    }

    public boolean insideField(Vector2 otherPosition) {
        return insideX(otherPosition) && insideY(otherPosition);
    }

    public boolean insideX(Vector2 otherPosition) {
        return otherPosition.x >= transform.getPosition().x - horizontalHeadroom &&
                otherPosition.x <= transform.getPosition().x + transform.getSize().x + horizontalHeadroom;
    }

    public boolean insideY(Vector2 otherPosition) {
        return otherPosition.y >= transform.getPosition().y - verticalHeadroom &&
                otherPosition.y <= transform.getPosition().y + transform.getSize().y + verticalHeadroom;
    }

    public float getUpperBound() {
        return transform.getPosition().y;
    }

    public float getLowerBound() {
        return transform.getPosition().y + transform.getHeight();
    }

    public float getLeftBound() {
        return transform.getPosition().x;
    }

    public float getRightBound() {
        return transform.getPosition().x + transform.getSize().x;
    }

    private void calculateHeadrooms() {
        horizontalHeadroom = Entity.find("PLAYER_TOP").getComponent(Transform.class).getWidth() / 2f * 1.1f;
        verticalHeadroom = transform.getHeight() * (1 - screenVerticalPercentage);
    }
}
