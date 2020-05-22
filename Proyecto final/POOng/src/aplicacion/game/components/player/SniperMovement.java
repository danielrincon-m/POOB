package aplicacion.game.components.player;

import aplicacion.game.components.common.Transform;
import aplicacion.game.components.target.TargetController;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.GameUtils;
import aplicacion.game.utils.Vector2;

public class SniperMovement extends ExtremeMovement {

    private TargetController targetController;

    /**
     * @param parent    La Entidad que contiene este componente
     * @param fieldSide El lado del campo en el que se encuentra el jugador padre
     */
    public SniperMovement(Entity parent, FieldSide fieldSide) {
        super(parent, fieldSide);
    }

    @Override
    public void start() {
        targetController = entityManager.find("TARGET_CONTROLLER").getComponent(TargetController.class);
        super.start();
    }

    @Override
    protected void aimForTarget() {
        Vector2 target = getTarget();
        if (target != null) {
            float deltaX = target.x - transform.getCenterPosition().x;
            float deltaY = Math.abs(target.y - transform.getCenterPosition().y);
            centerDelta = calculateCenterDelta(deltaX, deltaY);
        } else {
            super.aimForTarget();
        }
    }

    private Vector2 getTarget() {
        Entity target = targetController.getTarget(GameUtils.getOtherSide(fieldSide));
        if (target != null) {
            return target.getComponent(Transform.class).getCenterPosition();
        } else {
            return null;
        }
    }
}