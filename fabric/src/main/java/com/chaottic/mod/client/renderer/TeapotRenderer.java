package com.chaottic.mod.client.renderer;

import com.chaottic.mod.client.ModLayerLocations;
import com.chaottic.mod.client.model.TeapotModel;
import com.chaottic.mod.common.Mod;
import com.chaottic.mod.common.block.entity.TeapotBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public final class TeapotRenderer implements BlockEntityRenderer<TeapotBlockEntity> {
    private static final ResourceLocation TEXTURE = Mod.resourceLocation("");

    private final TeapotModel model;

    public TeapotRenderer(BlockEntityRendererProvider.Context context) {
        model = new TeapotModel(context.bakeLayer(ModLayerLocations.TEAPOT));
    }

    @Override
    public void render(TeapotBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        var vertexConsumer = multiBufferSource.getBuffer(model.renderType(TEXTURE));

        model.getRoot().render(poseStack, vertexConsumer, i, j);

        poseStack.popPose();
    }
}
