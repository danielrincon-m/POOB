package aplicacion;

public class MarbelGameBoard {
    private int nCeldas;
    private int nBarreras;
    private int nAgujeros;

    private Elemento[][] matrizElementos;

    public MarbelGameBoard(int nCeldas, int nBarreras, int nAgujeros) {
        this.nCeldas = nCeldas;
        this.nBarreras = nBarreras;
        this.nAgujeros = nAgujeros;
    }

    private int[] posicionAleatoria() {
        int[] posicion = new int[2];
        return posicion;
    }

    private void inicializar() {
        matrizElementos = new Elemento[nCeldas][nCeldas];

    }
}
