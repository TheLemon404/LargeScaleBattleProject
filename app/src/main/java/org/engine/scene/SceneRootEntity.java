package org.engine.scene;

public class SceneRootEntity extends Entity {

    public SceneRootEntity() {
        parent = Tree.root;
    }

    @Override
    public void setParent(Entity parent) throws Exception {
        throw new Exception(
            "Scene Root entities must only be parented to the Tree.root, and can never be reparented"
        );
    }
}
