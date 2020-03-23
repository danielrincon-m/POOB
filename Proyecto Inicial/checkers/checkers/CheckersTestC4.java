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
    public void segunGRProletarianDeberiaMorirAlConvertiseEnRey(){
        Checkers juego = new Checkers(8);
        juego.read("-.-.-.-..-.-Pw-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-");
        juego.swap();
        juego.select(2,5);
        juego.shift(true, true);
        assertEquals("-.-.-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-", juego.write());

    }
    
    @Test
    public void segunGRLibertarianNoDeberiaCapturarPieza(){
        Checkers juego = new Checkers(8);
        juego.read("-.-.-.-..-.-.-.--.-Nb-.-..-Lw-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-");
        juego.swap();
        juego.select(4,3);
        juego.jump(true, true);
        assertEquals("-.-.-.-..-.-Lw-.--.-Nb-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-", juego.write());
    }
    
    @Test
    public void segunGRPowerfulNoSeDejaCapturar(){
        Checkers juego = new Checkers(8);
        juego.read("-.-.-.-..-.-.-.--.-Wb-.-..-Nw-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-");
        juego.swap();
        juego.select(4,3);
        juego.jump(true, true);
        assertEquals("-.-.-.-..-.-Nw-.--.-Wb-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-", juego.write());
    }
    
    @Test
    public void segunGRHurriedRepiteDosVecesElMovimiento(){
        Checkers juego = new Checkers(8);
        juego.read("-.-.-.-..-.-.-.--.-.-.-..-Hw-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-");
        juego.swap();
        juego.select(4,3);
        juego.shift(true, true);
        assertEquals("-.-.-.-..-.-Hw-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-", juego.write());
    }
    
    @Test
    public void segunGRRebelSeMueveEnLaDireccionContraria(){
        Checkers juego = new Checkers(8);
        juego.read("-.-.-.-..-.-.-.--.-.-.-..-Rw-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-");
        juego.swap();
        juego.select(4,3);
        juego.shift(false, false);
        assertEquals("-.-.-.-..-.-.-.--.-Rw-.-..-.-.-.--.-.-.-..-.-.-.--.-.-.-..-.-.-.-", juego.write());
    }
}
