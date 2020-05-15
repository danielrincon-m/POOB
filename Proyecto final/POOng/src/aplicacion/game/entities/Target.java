package aplicacion.game.entities;

import aplicacion.ApplicationManager;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.components.target.TargetBehaviour;
import aplicacion.game.components.target.TargetController;
import aplicacion.game.enums.FieldSide;

public class Target extends Entity {

    private final int maxScore;

    private final FieldSide side;
    private final TargetController targetController;

    public Target(ApplicationManager applicationManager, String name, FieldSide side, int maxScore, TargetController targetController) {
        super(applicationManager, name);
        this.maxScore = maxScore;
        this.side = side;
        this.targetController = targetController;
    }

    @Override
    protected void defineComponents() {
        //addComponent(new Sprite(this, "resources/sprites/not_implemented.png", 1));
        addComponent(new RectangleCollider(this));
        addComponent(new TargetBehaviour(this, side, maxScore, targetController));
    }
}
