package com.chaottic.mod.common.block.entity;

import com.chaottic.mod.common.ModBlockEntityTypes;
import com.chaottic.mod.common.Util;
import lombok.Getter;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public final class TeapotBlockEntity extends BlockEntity {
    @Getter
    private final SingleVariantStorage<FluidVariant> storage = new SingleVariantStorage<FluidVariant>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return FluidConstants.BUCKET;
        }

        @Override
        protected void onFinalCommit() {
            setChanged();

            if (!level.isClientSide) {
                Util.blockEntityToClient(TeapotBlockEntity.this);
            }
        }
    };

    @Getter
    private int time;

    public TeapotBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public TeapotBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntityTypes.TEAPOT, blockPos, blockState);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        storage.variant = FluidVariant.fromNbt(compoundTag.getCompound("FluidVariant"));
        storage.amount = compoundTag.getLong("Amount");
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        compoundTag.put("FluidVariant", storage.variant.toNbt());
        compoundTag.putLong("Amount", storage.amount);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    public static void clientTick(Level level, BlockPos blockPos, BlockState blockState, TeapotBlockEntity blockEntity) {
        blockEntity.time++;
    }
}
