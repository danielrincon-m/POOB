package aplicacion.game.enums;

public enum BallSpeed {
    SLOW(1f),
    FAST(2f),
    INCREMENTAL(1f);

    private float initialSpeed;

    private BallSpeed(float initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public float initialSpeed() {
        return initialSpeed;
    }
}
