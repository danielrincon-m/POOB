package aplicacion.game.enums;

public enum BallType {
    /*
    FIXME: colocar los paths cuando estén disponibles
     */
    SLOW(120f, "resources/sprites/ball.png"),
    FAST(250f, "resources/sprites/ball2.png"),
    INCREMENTAL(200f, "resources/sprites/ball3.png");

    private float initialSpeed;
    private String spriteName;

    private BallType(float initialSpeed, String spriteName) {
        this.initialSpeed = initialSpeed;
        this.spriteName = spriteName;
    }

    public float initialSpeed() {
        return initialSpeed;
    }

    public String spritePath() {
        return spriteName;
    }
}
