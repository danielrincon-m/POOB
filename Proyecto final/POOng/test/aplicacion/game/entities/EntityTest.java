package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.enums.EntityName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EntityTest {

    @BeforeEach
    public void beforeEach() {
        Entity.removeAll();
    }

    @Test
    public void noDeberiaCrearLaMismaEntidadDosVeces() {
        try {
            new Player(EntityName.PLYER_ONE, 0, 0, 0, 0);
            new Player(EntityName.PLYER_ONE, 0, 0, 0, 0);
            fail("No se lanzó la excepción");
        } catch (EntityException e) {
            assertEquals(EntityException.DUPLICATED_NAME, e.getMessage());
        }
    }

    @Test
    public void deberiaAlmacenarLaEntidad() {
        Player player = new Player(EntityName.PLYER_ONE, 0, 0, 0, 0);
        Player playerCopy = (Player) Entity.find(EntityName.PLYER_ONE);
        assertEquals(player, playerCopy);
    }
}