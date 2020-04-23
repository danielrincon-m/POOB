package aplicacion.collision;

import java.util.ArrayList;

public class CollisionSystem {
    private ArrayList<Collider> simulatedColliders;

    public CollisionSystem() {
        simulatedColliders = new ArrayList<Collider>();
    }

    public void calculateCollisions() {
        for (Collider col1 : simulatedColliders) {
            for (Collider col2 : simulatedColliders) {
                if (col1 != col2) {
                    col1.checkIntersection(col2);
                }
            }
        }
    }

    public void removeCollider(Collider collider) {
        simulatedColliders.remove(collider);
    }

    public void addCollider(Collider collider) {
        simulatedColliders.add(collider);
    }
}
