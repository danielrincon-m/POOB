package aplicacion;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class AutomataCelular implements Serializable {
    static private int LONGITUD = 20;
    private Elemento[][] automata;

    /**
     * AutomataCelular Constructor
     */
    public AutomataCelular() {
        automata = new Elemento[LONGITUD][LONGITUD];
        for (int f = 0; f < LONGITUD; f++) {
            for (int c = 0; c < LONGITUD; c++) {
                automata[f][c] = null;
            }
        }
        algunosElementos();
    }

    /**
     * crear un nuevo automata
     */
    public void nuevoAutomata() {
        automata = new Elemento[LONGITUD][LONGITUD];
    }


    /**
     * Método Para obtener el tamaño del tablero
     *
     * @return El tamaño del tablero
     */
    public int getLongitud() {
        return LONGITUD;
    }

    /**
     * Método Que retorna un elemento en cierta posición
     *
     * @param f La fila
     * @param c La columna
     * @return El elemento que está en esa posición o nulo si no existe
     */
    public Elemento getElemento(int f, int c) {
        if (f >= 0 && f < LONGITUD && c >= 0 && c < LONGITUD) {
            return automata[f][c];
        } else {
            return null;
        }
    }

    /**
     * Método Que asigna un elemento en una posición específica del tablero
     *
     * @param f     La fila a escribir
     * @param c     La columna a escribir
     * @param nueva El elemento a añadir
     */
    public void setElemento(int f, int c, Elemento nueva) {
        automata[f][c] = nueva;
    }

    /**
     * Método de prueba para agregar elementos y probarlos en el tablero
     */
    public void algunosElementos() {
        // //Celulas
        // new Celula(this,1,1,"indiana");
        // new Celula(this,2,2,"007");
        // //Izquierdosas
        // new Izquierdosa(this,3,1,"marx");
        // new Izquierdosa(this,3,2, "hegel");
        // //Barreras
        // new Barrera(this,19,0,"suroeste");
        // new Barrera(this,0,19,"noreste");
        // //sociables
        // new Celula(this,3,5,"a");
        // new Celula(this,3,6,"b");
        // new Celula(this,5,7,"c");
        // new Sociable(this,4,6,"Daniel");
        // new Sociable(this,3,8,"Paula");
        // //Infecciosas
        // new Infeccioso(this,8,2,"i1");
        // new Infeccioso(this,8,5,"i2");
        // new Celula(this,7,3,"ca");
        // new Celula(this,9,1,"cb");
        // new Celula(this,7,1,"cc");
        // new Barrera(this,9,3,"cd");

        // //John y Horton
        new Conway(this, 5, 12, "Paula");
        new Conway(this, 5, 13, "Daniel");
        // Bloque
        new Conway(this, 19, 0, "Paula");
        new Conway(this, 18, 0, "Daniel");
        new Conway(this, 19, 1, "Paula");
        new Conway(this, 18, 1, "Daniel");
        //Parpadeador
        new Conway(this, 18, 8, "Paula");
        new Conway(this, 18, 9, "Daniel");
        new Conway(this, 18, 10, "Paula");
    }

    /**
     * Método que realiza una actualización del estado de los elementos según sus propiedades y su entorno
     */
    public void ticTac() {
        for (int i = 0; i < LONGITUD; i++) {
            for (int j = 0; j < LONGITUD; j++) {
                if (automata[i][j] != null) {
                    automata[i][j].decida();
                } else {
                    nace(i, j);
                }
            }
        }
        for (int i = 0; i < LONGITUD; i++) {
            for (int j = 0; j < LONGITUD; j++) {
                if (automata[i][j] != null) {
                    automata[i][j].cambie();
                }
            }
        }
    }

    /**
     * Método que verifica si una casilla tiene las cualidades correctas para que nazca un nuevo elemento
     *
     * @param fila    La fila que se desea verificar
     * @param columna La columna que se desea verificar
     */
    private void nace(int fila, int columna) {
        int celulasVivas = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Elemento elemento = getElemento(fila + i, columna + j);
                if (i != 0 || j != 0) {
                    if (elemento != null && elemento.isVivo()) {
                        celulasVivas++;
                    }
                }
            }
        }
        if (celulasVivas == 3) {
            new Conway(this, fila, columna, "Paula");
        }
    }

    /**
     * Genera un nuevo Autómata vacío
     *
     * @return El nuevo autómata
     */
    public Elemento[][] nuevo() {
        nuevoAutomata();
        return automata;
    }

    /**
     * Guarda la clase AutomataCelular en un archivo para ser cargada en el futuro en su estado actual
     *
     * @param file el archivo en donde se desea guardar la información
     * @throws AutomataException Cuando ocurre un error al guardar, con un mensaje general
     */
    public void guardar01(File file) throws AutomataException {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.ERROR_AL_GUARDAR);
        }
    }

    /**
     * Abre un archivo de tipo .dat que contiene un AutomataCelular y lo carga en el programa
     *
     * @param file El archivo con la información
     * @return El AutomataCelular construido a partir del archivo
     * @throws AutomataException Cuando ocurre un error al cargar, con un mensaje general
     */
    public AutomataCelular abrir01(File file) throws AutomataException {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            AutomataCelular automata = (AutomataCelular) objectIn.readObject();
            objectIn.close();
            return automata;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.ERROR_AL_ABRIR);
        }
    }

    /**
     * Guarda la clase AutomataCelular en un archivo para ser cargada en el futuro en su estado actual
     *
     * @param file el archivo en donde se desea guardar la información
     * @throws AutomataException Cuando ocurre un error al guardar, con mensajes específicos de los posibles errores
     */
    public void guardar(File file) throws AutomataException {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
        } catch (FileNotFoundException e) {
            throw new AutomataException(AutomataException.ARCHIVO_NO_ENCONTRADO);
        } catch (InvalidClassException e) {
            throw new AutomataException(AutomataException.CLASE_INVALIDA);
        } catch (NotSerializableException e) {
            throw new AutomataException(AutomataException.CLASE_NO_SERIALIZABLE);
        } catch (IOException e) {
            throw new AutomataException(AutomataException.ERROR_ENTRADA_SALIDA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.ERROR_AL_GUARDAR);
        }
    }

    /**
     * Abre un archivo de tipo .dat que contiene un AutomataCelular y lo carga en el programa
     *
     * @param file El archivo con la información
     * @return El AutomataCelular construido a partir del archivo
     * @throws AutomataException Cuando ocurre un error al cargar, con mensajes específicos dependiendo del error presentado
     */
    public AutomataCelular abrir(File file) throws AutomataException {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            AutomataCelular automata = (AutomataCelular) objectIn.readObject();
            objectIn.close();
            return automata;
        } catch (FileNotFoundException e) {
            throw new AutomataException(AutomataException.ARCHIVO_NO_ENCONTRADO);
        } catch (StreamCorruptedException e) {
            throw new AutomataException(AutomataException.ARCHIVO_CORRUPTO);
        } catch (ClassNotFoundException e) {
            throw new AutomataException(AutomataException.CLASE_NO_ENCONTRADA);
        } catch (InvalidClassException e) {
            throw new AutomataException(AutomataException.CLASE_INVALIDA);
        } catch (OptionalDataException e) {
            throw new AutomataException(AutomataException.DATOS_PRIMITIVOS);
        } catch (IOException e) {
            throw new AutomataException(AutomataException.ERROR_ENTRADA_SALIDA);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.ERROR_AL_ABRIR);
        }

    }

    /**
     * Exporta todos los elementos de la matriz en un archivo de texto, con su clase y su ubicación
     *
     * @param file El archivo al que se va a exportar
     * @throws AutomataException Cuando algo sale mal, con un mensaje de error general
     */
    public void exportar01(File file) throws AutomataException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(escribirAutomata());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new AutomataException(AutomataException.ERROR_AL_EXPORTAR);
        }
    }

    /**
     * Importa todas las piezas de un archvo de texto con el formato correcto y las carga en el tablero
     *
     * @param file El archivo de texto que contiene la información de las piezas
     * @throws AutomataException Cuando ocurre algún problema, con un mensaje de error general
     */
    public void importar01(File file) throws AutomataException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            construirAutomata(lines);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.ERROR_AL_IMPORTAR);
        }
    }

    /**
     * Exporta todos los elementos de la matriz en un archivo de texto, con su clase y su ubicación
     *
     * @param file El archivo al que se va a exportar
     * @throws AutomataException Cuando algo sale mal, con mensajes de error específicos
     */
    public void exportar02(File file) throws AutomataException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(escribirAutomata());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new AutomataException(AutomataException.ERROR_ENTRADA_SALIDA);
        } catch (Exception e) {
            throw new AutomataException(AutomataException.ERROR_AL_EXPORTAR);
        }
    }

    /**
     * Importa todas las piezas de un archvo de texto con el formato correcto y las carga en el tablero
     *
     * @param file El archivo de texto que contiene la información de las piezas
     * @throws AutomataException Cuando ocurre algún problema, con mensajes de error específicos
     */
    public void importar02(File file) throws AutomataException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            construirAutomata(lines);
        } catch (ClassNotFoundException e) {
            throw new AutomataException(AutomataException.CLASE_NO_ENCONTRADA);
        } catch (IllegalAccessException e) {
            throw new AutomataException(AutomataException.ACCESO_ILEGAL);
        } catch (InstantiationException e) {
            throw new AutomataException(AutomataException.ERROR_DE_INSTANCIACION);
        } catch (NoSuchMethodException e) {
            throw new AutomataException(AutomataException.NO_EXISTE_EL_METODO);
        } catch (InvocationTargetException e) {
            throw new AutomataException(AutomataException.ERROR_DE_INVOCACION);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.ERROR_AL_IMPORTAR);
        }
    }

    /**
     * Exporta todos los elementos de la matriz en un archivo de texto, con su clase y su ubicación
     *
     * @param file El archivo al que se va a exportar
     * @throws AutomataException Cuando algo sale mal, con mensajes de error especificos
     */
    public void exportar03(File file) throws AutomataException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(escribirAutomata());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new AutomataException(AutomataException.ERROR_ENTRADA_SALIDA);
        } catch (Exception e) {
            throw new AutomataException(AutomataException.ERROR_AL_EXPORTAR);
        }
    }

    /**
     * Importa todas las piezas de un archvo de texto con el formato correcto y las carga en el tablero, verificando que
     * las información de las piezas sea válida
     *
     * @param file El archivo de texto que contiene la información de las piezas
     * @throws AutomataException Cuando ocurre algún problema, con mensajes de error específicos
     */
    public void importar03(File file) throws AutomataException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            verificarErrores(lines);
            construirAutomata(lines);
        } catch (ClassNotFoundException e) {
            throw new AutomataException(AutomataException.CLASE_NO_ENCONTRADA);
        } catch (IllegalAccessException e) {
            throw new AutomataException(AutomataException.ACCESO_ILEGAL);
        } catch (InstantiationException e) {
            throw new AutomataException(AutomataException.ERROR_DE_INSTANCIACION);
        } catch (NoSuchMethodException e) {
            throw new AutomataException(AutomataException.NO_EXISTE_EL_METODO);
        } catch (InvocationTargetException e) {
            throw new AutomataException(AutomataException.ERROR_DE_INVOCACION);
        } catch (AutomataException e) {
            throw new AutomataException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.ERROR_AL_IMPORTAR);
        }
    }

    /**
     * Exporta todos los elementos de la matriz en un archivo de texto, con su clase y su ubicación
     *
     * @param file El archivo al que se va a exportar
     * @throws AutomataException Cuando algo sale mal, con mensajes de error especificos
     */
    public void exportar(File file) throws AutomataException {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(escribirAutomata());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new AutomataException(AutomataException.ERROR_ENTRADA_SALIDA);
        } catch (Exception e) {
            throw new AutomataException(AutomataException.ERROR_AL_EXPORTAR);
        }
    }

    /**
     * Importa todas las piezas de un archvo de texto con el formato correcto y las carga en el tablero, verificando que
     * las información de las piezas sea válida, además de aceptar cualquier clase que implemente la interfaz elemento*
     *
     * @param file El archivo de texto que contiene la información de las piezas
     * @throws AutomataException Cuando ocurre algún problema, con mensajes de error específicos
     */
    public void importar(File file) throws AutomataException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            verificarErrores(lines);
            construirAutomata(lines);
        } catch (ClassNotFoundException e) {
            throw new AutomataException(AutomataException.CLASE_NO_ENCONTRADA);
        } catch (IllegalAccessException e) {
            throw new AutomataException(AutomataException.ACCESO_ILEGAL);
        } catch (InstantiationException e) {
            throw new AutomataException(AutomataException.ERROR_DE_INSTANCIACION);
        } catch (NoSuchMethodException e) {
            throw new AutomataException(AutomataException.NO_EXISTE_EL_METODO);
        } catch (InvocationTargetException e) {
            throw new AutomataException(AutomataException.ERROR_DE_INVOCACION);
        } catch (AutomataException e) {
            throw new AutomataException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomataException(AutomataException.ERROR_AL_IMPORTAR);
        }
    }

    /**
     * Reinicia el autómata celular a uno vacío
     *
     * @return El AutomataCelular vacio
     */
    public AutomataCelular reiniciar() {
        return new AutomataCelular();
    }

    /**
     * Escribe las piezas del AutomataCelular en un formato adecuado para ser exportado
     *
     * @return La cadena lsita para ser escrita en el archivo
     */
    private String escribirAutomata() {
        StringBuilder matriz = new StringBuilder();
        for (int i = 0; i < LONGITUD; i++) {
            for (int j = 0; j < LONGITUD; j++) {
                if (automata[i][j] != null) {
                    //System.out.println(automata[i][j].getClass().getName());
                    matriz.append(automata[i][j].getClass().getSimpleName()).append(" ").append(i).append(" ").
                            append(j).append("\n");
                }
            }
        }
        return matriz.toString();
    }

    /**
     * Construye un nuevo AutomataCelular a partir de un archivo de texto
     *
     * @param lines Las lineas del archivo de texto
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    private void construirAutomata(ArrayList<String> lines) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        automata = new Elemento[LONGITUD][LONGITUD];
        for (String line : lines) {
            String[] splittedLine;
            splittedLine = line.trim().split(" ");
            int fila = Integer.parseInt(splittedLine[1]);
            int columna = Integer.parseInt(splittedLine[2]);
            Class cls = Class.forName("aplicacion." + splittedLine[0]);
            Class[] parameterType = {AutomataCelular.class, int.class, int.class, String.class};
            cls.getDeclaredConstructor(parameterType).newInstance(this, fila, columna, "Peppa");
        }
    }

    /**
     * Verifica errores en un archivo de importación y los escribe en un archivo de errores
     *
     * @param lines Las lineas del archivo de importación
     * @throws AutomataException
     * @throws IOException
     */
    private void verificarErrores(ArrayList<String> lines) throws AutomataException, IOException {
        File file = new File("automataErrG.txt");
        file.delete();
        boolean hayErrores = false;
        for (int i = 0; i < lines.size(); i++) {
            String[] splittedLine;
            splittedLine = lines.get(i).trim().split(" ");
            try {
                Class cls = Class.forName("aplicacion." + splittedLine[0]);
                if (!Elemento.class.isAssignableFrom(cls)) {
                    throw new ClassCastException();
                }
            } catch (ClassNotFoundException e) {
                hayErrores = true;
                escribirError("No se encontró la clase", i + 1, 1);
            } catch (ClassCastException e) {
                hayErrores = true;
                escribirError("La clase no es de tipo Elemento", i + 1, 1);
            } catch (Exception e) {
                hayErrores = true;
                escribirError("Error desconocido", i + 1, 1);
            }
            if (verificarEntero(splittedLine[1], i + 1, 2)) {
                hayErrores = true;
            }
            if (verificarEntero(splittedLine[2], i + 1, 3)) {
                hayErrores = true;
            }
        }
        if (hayErrores) {
            throw new AutomataException(AutomataException.ERROR_AL_IMPORTAR_CON_DETALLES);
        }
    }

    /**
     * Verifica que un entero cumpla con las especificaciones del AutomataCelular para ubicar un elemento
     *
     * @param numero   El número a convertir
     * @param linea    En que linea del texto se encuentra
     * @param posicion En qué posicion se encuentra
     * @return
     * @throws IOException
     */
    private boolean verificarEntero(String numero, int linea, int posicion) throws IOException {
        try {
            int x = Integer.parseInt(numero);
            if (x < 0 || x >= LONGITUD) {
                throw new IndexOutOfBoundsException();
            }
        } catch (NumberFormatException e) {
            escribirError("El número no es un entero", linea, posicion);
            return true;
        } catch (IndexOutOfBoundsException e) {
            escribirError("El número se sale de los límites", linea, posicion);
            return true;
        } catch (Exception e) {
            escribirError("Error desconocido", linea, posicion);
            return true;
        }
        return false;
    }

    /**
     * Escribe errores de importación en un archivo de texto
     *
     * @param error    El error a escribir
     * @param linea    La linea del error
     * @param posicion La posción en la linea del dato incorrecto
     * @throws IOException
     */
    private void escribirError(String error, int linea, int posicion) throws IOException {
        File file = new File("automataErr.txt");
        FileWriter fw = new FileWriter(file, true);
        fw.write(error + " en la linea " + linea + " en el dato número: " + posicion + "\n");
        fw.close();
    }
}
