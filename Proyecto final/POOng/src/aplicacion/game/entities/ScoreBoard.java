package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.enums.FieldSide;

public class ScoreBoard extends Entity{
    private int topScore;
    private int bottomScore;

    public ScoreBoard(String name, float xPosition, float yPosition, float width, float height) {
        super(name, xPosition, yPosition, width, height);
    }

    @Override
    protected void start() {
        topScore = 0;
        bottomScore = 0;
    }

    @Override
    protected void update() {

    }

    public void addScore(FieldSide scorerSide) {
        if (scorerSide == FieldSide.TOP) {
            topScore += 1;
        } else if (scorerSide == FieldSide.BOTTOM) {
            bottomScore += 1;
        } else {
            throw new EntityException(EntityException.INVALID_NAME);
        }
    }

    public int getScore (boolean top) {
        return top ? topScore : bottomScore;
    }
}
