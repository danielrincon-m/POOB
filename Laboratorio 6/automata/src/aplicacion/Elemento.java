package aplicacion;

import java.awt.*;
import java.io.Serializable;

public interface Elemento extends Serializable {
  int REDONDA = 1;
  int CUADRADA = 2;
  
  /**
   * Método que decide que hacer en el siguiente paso de tiempo
   *
   */
  default void decida(){
  };
   
  /**
   * Método que cmabia el estao del elemento según la decisión tomada
   *
   */
  default void cambie(){
  };
  
  /**
   * Método para obtener la forma del elemento
   *
   * @return La forma del elemento
   */
  int getForma();
  
  /**
   * Método para obtener el color del elemento
   *
   * @return El color del elemento
   */
  Color getColor();
  
  /**
   * Método para conocer si un elemento está vivo
   *
   * @return Si el elemento está vivo
   */
  default boolean isVivo(){
      return false;
  }
  
}
