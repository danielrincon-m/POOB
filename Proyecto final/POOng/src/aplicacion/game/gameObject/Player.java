package aplicacion.game.gameObject;

public class Player extends GameObject {
    private float loweLimit;
    private float higherLimit;
    private float speed = 1f;

    public Player(float xPosition, float yPosition, float width, float height) {
        super(xPosition, yPosition, width, height);
    }

    @Override
    public void update() {

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
