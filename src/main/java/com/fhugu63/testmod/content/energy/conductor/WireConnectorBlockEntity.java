package com.fhugu63.testmod.content.energy.conductor;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WireConnectorBlockEntity extends EnergyBlockEntity {
    public WireConnectorBlockEntity(BlockPos pos, BlockState blockState) {
        super(AllBlockEntitys.WIRE_CONNECTOR_BLOCK_ENTITY.get(), pos, blockState);
    }

    /*// Read values from the passed CompoundTag here.
    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        // Will default to 0 if absent. See the NBT article for more information.

        this.connectedConnectors.;
    }

    // Save values into the passed CompoundTag here.
    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putUUID("value", this.value);
    }*/

    @Override
    public void connectWireConnector(EnergyBlockEntity connectedTo) {
        this.getConnectedWireConnectors().add(connectedTo);

        if (!this.getBlockState().getValue(WireConnectorBlock.booleanPropertyConnected)) {
            BlockState newBlockState = this.getBlockState().setValue(WireConnectorBlock.booleanPropertyConnected, true);

            level.setBlock(this.getBlockPos(), newBlockState, Block.UPDATE_ALL);
        }
    }

    @Override
    public void disConnectWireConnector(EnergyBlockEntity energyBlockEntity) {
        this.getConnectedWireConnectors().remove(energyBlockEntity);

        if (this.getConnectedWireConnectors().isEmpty()) {
            level.setBlock(this.getBlockPos(), this.getBlockState().setValue(WireConnectorBlock.booleanPropertyConnected, false), Block.UPDATE_ALL);
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, WireConnectorBlockEntity blockEntity) {

    }


}
