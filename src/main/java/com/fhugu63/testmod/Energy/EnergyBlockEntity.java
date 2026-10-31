package com.fhugu63.testmod.Energy;

import com.fhugu63.testmod.BlockEntitys.WireConnectorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class EnergyBlockEntity extends BlockEntity {
    EnergyNetwork energyNetwork = null;
    private ArrayList<EnergyBlockEntity> connectedWith = new ArrayList<EnergyBlockEntity>();
    public float gettedEnergy = 0;

    public EnergyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public boolean energyNetworkNull() {
        if (energyNetwork == null) {
            return true;
        }

        return false;
    }

    public EnergyNetwork getEnergyNetwork() {
        return energyNetwork;
    }

    public void setEnergyNetwork(EnergyNetwork newEnergyNetwork) {
        this.energyNetwork = newEnergyNetwork;
    }

    public ArrayList<EnergyBlockEntity> getConnectedWireConnectors() {
        return connectedWith;
    }

    public void connectWireConnector(EnergyBlockEntity connectedTo) {
        this.connectedWith.add(connectedTo);
    }

    public void disConnectWireConnector(WireConnectorBlockEntity wireConnectorBlockEntity) {
        this.connectedWith.remove(wireConnectorBlockEntity);
    }

}
