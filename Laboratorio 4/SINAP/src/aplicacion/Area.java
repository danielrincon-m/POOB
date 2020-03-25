package aplicacion;

/**
 * Contiene los detalles de las áreas de reserva
 *
 * @author POOB 01
 * @version ECI 2016-1
 */

public class Area {
    private String nombre;
    private String name;
    private String ubicacion;
    private String area;
    private String descripcion;

    /**
     * Crea un area con sus detalles
     *
     * @param nombre      nombre en español
     * @param name        nombre en ingles
     * @param ubicacion   departamento(s) donde su ubica
     * @param area
     * @param descripcion
     */
    public Area(String nombre, String name, String ubicacion, String area, String descripcion) throws SINAPExcepecion {
        this.nombre = nombre.trim();
        this.name = name.trim();
        this.ubicacion = ubicacion.trim();
        this.area = area.trim();
        this.descripcion = descripcion.trim();
        checkExceptions();
    }

    /**
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @return
     */
    public String getArea() {
        return area;
    }

    /**
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return Una cadena con los nombres y la descripcion
     */
    public String toString() {
        return nombre + "\n" + name + "\n" + descripcion;
    }

    private void checkExceptions() throws SINAPExcepecion {
        //Nombre internacional vacio
        if (name.equals("")) {
            throw new SINAPExcepecion(SINAPExcepecion.SIN_NOMBRE_INTERNACIONAL);
        }
        //area no numerica
        try {
            Double num = Double.parseDouble(area);
        } catch (NumberFormatException e) {
            throw new SINAPExcepecion(SINAPExcepecion.AREA_NO_NUMERICA);
        }
        //descripcion muy corta
        int longitudMinimaDescripcion = 20;
        if (descripcion.length() < longitudMinimaDescripcion){
            throw new SINAPExcepecion(SINAPExcepecion.DESCRIPCION_MUY_CORTA);
        }
    }
}
