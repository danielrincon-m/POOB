package aplicacion.game.enums;

public enum BallSpeed {
    SLOW(120f),
    FAST(160f),
    INCREMENTAL(120f);

    private float initialSpeed;

    private BallSpeed(float initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public float initialSpeed() {
        return initialSpeed;
    }
}
