package aplicacion.game.engine.collision;

import aplicacion.game.GameManager;
import aplicacion.game.gameObject.GameObject;
import aplicacion.game.gameObject.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleColliderTest {

    Player p1;
    Player p2;
    RectangleCollider r1;
    RectangleCollider r2;

    @Test
    public void deberiaDetectarColisionConRectangle() {
        p1 = new Player(10, 10, 10, 10);
        p2 = new Player(19.9f, 19.9f, 10, 10);
        r1 = new RectangleCollider(p1);
        r2 = new RectangleCollider(p2);
        assertTrue(r1.collidesWith(r2));
    }

    @Test
    public void deberiaNoDetectarColisionAunqueEsteCerca() {
        p1 = new Player(10, 10, 10, 10);
        p2 = new Player(20f, 10f, 10, 10);
        r1 = new RectangleCollider(p1);
        r2 = new RectangleCollider(p2);
        assertFalse(r1.collidesWith(r2));
    }
}