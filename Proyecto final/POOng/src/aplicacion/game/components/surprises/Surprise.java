package aplicacion.game.components.surprises;

import aplicacion.game.components.Component;
import aplicacion.game.components.common.Collider;
import aplicacion.game.components.common.RectangleCollider;
import aplicacion.game.engine.timer.GameTimer;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.SurpriseProperties;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Componente abstracto del que derivan todas las sorpresas
 */
public abstract class Surprise extends Component {

    private float lifeTime;

    protected Entity ball;
    private Collider ballCollider;
    private Collider myCollider;
    private final SurpriseManager surpriseManager;
    private final SurpriseProperties surpriseProperties;

    /**
     * @param parent La Entidad padre de la sorpresa
     * @param surpriseManager El surpriseManager del juego
     * @param surpriseProperties Las propiedades de la sorpresa
     */
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

    /**
     * Acción que realiza la sorpresa cuando es golpeada por la bola
     */
    protected abstract void takeAction();

    /**
     * Destruye la sorpresa
     */
    protected void destroy() {
        surpriseManager.removeSurprise(surpriseProperties);
    }

    /**
     * Verifica si es hora de destruir la sorpresa por tiempo y si es así,
     * la destruye
     */
    private void checkDestroyTime() {
        lifeTime -= GameTimer.deltaTime();
        if (lifeTime <= 0) {
            destroy();
        }
    }

    /**
     * Calcula el tiempo de vida que tendrá la sorpresa
     */
    private void calculateLifeTime() {
        lifeTime = ThreadLocalRandom.current().nextInt(10, 21);
    }
}
