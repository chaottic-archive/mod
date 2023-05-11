package com.chaottic.mod.common.block;

import lombok.Getter;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public final class DeskBlock extends Block {
    private static final EnumProperty<Form> FORM = EnumProperty.create("form", Form.class);

    public DeskBlock(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(FORM, Form.SINGLE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FORM);
    }

    private enum Form implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right");

        @Getter
        private final String serializedName;

        Form(String serializedName) {
            this.serializedName = serializedName;
        }
    }
}
