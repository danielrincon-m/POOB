package aplicacion;

import aplicacion.game.enums.BallType;
import aplicacion.game.enums.Characters;

public class GameProperties {
    private final Characters[] selectedCharacters = new Characters[2];
    private BallType ballType;
    private int maxScore;

    public void setCharacter(int position, Characters characters) {
        selectedCharacters[position] = characters;
    }

    public void setBall(BallType type) {
        ballType = type;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    /*
    FIXME: Implementar la validación
     */
    public boolean areValid() {
        return true;
    }
}
