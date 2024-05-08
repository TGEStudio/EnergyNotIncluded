package net.tgestudio.energynotincluded;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tgestudio.energynotincluded.datagen.DataGeneration;
import net.tgestudio.energynotincluded.item.RegsCreativeModeTab;
import net.tgestudio.energynotincluded.registry_file.RegsBlocks;
import net.tgestudio.energynotincluded.registry_file.RegsItems;
import net.tgestudio.energynotincluded.registry_file.RegsMenu;
import org.slf4j.Logger;

@Mod(EnergyNotIncluded.MOD_ID)
public class EnergyNotIncluded
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "energynotincluded";

    private static final Logger LOGGER = LogUtils.getLogger();

    public EnergyNotIncluded()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RegsCreativeModeTab.register(modEventBus);
        RegsItems.register(modEventBus);
        RegsBlocks.register(modEventBus);
        RegsMenu.register(modEventBus);


        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(DataGeneration::generate);

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code

        }
    }
}
