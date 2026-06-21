package org.engine.scene;

import java.util.HashMap;
import java.util.Map;

public class EntityRegistry {

    int nextId = 0;
    private Map<Integer, Entity> entityMap = new HashMap<Integer, Entity>();

    public void registerEntity(Entity entity) {
        entity.id = nextId++;
        entityMap.put(entity.id, entity);
    }

    public void deregisterEntity(Entity entity) {
        entityMap.remove(entity.id);
    }
}
