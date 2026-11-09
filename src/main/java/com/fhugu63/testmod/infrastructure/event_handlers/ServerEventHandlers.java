package com.fhugu63.testmod.infrastructure.event_handlers;

import com.fhugu63.testmod.TestMod;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainerCodec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

@EventBusSubscriber(modid = TestMod.MODID, value = Dist.DEDICATED_SERVER)
public class ServerEventHandlers {
    @SubscribeEvent
    public static void onServerStart(ServerStartedEvent event) {
        //DataResult<EnergyNetworksContainer> result =
                //EnergyNetworksContainerCodec.energyNetworkContainerCodec.parse(JsonOps.INSTANCE, exampleJson);
    }

    @SubscribeEvent
    public static void onServerClose(ServerStoppingEvent event) {

    }
}
