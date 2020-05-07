package aplicacion.game.utils;

import aplicacion.game.enums.FieldSide;

public class GameUtils {
    public static FieldSide getOtherSide(FieldSide side) {
        return  side == FieldSide.TOP ? FieldSide.BOTTOM : FieldSide.TOP;
    }
}
