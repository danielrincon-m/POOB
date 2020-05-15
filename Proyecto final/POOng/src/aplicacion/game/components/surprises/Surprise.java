package aplicacion.game.components.surprises;

import aplicacion.game.components.Component;
import aplicacion.game.engine.Timer.GameTimer;
import aplicacion.game.entities.Entity;
import aplicacion.game.enums.SurpriseProperties;

import java.util.concurrent.ThreadLocalRandom;

public class Surprise extends Component {

    private float lifeTime;

    private final SurpriseManager surpriseManager;
    private final SurpriseProperties surpriseProperties;

    public Surprise(Entity parent, SurpriseManager surpriseManager, SurpriseProperties surpriseProperties) {
        super(parent);
        this.surpriseManager = surpriseManager;
        this.surpriseProperties = surpriseProperties;
    }

    @Override
    public void start() {
        calculateLifeTime();
    }

    @Override
    public void update() {
        checkTimeDestroy();
    }

    protected void destroy() {
        surpriseManager.removeSurprise(surpriseProperties);
    }

    private void checkTimeDestroy() {
        lifeTime -= GameTimer.deltaTime();
        if (lifeTime <= 0) {
            destroy();
        }
    }

    private void calculateLifeTime() {
        lifeTime = ThreadLocalRandom.current().nextInt(10, 21);
    }
}
