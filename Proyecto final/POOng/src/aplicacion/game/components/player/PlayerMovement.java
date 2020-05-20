package aplicacion.game.components.player;

import aplicacion.exception.EntityException;
import aplicacion.game.components.Component;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.engine.Input;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

import java.awt.event.KeyEvent;

public class PlayerMovement extends Component {

    private float leftBound;
    private float rightBound;
    private final float initialSpeed = 250f;
    private float speed;
    private int leftKey;
    private int rightKey;

    private PlayerEnergy playerEnergy;
    private FieldBounds fieldBounds;
    private FieldSide fieldSide;

    public PlayerMovement(Entity parent, FieldSide fieldSide) {
        super(parent);
        this.fieldSide = fieldSide;
        setControls();
    }

    @Override
    public void start() {
        playerEnergy = parent.getComponent(PlayerEnergy.class);
        fieldBounds = Entity.find("FIELD").getComponent(FieldBounds.class);
        setLimits();
        resetSpeed();
    }

    @Override
    public void update() {
        checkMovement();
    }

    protected void setFreezed(boolean freezed) {
        if (freezed) {
            speed = 0;
        } else {
            resetSpeed();
        }
    }

    protected void setSlowedDown (boolean slowedDowm) {
        this.setSlowedDown(slowedDowm, 0.5f);
    }

    protected void setSlowedDown(boolean slowedDowm, float percentage) {
        if (slowedDowm) {
            speed -=  speed * percentage;
        } else {
            resetSpeed();
        }
    }

    private void move(Vector2 translation, int direction) {
        if (direction == 1 || direction == -1) {
            transform.translate(translation.getMultiplied(direction));
            playerEnergy.wasteEnergy();
        }
    }

    private void checkMovement() {
        Vector2 movement = new Vector2(speed * GameTimer.deltaTime(), 0);
        int direction = 0;
        if (Input.getInstance().isKeyDown(leftKey)) {
            direction = -1;
        } else if (Input.getInstance().isKeyDown(rightKey)) {
            direction = 1;
        }
        move(movement, direction);
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

    private void resetSpeed() {
        speed = initialSpeed;
    }

    private void setLimits() {
        leftBound = fieldBounds.getLeftBound() - transform.getWidth() / 2f;
        rightBound = fieldBounds.getRightBound() + transform.getWidth() / 2f;
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
