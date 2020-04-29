package aplicacion.game.enums;

public enum BallSpeed {
    SLOW(40f),
    FAST(60f),
    INCREMENTAL(40f);

    private float initialSpeed;

    private BallSpeed(float initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public float initialSpeed() {
        return initialSpeed;
    }
}
