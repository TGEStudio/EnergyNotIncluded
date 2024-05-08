package net.tgestudio.energynotincluded.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.tgestudio.energynotincluded.EnergyNotIncluded;
import net.tgestudio.energynotincluded.registry_file.RegsBlocks;
import net.tgestudio.energynotincluded.registry_file.RegsItems;

public class ItemModelsGen extends ItemModelProvider {

    public ItemModelsGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, EnergyNotIncluded.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(RegsItems.SANDSTONE_DEBRI.getId());
        basicItem(RegsItems.BLUEPRINT.getId());
        withExistingParent(RegsBlocks.COAL_GENERATOR.getId().getPath(), modLoc("block/coal_generator"));
        //withExistingParent(RegsBlocks.PLACEABLE_BLOCK_WRONG.getId().getPath(), modLoc("block/placeable_block_wrong"));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation(EnergyNotIncluded.MOD_ID, "item/" + item.getId().getPath()));
    }
}
