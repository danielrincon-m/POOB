package aplicacion;

import java.awt.*;
import java.util.Random;

public class MarbelGameBoard {
    private int nCeldas;
    private int nBarreras;
    private int nAgujeros;
    private int nMovimientos = 0;
    private int nBienUbicadas = 0;
    private int nMalUbicadas = 0;

    private Elemento[][] matrizElementos;

    /**
     * Constructor del tablero de juego
     * @param nCeldas Número de celdas en el tablero de juego
     * @param nBarreras Número de barreras en el tablero de juego
     * @param nAgujeros Número de agujeros (y casillas) en el tablero de juego
     */
    public MarbelGameBoard(int nCeldas, int nBarreras, int nAgujeros) {
        this.nCeldas = nCeldas;
        this.nBarreras = nBarreras;
        this.nAgujeros = nAgujeros;
        inicializar();
    }

    /**
     * Reinicia las estadísticas y genera un nuevo tablero
     */
    public void reiniciar() {
        inicializar();
        nMovimientos = 0;
        nBienUbicadas = 0;
        nMalUbicadas = 0;
    }

    /**
     * Agrega un nuevo elemento al tablero
     * @param fila La fila del tablero, basada en 0
     * @param columna La columna del tablero, basada en 0
     * @param elemento El elemento que se desea agregar
     */
    public void agregarElemento(int fila, int columna, Elemento elemento) {
        matrizElementos[fila][columna] = elemento;
    }

    /**
     * Elimina un elemento del tablero
     * @param fila La fila del tablero, basada en 0
     * @param columna La columna del tablero, basada en 0
     */
    public void eliminarElemento(int fila, int columna) {
        matrizElementos[fila][columna] = null;
    }

    /**
     * Mueve el elemento ubicado en la posición inicial a la posición final, sobreescribiendo la última
     * @param filaInicial Fila del elemento a mover, basada en 0
     * @param columnaInicial Columna del elemento a mover basada en 0
     * @param filaFinal Fila a donde se moverá el elemento, basada en 0
     * @param columnaFinal Columna a donde se moverá el elemento, basada en 0
     */
    public void moverElemento(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        matrizElementos[filaFinal][columnaFinal] = matrizElementos[filaInicial][columnaInicial];
        matrizElementos[filaInicial][columnaInicial] = null;
    }

    /**
     * Retorna el elemento ubicado en la posición indicada
     * @param fila La fila del elemento a consultar
     * @param columna La columna del elemento a consultar
     * @return El elemento que se encuentra en la posición dada o null si no hay ningún elemento
     */
    public Elemento elementoEn(int fila, int columna) {
        return matrizElementos[fila][columna];
    }

    /**
     * Realiza un movimiento en el tablero en cierta dirección
     * @param direccion Norte, sur, este, oeste
     */
    public void realizarMovimiento(String direccion) {
        int filaInicial, filaFinal;
        int columnaInicial, columnaFinal;
        int aumentoFila, aumentoColumna;
        boolean filaInversa, columnaInversa;
        switch (direccion) {
            case "norte":
            case "oeste":
                filaInversa = false;
                columnaInversa = false;
                break;
            case "sur":
                filaInversa = true;
                columnaInversa = false;
                break;
            case "este":
                filaInversa = false;
                columnaInversa = true;
                break;
            default:
                return;
        }
        filaInicial = filaInversa ? nCeldas - 1 : 0;
        filaFinal = filaInversa ? -1 : nCeldas;
        aumentoFila = filaInversa ? -1 : 1;
        columnaInicial = columnaInversa ? nCeldas - 1 : 0;
        columnaFinal = columnaInversa ? -1 : nCeldas;
        aumentoColumna = columnaInversa ? -1 : 1;
        moverPiezas(filaInicial, filaFinal, aumentoFila, columnaInicial, columnaFinal, aumentoColumna, direccion);
    }

    /**
     * Calcula una posición aleatoria en el tablero que no esté ocupada
     * @return La posición disponible en el tablero [fila, columna]
     */
    public int[] posicionAleatoriaValida() {
        int[] posicion = new int[2];
        Random random = new Random();
        boolean valida = false;
        while (!valida) {
            posicion[0] = random.nextInt(nCeldas);
            posicion[1] = random.nextInt(nCeldas);
            if (matrizElementos[posicion[0]][posicion[1]] == null) {
                valida = true;
            }
        }
        return posicion;
    }

    /**
     * Verifica si la posición extsite en el tablero
     * @param fila Fila a verificar
     * @param columna Columna a verificar
     * @return Si la posición es válida en el tablero
     */
    public boolean existePosicion(int fila, int columna) {
        return fila >= 0 && fila < nCeldas && columna >= 0 && columna < nCeldas;
    }

    /**
     * Agregar una pieza bien o mal ubicada a las estadísticas
     * @param bienUbicada Si está bien ubicada
     */
    public void agregarUbicada(boolean bienUbicada) {
        if (bienUbicada) {
            nBienUbicadas++;
        } else {
            nMalUbicadas++;
        }
    }

    /**
     * Obtener el número de piezas ubicadas de las estadísticas
     * @param bienUbicada Si está bien ubiacada
     * @return Número de puezas ubicadas (bien o mal)
     */
    public int getUbicada(boolean bienUbicada) {
        if (bienUbicada) {
            return nBienUbicadas;
        } else {
            return nMalUbicadas;
        }
    }

    /**
     * Obtiene el número de movimientos realizados
     * @return Número de movimientos
     */
    public int getnMovimientos() {
        return nMovimientos;
    }

    /**
     * Establecer el número de celdas del tablero en cada dirección
     * @param nCeldas El número de celdas
     */
    public void setnCeldas(int nCeldas) throws UnsupportedOperationException{
        if ((nBarreras + nAgujeros * 2) > (nCeldas * nCeldas) || nCeldas < 2) {
            throw new UnsupportedOperationException();
        }
        this.nCeldas = nCeldas;
        reiniciar();
    }

    /**
     * Establecer el número de barreras en el juego
     * @param nBarreras El número de barreras
     */
    public void setnBarreras(int nBarreras) throws UnsupportedOperationException{
        if ((nBarreras + nAgujeros * 2) > (nCeldas * nCeldas)) {
            throw new UnsupportedOperationException();
        }
        this.nBarreras = nBarreras;
        reiniciar();
    }

    /**
     * Establecer el número de barreras (y agujeros) en el juego
     * @param nAgujeros El número de de agujeros
     */
    public void setnAgujeros(int nAgujeros) throws UnsupportedOperationException{
        if ((nBarreras + nAgujeros * 2) > (nCeldas * nCeldas)) {
            throw new UnsupportedOperationException();
        }
        this.nAgujeros = nAgujeros;
        reiniciar();
    }

    /**
     * Crear un nuevo tablero del tamaño solicitado, y con las piezas establecidas si es posible
     * @throws UnsupportedOperationException Cuando el número de piezas y agujeros supera el número de celdas en el tablero
     */
    private void inicializar() {
        matrizElementos = new Elemento[nCeldas][nCeldas];
        Random rand = new Random();
        for (int i = 0; i < nBarreras; i++) {
            new Barrera(this);
        }
        for (int i = 0; i < nAgujeros; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            //randomColor.brighter();
            new Agujero(this, randomColor);
            new Canica(this, randomColor);
        }
    }

    /**
     * Mover todas las piezas en una dirección concreta
     * @param filaInicial La fila en donde se iniciará la iteración del tablero
     * @param filaFinal La fila final de la iteración del tablero
     * @param aumentoFila Si la fila incrementa o decrementa en cada ciclo
     * @param columnaInicial La columna en donde se inciará la iteración del tablero
     * @param columnaFinal La columna final de la iteración del tablero
     * @param aumentoColumna Si la columna incrementa o decrementa en cada iteración
     * @param direccion La dirección del movimiento (norte, sur, este, oeste)
     */
    private void moverPiezas(int filaInicial, int filaFinal, int aumentoFila,
                             int columnaInicial, int columnaFinal, int aumentoColumna, String direccion) {
        boolean huboMovimiento = false;
        for (int i = filaInicial; i != filaFinal; i += aumentoFila) {
            for (int j = columnaInicial; j != columnaFinal; j += aumentoColumna) {
                Elemento elemento = matrizElementos[i][j];
                if (elemento != null && elemento.getTipo().equals("canica")) {
                    ((Canica) elemento).mover(direccion);
                    huboMovimiento = true;
                }
            }
        }
        if (huboMovimiento) {
            nMovimientos++;
        }
    }

    /**
     * Imprime el tablero
     */
    private void imprimirTablero() {
        for (int i = 0; i < nCeldas; i++) {
            for (int j = 0; j < nCeldas; j++) {
                Elemento elemento = matrizElementos[i][j];
                if (elemento != null) {
                    System.out.print(elemento.getInfo() + "\t");
                } else {
                    System.out.print(null + "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    //Temporal
/*    public static void main(String[] args) {
        MarbelGameBoard mgb = new MarbelGameBoard(4, 1, 3);
        mgb.imprimirTablero();
        mgb.realizarMovimiento("este");
        mgb.imprimirTablero();
    }*/
}
