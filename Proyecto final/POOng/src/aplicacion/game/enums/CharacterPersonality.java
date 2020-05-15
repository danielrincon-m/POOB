package aplicacion.game.enums;

public enum CharacterPersonality {
    HERMIONE(CharacterType.HUMAN, "Hermione", "resources/sprites/Hermione_front.png"),
    HARRY(CharacterType.HUMAN, "Harry", "resources/sprites/harry_front.png"),
    IRON(CharacterType.HUMAN, "Iron Man", "resources/sprites/iron_man_front.png"),
    SUPERMAN(CharacterType.HUMAN, "Superman", "resources/sprites/superman_front.png"),
    FREDDY_KRUEGER(CharacterType.HUMAN, "Freddy Krueger", "resources/sprites/freddy_krueger_front.png"),
    JASON_VOORHEES(CharacterType.HUMAN, "Jason Voorhees", "resources/sprites/Jason_voorhees_front.png"),

    EXTREME(CharacterType.MACHINE, "Extreme", "resources/sprites/maquina1_front.png"),
    SNIPER(CharacterType.MACHINE, "Sniper", "resources/sprites/maquina2_front.png"),
    GREEDY(CharacterType.MACHINE, "Greedy", "resources/sprites/maquina3_front.png"),
    LAZY(CharacterType.MACHINE, "Lazy", "resources/sprites/maquina4_front.png");

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
