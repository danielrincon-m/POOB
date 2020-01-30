import java.util.LinkedList;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Calculator with Xook symbols.
 * 
 * @author Paula Guevara and Daniel Rincón 
 * @version 29/01/2020
 */
public class CalcXook
{
    // instance variables - replace the example below with your own
    private int turn = 0; //0 -> number turn, 1-> operator turn
    private int initialXPosition;
    private int xPosition;
    private int minNumberOfChars = 3;
    private int maxNumberOfChars = 5;
    private int numberOfChars = 3;
    private int fontSize;
    
    private String color = "black";
    
    private LinkedList<Xook> xookQueue = new LinkedList<>();
    
    private LinkedList<Operator> operatorQueue = new LinkedList<>();
    
    /**
     * Constructor for objects of class CalcXook
     */
    public CalcXook(){
        calcInitialValues();
    }
    
    /**
     * Constructor for objects of class CalcXook
     * 
     * @param color The color of the font
     */
    public CalcXook(String color)
    {
        // initialise instance variables
        this.color = color;
        calcInitialValues();   
    }

    /**
     * Creates a new number in the calculator if possible
     * 
     * @param number the number to queue
     */
    public void setNumber(int number)
    {
        // put your code here
        if (turn == 1){
            JOptionPane.showMessageDialog(null, "Debe definir un operador");
            return;
        }
        switchTurn();
        
        Xook newXook = new Xook(number, xPosition, 90, fontSize, color);
        xPosition += fontSize;
        newXook.makeVisible();
        xookQueue.add(newXook);
        reDraw();
    }
    
    /**
     * Creates a new operator if possible
     * 
     * @param operator The operator that must be in (+, -, * , /).
     */
    public void setOperator(String operator){
        if (turn == 0){
            JOptionPane.showMessageDialog(null, "Debe definir un operando u obtener un resultado");
            return;
        }
        if (operator != "+" && operator != "-" && operator != "*" && operator != "/"){
            JOptionPane.showMessageDialog(null, "El operador no es válido");
            return;
        }
        switchTurn();
        
        Operator newOperator = new Operator(operator, color, xPosition, 90, fontSize);
        xPosition += fontSize;
        operatorQueue.add(newOperator);
        reDraw();
    }
    
    /**
     * Solves the actual operation in the calculator and enques the answer
     */
    public void operate(){
        if (xookQueue.size() < 2){
            JOptionPane.showMessageDialog(null, "Deben haber al menos dos operandos");
            return;
        }
        int iterations = operatorQueue.size();
        int total = 0;
        for (int i = 0; i < iterations; i++){
            Operator operator = operatorQueue.poll();
            Xook xook1 = xookQueue.poll();
            Xook xook2 = xookQueue.poll();            
            switch (operator.getOperator()){
                case "+":
                    total = xook1.getValue() + xook2.getValue();
                    break;
                case "-":
                    total = xook1.getValue() - xook2.getValue();
                    break;
                case "*":
                    total = xook1.getValue() * xook2.getValue();
                    break;
                case "/":
                    total = xook1.getValue() / xook2.getValue();
                    break;
            }
            operator.delete();
            xook1.makeInvisible();
            xook2.makeInvisible();
            xook1.setValue(total);
            xookQueue.addFirst(xook1);
        }
        
        reset();       
        setNumber(total);
        reDraw();
    }
    
    /**
     * Changes the font color of the calculator.
     * 
     * @param color The new color of the font.
     */
    public void changeColor(String color){
        this.color = color;
        
        xookQueue.forEach((xook) -> xook.changeColor(color));
        operatorQueue.forEach((operator) -> operator.changeColor(color));
    }
    
    /**
     * Clears the calculator and starts over.
     */
    public void clear(){
        reset();
    }
    
    /**
     * Changes the maximum number of operators on the screen (between 0 and 9).
     * 
     * @param number The maximum number of operators to bw shown.
     */
    public void changeVisibleOperators(int number){
        if (number < 3 || number > 9){
            JOptionPane.showMessageDialog(null, "El numero máximo de operandos a mostrar debe estar entre 3 y 9");
            return;
        }
        maxNumberOfChars = number;
        reDraw();
    }
    
    /**
     * Changes the size of the font depending on the number of symbols on screen and re-draws everything.
     */
    private void reDraw(){
        int n = xookQueue.size() + operatorQueue.size();
        if (n > maxNumberOfChars){
            return;
        }
        numberOfChars = n;
        if (n < minNumberOfChars){
            numberOfChars = minNumberOfChars;
        }
        calcInitialValues();
        for (int i = 0; i < n; i++){
            int index = i / 2;
            if (i % 2 == 0){
                xookQueue.get(index).changeWidth(fontSize);
                xookQueue.get(index).changeXPosition(xPosition);
            }else{
                operatorQueue.get(index).changeSize(fontSize);
                operatorQueue.get(index).changeXPosition(xPosition);
            }
            xPosition += fontSize;
        }
    }
    
    /**
     * Change the turn from number to operator and viceversa.
     */
    private void switchTurn(){
        turn = (turn + 1) % 2;
    }
    
    /**
     * Clear the screen and all data structures
     */
    private void reset(){
        xookQueue.forEach((xook) -> xook.makeInvisible());
        xookQueue.clear();
        operatorQueue.forEach((operator) -> operator.delete());
        operatorQueue.clear();
        xPosition = initialXPosition;
        turn = 0;
    }
    
    /**
     * Calculates the fontSize and initial x position for all the fonts.
     */
    private void calcInitialValues(){
        fontSize = 270 / numberOfChars;
        initialXPosition = fontSize / 6;
        xPosition = initialXPosition;
    }
}
