package org.engine.assets;

import java.util.*;

public class AssetRegistry {

    private static int nextId = 0;
    private static Map<AID, Asset> assetMap = new HashMap<AID, Asset>();

    public static void registerAsset(Asset asset) throws Exception {
        if (assetMap.containsKey(new AID(nextId))) throw new Exception(
            "Asset with id: " + nextId + " already registered"
        );

        asset.id = new AID(nextId++);
        assetMap.put(asset.id, asset);
    }

    public static void deregisterAsset(Asset asset) {
        assetMap.remove(asset.id);
    }
}
