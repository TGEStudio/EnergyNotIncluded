package net.tgestudio.energynotincluded.block.custom_multiblock;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class CoalGeneratorRenderer implements BlockEntityRenderer<CoalGeneratorEntity> {

    public CoalGeneratorRenderer(BlockEntityRendererProvider.Context context) {
    }


    @Override
    public boolean shouldRenderOffScreen(CoalGeneratorEntity pBlockEntity) {
        return true;
    }

    @Override
    public void render(CoalGeneratorEntity coalgenRenderer, float v, @NotNull PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        coalgenRenderer.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
            if (!h.getStackInSlot(0).isEmpty()) {

                Block coalGeneratorBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation("energynotincluded:models/block/coal_generator.json"));
                System.out.println(coalGeneratorBlock);
                poseStack.pushPose();
                poseStack.scale(1f, 1f, 1f);
                poseStack.translate(
                        0,
                        5,
                        0
                );
                poseStack.popPose();
                BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
                blockRenderer.getModelRenderer().tesselateBlock(
                        coalgenRenderer.getLevel(),
                        blockRenderer.getBlockModel(coalGeneratorBlock.defaultBlockState()),
                        coalGeneratorBlock.defaultBlockState(),
                        coalgenRenderer.getBlockPos(),
                        poseStack,
                        multiBufferSource.getBuffer(RenderType.solid()),
                        false,
                        coalgenRenderer.getLevel().getRandom(),
                        0, i1
                );
            }
        });
    }
}

