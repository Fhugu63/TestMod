package com.fhugu63.testmod.infrastructure.network;

import com.fhugu63.testmod.TestMod;
import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlockEntity;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.fhugu63.testmod.infrastructure.codec_record.ChangeIdInBlockInNetwork;
import com.fhugu63.testmod.infrastructure.codec_record.ClickRecord;
import com.fhugu63.testmod.infrastructure.codec_record.ConnectWireConnectorsRecord;
import com.fhugu63.testmod.infrastructure.codec_record.EnergyNetworkRecord;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.fhugu63.testmod.infrastructure.energy_system.IEnergyBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientHandleGetENId {
    public static void handleDataOnMain(final EnergyNetworkRecord data, final IPayloadContext context) {
        EnergyBlockEntity energyBlockEntity = (EnergyBlockEntity) context.player().level().getBlockEntity(data.blockPosToChange());

        EnergyNetwork.clientEnergyNetwork energyNetwork = null;

        if (!EnergyNetworksContainer.clientEnergyNetworksContainer.containtEnergyNetwork(data.id())) {
            energyNetwork = new EnergyNetwork.clientEnergyNetwork(data.totalEnergy(), data.r(), data.g(),
                    data.b(), data.id());

            EnergyNetworksContainer.clientEnergyNetworksContainer.putEnergyNetwork(data.id(), energyNetwork);
        } else {
            energyNetwork = EnergyNetworksContainer.clientEnergyNetworksContainer.getEnergyNetwork(data.id());
        }

        System.out.println(energyNetwork);

        energyBlockEntity.setEnergyNetworkId(data.id());
        energyBlockEntity.setChanged();
    }

    public static void handleDataChangeIdInNetworkOnMain(final ChangeIdInBlockInNetwork data, final IPayloadContext context) {
        EnergyNetworksContainer.getEnergyNetwork(data.id()).changeEnergyNetworkIdInConnectedBlocks(data.newId(), context.player().level());
    }

    public static void handleClickOnWireConnector(final ClickRecord data, final IPayloadContext context) {
        TestMod.LOGGER.error("Client got server packet!");
    }

    public static void handleConnectWireConnectors(final ConnectWireConnectorsRecord data, final IPayloadContext context) {
        Level level = context.player().level();

        WireConnectorBlockEntity firstWireConnector = (WireConnectorBlockEntity) level.getBlockEntity(data.firstBlockPos());
        WireConnectorBlockEntity secondWireConnector = (WireConnectorBlockEntity) level.getBlockEntity(data.secondBlockPos());

        firstWireConnector.connectWireConnector(secondWireConnector);
        secondWireConnector.connectWireConnector(firstWireConnector);

        EnergyNetwork.clientEnergyNetwork clientEnergyNetwork = new EnergyNetwork.clientEnergyNetwork(
                data.energyNetwork().totalEnergy(), data.energyNetwork().r(), data.energyNetwork().g(), data.energyNetwork().b(),
                data.energyNetwork().id()
        );

        EnergyNetworksContainer.clientEnergyNetworksContainer.putEnergyNetwork(clientEnergyNetwork.getId(), clientEnergyNetwork);

        firstWireConnector.setEnergyNetworkId(clientEnergyNetwork.getId());
        secondWireConnector.setEnergyNetworkId(clientEnergyNetwork.getId());
    }
}
