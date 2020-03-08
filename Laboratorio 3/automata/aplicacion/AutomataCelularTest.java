package aplicacion;
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
    /**
     * Default constructor for test class AutomataCelularTest
     */
    public AutomataCelularTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
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
}
