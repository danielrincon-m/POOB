package aplicacion.game.components.ball;

import aplicacion.game.components.Component;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entities.Entity;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;
import aplicacion.game.utils.Vector2;

import java.util.Random;

public class BallMovement extends Component {

    private float speed;
    private final float maxDeviationAngle = 30;

    private Vector2 initialPosition;
    private Vector2 direction;

    private FieldBounds fieldBounds;
    private FieldSide lastHitterSide;
    private Score score;

    public BallMovement(Entity parent, BallType speed) {
        super(parent);
        setRandomDirection();
        setSpeed(speed);
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
        checkScore();
    }

    public void hit(FieldSide hitterSide, float centerDistancePercentage) {
        lastHitterSide = hitterSide;

        float deviationAngle = maxDeviationAngle * centerDistancePercentage;
        float xSpeed = speed * (float) Math.sin(Math.toRadians(deviationAngle));
        float ySpeed = speed * (float) (Math.cos(Math.toRadians(deviationAngle)) * lastHitterSide.sideValue());
        this.direction = new Vector2(xSpeed, ySpeed).getNormalized();
    }

    public FieldSide getLastHitterSide() {
        return lastHitterSide;
    }

    public void reset(FieldSide moveTowards) {
        transform.setPosition(new Vector2(initialPosition));
        this.direction = new Vector2(0, (float)moveTowards.sideValue());
    }

    private void move() {
        Vector2 displacement = direction.getMultiplied(speed).getMultiplied(GameTimer.deltaTime());
        transform.translate(displacement);
    }

    private void checkScore() {
        if (!fieldBounds.insideField(transform.getCenterPosition())) {
            FieldSide winnerSide = score.score();
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

    private void setSpeed(BallType speed) {
        this.speed = speed.initialSpeed();
    }
}
