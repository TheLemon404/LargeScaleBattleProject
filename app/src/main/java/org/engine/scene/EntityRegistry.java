package org.engine.scene;

import java.util.HashMap;
import java.util.Map;

public class EntityRegistry {

    private int nextId = 0;
    private Map<EID, Entity> entityMap = new HashMap<EID, Entity>();

    public void registerEntity(Entity entity) throws Exception {
        if (entityMap.containsKey(new EID(nextId))) throw new Exception(
            "Entity of id: " + nextId + " already registered"
        );

        entity.id = new EID(nextId++);
        entityMap.put(entity.id, entity);
    }

    public void deregisterEntity(Entity entity) {
        entityMap.remove(entity.id);
    }
}
