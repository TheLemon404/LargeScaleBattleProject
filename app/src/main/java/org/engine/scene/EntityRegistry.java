package org.engine.scene;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EntityRegistry {

    private int nextId = 1;
    private Map<EID, Entity> entityMap = new HashMap<EID, Entity>();
    private Map<String, EID> entityNameMap = new HashMap<String, EID>();

    public Entity getEntityById(EID id) throws RuntimeException {
        if (!entityMap.containsKey(id)) throw new RuntimeException(
            "entity of ID: " + id + " not found in registry"
        );
        return entityMap.get(id);
    }

    public Entity getEntityByName(String name) throws RuntimeException {
        if (!entityNameMap.containsKey(name)) throw new RuntimeException(
            "entity of name: " + name + " not found in registry"
        );
        return entityMap.get(entityNameMap.get(name));
    }

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

    public Collection<Entity> getRegisterdEntities() {
        return entityMap.values();
    }
}
