package aplicacion;

import aplicacion.exception.ApplicationException;
import aplicacion.game.enums.BallType;
import aplicacion.game.enums.CharacterPersonality;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceManagerTest {

    ResourceManager rm = new ResourceManager();

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
            rm.getMachineImage(CharacterPersonality.HARRY);
            fail("No se lanzó la excepción");
        } catch (ApplicationException e) {
            assertEquals(ApplicationException.NOT_A_MACHINE, e.getMessage());
        }
    }

    @Test
    public void deberiaRetornarImagenesCorrectamente() {
        assertNotNull(rm.getMachineImage(CharacterPersonality.EXTREME));
        assertNotNull(rm.getPlayerImage(CharacterPersonality.HARRY));
        assertNotNull(rm.getBallImage(BallType.SLOW));
    }

    @Test
    public void deberiaRetornarNuloSiLaImagenNoExiste() {
        assertNull(rm.getSprite("ImaginaryPathThatDoNotExists"));
    }
}
