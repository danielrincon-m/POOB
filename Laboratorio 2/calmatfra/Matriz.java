/**
 * @author ECI, 2019
 *
 */
public class Matriz{

    private int filas;
    private int columnas;
    
    private Fraccionario [][] matriz;

    /**
     * Retorna una matriz dados sus elementos. Los fraccionarios se representan como {numerador, denominador}
     */
    public static boolean esMatriz (int [][][] elementos){
        return false;
    }

    /**
     * Contructor para la clase Matriz, el cual crea una matriz a partir de una lista 3D en donde los fraccionarios se representan como:
     *  {entero} ó {numerador, denominador} ó {parteEntera, numerador, denominador}
     * 
     * @param elementos Lista de 3 dimensiones, en donde se representa la matriz de fraccionarios
     */
    public Matriz (int [][][] elementos) {
        this.filas = elementos.length;
        this.columnas = filas == 0 ? 0 : elementos[0].length; //Si no hay ninguna fila, tampoco hay columnas
        this.matriz = new Fraccionario[filas][columnas];

        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                int [] listaFraccion = elementos[i][j];
                this.matriz[i][j] = construirFraccionario(listaFraccion);
            }
        }
    }    

    /**
     * Constructor para la clase Matriz que toma como argumento una lista 2D de Fraccionarios,
     * la matriz será del mismo tamaño que la lista dada.
     * 
     * @param elementos La lista 2D de Fraccionarios.
     */
    public Matriz (Fraccionario  elementos[][]) {
        this.filas = elementos.length;
        this.columnas = filas == 0 ? 0 : elementos[0].length; //Si no hay ninguna fila, tampoco hay columnas
        this.matriz = elementos;
    }

    /**
     * Retorna una matriz dada su diagonal. 
     */    
    public Matriz (Fraccionario d []){

    }    

    /**
     * Retorna una matriz de un numero repetido dada su dimension. 
     */
    public Matriz (Fraccionario e, int f, int c) {

    }

    /**
     * Retorna una matriz identidad dada su dimension. 
     */
    public Matriz (int n){
    }

    public Matriz dimension(){
        return null;
    }

    /**
     * Retorna si es posible el fraccionario perteneciente a la posición dada de la matriz, comenzando desde cero
     * 
     * @param f La fila que se desea consultar
     * @param c La columna que se desea consultar
     * 
     * @return El fraccionario perteneciente a esa posición o null si la posición es inválida
     */
    public Fraccionario get(int f, int c){
        if (f >= this.filas || c >= this.columnas){
            return null;
        }

        return matriz[f][c];
    }

    /**
     * Crea un objeto Matriz que representa la suma de dos matrices de las mismas dimensiones:
     * esta matriz + otro. Si las dos matrices no poseen la misma dimensión, se retorna null.
     * 
     * @param m La otra matriz con la que se desea sumar esta matriz.
     * 
     * @return Objeto Matriz que representa la suma de esta matriz + m, o null si no son de las mismas dimensiónes.
     */
    public Matriz sume(Matriz m){
        if (!mismasDimensiones(m)){
            return null;
        }
        
        Fraccionario[][] elementos = new Fraccionario[this.filas][this.columnas];
        
        for (int i = 0; i < this.filas; i++){
            for (int j = 0; j < this.columnas; j++){
                elementos[i][j] = this.matriz[i][j].sume(m.get(i, j));
            }
        }
        
        return new Matriz(elementos);
    }

    /**
     * Crea un objeto Matriz que representa la resta de dos matrices de las mismas dimensiones:
     * esta matriz - otro. Si las dos matrices no poseen la misma dimensión, se retorna null.
     * 
     * @param m La otra matriz con la que se desea restar esta matriz.
     * 
     * @return Objeto Matriz que representa la restauma de esta matriz + m, o null si no son de las mismas dimensiónes.
     */
    public Matriz reste(Matriz m){
        if (!mismasDimensiones(m)){
            return null;
        }
        
        Fraccionario[][] elementos = new Fraccionario[this.filas][this.columnas];
        
        for (int i = 0; i < this.filas; i++){
            for (int j = 0; j < this.columnas; j++){
                elementos[i][j] = this.matriz[i][j].reste(m.get(i, j));
            }
        }
        
        return new Matriz(elementos);
    }
    
    /**
     * Verifica si esta Matriz es de las mismas dimensiones que otra Matriz.
     * 
     * @param otra La matriz con la que se desea comparar esta Matriz.
     * 
     * @return Si las matrices tienen o no las mismas dimensiones.
     */
    public boolean mismasDimensiones(Matriz otra){
        if (this.filas == otra.getFilas() && this.columnas == otra.getColumnas()){
            return true;
        }
        return false;
    }
    
    public int getFilas(){
        return this.filas;
    }
    
    public int getColumnas(){
        return this.columnas;
    }
    
    /**
     * Compara esta matriz con otra
     */
    public boolean equals (Matriz otra) {
        return false;
    }

    /** 
     * Compara esta matriz con otra
     */
    @Override
    public boolean equals(Object otra) {
        return false;
    }  
    
    /** 
     * Retorna una cadena con los datos de la matriz alineado a derecha por columna
     * 
     * @return La representación en cadena de la matriz
     */
    @Override
    public String toString () {
        String s = "{";
        for (int i = 0; i < matriz.length; i++){
            s += "{";
            for (int j = 0; j < matriz[i].length; j++){
                String fraccionario = matriz[i][j].toString();
                s += (j == matriz[i].length - 1) ? fraccionario : fraccionario + ",";
            }
            s += (i == matriz.length - 1) ? "}" : "},";
        }
        return s + "}";
    }

    /**
     * Elige el constructor de Fraccionario apropiado según la representación que se haya elegido hacer en la forma de lista de enteros
     * 
     * @param listaFraccion La lista que representa el fraccionario
     * 
     * @return El objeto creado de fraccionario, o null si la entrada fue inválida
     */
    private Fraccionario construirFraccionario(int [] listaFraccion){
        Fraccionario fraccionario; 
        System.out.println(listaFraccion.length);
        switch (listaFraccion.length){
            case 1:
            fraccionario = new Fraccionario(listaFraccion[0]);
            break;
            case 2:
            fraccionario = new Fraccionario(listaFraccion[0], listaFraccion[1]);
            break;
            case 3:
            fraccionario = new Fraccionario(listaFraccion[0], listaFraccion[1], listaFraccion[2]);
            break;
            default:
            fraccionario = null;
        }
        return fraccionario;
    }

}
