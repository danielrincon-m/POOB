package aplicacion;

import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;

public class GameProperties {
    private final CharacterPersonality[] selectedCharacters = new CharacterPersonality[2];
    private BallType ballType;
    private int maxScore;

    public void setCharacter(int position, CharacterPersonality characters) {
        selectedCharacters[position] = characters;
    }

    public void setBall(BallType type) {
        ballType = type;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public CharacterPersonality[] getSelectedCharacters() {
        return selectedCharacters;
    }

    /*
        FIXME: Falta probar el método cuando no retorne siempre true
         */
    public boolean areValid() {
        return true;
        /*return !Arrays.asList(selectedCharacters).contains(null) &&
                ballType != null &&
                maxScore > 0;*/
    }
}
