import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle{

    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        height = 10;
        width = 120;
        xPosition = 0;
        yPosition = 0;
        color = "magenta";
        isVisible = false;
    }
    
    /**
     * Create a new rectangle with given size at given position with given color
     * @param xPosition position in x axis
     * @param yPosition position in y axis
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param color color of the rectangle
     */
    public Rectangle(int xPosition, int yPosition, int width, int height, String color) {
        this.height = height;
        this.width = width;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        isVisible = false;
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    /**
     * El rectangulo parpadea.
     * @param times El numero de veces que parpadea
     */
    public void blick(int times){
        for (int i = 0; i < times; i++){
            erase();
            draw();
        }
    }
    
    /**
     * Retorna el perimetro del rectángulo
     * 
     * @return perímetro del rectángulo.
     */
    public int perimeter() {
        return width * 2 + height * 2;
    }
    
    /**
     * Rota el rectangulo 90 grados a la derecha y lo traslada
     */
    public void rotate() {
        for (int i = 0; i < 5; i++) {
            int anchoTemporal = width;
            width = height;
            height = anchoTemporal;
            xPosition += anchoTemporal;
            draw();
        }
    }

    /*
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, width, height));
            canvas.wait(50);
        }
    }

    /*
     * Erase the rectangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
            canvas.wait(50);
        }
    }
}

