package aplicacion.game.enums;

public enum CharacterProperties {
    /*
    FIXME: colocar los paths cuando estén disponibles
     */
    HARRY(CharacterType.HUMAN, "Haryy", "resources/sprites/circle-test.png"),
    BOB(CharacterType.HUMAN, "Bob", "resources/sprites/circle-test.png"),
    EXTREME(CharacterType.MACHINE, "Extreme", "resources/sprites/circle-test.png"),
    SNIPER(CharacterType.MACHINE, "Sniper", "resources/sprites/circle-test.png"),
    GREEDY(CharacterType.MACHINE, "Greedy", "resources/sprites/circle-test.png"),
    LAZY(CharacterType.MACHINE, "Lazy", "resources/sprites/circle-test.png");

    private final CharacterType type;
    private final String name;
    private final String spritePath;

    private CharacterProperties(CharacterType type, String name, String spritePath) {
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
