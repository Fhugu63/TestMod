package com.fhugu63.testmod.content.energy.accumulator;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import com.fhugu63.testmod.TestMod;
import com.fhugu63.testmod.content.energy.conductor.WireConnectorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.BlockCapability;
import net.neoforged.neoforge.energy.IEnergyStorage;

import java.util.ArrayList;

public class CreativeAccumulatorBlockEntity extends EnergyBlockEntity {
    public static final BlockCapability<IEnergyStorage, Direction> ENERGY_STORAGE_BLOCK =
            BlockCapability.create(
                    // Provide a name to uniquely identify the capability.
                    ResourceLocation.fromNamespaceAndPath(TestMod.MODID, "energy_storage_block"),
                    // Provide the queried type. Here, we want to look up `IItemHandler` instances.
                    IEnergyStorage.class,
                    Direction.class
            );

    ArrayList<WireConnectorBlockEntity> connectedWithWireConnectors;

    public CreativeAccumulatorBlockEntity(BlockPos pos, BlockState blockState) {
        super(AllBlockEntitys.CREATIVE_ACCUMULATOR_BLOCK_ENTITY.get(), pos, blockState);
    }

    public void connectWireConnector(WireConnectorBlockEntity wireConnectorBlockEntity ) {
        connectedWithWireConnectors.add(wireConnectorBlockEntity);
    }

    /*public IEnergyStorage getEnergyStorage() {
        return this.level.getCapability(ENERGY_STORAGE_BLOCK, this.worldPosition);
    }*/

    public static void tick(Level level, BlockPos pos, BlockState state, CreativeAccumulatorBlockEntity blockEntity) {
        if (!blockEntity.energyNetworkNull()) {
            blockEntity.getEnergyNetwork().giveEnergy(200);
        }
    }
}
