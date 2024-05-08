package net.tgestudio.energynotincluded.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.tgestudio.energynotincluded.EnergyNotIncluded;
import net.tgestudio.energynotincluded.registry_file.RegsBlocks;

public class BlockStatesGen extends BlockStateProvider {

    public BlockStatesGen(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, EnergyNotIncluded.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //blockWithItem(RegsBlocks.PLACEABLE_BLOCK);
        blockWithItem(RegsBlocks.SLAVE);
        //blockWithItem(RegsBlocks.CONSBLOCK);
        simpleBlock(RegsBlocks.COAL_GENERATOR.get(), new ModelFile.UncheckedModelFile(modLoc("block/coal_generator")));
        //simpleBlock(RegsBlocks.PLACEABLE_BLOCK_WRONG.get(),new ModelFile.UncheckedModelFile(modLoc("block/placeable_block_wrong")));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
