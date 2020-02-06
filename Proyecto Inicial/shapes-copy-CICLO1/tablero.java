import java.util.ArrayList;
import java.awt.*;
/**
 * Write a description of class tablero here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class tablero
{
    // instance variables - replace the example below with your own
    private int width;
    private Rectangle [][] tablero1;
    private Rectangle [][] tablero2;
    private boolean isVisible=false;
    private boolean estado;
    private int size;
    private ArrayList<Rectangle> rectangles;
    /**
     * Constructor for objects of class tablero
     */
    public tablero(int width)
    {
         // initialise instance variable
         this.width= width-1;
         tablero1 = new Rectangle[width][width];
         tablero2 = new Rectangle[width][width];
         isVisible=estado=false;
         size=50;
         int bandera=0;
         Canvas canvas = Canvas.getCanvas();
         for (int i = 0; i < width; i++){
            for (int j = 0; j < width; j++){
                    tablero1[i][j] = new Rectangle((10+(size+1)*i),10+((size+1)*j),size);
                    tablero2[i][j] = new Rectangle( ((width*(size+1))+200)+((size+1)*i) , (10+(size+1)*j) , size );
                    if (bandera==0){
                        tablero1[i][j].changeColor("white");
                        tablero2[i][j].changeColor("white");
                        bandera=1;
                    }
                    else{
                        tablero1[i][j].changeColor("black");
                        tablero1[i][j].changeColor("black");
                        bandera=0;
                    }
            }
            if (bandera==0){
                        bandera=1;
            }
            else{
                        bandera=0;
            }
         }
         
    }
    public void makeVisible(){
     for (int i = 0; i < width; i++){
           for (int j = 0; j < width; j++){
                tablero1[i][j].makeVisible();
                tablero2[i][j].makeVisible();
           }
           
     }
   
    }
 }
  

