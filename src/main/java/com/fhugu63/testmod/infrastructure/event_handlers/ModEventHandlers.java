package com.fhugu63.testmod.infrastructure.event_handlers;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlockEntityRenderer;
import com.fhugu63.testmod.content.energy.accumulator.CreativeAccumulatorBlockEntity;
import com.fhugu63.testmod.TestMod;
import com.fhugu63.testmod.content.energy.motor.BasicElectriMotorRenderer;
import com.fhugu63.testmod.infrastructure.codec_record.ChangeIdInBlockInNetwork;
import com.fhugu63.testmod.infrastructure.codec_record.ClickRecord;
import com.fhugu63.testmod.infrastructure.codec_record.EnergyNetworkRecord;
import com.fhugu63.testmod.infrastructure.network.ClientHandleGetENId;
import com.fhugu63.testmod.infrastructure.network.ServerHandleGetENId;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handlers.ServerPayloadHandler;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = TestMod.MODID)
public class ModEventHandlers {
    @SubscribeEvent  // on the mod event bus
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.EnergyStorage.BLOCK, // capability to register for
                AllBlockEntitys.CREATIVE_ACCUMULATOR_BLOCK_ENTITY.get(), // block entity type to register for
                (myBlockEntity, context) ->
                        CreativeAccumulatorBlockEntity.ENERGY_STORAGE_BLOCK.getCapability(
                                myBlockEntity.getLevel(),
                                myBlockEntity.getBlockPos(),
                                myBlockEntity.getBlockState(),
                                myBlockEntity,
                                context
                        )
        );
    }

    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                // The block entity type to register the renderer for.
                AllBlockEntitys.WIRE_CONNECTOR_BLOCK_ENTITY.get(),
                // A function of BlockEntityRendererProvider.Context to BlockEntityRenderer.
                WireConnectorBlockEntityRenderer::new
        );
        event.registerBlockEntityRenderer(
                // The block entity type to register the renderer for.
                AllBlockEntitys.BASIC_ELECTRIC_MOTOR_BE.get(),
                // A function of BlockEntityRendererProvider.Context to BlockEntityRenderer.
                BasicElectriMotorRenderer::new
        );
    }

    @SubscribeEvent // on the mod event bus
    public static void register(final RegisterPayloadHandlersEvent event) {
        // Sets the current network version
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playBidirectional(
                EnergyNetworkRecord.TYPE,
                EnergyNetworkRecord.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ClientHandleGetENId::handleDataOnMain,
                        ServerHandleGetENId::handleDataOnMain
                )
        );

        registrar.playBidirectional(
                ChangeIdInBlockInNetwork.TYPE,
                ChangeIdInBlockInNetwork.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        ClientHandleGetENId::handleDataChangeIdInNetworkOnMain,
                        ServerHandleGetENId::handleDataChangeIdInNetworkOnMain
                )
        );
    }
}
