package aplicacion;

import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumSet;

public class GameProperties implements Serializable {
    private final CharacterPersonality[] selectedCharacters = new CharacterPersonality[2];
    private BallType ballType = BallType.FAST;
    private int maxScore = 10;

    public GameProperties() {
    }

    /**
     * @return los jugadores disponibles para ser seleccionados en un HashMap {CharacterPersonality -> BufferedImage}
     */
    public EnumSet<CharacterPersonality> getAvailablePlayerCharacters() {
        EnumSet<CharacterPersonality> playerPersonalities = EnumSet.allOf(CharacterPersonality.class);
        CharacterPersonality[] selectedCharacters = getSelectedCharacters();
        for (CharacterPersonality cp : selectedCharacters) {
            if (cp != null) {
                playerPersonalities.remove(cp);
            }
        }
        playerPersonalities.removeIf(cp -> cp.getType().equals(CharacterType.MACHINE));
        return playerPersonalities;
    }

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
     */
    public void deselectCharacters() {
        Arrays.fill(selectedCharacters, null);
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
