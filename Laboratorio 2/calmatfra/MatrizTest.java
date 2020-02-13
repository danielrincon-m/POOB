
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MatrizTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MatrizTest
{   
    //Ciclo 1
    @Test
    public void deberiaCrearUnaMatrizAPartirDeLista3D(){
        int[][][] elementos = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        Matriz matriz = new Matriz(elementos);
        assertEquals("{{1/2,1/3},{1/4,1/5}}", matriz.toString());
    }
    
    @Test
    public void deberiaCrearUnaMatrizAPartirDeMatrizDeFraccionarios(){
        Fraccionario[][] elementos = {{new Fraccionario(1, 2), new Fraccionario(1, 3)}, {new Fraccionario(1, 4), new Fraccionario(1, 5)}};
        Matriz matriz = new Matriz(elementos);
        assertEquals("{{1/2,1/3},{1/4,1/5}}", matriz.toString());
    }

    @Test
    public void deberiaCrearUnaMatrizVacia(){
        int[][][] elementos = {};
        Matriz matriz = new Matriz(elementos);
        assertEquals("{}", matriz.toString());
    }

    @Test
    public void deberiaRetornarLosElementosEnLaPosicionCorrecta(){
        int[][][] elementos = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        Matriz matriz = new Matriz(elementos);
        assertEquals("1/2", matriz.get(0, 0).toString());
        assertEquals("1/3", matriz.get(0, 1).toString());
        assertEquals("1/4", matriz.get(1, 0).toString());
        assertEquals("1/5", matriz.get(1, 1).toString());
    }

    @Test
    public void deberiaIdentificarDiferentesRepresentacionesDeFraccionario(){
        int[][][] elementos = {{{1}, {1, 3}}, {{1, 1, 4}, {1, 5}}};
        Matriz matriz = new Matriz(elementos);
        assertEquals("1/1", matriz.get(0, 0).toString());
        assertEquals("1/3", matriz.get(0, 1).toString());
        assertEquals("5/4", matriz.get(1, 0).toString());
        assertEquals("1/5", matriz.get(1, 1).toString());
    }

    @Test
    public void deberiaDarseCuentaSiLaPosicionNoExiste(){
        int[][][] elementos = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        Matriz matriz = new Matriz(elementos);
        assertNull(matriz.get(2, 2));
    }
    
    //Ciclo 2
    @Test
    public void deberiaDeterminarSiLasDimensionesDeDosMatricesCoinciden(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}, {{6}, {1, 4}}};
        int[][][] elem3 = {{{1, 2}, {1, 3}}};
        Matriz mat1 = new Matriz(elem1);
        Matriz mat2 = new Matriz(elem2);
        Matriz mat3 = new Matriz(elem3);
        assertTrue(mat1.mismasDimensiones(mat2));
        assertTrue(mat2.mismasDimensiones(mat1));
        assertFalse(mat1.mismasDimensiones(mat3));
        assertFalse(mat2.mismasDimensiones(mat3));
        assertFalse(mat3.mismasDimensiones(mat1));
    }
    
    @Test
    public void deberiaSumarDosMatricesDeLaMismaDimension(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}, {{6}, {1, 4}}};
        Matriz mat1 = new Matriz(elem1);
        Matriz mat2 = new Matriz(elem2);
        assertEquals("{{5/2,17/15},{25/4,9/20}}", mat1.sume(mat2).toString());
    }
    
    @Test
    public void sumaDeberiaRetornarNuloSiSonDeDiferentesDimensiones(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}};
        Matriz mat1 = new Matriz(elem1);
        Matriz mat2 = new Matriz(elem2);
        assertNull(mat1.sume(mat2));
    }
    
    @Test
    public void deberiaRestarDosMatricesDeLaMismaDimension(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}, {{6}, {1, 4}}};
        Matriz mat1 = new Matriz(elem1);
        Matriz mat2 = new Matriz(elem2);
        assertEquals("{{-3/2,-7/15},{-23/4,-1/20}}", mat1.reste(mat2).toString());
    }
    
    @Test
    public void restaDeberiaRetornarNuloSiSonDeDiferentesDimensiones(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}};
        Matriz mat1 = new Matriz(elem1);
        Matriz mat2 = new Matriz(elem2);
        assertNull(mat1.reste(mat2));
    }
    
    //ciclo 3
    @Test
    public void multipliqueDeberiaFuncionarParaMatricesDelMismoTamaño(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}, {{6}, {1, 4}}};
        Matriz mat1 = new Matriz(elem1);
        Matriz mat2 = new Matriz(elem2);
        assertEquals("{{1/1,4/15},{3/2,1/20}}", mat1.multiplique(mat2).toString());
    }
    
    @Test
    public void MultipliqueDeberiaRetornarNuloSiSonDeDiferenteTamaño(){
        int[][][] elem1 = {{{1, 2}, {1, 3}}, {{1, 4}, {1, 5}}};
        int[][][] elem2 = {{{2, 1}, {4, 5}}};
        Matriz mat1 = new Matriz(elem1);
        Matriz mat2 = new Matriz(elem2);
        assertNull(mat1.multiplique(mat2));
    }
    
    @Test
    public void ProdMatricialDeberiaFuncionarParaMatricesCompatibles(){
        int[][][] elem1 = {{{-1, 2}, {1, 3}}};
        int[][][] elem2 = {{{4}}, {{0, 4}}};
        Matriz mat1 = new Matriz(elem1);
        Matriz mat2 = new Matriz(elem2);
        assertEquals("{{-2/1}}", mat1.productoMatricial(mat2).toString());
    }
    
    @Test
    public void ProdMatricialDeberiaRetornarNuloSiSonIncompatibles(){
        int[][][] elem1 = {{{1, 2}, {1, 3}, {1/4}}};
        int[][][] elem2 = {{{4, 5}}, {{1, 4}}};
        Matriz mat1 = new Matriz(elem1);
        Matriz mat2 = new Matriz(elem2);
        assertNull(mat1.productoMatricial(mat2));
    }
}
