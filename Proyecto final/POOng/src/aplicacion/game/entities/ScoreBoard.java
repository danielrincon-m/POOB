package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.enums.EntityName;

public class ScoreBoard extends Entity{
    private int topScore;
    private int bottomScore;

    public ScoreBoard(EntityName name, float xPosition, float yPosition, float width, float height) {
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

    public void addScore(int scorerSide) {
        if (scorerSide == 1) {
            topScore += 1;
        } else if (scorerSide == -1) {
            bottomScore += 1;
        } else {
            throw new EntityException(EntityException.INVALID_NAME);
        }
    }

    public int getScore (boolean top) {
        return top ? topScore : bottomScore;
    }
}
