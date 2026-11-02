package com.fhugu63.testmod.content.energy.wire;

import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

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
        System.out.println("clicked!");
        System.out.println(level.getBlockEntity(clickedBlockPos));

        if (level.getBlockEntity(clickedBlockPos) == null) return InteractionResult.PASS;
        if (level.getBlockEntity(clickedBlockPos).getClass() == EnergyBlockEntity.class) return InteractionResult.PASS;

        EnergyBlockEntity wireConnector = (EnergyBlockEntity) level.getBlockEntity(clickedBlockPos);
        System.out.println("thats needed block!");

        assert wireConnector != null;

        if (firstConnect == null) {
            context.getPlayer().displayClientMessage(Component.translatable("Первый объект выбран"), true);

            firstConnect = wireConnector;
        } else {
            context.getPlayer().displayClientMessage(Component.translatable("Второй объект выбран"), true);

            secondConnect = wireConnector;

            firstConnect.connectWireConnector(secondConnect);
            secondConnect.connectWireConnector(firstConnect);

                if (!firstConnect.energyNetworkNull() && !secondConnect.energyNetworkNull()) {
                    if (firstConnect.getEnergyNetwork() != secondConnect.getEnergyNetwork()) {
                        if (firstConnect.getEnergyNetwork().getCountOfConnectedBlocks() >
                                secondConnect.getEnergyNetwork().getCountOfConnectedBlocks()) {
                            secondConnect.getEnergyNetwork().changeEnergyNetworkInConnectedBlocks(firstConnect.getEnergyNetwork());
                        } else if (firstConnect.getEnergyNetwork().getCountOfConnectedBlocks() <
                                secondConnect.getEnergyNetwork().getCountOfConnectedBlocks()) {
                            firstConnect.getEnergyNetwork().changeEnergyNetworkInConnectedBlocks(secondConnect.getEnergyNetwork());
                        } else {
                            firstConnect.getEnergyNetwork().changeEnergyNetworkInConnectedBlocks(secondConnect.getEnergyNetwork());
                        }
                    }
                } else if (!firstConnect.energyNetworkNull()) {
                energyNetwork = firstConnect.getEnergyNetwork();

                if (!secondConnect.energyNetworkNull()) {
                    energyNetwork.giveEnergy(secondConnect.getEnergyNetwork().getTotalEnergy());

                    secondConnect.getEnergyNetwork().changeEnergyNetworkInConnectedBlocks(energyNetwork);
                } else {
                    secondConnect.setEnergyNetwork(energyNetwork);
                    energyNetwork.addBlockEntityToNetwork(secondConnect);
                }
            } else if (!secondConnect.energyNetworkNull()) {
                energyNetwork = secondConnect.getEnergyNetwork();

                if (!firstConnect.energyNetworkNull()) {
                    energyNetwork.giveEnergy(firstConnect.getEnergyNetwork().getTotalEnergy());

                    firstConnect.getEnergyNetwork().changeEnergyNetworkInConnectedBlocks(energyNetwork);
                } else {
                    firstConnect.setEnergyNetwork(energyNetwork);
                    energyNetwork.addBlockEntityToNetwork(firstConnect);
                }
            } else {
                energyNetwork = new EnergyNetwork();

                firstConnect.setEnergyNetwork(energyNetwork);
                secondConnect.setEnergyNetwork(energyNetwork);

                energyNetwork.addBlockEntityToNetwork(firstConnect);
                energyNetwork.addBlockEntityToNetwork(secondConnect);
            }

            firstConnect = null;
            secondConnect = null;

            context.getPlayer().displayClientMessage(Component.translatable("Успешно соеденено!"), true);
        }

        return InteractionResult.PASS;
    }
}
