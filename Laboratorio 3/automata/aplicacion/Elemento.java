package aplicacion;
import java.awt.Color;

public interface Elemento{
  int REDONDA = 1;
  int CUADRADA = 2;
  
  default void decida(){
  };
   
  default void cambie(){
  };
  
  default int getForma(){
      return REDONDA;
  }
  
  Color getColor();
  
  default boolean isVivo(){
      return false;
  }
  
}
