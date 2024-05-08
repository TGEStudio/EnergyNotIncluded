package net.tgestudio.energynotincluded.registry_file;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tgestudio.energynotincluded.EnergyNotIncluded;
import net.tgestudio.energynotincluded.item.construction.Blueprint;

public class RegsItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EnergyNotIncluded.MOD_ID);

    public static final RegistryObject<Item> SANDSTONE_DEBRI = ITEMS.register("sandstone_debri",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUEPRINT = ITEMS.register("blueprint",
            () -> new Blueprint(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
