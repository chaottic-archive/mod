package com.chaottic.mod.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public final class Mod implements ModInitializer {
    private static final String MOD_ID = "mod";

    @Override
    public void onInitialize() {
        ModBlocks.registerAll();
        ModItems.registerAll();
    }

    public static ResourceLocation resourceLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
