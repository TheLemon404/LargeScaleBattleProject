package org.engine.scene;

import java.util.HashMap;
import java.util.Map;

public class EntityRegistry {

    private int nextId = 1;
    private Map<EID, Entity> entityMap = new HashMap<EID, Entity>();
    private Map<String, EID> entityNameMap = new HashMap<String, EID>();

    public void registerEntity(Entity entity) throws Exception {
        if (entityMap.containsKey(new EID(nextId))) throw new Exception(
            "Entity of id: " + nextId + " already registered with that ID"
        );

        entity.id = new EID(nextId++);
        entityMap.put(entity.id, entity);
    }

    public void registerEntityWithName(Entity entity) throws Exception {
        if (entityMap.containsKey(new EID(nextId))) throw new Exception(
            "Entity of id: " +
                nextId +
                " + " +
                entity.name +
                " already registered with that ID"
        );
        if (entityNameMap.containsKey(entity.name)) throw new Exception(
            "Entity of id: " +
                nextId +
                " + " +
                entity.name +
                " already registered with that name"
        );

        entity.id = new EID(nextId++);
        entityMap.put(entity.id, entity);
        entityNameMap.put(entity.name, entity.id);
    }

    public void deregisterEntity(Entity entity) {
        entityMap.remove(entity.id);
    }
}
