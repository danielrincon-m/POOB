
/**
 * Fraccionario
 * Esta clase implementa el tipo de dato Fraccionario; es decir, un n�mero racional que se pueden escribir de la forma p/q, donde p y q son enteros, con q <> 0
 * La implemantacion se hace mediante objetos inmutables
 * INV: El fraccionario se encuentra representado en forma irreductible.
 * @author E.C.I.
 *
 */
public class Fraccionario {

    private int numerador;
    private int denominador;

    /**Calcula el maximo comun divisor de dos enteros
     * Lo implementaremos mediante el algoritmo recursivo
     * @param a primer entero
     * @param b segundo entero
     * @return el Maximo Comun Divisor de a y b
     */
    public static int mcd(int a,int b){
        if(b == 0){
            return Math.abs(a);
        }else{
            return mcd(b, a % b);
        }
    }    

    /**Crea un nuevo fraccionario, dado el numerador y el denominador
     * @param numerador
     * @param denominador. denominador <> 0
     */
    public Fraccionario (int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador= denominador;
        simplificar();
    }

    /**Crea un fraccionario correspondiente a un entero
     * @param entero el entero a crear
     */
    public Fraccionario (int entero) {
        this.numerador = entero;
        this.denominador = 1;
    }

    /**Crea un fraccionario, a partir de su representacion mixta. 
     * El numero creado es enteroMixto + numeradorMixto/denominadorMixto
     * @param enteroMixto la parte entera del numero
     * @param numeradorMixto el numerador de la parte fraccionaria
     * @param denominadorMixto el denominador de la parte fraccionaria. denominadorMixto<>0
     */
    public Fraccionario (int enteroMixto, int numeradorMixto, int denominadorMixto) {
        this(enteroMixto * denominadorMixto + numeradorMixto, denominadorMixto);
    }

    /**
     * Un fraccionario p/q esta escrito en forma simplificada si q es un entero positivo y 
     * el maximo comun divisor de p y q es 1.
     * 
     * @return El numerador simplificado del fraccionario
     */
    public int numerador() {
        return numerador;
    }

    /**
     * Un fraccionario p/q esta escrito en forma simplificada si q es un entero Positivo y 
     * el maximo comun divisor de p y q es 1.
     * 
     * @return el denominador simplificado del fraccionario
     */
    public int denominador() {
        return denominador;
    }

    /**
     * Suma este fraccionario con otro fraccionario
     * 
     * @param otro es otro fraccionario
     * 
     * @return este+otro
     */
    public Fraccionario sume (Fraccionario otro) {
        int numerador = this.numerador * otro.denominador() + otro.numerador() * this.denominador;
        int denominador = this.denominador * otro.denominador;
        
        return new Fraccionario(numerador, denominador);
    }

    /**
     * Resta este fraccionario con otro fraccionario
     * 
     * @param otro es otro fraccionario
     * 
     * @return este-otro
     */
    public Fraccionario reste (Fraccionario otro) {
        int numerador = this.numerador * otro.denominador() - otro.numerador() * this.denominador;
        int denominador = this.denominador * otro.denominador;
        
        return new Fraccionario(numerador, denominador);
    }

    /**
     * Multiplica este fraccionario con otro fraccionario
     * 
     * @param otro El otro fraccionario
     * 
     * @return este * otro
     */
    public Fraccionario multiplique (Fraccionario otro) {
        return null;
    }

    /**
     * Divide este fraccionario sobre otro fraccionario
     * 
     * @param otro El otro fraccionario
     * 
     * @return este * otro
     */
    public Fraccionario divida (Fraccionario otro) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return equals((Fraccionario)obj);
    }    

    /**Compara este fraccionario con otro fraccionario
     * @param otro eL otro fraccionario
     * @return true si este fraccionario es igual matem�ticamente al otro fraccionario, False d.l.c.
     */
    public boolean equals (Fraccionario otro) {
        if (otro.numerador() == this.numerador && otro.denominador() == this.denominador){
            return true;
        }
        return false;
    }

    /** Calcula la representacion en cadena de un fraccionario en formato mixto simplificado
     * @see java.lang.Object#toString(java.lang.Object)
     */
    @Override
    public String toString() {
        return Integer.toString(numerador) + "/" + Integer.toString(denominador);
    }

    /**
     * Simplifica el fraccionario actual haciendo uso del máximo común divisor
     */
    private void simplificar(){
        boolean esNegativo;
        int maxComunDivisor = mcd(numerador, denominador);

        //Encuentra la mínima simplificación del fraccionario (cuando el mcd es 1)
        while(maxComunDivisor > 1){
            numerador /= maxComunDivisor;
            denominador /= maxComunDivisor;
            maxComunDivisor = mcd(numerador, denominador);
        }

        //Realiza la verificación de los signos, y pone únicamente el numerador como negativo si es necesario
        esNegativo = (numerador < 0) ^ (denominador < 0); //XOR si uno de los dos es negativo, el fraccionario es negativo, si ninguno o ambos son negativos, el fraccionario es positivo
        if (esNegativo && numerador > 0){
            numerador = -numerador;
        }else if (!esNegativo){
            numerador = Math.abs(numerador);
        }
        denominador = Math.abs(denominador);
    }
}
