package aplicacion.game.entities;

import aplicacion.exception.EntityException;
import aplicacion.game.components.common.Collider;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.enums.CharacterPersonality;
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
            new Player(null, "PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP,
                    CharacterPersonality.HARRY.spriteName(), 0);
            new Player(null, "PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP,
                    CharacterPersonality.HARRY.spriteName(), 0);
            fail("No se lanzó la excepción");
        } catch (EntityException e) {
            assertEquals(EntityException.DUPLICATED_NAME, e.getMessage());
        }
    }

    @Test
    public void deberiaAlmacenarLaEntidad() {
        Player player = new Player(null, "PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP,
                CharacterPersonality.HARRY.spriteName(), 0);
        Player playerCopy = (Player) Entity.find("PLAYER_TOP");
        assertEquals(player, playerCopy);
    }

    @Test
    public void noDeberiaAgregarDosVecesElMismoComponente() {
        try {
            Player p1 = new Player(null, "PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP,
                    CharacterPersonality.HARRY.spriteName(), 0);
            p1.addComponent(new RectangleCollider(p1));
            p1.addComponent(new RectangleCollider(p1));
            fail("No se lanzó la excepción, se agregaron dos colliders al mismo elemento");
        } catch (EntityException e) {
            assertEquals(EntityException.COMPONENT_ALREADY_ADDED, e.getMessage());
        }
    }

    @Test
    public void deberiaAlmacenarElComponenteYRetornarlo() {
        Player p1 = new Player(null, "PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP,
                CharacterPersonality.HARRY.spriteName(), 0);
        RectangleCollider col = new RectangleCollider(p1);
        p1.addComponent(col);
        assertEquals(col, p1.getComponent(RectangleCollider.class));
    }

    @Test
    public void deberiaRetornarErrorSiElComponenteNoHaSidoAgregadoOEsDiferenteAlRequerido() {
        Player p1 = new Player(null, "PLAYER_TOP", 0, 0, 0, 0, FieldSide.TOP,
                CharacterPersonality.HARRY.spriteName(), 0);
        RectangleCollider col = new RectangleCollider(p1);
        try {
            p1.getComponent(RectangleCollider.class);
            fail("No se lanzó la excepción");
        } catch (EntityException e) {
            assertEquals(EntityException.COMPONENT_NOT_FOUND, e.getMessage());
        }
        p1.addComponent(col);
        try {
            p1.getComponent(Collider.class);
            fail("No se lanzó la excepción");
        } catch (EntityException e) {
            assertEquals(EntityException.COMPONENT_NOT_FOUND, e.getMessage());
        }
    }
}