package aplicacion;

import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;

public class GameProperties {
    private final CharacterPersonality[] selectedCharacters = new CharacterPersonality[2];
    //private final CharacterPersonality[] selectedCharacters = {CharacterPersonality.HARRY,CharacterPersonality.HARRY};
    //private BallType ballType;
    private BallType ballType = BallType.FAST;
    private int maxScore = 10;

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

    public BallType getSelectedBallType() {
        return ballType;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public boolean getValidCharacters() {
        if (selectedCharacters[0] != null && selectedCharacters[1] != null) {
            return true;
        } else {
            return false;
        }
    }


    /*
        FIXME: Falta probar el método cuando no retorne siempre true
         */
    public boolean areValid() {
        if (getValidCharacters() && ballType != null && maxScore > 0) {
            return true;
        } else {
            return false;
        }
        //return true;
        /*return !Arrays.asList(selectedCharacters).contains(null) &&
                ballType != null &&
                maxScore > 0;*/
    }
}
