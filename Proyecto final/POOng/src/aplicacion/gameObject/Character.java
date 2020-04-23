package aplicacion.gameObject;

import aplicacion.collision.RectangleCollider;

public class Character extends GameObject {
    private float loweLimit;
    private float higherLimit;
    private float speed;

    public Character(float xPosition, float yPosition, float width, float height, float speed) {
        super(xPosition, yPosition, width, height);
        this.speed = speed;
        createCollider();
    }

    public void setLimits(float lowerLimit, float higherLimit) {
        this.loweLimit = lowerLimit;
        this.higherLimit = higherLimit;
    }

    public void moveHorizontal(int direction) {
        if (direction == -1 || direction == 1) {
            float newPosition = xPosition + speed * direction;
            xPosition = direction == -1 ? Math.max(loweLimit, newPosition) : Math.min(higherLimit, newPosition);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
