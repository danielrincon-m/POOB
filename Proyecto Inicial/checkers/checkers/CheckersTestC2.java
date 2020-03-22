package checkers;

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
        juego.read("-Nb-.-.-..-.-.-.--.-.-.-.NB-.-Nw-.--.-.-NW-.Nw-.-.-.--.-Nw-Nw-..-.-.-.-");
        assertEquals("-Nb-.-.-..-.-.-.--.-.-.-.NB-.-Nw-.--.-.-NW-.Nw-.-.-.--.-Nw-Nw-..-.-.-.-", juego.write());
    }
    
    //Save - recover
    @Test
    public void segunGRdeberiaGuardarYRecuperarElMismoTablero(){
        Checkers juego = new Checkers(8);
        juego.read("-Nb-.-.-..-.-.-.--.-.-.-.NB-.-Nw-.--.-.-NW-.Nw-.-.-.--.-Nw-Nw-..-.-.-.-");
        juego.save("juego1");
        assertEquals("-Nb-.-.-..-.-.-.--.-.-.-.NB-.-Nw-.--.-.-NW-.Nw-.-.-.--.-Nw-Nw-..-.-.-.-", juego.recover("juego1"));
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
        juego.read("-Nb-.-.-..-.-.-.--.-.-.-.NB-.-Nw-.--.-.-NW-.Nw-.-.-.--.-Nw-Nw-..-.-.-.-");
        juego.swap();
        juego.move("21-17");
        juego.move("13x22x31x24");
        juego.move("19x28");
        assertEquals("-Nb-.-.-..-.-.-.--.-.-.-..-.-Nw-.--.-.-.-..-.-.-.--.-.-.-NW.-.-.-.-", juego.write());
    }
    
    @Test
    public void segunGRnoDeberiaDejarMoverEnLaZonaDeConfiguracion(){
        Checkers juego = new Checkers(8);
        juego.read("-Nb-.-.-..-.-.-.--.-.-.-.NB-.-Nw-.--.-.-NW-.Nw-.-.-.--.-Nw-Nw-..-.-.-.-");
        juego.move("21-17");
        assertNotEquals("-Nb-.-.-..-.-.-.--.-.-.-.NB-.-Nw-.--Nw-.-NW-..-.-.-.--.-Nw-Nw-..-.-.-.-", juego.write());
    }
    
}
