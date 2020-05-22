package aplicacion.game.enums;

import aplicacion.game.components.player.ExtremeMovement;
import aplicacion.game.components.player.LazyMovement;
import aplicacion.game.components.player.PlayerMovement;
import aplicacion.game.components.player.SniperMovement;

/**
 * Representación de las posibles personalidades de los caracteres, junto con su tipo, nombre y la ruta de
 * su imagen representativa
 */
public enum CharacterPersonality {
    HERMIONE(CharacterType.HUMAN, "Hermione", "resources/sprites/Hermione_front.png", PlayerMovement.class),
    HARRY(CharacterType.HUMAN, "Harry", "resources/sprites/harry_front.png", PlayerMovement.class),
    IRON(CharacterType.HUMAN, "Iron Man", "resources/sprites/iron_man_front.png", PlayerMovement.class),
    SUPERMAN(CharacterType.HUMAN, "Superman", "resources/sprites/superman_front.png", PlayerMovement.class),
    FREDDY_KRUEGER(CharacterType.HUMAN, "Freddy Krueger", "resources/sprites/freddy_krueger_front.png", PlayerMovement.class),
    JASON_VOORHEES(CharacterType.HUMAN, "Jason Voorhees", "resources/sprites/Jason_voorhees_front.png", PlayerMovement.class),

    EXTREME(CharacterType.MACHINE, "Extreme", "resources/sprites/maquina1_front.png", ExtremeMovement.class),
    SNIPER(CharacterType.MACHINE, "Sniper", "resources/sprites/maquina2_front.png", SniperMovement.class),
    GREEDY(CharacterType.MACHINE, "Greedy", "resources/sprites/maquina3_front.png", PlayerMovement.class),
    LAZY(CharacterType.MACHINE, "Lazy", "resources/sprites/maquina4_front.png", LazyMovement.class);

    private final CharacterType type;
    private final String name;
    private final String spritePath;
    private final Class<? extends PlayerMovement> movementClass;

    private CharacterPersonality(CharacterType type, String name, String spritePath,
                                 Class<? extends PlayerMovement> movementClass) {
        this.type = type;
        this.name = name;
        this.spritePath = spritePath;
        this.movementClass = movementClass;
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

    public Class<? extends PlayerMovement> getMovementClass() {
        return movementClass;
    }
}
