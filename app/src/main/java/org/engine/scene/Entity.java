package org.engine.scene;

public class Entity {

    protected String name;
    protected EID id;
    protected EID parent;

    public Entity(String name, EID parent) {
        this.name = name;

        try {
            Tree.registry.registerEntityWithName(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.parent = parent;
    }

    public Entity(String name) {
        this.name = name;

        try {
            Tree.registry.registerEntityWithName(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.parent = Tree.root;
    }

    public Entity(EID parent) {
        try {
            Tree.registry.registerEntity(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.parent = parent;
    }

    public Entity() {
        this(Tree.root);
    }

    public EID getParent() {
        return parent;
    }

    public void setParent(EID entity) throws Exception {
        parent = entity;
    }

    public void destroy() {
        Tree.registry.deregisterEntity(this);
    }
}
