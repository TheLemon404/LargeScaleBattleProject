package org.engine.assets;

public abstract class Asset {

    public AID id;

    public Asset() {
        try {
            AssetRegistry.registerAsset(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unload() {
        AssetRegistry.deregisterAsset(this);
    }
}
