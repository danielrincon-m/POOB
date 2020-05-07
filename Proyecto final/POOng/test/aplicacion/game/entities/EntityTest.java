package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.components.Collider;
import aplicacion.game.components.RectangleCollider;
import aplicacion.game.enums.FieldSide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @BeforeEach
    public void beforeEach() {
        Entity.removeAll();
    }

    @Test
    public void noDeberiaCrearLaMismaEntidadDosVeces() {
        try {
            new Player("PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP);
            new Player("PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP);
            fail("No se lanzó la excepción");
        } catch (EntityException e) {
            assertEquals(EntityException.DUPLICATED_NAME, e.getMessage());
        }
    }

    @Test
    public void deberiaAlmacenarLaEntidad() {
        Player player = new Player("PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP);
        Player playerCopy = (Player) Entity.find("PLAYER_TOP");
        assertEquals(player, playerCopy);
    }

    @Test
    public void noDeberiaAgregarDosVecesElMismoComponente() {
        try {
            Player p1 = new Player("PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP);
            p1.addComponent(new RectangleCollider(p1));
            p1.addComponent(new RectangleCollider(p1));
            fail("No se lanzó la excepción, se agregaron dos colliders al mismo elemento");
        } catch (EntityException e) {
            assertEquals(EntityException.COMPONENT_ALREADY_ADDED, e.getMessage());
        }
    }

    @Test
    public void deberiaAlmacenarElComponenteYRetornarlo() {
        Player p1 = new Player("PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP);
        RectangleCollider col = new RectangleCollider(p1);
        p1.addComponent(col);
        assertEquals(col, p1.getComponent(RectangleCollider.class));
    }

    @Test
    public void deberiaRetornarNullSiElComponenteNoHaSidoAgregadoOEsDiferenteAlRequerido() {
        Player p1 = new Player("PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP);
        RectangleCollider col = new RectangleCollider(p1);
        assertNull(p1.getComponent(RectangleCollider.class));
        p1.addComponent(col);
        assertNull(p1.getComponent(Collider.class));
    }
}