package org.engine.scene;

import java.util.function.Supplier;

public class Tree {

    public static Entity root = new Entity();
    public static EntityRegistry registry = new EntityRegistry();

    public static <T extends Entity> T createEntity(Supplier<T> supplier) {
        EntityFactory<T> factory = new EntityFactory<T>(supplier);
        return factory.createInstance();
    }
}
