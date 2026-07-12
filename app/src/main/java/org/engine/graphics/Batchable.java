package org.engine.graphics;

public interface Batchable {
    public void extractBatchInstanceData(Batch<? extends Batchable> batch);
}
