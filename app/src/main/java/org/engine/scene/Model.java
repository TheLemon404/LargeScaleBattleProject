package org.engine.scene;

import org.engine.graphics.*;

public class Model extends Entity implements Renderable {

    private Mesh mesh;
    public boolean visible = true;

    public Model(Mesh mesh) {
        this.mesh = mesh;
    }

    @Override
    public void draw() {
        if (visible) mesh.draw(transform);
    }
}
