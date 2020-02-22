

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CheckersTestC2.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CheckersTestC2
{
    //Read - write
    @Test
    public void segunGRdeberiaCovertirCorrectamenteUnTableroAString(){
        Checkers juego = new Checkers(8);
        juego.read("-b-.-.-..-.-.-.--.-.-.-.B-.-w-.--.-.-W-.w-.-.-.--.-w-w-..-.-.-.-");
        assertEquals("-b-.-.-..-.-.-.--.-.-.-.B-.-w-.--.-.-W-.w-.-.-.--.-w-w-..-.-.-.-", juego.write());
    }
    
    //Save - recover
    @Test
    public void segunGRdeberiaGuardarYRecuperarElMismoTablero(){
        Checkers juego = new Checkers(8);
        juego.read("-b-.-.-..-.-.-.--.-.-.-.B-.-w-.--.-.-W-.w-.-.-.--.-w-w-..-.-.-.-");
        juego.save("juego1");
        assertEquals("-b-.-.-..-.-.-.--.-.-.-.B-.-w-.--.-.-W-.w-.-.-.--.-w-w-..-.-.-.-", juego.recover("juego1"));
    }
    
    //recover
    @Test
    public void segunGRdeberiaRetornarNullSiLaLlaveNoExiste(){
        Checkers juego = new Checkers(8);
        assertNull(juego.recover("llaveInexistente"));
    }
    
    //move
    @Test
    public void segunGRdeberiaRealizarMovimientosCorrectamente(){
        Checkers juego = new Checkers(8);
        juego.read("-b-.-.-..-.-.-.--.-.-.-.B-.-w-.--.-.-W-.w-.-.-.--.-w-w-..-.-.-.-");
        juego.swap();
        juego.move("21-17");
        juego.move("13x22x31x24");
        juego.move("19x28");
        assertEquals("-b-.-.-..-.-.-.--.-.-.-..-.-w-.--.-.-.-..-.-.-.--.-.-.-W.-.-.-.-", juego.write());
    }
    
    @Test
    public void segunGRnoDeberiaDejarMoverEnLaZonaDeConfiguracion(){
        Checkers juego = new Checkers(8);
        juego.read("-b-.-.-..-.-.-.--.-.-.-.B-.-w-.--.-.-W-.w-.-.-.--.-w-w-..-.-.-.-");
        juego.move("21-17");
        assertNotEquals("-b-.-.-..-.-.-.--.-.-.-.B-.-w-.--w-.-W-..-.-.-.--.-w-w-..-.-.-.-", juego.write());
    }
}
