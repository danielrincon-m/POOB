package aplicacion.game.utils;

public class GameUtils {
    public static int getOtherPlayerSide(int player) {
        return  player == 1 ? -1 : 1;
    }
}
