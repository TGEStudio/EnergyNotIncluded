package net.tgestudio.energynotincluded.block.construction.construct_base;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.tgestudio.energynotincluded.block.construction.ConstructorBlockEntity;
import net.tgestudio.energynotincluded.registry_file.RegsBlocks;
import net.tgestudio.energynotincluded.registry_file.RegsConsBlueprint;
import net.tgestudio.energynotincluded.registry_file.RegsItems;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.level.block.Blocks.AIR;

public class ConsRenderer implements BlockEntityRenderer<ConstructorBlockEntity> {

    public ConsRenderer(BlockEntityRendererProvider.Context context) {
    }


    @Override
    public boolean shouldRenderOffScreen(ConstructorBlockEntity pBlockEntity) {
        return true;
    }

    @Override
    public void render(ConstructorBlockEntity consRenderer, float v, @NotNull PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {
        consRenderer.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
            if (h.getStackInSlot(ConstructorBlockEntity.SLOT).is(RegsItems.BLUEPRINT.get())) {
                CompoundTag itemList = h.getStackInSlot(ConstructorBlockEntity.SLOT).getTag();
                if (itemList != null) {

                    String blueprintID = itemList.getString("blueprint_name");
                    ConsBlockBlueprint consBlockBlueprint = RegsConsBlueprint.get(blueprintID);

                    for (ConsBlock consBlock : consBlockBlueprint.getConsBlockList()) {

                        Vec3i masterVec3i = RegsConsBlueprint.rotate(consRenderer.getBlockState(), consBlock.getMasterVec3i());
                        Vec3i blockPos = RegsConsBlueprint.rotate(consRenderer.getBlockState(), consBlock.getBlockPosVec3i());

                        BlockPos masterPos = consRenderer.getBlockPos().offset(masterVec3i);
                        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();

                        poseStack.pushPose();
                        poseStack.scale(1f, 1f, 1f);
                        poseStack.translate(
                                0 + (float) blockPos.getX() + masterVec3i.getX(),
                                0 + (float) blockPos.getY() + masterVec3i.getY(),
                                0 + (float) blockPos.getZ() + masterVec3i.getZ()
                        );
                        Block consBlockState = consRenderer.getLevel().getBlockState(masterPos.offset(blockPos)).getBlock();
                        BlockState placeBlock = AIR.defaultBlockState();
                        if (AIR == consBlockState) {
                            placeBlock = RegsBlocks.PLACEABLE_BLOCK.get().defaultBlockState();
                        } else {
                            if (consBlock.defaultBlockState().getBlock() != consBlockState) {
                                placeBlock = RegsBlocks.PLACEABLE_BLOCK_WRONG.get().defaultBlockState();
                            }
                        }

                        blockRenderer.renderSingleBlock(placeBlock, poseStack, multiBufferSource, i, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.solid());
                        poseStack.popPose();

                        poseStack.pushPose();
                        poseStack.scale(0.5f, 0.5f, 0.5f);
                        poseStack.translate(
                                0.5f + (float) (blockPos.getX() + masterVec3i.getX()) * 2,
                                0.5f + (float) (blockPos.getY() + masterVec3i.getY()) * 2,
                                0.5f + (float) (blockPos.getZ() + masterVec3i.getZ()) * 2
                        );
                        if (consBlock.defaultBlockState().getBlock() != consBlockState) {
                            blockRenderer.renderSingleBlock(consBlock.defaultBlockState(), poseStack, multiBufferSource, i, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, RenderType.solid());
                        }
                        poseStack.popPose();
                    }
                }
            }
        });
    }
}

