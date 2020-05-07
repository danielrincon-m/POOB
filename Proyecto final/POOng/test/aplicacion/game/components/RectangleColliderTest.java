package aplicacion.game.components;

import aplicacion.game.entities.Entity;
import aplicacion.game.entities.Player;
import aplicacion.game.enums.FieldSide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RectangleColliderTest {

    Player p1;
    Player p2;
    RectangleCollider r1;
    RectangleCollider r2;

    @BeforeEach
    public void beforeEach() {
        Entity.removeAll();
    }

    @Test
    public void deberiaDetectarColisionConRectangle() {
        p1 = new Player("PLAYER_TOP", 10, 10, 10, 10, FieldSide.TOP);
        p2 = new Player("PLAYER_BOTTOM",19.9f, 19.9f, 10, 10, FieldSide.BOTTOM);
        r1 = new RectangleCollider(p1);
        r2 = new RectangleCollider(p2);
        assertTrue(r1.collidesWith(r2));
    }

    @Test
    public void deberiaNoDetectarColisionAunqueEsteCerca() {
        p1 = new Player("PLAYER_TOP",10, 10, 10, 10, FieldSide.TOP);
        p2 = new Player("PLAYER_BOTTOM",20f, 10f, 10, 10, FieldSide.BOTTOM);
        r1 = new RectangleCollider(p1);
        r2 = new RectangleCollider(p2);
        assertFalse(r1.collidesWith(r2));
    }
}