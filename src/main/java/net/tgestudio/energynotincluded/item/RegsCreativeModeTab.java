package net.tgestudio.energynotincluded.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.tgestudio.energynotincluded.EnergyNotIncluded;
import net.tgestudio.energynotincluded.registry_file.RegsBlocks;
import net.tgestudio.energynotincluded.registry_file.RegsItems;

public class RegsCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EnergyNotIncluded.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ENERGYNOTINCLUDED_TAB =
            CREATIVE_MODE_TABS.register("energynotincluded_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(RegsItems.SANDSTONE_DEBRI.get()))
                            .title(Component.translatable("creativetab.eni_tab"))
                            .displayItems((pParameters, pOutput) ->
                            {
                                pOutput.accept(RegsItems.SANDSTONE_DEBRI.get());
                                pOutput.accept((RegsItems.BLUEPRINT.get()));
                                //pOutput.accept(RegsBlocks.SLAVE.get());
                                pOutput.accept(RegsBlocks.MASTER.get());
                                pOutput.accept(RegsBlocks.CONSBLOCK.get());

                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
