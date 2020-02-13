import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FraccionarioTest {
    // Pruebas Para mcd (a, b)
    @Test
    public void mcdDeberiaSer1siPrimosRelativos() {
        assertEquals (1, Fraccionario.mcd(3, 4));
        assertEquals (1, Fraccionario.mcd(6, 35));
        assertEquals (1, Fraccionario.mcd(35, 24));
        assertEquals (1, Fraccionario.mcd(7, 5));
    }

    @Test
    public void mcdDeberiaSerElMenorSiMultiplos() {
        assertEquals (3, Fraccionario.mcd(3, 12));
        assertEquals (4, Fraccionario.mcd(16, 4));
    }

    @Test
    public void mcdDeberiaSerCualquieraDeLosDosSiIguales() {
        assertEquals (1204, Fraccionario.mcd(1204, 1204));
        assertEquals (452, Fraccionario.mcd(452, 452));
        assertEquals (1, Fraccionario.mcd(1, 1));
    }

    @Test
    public void mcdDeberiaCalcularseBienEnCasosCorrientes() {
        assertEquals (30, Fraccionario.mcd(180, 210));
        assertEquals (415800, Fraccionario.mcd(4158000, 5405400));
    }

    @Test
    public void mcdDeberiaSerElMismoParaNegativos() {
        assertEquals (Fraccionario.mcd(-210,180), Fraccionario.mcd(180, 210));
        assertEquals (Fraccionario.mcd(210,-180), Fraccionario.mcd(180, 210));
        assertEquals (Fraccionario.mcd(-210,-180), Fraccionario.mcd(180, 210));
    }

    @Test
    public void mcdDeberiaSerConmutativo() {
        assertEquals (Fraccionario.mcd(210,180), Fraccionario.mcd(180, 210));
        assertEquals (Fraccionario.mcd(5405400,4158000), Fraccionario.mcd(4158000, 5405400));
        assertEquals (Fraccionario.mcd(12,3), Fraccionario.mcd(3, 12));
        assertEquals (Fraccionario.mcd(35, 24), Fraccionario.mcd(35, 24));
        assertEquals (Fraccionario.mcd(210,-180), Fraccionario.mcd(-180, 210));
    }

    
    // Pruebas de creacion de fraccionarios
    @Test
    public void deberiaPoderCrearEnteros() {
        Fraccionario ent1 = new Fraccionario (1234);
        assertEquals(1234,ent1.numerador());
        assertEquals(1,ent1.denominador());
        assertEquals(0,new Fraccionario (0).numerador());
    }

    @Test
    public void deberiaPoderCrearFraccionariosNumeradorCero() {
        assertEquals(0,new Fraccionario (0,10).numerador());
        assertEquals(0,new Fraccionario (0).numerador());
        assertEquals(0,new Fraccionario (0,-10).numerador());
    }

    @Test
    public void deberiaPoderCrearCompuestos() {
        Fraccionario frac = new Fraccionario(1, 1, 4);
        assertEquals(5, frac.numerador());
        assertEquals(4, frac.denominador());
    }

    @Test
    public void noDeberiaSimplificarSiPrimosRelativos () {
        Fraccionario pr= new Fraccionario (100,3);
        assertEquals(100, pr.numerador());
        assertEquals(3, pr.denominador());
        Fraccionario pr2= new Fraccionario (-100,1);
        assertEquals(-100, pr2.numerador());
        assertEquals(1, pr2.denominador());
    }

    @Test
    public void deberiaSimplificarFraccionarios() {
        Fraccionario fr1= new Fraccionario (5405400,4158000);
        assertEquals(13, fr1.numerador());
        assertEquals(10, fr1.denominador());
        Fraccionario fr2= new Fraccionario (-100,60);
        assertEquals(-5, fr2.numerador());
        assertEquals(3, fr2.denominador());
    }

    @Test
    public void noDeberiaHaberDenominadoresSimplificadosNegativos () {
        Fraccionario fr1= new Fraccionario (100,-3);
        assertEquals(-100, fr1.numerador());
        assertEquals(3, fr1.denominador());
        Fraccionario fr2= new Fraccionario (-100,-3);
        assertEquals(100, fr2.numerador());
        assertEquals(3, fr2.denominador());
    }

    @Test
    public void deberiaDarseCuentaQueUnFraccionarioEsIgualAElMismo () {
        assertEquals(new Fraccionario (0),new Fraccionario (0));
        assertEquals(new Fraccionario (1234),new Fraccionario (1234));
        assertEquals (new Fraccionario(5405400,4158000), new Fraccionario(5405400,4158000));
    }

    @Test
    public void deberiaDarseCuentaSiDosFraccionariosSonIgualesConNegativos () {
        assertEquals(new Fraccionario(3,100), new Fraccionario(-3,-100));
        assertEquals(new Fraccionario(-3,100), new Fraccionario(3,-100));
    }

    @Test
    public void deberiaDarseCuentaSiDosFraccionariosCreadosDistintosSonIguales () {
        assertEquals (new Fraccionario(5405400,4158000), new Fraccionario(54054,41580));
        assertEquals (new Fraccionario(13,10), new Fraccionario(54054,41580));
    }

    @Test
    public void deberiaDarseCuentaQueCualquieFraccionarioConNumeradorCeroEsIgual () {
        assertEquals (new Fraccionario(0,4158000), new Fraccionario(0,1));
        assertEquals (new Fraccionario(0,-10), new Fraccionario(0));
    }

    
    //Pruebas de toString() 
    @Test
    public void deberiaPoderEscribirFraccionarioComoCadena () {
        assertEquals("1/2", new Fraccionario(1,2).toString());
        assertEquals("-1/2", new Fraccionario(-1,2).toString());
        assertEquals("1/2", new Fraccionario(4,8).toString());
    }

    
    //Pruebas de sume()
    @Test
    public void deberiaSumarFraccionariosCorrectamente() {
        Fraccionario frac1 = new Fraccionario(1, 2);
        Fraccionario frac2 = new Fraccionario(3, 2);
        Fraccionario frac3 = new Fraccionario(7, 3);
        assertEquals("2/1", frac1.sume(frac2).toString());
        assertEquals("17/6", frac1.sume(frac3).toString());
    }

    @Test
    public void laSumaDeberiaSerConmutativa() {
        Fraccionario frac1 = new Fraccionario(3, 2);
        Fraccionario frac2 = new Fraccionario(7, 3);
        assertEquals(frac1.sume(frac2).toString(), frac2.sume(frac1).toString());
    }
    
    @Test
    public void laSumaDeberiaFuncionarConCero() {
        Fraccionario frac1 = new Fraccionario(3, 2);
        Fraccionario frac2 = new Fraccionario(0, 3);
        assertEquals("3/2", frac1.sume(frac2).toString());
    }
    
    @Test
    public void laSumaDeberiaFuncionarConNegativos() {
        Fraccionario frac1 = new Fraccionario(-1, 2);
        Fraccionario frac2 = new Fraccionario(2, 3);
        Fraccionario frac3 = new Fraccionario(-4, 5);
        assertEquals("1/6", frac1.sume(frac2).toString());
        assertEquals("-13/10", frac1.sume(frac3).toString());
        assertEquals("-2/15", frac2.sume(frac3).toString());
    }

    
    //Pruebas de reste()
    @Test
    public void deberiaRestarFraccionariosCorrectamente() {
        Fraccionario frac1 = new Fraccionario(1, 2);
        Fraccionario frac2 = new Fraccionario(3, 2);
        Fraccionario frac3 = new Fraccionario(7, 3);
        assertEquals("-1/1", frac1.reste(frac2).toString());
        assertEquals("-11/6", frac1.reste(frac3).toString());
    }
    
    @Test
    public void laRestaDeberiaFuncionarConCero() {
        Fraccionario frac1 = new Fraccionario(3, 2);
        Fraccionario frac2 = new Fraccionario(0, 3);
        assertEquals("3/2", frac1.reste(frac2).toString());
    }
    
    @Test
    public void laRestaDeberiaFuncionarConNegativos() {
        Fraccionario frac1 = new Fraccionario(-1, 2);
        Fraccionario frac2 = new Fraccionario(2, 3);
        Fraccionario frac3 = new Fraccionario(-4, 5);
        assertEquals("-7/6", frac1.reste(frac2).toString());
        assertEquals("3/10", frac1.reste(frac3).toString());
        assertEquals("22/15", frac2.reste(frac3).toString());
    }
    
    
    //Pruebas de multiplique
    @Test
    public void deberiaMultiplicarFraccionariosCorrectamente() {
        Fraccionario frac1 = new Fraccionario(1, 2);
        Fraccionario frac2 = new Fraccionario(3, 2);
        Fraccionario frac3 = new Fraccionario(7, 3);
        assertEquals("3/4", frac1.multiplique(frac2).toString());
        assertEquals("7/6", frac1.multiplique(frac3).toString());
    }
    
    @Test
    public void laMultiplicacionDeberiaFuncionarConCero() {
        Fraccionario frac1 = new Fraccionario(3, 2);
        Fraccionario frac2 = new Fraccionario(0, 3);
        assertEquals("0/1", frac1.multiplique(frac2).toString());
    }
    
    @Test
    public void laMultiplicacionDeberiaFuncionarConNegativos() {
        Fraccionario frac1 = new Fraccionario(-1, 2);
        Fraccionario frac2 = new Fraccionario(2, 3);
        Fraccionario frac3 = new Fraccionario(-4, 5);
        assertEquals("-1/3", frac1.multiplique(frac2).toString());
        assertEquals("2/5", frac1.multiplique(frac3).toString());
        assertEquals("-8/15", frac2.multiplique(frac3).toString());
    }
    
    @Test
    public void laMultiplicacionDeberiaSerConmutativa() {
        Fraccionario frac1 = new Fraccionario(1, 2);
        Fraccionario frac2 = new Fraccionario(3, 2);
        assertEquals(frac1.multiplique(frac2).toString(), frac2.multiplique(frac1).toString());
    }
}
