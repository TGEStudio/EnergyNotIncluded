package net.tgestudio.energynotincluded.block.construction;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.tgestudio.energynotincluded.block.construction.construct_base.ConsBlock;

import static net.tgestudio.energynotincluded.registry_file.RegsBlocks.SLAVE_ENTITY;

public class ConsSlaveEntity extends BlockEntity {
    public static final String ITEMS_TAG = "Inventory";
    private ConsBlock consBlock;

    public ConsSlaveEntity(BlockPos pPos, BlockState pBlockState) {
        super(SLAVE_ENTITY.get(), pPos, pBlockState);
    }

    public void drops() {
        if (consBlock != null) {
            SimpleContainer inventory = new SimpleContainer(1);
            ItemStack dropItem = consBlock.defaultBlockState().getBlock().asItem().getDefaultInstance();
            inventory.addItem(dropItem);
            Containers.dropContents(this.level, this.worldPosition, inventory);
        }
    }

    public void setConsBlock(ConsBlock consBlock) {
        this.consBlock = consBlock;
    }

    public ConsBlock getConsBlock() {
        return this.consBlock;
    }

    //region Save & Load
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        saveClientData(tag);
    }

    private void saveClientData(CompoundTag tag) {
        tag.put(ITEMS_TAG, consBlock.defaultBlockState()
                .getBlock()
                .asItem()
                .getDefaultInstance()
                .serializeNBT());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        loadClientData(tag);
    }

    private void loadClientData(CompoundTag tag) {
        if (tag.contains(ITEMS_TAG)) {
            consBlock.defaultBlockState()
                    .getBlock()
                    .asItem()
                    .getDefaultInstance()
                    .deserializeNBT(tag.getCompound(ITEMS_TAG));
        }
    }
    //endregion

}
