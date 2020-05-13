package aplicacion.game.entities;

import aplicacion.game.components.scoreBoard.Score;

public class ScoreBoard extends Entity {

    public ScoreBoard(String name) {
        super(name);
    }

    @Override
    protected void defineComponents() {
        addComponent(new Score(this));
    }
}
