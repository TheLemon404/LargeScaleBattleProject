package org.engine.scene;

public class Tree {

    public static EID root = new EID(0);
    public static EntityRegistry registry = new EntityRegistry();

    public static <T extends Entity> T getEntityById(EID id, Class<T> type)
        throws RuntimeException {
        Entity e = registry.getEntityById(id);
        if (!type.isInstance(e)) throw new RuntimeException(
            "Entity with ID: " + id + " is not of type " + type
        );
        return type.cast(e);
    }

    public static <T extends Entity> T getEntityByName(
        String name,
        Class<T> type
    ) throws RuntimeException {
        Entity e = registry.getEntityByName(name);
        if (!type.isInstance(e)) throw new RuntimeException(
            "Entity with ID: " +
                e.id +
                " and name: " +
                name +
                " is not of type " +
                type
        );
        return type.cast(e);
    }
}
