package aplicacion.game.components.scoreBoard;

import aplicacion.game.components.Component;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.entities.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;

import java.util.HashMap;

public class Score extends Component {
    private HashMap<FieldSide, Integer> score;

    private BallMovement ballMovement;
    private Transform ballTransform;
    private FieldBounds fieldBounds;

    public Score(Entity parent) {
        super(parent);
    }

    @Override
    public void start() {
        ballMovement = Entity.find("BALL").getComponent(BallMovement.class);
        ballTransform = Entity.find("BALL").getComponent(Transform.class);
        fieldBounds = Entity.find("FIELD").getComponent(FieldBounds.class);
        initializeScore();
    }

    @Override
    public void update() {

    }

    public FieldSide score() {
        FieldSide winner = null;
        if (!fieldBounds.insideX(ballTransform.getCenterPosition())) {
            winner = GameUtils.getOtherSide(ballMovement.getLastHitterSide());
            addScore(winner);
        } else if (!fieldBounds.insideY(ballTransform.getCenterPosition())) {
            winner = ballMovement.getLastHitterSide();
            addScore(winner);
        }
        return winner;
    }

    private void addScore(FieldSide winner) {
        score.put(winner, score.get(winner) + 1);
    }

    public int getScore(boolean top) {
        return top ? score.get(FieldSide.TOP) : score.get(FieldSide.BOTTOM);
    }

    private void initializeScore() {
        score = new HashMap<>();
        for (FieldSide side : FieldSide.values()) {
            score.put(side, 0);
        }
    }
}
