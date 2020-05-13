package aplicacion.game.entities;

import aplicacion.game.components.target.TargetController;

public class TargetManager extends Entity {

    private final int maxScore;

    public TargetManager(String name, int maxScore) {
        super(name);
        this.maxScore = maxScore;
    }

    @Override
    protected void defineComponents() {
        addComponent(new TargetController(this, maxScore));
    }
}

