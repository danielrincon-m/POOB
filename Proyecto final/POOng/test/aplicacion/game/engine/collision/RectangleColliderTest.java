package aplicacion.game.engine.collision;

import aplicacion.game.entities.Entity;
import aplicacion.game.entities.Player;
import aplicacion.game.enums.EntityName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        p1 = new Player(EntityName.PLYER_ONE, 10, 10, 10, 10);
        p2 = new Player(EntityName.PLAYER_TWO,19.9f, 19.9f, 10, 10);
        r1 = new RectangleCollider(p1);
        r2 = new RectangleCollider(p2);
        assertTrue(r1.collidesWith(r2));
    }

    @Test
    public void deberiaNoDetectarColisionAunqueEsteCerca() {
        p1 = new Player(EntityName.PLYER_ONE,10, 10, 10, 10);
        p2 = new Player(EntityName.PLAYER_TWO,20f, 10f, 10, 10);
        r1 = new RectangleCollider(p1);
        r2 = new RectangleCollider(p2);
        assertFalse(r1.collidesWith(r2));
    }
}