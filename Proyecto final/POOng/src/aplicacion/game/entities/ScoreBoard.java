package aplicacion.game.entities;

import aplicacion.ApplicationManager;
import aplicacion.game.components.scoreBoard.Score;

public class ScoreBoard extends Entity {

    public ScoreBoard(ApplicationManager applicationManager, String name) {
        super(applicationManager, name);
    }

    @Override
    protected void defineComponents() {
        addComponent(new Score(this));
    }
}
