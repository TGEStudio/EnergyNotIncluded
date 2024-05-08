package net.tgestudio.energynotincluded.item.construction;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Blueprint extends Item {
    public Blueprint(Properties pProperties) {
        super(pProperties);
    }

    private String blueprintName = null;

    public boolean isEmpty() {
        return blueprintName != null;
    }

    public String getName() {
        if (!isEmpty()) {
            return blueprintName;
        }
        return null;
    }

    public void setName(String blueprintName) {
        this.blueprintName = blueprintName;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide()) {

        } else {
            if (pPlayer.isShiftKeyDown()) {
                setName("TEST2");
            } else {
                setName("TEST");
            }
            if (!this.getDefaultInstance().hasTag()) {
                CompoundTag newTag = new CompoundTag();
                newTag.putString("blueprint_name", blueprintName);
                pPlayer.getItemInHand(pUsedHand).setTag(newTag);
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
