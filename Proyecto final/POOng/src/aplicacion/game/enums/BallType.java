package aplicacion.game.enums;

/**
 * Representación de los tipos de bola, junto con su velocidad inicial, y el path de su imagen representativa
 */
public enum BallType {
    SLOW(230f, "resources/sprites/ball.png"),
    FAST(300f, "resources/sprites/ball2.png"),
    INCREMENTAL(230f, "resources/sprites/ball3.png");

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
