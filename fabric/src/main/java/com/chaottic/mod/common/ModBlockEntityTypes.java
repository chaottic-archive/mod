package com.chaottic.mod.common;

import com.chaottic.mod.common.block.entity.TeapotBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ModBlockEntityTypes {
    public static final BlockEntityType<TeapotBlockEntity> TEAPOT = FabricBlockEntityTypeBuilder.create(TeapotBlockEntity::new, ModBlocks.TEAPOT).build();

    private ModBlockEntityTypes() {}

    public static void registerAll() {
        register("teapot", TEAPOT);
    }

    private static <T extends BlockEntity> void register(String name, BlockEntityType<T> type) {
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Mod.resourceLocation(name), type);
    }
}
