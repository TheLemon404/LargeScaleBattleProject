package org.engine.scene;

public class Entity {

    public int id;

    protected Entity parent;

    public Entity(Entity parent) {
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
}
