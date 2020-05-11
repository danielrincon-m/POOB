package aplicacion.game.enums;

public enum CharacterPersonality {
    /*
    FIXME: colocar los paths cuando estén disponibles
     */

    HARRY(CharacterType.HUMAN, "Harry", "resources/sprites/circle-test.png"),
    BOB(CharacterType.HUMAN, "Bob", "resources/sprites/harry_front.png"),
    EXTREME(CharacterType.MACHINE, "Extreme", "resources/sprites/superman_front.png"),

    SNIPER(CharacterType.MACHINE, "Sniper", "resources/sprites/circle-test.png"),
    GREEDY(CharacterType.MACHINE, "Greedy", "resources/sprites/circle-test.png"),
    LAZY(CharacterType.MACHINE, "Lazy", "resources/sprites/circle-test.png");

    private final CharacterType type;
    private final String name;
    private final String spritePath;

    private CharacterPersonality(CharacterType type, String name, String spritePath) {
        this.type = type;
        this.name = name;
        this.spritePath = spritePath;
    }

    public CharacterType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String spritePath() {
        return spritePath;
    }
}