package aplicacion;

import java.awt.*;
import java.util.Random;

public class MarbelGameBoard {
    private int nCeldas;
    private int nBarreras;
    private int nAgujeros;

    private Elemento[][] matrizElementos;

    public MarbelGameBoard(int nCeldas, int nBarreras, int nAgujeros) {
        this.nCeldas = nCeldas;
        this.nBarreras = nBarreras;
        this.nAgujeros = nAgujeros;
        inicializar();
    }

    public void agregarElemento(int fila, int columna, Elemento elemento) {
        matrizElementos[fila][columna] = elemento;
    }

    public void eliminarElemento(int fila, int columna) {
        matrizElementos[fila][columna] = null;
    }

    public void moverElemento(int filaInicial, int columnaInicial, int filaFinal, int columnaFinal) {
        matrizElementos[filaFinal][columnaFinal] = matrizElementos[filaInicial][columnaInicial];
        matrizElementos[filaInicial][columnaInicial] = null;
    }

    public Elemento elementoEn(int fila, int columna) {
        return matrizElementos[fila][columna];
    }

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

    public boolean existePosicion(int fila, int columna) {
        return fila >= 0 && fila < nCeldas && columna >= 0 && columna < nCeldas;
    }

    private void inicializar() {
        matrizElementos = new Elemento[nCeldas][nCeldas];
        Color color;
        Random rand = new Random();
        for (int i = 0; i < nBarreras; i++) {
            Barrera ba = new Barrera(this);
        }
        for (int i = 0; i < nAgujeros; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            randomColor.brighter();
            Agujero ag = new Agujero(this, randomColor);
            Canica ca = new Canica(this, randomColor);
        }
    }

    private void moverPiezas(int filaInicial, int filaFinal, int aumentoFila,
                             int columnaInicial, int columnaFinal, int aumentoColumna, String direccion) {
        for (int i = filaInicial; i != filaFinal; i += aumentoFila) {
            for (int j = columnaInicial; j != columnaFinal; j += aumentoColumna) {
                Elemento elemento = matrizElementos[i][j];
                if (elemento != null && elemento.getTipo().equals("canica")) {
                    ((Canica)elemento).mover(direccion);
                }
            }
        }
    }

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
    public static void main(String[] args) {
        MarbelGameBoard mgb = new MarbelGameBoard(4, 1, 3);
        mgb.imprimirTablero();
        mgb.realizarMovimiento("este");
        mgb.imprimirTablero();
    }
}
