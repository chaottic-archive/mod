package com.chaottic.mod.client.renderer;

import com.chaottic.mod.client.ModLayerLocations;
import com.chaottic.mod.client.model.TeapotModel;
import com.chaottic.mod.common.Mod;
import com.chaottic.mod.common.block.entity.TeapotBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public final class TeapotRenderer implements BlockEntityRenderer<TeapotBlockEntity> {
    private static final ResourceLocation TEXTURE = Mod.resourceLocation("textures/entity/teapot/teapot.png");

    private final TeapotModel model;

    public TeapotRenderer(BlockEntityRendererProvider.Context context) {
        model = new TeapotModel(context.bakeLayer(ModLayerLocations.TEAPOT));
    }

    @Override
    public void render(TeapotBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.scale(-1.0F, -1.0F, 1.0F);

        var g = (float) blockEntity.getTime() + f;

        poseStack.translate(0.0F, (float) (Math.sin(g * 0.275F) * 0.2F) * 0.1F, 0.0F);

        var root = model.getRoot();
        var lid = root.getChild("lid");
        var base = root.getChild("base");

        lid.y = 5.5F - (float) (Math.sin(g * 0.3F) * 0.225F);
        lid.zRot = -3.14159F;

        base.y = 1.525F;

        var vertexConsumer = multiBufferSource.getBuffer(model.renderType(TEXTURE));

        root.render(poseStack, vertexConsumer, i, j);

        renderFluid(multiBufferSource, poseStack, i, blockEntity.getStorage(), blockEntity.getLevel(), blockEntity.getBlockPos());

        poseStack.popPose();
    }

    private void renderFluid(MultiBufferSource source, PoseStack poseStack, int i, SingleVariantStorage<FluidVariant> storage, BlockAndTintGetter getter, BlockPos blockPos) {
        if (!storage.isResourceBlank()) {
            var fluidVariant = storage.getResource();
            var amount = storage.getAmount();

            var last = poseStack.last();
            var pose = last.pose();
            var normal = last.normal();

            var consumer = source.getBuffer(RenderType.solid());

            var color = 0xFFFFFFFF;

            vertex(consumer, pose, -0.5F, 0.0F, -0.5F, color, 0.0F, 0.0F, i, normal, 0, 0, 0);
            vertex(consumer, pose, 0.5F, 0.0F, -0.5F, color, 0.0F, 0.0F, i, normal, 0, 0, 0);
            vertex(consumer, pose, 0.5F, 0.0F, 0.5F, color, 0.0F, 0.0F, i, normal, 0, 0, 0);
            vertex(consumer, pose, -0.5F, 0.0F, 0.5F, color, 0.0F, 0.0F, i, normal, 0, 0, 0);
        }
    }

    private static void vertex(VertexConsumer consumer, Matrix4f matrix4f, float x, float y, float z, int color, float u, float v, int uv2, Matrix3f matrix3f, float nX, float nY, float nZ) {
        consumer.vertex(matrix4f, x, y, z).color(color).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(uv2).normal(matrix3f, nX, nY, nZ).endVertex();
    }
}
