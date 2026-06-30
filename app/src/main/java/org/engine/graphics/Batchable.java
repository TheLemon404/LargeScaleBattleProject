package org.engine.graphics;

import org.engine.scene.Entity;

public interface Batchable {
    public void extractBatchInstanceData(
        GraphicsContext context,
        Batch<? extends Batchable> batch
    );
}
