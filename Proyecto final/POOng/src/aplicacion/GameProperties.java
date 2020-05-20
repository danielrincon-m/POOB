package aplicacion;

import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;

import java.io.Serializable;
import java.util.Arrays;

public class GameProperties implements Serializable {
    private final CharacterPersonality[] selectedCharacters = new CharacterPersonality[2];
    private BallType ballType = BallType.FAST;
    private int maxScore = 10;

    /**
     * Almacena al personaje elegido en la posición dada
     * @param position La posición del personaje (0 ó 1)
     * @param character El personaje elegido
     */
    public void setCharacter(int position, CharacterPersonality character) {
        selectedCharacters[position] = character;
    }

    /**
     * Deselecciona el personaje de la posición dada
     * @param position La posición del personaje a deseleccionar
     */
    public void deselectCharacter(int position) {
        selectedCharacters[position] = null;
    }

    /**
     * Establece el tipo de bola seleccionada
     * @param type El tipo de bola seleccionada
     */
    public void setBall(BallType type) {
        ballType = type;
    }

    /**
     * Establece el puntaje máximo del juego
     * @param maxScore El puntaje máximo del juego
     */
    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * @return Los personajes seleccionados,o null en la posición en la que no se haya seleccionado ninguno
     */
    public CharacterPersonality[] getSelectedCharacters() {
        return selectedCharacters;
    }

    /**
     * @return El tipo de bola seleccionada
     */
    public BallType getSelectedBallType() {
        return ballType;
    }

    /**
     * @return El puntaje máximo seleccionado
     */
    public int getMaxScore() {
        return maxScore;
    }

    /**
     * @return Si las propiedades del juego configuradas son válidas
     */
    public boolean areValid() {
        return !Arrays.asList(selectedCharacters).contains(null) &&
                ballType != null &&
                maxScore > 0;
    }
}
