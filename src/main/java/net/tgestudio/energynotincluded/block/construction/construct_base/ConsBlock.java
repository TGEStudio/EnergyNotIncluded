package net.tgestudio.energynotincluded.block.construction.construct_base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.state.BlockState;


public class ConsBlock {

    private final BlockState blockState;
    private final Vec3i blockPos;
    private final Vec3i masterBlockPos;

    public ConsBlock(Vec3i masterBlockPos, BlockState blockState, Vec3i blockPos) {
        this.masterBlockPos = masterBlockPos;
        this.blockState = blockState;
        this.blockPos = blockPos;
    }

    public Vec3i getBlockPosVec3i() {
        return this.blockPos;
    }

    public BlockPos getMasterBlockPos(BlockPos blockPos) {
        return blockPos.offset(this.masterBlockPos);
    }

    public Vec3i getMasterVec3i() {
        return this.masterBlockPos;
    }

    public BlockState defaultBlockState() {
        return this.blockState;
    }
}
