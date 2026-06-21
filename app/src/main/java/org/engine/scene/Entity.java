package org.engine.scene;

public class Entity {

    protected int id;
    protected Entity parent;

    public Entity(Entity parent) {
        Tree.registry.registerEntity(this);
        this.parent = parent;
    }

    public Entity() {
        this(Tree.root);
    }

    public Entity getParent() {
        return parent;
    }

    public void setParent(Entity entity) throws Exception {
        parent = entity;
    }

    public void destroy() {
        Tree.registry.deregisterEntity(this);
    }
}
