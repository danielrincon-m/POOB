package aplicacion.game.enums;


public enum FieldSide {
    TOP(1),
    BOTTOM(-1);

    private final float sideValue;

    private FieldSide(int sideValue) {
        this.sideValue = sideValue;
    }

    public float sideValue() {
        return sideValue;
    }
}

