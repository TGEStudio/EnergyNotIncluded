package net.tgestudio.energynotincluded.registry_file;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tgestudio.energynotincluded.EnergyNotIncluded;
import net.tgestudio.energynotincluded.screen.CoalGenMenu;
import net.tgestudio.energynotincluded.screen.ConsMenu;

public class RegsMenu {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, EnergyNotIncluded.MOD_ID);

    public static final RegistryObject<MenuType<ConsMenu>> CONSTRUCTOR_BLOCK_MENU =
            registerMenuType(ConsMenu::new, "constructor_block_menu");
    public static final RegistryObject<MenuType<CoalGenMenu>> COAL_GENERATOR_MENU =
            registerMenuType(CoalGenMenu::new, "coal_generator_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
