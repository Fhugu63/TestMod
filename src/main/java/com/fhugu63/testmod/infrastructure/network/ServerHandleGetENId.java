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
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.HashMap;

public class ServerHandleGetENId {
    public static HashMap<Player, WireConnectorBlockEntity> map = new HashMap<>();

    public static void handleDataOnMain(final EnergyNetworkRecord data, final IPayloadContext context) {
        EnergyBlockEntity energyBlockEntity = (EnergyBlockEntity) context.player().level().getBlockEntity(data.blockPosToChange());
        EnergyBlockEntity secondEnergyBlockEntity = (EnergyBlockEntity) context.player().level().getBlockEntity(data.blockPosToChange());

        EnergyNetwork energyNetwork = null;


    }

    public static void handleDataChangeIdInNetworkOnMain(final ChangeIdInBlockInNetwork data, final IPayloadContext context) {
        EnergyNetworksContainer.getEnergyNetwork(data.id()).changeEnergyNetworkIdInConnectedBlocks(data.newId(), context.player().level());

        PacketDistributor.sendToAllPlayers(new ChangeIdInBlockInNetwork(data.id(), data.newId()));
    }

    public static void handleClickOnWireConnector(final ClickRecord data, final IPayloadContext context) {
        Player player = context.player();
        Level level = player.level();

        WireConnectorBlockEntity wireConnectorBlockEntity = (WireConnectorBlockEntity) level.getBlockEntity(data.blockPos());

        if (map.containsKey(player)) {
            WireConnectorBlockEntity wireConnectorBlockEntity1 = map.get(player);

            EnergyNetwork energyNetwork = null;

            if (!wireConnectorBlockEntity.haveEnergyNetwork() && !wireConnectorBlockEntity1.haveEnergyNetwork()) {
                energyNetwork = EnergyNetworksContainer.createEnergyNetwork();

                wireConnectorBlockEntity.setEnergyNetworkId(energyNetwork.getId());
                wireConnectorBlockEntity1.setEnergyNetworkId(energyNetwork.getId());

                energyNetwork.addBlockEntityToNetwork(wireConnectorBlockEntity);
                energyNetwork.addBlockEntityToNetwork(wireConnectorBlockEntity1);
            } else if (wireConnectorBlockEntity1.haveEnergyNetwork()) {
                energyNetwork = EnergyNetworksContainer.getEnergyNetwork(wireConnectorBlockEntity1.getEnergyNetworkId());

                wireConnectorBlockEntity.setEnergyNetworkId(energyNetwork.getId());
                energyNetwork.addBlockEntityToNetwork(wireConnectorBlockEntity);
            } else {
                energyNetwork = EnergyNetworksContainer.getEnergyNetwork(wireConnectorBlockEntity.getEnergyNetworkId());

                wireConnectorBlockEntity1.setEnergyNetworkId(energyNetwork.getId());
                energyNetwork.addBlockEntityToNetwork(wireConnectorBlockEntity1);
            }

            PacketDistributor.sendToAllPlayers(new ConnectWireConnectorsRecord(
                    new EnergyNetworkRecord(energyNetwork.getTotalEnergy(), energyNetwork.getR(), energyNetwork.getG(), energyNetwork.getB(),
                            wireConnectorBlockEntity.getBlockPos(), energyNetwork.getId()),
                    wireConnectorBlockEntity.getBlockPos(), wireConnectorBlockEntity1.getBlockPos()
            ));

            map.remove(player);

            TestMod.LOGGER.info("handled!");
        } else {
            map.put(player, wireConnectorBlockEntity);

            TestMod.LOGGER.info("putted!");
        }

        /*BlockPos blockPos = wireConnectorBlockEntity.playerCickOn(player, data.blockPos());

        if (blockPos != null) {
            PacketDistributor.sendToAllPlayers(new ConnectWireConnectorsRecord(data.blockPos(), blockPos));
        }*/
    }

    public static void handleConnectWireConnectors(final ConnectWireConnectorsRecord data, final IPayloadContext context) {
        TestMod.LOGGER.error("Server got client packet!");
    }
}
