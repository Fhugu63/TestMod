package com.fhugu63.testmod.content.energy.wire;

import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import com.fhugu63.testmod.infrastructure.codec_record.ChangeIdInBlockInNetwork;
import com.fhugu63.testmod.infrastructure.codec_record.EnergyNetworkRecord;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import com.fhugu63.testmod.infrastructure.energy_system.IEnergyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Objects;

public class CopperWireItem extends Item {
    EnergyBlockEntity firstConnect;
    EnergyBlockEntity secondConnect;

    public CopperWireItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) return InteractionResult.PASS;

        BlockPos clickedBlockPos = context.getClickedPos();
        Level level = context.getLevel();
        EnergyNetwork energyNetwork = null;

        if (level.getBlockEntity(clickedBlockPos) == null) return InteractionResult.PASS;
        if (level.getBlockEntity(clickedBlockPos).getClass() == EnergyBlockEntity.class) return InteractionResult.PASS;

        EnergyBlockEntity wireConnector = (EnergyBlockEntity) level.getBlockEntity(clickedBlockPos);

        assert wireConnector != null;

        if (firstConnect == null) {
            firstConnect = wireConnector;

            context.getPlayer().displayClientMessage(Component.translatable("Первый объеёт выбран!"), true);
        } else {
            context.getPlayer().displayClientMessage(Component.translatable("Второй объеёт выбран!"), true);
            secondConnect = wireConnector;

            EnergyNetwork firstEnergyNetwork = EnergyNetworksContainer.getEnergyNetwork(firstConnect.getEnergyNetworkId());
            EnergyNetwork secondEnergyNetwork = EnergyNetworksContainer.getEnergyNetwork(secondConnect.getEnergyNetworkId());

            if (firstConnect.haveEnergyNetwork() && !secondConnect.haveEnergyNetwork()) {
                energyNetwork = firstEnergyNetwork;

                secondConnect.setEnergyNetworkId(firstEnergyNetwork.getId());
            } else if (!firstConnect.haveEnergyNetwork() && secondConnect.haveEnergyNetwork()) {
                energyNetwork = secondEnergyNetwork;

                firstConnect.setEnergyNetworkId(secondEnergyNetwork.getId());
            } else if (firstConnect.haveEnergyNetwork() && secondConnect.haveEnergyNetwork()) {
                energyNetwork = firstEnergyNetwork;

                secondEnergyNetwork.changeEnergyNetworkIdInConnectedBlocks(energyNetwork.getId(), context.getLevel());
                secondConnect.setEnergyNetworkId(firstEnergyNetwork.getId());

                EnergyNetworksContainer.removeEnergyNetwork(secondEnergyNetwork.getId());

                PacketDistributor.sendToServer(new ChangeIdInBlockInNetwork(secondEnergyNetwork.getId(), energyNetwork.getId()));
            } else {
                energyNetwork = EnergyNetworksContainer.createEnergyNetwork();
            }

            context.getPlayer().displayClientMessage(Component.translatable("Успешно соеденено!"), true);

            ArrayList<Float> floatList = new ArrayList<>();

            floatList.add(energyNetwork.getTotalEnergy());
            floatList.add(energyNetwork.getMaxCanContainsEnergy());

            floatList.add(energyNetwork.getR());
            floatList.add(energyNetwork.getG());
            floatList.add(energyNetwork.getB());

            PacketDistributor.sendToServer(
                    new EnergyNetworkRecord(floatList, energyNetwork.getId(),
                            energyNetwork.getEnergyBlocksPoses(), firstConnect.getBlockPos(), secondConnect.getBlockPos())
            );

            firstConnect = null;
            secondConnect = null;
        }

        return InteractionResult.PASS;
    }
}
