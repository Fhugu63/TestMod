package com.fhugu63.testmod.content.energy.lamp;

import com.fhugu63.testmod.AllBlockEntitys;
import com.fhugu63.testmod.infrastructure.energy_system.EnergyBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class LampBlockEntity extends EnergyBlockEntity {
    boolean light = false;
    public LampBlockEntity(BlockPos pos, BlockState blockState) {
        super(AllBlockEntitys.LAMP_BLOCK_ENTITY.get(), pos, blockState);
    }



    public static void tick(Level level, BlockPos pos, BlockState state, LampBlockEntity blockEntity) {
        /*if (blockEntity.getEnergyNetwork() != null) {
            blockEntity.gettedEnergy = blockEntity.getEnergyNetwork().getEnergy(100);
            //System.out.println(blockEntity.gettedEnergy);
            //System.out.println(blockEntity.getEnergyNetwork().getTotalEnergy());
        }*/
    }
}
