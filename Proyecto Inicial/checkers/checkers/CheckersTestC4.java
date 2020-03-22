package checkers;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CheckersTestC4.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CheckersTestC4
{
    @Test
    public void segunGRdeberiaMorirAlConvertiseEnRey(){
        Checkers juego = new Checkers(8);
        juego.read("-.-.-.-..-.-Pw-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-");
        juego.swap();
        juego.select(2,5);
        juego.shift(true, true);
        assertEquals("-.-.-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-", juego.write());

    }
}
