package com.fhugu63.testmod.content.energy.conductor;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.infrastructure.energy_system.IEnergyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class WireConnectorBlockEntity extends EnergyBlockEntity implements IEnergyBlockEntity {
    String energyNetworkIdName = "networkId";
    List<WireConnectorBlockEntity> connectedWireConnectors = new ArrayList<>();

    public WireConnectorBlockEntity(BlockPos pos, BlockState blockState) {
        super(AllBlockEntitys.WIRE_CONNECTOR_BLOCK_ENTITY.get(), pos, blockState);
    }

    public List<WireConnectorBlockEntity> getConnectedWireConnectors() {
        return this.connectedWireConnectors;
    }

    public void connectWireConnector(WireConnectorBlockEntity connectedTo) {
        this.getConnectedWireConnectors().add(connectedTo);

        if (!this.getBlockState().getValue(WireConnectorBlock.booleanPropertyConnected)) {
            BlockState newBlockState = this.getBlockState().setValue(WireConnectorBlock.booleanPropertyConnected, true);

            level.setBlock(this.getBlockPos(), newBlockState, Block.UPDATE_ALL);
        }
    }

    public void disConnectWireConnector(WireConnectorBlockEntity energyBlockEntity) {
        this.getConnectedWireConnectors().remove(energyBlockEntity);

        if (this.getConnectedWireConnectors().isEmpty()) {
            level.setBlock(this.getBlockPos(), this.getBlockState().setValue(WireConnectorBlock.booleanPropertyConnected, false), Block.UPDATE_ALL);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, WireConnectorBlockEntity blockEntity) {

    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.setEnergyNetworkId(tag.getLong(energyNetworkIdName));
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        if (this.haveEnergyNetwork()) {
            tag.putLong(energyNetworkIdName, this.getEnergyNetworkId());
        }
    }

    @Override
    public boolean haveEnergyNetwork() {
        return false;
    }

    @Override
    public Long getEnergyNetworkId() {
        return 0L;
    }

    @Override
    public void setEnergyNetworkId(Long newEnergyNetworkId) {

    }

    @Override
    public void tick() {

    }

    @Override
    public void onPlace() {

    }

    @Override
    public void onDestroy() {

    }
}
