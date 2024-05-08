package net.tgestudio.energynotincluded.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.tgestudio.energynotincluded.EnergyNotIncluded;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BlockTagsGen extends BlockTagsProvider {

    public BlockTagsGen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, EnergyNotIncluded.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
