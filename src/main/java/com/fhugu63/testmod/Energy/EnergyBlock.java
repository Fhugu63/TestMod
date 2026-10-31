package com.fhugu63.testmod.Energy;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EnergyBlock extends Block {
    public EnergyBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        EnergyBlockEntity energyBlockEntity = (EnergyBlockEntity) level.getBlockEntity(pos);

        if (!energyBlockEntity.energyNetworkNull()) {
            energyBlockEntity.getEnergyNetwork().removeBlockEntityFromNetwork(energyBlockEntity);
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
