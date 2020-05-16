package aplicacion.game.utils;

import aplicacion.game.enums.FieldSide;

public class GameUtils {
    public static FieldSide getOtherSide(FieldSide side) {
        return  side == FieldSide.TOP ? FieldSide.BOTTOM : FieldSide.TOP;
    }

    public static String getPlayerNameBySide(FieldSide side) {
        if (side.equals(FieldSide.TOP)) {
            return ("PLAYER_TOP");
        } else {
            return ("PLAYER_BOTTOM");
        }
    }
}
