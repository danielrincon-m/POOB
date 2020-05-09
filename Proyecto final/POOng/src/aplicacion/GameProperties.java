package aplicacion;

import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterProperties;

public class GameProperties {
    private final CharacterProperties[] selectedCharacters = new CharacterProperties[2];
    private BallType ballType;
    private int maxScore;

    public void setCharacter(int position, CharacterProperties characters) {
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
