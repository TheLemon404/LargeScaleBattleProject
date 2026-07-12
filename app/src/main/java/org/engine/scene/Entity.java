package org.engine.scene;

import java.util.ArrayList;

public class Entity {

    protected String name;
    protected EID id;
    protected EID parent;

    public Transform transform = new Transform();

    public Transform getLocalTransform() {
        return transform;
    }

    public Transform getGlobalTransform() {
        if (parent != Tree.root) {
            return transform.add(
                Tree.getEntityById(parent, Entity.class).getGlobalTransform()
            );
        }

        return transform;
    }

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

    //todo need to impliment this
    public ArrayList<Entity> getChildren() {
        return new ArrayList<>();
    }

    //todo need to impliment this
    public ArrayList<Entity> getAllChildren() {
        return new ArrayList<>();
    }
}
