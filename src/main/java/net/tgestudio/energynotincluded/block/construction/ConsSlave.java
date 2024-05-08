package net.tgestudio.energynotincluded.block.construction;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.tgestudio.energynotincluded.registry_file.RegsConsBlueprint;
import org.jetbrains.annotations.Nullable;

public class ConsSlave extends BaseEntityBlock {

    public ConsSlave(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (level.getBlockEntity(pos) instanceof ConsSlaveEntity consSlaveEntity) {
            BlockPos masterPos = consSlaveEntity.getConsBlock().getMasterBlockPos(pos);
            RegsConsBlueprint.get("TEST").toNormal(level, masterPos);
            consSlaveEntity.drops();
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ConsSlaveEntity(blockPos, blockState);
    }

}
