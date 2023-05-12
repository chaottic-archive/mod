package com.chaottic.mod.client;

import com.chaottic.mod.common.Mod;
import net.minecraft.client.model.geom.ModelLayerLocation;

public final class ModLayerLocations {
    public static final ModelLayerLocation TEAPOT = of("teapot");

    private ModLayerLocations() {}

    private static ModelLayerLocation of(String name) {
        return new ModelLayerLocation(Mod.resourceLocation(name), "main");
    }
}
