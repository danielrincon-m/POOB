package aplicacion.game.components.surprises;

import aplicacion.game.components.Component;
import aplicacion.game.components.common.Collider;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.SurpriseProperties;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Surprise extends Component {

    private float lifeTime;

    protected Entity ball;
    private Collider ballCollider;
    private Collider myCollider;
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
        ball = entityManager.find("BALL");
        ballCollider = ball.getComponent(RectangleCollider.class);
        myCollider = parent.getComponent(RectangleCollider.class);
    }

    @Override
    public void update() {
        checkDestroyTime();
        if (myCollider.collidesWith(ballCollider)) {
            takeAction();
            destroy();
        }
    }

    protected abstract void takeAction();

    protected void destroy() {
        surpriseManager.removeSurprise(surpriseProperties);
    }

    private void checkDestroyTime() {
        lifeTime -= GameTimer.deltaTime();
        if (lifeTime <= 0) {
            destroy();
        }
    }

    private void calculateLifeTime() {
        lifeTime = ThreadLocalRandom.current().nextInt(10, 21);
    }
}
