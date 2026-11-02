package com.fhugu63.testmod.infrastructure.event_handlers;

import com.fhugu63.testmod.AllBlocks;
import com.fhugu63.testmod.TestMod;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = TestMod.MODID, value = Dist.CLIENT)
public class ClientEventHandlers {
    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        ItemBlockRenderTypes.setRenderLayer(AllBlocks.LAMP_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(AllBlocks.WIRE_CONNECTOR.get(), RenderType.translucent());
    }
}
