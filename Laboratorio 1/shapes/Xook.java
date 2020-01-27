import java.util.ArrayList;

/**
 * Write a description of class Xook here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Xook
{
    // instance variables - replace the example below with your own
    private int value;
    private int totalRectangles;
    private int totalCircles;
    
    private ArrayList<Circle> circles;
    private ArrayList<Rectangle> rectangles;

    /**
     * Constructor for objects of class Xook
     */
    public Xook()
    {
        // initialise instance variables
        value = 0;
        totalRectangles = 0;
        totalCircles = 0;
    }

    /**
     * Returns the value
     *
     * @return the value
     */
    public int getValue()
    {
        return value;
    }
    
    public void makeVisible(){
        Circle circle = new Circle();
        circle.makeVisible();
        circle.moveHorizontal(135);
        circle.moveVertical(120);
    }
    
    private void ubircarCirculos(){
        
    }
}
