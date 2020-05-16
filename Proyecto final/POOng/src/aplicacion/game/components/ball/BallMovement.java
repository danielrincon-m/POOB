package aplicacion.game.components.ball;

import aplicacion.game.components.Component;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;
import aplicacion.game.utils.Vector2;

import java.util.Random;

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

    public BallMovement(Entity parent, BallType type) {
        super(parent);
        this.TYPE = type;
        setRandomDirection();
        setInitialSpeed();
    }

    @Override
    public void start() {
        fieldBounds = Entity.find("FIELD").getComponent(FieldBounds.class);
        score = Entity.find("SCORE_BOARD").getComponent(Score.class);
        initialPosition = new Vector2(transform.getPosition());
    }

    @Override
    public void update() {
        move();
        checkIncremental();
        checkScore();
    }

    public void hit(FieldSide hitterSide, float centerDistancePercentage) {
        lastHitterSide = hitterSide;
        float deviationAngle = MAX_DEVIATION_ANGLE * centerDistancePercentage;
        float xSpeed = speed * (float) Math.sin(Math.toRadians(deviationAngle));
        float ySpeed = speed * (float) (Math.cos(Math.toRadians(deviationAngle)) * lastHitterSide.sideValue());
        this.direction = new Vector2(xSpeed, ySpeed).getNormalized();
    }

    public void reset(FieldSide moveTowards) {
        transform.setPosition(new Vector2(initialPosition));
        direction = new Vector2(0, moveTowards.sideValue());
        lastHitterSide = moveTowards;
        resetSpeed();
    }

    public void fastBall() {
        increaseSpeed(true, 0.2f);
    }

    public void flashBall() {
        increaseSpeed(false, 0.8f);
    }

    public FieldSide getLastHitterSide() {
        return lastHitterSide;
    }

    private void move() {
        Vector2 displacement = direction.getMultiplied(speed).getMultiplied(GameTimer.deltaTime());
        transform.translate(displacement);
    }

    private void checkIncremental() {
        if (TYPE.equals(BallType.INCREMENTAL)) {
            increaseSpeed(false, SPEED_INCREASE_PERCENTAGE * GameTimer.deltaTime());
        }
    }

    private void checkScore() {
        if (!fieldBounds.insideField(transform.getCenterPosition())) {
            FieldSide winnerSide = score.caclulateBallScore();
            reset(GameUtils.getOtherSide(winnerSide));
        }
    }

    private void setRandomDirection() {
        float[] possibilities = {-1f, 1f};
        Random r = new Random();
        float ySpeed = possibilities[r.nextInt(2)];
        direction = new Vector2(0, ySpeed);
        direction.normalize();
        lastHitterSide = ySpeed == 1 ? FieldSide.BOTTOM : FieldSide.TOP;
    }

    private void increaseSpeed(boolean permanent, float percentage) {
        if (permanent) {
            initialSpeed *= 1 + percentage;
        }
        speed *= 1 + percentage;
    }

    private void resetSpeed() {
        speed = initialSpeed;
    }

    private void setInitialSpeed() {
        initialSpeed = TYPE.initialSpeed();
        resetSpeed();
    }
}
