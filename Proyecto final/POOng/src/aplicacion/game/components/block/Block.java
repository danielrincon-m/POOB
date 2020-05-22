package aplicacion.game.components.block;

import aplicacion.game.components.Component;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.utils.Vector2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Componente que se encarga de darle su comportamiento al bloque
 */
public class Block extends Component {

    private boolean inField = false;
    private final float MIN_LIFETIME = 5;
    private final float MAX_LIFETIME = 8;
    private final float MIN_FREE_WINDOW = 4;
    private final float MAX_FREE_WINDOW = 6;
    private float lifeTime;
    private float nextSpawnTime;

    private Entity field;
    private BallMovement ballMovement;
    private FieldBounds fieldBounds;
    private RectangleCollider ballCollider;
    private RectangleCollider myCollider;

    /**
     * @param parent la Entidad que contiene este Componente
     */
    public Block(Entity parent) {
        super(parent);
    }

    @Override
    public void start() {
        Entity ball = entityManager.find("BALL");
        ballMovement = ball.getComponent(BallMovement.class);
        ballCollider = ball.getComponent(RectangleCollider.class);
        field = entityManager.find("FIELD");
        fieldBounds = field.getComponent(FieldBounds.class);
        myCollider = parent.getComponent(RectangleCollider.class);
        setSize();
        disappear();
    }

    @Override
    public void update() {
        checkBallHit();
        if (inField) {
            checkDisappear();
        } else {
            checkAppear();
        }
    }

    /**
     * Verifica si colisionó con la bola y cambia su dirección si es así
     */
    private void checkBallHit() {
        if (myCollider.collidesWith(ballCollider)) {
            ballMovement.reverseY();
            disappear();
        }
    }

    /**
     * Verifica si el bloque debe aparecer
     */
    private void checkAppear() {
        nextSpawnTime -= GameTimer.deltaTime();
        if (nextSpawnTime <= 0) {
            appear();
        }
    }

    /**
     * Hace aparecer el bloque y comienza a contar el tiempo de desaparicion
     */
    private void appear() {
        setPosition();
        setLifeTime();
        inField = true;
    }

    /**
     * Verifica si el bloque debe desaparecer por tiempo
     */
    private void checkDisappear() {
        lifeTime -= GameTimer.deltaTime();
        if (lifeTime <= 0) {
            disappear();
        }
    }

    /**
     * Hace desaparecer el bloque y comienza a contar el tiempo de aparicion
     */
    private void disappear() {
        transform.setPosition(new Vector2(5000, 5000));
        setNextSpawnTime();
        inField = false;
    }

    /**
     * Define el tamaño del bloque
     */
    private void setSize() {
        transform.setSize(new Vector2(50, 50));
    }

    /**
     * Define una posición aleatoria para el bloque en el centro del tablero
     */
    private void setPosition() {
        inField = true;
        float yPosition = field.getComponent(Transform.class).getCenterPosition().y - transform.getHeight() / 2.5f;
        float xPosition = fieldBounds.getRandomXPosition() - transform.getWidth() / 2f;
        transform.setPosition(new Vector2(xPosition, yPosition));
    }

    /**
     * Define un tiempo de vida aleatorio entre el rango por defecto
     */
    private void setLifeTime() {
        lifeTime = ThreadLocalRandom.current().nextFloat() * (MAX_LIFETIME - MIN_LIFETIME) + MIN_LIFETIME;
    }

    /**
     * Define el tiempo en el que aparecerá el siguiente bloque en el rango por defecto
     */
    private void setNextSpawnTime() {
        nextSpawnTime = ThreadLocalRandom.current().nextFloat() * (MAX_FREE_WINDOW - MIN_FREE_WINDOW) + MIN_FREE_WINDOW;
    }
}

