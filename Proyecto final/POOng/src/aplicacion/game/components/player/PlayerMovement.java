package aplicacion.game.components.player;

import aplicacion.exception.EntityException;
import aplicacion.game.components.Component;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.engine.Input;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entities.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

import java.awt.event.KeyEvent;

public class PlayerMovement extends Component {

    private float leftBound;
    private float rightBound;
    private float speed = 190f;
    private int leftKey;
    private int rightKey;

    private FieldBounds fieldBounds;
    private FieldSide fieldSide;

    public PlayerMovement(Entity parent, FieldSide fieldSide) {
        super(parent);
        this.fieldSide = fieldSide;
        setControls();
    }

    @Override
    public void start() {
        fieldBounds = Entity.find("FIELD").getComponent(FieldBounds.class);
        setLimits();
    }

    @Override
    public void update() {
        checkMovement();
    }

    private void move(Vector2 translation, int direction) {
        if (direction != 1 && direction != -1) {
            throw new EntityException(EntityException.INVALID_DIRECTION);
        }
        transform.translate(translation.getMultiplied(direction));
    }

    private void checkMovement() {
        Vector2 movement = new Vector2(speed * GameTimer.deltaTime(), 0);
        if (Input.getInstance().isKeyDown(leftKey)) {
            move(movement, -1);
        } else if (Input.getInstance().isKeyDown(rightKey)) {
            move(movement, 1);
        }
        checkOutOfBounds();
    }

    private void checkOutOfBounds() {
        if (transform.getCenterPosition().x < leftBound) {
            transform.setPosition(
                    new Vector2(leftBound - transform.getWidth() / 2f, transform.getPosition().y)
            );
        }
        if (transform.getCenterPosition().x > rightBound) {
            transform.setPosition(
                    new Vector2(rightBound - transform.getSize().x / 2f, transform.getPosition().y)
            );
        }
    }

    private void setLimits() {
        leftBound = fieldBounds.getLeftBound();
        rightBound = fieldBounds.getRightBound();
    }

    private void setControls() {
        if (fieldSide == FieldSide.TOP) {
            leftKey = KeyEvent.VK_A;
            rightKey = KeyEvent.VK_D;
        } else if (fieldSide == FieldSide.BOTTOM) {
            leftKey = KeyEvent.VK_LEFT;
            rightKey = KeyEvent.VK_RIGHT;
        } else {
            throw new EntityException(EntityException.INVALID_NAME);
        }
    }
}
