package aplicacion.game.components.common;

import aplicacion.game.components.Component;
import aplicacion.game.entitiy.Entity;
import aplicacion.game.utils.Vector2;

public class Transform extends Component {
    private Vector2 centerPosition = new Vector2();
    private Vector2 position = new Vector2();
    private Vector2 size = new Vector2();

    public Transform(Entity parent, Vector2 position, Vector2 size){
        super(parent, parent);
        setPosition(position);
        setSize(size);
    }

    public Transform(Entity parent) {
        this(parent, new Vector2(), new Vector2());
    }

    @Override
    public void start() {}

    @Override
    public void update() {}

    public void setPosition(Vector2 position) {
        this.position = position;
        centerPosition = new Vector2(position.x + size.x / 2f, position.y + size.y / 2f);
        //System.out.println(parent.getName() + " Position: " + position);
    }

    public void setSize(Vector2 size) {
        this.size = size;
        centerPosition = new Vector2(position.x + size.x / 2f, position.y + size.y / 2f);
        //System.out.println(parent.getName() + " Size: " + size);
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
