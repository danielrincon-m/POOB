package aplicacion.game.entities;

import aplicacion.game.enums.FieldSide;
import aplicacion.game.utils.Vector2;

public class Field extends Entity {

    private Ball ball;

    public Field(String name, float xPosition, float yPosition, float width, float height) {
        super(name, xPosition, yPosition, width, height);
    }

    @Override
    protected void start() {
        ball = (Ball) Entity.find("BALL");
    }

    @Override
    protected void update() {
    }

    /*
    FIXME: Que la bolita salga un poco mas hacia los lados para detectar que esta afuera,
        y que en los verticales salga de la pantalla
     */
    public boolean insideField(Vector2 otherPosition) {
        return otherPosition.x >= transform.getPosition().x &&
                otherPosition.x <= transform.getPosition().x + transform.getSize().x &&
                otherPosition.y >= transform.getPosition().y &&
                otherPosition.y <= transform.getPosition().y + transform.getSize().y;
    }

    /*
      FIXME: Esta vaina es mas compleja jajaja :(
       Creo que si sobrepasa los límites de los jugadores hacia atras, el jugador al que le hicieron score pierde
       si sobrepasa un limite lateral, el ultimo jugador que le dio pierde.
    */
    public FieldSide whoScores() {
        if (ball.transform.getPosition().y < transform.getCenterPosition().y){
            return FieldSide.BOTTOM;
        }else {
            return FieldSide.TOP;
        }
    }

    public float getLeftBound() {
        return transform.getPosition().x;
    }

    public float getRightBound() {
        return transform.getPosition().x + transform.getSize().x;
    }

}
