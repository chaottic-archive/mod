package com.chaottic.mod.client;

import com.chaottic.mod.client.model.TeapotModel;
import com.chaottic.mod.client.renderer.TeapotRenderer;
import com.chaottic.mod.common.Mod;
import com.chaottic.mod.common.ModBlockEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(ModBlockEntityTypes.TEAPOT, TeapotRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModLayerLocations.TEAPOT, TeapotModel::layerDefinition);

        ClientPlayNetworking.registerGlobalReceiver(Mod.BLOCK_ENTITY_TO_CLIENT, (minecraft, listener, buf, sender) -> {
            var blockPos = buf.readBlockPos();
            var nbt = buf.readNbt();
            var type = buf.readById(BuiltInRegistries.BLOCK_ENTITY_TYPE);

            minecraft.execute(() -> {
                var level = minecraft.level;
                if (level != null) {
                    level.getBlockEntity(blockPos, type).ifPresent(blockEntity -> blockEntity.load(nbt));
                }
            });
        });
    }
}
