package org.engine.assets;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class AssetRegistry {

    private static int nextId = 0;
    private static Map<AID, Asset> assetMap = new HashMap<AID, Asset>();
    private static Map<String, AID> assetNameMap = new HashMap<String, AID>();

    public static void registerAsset(Asset asset) throws Exception {
        if (assetMap.containsKey(new AID(nextId))) throw new Exception(
            "Asset with id: " + nextId + " already registered"
        );

        asset.id = new AID(nextId++);
        assetMap.put(asset.id, asset);
    }

    public static void registerAssetWithName(String name, Asset asset)
        throws Exception {
        asset.name = name;
        registerAsset(asset);

        if (assetNameMap.containsKey(name)) throw new Exception(
            "Asset with name: " + name + " already registered"
        );
        assetNameMap.put(name, asset.id);
    }

    public static void deregisterAsset(Asset asset) {
        assetMap.remove(asset.id);
    }

    public static Asset getAsset(AID id) throws NoSuchElementException {
        if (!assetMap.containsKey(id)) throw new NoSuchElementException(
            "Asset with id: " + id + " not found in asset registry"
        );
        return assetMap.get(id);
    }

    public static Asset getAssetByName(String name)
        throws NoSuchElementException {
        if (!assetNameMap.containsKey(name)) throw new NoSuchElementException(
            "Asset with name: " + name + " not found in asset registry"
        );
        return assetMap.get(assetNameMap.get(name));
    }
}
