package aplicacion.game.components;

import aplicacion.exception.EntityException;
import aplicacion.game.components.common.Collider;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Transform;
import aplicacion.game.components.field.FieldBounds;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.entitiy.EntityManager;
import aplicacion.game.utils.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ComponentTest {

    EntityManager entityManager;
    Entity e1;
    Entity e2;

    @BeforeEach
    public void beforeEach() {
        entityManager = new EntityManager();
        e1 = new Entity("e1", entityManager);
        e1.addComponent(new Transform(e1));
        e2 = new Entity("e2", entityManager);
        e2.addComponent(new Transform(e2));
    }

    @Test
    public void collideDeberiaDetectarColisionConRectangle() {
        Transform t1 = e1.getComponent(Transform.class);
        t1.setPosition(new Vector2(10, 10));
        t1.setSize(new Vector2(10, 10));
        Transform t2 = e2.getComponent(Transform.class);
        t2.setPosition(new Vector2(19, 19));
        t2.setSize(new Vector2(10, 10));
        e1.addComponent(new RectangleCollider(e1));
        e2.addComponent(new RectangleCollider(e2));
        e1.startAllComponents();
        e2.startAllComponents();
        assertTrue(e1.getComponent(RectangleCollider.class).collidesWith(e2.getComponent(RectangleCollider.class)));
    }

    @Test
    public void colliderDeberiaNoDetectarColisionAunqueEsteCerca() {
        Transform t1 = e1.getComponent(Transform.class);
        t1.setPosition(new Vector2(10, 10));
        t1.setSize(new Vector2(10, 10));
        Transform t2 = e2.getComponent(Transform.class);
        t2.setPosition(new Vector2(20, 20));
        t2.setSize(new Vector2(10, 10));
        e1.addComponent(new RectangleCollider(e1));
        e2.addComponent(new RectangleCollider(e2));
        e1.startAllComponents();
        e2.startAllComponents();
        assertFalse(e1.getComponent(RectangleCollider.class).collidesWith(e2.getComponent(RectangleCollider.class)));
    }

    @Test
    public void TransformDeberiaCambiarSuPosicionCorrectamente() {
        e1.getComponent(Transform.class).setPosition(new Vector2(20, 20));
        assertEquals(e1.getComponent(Transform.class).getPosition(), new Vector2(20, 20));
    }

    @Test
    public void transformDeberiaCambiarSuTamanoCorrectamente() {
        e1.getComponent(Transform.class).setSize(new Vector2(20, 20));
        assertEquals(e1.getComponent(Transform.class).getSize(), new Vector2(20, 20));
    }

    @Test
    public void transformDeberiaTrasladarseCorrectamente() {
        e1.getComponent(Transform.class).translate(new Vector2(20, 20));
        assertEquals(e1.getComponent(Transform.class).getPosition(), new Vector2(20, 20));
    }

    @Test
    public void elCampoDeberiaEncontrarCorrectamenteSiHayAlguienDentro() {
        e1.getComponent(Transform.class).setSize(new Vector2(10, 10));
        FieldBounds fb = new FieldBounds(e1, 1);
        e1.addComponent(fb);
        assertFalse(fb.insideField(new Vector2(20, 0)));
        assertTrue(fb.insideField(new Vector2(9.8f, 1)));
    }

    @Test
    public void noDeberiaAgregarDosVecesElMismoComponente() {
        try {
            e1.addComponent(new RectangleCollider(e1));
            e1.addComponent(new RectangleCollider(e1));
            fail("No se lanzó la excepción, se agregaron dos colliders al mismo elemento");
        } catch (EntityException e) {
            assertEquals(EntityException.COMPONENT_ALREADY_ADDED, e.getMessage());
        }
    }

    @Test
    public void deberiaAlmacenarElComponenteYRetornarlo() {
        RectangleCollider col = new RectangleCollider(e1);
        e1.addComponent(col);
        assertEquals(col, e1.getComponent(RectangleCollider.class));
    }

    @Test
    public void deberiaRetornarErrorSiElComponenteNoHaSidoAgregadoOEsDiferenteAlRequerido() {
        RectangleCollider col = new RectangleCollider(e1);
        try {
            e1.getComponent(RectangleCollider.class);
            fail("No se lanzó la excepción");
        } catch (EntityException e) {
            assertEquals(EntityException.COMPONENT_NOT_FOUND, e.getMessage());
        }
        e1.addComponent(col);
        try {
            e1.getComponent(Collider.class);
            fail("No se lanzó la excepción");
        } catch (EntityException e) {
            assertEquals(EntityException.COMPONENT_NOT_FOUND, e.getMessage());
        }
    }
}
