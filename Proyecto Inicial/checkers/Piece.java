
/**
 * Write a description of class Piece here.
 * 
 * @author Paula Guevara & Daniel Rincón 
 * @version 04-02-2020
 */
public class Piece
{
    boolean isKing;
    boolean isWhite;
    
    float framePercentage = 0.2f;
    float crownPercentage = 0.1f;
    
    int xPosition = 0;
    int yPosition = 0;
    int size = 10;
    int circleXPosition;
    int circleYPosition;
    int circleSize;
    int crownXPosition;
    int crownYPosition;
    int crownSize;
    
    String circleColor;
    String crownColor;
    
    Circle circle;
    Circle crown;
    
    public Piece(boolean isKing, boolean isWhite, int xPosition, int yPosition, int size){        
        this.isKing = isKing;
        this.isWhite = isWhite;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        
        setColors();
        draw();
    }
    
    public void move(int newPositionX, int newPositionY){
        xPosition = newPositionX;
        yPosition = newPositionY;
        calculateCircleData();
        
        circle.setPosition(circleXPosition, circleYPosition);
    }
    
    public void setSelected(boolean selected){
        if (selected)
            circle.changeColor("orange");
        else
            circle.changeColor(circleColor);
        
    }
    
    private void calculateCircleData(){
        circleXPosition = xPosition + (int)(size * framePercentage / 2f);
        circleYPosition = yPosition + (int)(size * framePercentage / 2f);
        circleSize = size - (int)(size * framePercentage);
        crownSize = (int)(size * crownPercentage);
        crownXPosition = circleXPosition + (circleSize / 2) - (crownSize / 2);
        crownYPosition = circleYPosition;
    }
    
    private void draw(){
        if (circle != null)
            return;

        calculateCircleData();
        
        circle = new Circle(circleSize, circleXPosition, circleYPosition, circleColor);
        circle.makeVisible();
        
        if (isKing){
            crown = new Circle(crownSize, crownXPosition, crownYPosition, crownColor);
            crown.makeVisible();
        }
    }
    
    private void setColors(){
        if (isWhite)
            circleColor = "white";
        else
            circleColor = "black";
        crownColor = "red";
    }
}
