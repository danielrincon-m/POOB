package aplicacion.game.components.target;

import aplicacion.game.components.Component;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.components.scoreBoard.Score;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entities.Ball;
import aplicacion.game.entities.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;
import aplicacion.game.utils.Vector2;

import java.util.Random;

public class TargetBehaviour extends Component {

    private int scoreBonus;
    private final int maxScore;
    private float leftBound;
    private float rightBound;
    private float lifetime = 10;

    private RectangleCollider collider;
    private final TargetController targetController;
    private final FieldSide side;
    private Score scoreBoard;
    private FieldBounds fieldBounds;
    private BallMovement ballMovement;
    private RectangleCollider ballCollider;

    public TargetBehaviour(Entity parent, FieldSide side, int maxScore, TargetController targetController) {
        super(parent);
        this.maxScore = maxScore;
        this.side = side;
        this.targetController = targetController;
    }

    @Override
    public void start() {
        scoreBoard = Entity.find("SCORE_BOARD").getComponent(Score.class);
        ballMovement = Entity.find("BALL").getComponent(BallMovement.class);
        ballCollider = Entity.find("BALL").getComponent(RectangleCollider.class);
        fieldBounds = Entity.find("FIELD").getComponent(FieldBounds.class);
        collider = parent.getComponent(RectangleCollider.class);
        setScoreBonus();
        getBounds();
        setPosition();
        setSize();
    }

    @Override
    public void update() {
        checkDisappear();
        checkBallHit();
    }

    private void checkDisappear() {
        lifetime -= GameTimer.deltaTime();
        if (lifetime <= 0) {
            remove();
        }
    }

    private void checkBallHit() {
        if (collider.collidesWith(ballCollider)) {
            addScore();
            remove();
        }
    }

    private void addScore() {
        FieldSide winner = side.equals(FieldSide.TOP) ? FieldSide.BOTTOM : FieldSide.TOP;
        scoreBoard.addScore(winner, scoreBonus);
        ballMovement.reset(GameUtils.getOtherSide(winner));
    }

    private void remove() {
        targetController.removeTarget(side, parent.getName());
    }

    private void getBounds() {
        leftBound = fieldBounds.getLeftBound();
        rightBound = fieldBounds.getRightBound();
    }

    private void setScoreBonus() {
        Random r = new Random();
        int bound = ((maxScore / 2) - 2) + 1;
        if (bound > 0) {
            scoreBonus = r.nextInt(bound) + 2;
        } else {
            scoreBonus = 1;
        }
    }

    private void setPosition() {
        Vector2 position = transform.getPosition();
        Random r = new Random();
        //random xPosition
        position.x = leftBound + r.nextFloat() * (rightBound - leftBound);
        position.x -= transform.getWidth() / 2f;
        //yPosition
        if (side.equals(FieldSide.TOP)) {
            position.y = 10;
        } else {
            position.y = 740;
        }
        transform.setPosition(position);
    }

    private void setSize() {
        Vector2 size = new Vector2(100, 50);
        transform.setSize(size);
    }
}
