import java.util.ArrayList;

/**
 * Class for drawing basic operators (+, -, *, /).
 * 
 * @author Paula Guevara and Daniel Rincón
 * @version 29/01/2020
 */
public class Operator
{
    // instance variables - replace the example below with your own
    private int xPosition;
    private int yPosition;
    private int size;
    private int frameSize;
    
    private String color;
    private String operator;
    
    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private ArrayList<Circle> circles = new ArrayList<Circle>();

    /**
     * Constructor for objects of class Operator
     * 
     * @param operator The operator to be represented
     * @param color The color of the operator
     * @param xPosition The position in the X axis of the top left corner of the operator
     * @param yPosition The position in the Y axis of the top left corner of the operator
     * @param size The size (x and y) of the operator
     */
    public Operator(String operator, String color, int xPosition, int yPosition, int size)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        this.frameSize = (int)(size / 3.5);
        this.color = color;
        this.operator = operator;
        
        makeVisible();
    }
    
    /**
     * Erases the symbol from de canvas and clears all data structures
     */
    public void delete(){
        for (int i = 0; i < rectangles.size(); i++){
            rectangles.get(i).makeInvisible();
        }
        for (int i = 0; i < circles.size(); i++){
            circles.get(i).makeInvisible();
        }
        rectangles.clear();
        circles.clear();
    }
    
    /**
     * Changes the color of the operator
     * 
     * @param color The new color of the operator
     */
    public void changeColor(String color){
        for (int i = 0; i < rectangles.size(); i++){
            rectangles.get(i).changeColor(color);
        }
        for (int i = 0; i < circles.size(); i++){
            circles.get(i).changeColor(color);
        }
    }
    /**
     * Returns the String representation of the operator
     * 
     * @return The string representation of the operator.
     */
    public String getOperator(){
        return operator;
    }
    
    /**
     * Changes the size (x and y) of the operator
     * 
     * @param newSize The new size of the operator
     */
    public void changeSize(int newSize){
        size = newSize;
        frameSize = (int)(size / 3.5);
        delete();
        makeVisible();
    }
    
    /**
     * Changes the position on te X axis of the top left corner of the operator
     * 
     * @param newPosition The new position of the top left corner of the operator
     */
    public void changeXPosition(int newPosition){
        xPosition = newPosition;
        delete();
        makeVisible();
    }
    
    /**
     * Creates a + symbol
     */
    private void createPlus(){
        int squareLongSize = size - 2 * frameSize;
        int squareShortSize = squareLongSize / 3;
        int shortOffset = frameSize;
        int longOffset = frameSize + squareLongSize / 2 - squareShortSize / 2;
        
        rectangles.add(new Rectangle(xPosition + shortOffset, yPosition + longOffset, squareLongSize, squareShortSize, color));
        rectangles.add(new Rectangle(xPosition + longOffset, yPosition + shortOffset, squareShortSize, squareLongSize, color));
    }
    
    /**
     * Creates a - symbol
     */
    private void createMinus(){
        int squareLongSize = size - 2 * frameSize;
        int squareShortSize = squareLongSize / 3;
        int shortOffset = frameSize;
        int longOffset = frameSize + squareLongSize / 2 - squareShortSize / 2;
        
        rectangles.add(new Rectangle(xPosition + shortOffset, yPosition + longOffset, squareLongSize, squareShortSize, color));
    }
    
    /**
     * Creates a * symbol
     */
    private void createMult(){
        int offset = (int)(frameSize * 1.5);
        int diameter = (int)(size - 2 * 1.5 * frameSize);
        
        circles.add(new Circle(xPosition + offset, yPosition + offset, diameter, color));
    }
    
    /**
     * Creates a / symbol
     */
    private void createDiv(){
        int offset = (int)((size - 2 * frameSize) / 2.4f);
        createMinus();
        for (int i = 0; i < 2; i++){
            createMult();
        }
        circles.get(0).moveVertical(offset);
        circles.get(1).moveVertical(-offset);
    }

    /**
     * Draws the symbol on the canvas at the given position with the given size.
     */
    private void makeVisible(){
        switch (operator){
            case "+":
                createPlus();
                break;
            case "-":
                createMinus();
                break;
            case "*":
                createMult();
                break;
            case "/":
                createDiv();
                break;
        }
        
        for (int i = 0; i < rectangles.size(); i++){
            rectangles.get(i).makeVisible();
        }
        for (int i = 0; i < circles.size(); i++){
            circles.get(i).makeVisible();
        }
    }
}
