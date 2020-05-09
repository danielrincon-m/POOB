package aplicacion.game.enums;

public enum Characters {
    /*
    FIXME: colocar los paths cuando estén disponibles
     */
    BOB(CharacterType.HUMAN, "Bob", ""),
    EXTREME(CharacterType.MACHINE, "Extreme", ""),
    SNIPER(CharacterType.MACHINE, "Sniper", ""),
    GREEDY(CharacterType.MACHINE, "Greedy", ""),
    LAZY(CharacterType.MACHINE, "Lazy", "");

    private final CharacterType type;
    private final String name;
    private final String spritePath;

    private Characters(CharacterType type, String name, String spritePath) {
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
