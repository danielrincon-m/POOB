import java.util.ArrayList;

/**
 * Write a description of class Operator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
     */
    public Operator(String operator, String color, int xPosition, int yPosition, int size, int frameSize)
    {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        this.frameSize = frameSize;
        this.color = color;
        this.operator = operator;
        
        makeVisible();
    }
    
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
    
    public void changeColor(String color){
        for (int i = 0; i < rectangles.size(); i++){
            rectangles.get(i).changeColor(color);
        }
        for (int i = 0; i < circles.size(); i++){
            circles.get(i).changeColor(color);
        }
    }
    
    public String getOperator(){
        return operator;
    }
    
    private void createPlus(){
        int squareLongSize = size - 2 * frameSize;
        int squareShortSize = squareLongSize / 3;
        int shortOffset = frameSize;
        int longOffset = frameSize + squareLongSize / 2 - squareShortSize / 2;
        
        rectangles.add(new Rectangle(xPosition + shortOffset, yPosition + longOffset, squareLongSize, squareShortSize, color));
        rectangles.add(new Rectangle(xPosition + longOffset, yPosition + shortOffset, squareShortSize, squareLongSize, color));
    }
    
    private void createMinus(){
        int squareLongSize = size - 2 * frameSize;
        int squareShortSize = squareLongSize / 3;
        int shortOffset = frameSize;
        int longOffset = frameSize + squareLongSize / 2 - squareShortSize / 2;
        
        rectangles.add(new Rectangle(xPosition + shortOffset, yPosition + longOffset, squareLongSize, squareShortSize, color));
    }
    
    private void createMult(){
        int offset = frameSize * 2;
        int diameter = size - 4 * frameSize;
        
        circles.add(new Circle(xPosition + offset, yPosition + offset, diameter, color));
    }
    
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
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
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
