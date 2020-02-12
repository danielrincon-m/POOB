import static org.junit.Assert.*;
import org.junit.Test;

/**
 * The test class TestUnitTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestUnitTest
{
    @Test
    public void deberiaPasar(){
        TestClass test = new TestClass(1);
        assertEquals(1, test.getX());
    }
    
    @Test
    public void deberiaFallar(){
        TestClass test = new TestClass(1);
        assertEquals(0, test.getX());
    }
    
    @Test
    public void deberiaErrar(){
        TestClass test = new TestClass(0);
        assertEquals(0, test.getX());
    }
}
