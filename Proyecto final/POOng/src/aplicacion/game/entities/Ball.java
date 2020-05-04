package aplicacion.game.entities;

import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.enums.BallSpeed;
import aplicacion.game.enums.EntityName;
import aplicacion.game.utils.GameUtils;
import aplicacion.game.utils.Vector2;

import java.util.Random;

public class Ball extends Entity {

    private float speed;
    private final float maxDeviationAngle = 30;
    private int lastHitterSide;

    private Vector2 initialPosition;
    private Vector2 direction;

    private Field field;
    private ScoreBoard scoreBoard;

    public Ball(EntityName name, float xPosition, float yPosition, float width, float height, BallSpeed speed) {
        super(name, xPosition, yPosition, width, height);
        setRandomDirection();
        setSpeed(speed);
    }

    @Override
    public void start() {
        field = (Field) Entity.find(EntityName.FIELD);
        scoreBoard = (ScoreBoard) Entity.find(EntityName.SCORE_BOARD);
        initialPosition = new Vector2(transform.getPosition());
    }

    @Override
    public void update() {
        move();
        checkScore();
    }

    public void hit(int hitterSide, float centerDistancePercentage) {
        lastHitterSide = hitterSide;

        float deviationAngle = maxDeviationAngle * centerDistancePercentage;
        float xSpeed = speed * (float) Math.sin(Math.toRadians(deviationAngle));
        float ySpeed = speed * (float) (Math.cos(Math.toRadians(deviationAngle)) * lastHitterSide);
        //System.out.println(xSpeed + "," + ySpeed);
        this.direction = new Vector2(xSpeed, ySpeed).getNormalized();
    }

    public void reset(int direction) {
        transform.setPosition(new Vector2(initialPosition));
        this.direction = new Vector2(0, (float)direction);
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
            int scorerSide = field.whoScores(transform.getCenterPosition(), lastHitterSide);
            scoreBoard.addScore(scorerSide);
            reset(GameUtils.getOtherPlayerSide(scorerSide));
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

    private void setSpeed(BallSpeed speed) {
        this.speed = speed.initialSpeed();
        //System.out.println(this.speed);
    }
}
