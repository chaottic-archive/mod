package com.chaottic.mod.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public final class Mod implements ModInitializer {
    private static final String MOD_ID = "mod";

    private static final CreativeModeTab CREATIVE_MODE_TAB = FabricItemGroup.builder(resourceLocation("mod")).icon(ModItems.TEAPOT::getDefaultInstance).displayItems((parameters, output) -> {
        output.accept(ModItems.DESK);
        output.accept(ModItems.OVEN);
        output.accept(ModItems.TEAPOT);
    }).build();

    public static ResourceLocation BLOCK_ENTITY_TO_CLIENT = resourceLocation("block_entity_to_client");

    @Override
    public void onInitialize() {
        ModBlocks.registerAll();
        ModItems.registerAll();
        ModBlockEntityTypes.registerAll();
    }

    public static ResourceLocation resourceLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
