package aplicacion.game.enums;

public enum CharacterPersonality {
    /*
    FIXME: colocar los paths cuando estén disponibles
     */
    BORRAR(CharacterType.HUMAN, "Borrar", "resources/sprites/harry_front.png"),
    HARRY(CharacterType.HUMAN, "Harry", "resources/sprites/harry_front.png"),
    IRON(CharacterType.HUMAN, "Iron Man", "resources/sprites/iron_man_front.png"),
    SUPERMAN(CharacterType.HUMAN, "Superman", "resources/sprites/superman_front.png"),
    EXTREME(CharacterType.MACHINE, "Extreme", "resources/sprites/superman_front.png"),
    SNIPER(CharacterType.MACHINE, "Sniper", "resources/sprites/ball.png"),
    GREEDY(CharacterType.MACHINE, "Greedy", "resources/sprites/ball.png"),
    LAZY(CharacterType.MACHINE, "Lazy", "resources/sprites/ball.png");

    private final CharacterType type;
    private final String name;
    private final String spriteName;

    private CharacterPersonality(CharacterType type, String name, String spriteName) {
        this.type = type;
        this.name = name;
        this.spriteName = spriteName;
    }

    public CharacterType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String spriteName() {
        return spriteName;
    }
}
