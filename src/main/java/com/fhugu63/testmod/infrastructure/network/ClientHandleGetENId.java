package com.fhugu63.testmod.infrastructure.network;

import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.fhugu63.testmod.infrastructure.codec_record.ChangeIdInBlockInNetwork;
import com.fhugu63.testmod.infrastructure.codec_record.ClickRecord;
import com.fhugu63.testmod.infrastructure.codec_record.EnergyNetworkRecord;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.fhugu63.testmod.infrastructure.energy_system.IEnergyBlockEntity;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientHandleGetENId {
    public static void handleDataOnMain(final EnergyNetworkRecord data, final IPayloadContext context) {
        EnergyBlockEntity energyBlockEntity = (EnergyBlockEntity) context.player().level().getBlockEntity(data.blockPosToChange());
        EnergyBlockEntity secondEnergyBlockEntity = (EnergyBlockEntity) context.player().level().getBlockEntity(data.blockPosToChange());

        EnergyNetwork energyNetwork = null;

        if (!EnergyNetworksContainer.containtEnergyNetwork(data.id())) {
            energyNetwork = new EnergyNetwork(data.floatList().get(0), data.floatList().get(1), data.floatList().get(2),
                    data.floatList().get(3), data.floatList().get(4), data.id(), data.blockPosesList());

            EnergyNetworksContainer.putEnergyNetwork(data.id(), energyNetwork);
        } else {
            energyNetwork = EnergyNetworksContainer.getEnergyNetwork(data.id());

            data.blockPosesList().forEach(blockPos -> {
                EnergyBlockEntity energyBlockEntity1 = (EnergyBlockEntity) context.player().level().getBlockEntity(blockPos);

                EnergyNetworksContainer.getEnergyNetwork(data.id()).addBlockEntityToNetwork(energyBlockEntity1);
            });
        }

        System.out.println(energyNetwork);

        energyBlockEntity.setEnergyNetworkId(data.id());
        secondEnergyBlockEntity.setEnergyNetworkId(data.id());

        energyBlockEntity.connectWireConnector(secondEnergyBlockEntity);
        secondEnergyBlockEntity.connectWireConnector(energyBlockEntity);

        energyBlockEntity.setChanged();
        secondEnergyBlockEntity.setChanged();
    }

    public static void handleDataChangeIdInNetworkOnMain(final ChangeIdInBlockInNetwork data, final IPayloadContext context) {
        EnergyNetworksContainer.getEnergyNetwork(data.id()).changeEnergyNetworkIdInConnectedBlocks(data.newId(), context.player().level());
    }
}
