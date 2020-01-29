import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Write a description of class CalcXook here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CalcXook
{
    // instance variables - replace the example below with your own
    private int turn = 0; //0 -> number turn, 1-> operator turn
    private int initialXPosition = 10;
    private int xPosition;
    
    private String color;
    
    private LinkedList<Integer> numberQueue = new LinkedList<>();
    
    private Operator operator;
    
    private ArrayList<Xook> xookList = new ArrayList<Xook>();
    
    /**
     * Constructor for objects of class CalcXook
     */
    public CalcXook(String color)
    {
        // initialise instance variables
        this.color = color;
        xPosition = initialXPosition;        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void setNumber(int number)
    {
        // put your code here
        if (turn == 1){
            return;
        }
        switchTurn();
        
        numberQueue.addLast(number);
        
        Xook newXook = new Xook(number, xPosition, 90, 1, color);
        xPosition += newXook.getWidth();
        newXook.makeVisible();
        xookList.add(newXook);
    }
    
    public void setOperator(String operator){
        if (turn == 0 || numberQueue.size() > 1){
            return;
        }
        if (operator != "+" && operator != "-" && operator != "*" && operator != "/"){
            return;
        }
        switchTurn();
        
        int operatorSize = 80;
        
        this.operator = new Operator(operator, color, xPosition, 90, operatorSize, 25);
        xPosition += operatorSize;
    }
    
    public void operate(){
        if (numberQueue.size() < 2){
            return;
        }
        int total = 0;
        
        switch (operator.getOperator()){
            case "+":
                total = numberQueue.poll() + numberQueue.poll();
                break;
            case "-":
                total = numberQueue.poll() - numberQueue.poll();
                break;
            case "*":
                total = numberQueue.poll() * numberQueue.poll();
                break;
            case "/":
                total = numberQueue.poll() / numberQueue.poll();
                break;
        }
        
        reset();       
        setNumber(total);
    }
    
    public void changeColor(String color){
        this.color = color;
        
        xookList.forEach((xook) -> xook.changeColor(color));
        operator.changeColor(color);
    }
    
    private void switchTurn(){
        turn = (turn + 1) % 2;
    }
    
    private void reset(){
        for (int i = 0; i < xookList.size(); i++){
            xookList.get(i).makeInvisible();
        }
        xookList.clear();
        numberQueue.clear();
        operator.delete();
        operator = null;
        xPosition = initialXPosition;
        turn = 0;
    }
}
