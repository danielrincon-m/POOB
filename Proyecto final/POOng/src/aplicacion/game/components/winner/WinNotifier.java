package aplicacion.game.components.winner;

import aplicacion.game.components.Component;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.enums.FieldSide;

public class WinNotifier extends Component {

    private boolean someoneWinned = false;
    private String winReason = "";

    private FieldSide winner = null;

    public WinNotifier(Entity parent) {
        super(parent);
    }

    @Override
    public void start() {
    }

    @Override
    public void update() {
    }

    public String getWinReason() {
        return winReason;
    }

    public FieldSide getWinner() {
        return winner;
    }

    public boolean someoneWined() {
        return someoneWinned;
    }

    public void winGame(FieldSide winner, String reason) {
        someoneWinned = true;
        this.winner = winner;
        winReason = reason;
    }
}
