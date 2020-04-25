package aplicacion.game.gameObject;

import aplicacion.Manager;
import aplicacion.game.GameManager;

public abstract class GameObject {

    protected GameManager gameManager;
    protected float xPosition;
    protected float yPosition;
    protected float width;
    protected float height;

    public GameObject(float xPosition, float yPosition, float width, float height) {
        gameManager = GameManager.getInstance();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
    }

    public abstract void update();

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String toString() {
        return "" + xPosition + "-" + yPosition + "--" + width + "-" + height;
    }
}
