package com.chaottic.mod.common.block;

import com.chaottic.mod.common.Util;
import com.mojang.math.Axis;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Quaternionf;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING;

public final class DeskBlock extends Block {
    private static final EnumProperty<Shape> SHAPE = EnumProperty.create("shape", Shape.class);

    private static final VoxelShape VOXEL_SHAPE = Shapes.join(box(0, 0, 3, 16, 13, 13), box(0, 13, 0, 16, 16, 16), BooleanOp.OR);

    private static final Map<BlockState, VoxelShape> MAP = new HashMap<>();

    public DeskBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(SHAPE, Shape.SINGLE).setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return defaultBlockState().setValue(FACING, blockPlaceContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return MAP.computeIfAbsent(blockState, DeskBlock::getShape);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, FACING);
    }

    private static VoxelShape getShape(BlockState blockState) {
        return Util.rotateShape(VOXEL_SHAPE, getAngle(blockState.getValue(FACING)));
    }

    private static Quaternionf getAngle(Direction direction) {
        return switch (direction) {
            case EAST,
                 WEST -> Axis.YP.rotationDegrees(90.0F);
            default -> Axis.YP.rotationDegrees(0.0F);
        };
    }

    private enum Shape implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right");

        @Getter
        private final String serializedName;

        Shape(String serializedName) {
            this.serializedName = serializedName;
        }
    }
}
