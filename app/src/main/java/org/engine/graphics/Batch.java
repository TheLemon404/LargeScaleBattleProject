package org.engine.graphics;

import org.engine.scene.Entity;

public class Batch<T extends Entity & Batchable> implements Renderable {

    public void draw(GraphicsContext context) {}
}
