package aplicacion.game.enums;

import aplicacion.game.components.surprises.*;

public enum SurpriseProperties {
    FASTBALL("FAST_BALL", "resources/sprites/Surprise Phantom Racket.png", FastBall.class),
    FLASHBALL("FLASH_BALL", "resources/sprites/Surprise Flash.png", FlashBall.class),
    FREEZER("FREEZER", "resources/sprites/Surprise Freezer.png", Freezer.class),
    TURTLE("TURTLE", "resources/sprites/Surprise Turtle.png", Turtle.class),
    ENERGY("ENERGY", "resources/sprites/Surprise Energy.png", Energy.class);

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