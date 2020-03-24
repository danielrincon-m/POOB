package checkers;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CheckersTestC3.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CheckersTestC3
{
    @Test
    public void deberiaRealizarLaSolucionDelPrimerCasoDeLaArena(){
        CheckersContest cc = new CheckersContest();
        String[] moves = {"21-17","13x22x31x24","19x28"};
        String[] solution = cc.solve("NW", moves);
        assertEquals("-.-.-.-..-.-.-.--.-.-.-.NB-.-.-.--.-.-NW-.NW-.-.-.--.-NW-NW-..-.-.-.-", solution[0]);
        assertEquals("-.-.-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-NW.-.-.-.-", solution[1]);
    }
    
    @Test
    public void deberiaRealizarLaSolucionDelSegundoCasoDeLaArena(){
        CheckersContest cc = new CheckersContest();
        String[] moves = {"2-7","9x2","32-27","2x11x18","5-9"};
        String[] solution = cc.solve("NB", moves);
        assertEquals("-.-NB-.-.NW-NB-.-.--NW-.-.-..-.-NB-.--.-.-.-..-.-.-.--.-.-.-..-.-.-NW-", solution[0]);
        assertEquals("-.-.-.-..-.-.-.--NW-.-.-..-.-.-.--.-NW-.-..-.-.-.--.-.-NW-..-.-.-.-", solution[1]);
    }
}
