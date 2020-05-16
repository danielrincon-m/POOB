package aplicacion.game.components;

import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.entitiy.Entity;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RectangleColliderTest {

    Entity b1;
    Entity b2;
    RectangleCollider r1;
    RectangleCollider r2;

    @BeforeEach
    public void beforeEach() {
        Entity.removeAll();
    }

/*    @Test
    public void deberiaDetectarColisionConRectangle() {
        b1 = new Entity(null, "BALL_1", 10, 10, 10, 10, BallType.SLOW);
        b2 = new Entity(null, "BALL_2",19f, 19f, 10, 10, BallType.SLOW);
        r1 = new RectangleCollider(b1);
        r2 = new RectangleCollider(b2);
        r1.start();
        r2.start();
        assertTrue(r1.collidesWith(r2));
    }

    @Test
    public void deberiaNoDetectarColisionAunqueEsteCerca() {
        b1 = new Entity(null, "PLAYER_TOP",10, 10, 10, 10, BallType.SLOW);
        b2 = new Entity(null, "PLAYER_BOTTOM",20f, 10f, 10, 10, BallType.SLOW);
        r1 = new RectangleCollider(b1);
        r2 = new RectangleCollider(b2);
        r1.start();
        r2.start();
        assertFalse(r1.collidesWith(r2));
    }*/
}