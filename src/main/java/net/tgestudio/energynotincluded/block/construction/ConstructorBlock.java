package net.tgestudio.energynotincluded.block.construction;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.tgestudio.energynotincluded.registry_file.RegsConsBlueprint;
import org.jetbrains.annotations.Nullable;

public class ConstructorBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public ConstructorBlock(Properties pProperties) {
        super(pProperties);
    }

    /*@Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Blocks.GLASS.defaultBlockState().getShape(pLevel,pPos);
    }*/

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pLevel.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity pEntity = pLevel.getBlockEntity(pPos);
            if (pEntity instanceof ConstructorBlockEntity) {
                NetworkHooks.openScreen((ServerPlayer) pPlayer, (ConstructorBlockEntity) pEntity, pPos);
            } else {
                throw new IllegalStateException("Container Missing !!!");
            }

            //CheckBlocks
            if (pPlayer.isShiftKeyDown()) {
                BlockPos masterPos = RegsConsBlueprint.get("TEST").getConsBlockList().get(0).getMasterBlockPos(pPos);
                if (RegsConsBlueprint.get("TEST").checkBlocks(pLevel, masterPos)) {
                    RegsConsBlueprint.get("TEST").toSlave(pLevel, masterPos);
                }
            }
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ConstructorBlockEntity(blockPos, blockState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
}
