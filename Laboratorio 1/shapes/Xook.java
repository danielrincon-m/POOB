import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Xook here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Xook
{
    // instance variables - replace the example below with your own
    private boolean isVisible;
    
    private int xPosition;
    private int yPosition;
    private int value;
    private int numCircles;
    private int numRectangles;
    
    private String color;
    
    private ArrayList<Circle> circles;
    private ArrayList<Rectangle> rectangles;
    
    
    /**
     * Constructor for objects of class Xook
     */
    public Xook()
    {
        // initialise instance variables
        isVisible = false;
        
        xPosition = 10;
        yPosition = 10;
        value = 0;
        numCircles = 0;
        numRectangles = 0;
        
        color = "black";
        
        circles = new ArrayList<Circle>();
        rectangles = new ArrayList<Rectangle>();
        
        createSymbol();
    }
    
    public int getValue(){
        return value;   
    }
    
    public void makeVisible(){
        isVisible = true;
        
        for (int i = 0; i < numCircles; i++){
            circles.get(i).makeVisible();
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).makeVisible();
        }
    }
    
    public void makeInvisible(){
        isVisible = false;
        
        for (int i = 0; i < numCircles; i++){
            circles.get(i).makeInvisible();
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).makeInvisible();
        }
    }
    
    public void random(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(20); //Genera un numero aleatorio entre 0 y 19
        setValue(randomNumber);
    }
    
    public void setValue(int value){
        this.value = value;
        deleteSymbol();
        calcNumOfShapes();
        createSymbol();
    }
    
    public void moveHorizontal(int distance){
        xPosition += distance;
        for (int i = 0; i < numCircles; i++){
            circles.get(i).moveHorizontal(distance);
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).moveHorizontal(distance);
        }
    }
    
    public void changeColor(String color){
        this.color = color;
        for (int i = 0; i < numCircles; i++){
            circles.get(i).changeColor(color);
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).changeColor(color);
        }
    }
    
    private void createSymbol(){
        int circleDiameter = 15;
        int circleSeparation = 20;
        int rectangleWidth = 80;
        int rectangleHeight = 10;
        int rectangleSeparation = 13;
        int initialXCircle = (rectangleWidth / 2) - (circleDiameter / 2) - ((numCircles - 1) * (circleSeparation / 2));
        int initialYRectangle = 20;
        
        for (int i = 0; i < numCircles; i++){
            Circle c = new Circle(xPosition + initialXCircle + circleSeparation * i, 
                                  yPosition, 
                                  circleDiameter, 
                                  color);
            circles.add(c);
        }
        for (int i = 0; i < numRectangles; i++){
            Rectangle r = new Rectangle(xPosition, 
                                        yPosition + initialYRectangle + rectangleSeparation * i, 
                                        rectangleWidth, 
                                        rectangleHeight, 
                                        color);
            rectangles.add(r);
        }
        
        if (isVisible){
            makeVisible();
        }
    }
    
    private void deleteSymbol(){
        for (int i = 0; i < numCircles; i++){
            circles.get(i).makeInvisible();
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).makeInvisible();
        }
        circles.clear();
        rectangles.clear();
    }
    
    private void calcNumOfShapes(){
        numCircles = value % 5;
        numRectangles = value / 5;
    }
}
