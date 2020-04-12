package aplicacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MarbelGameBoardTest {

    MarbelGameBoard board;

    @BeforeEach
    void beforeEach() {
        board = new MarbelGameBoard(4, 1, 3);
    }

    @Test
    void setnCeldas() {
        assertThrows(UnsupportedOperationException.class, () -> {
            board.setnCeldas(-1);
        });
    }

    @Test
    void setnBarreras() {
        assertThrows(UnsupportedOperationException.class, () -> {
            board.setnBarreras(100);
        });
    }

    @Test
    void setnAgujeros() {
        assertThrows(UnsupportedOperationException.class, () -> {
            board.setnAgujeros(100);
        });
    }
}