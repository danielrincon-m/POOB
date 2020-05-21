package aplicacion.game.components.field;

import aplicacion.game.components.Component;
import aplicacion.game.components.common.Transform;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.utils.Vector2;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Componente del campo que le da su ubicación espacial y ayuda
 * a las demás entidades a conocer si están dentro o fuera del campo
 */
public class FieldBounds extends Component {

    private final float screenVerticalPercentage;
    private float horizontalHeadroom;
    private float verticalHeadroom;

    /**
     * @param parent La Entidad que contiene este componente
     * @param screenVerticalPercentage El porcentaje vertical de la pantalla que ocupa el campo
     */
    public FieldBounds (Entity parent, float screenVerticalPercentage) {
        super(parent);
        this.screenVerticalPercentage = screenVerticalPercentage;
    }

    @Override
    public void start() {
        calculateHeadrooms();
    }

    @Override
    public void update() {

    }

    /**
     * @param otherPosition Posición en el espacio de alguna Entidad
     * @return Si la entidad se encuentra dentro del campo
     */
    public boolean insideField(Vector2 otherPosition) {
        return insideX(otherPosition) && insideY(otherPosition);
    }

    /**
     * @param otherPosition Posición en el espacio de otra Entidad
     * @return Si la entidad se encuentra dentro del campo en el eje x
     */
    public boolean insideX(Vector2 otherPosition) {
        return otherPosition.x >= transform.getPosition().x - horizontalHeadroom &&
                otherPosition.x <= transform.getPosition().x + transform.getSize().x + horizontalHeadroom;
    }

    /**
     * @param otherPosition Posición en el espacio de otra Entidad
     * @return Si la entidad se encuentra dentro del campo en el eje y
     */
    public boolean insideY(Vector2 otherPosition) {
        return otherPosition.y >= transform.getPosition().y - verticalHeadroom &&
                otherPosition.y <= transform.getPosition().y + transform.getSize().y + verticalHeadroom;
    }

    /**
     * @return Una posición aleatoria dentro del campo, excluyendo los bordes
     */
    public Vector2 getRandomPositionCloseToCenter() {
        float widthReduction = transform.getWidth() / 8f;
        float heightReduction = transform.getHeight() / 6f;
        Vector2 upperLimits = new Vector2(getRightBound() - widthReduction, getLowerBound() - heightReduction);
        Vector2 lowerLimits = new Vector2(getLeftBound() + widthReduction, getUpperBound() + heightReduction);

        float xPosition = ThreadLocalRandom.current().nextFloat() * (upperLimits.x - lowerLimits.x) + lowerLimits.x;
        float yPosition = ThreadLocalRandom.current().nextFloat() * (upperLimits.y - lowerLimits.y) + lowerLimits.y;
        return new Vector2(xPosition, yPosition);
    }

    /**
     * @return La posición del borde superior del campo
     */
    public float getUpperBound() {
        return transform.getPosition().y;
    }

    /**
     * @return La posición del borde inferior del campo
     */
    public float getLowerBound() {
        return transform.getPosition().y + transform.getHeight();
    }

    /**
     * @return La posición del borde izquierdo del campo
     */
    public float getLeftBound() {
        return transform.getPosition().x;
    }

    /**
     * @return La posición del borde derecho del campo
     */
    public float getRightBound() {
        return transform.getPosition().x + transform.getSize().x;
    }

    /**
     * Calcula los espacios de libertad fuera del campo que tiene la pelota para navegar
     */
    private void calculateHeadrooms() {
        horizontalHeadroom = entityManager.find("PLAYER_TOP").getComponent(Transform.class).getWidth() / 2f * 1.2f;
        verticalHeadroom = transform.getHeight() * (1 - screenVerticalPercentage);
    }
}
