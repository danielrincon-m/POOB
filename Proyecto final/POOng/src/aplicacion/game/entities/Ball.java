package aplicacion.game.entities;

import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;
import aplicacion.game.utils.Vector2;

import java.util.Random;

public class Ball extends Entity {

    private float speed;
    private final float maxDeviationAngle = 30;

    private Vector2 initialPosition;
    private Vector2 direction;

    private Field field;
    private FieldSide lastHitterSide;
    private ScoreBoard scoreBoard;

    public Ball(String name, float xPosition, float yPosition, float width, float height, BallType speed) {
        super(name, xPosition, yPosition, width, height);
        setRandomDirection();
        setSpeed(speed);
    }

    @Override
    public void start() {
        field = (Field) Entity.find("FIELD");
        scoreBoard = (ScoreBoard) Entity.find("SCORE_BOARD");
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
        //System.out.println(xSpeed + "," + ySpeed);
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
        //System.out.println(initialPosition);
        //System.out.println(direction);
        //System.out.println(transform.position);
    }

    private void checkScore() {
        if (!field.insideField(transform.getCenterPosition())) {
            FieldSide scorerSide = field.whoScores();
            scoreBoard.addScore(scorerSide);
            reset(GameUtils.getOtherSide(scorerSide));
        }
    }

    private void setRandomDirection() {
        float[] possibilities = {-1f, 1f};
        Random r = new Random();
        float ySpeed = possibilities[r.nextInt(2)];
        direction = new Vector2(0, ySpeed);
        direction.normalize();
        //System.out.println(direction);
    }

    private void setSpeed(BallType speed) {
        this.speed = speed.initialSpeed();
        //System.out.println(this.speed);
    }
}
