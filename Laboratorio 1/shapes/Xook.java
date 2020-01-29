import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a number between 0 and 19 in a symbol.
 *
 * @author Paula Guevara and Daniel Rincón
 * @version 29/01/2020
 */
public class Xook
{
    private boolean isVisible = false;
    
    private float scale = 1f;
    
    private int xPosition = 10;
    private int yPosition = 10;
    private int value;
    private int numCircles;
    private int numRectangles;
    
    private String color = "black";
    
    private ArrayList<Circle> circles;
    private ArrayList<Rectangle> rectangles;
    
    
    /**
     * Constructor for objects of class Xook
     */
    public Xook(int value)
    {
        // initialise instance variables
        this.value = value;
        
        circles = new ArrayList<Circle>();
        rectangles = new ArrayList<Rectangle>();
        
        calcNumOfShapes();
        createSymbol();
    }
    
    /**
     * Constructor for objects of class Xook
     * 
     * @param value Value of the Xook.
     * @param xPosition X coordinate of the top left corner of the Xook.
     * @param yPosition Y coordinate of the top left corner of the Xook.
     * @param scale The scale of the Xook.
     */
    public Xook(int value, int xPosition, int yPosition, double scale, String color){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.value = value;
        this.scale = (float)scale;
        this.color = color;

        circles = new ArrayList<Circle>();
        rectangles = new ArrayList<Rectangle>();
        
        calcNumOfShapes();
        createSymbol();
    }
    
    /**
     * @return The actual value of the Xook.
     */
    public int getValue(){
        return value;   
    }
    
    /**
     * Shows the Xook on the Canvas
     */
    public void makeVisible(){
        isVisible = true;
        
        for (int i = 0; i < numCircles; i++){
            circles.get(i).makeVisible();
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).makeVisible();
        }
    }
    
    /**
     * Hides the Xook on the Canvas
     */
    public void makeInvisible(){
        isVisible = false;
        
        for (int i = 0; i < numCircles; i++){
            circles.get(i).makeInvisible();
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).makeInvisible();
        }
    }
    
    /**
     * Changes the Xook Value for a random number an regenerates it
     */
    public void random(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(20); //Genera un numero aleatorio entre 0 y 19
        setValue(randomNumber);
    }
    
    /**
     * Sets the value of the Xook and regenerates it
     * 
     * @param value The new value of the Xook
     */
    public void setValue(int value){
        this.value = value;
        deleteSymbol();
        calcNumOfShapes();
        createSymbol();
    }
    
    /**
     * Moves the Xook on the X axis
     * 
     * @param distance Distance to move (-inf, inf)
     */
    public void moveHorizontal(int distance){
        xPosition += distance;
        for (int i = 0; i < numCircles; i++){
            circles.get(i).moveHorizontal(distance);
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).moveHorizontal(distance);
        }
    }
    
    public void moveVertical(int distance){
        yPosition += distance;
        for (int i = 0; i < numCircles; i++){
            circles.get(i).moveVertical(distance);
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).moveVertical(distance);
        }
    }
    
    /**
     * Changes the color of the Xook
     * 
     * @param color The new color of the Xook
     */
    public void changeColor(String color){
        this.color = color;
        for (int i = 0; i < numCircles; i++){
            circles.get(i).changeColor(color);
        }
        for (int i = 0; i < numRectangles; i++){
            rectangles.get(i).changeColor(color);
        }
    }
    
    /**
     * Changes the scale of the Xook
     * 
     * @param newScale The new scale of the Xook
     */
    public void changeScale(double newScale){
        scale = (float)newScale;
        deleteSymbol();
        createSymbol();
    }
    
    /**
     * @return width of the Xook
     */
    public int getWidth(){
        return (int)(80 * scale);
    }
    
    /**
     * Builds the Xook symbol with the actual coordinates and number of shapes.
     */
    private void createSymbol(){
        int circleDiameter = (int)(15 * scale);
        int circleSeparation = (int)(20 * scale);
        int rectangleWidth = (int)(80 * scale);
        int rectangleHeight = (int)(10 * scale);
        int rectangleSeparation = (int)(13 * scale);
        int initialXCircle = (int)(((rectangleWidth / 2) - (circleDiameter / 2) - ((numCircles - 1) * (circleSeparation / 2))) * scale);
        int initialYRectangle = (int)(20 * scale);
        
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
    
    /**
     * Deletes the symbol for making a new one
     */
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
    
    /**
     * Calculates the number of Circles and rectangles on the Xook
     */
    private void calcNumOfShapes(){
        numCircles = value % 5;
        numRectangles = value / 5;
    }
}
