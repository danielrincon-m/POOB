package aplicacion;

import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GamePropertiesTest {

    GameProperties gp;

    @BeforeEach
    void setUp() {
        gp = new GameProperties();
    }

    @Test
    public void deberiaRemoverLosCaracteresSeleccionadosDeLosSeleccionables() {
        int characterCount = 0;

        for (CharacterPersonality cp : CharacterPersonality.values()) {
            if (cp.getType().equals(CharacterType.HUMAN)) {
                characterCount++;
            }
        }

        //Deberia Mostrar todos los personajes
        assertEquals(characterCount, gp.getAvailablePlayerCharacters().size());
        gp.setCharacter(0, CharacterPersonality.EXTREME);
        //Deberia mostrar todos los personajes ya que seleccionamos una máquina
        assertEquals(characterCount, gp.getAvailablePlayerCharacters().size());
        gp.setCharacter(1, CharacterPersonality.HARRY);
        //Deberia mostrar menos 1 personaje ya que seleccionamos un personaje humano
        assertEquals(characterCount - 1, gp.getAvailablePlayerCharacters().size());
    }

    @Test
    public void deberiaLimpiarlaListaDeCaracteres() {
        gp.setCharacter(0, CharacterPersonality.HERMIONE);
        gp.setCharacter(1, CharacterPersonality.HERMIONE);
        assertFalse(Arrays.asList(gp.getSelectedCharacters()).contains(null));
        gp.deselectCharacters();
        assertNull(gp.getSelectedCharacters()[0]);
        assertNull(gp.getSelectedCharacters()[1]);
    }

    @Test
    public void deberiaValidarLasPropiedadesCorrectamente() {
        assertFalse(gp.areValid());
        gp.setCharacter(0, CharacterPersonality.HERMIONE);
        gp.setCharacter(1, CharacterPersonality.HERMIONE);
        assertTrue(gp.areValid());
    }
}