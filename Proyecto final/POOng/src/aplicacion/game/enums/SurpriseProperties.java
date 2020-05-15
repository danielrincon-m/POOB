package aplicacion.game.enums;

import aplicacion.game.components.surprises.FastBall;
import aplicacion.game.components.surprises.Surprise;

public enum SurpriseProperties {
    FASTBALL("FAST_BALL", "resources/sprites/Phantom Racket.png", FastBall.class);

    private final String name;
    private final String spritePath;
    private final Class<? extends Surprise> cls;

    private SurpriseProperties(String name, String spritePath, Class<? extends Surprise> cls) {
        this.name = name;
        this.spritePath = spritePath;
        this.cls = cls;
    }

    public String getName() {
        return name;
    }

    public String spritePath() {
        return spritePath;
    }

    public Class<? extends Surprise> className() {
        return cls;
    }
}
