import aplicacion.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SinapTest {

    @Test
    public void deberiaAdicionarLaNuevaArea() {
        Sinap sinap = new Sinap();
        String nombre = "Tuparro";
        String name = "Tuparro National Park";
        String ubicacion = "Vichada";
        String area = "548.000";
        String descripcion = "Es una extensa sabana verde surcada...";

        sinap.adicione(nombre, name, ubicacion, area, descripcion);
        assertEquals(nombre, sinap.getDetalles(nombre, name).getNombre());
        assertEquals(name, sinap.getDetalles(nombre, name).getName());
        assertEquals(ubicacion, sinap.getDetalles(nombre, name).getUbicacion());
        assertEquals(area, sinap.getDetalles(nombre, name).getArea());
        assertEquals(descripcion, sinap.getDetalles(nombre, name).getDescripcion());
    }

    @Test
    public void deberiaListarTodasLasAreas() {
        Sinap sinap = new Sinap();
        String nombre = "Tuparro";
        String name = "Tuparro National Park";
        String ubicacion = "Vichada";
        String area = "548.000";
        String descripcion = "Es una extensa sabana verde surcada...";

        sinap.adicione(nombre, name, ubicacion, area, descripcion);
        String ans = "Tuparro\n" +
                "Tuparro National Park\n" +
                "Es una extensa sabana verde surcada...\n\n";
        assertEquals(ans, sinap.toString());
    }

    @Test
    public void sinapPruebaDeAceptacion(){
        Sinap sinap = new Sinap();
        String nombre = "Tuparro";
        String name = "Tuparro National Park";
        String ubicacion = "Vichada";
        String area = "548.000";
        String descripcion = "Es una extensa sabana verde surcada...";
        String ans = "Tuparro\n" +
                "Tuparro National Park\n" +
                "Es una extensa sabana verde surcada...\n\n";

        sinap.adicione(nombre, name, ubicacion, area, descripcion);
        assertEquals(nombre, sinap.getDetalles(nombre, name).getNombre());
        assertEquals(name, sinap.getDetalles(nombre, name).getName());
        assertEquals(ubicacion, sinap.getDetalles(nombre, name).getUbicacion());
        assertEquals(area, sinap.getDetalles(nombre, name).getArea());
        assertEquals(descripcion, sinap.getDetalles(nombre, name).getDescripcion());
        assertEquals(ans, sinap.toString());
    }
}
