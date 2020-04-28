package aplicacion;

import java.io.File;

public class AutomataCelular{
    static private int LONGITUD=20;
    private Elemento[][] automata;

    /**
     * AutomataCelular Constructor
     */
    public AutomataCelular() {
        automata=new Elemento[LONGITUD][LONGITUD];
        for (int f=0;f<LONGITUD;f++){
            for (int c=0;c<LONGITUD;c++){
                automata[f][c]=null;
            }
        }
        algunosElementos();
    }

    /**
     * crear un nuevo automata
     */
    public void nuevoAutomata(){ automata = new Elemento[LONGITUD][LONGITUD];}


    /**
     * Método Para obtener el tamaño del tablero
     * @return El tamaño del tablero
     */
    public int  getLongitud(){
        return LONGITUD;
    }

    /**
     * Método Que retorna un elemento en cierta posición
     * @param f La fila
     * @param c La columna
     * @return El elemento que está en esa posición o nulo si no existe
     */
    public Elemento getElemento(int f,int c){
        if(f >= 0 && f < LONGITUD && c >= 0 && c < LONGITUD){
            return automata[f][c];
        }else{
            return null;
        }
    }

    /**
     * Método Que asigna un elemento en una posición específica del tablero
     *
     * @param f La fila a escribir
     * @param c La columna a escribir
     * @param nueva El elemento a añadir
     */
    public void setElemento(int f, int c, Elemento nueva){
        automata[f][c]=nueva;
    }

    /**
     * Método de prueba para agregar elementos y probarlos en el tablero
     *
     */
    public void algunosElementos(){
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
     *
     */
    public void ticTac(){
        for(int i=0; i< LONGITUD; i++){
            for(int j=0; j< LONGITUD; j++){
                if(automata[i][j] != null){
                    automata[i][j].decida();
                }else{
                    nace(i, j);
                }
            }
        }
        for(int i=0; i< LONGITUD; i++){
            for(int j=0; j< LONGITUD; j++){
                if(automata[i][j] != null){
                    automata[i][j].cambie();
                }
            }
        }
    }

    /**
     * Método que verifica si una casilla tiene las cualidades correctas para que nazca un nuevo elemento
     *
     * @param fila La fila que se desea verificar
     * @param columna La columna que se desea verificar
     */
    private void nace(int fila, int columna){
        int celulasVivas=0;
        for(int i=-1; i <=1;i++){
            for(int j=-1; j<=1;j++){
                Elemento elemento = getElemento(fila + i, columna + j);
                if(i!=0 || j!=0){
                    if(elemento!=null && elemento.isVivo()){
                        celulasVivas++;
                    }
                }
            }
        }
        if(celulasVivas == 3){
            Elemento elemento = new Conway(this, fila, columna, "Paula");
        }
    }

    public Elemento[][] nuevo(){
        nuevoAutomata();
        return automata;
    }

    public void guardar(File file) throws  AutomataException{
        throw new AutomataException(AutomataException.GUARDAR_EN_CONSTRUCCION);
    }

    public  void abrir(File file) throws  AutomataException{
        throw  new AutomataException(AutomataException.ABRIR_EN_CONSTRUCCION);
    }
    public  void exportar(File file) throws  AutomataException{
        throw  new AutomataException(AutomataException.EXPORTE_EN_CONSTRUCCION);
    }

    public  void  importar(File file) throws  AutomataException{
        throw  new AutomataException(AutomataException.IMPORTAR_EN_CONSTRUCCION);
    }
    public AutomataCelular reiniciar() {
        return new AutomataCelular();
    }
}
