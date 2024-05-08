package net.tgestudio.energynotincluded.datagen;

import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import net.tgestudio.energynotincluded.EnergyNotIncluded;
import net.tgestudio.energynotincluded.registry_file.RegsBlocks;

import java.util.Map;
import java.util.stream.Collectors;

public class LootTablesGen extends VanillaBlockLoot {

    @Override
    protected void generate() {
        dropSelf(RegsBlocks.CONSBLOCK.get());
        //dropSelf(RegsBlocks.SLAVE.get());
        //dropSelf(RegsBlocks.PLACEABLE_BLOCK_WRONG.get());
        //dropSelf(RegsBlocks.PLACEABLE_BLOCK.get());
        //createStandardTable(RegsBlocks.MASTER.get(), RegsBlocks.MASTER_ENTITY.get());
        //createStandardTable(RegsBlocks.SLAVE.get(), RegsBlocks.SLAVE_ENTITY.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(EnergyNotIncluded.MOD_ID))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
