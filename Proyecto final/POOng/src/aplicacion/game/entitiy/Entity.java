package aplicacion.game.entitiy;

import aplicacion.exception.EntityException;
import aplicacion.game.components.Component;
import aplicacion.game.components.common.Transform;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase base de las Entidades, contiene todos los componentes, y define las funcionalidades
 * base de las entidades
 */
public class Entity implements Serializable {

    protected String name;

    protected ArrayList<Component> components = new ArrayList<>();

    private final EntityManager entityManager;
    protected Transform transform;

    /**
     * @param name          El nombre de la Entidad
     * @param entityManager El EntityManager
     */
    public Entity(String name, EntityManager entityManager) {
        this.name = name;
        this.entityManager = entityManager;
    }

    /**
     * Llama al método start de todos los componentes
     */
    public void startAllComponents() {
        for (Component component : components) {
            component.start();
        }
    }

    /**
     * Llama al método update de todos los componentes
     */
    public void updateAllComponents() {
        for (Component component : components) {
            component.update();
        }
    }


    //Entity

    /**
     * @return El nombre de la entidad
     */
    public String getName() {
        return name;
    }


    //Components

    /**
     * Agrega un nuevo componente a la entidad, si esta no contiene un componente de la misma clase
     *
     * @param c El componente que se desea agregar
     * @throws EntityException Cuando el componente ya existe en la entidad
     */
    public void addComponent(Component c) throws EntityException {
        for (Component component : components) {
            if (component.getClass().equals(c.getClass())) {
                throw new EntityException(EntityException.COMPONENT_ALREADY_ADDED);
            }
        }
        components.add(c);
        if (entityManager.isRunning()) {
            c.start();
            entityManager.sortEntities();
        }
    }

    /**
     * Obtiene un componente de la entidad si esta lo contiene
     *
     * @param c   La clase del componente que se desea obtener
     * @param <T> El tipo debe extender Componente
     * @return El componente encontrado
     * @throws EntityException Cuando la Entidad no contiene ningun componente de esa clase
     */
    public <T extends Component> T getComponent(Class<T> c) throws EntityException {
        for (Component component : components) {
            if (component.getClass().equals(c)) {
                return c.cast(component);
            }
        }
        throw new EntityException(EntityException.COMPONENT_NOT_FOUND);
    }

    /**
     * Verifica si la entidad contiene un componente de cierta clase
     *
     * @param c   La clase del componente que se desea verificar
     * @param <T> La clase debe extender Component
     * @return Si el componente se encuentra en la Entidad
     */
    public <T extends Component> boolean hasComponent(Class<T> c) {
        try {
            getComponent(c);
            return true;
        } catch (EntityException e) {
            return false;
        }
    }

    /**
     * @return El EntityManager asociado a esta entidad
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
