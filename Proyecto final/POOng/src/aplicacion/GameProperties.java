package aplicacion;

import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;

import java.util.Arrays;

public class GameProperties {
    private final CharacterPersonality[] selectedCharacters = new CharacterPersonality[2];
    private BallType ballType;
    private int maxScore;
    //private int maxScore = 10;

    public void setCharacter(int position, CharacterPersonality characters) {
        selectedCharacters[position] = characters;
    }

    public void deselectCharacter(int position) {
        selectedCharacters[position] = null;
    }

    public void setBall(BallType type) {
        ballType = type;
        //System.out.println(ballType);
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

    public boolean areValid() {
        return !Arrays.asList(selectedCharacters).contains(null) &&
                ballType != null &&
                maxScore > 0;
    }
}
