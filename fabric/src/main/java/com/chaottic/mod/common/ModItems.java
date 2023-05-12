package com.chaottic.mod.common;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public final class ModItems {
    public static final Item DESK = new BlockItem(ModBlocks.DESK, new Item.Properties());
    public static final Item OVEN = new BlockItem(ModBlocks.OVEN, new Item.Properties());
    public static final Item TEAPOT = new BlockItem(ModBlocks.TEAPOT, new Item.Properties());

    private ModItems() {}

    public static void registerAll() {
        register("desk", DESK);
        register("oven", OVEN);
        register("teapot", TEAPOT);
    }

    private static void register(String name, Item item) {
        Registry.register(BuiltInRegistries.ITEM, Mod.resourceLocation(name), item);
    }
}
