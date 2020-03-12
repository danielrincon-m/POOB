package aplicacion;
import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AutomataCelularTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AutomataCelularTest
{    
    @Test
    public void deberiaMorirEnTresTictac(){
        AutomataCelular automata=new AutomataCelular();
        new Celula(automata,1,1, "indiana");
        automata.ticTac();
        assertEquals(true,automata.getElemento(1,1).isVivo());
        automata.ticTac();
        assertEquals(true,automata.getElemento(1,1).isVivo());
        automata.ticTac();
        assertEquals(false,automata.getElemento(1,1).isVivo());
    }
    
    @Test
    public void deberiaMorirLaCelulaIzquierdosaDeLaIzquierda(){
        AutomataCelular automata=new AutomataCelular();
        new Izquierdosa(automata,3,1,"marx");
        new Izquierdosa(automata,3,2, "hegel");
        automata.ticTac();
        assertEquals(true,automata.getElemento(3, 1).isVivo());
        assertEquals(true,automata.getElemento(3, 2).isVivo());
        automata.ticTac();
        assertEquals(false,automata.getElemento(3, 1).isVivo());
        assertEquals(true,automata.getElemento(3, 2).isVivo());
        automata.ticTac();
        assertEquals(false,automata.getElemento(3, 1).isVivo());
        assertEquals(true,automata.getElemento(3, 2).isVivo());
    }
    
    @Test
    public void deberiaPermanecerMuertaLaBarrera(){
        AutomataCelular automata=new AutomataCelular();
        new Barrera(automata,0,19,"sureste");
        new Barrera(automata,19,0,"noreste");
        automata.ticTac();
        assertEquals(false,automata.getElemento(0,19).isVivo());
        automata.ticTac();
        assertEquals(false,automata.getElemento(19,0).isVivo());
    }
    
    @Test
    public void deberiaComportarseComoSociable(){
        AutomataCelular automata=new AutomataCelular();
        new Sociable(automata,4,6,"Daniel");
        new Celula(automata,3,5,"a");
        new Celula(automata,3,6,"b");
        new Celula(automata,5,5,"c");
        automata.ticTac();
        assertEquals(false,automata.getElemento(4,6).isVivo());
        automata.ticTac();
        assertEquals(true,automata.getElemento(4,6).isVivo());
        automata.ticTac();
        assertEquals(false,automata.getElemento(4,6).isVivo());
    }
    
    @Test
    public void infecciosaDeberiaInfectarALasDemas(){
        AutomataCelular automata = new AutomataCelular();
        new Infeccioso(automata,8,2,"i1");
        new Celula(automata,7,3,"ca");
        new Celula(automata,9,1,"cb");              
        new Celula(automata,7,1,"cc");
        new Barrera(automata,9,3,"cd");
        automata.ticTac();
        assertTrue(automata.getElemento(7,3).getColor() == Color.yellow);
        assertTrue(automata.getElemento(9,1).getColor() == Color.yellow);
        assertTrue(automata.getElemento(7,1).getColor() == Color.yellow);
        assertTrue(automata.getElemento(9,3).getColor() == Color.yellow);
    }
}
