/** Calculadora.java
 * Representa una calculadora de matrices de fraccionarios
 * @author ESCUELA 2018-01
 */

import java.util.HashMap;
public class Calmatfra{

    private HashMap<String, Matriz> variables;
    //Consultar en el API Java la clase HashMap

    public Calmatfra(){
        this.variables = new HashMap<String, Matriz>();
    }

    /**
     * Crea una matriz a partir de un arreglo 3D de enteros y la almacena con la llave dada.
     * 
     * @param varibale La llave para almacenar y acceder a la matriz.
     * 
     * @param matrizRepresentada La representación en un arreglo 3D de la matriz,
     * cada Fraccionario se representa como {entero} ó {numerador, denominador} ó {parteEntera, numerador, denominador}.
     */
    public void asigne(String variable, int [][][] matrizRepresentada){
        Matriz matriz = new Matriz(matrizRepresentada);
        variables.put(variable, matriz);
    }

    // Los operadores binarios : + (suma), - (resta), . (multiplique elemento a elemento), * (multiplique matricial)
    /**
     * Se realiza la operación solicitada sobre las matrices dadas y se 
     * guarda el resultado con la llave especificada, si alguna de las
     * matrices a operar no existe, el resultado será nulo.
     * 
     * @param respuesta La llave que se usará para acceder a la respuesta.
     * @param operando1 La primera matriz a operar.
     * @param operacion La operación que se realizará, estas pueden ser:
     *  + (suma), - (resta), . (multiplique elemento a elemento), * (multiplique matricial).
     * @param operando2 La segunda matriz a operar.
     */
    public void opere(String respuesta, String operando1, char operacion, String operando2){
        Matriz matriz1 = variables.get(operando1);
        Matriz matriz2 = variables.get(operando2);
        Matriz matrizRespuesta = null;
        
        //Las matrices deben existir en el HashMap
        if (matriz1 == null || matriz2 == null){
            return;
        }
        
        switch (operacion){
            case '+':
                matrizRespuesta = matriz1.sume(matriz2);
                break;
            
            case '-':
                matrizRespuesta = matriz1.reste(matriz2);
                break;
                
            case '.':
                matrizRespuesta = matriz1.multiplique(matriz2);
                break;
                
            case '*':
                matrizRespuesta = matriz1.productoMatricial(matriz2);
                break;
        }
        variables.put(respuesta, matrizRespuesta);
    }
    
    /**
     * Retorna una representación tipo String de la matriz almacenada en la llave dada,
     * si no hay ninguna matriz asociada a la llave, retorna null.
     * 
     * @param variable La llave usada para acceder a la matriz.
     * 
     * @return Representación tipo String de la matriz correspondiente a la llave o null.
     */
    public String consulta(String variable){
        if (!variables.containsKey(variable)){
            return null;
        }
        return variables.get(variable).toString();
    }

    public boolean ok(){
        return false;
    }
}

