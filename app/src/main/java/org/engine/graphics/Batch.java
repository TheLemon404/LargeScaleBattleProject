package org.engine.graphics;

import java.util.ArrayList;
import org.engine.scene.Transform;

public class Batch<T extends Batchable> implements Renderable {

    private ArrayList<T> instances = new ArrayList<T>();

    public void batch() {
        for (T instance : instances) {
            instance.extractBatchInstanceData(this);
        }
    }

    public void draw() {}
}
