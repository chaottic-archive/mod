package com.chaottic.mod.common.block.entity;

import com.chaottic.mod.common.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public final class TeapotBlockEntity extends BlockEntity {
    public TeapotBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public TeapotBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntityTypes.TEAPOT, blockPos, blockState);
    }
}
