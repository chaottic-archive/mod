package com.chaottic.mod.common;

import com.chaottic.mod.common.block.DeskBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public final class ModBlocks {
    public static final Block DESK = new DeskBlock(Properties.copy(Blocks.OAK_PLANKS));

    private ModBlocks() {}

    public static void registerAll() {
        register("desk", DESK);
    }

    private static void register(String name, Block block) {
        Registry.register(BuiltInRegistries.BLOCK, Mod.resourceLocation(name), block);
    }
}
