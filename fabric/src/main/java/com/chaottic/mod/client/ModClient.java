package com.chaottic.mod.client;

import com.chaottic.mod.client.model.TeapotModel;
import com.chaottic.mod.client.renderer.TeapotRenderer;
import com.chaottic.mod.common.ModBlockEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public final class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntityTypes.TEAPOT, TeapotRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModLayerLocations.TEAPOT, TeapotModel::layerDefinition);
    }
}
