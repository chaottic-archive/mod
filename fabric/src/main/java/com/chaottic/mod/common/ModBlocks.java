package com.chaottic.mod.common;

import com.chaottic.mod.common.block.DeskBlock;
import com.chaottic.mod.common.block.OvenBlock;
import com.chaottic.mod.common.block.TeapotBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class ModBlocks {
    public static final Block DESK = new DeskBlock(Properties.copy(Blocks.OAK_PLANKS));
    public static final Block OVEN = new OvenBlock(Properties.copy(Blocks.STONE));
    public static final Block TEAPOT = new TeapotBlock(Properties.copy(Blocks.STONE).noOcclusion());

    private ModBlocks() {}

    public static void registerAll() {
        register("desk", DESK);
        register("oven", OVEN);
        register("teapot", TEAPOT);
    }

    private static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, Mod.resourceLocation(name), block);
    }
}
