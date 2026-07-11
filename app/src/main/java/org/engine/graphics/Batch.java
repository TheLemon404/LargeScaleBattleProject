package org.engine.graphics;

import java.util.ArrayList;

public class Batch<T extends Batchable> implements Renderable {

    private ArrayList<T> instances = new ArrayList<T>();

    public void batch(GraphicsContext context) {
        for (T instance : instances) {
            instance.extractBatchInstanceData(context, this);
        }
    }

    public void draw(GraphicsContext context) {}
}
