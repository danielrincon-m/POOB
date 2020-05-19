package aplicacion.game.enums;

/**
 * Representación de los posibles lados de un campo, junto con un valor respresentativo
 */
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

