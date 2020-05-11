package aplicacion.game.enums;

public enum BallType {
    /*
    FIXME: colocar los paths cuando estén disponibles
     */
    SLOW(120f, "resources/sprites/ball.png"),
    FAST(250f, "resources/sprites/ball.png"),
    INCREMENTAL(120f, "resources/sprites/ball.png");

    private float initialSpeed;
    private String spritePath;

    private BallType(float initialSpeed, String spritePath) {
        this.initialSpeed = initialSpeed;
        this.spritePath = spritePath;
    }

    public float initialSpeed() {
        return initialSpeed;
    }

    public String spritePath() {
        return spritePath;
    }
}
