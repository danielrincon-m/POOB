package aplicacion.game.entitiy;

import aplicacion.exception.EntityException;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.common.Sprite;
import aplicacion.game.components.common.Transform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    EntityManager entityManager;

    @BeforeEach
    public void beforeEach() {
        entityManager = new EntityManager();
    }

    @Test
    public void noDeberiaCrearLaMismaEntidadDosVeces() {
        try {
            entityManager.registerEntity(new Entity("TEST", entityManager));
            entityManager.registerEntity(new Entity("TEST", entityManager));
            fail("No se lanzó la excepción");
        } catch (EntityException e) {
            assertEquals(EntityException.DUPLICATED_NAME, e.getMessage());
        }
    }

    @Test
    public void deberiaAlmacenarLaEntidad() {
        Entity entity = new Entity("TEST", entityManager);
        entityManager.registerEntity(entity);
        Entity entityCopy = entityManager.find("TEST");
        assertEquals(entity, entityCopy);
    }

    @Test
    public void deberiaVerificarSiExisteElComponente() {
        Entity entity = new Entity("TEST", entityManager);
        entity.addComponent(new Transform(entity));
        assertTrue(entity.hasComponent(Transform.class));
        assertFalse(entity.hasComponent(RectangleCollider.class));
    }

    @Test
    public void deberiaRetornarCorrectamenteElNombre() {
        Entity entity = new Entity("TEST", entityManager);
        entityManager.registerEntity(entity);
        assertEquals("TEST", entity.getName());
    }

    @Test
    public void deberiaOrdenarLasEntidadesPorZIndex() {
        Entity e1 = new Entity("E1", entityManager);
        Entity e2 = new Entity("E2", entityManager);
        Entity e3 = new Entity("E3", entityManager);
        e1.addComponent(new Transform(e1));
        e2.addComponent(new Transform(e2));
        e3.addComponent(new Transform(e3));
        e1.addComponent(new Sprite(e1, "", 3));
        e2.addComponent(new Sprite(e2, "", -1));
        e3.addComponent(new Sprite(e3, "", 1));
        entityManager.registerEntity(e1);
        entityManager.registerEntity(e2);
        entityManager.registerEntity(e3);
        entityManager.sortEntities();
        LinkedHashMap<String, Entity> entities = entityManager.getEntities();
        int[] orderedzIndexes = {-1, 1, 3};
        int index = 0;
        for (String key : entities.keySet()) {
            assertEquals(orderedzIndexes[index], entities.get(key).getComponent(Sprite.class).getzIndex());
            index++;
        }
    }
}