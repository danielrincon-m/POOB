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
    private int numCircles;
    private int numRectangles;
    
    private ArrayList<Circle> circles;
    private ArrayList<Rectangle> rectangles;
    
    
    /**
     * Constructor for objects of class Xook
     */
    public Xook()
    {
        // initialise instance variables
        circles = new ArrayList<Circle>();
        rectangles = new ArrayList<Rectangle>();
        
        value = 0;
        numCircles = 4;
        numRectangles = 2;        
    }    
    
    public void makeVisible(){
        int xInicial = 10;
        int yInicial = 50;
        
        for (int i = 0; i < numCircles; i++){
            Circle c = new Circle();
            circles.add(c);
            c.moveHorizontal(xInicial + 50 * i);
            c.moveVertical(20);
            c.makeVisible();
        }
        
        for (int i = 0; i < numRectangles; i++){
            Rectangle r= new Rectangle();
            rectangles.add(r);
            r.moveVertical(yInicial + 20 * i);
            r.moveHorizontal(30);
            r.makeVisible();
        }
    }
    
    public int getValue(){
        return value;   
    }
    
}
