package aplicacion.game.utils;

import aplicacion.game.components.common.Sprite;
import aplicacion.game.entities.Entity;

import java.util.Comparator;
import java.util.Map;

public class ZIndexComparator implements Comparator<Map.Entry<String, Entity>> {
    @Override
    public int compare(Map.Entry<String, Entity> entry1, Map.Entry<String, Entity> entry2) {
        Entity e1 = entry1.getValue();
        Entity e2 = entry2.getValue();
        if (!e1.hasComponent(Sprite.class)) {
            if (!e2.hasComponent(Sprite.class)) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (!e2.hasComponent(Sprite.class)) {
                return 1;
            } else {
                Integer i1 = e1.getComponent(Sprite.class).getzIndex();
                Integer i2 = e2.getComponent(Sprite.class).getzIndex();
                return i1.compareTo(i2);
            }
        }
    }
}
