package aplicacion;

import aplicacion.exception.ApplicationException;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;
import aplicacion.game.enums.CharacterType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceManagerTest {

    ResourceManager rm = new ResourceManager(null);

    @Test
    public void laFuncionDeJugadorNoDeberiaAceptarMaquinas() {
        try {
            rm.getPlayerImage(CharacterPersonality.EXTREME);
            fail("No se lanzó la excepción");
        } catch (ApplicationException e) {
            assertEquals(ApplicationException.NOT_A_CHARACTER, e.getMessage());
        }
    }

    @Test
    public void laFuncionDeMaquinaNoDeberiaAceptarJugadores() {
        try {
            rm.getMachineImage(CharacterPersonality.BOB);
            fail("No se lanzó la excepción");
        } catch (ApplicationException e) {
            assertEquals(ApplicationException.NOT_A_MACHINE, e.getMessage());
        }
    }

    @Test
    public void deberiaRemoverLosCaracteresSeleccionadosDeLosSeleccionables() {
        ApplicationManager testManager = new ApplicationManager(null);
        ResourceManager rm = testManager.getResourceManager();
        GameProperties gp = testManager.getGameProperties();
        int characterCount = 0;

        for (CharacterPersonality cp : CharacterPersonality.values()) {
            if (cp.getType().equals(CharacterType.HUMAN)) {
                characterCount++;
            }
        }

        //Deberia Mostrar todos los personajes
        assertEquals(characterCount, rm.getAvailablePlayerImages().size());
        gp.setCharacter(0, CharacterPersonality.EXTREME);
        //Deberia mostrar todos los personajes ya que seleccionamos una máquina
        assertEquals(characterCount, rm.getAvailablePlayerImages().size());
        gp.setCharacter(1, CharacterPersonality.BOB);
        //Deberia mostrar menos 1 personaje ya que seleccionamos un personaje humano
        assertEquals(characterCount - 1, rm.getAvailablePlayerImages().size());
    }

    @Test
    public void deberiaRetornarImagenesCorrectamente() {
        assertNotNull(rm.getMachineImage(CharacterPersonality.EXTREME));
        assertNotNull(rm.getPlayerImage(CharacterPersonality.BOB));
        assertNotNull(rm.getBallImage(BallType.SLOW));
    }
}