package aplicacion.game.components;

import aplicacion.game.utils.Vector2;

public class Transform implements Component{
    private Vector2 centerPosition = new Vector2();
    private Vector2 position = new Vector2();
    private Vector2 size = new Vector2();

    public Transform(Vector2 position, Vector2 size){
        setPosition(position);
        setSize(size);
    }

    @Override
    public void start() {}

    @Override
    public void update() {}

    public void setPosition(Vector2 position) {
        this.position = position;
        centerPosition = new Vector2(position.x + size.x / 2f, position.y + size.y / 2f);
    }

    public void setSize(Vector2 size) {
        this.size = size;
        centerPosition = new Vector2(position.x + size.x / 2f, position.y + size.y / 2f);
    }

    public void translate (float xTranslation, float yTranslation) {
        position.add(xTranslation, yTranslation);
        centerPosition.add(xTranslation, yTranslation);
    }

    public void translate (Vector2 translation) {
        position.add(translation.x, translation.y);
        centerPosition.add(translation.x, translation.y);
    }

    public Vector2 getPosition() {
        return new Vector2(position);
    }

    public Vector2 getSize() {
        return new Vector2(size);
    }

    public Vector2 getCenterPosition() {
        return new Vector2(position.x + (size.x / 2f), position.y + (size.y / 2f));
    }

    public float getWidth() {
        return size.x;
    }

    public float getHeight() {
        return size.y;
    }
}