package aplicacion.game.components.scoreBoard;

import aplicacion.game.components.Component;
import aplicacion.game.components.ball.BallMovement;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.entitiy.Entity;
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
        ballMovement = entityManager.find("BALL").getComponent(BallMovement.class);
        ballTransform = entityManager.find("BALL").getComponent(Transform.class);
        fieldBounds = entityManager.find("FIELD").getComponent(FieldBounds.class);
        initializeScore();
    }

    @Override
    public void update() {
        //System.out.println(score.get(FieldSide.TOP) + ", " + score.get(FieldSide.BOTTOM));
    }

    public FieldSide caclulateBallScore() {
        FieldSide winner = null;
        if (!fieldBounds.insideX(ballTransform.getCenterPosition())) {
            winner = GameUtils.getOtherSide(ballMovement.getLastHitterSide());
            addScore(winner, 1);
        } else if (!fieldBounds.insideY(ballTransform.getCenterPosition())) {
            winner = ballMovement.getLastHitterSide();
            addScore(winner, 1);
        }
        return winner;
    }

    public void addScore(FieldSide winner, int amount) {
        score.put(winner, score.get(winner) + amount);
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
