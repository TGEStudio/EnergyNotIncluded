package net.tgestudio.energynotincluded.block.construction.construct_base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.tgestudio.energynotincluded.block.construction.ConsSlaveEntity;
import net.tgestudio.energynotincluded.registry_file.RegsBlocks;

import java.util.List;

public final class ConsBlockBlueprint {
    private final List<ConsBlock> ConsBlockList;

    public ConsBlockBlueprint(List<ConsBlock> consBlockList) {
        this.ConsBlockList = consBlockList;
    }

    public boolean checkBlocks(Level bLevel, BlockPos bConsPos) {
        for (var consBlock : ConsBlockList) {
            if (!bLevel.isClientSide() &&
                    !checkBlock(bLevel, bConsPos, consBlock)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkBlock(Level bLevel, BlockPos bConsPos, ConsBlock consBlock) {
        return bLevel.getBlockState(bConsPos.offset(consBlock.getBlockPosVec3i())) == consBlock.defaultBlockState();
    }

    public void toSlave(Level bLevel, BlockPos bConsPos) {
        boolean isMaster = false;
        for (var consBlock : ConsBlockList) {
            if (!isMaster) {
                isMaster = true;
                continue;
            }

            BlockPos consBlockPos = bConsPos.offset(consBlock.getBlockPosVec3i());
            bLevel.setBlock(consBlockPos, RegsBlocks.SLAVE.get().defaultBlockState(), 3);

            if (bLevel.getBlockEntity(consBlockPos) instanceof ConsSlaveEntity consSlaveEntity) {
                consSlaveEntity.setConsBlock(consBlock);
            }
        }
    }

    public void toNormal(Level bLevel, BlockPos bConsPos) {
        for (var consBlock : ConsBlockList) {
            bLevel.setBlock(bConsPos.offset(consBlock.getBlockPosVec3i()), consBlock.defaultBlockState(), 3);
        }
    }

    public List<ConsBlock> getConsBlockList() {
        return this.ConsBlockList;
    }
}
