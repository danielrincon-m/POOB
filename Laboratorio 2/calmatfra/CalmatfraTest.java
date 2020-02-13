
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CalmatfraTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CalmatfraTest
{
    //Ciclo 1
    @Test
    public void deberiaCrearYAlmacenarUnaMatrizDeseUnaLista3D(){
        int[][][] elementos = {{{1, 2}, {1}}, {{1, 1, 4}, {1, 5}}};
        Calmatfra calmatfra = new Calmatfra();
        calmatfra.asigne("mat1", elementos);
        assertEquals(calmatfra.consulta("mat1"), "{{1/2,1/1},{5/4,1/5}}");
    }

    @Test
    public void deberiaRetornarNullSiLaMatrizNoExiste(){
        Calmatfra calmatfra = new Calmatfra();
        assertNull(calmatfra.consulta("a"));
    }
    
    //Ciclo 2
    @Test
    public void hacerUnaSumaNoDeberiaAlterarLasMatricesOriginales(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}, {{6}, {1, 4}}};
        Calmatfra calmatfra = new Calmatfra();
        
        calmatfra.asigne("mat1", elem1);
        calmatfra.asigne("mat2", elem2);
        
        String original1 = calmatfra.consulta("mat1");
        String original2 = calmatfra.consulta("mat2");
        
        calmatfra.opere("mat3", "mat1", '+', "mat2");
        
        assertEquals(original1, calmatfra.consulta("mat1"));
        assertEquals(original2, calmatfra.consulta("mat2"));
    }
    
    @Test
    public void hacerUnaRestaNoDeberiaAlterarLasMatricesOriginales(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}, {{6}, {1, 4}}};
        Calmatfra calmatfra = new Calmatfra();
        
        calmatfra.asigne("mat1", elem1);
        calmatfra.asigne("mat2", elem2);
        
        String original1 = calmatfra.consulta("mat1");
        String original2 = calmatfra.consulta("mat2");
        
        calmatfra.opere("mat3", "mat1", '-', "mat2");
        
        assertEquals(original1, calmatfra.consulta("mat1"));
        assertEquals(original2, calmatfra.consulta("mat2"));
    }
    
    @Test
    public void elResultadoDeLasOperacionesDeberiaGuardarseCorrectamente(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}, {{6}, {1, 4}}};
        Calmatfra calmatfra = new Calmatfra();
        
        calmatfra.asigne("mat1", elem1);
        calmatfra.asigne("mat2", elem2);
        
        calmatfra.opere("mat3", "mat1", '+', "mat2");
        calmatfra.opere("mat4", "mat1", '-', "mat2");
        calmatfra.opere("mat5", "mat1", '.', "mat2");
        calmatfra.opere("mat6", "mat1", '*', "mat2");
        
        assertEquals("{{5/2,17/15},{25/4,9/20}}", calmatfra.consulta("mat3"));
        assertEquals("{{-3/2,-7/15},{-23/4,-1/20}}", calmatfra.consulta("mat4"));
        assertEquals("{{1/1,4/15},{3/2,1/20}}", calmatfra.consulta("mat5"));
        assertEquals("{{3/1,29/60},{17/10,1/4}}", calmatfra.consulta("mat6"));
    }
    
    @Test
    public void deberiaResponderNullSiUnaMatrizNoExiste(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        Calmatfra calmatfra = new Calmatfra();
        
        calmatfra.asigne("mat1", elem1);
        
        calmatfra.opere("mat3", "mat1", '+', "mat2");
        
        assertNull(calmatfra.consulta("mat3"));
    }
    
    
    //Ciclo 3
    @Test
    public void hacerUnaMultiplicacionNoDeberiaAlterarLasMatricesOriginales(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}, {{6}, {1, 4}}};
        Calmatfra calmatfra = new Calmatfra();
        
        calmatfra.asigne("mat1", elem1);
        calmatfra.asigne("mat2", elem2);
        
        String original1 = calmatfra.consulta("mat1");
        String original2 = calmatfra.consulta("mat2");
        
        calmatfra.opere("mat3", "mat1", '.', "mat2");
        
        assertEquals(original1, calmatfra.consulta("mat1"));
        assertEquals(original2, calmatfra.consulta("mat2"));
    }
    
    @Test
    public void hacerUnProductoMatricialNoDeberiaAlterarLasMatricesOriginales(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}, {{6}, {1, 4}}};
        Calmatfra calmatfra = new Calmatfra();
        
        calmatfra.asigne("mat1", elem1);
        calmatfra.asigne("mat2", elem2);
        
        String original1 = calmatfra.consulta("mat1");
        String original2 = calmatfra.consulta("mat2");
        
        calmatfra.opere("mat3", "mat1", '*', "mat2");
        
        assertEquals(original1, calmatfra.consulta("mat1"));
        assertEquals(original2, calmatfra.consulta("mat2"));
    }
}
