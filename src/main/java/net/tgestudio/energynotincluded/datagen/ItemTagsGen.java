package net.tgestudio.energynotincluded.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tgestudio.energynotincluded.EnergyNotIncluded;

import java.util.concurrent.CompletableFuture;

public class ItemTagsGen extends ItemTagsProvider {

    public ItemTagsGen(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(packOutput, lookupProvider, blockTags.contentsGetter(), EnergyNotIncluded.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
    }
}
