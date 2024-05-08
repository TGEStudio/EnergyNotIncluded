package net.tgestudio.energynotincluded.client;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.tgestudio.energynotincluded.block.construction.construct_base.ConsRenderer;
import net.tgestudio.energynotincluded.block.custom_multiblock.CoalGeneratorRenderer;
import net.tgestudio.energynotincluded.registry_file.RegsBlocks;
import net.tgestudio.energynotincluded.registry_file.RegsMenu;
import net.tgestudio.energynotincluded.screen.ConsScreen;

import static net.tgestudio.energynotincluded.EnergyNotIncluded.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void initClient(EntityRenderersEvent.RegisterRenderers event) {
        //event.registerBlockEntityRenderer(RegsBlocks.MASTER_ENTITY.get(), MultiBlockTestRenderer::new);
        event.registerBlockEntityRenderer(RegsBlocks.CONSBLOCK_ENTITY.get(), ConsRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(RegsBlocks.CONSBLOCK.get(), RenderType.cutout());
        event.registerBlockEntityRenderer(RegsBlocks.COAL_GENERATOR_ENTITY.get(), CoalGeneratorRenderer::new);


    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(RegsMenu.CONSTRUCTOR_BLOCK_MENU.get(), ConsScreen::new);
        //BlockRenderType.setRenderLayer(RegsBlocks.CONSBLOCK.get(), RenderType.cutout());

    }
}
