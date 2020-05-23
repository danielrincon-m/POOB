package aplicacion.game.utils;

import aplicacion.game.enums.FieldSide;

/**
 * Clase con utilidades especificas de POOng
 */
public class GameUtils {
    /**
     * Obtiene el otro lado del campo
     *
     * @param side El lado dado
     * @return EL otro lado del campo
     */
    public static FieldSide getOtherSide(FieldSide side) {
        return side == FieldSide.TOP ? FieldSide.BOTTOM : FieldSide.TOP;
    }

    /**
     * @param side El lado del jugador
     * @return El nombre del jugador de ese lado
     */
    public static String getPlayerNameBySide(FieldSide side) {
        if (side.equals(FieldSide.TOP)) {
            return ("PLAYER_TOP");
        } else {
            return ("PLAYER_BOTTOM");
        }
    }
}
