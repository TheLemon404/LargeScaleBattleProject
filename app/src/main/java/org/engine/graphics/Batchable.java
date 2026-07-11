package org.engine.graphics;

public interface Batchable {
    public void extractBatchInstanceData(
        GraphicsContext context,
        Batch<? extends Batchable> batch
    );
}
