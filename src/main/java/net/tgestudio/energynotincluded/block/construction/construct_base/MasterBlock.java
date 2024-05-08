package net.tgestudio.energynotincluded.block.construction.construct_base;

import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.Blocks;


public final class MasterBlock extends ConsBlock {
    public MasterBlock(Vec3i blockPos) {
        super(blockPos, Blocks.DIAMOND_BLOCK.defaultBlockState(), new Vec3i(0, 0, 0));
    }
}
