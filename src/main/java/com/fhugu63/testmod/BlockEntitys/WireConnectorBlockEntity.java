package com.fhugu63.testmod.BlockEntitys;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.Blocks.WireConnectorBlock;
import com.fhugu63.testmod.Energy.EnergyBlockEntity;
import com.fhugu63.testmod.Energy.EnergyNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

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

    public static void tick(Level level, BlockPos pos, BlockState state, WireConnectorBlockEntity blockEntity) {

    }
}
