package com.chaottic.mod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import lombok.Getter;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public final class TeapotModel extends Model {
    @Getter
    private final ModelPart root;
    private final ModelPart lid;
    private final ModelPart base;

    public TeapotModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.root = root;
        lid = root.getChild("lid");
        base = root.getChild("base");
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
        root.render(poseStack, vertexConsumer, i, j, f, g, h, k);
    }

    public static LayerDefinition layerDefinition() {
        var meshdefinition = new MeshDefinition();
        var partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(50, 5).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 26).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(1, 35).addBox(-3.0F, 1.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 1.0F));

        partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, -4.0F, 10.0F, 6.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(40, 16).addBox(-1.0F, -7.0F, 5.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(40, 9).addBox(-1.0F, -4.0F, -7.0F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(50, 12).addBox(-1.0F, -6.0F, -7.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(40, 4).addBox(-1.0F, -8.0F, -8.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-4.0F, -7.0F, -3.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
