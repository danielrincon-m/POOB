package aplicacion.game.components.player;

import aplicacion.exception.EntityException;
import aplicacion.game.components.Component;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.engine.input.Input;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

import java.awt.event.KeyEvent;

/**
 * Componente del jugador que se encarga de moverlo en el eje x
 */
public class PlayerMovement extends Component {

    protected float leftBound;
    protected float rightBound;
    protected final float initialSpeed = 280f;
    protected float speed;
    protected int leftKey;
    protected int rightKey;

    protected PlayerEnergy playerEnergy;
    protected FieldBounds fieldBounds;
    protected final FieldSide fieldSide;

    /**
     * @param parent    La Entidad que contiene este componente
     * @param fieldSide El lado del campo en el que se encuentra el jugador padre
     */
    public PlayerMovement(Entity parent, FieldSide fieldSide) {
        super(parent);
        this.fieldSide = fieldSide;
        setControls();
    }

    @Override
    public void start() {
        playerEnergy = parent.getComponent(PlayerEnergy.class);
        fieldBounds = entityManager.find("FIELD").getComponent(FieldBounds.class);
        setLimits();
        resetSpeed();
    }

    @Override
    public void update() {
        checkMovement();
    }

    /**
     * Establece el estado congelado del jugador
     *
     * @param freezed Si se congela o se descongela
     */
    protected void setFreezed(boolean freezed) {
        if (freezed) {
            speed = 0;
        } else {
            resetSpeed();
        }
    }

    /**
     * Establece el estado realentizado del jugador
     *
     * @param slowedDowm Si se mueve lento o no
     */
    protected void setSlowedDown(boolean slowedDowm) {
        this.setSlowedDown(slowedDowm, 0.5f);
    }

    /**
     * Establece el estado realentizado del jugador
     *
     * @param slowedDowm Si se mueve lento o no
     * @param percentage El porcentaje de velocidad que se le quitará al jugador
     */
    protected void setSlowedDown(boolean slowedDowm, float percentage) {
        if (slowedDowm) {
            speed -= speed * percentage;
        } else {
            resetSpeed();
        }
    }

    /**
     * Mueve al jugador
     *
     * @param translation El delta de movimiento del jugador en el eje x
     * @param direction   La dirección de movimiento del jugador (-1, 1)
     */
    protected void move(Vector2 translation, int direction) {
        if ((direction == 1 || direction == -1) && speed != 0) {
            transform.translate(translation.getMultiplied(direction));
            if (!checkOutOfBounds()) {
                playerEnergy.wasteEnergy();
            }
        }
    }

    /**
     * Verifica si el jugador se debe mover según las teclas presionadas
     */
    protected void checkMovement() {
        Vector2 movement = new Vector2(speed * GameTimer.deltaTime(), 0);
        int direction = 0;
        if (Input.getInstance().isKeyDown(leftKey)) {
            direction = -1;
        } else if (Input.getInstance().isKeyDown(rightKey)) {
            direction = 1;
        }
        move(movement, direction);
    }

    /**
     * Verifica si el jugador se encuentra fuera del campo de juego y lo devuelve al borde
     * si es así
     */
    protected boolean checkOutOfBounds() {
        if (transform.getCenterPosition().x < leftBound) {
            transform.setPosition(
                    new Vector2(leftBound - transform.getWidth() / 2f, transform.getPosition().y)
            );
            return true;
        }
        if (transform.getCenterPosition().x > rightBound) {
            transform.setPosition(
                    new Vector2(rightBound - transform.getSize().x / 2f, transform.getPosition().y)
            );
            return true;
        }
        return false;
    }

    /**
     * Regresa la velocidad del jugador
     * a su velocidad inicial
     */
    protected void resetSpeed() {
        speed = initialSpeed;
    }

    /**
     * Establece los límites del jugaodr en el campo con respecto sus bordes
     */
    protected void setLimits() {
        leftBound = fieldBounds.getLeftBound() - transform.getWidth() / 4f;
        rightBound = fieldBounds.getRightBound() + transform.getWidth() / 4f;
    }

    /**
     * Establece los controles que afectan al jugador
     * dependiendo de su posición
     */
    protected void setControls() {
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
