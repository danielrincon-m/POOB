import aplicacion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SinapTest {

    private String nombre;
    private String name;
    private String ubicacion;
    private String area;
    private String descripcion;

    private Sinap sinap;

    @BeforeEach
    public final void setUp(){
        sinap = new Sinap();
        nombre = "Tuparro";
        name = "Tuparro National Park";
        ubicacion = "Vichada";
        area = "548.000";
        descripcion = "Es una extensa sabana verde surcada...";
    }

    @Test
    public void deberiaAdicionarLaNuevaArea() {
        try {
            sinap.adicione(nombre, name, ubicacion, area, descripcion);
        } catch (Exception e) {
            fail("Ocurrió una excepcion");
        }
        assertEquals(nombre, sinap.getDetalles(nombre, name).getNombre());
        assertEquals(name, sinap.getDetalles(nombre, name).getName());
        assertEquals(ubicacion, sinap.getDetalles(nombre, name).getUbicacion());
        assertEquals(area, sinap.getDetalles(nombre, name).getArea());
        assertEquals(descripcion, sinap.getDetalles(nombre, name).getDescripcion());
    }

    @Test
    public void deberiaListarTodasLasAreas() {
        String ans = "Tuparro\n" +
                "Tuparro National Park\n" +
                "Es una extensa sabana verde surcada...\n\n";
        try {
            sinap.adicione(nombre, name, ubicacion, area, descripcion);
        } catch (Exception e) {
            fail("Ocurrió una excepcion");
        }
        assertEquals(ans, sinap.toString());
    }

    @Test
    public void sinapPruebaDeAceptacion() {
        String ans = "Tuparro\n" +
                "Tuparro National Park\n" +
                "Es una extensa sabana verde surcada...\n\n";

        try {
            sinap.adicione(nombre, name, ubicacion, area, descripcion);
        } catch (Exception e) {
            fail("Ocurrió una excepcion");
        }
        assertEquals(nombre, sinap.getDetalles(nombre, name).getNombre());
        assertEquals(name, sinap.getDetalles(nombre, name).getName());
        assertEquals(ubicacion, sinap.getDetalles(nombre, name).getUbicacion());
        assertEquals(area, sinap.getDetalles(nombre, name).getArea());
        assertEquals(descripcion, sinap.getDetalles(nombre, name).getDescripcion());
        assertEquals(ans, sinap.toString());
    }

    @Test
    public void deberiaLanzarExcepcionSiNoHayNombreInternacional() {
        name = "";

        try {
            sinap.adicione(nombre, name, ubicacion, area, descripcion);
            fail("Adicionó área sin nombre internacional");
        } catch (SINAPExcepecion e) {
            assertEquals(SINAPExcepecion.SIN_NOMBRE_INTERNACIONAL, e.getMessage());
        }
    }

    @Test
    public void deberiaLanzarExcepcionSiElAreaEstaDuplicada() {
        try {
            sinap.adicione(nombre, name, ubicacion, area, descripcion);
            sinap.adicione(nombre, name, ubicacion, area, descripcion);
            fail("Adicionó área dos veces");
        } catch (SINAPExcepecion e) {
            assertEquals(SINAPExcepecion.AREA_DUPLICADA, e.getMessage());
        }
    }

    @Test
    public void deberiaLanzarExcepcionSiElAreaNoEsNumerica() {
        String area = "548.000 Millones";

        try {
            sinap.adicione(nombre, name, ubicacion, area, descripcion);
            fail("Adicionó área con área no numérica");
        } catch (SINAPExcepecion e) {
            assertEquals(SINAPExcepecion.AREA_NO_NUMERICA, e.getMessage());
        }
    }

    @Test
    public void deberiaLanzarExcepcionSiLaDescripcionEsMuyCorta() {
        String descripcion = "Sabana";

        try {
            sinap.adicione(nombre, name, ubicacion, area, descripcion);
            fail("Adicionó área con descripcion muy corta");
        } catch (SINAPExcepecion e) {
            assertEquals(SINAPExcepecion.DESCRIPCION_MUY_CORTA, e.getMessage());
        }
    }

    @Test
    public void deberiaEncontrarUnArea() {
        String ans = "Tuparro\n" +
                "Tuparro National Park\n" +
                "Es una extensa sabana verde surcada...";
        try {
            sinap.adicione(nombre, name, ubicacion, area, descripcion);
            assertEquals(ans, sinap.busque("T").get(0).toString());
        } catch (Exception e) {
            fail ("Ocurrió una excepcion");
        }
    }
}
