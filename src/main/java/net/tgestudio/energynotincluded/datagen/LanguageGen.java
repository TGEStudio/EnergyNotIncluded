package net.tgestudio.energynotincluded.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.tgestudio.energynotincluded.EnergyNotIncluded;


public class LanguageGen extends LanguageProvider {

    public LanguageGen(PackOutput output, String locale) {
        super(output, EnergyNotIncluded.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        //add(RegsBlocks.ALGAE_BLOCK.get(), "Algae Block");
    }
}
