
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
    
    int xPosition = 0;
    int yPosition = 0;
    int size = 10;
    int circleXPosition;
    int circleYPosition;
    int circleSize;
    
    String color;
    
    Circle circle;
    
    public Piece(boolean isKing, boolean isWhite, int xPosition, int yPosition, int size){        
        this.isKing = isKing;
        this.isWhite = isWhite;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.size = size;
        
        setColor();
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
            circle.changeColor(color);
        
    }
    
    private void calculateCircleData(){
        circleXPosition = xPosition + (int)(size * framePercentage / 2f);
        circleYPosition = yPosition + (int)(size * framePercentage / 2f);
        circleSize = size - (int)(size * framePercentage);
    }
    
    private void draw(){
        if (circle != null)
            return;

        calculateCircleData();
        
        circle = new Circle(circleSize, circleXPosition, circleYPosition, color);
        circle.makeVisible();
    }
    
    private void setColor(){
        if (isWhite)
            color = "white";
        else
            color = "black";
    }
}
