package aplicacion.game.components.player;

import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;
import aplicacion.game.utils.Vector2;

/**
 * Clase encargada del movimiento de la máquina Extreme
 */
public class ExtremeMovement extends LazyMovement {

    /**
     * @param parent    La Entidad que contiene este componente
     * @param fieldSide El lado del campo en el que se encuentra el jugador padre
     */
    public ExtremeMovement(Entity parent, FieldSide fieldSide) {
        super(parent, fieldSide);
    }

    @Override
    public void update() {
        aimForTarget();
        super.update();
    }

    /**
     * Caclula la posición en el jugador con la que tiene que golpear a la bola para llegar al objetivo
     */
    protected void aimForTarget() {
        Vector2 currentBallDir = ballMovement.getDirection();
        boolean rightCorner = !(currentBallDir.x < 0);
        Vector2 target = fieldBounds.getCorner(GameUtils.getOtherSide(fieldSide), rightCorner);
        target.x *= rightCorner ? 0.92 : 1.08;
        float deltaX = target.x - transform.getCenterPosition().x;
        float deltaY = Math.abs(target.y - calculateHitPointY());
        centerDelta = calculateCenterDelta(deltaX, deltaY);
    }

    /**
     * @param deltaX Diferencia en x del jugador con el objetivo
     * @param deltaY Diferencia en y del jugador con el objetivo
     * @return En donde le debe pegar la bola al jugador para darle el ángulo requerido para que llegue al objetivo
     */
    protected double calculateCenterDelta(float deltaX, float deltaY) {
        double requiredAngle = Math.atan(deltaX / deltaY);
        double centerDelta = requiredAngle * (transform.getWidth() / 2f) / Math.toRadians(ballMovement.MAX_DEVIATION_ANGLE);
        return Math.min(Math.max(centerDelta, -transform.getWidth() / 2f), transform.getWidth() / 2f);
    }
}
