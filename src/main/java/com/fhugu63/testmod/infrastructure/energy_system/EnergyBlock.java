package com.fhugu63.testmod.infrastructure.energy_system;

import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlockEntity;
import com.fhugu63.testmod.infrastructure.EnergyNetworksContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class EnergyBlock extends Block {
    public EnergyBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        EnergyBlockEntity energyBlockEntity = (EnergyBlockEntity) level.getBlockEntity(pos);

        if (energyBlockEntity.haveEnergyNetwork()) {
            EnergyNetworksContainer.getEnergyNetwork(energyBlockEntity.getEnergyNetworkId())
                    .removeBlockEntityFromNetwork(energyBlockEntity);
        }

        /*for (EnergyBlockEntity connectedEnergyBlockEntity : energyBlockEntity.getConnectedWireConnectors()) {
            connectedEnergyBlockEntity.disConnectWireConnector(energyBlockEntity);
        }*/

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        EnergyBlockEntity energyBlockEntity = (EnergyBlockEntity) level.getBlockEntity(pos);
        energyBlockEntity.onPlace();
    }
}
