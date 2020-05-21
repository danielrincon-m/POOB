package aplicacion.game.components.ball;

import aplicacion.game.components.Component;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;
import aplicacion.game.utils.Vector2;

import java.util.Random;

/**
 * Componente encargado del movimiento de la bola
 */
public class BallMovement extends Component {

    private float initialSpeed;
    private float speed;
    private final float SPEED_INCREASE_PERCENTAGE = 0.05f;
    private final float MAX_DEVIATION_ANGLE = 30;

    private Vector2 initialPosition;
    private Vector2 direction;

    private final BallType TYPE;
    private FieldBounds fieldBounds;
    private FieldSide lastHitterSide;
    private Score score;

    /**
     * Constructor de BallMovement
     * @param parent La entidad a la que pertenece este componente
     * @param type El tipo de bola creado
     */
    public BallMovement(Entity parent, BallType type) {
        super(parent);
        this.TYPE = type;
        setRandomDirection();
        setInitialSpeed();
    }

    @Override
    public void start() {
        fieldBounds = entityManager.find("FIELD").getComponent(FieldBounds.class);
        score = entityManager.find("SCORE_BOARD").getComponent(Score.class);
        initialPosition = new Vector2(transform.getPosition());
    }

    @Override
    public void update() {
        move();
        checkIncremental();
        checkScore();
    }

    /**
     * Método que se encarga de cambiar la dirección de la pelota en base a quien la golpea
     * @param hitterSide El lado en el que se encuentra el golpeador de la pelota
     * @param centerDistancePercentage El porcentaje de distanca desde el centro del golpeador hacia su borde
     *                                 con respecto al centro de la bola (el lado izquierdo debe der negativo)
     */
    public void hit(FieldSide hitterSide, float centerDistancePercentage) {
        lastHitterSide = hitterSide;
        float deviationAngle = MAX_DEVIATION_ANGLE * centerDistancePercentage;
        float xSpeed = speed * (float) Math.sin(Math.toRadians(deviationAngle));
        float ySpeed = speed * (float) (Math.cos(Math.toRadians(deviationAngle)) * lastHitterSide.sideValue());
        direction = new Vector2(xSpeed, ySpeed).getNormalized();
    }

    /**
     * Revierte la dirección de la pelota en el eje y, cambiando el golpeador
     */
    public void reverseY() {
        direction = new Vector2(direction.x, -direction.y);
        lastHitterSide = GameUtils.getOtherSide(lastHitterSide);
    }

    /**
     * Reinicia la posición de la bola, y cambia su dirección
     * @param moveTowards Dirección en la cual se debe mover
     */
    public void reset(FieldSide moveTowards) {
        transform.setPosition(new Vector2(initialPosition));
        direction = new Vector2(0, moveTowards.sideValue());
        lastHitterSide = moveTowards;
        resetSpeed();
    }

    /**
     * Alteración del estado gracias a la sorpresa FastBall
     */
    public void fastBall() {
        increaseSpeed(true, 0.2f);
    }

    /**
     * Alteración del estado gracias a la sorpresa FlashBall
     */
    public void flashBall() {
        increaseSpeed(false, 0.8f);
    }

    /**
     * @return El FieldSide del último juegador que golpeó la bola
     */
    public FieldSide getLastHitterSide() {
        return lastHitterSide;
    }

    /**
     * Desplaza la bola en base a su dirección y su velocidad
     */
    private void move() {
        Vector2 displacement = direction.getMultiplied(speed).getMultiplied(GameTimer.deltaTime());
        transform.translate(displacement);
    }

    /**
     * Verifica si la bola es de tipo incremental, y aumenta su velocidad
     */
    private void checkIncremental() {
        if (TYPE.equals(BallType.INCREMENTAL)) {
            increaseSpeed(false, SPEED_INCREASE_PERCENTAGE * GameTimer.deltaTime());
        }
    }

    /**
     * Verifica si la bola salió del campo, realiza el score correspondiente y reinicia la posición y velocidad de
     * la bola
     */
    private void checkScore() {
        if (!fieldBounds.insideField(transform.getCenterPosition())) {
            FieldSide winnerSide = score.caclulateBallScore();
            reset(GameUtils.getOtherSide(winnerSide));
        }
    }

    /**
     * Genera una dirección aleatoria de la bola entre las posibilidades (arriba, abajo)
     */
    private void setRandomDirection() {
        float[] possibilities = {-1f, 1f};
        Random r = new Random();
        float ySpeed = possibilities[r.nextInt(2)];
        direction = new Vector2(0, ySpeed);
        direction.normalize();
        lastHitterSide = ySpeed == 1 ? FieldSide.BOTTOM : FieldSide.TOP;
    }

    /**
     * Incrementa la velocidad de la bola, permanentemente, o hasta que se reinicie
     * @param permanent Si el incremento de la velocidad es permanente
     * @param percentage El porcentaje de incremento de la velocidad con respecto
     *                   a la velocidad actual
     */
    private void increaseSpeed(boolean permanent, float percentage) {
        if (permanent) {
            initialSpeed *= 1 + percentage;
        }
        speed *= 1 + percentage;
    }

    /**
     * Reinicia la velocidad de la pelota a la velocidad inicial, con los cambios
     * permanentes de velocidad aplicados
     */
    private void resetSpeed() {
        speed = initialSpeed;
    }

    /**
     * Establece la velocidad inicial de la bola basada en su tipo
     */
    private void setInitialSpeed() {
        initialSpeed = TYPE.initialSpeed();
        resetSpeed();
    }
}
